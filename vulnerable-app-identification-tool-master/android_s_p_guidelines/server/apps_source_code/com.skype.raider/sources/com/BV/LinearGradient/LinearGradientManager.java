package com.BV.LinearGradient;

import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;

public class LinearGradientManager extends SimpleViewManager<LinearGradientView> {
    public static final String PROP_BORDER_RADII = "borderRadii";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_END_POS = "endPoint";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POS = "startPoint";
    public static final String REACT_CLASS = "BVLinearGradient";

    public String getName() {
        return REACT_CLASS;
    }

    protected LinearGradientView createViewInstance(ae context) {
        return new LinearGradientView(context);
    }

    @ReactProp(name = "colors")
    public void setColors(LinearGradientView gradientView, al colors) {
        gradientView.setColors(colors);
    }

    @ReactProp(name = "locations")
    public void setLocations(LinearGradientView gradientView, al locations) {
        if (locations != null) {
            gradientView.setLocations(locations);
        }
    }

    @ReactProp(name = "startPoint")
    public void setStartPosition(LinearGradientView gradientView, al startPos) {
        gradientView.setStartPosition(startPos);
    }

    @ReactProp(name = "endPoint")
    public void setEndPosition(LinearGradientView gradientView, al endPos) {
        gradientView.setEndPosition(endPos);
    }

    @ReactProp(name = "borderRadii")
    public void setBorderRadii(LinearGradientView gradientView, al borderRadii) {
        gradientView.setBorderRadii(borderRadii);
    }
}
