package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;

public class AirMapCallout extends ReactViewGroup {
    public int a;
    public int b;
    private boolean c = false;

    public AirMapCallout(Context context) {
        super(context);
    }

    public void setTooltip(boolean tooltip) {
        this.c = tooltip;
    }

    public final boolean a_() {
        return this.c;
    }
}
