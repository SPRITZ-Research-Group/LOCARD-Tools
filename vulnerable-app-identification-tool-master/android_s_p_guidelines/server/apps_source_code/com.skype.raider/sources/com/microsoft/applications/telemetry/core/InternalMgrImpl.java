package com.microsoft.applications.telemetry.core;

import android.content.Context;
import android.content.IntentFilter;
import com.microsoft.applications.telemetry.EventProperties;
import com.microsoft.applications.telemetry.ILogger;
import com.microsoft.applications.telemetry.ISemanticContext;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.PiiKind;
import com.microsoft.applications.telemetry.TransmitProfile;
import com.microsoft.applications.telemetry.pal.hardware.DeviceInformation;
import com.microsoft.applications.telemetry.pal.hardware.HardwareInformationReceiver;
import com.microsoft.applications.telemetry.pal.hardware.NetworkInformation;
import com.microsoft.applications.telemetry.pal.hardware.SystemInformation;
import java.util.Date;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class InternalMgrImpl {
    private static final String LOG_TAG = ("[ACT]:" + InternalMgrImpl.class.getSimpleName().toUpperCase());
    private static final Lock READ_LOCK;
    private static final ReadWriteLock READ_WRITE_LOCK;
    private static final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();
    private static Context appContext;
    private static LogConfiguration config;
    private static EventProperties contextProperties = new EventProperties("");
    private static HardwareInformationReceiver hardwareReceiver = null;
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static boolean isTearDownCompleted = false;
    private static ConcurrentHashMap<String, ILogger> loggerHashMap = new ConcurrentHashMap();
    private static EventMessenger messenger;
    private static final SemanticContext semanticContext = new SemanticContext(true);
    private static ConcurrentHashMap<String, String> tenantTokenInitIdHashMap = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, AtomicLong> tenantTokenSequenceHashMap = new ConcurrentHashMap();

    static {
        ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        READ_WRITE_LOCK = reentrantReadWriteLock;
        READ_LOCK = reentrantReadWriteLock.readLock();
    }

    public static void deregisterEventListener(ITransmissionEvents eventListener) {
        messenger.deregisterEventListener(eventListener);
    }

    public static void flush() {
    }

    public static void flushAndTearDown() {
        WRITE_LOCK.lock();
        try {
            if (!isTearDownCompleted) {
                teardownReceiver();
                if (messenger != null) {
                    messenger.flushAndTeardown();
                }
                isTearDownCompleted = true;
            }
            WRITE_LOCK.unlock();
        } catch (Throwable th) {
            WRITE_LOCK.unlock();
        }
    }

    public static LogConfiguration getConfig() {
        return config;
    }

    protected static EventProperties getContextProperties() {
        return contextProperties;
    }

    protected static String getInitIdForTenantToken(String tenantToken) {
        Object tenantToken2;
        if (tenantToken2.isEmpty()) {
            tenantToken2 = config.getTenantToken();
        }
        tenantTokenInitIdHashMap.putIfAbsent(tenantToken2, UUID.randomUUID().toString());
        return (String) tenantTokenInitIdHashMap.get(tenantToken2);
    }

    public static AtomicBoolean getIsInitialized() {
        return isInitialized;
    }

    public static ILogger getLogger() {
        return getLogger("");
    }

    public static ILogger getLogger(String source) {
        Preconditions.isNotNull(source, "source cannot be null.");
        if (isInitialized.get() && source.isEmpty()) {
            source = config.getSource();
        }
        return getLoggerInternal(source, "");
    }

    public static ILogger getLogger(String source, String tenantToken) {
        Preconditions.isNotNull(source, "source cannot be null.");
        Preconditions.isNotNull(tenantToken, "tenantToken cannot be null");
        if (isInitialized.get() && source.isEmpty()) {
            source = config.getSource();
        }
        return getLoggerInternal(source, tenantToken);
    }

    private static ILogger getLoggerInternal(String source, String tenantToken) {
        READ_LOCK.lock();
        source = source.toLowerCase();
        String sourceKey = source;
        tenantToken = tenantToken.toLowerCase();
        try {
            if (isInitialized.get()) {
                sourceKey = source.equals(config.getSource()) ? "" : source;
                if (!loggerHashMap.containsKey(tenantToken + sourceKey)) {
                    String tenantToken2;
                    ConcurrentHashMap concurrentHashMap = loggerHashMap;
                    String str = tenantToken + sourceKey;
                    IEventMessenger iEventMessenger = messenger;
                    if (tenantToken.isEmpty()) {
                        tenantToken2 = config.getTenantToken();
                    } else {
                        tenantToken2 = tenantToken;
                    }
                    concurrentHashMap.put(str, new Logger(iEventMessenger, source, tenantToken2));
                }
            } else if (!loggerHashMap.containsKey(tenantToken + sourceKey)) {
                loggerHashMap.put(tenantToken + sourceKey, new Logger(source, tenantToken));
            }
            READ_LOCK.unlock();
            return (ILogger) loggerHashMap.get(tenantToken + sourceKey);
        } catch (Throwable th) {
            READ_LOCK.unlock();
        }
    }

    protected static EventMessenger getMessenger() {
        return messenger;
    }

    public static ISemanticContext getSemanticContext() {
        return semanticContext;
    }

    protected static String getSequenceForTenantToken(String tenantToken) {
        Object tenantToken2;
        if (tenantToken2.isEmpty()) {
            tenantToken2 = config.getTenantToken();
        }
        if (!tenantTokenSequenceHashMap.containsKey(tenantToken2)) {
            tenantTokenSequenceHashMap.put(tenantToken2, new AtomicLong(1));
        }
        return String.valueOf(((AtomicLong) tenantTokenSequenceHashMap.get(tenantToken2)).getAndIncrement());
    }

    public static ILogger initialize(String tenantToken, LogConfiguration configuration, Context context) {
        WRITE_LOCK.lock();
        try {
            if (isTearDownCompleted) {
                throw new IllegalStateException("Initialize cannot be called after tear down.");
            }
            if (!isInitialized.get()) {
                isInitialized.set(true);
                appContext = (Context) Preconditions.isNotNull(context, "Context cannot be null.");
                Preconditions.isNotNull(configuration, "LogConfiguration cannot be null.");
                config = new LogConfiguration(configuration);
                config.setTenantToken(tenantToken.toLowerCase());
                EventMessenger eventMessenger = new EventMessenger(config, appContext);
                messenger = eventMessenger;
                eventMessenger.start();
                markLoggersInitialized();
                DeviceInformation.update(appContext);
                NetworkInformation.checkForNetworkAccess(appContext);
                if (NetworkInformation.hasNetworkAccessPermission()) {
                    NetworkInformation.update(appContext);
                }
                SystemInformation.initializeAppInfo(appContext);
                setupReceiver();
                populateCommonFields();
            }
            WRITE_LOCK.unlock();
            return getLogger();
        } catch (Throwable th) {
            WRITE_LOCK.unlock();
        }
    }

    private static void markLoggersInitialized() {
        for (Entry value : loggerHashMap.entrySet()) {
            ((Logger) ((ILogger) value.getValue())).markLoggingEnabled(messenger, config.getSource(), config.getTenantToken());
        }
    }

    public static synchronized void pauseTransmission(boolean isPausedByUser) {
        synchronized (InternalMgrImpl.class) {
            TraceHelper.TraceVerbose(LOG_TAG, "pauseTransmission, isPausedByUser: " + isPausedByUser);
            messenger.pauseTransmission(isPausedByUser);
        }
    }

    private static void populateCommonFields() {
        if (!semanticContext.containsKey("AppInfo.Id")) {
            semanticContext.setAppId(SystemInformation.getAppId());
        }
        if (!semanticContext.containsKey("AppInfo.Language")) {
            semanticContext.setAppLanguage(SystemInformation.getAppLanguage());
        }
        if (!semanticContext.containsKey("AppInfo.Version")) {
            semanticContext.setAppVersion(SystemInformation.getAppVersion());
        }
        if (!semanticContext.containsKey("DeviceInfo.Id")) {
            semanticContext.setDeviceId(DeviceInformation.getDeviceId());
        }
        if (!semanticContext.containsKey("DeviceInfo.Make")) {
            semanticContext.setDeviceMake(DeviceInformation.getManufacturer());
        }
        if (!semanticContext.containsKey("DeviceInfo.Model")) {
            semanticContext.setDeviceModel(DeviceInformation.getModel());
        }
        if (!semanticContext.containsKey("DeviceInfo.NetworkProvider")) {
            semanticContext.setNetworkProvider(NetworkInformation.getNetworkProvider());
        }
        if (!semanticContext.containsKey("UserInfo.Language")) {
            semanticContext.setUserLanguage(SystemInformation.getUserLanguage());
        }
        if (!semanticContext.containsKey("UserInfo.TimeZone")) {
            semanticContext.setUserTimeZone(SystemInformation.getUserTimezone());
        }
    }

    public static void registerEventListener(ITransmissionEvents eventListener) {
        messenger.registerEventListener(eventListener);
    }

    public static void reset() {
        flushAndTearDown();
        loggerHashMap = new ConcurrentHashMap();
        contextProperties = new EventProperties("");
        tenantTokenInitIdHashMap = new ConcurrentHashMap();
        tenantTokenSequenceHashMap = new ConcurrentHashMap();
        isInitialized.set(false);
        isTearDownCompleted = false;
        config = null;
        if (messenger != null) {
            messenger.resetPersistentStorage();
        }
        messenger = null;
        appContext = null;
        hardwareReceiver = null;
    }

    public static synchronized void resumeTransmission(boolean isResumedByUser) {
        synchronized (InternalMgrImpl.class) {
            TraceHelper.TraceVerbose(LOG_TAG, "resumeTransmission, isResumedByUser: " + isResumedByUser);
            messenger.resumeTransmission(isResumedByUser);
        }
    }

    protected static void setConfiguration(LogConfiguration configuration) {
        config = configuration;
    }

    public static void setContext(String name, String value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, String value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setContext(String name, double value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, double value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setContext(String name, long value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, long value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setContext(String name, boolean value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, boolean value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setContext(String name, Date value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, Date value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setContext(String name, UUID value) {
        contextProperties.setProperty(name, value);
    }

    public static void setContext(String name, UUID value, PiiKind piiKind) {
        contextProperties.setProperty(name, value, piiKind);
    }

    public static void setTransmitProfile(TransmitProfile transmitProfile) {
        try {
            Preconditions.isNotNull(transmitProfile, "transmitProfile cannot be null");
            messenger.setTransmitProfile(transmitProfile);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public static boolean setTransmitProfile(String profileName) {
        boolean z = false;
        try {
            Preconditions.isNotNull(profileName, "profileName cannot be null or empty");
            return messenger.setTransmitProfile(profileName);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[z]));
            return z;
        }
    }

    public static void loadTransmitProfiles(String profilesJson) {
        try {
            Preconditions.isNotNullOrEmpty(profilesJson, "profilesJson cannot be null or empty");
            messenger.loadTransmitProfiles(profilesJson);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                throw e;
            }
            TraceHelper.TraceError(LOG_TAG, String.format("Caught Exception. Exception: " + e.getLocalizedMessage(), new Object[0]));
        }
    }

    public static void resetTransmitProfiles() {
        messenger.resetTransmitProfiles();
    }

    private static void setupReceiver() {
        TraceHelper.TraceDebug(LOG_TAG, "Registering hardware receiver");
        hardwareReceiver = new HardwareInformationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        appContext.registerReceiver(hardwareReceiver, filter);
    }

    private static void teardownReceiver() {
        if (appContext != null && hardwareReceiver != null) {
            TraceHelper.TraceDebug(LOG_TAG, "Tearing down hardware receiver");
            try {
                appContext.unregisterReceiver(hardwareReceiver);
            } catch (IllegalArgumentException e) {
                TraceHelper.TraceWarning(LOG_TAG, "Unable to unregister hardware receiver");
            }
        }
    }

    private InternalMgrImpl() {
        throw new AssertionError();
    }
}
