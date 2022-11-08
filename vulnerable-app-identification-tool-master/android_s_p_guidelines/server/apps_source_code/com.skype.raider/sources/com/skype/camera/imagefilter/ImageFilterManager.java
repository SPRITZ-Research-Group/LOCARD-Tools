package com.skype.camera.imagefilter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ImageFilterManager extends SimpleViewManager<ImageFilterView> implements EventReporter {
    public static final String PROP_FILTER = "filter";
    public static final String PROP_FILTER_DIMENSION = "filterDimension";
    public static final String PROP_SOURCE = "source";
    public static final String REACT_CLASS = "ImageFilter";
    private RCTDeviceEventEmitter eventReporter;

    public String getName() {
        return REACT_CLASS;
    }

    public ImageFilterView createViewInstance(ae reactContext) {
        this.eventReporter = (RCTDeviceEventEmitter) reactContext.a(RCTDeviceEventEmitter.class);
        return new ImageFilterView((Context) reactContext, (EventReporter) this);
    }

    @ReactProp(name = "source")
    public void setSrc(ImageFilterView view, @Nullable String src) {
        if (!TextUtils.isEmpty(src)) {
            view.setSrc(Uri.parse(src));
        }
    }

    @ReactProp(name = "filter")
    public void setFilter(ImageFilterView view, @Nullable String filter) {
        view.setFilter(filter);
    }

    public void reportEvent(String eventName, String payload) {
        if (this.eventReporter != null) {
            ar data = new WritableNativeMap();
            data.putString("payload", payload);
            this.eventReporter.emit(eventName, data);
        }
    }
}
