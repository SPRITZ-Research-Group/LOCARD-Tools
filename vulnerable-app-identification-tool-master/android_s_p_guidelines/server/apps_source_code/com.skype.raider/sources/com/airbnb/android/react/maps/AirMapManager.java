package com.airbnb.android.react.maps;

import android.view.View;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.h;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.a;
import com.google.android.gms.maps.model.MapStyleOptions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class AirMapManager extends ViewGroupManager<AirMapView> {
    private static final int FIT_TO_COORDINATES = 5;
    private static final int FIT_TO_ELEMENTS = 3;
    private static final int FIT_TO_SUPPLIED_MARKERS = 4;
    private static final String REACT_CLASS = "AIRMap";
    private final Map<String, Integer> MAP_TYPES = e.a("standard", Integer.valueOf(1), "satellite", Integer.valueOf(2), "hybrid", Integer.valueOf(4), "terrain", Integer.valueOf(3), "none", Integer.valueOf(0));
    private final ag appContext;
    protected GoogleMapOptions googleMapOptions;

    public AirMapManager(ag context) {
        this.appContext = context;
        this.googleMapOptions = new GoogleMapOptions();
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected AirMapView createViewInstance(ae context) {
        return new AirMapView(context, this.appContext, this, this.googleMapOptions);
    }

    @ReactProp(name = "region")
    public void setRegion(AirMapView view, am region) {
        view.setRegion(region);
    }

    @ReactProp(name = "mapType")
    public void setMapType(AirMapView view, @Nullable String mapType) {
        view.a.a(((Integer) this.MAP_TYPES.get(mapType)).intValue());
    }

    @ReactProp(name = "customMapStyleString")
    public void setMapStyle(AirMapView view, @Nullable String customMapStyleString) {
        view.a.a(new MapStyleOptions(customMapStyleString));
    }

    @ReactProp(defaultBoolean = false, name = "showsUserLocation")
    public void setShowsUserLocation(AirMapView view, boolean showUserLocation) {
        view.setShowsUserLocation(showUserLocation);
    }

    @ReactProp(defaultBoolean = true, name = "showsMyLocationButton")
    public void setShowsMyLocationButton(AirMapView view, boolean showMyLocationButton) {
        view.setShowsMyLocationButton(showMyLocationButton);
    }

    @ReactProp(defaultBoolean = true, name = "toolbarEnabled")
    public void setToolbarEnabled(AirMapView view, boolean toolbarEnabled) {
        view.setToolbarEnabled(toolbarEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "handlePanDrag")
    public void setHandlePanDrag(AirMapView view, boolean handlePanDrag) {
        view.setHandlePanDrag(handlePanDrag);
    }

    @ReactProp(defaultBoolean = false, name = "showsTraffic")
    public void setShowTraffic(AirMapView view, boolean showTraffic) {
        view.a.a(showTraffic);
    }

    @ReactProp(defaultBoolean = false, name = "showsBuildings")
    public void setShowBuildings(AirMapView view, boolean showBuildings) {
        view.a.c(showBuildings);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoors")
    public void setShowIndoors(AirMapView view, boolean showIndoors) {
        view.a.b(showIndoors);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoorLevelPicker")
    public void setShowsIndoorLevelPicker(AirMapView view, boolean showsIndoorLevelPicker) {
        view.a.b().c(showsIndoorLevelPicker);
    }

    @ReactProp(defaultBoolean = false, name = "showsCompass")
    public void setShowsCompass(AirMapView view, boolean showsCompass) {
        view.a.b().a(showsCompass);
    }

    @ReactProp(defaultBoolean = false, name = "scrollEnabled")
    public void setScrollEnabled(AirMapView view, boolean scrollEnabled) {
        view.a.b().d(scrollEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "zoomEnabled")
    public void setZoomEnabled(AirMapView view, boolean zoomEnabled) {
        view.a.b().e(zoomEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "rotateEnabled")
    public void setRotateEnabled(AirMapView view, boolean rotateEnabled) {
        view.a.b().g(rotateEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "cacheEnabled")
    public void setCacheEnabled(AirMapView view, boolean cacheEnabled) {
        view.setCacheEnabled(cacheEnabled);
    }

    @ReactProp(defaultBoolean = false, name = "loadingEnabled")
    public void setLoadingEnabled(AirMapView view, boolean loadingEnabled) {
        view.a(loadingEnabled);
    }

    @ReactProp(defaultBoolean = true, name = "moveOnMarkerPress")
    public void setMoveOnMarkerPress(AirMapView view, boolean moveOnPress) {
        view.setMoveOnMarkerPress(moveOnPress);
    }

    @ReactProp(customType = "Color", name = "loadingBackgroundColor")
    public void setLoadingBackgroundColor(AirMapView view, @Nullable Integer loadingBackgroundColor) {
        view.setLoadingBackgroundColor(loadingBackgroundColor);
    }

    @ReactProp(customType = "Color", name = "loadingIndicatorColor")
    public void setLoadingIndicatorColor(AirMapView view, @Nullable Integer loadingIndicatorColor) {
        view.setLoadingIndicatorColor(loadingIndicatorColor);
    }

    @ReactProp(defaultBoolean = false, name = "pitchEnabled")
    public void setPitchEnabled(AirMapView view, boolean pitchEnabled) {
        view.a.b().f(pitchEnabled);
    }

    public void receiveCommand(AirMapView view, int commandId, @Nullable al args) {
        switch (commandId) {
            case 3:
                view.b(args.getBoolean(0));
                return;
            case 4:
                view.a(args.getArray(0), args.getBoolean(1));
                return;
            case 5:
                al array = args.getArray(0);
                am map = args.getMap(1);
                boolean z = args.getBoolean(2);
                a aVar = new a();
                for (int i = 0; i < array.size(); i++) {
                    am map2 = array.getMap(i);
                    aVar.a(new LatLng(Double.valueOf(map2.getDouble("latitude")).doubleValue(), Double.valueOf(map2.getDouble("longitude")).doubleValue()));
                }
                com.google.android.gms.maps.a a = b.a(aVar.a(), 50);
                if (map != null) {
                    view.a.a(map.getInt("left"), map.getInt("top"), map.getInt("right"), map.getInt("bottom"));
                }
                if (z) {
                    view.c();
                    view.a.b(a);
                } else {
                    view.a.a(a);
                }
                view.a.a(0, 0, 0, 0);
                return;
            default:
                return;
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        Map a = e.a("registrationName", "onMapReady");
        Map a2 = e.a("registrationName", "onPress");
        Map a3 = e.a("registrationName", "onLongPress");
        Map a4 = e.a("registrationName", "onMarkerPress");
        Map a5 = e.a("registrationName", "onMarkerSelect");
        Map a6 = e.a("registrationName", "onMarkerDeselect");
        Map a7 = e.a("registrationName", "onCalloutPress");
        Map<String, Map<String, String>> map = new HashMap();
        map.put("onMapReady", a);
        map.put("onPress", a2);
        map.put("onLongPress", a3);
        map.put("onMarkerPress", a4);
        map.put("onMarkerSelect", a5);
        map.put("onMarkerDeselect", a6);
        map.put("onCalloutPress", a7);
        map.putAll(e.a("onMarkerDragStart", e.a("registrationName", "onMarkerDragStart"), "onMarkerDrag", e.a("registrationName", "onMarkerDrag"), "onMarkerDragEnd", e.a("registrationName", "onMarkerDragEnd"), "onPanDrag", e.a("registrationName", "onPanDrag")));
        return map;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return e.a("fitToElements", Integer.valueOf(3), "fitToSuppliedMarkers", Integer.valueOf(4), "fitToCoordinates", Integer.valueOf(5));
    }

    public h createShadowNodeInstance() {
        return new d();
    }

    public void addView(AirMapView parent, View child, int index) {
        parent.a(child, index);
    }

    public int getChildCount(AirMapView view) {
        return view.b();
    }

    public View getChildAt(AirMapView view, int index) {
        return view.a(index);
    }

    public void removeViewAt(AirMapView parent, int index) {
        parent.b(index);
    }

    public void updateExtraData(AirMapView view, Object extraData) {
        view.a(extraData);
    }

    void pushEvent(ae context, View view, String name, ar data) {
        ((RCTEventEmitter) context.a(RCTEventEmitter.class)).receiveEvent(view.getId(), name, data);
    }

    public void onDropViewInstance(AirMapView view) {
        view.a();
        super.onDropViewInstance(view);
    }

    private void emitMapError(ae context, String message, String type) {
        ar error = new WritableNativeMap();
        error.putString("message", message);
        error.putString("type", type);
        ((RCTDeviceEventEmitter) context.a(RCTDeviceEventEmitter.class)).emit("onError", error);
    }
}
