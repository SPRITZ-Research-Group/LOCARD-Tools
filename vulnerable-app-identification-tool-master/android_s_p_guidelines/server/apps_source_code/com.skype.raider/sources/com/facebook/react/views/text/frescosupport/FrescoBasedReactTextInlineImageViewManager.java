package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.b;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ae;
import javax.annotation.Nullable;

@ReactModule(name = "RCTTextInlineImage")
public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, a> {
    protected static final String REACT_CLASS = "RCTTextInlineImage";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private final b mDraweeControllerBuilder;

    public FrescoBasedReactTextInlineImageViewManager() {
        this(null, null);
    }

    public FrescoBasedReactTextInlineImageViewManager(@Nullable b draweeControllerBuilder, @Nullable Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public View createViewInstance(ae context) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    public a createShadowNodeInstance() {
        b bVar;
        if (this.mDraweeControllerBuilder != null) {
            bVar = this.mDraweeControllerBuilder;
        } else {
            bVar = Fresco.a();
        }
        return new a(bVar, this.mCallerContext);
    }

    public Class<a> getShadowNodeClass() {
        return a.class;
    }

    public void updateExtraData(View root, Object extraData) {
    }
}
