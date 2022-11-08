package com.airbnb.android.react.maps;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class b implements q {
    public final List<NativeModule> a(ag reactContext) {
        return Arrays.asList(new NativeModule[]{new AirMapModule(reactContext)});
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        AirMapCalloutManager calloutManager = new AirMapCalloutManager();
        AirMapMarkerManager annotationManager = new AirMapMarkerManager();
        AirMapPolylineManager polylineManager = new AirMapPolylineManager(reactContext);
        AirMapPolygonManager polygonManager = new AirMapPolygonManager(reactContext);
        AirMapCircleManager circleManager = new AirMapCircleManager(reactContext);
        AirMapManager mapManager = new AirMapManager(reactContext);
        AirMapLiteManager mapLiteManager = new AirMapLiteManager(reactContext);
        AirMapUrlTileManager tileManager = new AirMapUrlTileManager(reactContext);
        return Arrays.asList(new ViewManager[]{calloutManager, annotationManager, polylineManager, polygonManager, circleManager, mapManager, mapLiteManager, tileManager});
    }
}
