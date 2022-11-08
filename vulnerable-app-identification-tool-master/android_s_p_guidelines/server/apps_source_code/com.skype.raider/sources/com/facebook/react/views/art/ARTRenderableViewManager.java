package com.facebook.react.views.art;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.w;

public class ARTRenderableViewManager extends ViewManager<View, w> {
    static final String CLASS_GROUP = "ARTGroup";
    static final String CLASS_SHAPE = "ARTShape";
    static final String CLASS_TEXT = "ARTText";
    private final String mClassName;

    public static ARTRenderableViewManager createARTGroupViewManager() {
        return new ARTGroupViewManager();
    }

    public static ARTRenderableViewManager createARTShapeViewManager() {
        return new ARTShapeViewManager();
    }

    public static ARTRenderableViewManager createARTTextViewManager() {
        return new ARTTextViewManager();
    }

    ARTRenderableViewManager(String className) {
        this.mClassName = className;
    }

    public String getName() {
        return this.mClassName;
    }

    public w createShadowNodeInstance() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return new a();
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return new b();
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return new d();
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }

    public Class<? extends w> getShadowNodeClass() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return a.class;
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return b.class;
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return d.class;
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }

    protected View createViewInstance(ae reactContext) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }

    public void updateExtraData(View root, Object extraData) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
}
