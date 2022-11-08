package com.airbnb.android.react.maps;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

public class AirMapPolylineManager extends ViewGroupManager<AirMapPolyline> {
    private final DisplayMetrics metrics;

    public AirMapPolylineManager(ag reactContext) {
        if (VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactContext.getResources().getDisplayMetrics();
    }

    public String getName() {
        return "AIRMapPolyline";
    }

    public AirMapPolyline createViewInstance(ae context) {
        return new AirMapPolyline(context);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(AirMapPolyline view, al coordinates) {
        view.setCoordinates(coordinates);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapPolyline view, float widthInPoints) {
        view.setWidth(this.metrics.density * widthInPoints);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "strokeColor")
    public void setStrokeColor(AirMapPolyline view, int color) {
        view.setColor(color);
    }

    @ReactProp(defaultBoolean = false, name = "geodesic")
    public void setGeodesic(AirMapPolyline view, boolean geodesic) {
        view.setGeodesic(geodesic);
    }

    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(AirMapPolyline view, float zIndex) {
        view.setZIndex(zIndex);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }
}
