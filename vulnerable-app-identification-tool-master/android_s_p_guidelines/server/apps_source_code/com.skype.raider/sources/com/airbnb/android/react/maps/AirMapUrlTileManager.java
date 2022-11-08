package com.airbnb.android.react.maps;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ag;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;

public class AirMapUrlTileManager extends ViewGroupManager<AirMapUrlTile> {
    private DisplayMetrics metrics;

    public AirMapUrlTileManager(ag reactContext) {
        if (VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactContext.getResources().getDisplayMetrics();
    }

    public String getName() {
        return "AIRMapUrlTile";
    }

    public AirMapUrlTile createViewInstance(ae context) {
        return new AirMapUrlTile(context);
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(AirMapUrlTile view, String urlTemplate) {
        view.setUrlTemplate(urlTemplate);
    }

    @ReactProp(defaultFloat = -1.0f, name = "zIndex")
    public void setZIndex(AirMapUrlTile view, float zIndex) {
        view.setZIndex(zIndex);
    }
}
