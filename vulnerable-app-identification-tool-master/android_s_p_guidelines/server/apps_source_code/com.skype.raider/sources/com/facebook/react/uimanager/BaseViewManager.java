package com.facebook.react.uimanager;

import android.os.Build.VERSION;
import android.view.View;
import com.facebook.react.bridge.al;
import com.facebook.react.h.b;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.i.a;

public abstract class BaseViewManager<T extends View, C extends h> extends ViewManager<T, C> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROP_ELEVATION = "elevation";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String PROP_NATIVE_ID = "nativeID";
    private static final String PROP_OPACITY = "opacity";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_ROTATION = "rotation";
    private static final String PROP_SCALE_X = "scaleX";
    private static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private static final String PROP_TRANSLATE_X = "translateX";
    private static final String PROP_TRANSLATE_Y = "translateY";
    private static final String PROP_VIEW_LAYER_TYPE = "viewLayerTypeAndroid";
    private static final String PROP_Z_INDEX = "zIndex";
    private static a sMatrixDecompositionContext = new a();
    private static double[] sTransformDecompositionArray = new double[16];
    private View mDefaultView;

    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(T view, int backgroundColor) {
        view.setBackgroundColor(backgroundColor);
    }

    @ReactProp(name = "transform")
    public void setTransform(T view, al matrix) {
        if (matrix == null) {
            resetTransformProperty(view);
        } else {
            setTransformProperty(view, matrix);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(T view, float opacity) {
        view.setAlpha(opacity);
    }

    @ReactProp(name = "elevation")
    public void setElevation(T view, float elevation) {
        if (VERSION.SDK_INT >= 21) {
            view.setElevation(p.a(elevation));
        }
    }

    @ReactProp(name = "zIndex")
    public void setZIndex(T view, float zIndex) {
        ViewGroupManager.setViewZIndex(view, Math.round(zIndex));
    }

    @ReactProp(name = "viewLayerTypeAndroid")
    public void setViewLayerType(T view, String viewLayerType) {
        int type = 0;
        if (viewLayerType == null || viewLayerType.equals("none")) {
            type = 0;
        } else if (viewLayerType.equals("hardware")) {
            type = 2;
        } else if (viewLayerType.equals("software")) {
            type = 1;
        } else {
            com.facebook.infer.annotation.a.a(false, "wrong viewLayerType: " + viewLayerType);
        }
        view.setLayerType(type, null);
    }

    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(T view, boolean useHWTexture) {
        view.setLayerType(useHWTexture ? 2 : 0, null);
    }

    @ReactProp(name = "testID")
    public void setTestId(T view, String testId) {
        view.setTag(b.react_test_id, testId);
        view.setTag(testId);
    }

    @ReactProp(name = "nativeID")
    public void setNativeId(T view, String nativeId) {
        view.setTag(b.view_tag_native_id, nativeId);
        com.facebook.react.uimanager.b.a.a(view);
    }

    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(T view, String accessibilityLabel) {
        view.setContentDescription(accessibilityLabel);
    }

    @ReactProp(name = "accessibilityComponentType")
    public void setAccessibilityComponentType(T view, String accessibilityComponentType) {
        a.a((View) view, accessibilityComponentType);
    }

    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(T view, String importantForAccessibility) {
        if (importantForAccessibility == null || importantForAccessibility.equals("auto")) {
            if (this.mDefaultView == null) {
                this.mDefaultView = createViewInstance((ae) view.getContext());
            }
            view.setFocusable(this.mDefaultView.isFocusable());
            view.setImportantForAccessibility(0);
        } else if (importantForAccessibility.equals("yes")) {
            view.setFocusable(true);
            view.setImportantForAccessibility(1);
        } else if (importantForAccessibility.equals("no")) {
            view.setFocusable(false);
            view.setImportantForAccessibility(2);
        } else if (importantForAccessibility.equals("no-hide-descendants")) {
            view.setFocusable(false);
            view.setImportantForAccessibility(4);
        }
    }

    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(T view, float rotation) {
        view.setRotation(rotation);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(T view, float scaleX) {
        view.setScaleX(scaleX);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(T view, float scaleY) {
        view.setScaleY(scaleY);
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(T view, float translateX) {
        view.setTranslationX(p.a(translateX));
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(T view, float translateY) {
        view.setTranslationY(p.a(translateY));
    }

    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(T view, String liveRegion) {
        if (VERSION.SDK_INT < 19) {
            return;
        }
        if (liveRegion == null || liveRegion.equals("none")) {
            view.setAccessibilityLiveRegion(0);
        } else if (liveRegion.equals("polite")) {
            view.setAccessibilityLiveRegion(1);
        } else if (liveRegion.equals("assertive")) {
            view.setAccessibilityLiveRegion(2);
        }
    }

    protected void onHostDestroy() {
        super.onHostDestroy();
        this.mDefaultView = null;
    }

    private static void setTransformProperty(View view, al transforms) {
        ag.a(transforms, sTransformDecompositionArray);
        i.a(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(p.a((float) sMatrixDecompositionContext.e[0]));
        view.setTranslationY(p.a((float) sMatrixDecompositionContext.e[1]));
        view.setRotation((float) sMatrixDecompositionContext.f[2]);
        view.setRotationX((float) sMatrixDecompositionContext.f[0]);
        view.setRotationY((float) sMatrixDecompositionContext.f[1]);
        view.setScaleX((float) sMatrixDecompositionContext.c[0]);
        view.setScaleY((float) sMatrixDecompositionContext.c[1]);
        double[] perspectiveArray = sMatrixDecompositionContext.a;
        if (perspectiveArray.length > 2) {
            float invertedCameraDistance = (float) perspectiveArray[2];
            if (invertedCameraDistance < 0.0f) {
                view.setCameraDistance((b.b().density * (-1.0f / invertedCameraDistance)) * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            }
        }
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(p.a(0.0f));
        view.setTranslationY(p.a(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }
}
