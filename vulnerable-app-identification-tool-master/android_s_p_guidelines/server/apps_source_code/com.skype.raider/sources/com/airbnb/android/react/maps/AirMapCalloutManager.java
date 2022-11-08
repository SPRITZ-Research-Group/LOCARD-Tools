package com.airbnb.android.react.maps;

import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import java.util.Map;
import javax.annotation.Nullable;

public class AirMapCalloutManager extends ViewGroupManager<AirMapCallout> {
    public String getName() {
        return "AIRMapCallout";
    }

    public AirMapCallout createViewInstance(ae context) {
        return new AirMapCallout(context);
    }

    @ReactProp(defaultBoolean = false, name = "tooltip")
    public void setTooltip(AirMapCallout view, boolean tooltip) {
        view.setTooltip(tooltip);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }

    public h createShadowNodeInstance() {
        return new d();
    }

    public void updateExtraData(AirMapCallout view, Object extraData) {
        Map<String, Float> data = (Map) extraData;
        float width = ((Float) data.get("width")).floatValue();
        float height = ((Float) data.get("height")).floatValue();
        view.a = (int) width;
        view.b = (int) height;
    }
}
