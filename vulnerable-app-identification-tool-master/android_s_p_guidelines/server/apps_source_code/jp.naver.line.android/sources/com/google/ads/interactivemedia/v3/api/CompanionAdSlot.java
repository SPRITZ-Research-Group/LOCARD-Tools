package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;

public interface CompanionAdSlot {

    public interface ClickListener {
        void onCompanionAdClick();
    }

    void addClickListener(ClickListener clickListener);

    ViewGroup getContainer();

    int getHeight();

    int getWidth();

    boolean isFilled();

    void removeClickListener(ClickListener clickListener);

    void setContainer(ViewGroup viewGroup);

    void setSize(int i, int i2);
}
