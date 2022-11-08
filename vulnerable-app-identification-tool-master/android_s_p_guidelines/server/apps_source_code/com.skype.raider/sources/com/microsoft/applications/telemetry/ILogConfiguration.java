package com.microsoft.applications.telemetry;

interface ILogConfiguration {
    void enableAutoUserSession(boolean z);

    void enablePauseOnBackground(boolean z);

    @Deprecated
    String getCacheFilePath();

    int getCacheFileSizeLimitInBytes();

    @Deprecated
    int getCacheMemorySizeLimitInNumberOfEvents();

    String getClientId();

    String getClientKey();

    String getCollectorUrl();

    String getSource();

    String getTenantToken();

    boolean isAutoUserSessionEnabled();

    boolean isPauseOnBackgroundEnabled();

    @Deprecated
    void setCacheFilePath(String str);

    void setCacheFileSizeLimitInBytes(int i);

    @Deprecated
    void setCacheMemorySizeLimitInNumberOfEvents(int i);

    void setCollectorUrl(String str);

    void setSource(String str);

    void setTenantToken(String str);
}
