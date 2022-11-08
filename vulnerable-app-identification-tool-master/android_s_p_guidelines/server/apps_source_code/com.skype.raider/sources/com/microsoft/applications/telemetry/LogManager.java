package com.microsoft.applications.telemetry;

import android.content.Context;
import com.microsoft.applications.telemetry.core.InternalMgrImpl;
import com.microsoft.applications.telemetry.core.TraceHelper;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public enum LogManager {
    INSTANCE;
    
    private static final String LOG_TAG = null;
    private Context appContext;
    private AtomicBoolean isInitialized;
    private LogConfiguration logConfig;

    static {
        LOG_TAG = "[ACT]:" + LogManager.class.getSimpleName().toUpperCase();
    }

    public static synchronized ILogger initialize(Context context, String tenantToken) throws IllegalStateException {
        ILogger initialize;
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("initialize|context:%s|tenantToken:%s", new Object[]{context, tenantToken}));
            initialize = initialize(context, tenantToken, null);
        }
        return initialize;
    }

    public static synchronized ILogger initialize(Context context, String tenantToken, LogConfiguration configuration) throws IllegalStateException {
        ILogger initializeLogger;
        synchronized (LogManager.class) {
            INSTANCE.logConfig = configuration != null ? configuration : getDefaultLogConfiguration();
            INSTANCE.logConfig.setTenantToken(tenantToken);
            INSTANCE.logConfig.setConfigSettingsFromContext(context);
            TraceHelper.TraceInformation(LOG_TAG, String.format("initialize|context:%s|tenantToken:%s|configuration:%s", new Object[]{context, tenantToken, configuration}));
            if (context == null) {
                throw new IllegalArgumentException("Context can not be null.");
            } else if (INSTANCE.isInitialized.get()) {
                throw new IllegalStateException("Telemetry system has already been initialized!");
            } else {
                INSTANCE.appContext = context;
                initializeLogger = INSTANCE.initializeLogger(tenantToken, INSTANCE.logConfig, INSTANCE.appContext);
            }
        }
        return initializeLogger;
    }

    private static LogConfiguration getDefaultLogConfiguration() {
        return new LogConfiguration();
    }

    public static synchronized void flush() {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, "Flushing the log manager");
            InternalMgrImpl.flush();
        }
    }

    public static synchronized void flushAndTeardown() {
        synchronized (LogManager.class) {
            if (INSTANCE.isInitialized.get()) {
                TraceHelper.TraceInformation(LOG_TAG, "Tearing down the log manager");
                InternalMgrImpl.flushAndTearDown();
                INSTANCE.isInitialized.set(false);
            } else {
                TraceHelper.TraceWarning(LOG_TAG, "flushAndTeardown called before initialization. Ignoring.");
            }
        }
    }

    public static synchronized void setTransmitProfile(TransmitProfile profile) {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("setTransmitProfile|profile: %d", new Object[]{Integer.valueOf(profile.getValue())}));
            INSTANCE.verifyInitialized();
            InternalMgrImpl.setTransmitProfile(profile);
        }
    }

    public static synchronized boolean setTransmitProfile(String profileName) {
        boolean transmitProfile;
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("setTransmitProfile|profile: %s", new Object[]{profileName}));
            INSTANCE.verifyInitialized();
            transmitProfile = InternalMgrImpl.setTransmitProfile(profileName);
        }
        return transmitProfile;
    }

    public static synchronized void loadTransmitProfiles(String profilesJson) {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("loadTransmitProfiles|json: %s", new Object[]{profilesJson}));
            INSTANCE.verifyInitialized();
            InternalMgrImpl.loadTransmitProfiles(profilesJson);
        }
    }

    public static synchronized void resetTransmitProfiles() {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, "resetTransmitProfiles");
            INSTANCE.verifyInitialized();
            InternalMgrImpl.resetTransmitProfiles();
        }
    }

    public static synchronized void pauseTransmission() {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("pauseTransmission", new Object[0]));
            INSTANCE.verifyInitialized();
            InternalMgrImpl.pauseTransmission(true);
        }
    }

    public static synchronized void resumeTransmission() {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("resumeTransmission", new Object[0]));
            INSTANCE.verifyInitialized();
            InternalMgrImpl.resumeTransmission(true);
        }
    }

    public static synchronized void appStart(Context context, String tenantToken, LogConfiguration configuration) {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("onAppStart", new Object[]{context, tenantToken, configuration}));
            if (INSTANCE.isInitialized.get()) {
                TraceHelper.TraceWarning(LOG_TAG, "OnAppStart already called. Ignoring.");
            } else {
                initialize(context, tenantToken, configuration);
            }
        }
    }

    public static synchronized void appStop() {
        synchronized (LogManager.class) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("onAppStop", new Object[0]));
            if (INSTANCE.isInitialized.get()) {
                flushAndTeardown();
            } else {
                TraceHelper.TraceWarning(LOG_TAG, "onAppStop called before initialization. Ignoring.");
            }
        }
    }

    public static synchronized ISemanticContext getSemanticContext() {
        ISemanticContext semanticContext;
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, "getSemanticContext");
            INSTANCE.verifyInitialized();
            semanticContext = InternalMgrImpl.getSemanticContext();
        }
        return semanticContext;
    }

    public static synchronized void setContext(String name, String value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, value}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, String value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, value, piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized void setContext(String name, double value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, Double.valueOf(value)}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, double value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, Double.valueOf(value), piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized void setContext(String name, long value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, Long.valueOf(value)}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, long value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, Long.valueOf(value), piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized void setContext(String name, boolean value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, Boolean.valueOf(value)}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, boolean value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, Boolean.valueOf(value), piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized void setContext(String name, Date value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, value}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, Date value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, value, piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized void setContext(String name, UUID value) {
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s", new Object[]{name, value}));
            InternalMgrImpl.setContext(name, value);
        }
    }

    public static void setContext(String name, UUID value, PiiKind piiKind) {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("setContext|name: %s|value: %s|piiKind: %s", new Object[]{name, value, piiKind}));
        InternalMgrImpl.setContext(name, value, piiKind);
    }

    public static synchronized ILogger getLogger() {
        ILogger logger;
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getLogger", new Object[0]));
            INSTANCE.verifyInitialized();
            logger = InternalMgrImpl.getLogger();
        }
        return logger;
    }

    public static synchronized ILogger getLogger(String source) {
        ILogger logger;
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getLogger|source: %s", new Object[]{source}));
            INSTANCE.verifyInitialized();
            logger = InternalMgrImpl.getLogger(source);
        }
        return logger;
    }

    public static synchronized ILogger getLogger(String tenantToken, String source) {
        ILogger logger;
        synchronized (LogManager.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getLogger|tenantToken: %s|source: %s", new Object[]{tenantToken, source}));
            INSTANCE.verifyInitialized();
            logger = InternalMgrImpl.getLogger(source, tenantToken);
        }
        return logger;
    }

    protected static void reset() {
        InternalMgrImpl.reset();
    }

    private void verifyInitialized() {
        if (!this.isInitialized.get()) {
            TraceHelper.TraceWarning(LOG_TAG, "The telemetry system has not yet been initialized!");
        }
    }

    private ILogger initializeLogger(String tenantToken, LogConfiguration config, Context context) {
        ILogger logger = InternalMgrImpl.initialize(tenantToken, config, context);
        this.isInitialized.set(true);
        return logger;
    }
}
