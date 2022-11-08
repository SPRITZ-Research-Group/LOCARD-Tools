package com.facebook.react.views.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.c;
import com.facebook.react.bridge.n;

public final class d {
    private static final TypedValue a = new TypedValue();

    public static Drawable a(Context context, am drawableDescriptionDict) {
        String type = drawableDescriptionDict.getString("type");
        if ("ThemeAttrAndroid".equals(type)) {
            String attr = drawableDescriptionDict.getString("attribute");
            if (attr == null) {
                throw new c("Expected object to not be null!");
            }
            int attrID = context.getResources().getIdentifier(attr, "attr", "android");
            if (attrID == 0) {
                throw new n("Attribute " + attr + " couldn't be found in the resource list");
            } else if (!context.getTheme().resolveAttribute(attrID, a, true)) {
                throw new n("Attribute " + attr + " couldn't be resolved into a drawable");
            } else if (VERSION.SDK_INT >= 21) {
                return context.getResources().getDrawable(a.resourceId, context.getTheme());
            } else {
                return context.getResources().getDrawable(a.resourceId);
            }
        } else if (!"RippleAndroid".equals(type)) {
            throw new n("Invalid type for android drawable: " + type);
        } else if (VERSION.SDK_INT < 21) {
            throw new n("Ripple drawable is not available on android API <21");
        } else {
            int color;
            if (drawableDescriptionDict.hasKey("color") && !drawableDescriptionDict.isNull("color")) {
                color = drawableDescriptionDict.getInt("color");
            } else if (context.getTheme().resolveAttribute(16843820, a, true)) {
                color = context.getResources().getColor(a.resourceId);
            } else {
                throw new n("Attribute colorControlHighlight couldn't be resolved into a drawable");
            }
            Drawable mask = null;
            if (!(drawableDescriptionDict.hasKey("borderless") && !drawableDescriptionDict.isNull("borderless") && drawableDescriptionDict.getBoolean("borderless"))) {
                mask = new ColorDrawable(-1);
            }
            return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{color}), null, mask);
        }
    }
}
