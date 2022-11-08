package com.facebook.react.flat;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.q;
import com.facebook.react.views.view.d;
import java.util.Map;
import javax.annotation.Nullable;

public final class RCTViewManager extends FlatViewManager {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    static final String REACT_CLASS = "RCTView";
    private static final int[] TMP_INT_ARRAY = new int[2];

    public final /* bridge */ /* synthetic */ void removeAllViews(t tVar) {
        super.removeAllViews(tVar);
    }

    public final /* bridge */ /* synthetic */ void setBackgroundColor(t tVar, int i) {
        super.setBackgroundColor(tVar, i);
    }

    public final String getName() {
        return "RCTView";
    }

    public final Map<String, Integer> getCommandsMap() {
        return e.a("hotspotUpdate", Integer.valueOf(1), "setPressed", Integer.valueOf(2));
    }

    public final ae createShadowNodeInstance() {
        return new ae();
    }

    public final Class<ae> getShadowNodeClass() {
        return ae.class;
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public final void setHotspot(t view, @Nullable am bg) {
        Drawable drawable;
        if (bg == null) {
            drawable = null;
        } else {
            drawable = d.a(view.getContext(), bg);
        }
        view.a(drawable);
    }

    public final void receiveCommand(t view, int commandId, @Nullable al args) {
        switch (commandId) {
            case 1:
                if (args == null || args.size() != 2) {
                    throw new n("Illegal number of arguments for 'updateHotspot' command");
                } else if (VERSION.SDK_INT >= 21) {
                    view.getLocationOnScreen(TMP_INT_ARRAY);
                    view.drawableHotspotChanged(p.a((float) args.getDouble(0)) - ((float) TMP_INT_ARRAY[0]), p.a((float) args.getDouble(1)) - ((float) TMP_INT_ARRAY[1]));
                    return;
                } else {
                    return;
                }
            case 2:
                if (args == null || args.size() != 1) {
                    throw new n("Illegal number of arguments for 'setPressed' command");
                }
                view.setPressed(args.getBoolean(0));
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public final void setNeedsOffscreenAlphaCompositing(t view, boolean needsOffscreenAlphaCompositing) {
        view.a(needsOffscreenAlphaCompositing);
    }

    @ReactProp(name = "pointerEvents")
    public final void setPointerEvents(t view, @Nullable String pointerEventsStr) {
        view.a(parsePointerEvents(pointerEventsStr));
    }

    @ReactProp(name = "removeClippedSubviews")
    public final void setRemoveClippedSubviews(t view, boolean removeClippedSubviews) {
        view.b(removeClippedSubviews);
    }

    private static q parsePointerEvents(@Nullable String pointerEventsStr) {
        if (pointerEventsStr != null) {
            Object obj = -1;
            switch (pointerEventsStr.hashCode()) {
                case -2089141766:
                    if (pointerEventsStr.equals("box-none")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -2089112978:
                    if (pointerEventsStr.equals("box-only")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 3005871:
                    if (pointerEventsStr.equals("auto")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3387192:
                    if (pointerEventsStr.equals("none")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return q.NONE;
                case 1:
                    return q.AUTO;
                case 2:
                    return q.BOX_NONE;
                case 3:
                    return q.BOX_ONLY;
            }
        }
        return q.AUTO;
    }

    @ReactProp(name = "hitSlop")
    public final void setHitSlop(t view, @Nullable am hitSlop) {
        if (hitSlop == null) {
            view.b(null);
        } else {
            view.b(new Rect((int) p.a((float) hitSlop.getDouble("left")), (int) p.a((float) hitSlop.getDouble("top")), (int) p.a((float) hitSlop.getDouble("right")), (int) p.a((float) hitSlop.getDouble("bottom"))));
        }
    }
}
