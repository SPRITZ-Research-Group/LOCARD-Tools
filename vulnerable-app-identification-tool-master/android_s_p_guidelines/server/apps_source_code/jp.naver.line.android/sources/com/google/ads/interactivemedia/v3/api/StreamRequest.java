package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public interface StreamRequest {

    public enum StreamFormat {
        DASH,
        HLS
    }

    Map<String, String> getAdTagParameters();

    String getApiKey();

    String getAssetKey();

    String getAuthToken();

    String getContentSourceId();

    StreamFormat getFormat();

    String getManifestSuffix();

    String getStreamActivityMonitorId();

    StreamDisplayContainer getStreamDisplayContainer();

    Boolean getUseQAStreamBaseUrl();

    Object getUserRequestContext();

    String getVideoId();

    void setAdTagParameters(Map<String, String> map);

    void setAuthToken(String str);

    void setFormat(StreamFormat streamFormat);

    void setManifestSuffix(String str);

    void setStreamActivityMonitorId(String str);

    void setUseQAStreamBaseUrl(Boolean bool);

    void setUserRequestContext(Object obj);
}
