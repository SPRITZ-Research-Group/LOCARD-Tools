package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.view.View;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ad;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aj;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.l;
import com.facebook.react.bridge.n;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.views.view.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "UIManager")
public class UIManagerModule extends ReactContextBaseJavaModule implements ad, v {
    private static final boolean DEBUG = false;
    protected static final String NAME = "UIManager";
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private static volatile float sMaxContentSizeMultiplier = 0.0f;
    private int mBatchId = 0;
    private final c mEventDispatcher;
    private final a mMemoryTrimCallback = new a();
    private final Map<String, Object> mModuleConstants;
    private int mNextRootViewTag = 1;
    private final ai mUIImplementation;

    private class a implements ComponentCallbacks2 {
        final /* synthetic */ UIManagerModule a;

        private a(UIManagerModule uIManagerModule) {
            this.a = uIManagerModule;
        }

        /* synthetic */ a(UIManagerModule x0, byte b) {
            this(x0);
        }

        public final void onTrimMemory(int level) {
            if (level >= 60) {
                as.a().b();
            }
        }

        public final void onConfigurationChanged(Configuration newConfig) {
        }

        public final void onLowMemory() {
        }
    }

    public UIManagerModule(ag reactContext, List<ViewManager> viewManagerList, aj uiImplementationProvider, boolean lazyViewManagersEnabled) {
        super(reactContext);
        b.a(reactContext);
        this.mEventDispatcher = new c(reactContext);
        this.mModuleConstants = createConstants(viewManagerList, lazyViewManagersEnabled);
        this.mUIImplementation = new ai(reactContext, (List) viewManagerList, this.mEventDispatcher);
        reactContext.a((v) this);
    }

    public ai getUIImplementation() {
        return this.mUIImplementation;
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
    }

    public void onHostResume() {
        this.mUIImplementation.e();
    }

    public void onHostPause() {
        this.mUIImplementation.f();
    }

    public void onHostDestroy() {
        this.mUIImplementation.g();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.a();
        getReactApplicationContext().unregisterComponentCallbacks(this.mMemoryTrimCallback);
        as.a().b();
        ao.a();
        b.a();
    }

