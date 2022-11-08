package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.q;
import com.facebook.yoga.a;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTView")
public class ReactViewManager extends ViewGroupManager<ReactViewGroup> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};

    @ReactProp(name = "accessible")
    public void setAccessible(ReactViewGroup view, boolean accessible) {
        view.setFocusable(accessible);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactViewGroup view, int index, float borderRadius) {
        if (!a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(ReactViewGroup view, @Nullable am hitSlop) {
        int i = 0;
        if (hitSlop == null) {
            view.setHitSlopRect(null);
            return;
        }
        int a;
        int a2;
        int a3;
        if (hitSlop.hasKey("left")) {
            a = (int) p.a((float) hitSlop.getDouble("left"));
        } else {
            a = 0;
        }
        if (hitSlop.hasKey("top")) {
            a2 = (int) p.a((float) hitSlop.getDouble("top"));
        } else {
            a2 = 0;
        }
        if (hitSlop.hasKey("right")) {
            a3 = (int) p.a((float) hitSlop.getDouble("right"));
        } else {
            a3 = 0;
        }
        if (hitSlop.hasKey("bottom")) {
            i = (int) p.a((float) hitSlop.getDouble("bottom"));
        }
        view.setHitSlopRect(new Rect(a, a2, a3, i));
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(ReactViewGroup view, @Nullable String pointerEventsStr) {
        if (pointerEventsStr == null) {
            view.a(q.AUTO);
        } else {
            view.a(q.valueOf(pointerEventsStr.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup view, @Nullable am bg) {
        Drawable drawable;
        if (bg == null) {
            drawable = null;
        } else {
            drawable = d.a(view.getContext(), bg);
        }
        view.setTranslucentBackgroundDrawable(drawable);
    }

    @ReactProp(name = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(ReactViewGroup view, @Nullable am fg) {
        Drawable drawable;
        if (fg == null) {
            drawable = null;
        } else {
            drawable = d.a(view.getContext(), fg);
        }
        view.setForeground(drawable);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactViewGroup view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup view, boolean needsOffscreenAlphaCompositing) {
        view.setNeedsOffscreenAlphaCompositing(needsOffscreenAlphaCompositing);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactViewGroup view, int index, float width) {
        if (!a.a(width)) {
            width = p.a(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactViewGroup view, int index, Integer color) {
        float alphaComponent = Float.NaN;
        float rgbComponent = color == null ? Float.NaN : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactViewGroup view, @Nullable String value) {
        view.setOverflow(value);
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(ReactViewGroup view, boolean collapsable) {
    }

    @ReactProp(name = "restrictFocusWithin")
    public void setRestrictFocusWithin(ReactViewGroup view, boolean restrictFocusWithin) {
        view.setRestrictFocusWithin(restrictFocusWithin);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactViewGroup createViewInstance(ae context) {
        return new ReactViewGroup(context);
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("hotspotUpdate", Integer.valueOf(1), "setPressed", Integer.valueOf(2));
    }

    public void receiveCommand(ReactViewGroup root, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                if (args == null || args.size() != 2) {
                    throw new n("Illegal number of arguments for 'updateHotspot' command");
                } else if (VERSION.SDK_INT >= 21) {
                    root.drawableHotspotChanged(p.a((float) args.getDouble(0)), p.a((float) args.getDouble(1)));
                    return;
                } else {
                    return;
                }
            case 2:
                if (args == null || args.size() != 1) {
                    throw new n("Illegal number of arguments for 'setPressed' command");
                }
                root.setPressed(args.getBoolean(0));
                return;
            default:
                return;
        }
    }

    public void addView(ReactViewGroup parent, View child, int index) {
        if (parent.e_()) {
            parent.a(child, index);
        } else {
            parent.addView(child, index);
        }
    }

    public int getChildCount(ReactViewGroup parent) {
        if (parent.e_()) {
            return parent.i();
        }
        return parent.getChildCount();
    }

    public View getChildAt(ReactViewGroup parent, int index) {
        if (parent.e_()) {
            return parent.b(index);
        }
        return parent.getChildAt(index);
    }

    public void removeViewAt(ReactViewGroup parent, int index) {
        if (parent.e_()) {
            View child = getChildAt(parent, index);
            if (child.getParent() != null) {
                parent.removeView(child);
            }
            parent.a(child);
            return;
        }
        parent.removeViewAt(index);
    }

    public void removeAllViews(ReactViewGroup parent) {
        if (parent.e_()) {
            parent.j();
        } else {
            parent.removeAllViews();
        }
    }
}
