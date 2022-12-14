package com.google.ads.interactivemedia.v3.api;

public interface AdsManagerLoadedEvent {
    AdsManager getAdsManager();

    StreamManager getStreamManager();

    Object getUserRequestContext();
}