    private static Map<String, Object> createConstants(List<ViewManager> viewManagerList, boolean lazyViewManagersEnabled) {
        ReactMarker.logMarker(aj.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        com.facebook.systrace.a.a("CreateUIManagerConstants");
        try {
            Map<String, Object> a = ak.a((List) viewManagerList, lazyViewManagersEnabled);
            return a;
        } finally {
            com.facebook.systrace.a.a();
            ReactMarker.logMarker(aj.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    public Map<String, Double> getPerformanceCounters() {
        Map<String, Double> perfMap = new HashMap();
        perfMap.put("LayoutCount", Double.valueOf(this.mUIImplementation.b()));
        perfMap.put("LayoutTimer", Double.valueOf(this.mUIImplementation.c()));
        return perfMap;
    }

    public int addRootView(SizeMonitoringFrameLayout rootView) {
        int width;
        int height;
        com.facebook.systrace.a.a("UIManagerModule.addRootView");
        final int tag = this.mNextRootViewTag;
        this.mNextRootViewTag += 10;
        if (rootView.getLayoutParams() == null || rootView.getLayoutParams().width <= 0 || rootView.getLayoutParams().height <= 0) {
            width = rootView.getWidth();
            height = rootView.getHeight();
        } else {
            width = rootView.getLayoutParams().width;
            height = rootView.getLayoutParams().height;
        }
        final ag reactApplicationContext = getReactApplicationContext();
        this.mUIImplementation.a(rootView, tag, width, height, new ae(reactApplicationContext, rootView.getContext()));
        rootView.setOnSizeChangedListener(new com.facebook.react.uimanager.SizeMonitoringFrameLayout.a(this) {
            final /* synthetic */ UIManagerModule c;

            public final void a(final int width, final int height) {
                reactApplicationContext.e(new l(this, reactApplicationContext) {
                    final /* synthetic */ AnonymousClass1 c;

                    public final void a() {
                        this.c.c.updateNodeSize(tag, width, height);
                    }
                });
            }
        });
        com.facebook.systrace.a.a();
        return tag;
    }

    @ReactMethod
    public void removeRootView(int rootViewTag) {
        this.mUIImplementation.a(rootViewTag);
    }

    public void updateNodeSize(int nodeViewTag, int newWidth, int newHeight) {
        getReactApplicationContext().h();
        this.mUIImplementation.a(nodeViewTag, newWidth, newHeight);
    }

    @ReactMethod
    public void createView(int tag, String className, int rootViewTag, am props) {
        this.mUIImplementation.a(tag, className, rootViewTag, props);
    }

    @ReactMethod
    public void updateView(int tag, String className, am props) {
        this.mUIImplementation.a(tag, className, props);
    }

    @ReactMethod
    public void manageChildren(int viewTag, @Nullable al moveFrom, @Nullable al moveTo, @Nullable al addChildTags, @Nullable al addAtIndices, @Nullable al removeFrom) {
        this.mUIImplementation.a(viewTag, moveFrom, moveTo, addChildTags, addAtIndices, removeFrom);
    }

    @ReactMethod
    public void setChildren(int viewTag, al childrenTags) {
        this.mUIImplementation.a(viewTag, childrenTags);
    }

    @ReactMethod
    public void replaceExistingNonRootView(int oldTag, int newTag) {
        this.mUIImplementation.a(oldTag, newTag);
    }

    @ReactMethod
    public void removeSubviewsFromContainerWithID(int containerTag) {
        this.mUIImplementation.b(containerTag);
    }

    @ReactMethod
    public void measure(int reactTag, d callback) {
        this.mUIImplementation.a(reactTag, callback);
    }

    @ReactMethod
    public void measureInWindow(int reactTag, d callback) {
        this.mUIImplementation.b(reactTag, callback);
    }

    @ReactMethod
    public void measureLayout(int tag, int ancestorTag, d errorCallback, d successCallback) {
        this.mUIImplementation.a(tag, ancestorTag, errorCallback, successCallback);
    }

    @ReactMethod
    public void measureLayoutRelativeToParent(int tag, d errorCallback, d successCallback) {
        this.mUIImplementation.a(tag, errorCallback, successCallback);
    }

    @ReactMethod
    public void findSubviewIn(int reactTag, al point, d callback) {
        this.mUIImplementation.a(reactTag, (float) Math.round(p.a((float) point.getDouble(0))), (float) Math.round(p.a((float) point.getDouble(1))), callback);
    }

    @ReactMethod
    public void viewIsDescendantOf(int reactTag, int ancestorReactTag, d callback) {
        this.mUIImplementation.a(reactTag, ancestorReactTag, callback);
    }

    public void registerAnimation(com.facebook.react.a.a animation) {
        this.mUIImplementation.a(animation);
    }

    public void addAnimation(int reactTag, int animationID, d onSuccess) {
        this.mUIImplementation.b(reactTag, animationID, onSuccess);
    }

    public void removeAnimation(int reactTag, int animationID) {
        this.mUIImplementation.b(reactTag, animationID);
    }

    @ReactMethod
    public void setJSResponder(int reactTag, boolean blockNativeResponder) {
        this.mUIImplementation.a(reactTag, blockNativeResponder);
    }

    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.d();
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int reactTag, int commandId, al commandArgs) {
        this.mUIImplementation.a(reactTag, commandId, commandArgs);
    }

    @ReactMethod
    public void showPopupMenu(int reactTag, al items, d error, d success) {
        this.mUIImplementation.a(reactTag, items, error, success);
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean enabled) {
        this.mUIImplementation.a(enabled);
    }

    @ReactMethod
    public void configureNextLayoutAnimation(am config, d success, d error) {
        this.mUIImplementation.a(config);
    }

    public void onBatchComplete() {
        int batchId = this.mBatchId;
        this.mBatchId++;
        com.facebook.systrace.b.a();
        try {
            this.mUIImplementation.c(batchId);
        } finally {
            com.facebook.systrace.a.a();
        }
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable com.facebook.react.uimanager.debug.a listener) {
        this.mUIImplementation.a(listener);
    }

    public c getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @ReactMethod
    public void sendAccessibilityEvent(int tag, int eventType) {
        this.mUIImplementation.c(tag, eventType);
    }

    public void addUIBlock(ah block) {
        this.mUIImplementation.a(block);
    }

    public int resolveRootTagFromReactTag(int reactTag) {
        return this.mUIImplementation.d(reactTag);
    }

    @ReactMethod
    public void getContentSizeMultiplier(d callback) {
        float scale = getReactApplicationContext().getResources().getConfiguration().fontScale;
        callback.invoke(Float.valueOf(scale));
    }

    @ReactMethod
    public void getMaxContentSizeMultiplier(d callback) {
        callback.invoke(Float.valueOf(sMaxContentSizeMultiplier));
    }

    public static float getMaxContentSizeMultiplierInternal() {
        return sMaxContentSizeMultiplier;
    }

    @ReactMethod
    public void setMaxContentSizeMultiplier(float maxContentSizeMultiplier) {
        if (maxContentSizeMultiplier == sMaxContentSizeMultiplier) {
            return;
        }
        if (Float.isNaN(maxContentSizeMultiplier) || (maxContentSizeMultiplier != 0.0f && maxContentSizeMultiplier < 1.0f)) {
            throw new n("maxContentSizeMultiplier must be 0 or >= 1");
        }
        sMaxContentSizeMultiplier = maxContentSizeMultiplier;
    }

    public View resolveView(int tag) {
        ap.b();
        return this.mUIImplementation.a().a().a(tag);
    }
}
