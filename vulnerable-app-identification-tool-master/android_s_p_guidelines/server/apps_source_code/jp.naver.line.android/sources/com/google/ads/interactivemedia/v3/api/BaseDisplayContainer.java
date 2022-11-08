package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;
import java.util.Collection;

public interface BaseDisplayContainer {
    ViewGroup getAdContainer();

    Collection<CompanionAdSlot> getCompanionSlots();

    void setAdContainer(ViewGroup viewGroup);

    void setCompanionSlots(Collection<CompanionAdSlot> collection);
}
