package com.airbnb.android.react.maps;

import android.graphics.Color;
import android.view.View;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.google.android.gms.maps.model.d;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class AirMapMarkerManager extends ViewGroupManager<AirMapMarker> {
    private static final int HIDE_INFO_WINDOW = 2;
    private static final int SHOW_INFO_WINDOW = 1;

    public String getName() {
        return "AIRMapMarker";
    }

    public AirMapMarker createViewInstance(ae context) {
        return new AirMapMarker(context);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(AirMapMarker view, am map) {
        view.setCoordinate(map);
    }

    @ReactProp(name = "title")
    public void setTitle(AirMapMarker view, String title) {
        view.setTitle(title);
    }

    @ReactProp(name = "identifier")
    public void setIdentifier(AirMapMarker view, String identifier) {
        view.setIdentifier(identifier);
    }

    @ReactProp(name = "description")
    public void setDescription(AirMapMarker view, String description) {
        view.setSnippet(description);
    }

    @ReactProp(name = "anchor")
    public void setAnchor(AirMapMarker view, am map) {
        double x = (map == null || !map.hasKey("x")) ? 0.5d : map.getDouble("x");
        double y = (map == null || !map.hasKey("y")) ? 1.0d : map.getDouble("y");
        view.setAnchor(x, y);
    }

    @ReactProp(name = "calloutAnchor")
    public void setCalloutAnchor(AirMapMarker view, am map) {
        double x = (map == null || !map.hasKey("x")) ? 0.5d : map.getDouble("x");
        double y = (map == null || !map.hasKey("y")) ? 0.0d : map.getDouble("y");
        view.setCalloutAnchor(x, y);
    }

    @ReactProp(name = "image")
    public void setImage(AirMapMarker view, @Nullable String source) {
        view.setImage(source);
    }

    @ReactProp(customType = "Color", defaultInt = -65536, name = "pinColor")
    public void setPinColor(AirMapMarker view, int pinColor) {
        float[] hsv = new float[3];
        Color.colorToHSV(pinColor, hsv);
        view.setMarkerHue(hsv[0]);
    }

    @ReactProp(defaultFloat = 0.0f, name = "rotation")
    public void setMarkerRotation(AirMapMarker view, float rotation) {
        view.setRotation(rotation);
    }

    @ReactProp(defaultBoolean = false, name = "flat")
    public void setFlat(AirMapMarker view, boolean flat) {
        view.setFlat(flat);
    }

    @ReactProp(defaultBoolean = false, name = "draggable")
    public void setDraggable(AirMapMarker view, boolean draggable) {
        view.setDraggable(draggable);
    }

    @ReactProp(defaultFloat = 0.0f, name = "zIndex")
    public void setZIndex(AirMapMarker view, float zIndex) {
        super.setZIndex(view, zIndex);
        view.setZIndex(Math.round(zIndex));
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(AirMapMarker view, float opacity) {
        super.setOpacity(view, opacity);
        view.setOpacity(opacity);
    }

    public void addView(AirMapMarker parent, View child, int index) {
        if (child instanceof AirMapCallout) {
            parent.setCalloutView((AirMapCallout) child);
            return;
        }
        super.addView(parent, child, index);
        parent.d_();
    }

    public void removeViewAt(AirMapMarker parent, int index) {
        super.removeViewAt(parent, index);
        parent.d_();
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return e.a("showCallout", Integer.valueOf(1), "hideCallout", Integer.valueOf(2));
    }

    public void receiveCommand(AirMapMarker view, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                ((d) view.a()).c();
                return;
            case 2:
                ((d) view.a()).d();
                return;
            default:
                return;
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Map<String, String>> map = e.a("onPress", e.a("registrationName", "onPress"), "onCalloutPress", e.a("registrationName", "onCalloutPress"), "onDragStart", e.a("registrationName", "onDragStart"), "onDrag", e.a("registrationName", "onDrag"), "onDragEnd", e.a("registrationName", "onDragEnd"));
        map.putAll(e.a("onDragStart", e.a("registrationName", "onDragStart"), "onDrag", e.a("registrationName", "onDrag"), "onDragEnd", e.a("registrationName", "onDragEnd")));
        return map;
    }

    public h createShadowNodeInstance() {
        return new d();
    }

    public void updateExtraData(AirMapMarker view, Object extraData) {
        HashMap<String, Float> data = (HashMap) extraData;
        view.a((int) ((Float) data.get("width")).floatValue(), (int) ((Float) data.get("height")).floatValue());
    }
}
