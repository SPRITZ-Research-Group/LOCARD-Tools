package com.google.ads.interactivemedia.v3.api;

import java.util.List;
import java.util.Set;

public interface AdsRenderingSettings {
    int getBitrateKbps();

    boolean getDisableUi();

    boolean getEnablePreloading();

    List<String> getMimeTypes();

    boolean isRenderCompanions();

    void setBitrateKbps(int i);

    void setDisableUi(boolean z);

    void setEnablePreloading(boolean z);

    void setLoadVideoTimeout(int i);

    void setMimeTypes(List<String> list);

    void setPlayAdsAfterTime(double d);

    void setRenderCompanions(boolean z);

    void setUiElements(Set<UiElement> set);
}
