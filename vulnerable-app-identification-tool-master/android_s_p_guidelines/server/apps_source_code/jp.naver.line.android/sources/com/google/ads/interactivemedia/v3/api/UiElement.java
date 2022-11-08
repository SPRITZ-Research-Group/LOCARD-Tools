package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.impl.data.m;

public interface UiElement {
    public static final UiElement AD_ATTRIBUTION = new m("adAttribution");
    public static final UiElement COUNTDOWN = new m("countdown");

    String getName();
}
