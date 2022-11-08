package com.google.ads.interactivemedia.v3.api;

import java.util.Set;

public interface Ad {
    String getAdId();

    AdPodInfo getAdPodInfo();

    String getAdSystem();

    String[] getAdWrapperCreativeIds();

    String[] getAdWrapperIds();

    String[] getAdWrapperSystems();

    String getAdvertiserName();

    String getContentType();

    String getCreativeAdId();

    String getCreativeId();

    String getDealId();

    String getDescription();

    double getDuration();

    int getHeight();

    double getSkipTimeOffset();

    String getSurveyUrl();

    String getTitle();

    String getTraffickingParameters();

    Set<UiElement> getUiElements();

    String getUniversalAdIdRegistry();

    String getUniversalAdIdValue();

    int getWidth();

    boolean isLinear();

    boolean isSkippable();

    boolean isUiDisabled();
}
