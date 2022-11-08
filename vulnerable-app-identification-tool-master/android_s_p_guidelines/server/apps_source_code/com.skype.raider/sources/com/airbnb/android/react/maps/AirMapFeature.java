package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;

public abstract class AirMapFeature extends ReactViewGroup {
    public abstract Object a();

    public abstract void b();

    public AirMapFeature(Context context) {
        super(context);
    }
}
