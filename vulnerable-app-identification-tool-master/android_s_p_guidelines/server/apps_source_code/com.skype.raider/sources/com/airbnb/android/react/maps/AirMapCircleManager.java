package com.airbnb.android.react.maps;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.LatLng;

public class AirMapCircleManager extends ViewGroupManager<AirMapCircle> {
    private final DisplayMetrics metrics;

    public AirMapCircleManager(ag reactContext) {
        if (VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactContext.getResources().getDisplayMetrics();
    }

    public String getName() {
        return "AIRMapCircle";
    }

    public AirMapCircle createViewInstance(ae context) {
        return new AirMapCircle(context);
    }

    @ReactProp(name = "center")
    public void setCenter(AirMapCircle view, am center) {
        view.setCenter(new LatLng(center.getDouble("latitude"), center.getDouble("longitude")));
    }

    @ReactProp(defaultDouble = 0.0d, name = "radius")
    public void setRadius(AirMapCircle view, double radius) {
        view.setRadius(radius);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapCircle view, float widthInPoints) {
        view.setStrokeWidth(this.metrics.density * widthInPoints);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "fillColor")
    public void setFillColor(AirMapCircle view, int color) {
        view.setFillColor(color);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "strokeColor")
    public void setStrokeColor(AirMapCircle view, int color) {
        view.setStrokeColor(color);
    }

    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(AirMapCircle view, float zIndex) {
        view.setZIndex(zIndex);
    }
}
