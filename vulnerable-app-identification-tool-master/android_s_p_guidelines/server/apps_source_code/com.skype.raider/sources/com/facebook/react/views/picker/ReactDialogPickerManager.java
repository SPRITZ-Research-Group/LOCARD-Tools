package com.facebook.react.views.picker;

import android.content.Context;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ae;

@ReactModule(name = "AndroidDialogPicker")
public class ReactDialogPickerManager extends ReactPickerManager {
    protected static final String REACT_CLASS = "AndroidDialogPicker";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactPicker createViewInstance(ae reactContext) {
        return new ReactPicker((Context) reactContext, 0);
    }
}
