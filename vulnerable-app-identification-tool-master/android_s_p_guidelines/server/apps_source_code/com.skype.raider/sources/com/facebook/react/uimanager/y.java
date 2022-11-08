package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;

public final class y {
    private static YogaConfig a;

    public static YogaConfig a() {
        if (a == null) {
            YogaConfig yogaConfig = new YogaConfig();
            a = yogaConfig;
            yogaConfig.setPointScaleFactor(0.0f);
            a.setUseLegacyStretchBehaviour(true);
        }
        return a;
    }
}
