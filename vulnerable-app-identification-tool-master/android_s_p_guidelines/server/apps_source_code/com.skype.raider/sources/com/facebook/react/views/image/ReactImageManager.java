package com.facebook.react.views.image;

import android.graphics.PorterDuff.Mode;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.b;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.common.e;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.p;
import com.facebook.yoga.a;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTImageView")
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    protected static final String REACT_CLASS = "RCTImageView";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private b mDraweeControllerBuilder;

    public String getName() {
        return REACT_CLASS;
    }

    public ReactImageManager(b draweeControllerBuilder, Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }

    public b getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.a();
        }
        return this.mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public ReactImageView createViewInstance(ae context) {
        return new ReactImageView(context, getDraweeControllerBuilder(), getCallerContext());
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView view, @Nullable al sources) {
        view.setSource(sources);
    }

    @ReactProp(name = "blurRadius")
    public void setBlurRadius(ReactImageView view, float blurRadius) {
        view.setBlurRadius(blurRadius);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView view, @Nullable String source) {
        view.setLoadingIndicatorSource(source);
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(ReactImageView view, @Nullable Integer borderColor) {
        if (borderColor == null) {
            view.setBorderColor(0);
        } else {
            view.setBorderColor(borderColor.intValue());
        }
    }

    @ReactProp(name = "overlayColor")
    public void setOverlayColor(ReactImageView view, @Nullable Integer overlayColor) {
        if (overlayColor == null) {
            view.setOverlayColor(0);
        } else {
            view.setOverlayColor(overlayColor.intValue());
        }
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ReactImageView view, float borderWidth) {
        view.setBorderWidth(borderWidth);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactImageView view, int index, float borderRadius) {
        if (!a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactImageView view, @Nullable String resizeMode) {
        view.setScaleType(c.a(resizeMode));
    }

    @ReactProp(name = "resizeMethod")
    public void setResizeMethod(ReactImageView view, @Nullable String resizeMethod) {
        if (resizeMethod == null || "auto".equals(resizeMethod)) {
            view.setResizeMethod(b.AUTO);
        } else if ("resize".equals(resizeMethod)) {
            view.setResizeMethod(b.RESIZE);
        } else if ("scale".equals(resizeMethod)) {
            view.setResizeMethod(b.SCALE);
        } else {
            throw new n("Invalid resize method: '" + resizeMethod + "'");
        }
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ReactImageView view, @Nullable Integer tintColor) {
        if (tintColor == null) {
            view.clearColorFilter();
        } else {
            view.setColorFilter(tintColor.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView view, boolean enabled) {
        view.setProgressiveRenderingEnabled(enabled);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactImageView view, int durationMs) {
        view.setFadeDuration(durationMs);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView view, boolean shouldNotifyLoadEvents) {
        view.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReactImageView view, am headers) {
        view.setHeaders(headers);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a(a.b(4), e.a("registrationName", "onLoadStart"), a.b(2), e.a("registrationName", "onLoad"), a.b(1), e.a("registrationName", "onError"), a.b(3), e.a("registrationName", "onLoadEnd"));
    }

    protected void onAfterUpdateTransaction(ReactImageView view) {
        super.onAfterUpdateTransaction(view);
        view.a();
    }

    protected void onHostDestroy() {
        super.onHostDestroy();
        this.mDraweeControllerBuilder = null;
    }
}
