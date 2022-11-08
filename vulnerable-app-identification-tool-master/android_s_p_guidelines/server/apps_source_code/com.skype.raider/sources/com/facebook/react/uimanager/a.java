package com.facebook.react.uimanager;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RadioButton;

final class a {
    private static final AccessibilityDelegate a = new AccessibilityDelegate() {
        public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(Button.class.getName());
        }

        public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(Button.class.getName());
        }
    };
    private static final AccessibilityDelegate b = new AccessibilityDelegate() {
        public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(RadioButton.class.getName());
            event.setChecked(true);
        }

        public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(RadioButton.class.getName());
            info.setCheckable(true);
            info.setChecked(true);
        }
    };
    private static final AccessibilityDelegate c = new AccessibilityDelegate() {
        public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(RadioButton.class.getName());
            event.setChecked(false);
        }

        public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(RadioButton.class.getName());
            info.setCheckable(true);
            info.setChecked(false);
        }
    };

    public static void a(View view, String componentType) {
        if (componentType == null) {
            view.setAccessibilityDelegate(null);
            return;
        }
        Object obj = -1;
        switch (componentType.hashCode()) {
            case -1377687758:
                if (componentType.equals("button")) {
                    obj = null;
                    break;
                }
                break;
            case -1320494052:
                if (componentType.equals("radiobutton_unchecked")) {
                    obj = 2;
                    break;
                }
                break;
            case -714126251:
                if (componentType.equals("radiobutton_checked")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                view.setAccessibilityDelegate(a);
                return;
            case 1:
                view.setAccessibilityDelegate(b);
                return;
            case 2:
                view.setAccessibilityDelegate(c);
                return;
            default:
                view.setAccessibilityDelegate(null);
                return;
        }
    }

    public static void a(View view, int eventType) {
        view.sendAccessibilityEvent(eventType);
    }
}
