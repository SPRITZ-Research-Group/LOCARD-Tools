package com.facebook.react.uimanager;

import android.widget.ImageView.ScaleType;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.events.h;
import com.facebook.systrace.a;
import com.facebook.systrace.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ak {
    private static void a(Map dest, Map source) {
        for (Object key : source.keySet()) {
            Object sourceValue = source.get(key);
            Object destValue = dest.get(key);
            if (destValue != null && (sourceValue instanceof Map) && (destValue instanceof Map)) {
                a((Map) destValue, (Map) sourceValue);
            } else {
                dest.put(key, sourceValue);
            }
        }
    }

    static Map<String, Object> a(List<ViewManager> viewManagers, boolean lazyViewManagersEnabled) {
        Map<String, Object> constants = new HashMap();
        constants.put("UIView", e.a("ContentMode", e.a("ScaleAspectFit", Integer.valueOf(ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ScaleType.CENTER_INSIDE.ordinal()))));
        constants.put("StyleConstants", e.a("PointerEventsValues", e.a("none", Integer.valueOf(q.NONE.ordinal()), "boxNone", Integer.valueOf(q.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(q.BOX_ONLY.ordinal()), "unspecified", Integer.valueOf(q.AUTO.ordinal()))));
        constants.put("PopupMenu", e.a("dismissed", "dismissed", "itemSelected", "itemSelected"));
        constants.put("AccessibilityEventTypes", e.a("typeWindowStateChanged", Integer.valueOf(32), "typeViewClicked", Integer.valueOf(1)));
        Map bubblingEventTypesConstants = e.a().a("topChange", e.a("phasedRegistrationNames", e.a("bubbled", "onChange", "captured", "onChangeCapture"))).a("topSelect", e.a("phasedRegistrationNames", e.a("bubbled", "onSelect", "captured", "onSelectCapture"))).a(h.START.a(), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).a(h.MOVE.a(), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).a(h.END.a(), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).a();
        Map directEventTypesConstants = e.a().a("topContentSizeChange", e.a("registrationName", "onContentSizeChange")).a("topLayout", e.a("registrationName", "onLayout")).a("topLoadingError", e.a("registrationName", "onLoadingError")).a("topLoadingFinish", e.a("registrationName", "onLoadingFinish")).a("topLoadingStart", e.a("registrationName", "onLoadingStart")).a("topSelectionChange", e.a("registrationName", "onSelectionChange")).a("topMessage", e.a("registrationName", "onMessage")).a();
        for (ViewManager viewManager : viewManagers) {
            b.a();
            viewManager.getName();
            try {
                Map viewManagerBubblingEvents = viewManager.getExportedCustomBubblingEventTypeConstants();
                if (viewManagerBubblingEvents != null) {
                    a(bubblingEventTypesConstants, viewManagerBubblingEvents);
                }
                Map viewManagerDirectEvents = viewManager.getExportedCustomDirectEventTypeConstants();
                if (viewManagerDirectEvents != null) {
                    a(directEventTypesConstants, viewManagerDirectEvents);
                }
                Map viewManagerConstants = new HashMap();
                Map customViewConstants = viewManager.getExportedViewConstants();
                if (customViewConstants != null) {
                    viewManagerConstants.put("Constants", customViewConstants);
                }
                Map viewManagerCommands = viewManager.getCommandsMap();
                if (viewManagerCommands != null) {
                    viewManagerConstants.put("Commands", viewManagerCommands);
                }
                Map<String, String> viewManagerNativeProps = viewManager.getNativeProps();
                if (!viewManagerNativeProps.isEmpty()) {
                    viewManagerConstants.put("NativeProps", viewManagerNativeProps);
                }
                if (!viewManagerConstants.isEmpty()) {
                    constants.put(viewManager.getName(), viewManagerConstants);
                }
                a.a();
            } catch (Throwable th) {
                a.a();
            }
        }
        constants.put("customBubblingEventTypes", bubblingEventTypesConstants);
        constants.put("customDirectEventTypes", directEventTypesConstants);
        constants.put("AndroidLazyViewManagersEnabled", Boolean.valueOf(lazyViewManagersEnabled));
        return constants;
    }
}
