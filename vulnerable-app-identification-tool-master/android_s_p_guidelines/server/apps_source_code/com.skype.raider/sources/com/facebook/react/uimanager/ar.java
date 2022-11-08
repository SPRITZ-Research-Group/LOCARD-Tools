package com.facebook.react.uimanager;

import com.facebook.react.bridge.am;
import java.util.Arrays;
import java.util.HashSet;

public final class ar {
    public static final int[] a = new int[]{8, 4, 5, 1, 3};
    public static final int[] b = new int[]{8, 7, 6, 4, 5, 1, 3};
    public static final int[] c = new int[]{4, 5, 1, 3};
    private static final HashSet<String> d = new HashSet(Arrays.asList(new String[]{"alignSelf", "alignItems", "collapsable", "overflow", "flex", "flexBasis", "flexDirection", "flexGrow", "flexShrink", "flexWrap", "justifyContent", "overflow", "alignContent", "display", "position", "right", "top", "bottom", "left", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom"}));

    public static boolean a(am map, String prop) {
        if (d.contains(prop)) {
            return true;
        }
        if (!"pointerEvents".equals(prop)) {
            return false;
        }
        String value = map.getString(prop);
        if ("auto".equals(value) || "box-none".equals(value)) {
            return true;
        }
        return false;
    }
}
