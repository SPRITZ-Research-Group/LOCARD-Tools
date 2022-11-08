package com.microsoft.applications.telemetry;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import com.microsoft.applications.telemetry.core.LifecycleHandler;
import com.microsoft.applications.telemetry.core.TraceHelper;

@TargetApi(14)
public class InstrumentedApplication extends Application {
    private static final String ENABLE_AUTO_USER_SESSION_KEY = "com.microsoft.applications.telemetry.enableAutoUserSession";
    private static final String ENABLE_PAUSE_ON_BACKGROUND = "com.microsoft.applications.telemetry.enablePauseOnBackground";
    private static final String EVENT_COLLECTOR_URI_KEY = "com.microsoft.applications.telemetry.eventCollectorUri";
    private static final String LOG_TAG = ("[ACT]:" + InstrumentedApplication.class.getSimpleName().toUpperCase());
    private static final String LOG_UNCAUGHT_EXCEPTIONS = "com.microsoft.applications.telemetry.logUncaughtException";
    private static final String TENANT_TOKEN_META_KEY = "com.microsoft.applications.telemetry.tenantToken";
    private static boolean enableAutoUserSession = false;
    private static boolean enablePauseOnBackground = true;
    private static String eventCollectorUri;
    private static LogConfiguration logConfig;
    private static boolean logUncaughtExceptions = true;
    private static ILogger logger;
    private static String tenantToken;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void onCreate() {
        super.onCreate();
        TraceHelper.TraceVerbose(LOG_TAG, "onCreate");
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), 128);
            if (ai.metaData == null) {
                String err = "The application must provide <meta-data> with 'com.microsoft.applications.telemetry.tenantToken' in the manifest";
                TraceHelper.TraceError(LOG_TAG, err);
                throw new IllegalStateException(err);
            }
            Bundle bundle = ai.metaData;
            for (String key : ai.metaData.keySet()) {
                Object obj = -1;
                switch (key.hashCode()) {
                    case -1950368307:
                        if (key.equals(ENABLE_PAUSE_ON_BACKGROUND)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -322290647:
                        if (key.equals(LOG_UNCAUGHT_EXCEPTIONS)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -180742490:
                        if (key.equals(EVENT_COLLECTOR_URI_KEY)) {
                            int obj2 = 1;
                            break;
                        }
                        break;
                    case -157583898:
                        if (key.equals(ENABLE_AUTO_USER_SESSION_KEY)) {
                            obj2 = 2;
                            break;
                        }
                        break;
                    case 1522681468:
                        if (key.equals(TENANT_TOKEN_META_KEY)) {
                            obj2 = null;
                            break;
                        }
                        break;
                }
                switch (obj2) {
                    case null:
                        tenantToken = bundle.getString(TENANT_TOKEN_META_KEY);
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Configured tenantToken: %s", new Object[]{tenantToken}));
                        break;
                    case 1:
                        eventCollectorUri = bundle.getString(EVENT_COLLECTOR_URI_KEY);
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Configured Collector URI: %s", new Object[]{eventCollectorUri}));
                        break;
                    case 2:
                        enableAutoUserSession = bundle.getBoolean(ENABLE_AUTO_USER_SESSION_KEY);
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Configured Enable Auto User Session: %b", new Object[]{Boolean.valueOf(enableAutoUserSession)}));
                        break;
                    case 3:
                        enablePauseOnBackground = bundle.getBoolean(ENABLE_PAUSE_ON_BACKGROUND);
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Configured Enable Pause On Background: %b", new Object[]{Boolean.valueOf(enablePauseOnBackground)}));
                        break;
                    case 4:
                        logUncaughtExceptions = bundle.getBoolean(LOG_UNCAUGHT_EXCEPTIONS);
                        TraceHelper.TraceInformation(LOG_TAG, String.format("Configured Log Uncaught Exceptions: %b", new Object[]{Boolean.valueOf(logUncaughtExceptions)}));
                        break;
                    default:
                        TraceHelper.TraceWarning(LOG_TAG, String.format("Unrecognized metadata key: %s", new Object[]{key}));
                        break;
                }
            }
            checkTenantToken(tenantToken);
            LogConfiguration logConfiguration = new LogConfiguration();
            logConfig = logConfiguration;
            logConfiguration.setConfigSettingsFromContext(this);
            if (eventCollectorUri != null) {
                logConfig.setCollectorUrl(eventCollectorUri);
            }
            logConfig.enableAutoUserSession(enableAutoUserSession);
            logConfig.enablePauseOnBackground(enablePauseOnBackground);
            LogManager.appStart(this, tenantToken, logConfig);
            logger = LogManager.getLogger();
            registerActivityLifecycleCallbacks(new LifecycleHandler());
            if (logUncaughtExceptions) {
                Thread.setDefaultUncaughtExceptionHandler(new InstrumentedExceptionHandler(logger, getApplicationContext(), Thread.getDefaultUncaughtExceptionHandler()));
            }
        } catch (NameNotFoundException e) {
            TraceHelper.TraceError(LOG_TAG, "Could not find metadata in package: " + getPackageName());
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        LogManager.flush();
    }

    public void onTerminate() {
        super.onTerminate();
        LogManager.appStop();
    }

    public void finalize() throws Throwable {
        LogManager.appStop();
        super.finalize();
    }

    public ILogger getLogger() {
        return logger;
    }

    private void checkTenantToken(String tenantToken) {
        if (tenantToken == null || tenantToken.isEmpty()) {
            String errMsg = String.format("An tenantToken is required! Please provide a token via metadata in the application manifest: '<meta-data android:name=\"com.microsoft.applications.telemetry.tenantToken\" android:value=\"[yourtoken]\"' />", new Object[0]);
            TraceHelper.TraceError(LOG_TAG, errMsg);
            throw new IllegalArgumentException(errMsg);
        }
    }
}
