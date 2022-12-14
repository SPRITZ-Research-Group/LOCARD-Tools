package com.airbnb.android.react.maps;

import com.facebook.react.bridge.ag;
import com.google.android.gms.maps.GoogleMapOptions;

public class AirMapLiteManager extends AirMapManager {
    private static final String REACT_CLASS = "AIRMapLite";

    public String getName() {
        return REACT_CLASS;
    }

    public AirMapLiteManager(ag context) {
        super(context);
        this.googleMapOptions = new GoogleMapOptions().a(true);
    }
}
