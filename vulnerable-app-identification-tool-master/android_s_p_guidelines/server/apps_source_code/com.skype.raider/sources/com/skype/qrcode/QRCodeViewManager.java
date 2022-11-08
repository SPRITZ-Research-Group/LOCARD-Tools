package com.skype.qrcode;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

class QRCodeViewManager extends SimpleViewManager<a> {
    public static final String PROP_SKPQR_BACKGROUND_COLOR = "skpqrBackgroundColor";
    public static final String PROP_SKPQR_COLOR = "skpqrColor";
    public static final String PROP_SKPQR_CORRECTION_LEVEL = "skpqrCorrectionLevel";
    public static final String PROP_SKPQR_MESSAGE = "skpqrMessage";
    public static final String REACT_CLASS = "SKPQRCodeView";

    QRCodeViewManager() {
    }

    public String getName() {
        return REACT_CLASS;
    }

    public a createViewInstance(ae context) {
        return new a(context);
    }

    protected void onAfterUpdateTransaction(a view) {
        super.onAfterUpdateTransaction(view);
        view.a();
    }

    @ReactProp(name = "skpqrMessage")
    public void setSkpqrMessage(a view, @Nullable String message) {
        view.a(message);
    }

    @ReactProp(name = "skpqrCorrectionLevel")
    public void setSkpqrCorrectionLevel(a view, @Nullable String correctionLevel) {
        view.b(correctionLevel);
    }

    @ReactProp(defaultInt = -16777216, name = "skpqrColor")
    public void setSkpqrColor(a view, int color) {
        view.a(color);
    }

    @ReactProp(defaultInt = -1, name = "skpqrBackgroundColor")
    public void setSkpqrBackgroundColor(a view, int backgroundColor) {
        view.b(backgroundColor);
    }
}
