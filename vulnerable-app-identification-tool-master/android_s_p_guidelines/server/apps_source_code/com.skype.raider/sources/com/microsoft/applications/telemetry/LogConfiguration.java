package com.microsoft.applications.telemetry;

import android.content.Context;
import com.microsoft.applications.telemetry.core.Preconditions;
import java.io.File;

public class LogConfiguration implements ILogConfiguration {
    public static final int BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS = 3000;
    public static final String COLLECTOR_URL_AUSTRALIA = "https://au.pipe.aria.microsoft.com/Collector/3.0/";
    public static final String COLLECTOR_URL_EUROPE = "https://eu.pipe.aria.microsoft.com/Collector/3.0/";
    public static final String COLLECTOR_URL_GERMANY = "https://de.pipe.aria.microsoft.com/Collector/3.0/";
    public static final String COLLECTOR_URL_IN_INTEGRATION = "https://pipe.int.trafficmanager.net/Collector/3.0/";
    public static final String COLLECTOR_URL_IN_PRODUCTION = "https://mobile.pipe.aria.microsoft.com/Collector/3.0/";
    public static final String COLLECTOR_URL_JAPAN = "https://jp.pipe.aria.microsoft.com/Collector/3.0/";
    public static final String COLLECTOR_URL_UNITED_STATES = "https://us.pipe.aria.microsoft.com/Collector/3.0/";
    public static final int DATA_PACKAGE_SIZE_LIMITS = 3145728;
    private static LogConfiguration DEFAULT_CONFIGURATION = new LogConfiguration();
    private static final String LOG_TAG = LogConfiguration.class.getSimpleName();
    public static final int MAX_BACKOFF_FOR_SENDING_RETRIES_MILLIS = 120000;
    public static final int MAX_RETRIES = 1;
    public static final int MAX_TPM_BACKOFF_COUNT = 4;
    private String cacheFilePath = null;
    private int cacheFileSizeLimitInBytes = 10485760;
    private int cacheMemorySizeLimitInNumberOfEvents = 512;
    private String clientId = "JavaLibrary";
    private String clientKey = "aKaIh0hlBs2g12kx76YdKePQKZKhrmnufR31kt4zEK6rjEGRDkoUMHv0Ghx1NdIB";
    private String collectorUrl = COLLECTOR_URL_IN_PRODUCTION;
    private boolean enableAutoUserSession = false;
    private boolean enablePauseOnBackground = true;
    private String offlineKVPStoragePath = null;
    private String source = "act_default_source";
    private String tenantToken;

    public LogConfiguration(LogConfiguration configuration) {
        this.collectorUrl = configuration.collectorUrl;
        this.tenantToken = configuration.tenantToken;
        this.clientId = configuration.clientId;
        this.clientKey = configuration.clientKey;
        this.source = configuration.source;
        this.cacheFileSizeLimitInBytes = configuration.cacheFileSizeLimitInBytes;
        this.cacheMemorySizeLimitInNumberOfEvents = configuration.cacheMemorySizeLimitInNumberOfEvents;
        this.cacheFilePath = configuration.cacheFilePath;
        this.offlineKVPStoragePath = configuration.offlineKVPStoragePath;
        this.enableAutoUserSession = configuration.enableAutoUserSession;
        this.enablePauseOnBackground = configuration.enablePauseOnBackground;
    }

    @Deprecated
    public void setCacheMemorySizeLimitInNumberOfEvents(int cacheMemorySizeLimitInNumberOfEvents) {
        Preconditions.isTrue(cacheMemorySizeLimitInNumberOfEvents > 0, "cacheMemorySizeLimitInNumberOfEvents should be greater than 0.");
        this.cacheMemorySizeLimitInNumberOfEvents = cacheMemorySizeLimitInNumberOfEvents;
    }

    public void enableAutoUserSession(boolean enableAutoUserSession) {
        this.enableAutoUserSession = enableAutoUserSession;
    }

    public boolean isAutoUserSessionEnabled() {
        return this.enableAutoUserSession;
    }

    public void enablePauseOnBackground(boolean enablePauseOnBackground) {
        this.enablePauseOnBackground = enablePauseOnBackground;
    }

    public boolean isPauseOnBackgroundEnabled() {
        return this.enablePauseOnBackground;
    }

    @Deprecated
    public int getCacheMemorySizeLimitInNumberOfEvents() {
        return this.cacheMemorySizeLimitInNumberOfEvents;
    }

    public void setCacheFileSizeLimitInBytes(int cacheFileSizeLimitInBytes) {
        Preconditions.isTrue(cacheFileSizeLimitInBytes > 0, "cacheFileSizeLimitInBytes should be greater than 0.");
        this.cacheFileSizeLimitInBytes = cacheFileSizeLimitInBytes;
    }

    public int getCacheFileSizeLimitInBytes() {
        return this.cacheFileSizeLimitInBytes;
    }

    public void setCollectorUrl(String collectorUrl) {
        this.collectorUrl = Preconditions.isNotNullOrEmpty(collectorUrl, "collectorUrl cannot be null or empty.");
    }

    public String getCollectorUrl() {
        return this.collectorUrl;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setTenantToken(String tenantToken) {
        this.tenantToken = Preconditions.isValidToken(tenantToken, "tenantToken is not valid.");
    }

    public String getTenantToken() {
        return this.tenantToken;
    }

    protected void setAuthenticationSettings(String clientId, String clientKey) {
        this.clientId = Preconditions.isNotNullOrEmpty(clientId, "clientId cannot be null or empty");
        this.clientKey = Preconditions.isNotNullOrEmpty(clientKey, "clientKey cannot be null or empty");
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientKey() {
        return this.clientKey;
    }

    @Deprecated
    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }

    @Deprecated
    public String getCacheFilePath() {
        return this.cacheFilePath;
    }

    @Deprecated
    public String getOfflineKVPStoragePath() {
        return this.offlineKVPStoragePath;
    }

    protected void setConfigSettingsFromContext(Context ctx) {
        String absolutePath = ctx.getFilesDir().getAbsolutePath() + "/aria";
        File ariaDirectory = new File(absolutePath);
        if (!ariaDirectory.exists()) {
            ariaDirectory.mkdirs();
        }
        if (this.cacheFilePath == null) {
            this.cacheFilePath = absolutePath + "/offlinestorage";
        }
        if (this.offlineKVPStoragePath == null) {
            this.offlineKVPStoragePath = absolutePath + "/offlineKVP.db";
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("CollectorUrl=%s,", new Object[]{this.collectorUrl}));
        builder.append(String.format("TenantToken=%s,", new Object[]{this.tenantToken}));
        builder.append(String.format("Source=%s,", new Object[]{this.source}));
        builder.append(String.format("CollectorUrl=%s,", new Object[]{this.collectorUrl}));
        builder.append(String.format("CacheFileSizeLimitInBytes=%s,", new Object[]{Integer.valueOf(this.cacheFileSizeLimitInBytes)}));
        builder.append(String.format("CacheMemorySizeLimitInNumberOfEvents=%s,", new Object[]{Integer.valueOf(this.cacheMemorySizeLimitInNumberOfEvents)}));
        builder.append(String.format("CacheFilePath=%s,", new Object[]{this.cacheFilePath}));
        return builder.toString();
    }

    public static LogConfiguration getDefault() {
        return DEFAULT_CONFIGURATION;
    }
}
