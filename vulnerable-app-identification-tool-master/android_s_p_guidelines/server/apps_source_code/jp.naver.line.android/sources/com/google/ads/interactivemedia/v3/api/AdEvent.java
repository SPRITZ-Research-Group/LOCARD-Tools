package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public interface AdEvent {

    public interface AdEventListener {
        void onAdEvent(AdEvent adEvent);
    }

    public enum AdEventType {
        ALL_ADS_COMPLETED,
        CLICKED,
        COMPLETED,
        CUEPOINTS_CHANGED,
        CONTENT_PAUSE_REQUESTED,
        CONTENT_RESUME_REQUESTED,
        FIRST_QUARTILE,
        LOG,
        AD_BREAK_READY,
        MIDPOINT,
        PAUSED,
        RESUMED,
        SKIPPED,
        STARTED,
        TAPPED,
        ICON_TAPPED,
        THIRD_QUARTILE,
        LOADED,
        AD_PROGRESS,
        AD_BREAK_STARTED,
        AD_BREAK_ENDED
    }

    Ad getAd();

    Map<String, String> getAdData();

    AdEventType getType();
}
