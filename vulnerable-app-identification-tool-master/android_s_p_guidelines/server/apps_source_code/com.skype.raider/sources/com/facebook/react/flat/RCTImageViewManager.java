package com.facebook.react.flat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.b;
import javax.annotation.Nullable;

public final class RCTImageViewManager extends FlatViewManager {
    static final String REACT_CLASS = "RCTImageView";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private b mDraweeControllerBuilder;

    public final /* bridge */ /* synthetic */ void removeAllViews(t tVar) {
        super.removeAllViews(tVar);
    }

    public final /* bridge */ /* synthetic */ void setBackgroundColor(t tVar, int i) {
        super.setBackgroundColor(tVar, i);
    }

    public RCTImageViewManager() {
        this(null, null);
    }

    public RCTImageViewManager(b draweeControllerBuilder, Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public final String getName() {
        return REACT_CLASS;
    }

    public final z createShadowNodeInstance() {
        return new z(new k());
    }

    public final Class<z> getShadowNodeClass() {
        return z.class;
    }

    public final b getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.a();
        }
        return this.mDraweeControllerBuilder;
    }

    public final Object getCallerContext() {
        return this.mCallerContext;
    }
}
