package com.facebook.react.views.progressbar;

import android.content.Context;
import android.widget.ProgressBar;
import com.facebook.react.bridge.n;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidProgressBar")
public class ReactProgressBarViewManager extends BaseViewManager<a, b> {
    static final String DEFAULT_STYLE = "Normal";
    static final String PROP_ANIMATING = "animating";
    static final String PROP_INDETERMINATE = "indeterminate";
    static final String PROP_PROGRESS = "progress";
    static final String PROP_STYLE = "styleAttr";
    protected static final String REACT_CLASS = "AndroidProgressBar";
    private static Object sProgressBarCtorLock = new Object();

    public static ProgressBar createProgressBar(Context context, int style) {
        ProgressBar progressBar;
        synchronized (sProgressBarCtorLock) {
            progressBar = new ProgressBar(context, null, style);
        }
        return progressBar;
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected a createViewInstance(ae context) {
        return new a(context);
    }

    @ReactProp(name = "styleAttr")
    public void setStyle(a view, @Nullable String styleName) {
        view.a(styleName);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(a view, @Nullable Integer color) {
        view.a(color);
    }

    @ReactProp(name = "indeterminate")
    public void setIndeterminate(a view, boolean indeterminate) {
        view.a(indeterminate);
    }

    @ReactProp(name = "progress")
    public void setProgress(a view, double progress) {
        view.a(progress);
    }

    @ReactProp(name = "animating")
    public void setAnimating(a view, boolean animating) {
        view.b(animating);
    }

    public b createShadowNodeInstance() {
        return new b();
    }

    public Class<b> getShadowNodeClass() {
        return b.class;
    }

    public void updateExtraData(a root, Object extraData) {
    }

    protected void onAfterUpdateTransaction(a view) {
        view.a();
    }

    static int getStyleFromString(@Nullable String styleStr) {
        if (styleStr == null) {
            throw new n("ProgressBar needs to have a style, null received");
        } else if (styleStr.equals("Horizontal")) {
            return 16842872;
        } else {
            if (styleStr.equals("Small")) {
                return 16842873;
            }
            if (styleStr.equals("Large")) {
                return 16842874;
            }
            if (styleStr.equals("Inverse")) {
                return 16843399;
            }
            if (styleStr.equals("SmallInverse")) {
                return 16843400;
            }
            if (styleStr.equals("LargeInverse")) {
                return 16843401;
            }
            if (styleStr.equals(DEFAULT_STYLE)) {
                return 16842871;
            }
            throw new n("Unknown ProgressBar style: " + styleStr);
        }
    }
}
