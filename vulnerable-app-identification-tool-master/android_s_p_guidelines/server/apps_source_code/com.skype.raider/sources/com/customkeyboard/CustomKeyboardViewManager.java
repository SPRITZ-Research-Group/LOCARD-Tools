package com.customkeyboard;

import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;

public class CustomKeyboardViewManager extends ViewGroupManager<CustomKeyboardView> {
    public String getName() {
        return "CustomKeyboardView";
    }

    protected CustomKeyboardView createViewInstance(ae reactContext) {
        return new CustomKeyboardView(reactContext);
    }

    @ReactProp(name = "name")
    public void setKeyboardName(CustomKeyboardView view, String name) {
        if (view.getContext() instanceof ai) {
            ((CustomKeyboard) ((ai) view.getContext()).b(CustomKeyboard.class)).registerCustomKeyboard(name, view);
            view.a = name;
            return;
        }
        throw new IllegalStateException("React view context should be ReactContext");
    }

    @ReactProp(name = "useCustomHeight")
    public void setUseCustomHeight(CustomKeyboardView view, boolean useCustomHeight) {
        view.c = useCustomHeight;
    }

    @ReactProp(name = "disableDefaultKeyboard")
    public void setDisableDefaultKeyboard(CustomKeyboardView view, boolean disableDefaultKeyboard) {
        view.b = disableDefaultKeyboard;
    }
}
