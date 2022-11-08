package com.airbnb.android.react.lottie;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView.ScaleType;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieAnimationView.a;
import com.facebook.react.bridge.al;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import java.util.WeakHashMap;

class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RESET = 2;
    private static final String REACT_CLASS = "LottieAnimationView";
    public static final String TAG = LottieAnimationViewManager.class.getSimpleName();
    private static final int VERSION = 1;
    private Map<LottieAnimationView, a> propManagersMap = new WeakHashMap();

    LottieAnimationViewManager() {
    }

    public Map<String, Object> getExportedViewConstants() {
        return e.a().a("VERSION", Integer.valueOf(1)).a();
    }

    public String getName() {
        return REACT_CLASS;
    }

    public LottieAnimationView createViewInstance(ae context) {
        LottieAnimationView view = new LottieAnimationView(context);
        view.setScaleType(ScaleType.CENTER_INSIDE);
        return view;
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("play", Integer.valueOf(1), "reset", Integer.valueOf(2));
    }

    public void receiveCommand(final LottieAnimationView view, int commandId, final al args) {
        switch (commandId) {
            case 1:
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ LottieAnimationViewManager c;

                    public final void run() {
                        int startFrame = args.getInt(0);
                        int endFrame = args.getInt(1);
                        if (!(startFrame == -1 || endFrame == -1)) {
                            view.setMinAndMaxFrame(args.getInt(0), args.getInt(1));
                        }
                        if (ViewCompat.D(view)) {
                            view.setProgress(0.0f);
                            view.a();
                        }
                    }
                });
                return;
            case 2:
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ LottieAnimationViewManager b;

                    public final void run() {
                        if (ViewCompat.D(view)) {
                            view.b();
                            view.setProgress(0.0f);
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView view, String name) {
        getOrCreatePropertyManager(view).a(name);
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView view, String json) {
        getOrCreatePropertyManager(view).b(json);
    }

    @ReactProp(name = "templateContext")
    public void setTemplateContext(LottieAnimationView view, String json) {
        getOrCreatePropertyManager(view).c(json);
    }

    @ReactProp(name = "cacheStrategy")
    public void setCacheStrategy(LottieAnimationView view, String name) {
        if (name != null) {
            a strategy = a.Weak;
            Object obj = -1;
            switch (name.hashCode()) {
                case -891980137:
                    if (name.equals("strong")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3387192:
                    if (name.equals("none")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3645304:
                    if (name.equals("weak")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    strategy = a.None;
                    break;
                case 1:
                    strategy = a.Weak;
                    break;
                case 2:
                    strategy = a.Strong;
                    break;
            }
            getOrCreatePropertyManager(view).a(strategy);
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(LottieAnimationView view, String resizeMode) {
        ScaleType mode = null;
        if ("cover".equals(resizeMode)) {
            mode = ScaleType.CENTER_CROP;
        } else if ("contain".equals(resizeMode)) {
            mode = ScaleType.CENTER_INSIDE;
        } else if ("center".equals(resizeMode)) {
            mode = ScaleType.CENTER;
        }
        getOrCreatePropertyManager(view).a(mode);
    }

    @ReactProp(name = "progress")
    public void setProgress(LottieAnimationView view, float progress) {
        getOrCreatePropertyManager(view).a(Float.valueOf(progress));
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView view, double speed) {
        getOrCreatePropertyManager(view).a((float) speed);
    }

    @ReactProp(name = "loop")
    public void setLoop(LottieAnimationView view, boolean loop) {
        getOrCreatePropertyManager(view).a(loop);
    }

    @ReactProp(name = "hardwareAccelerationAndroid")
    public void setHardwareAcceleration(LottieAnimationView view, boolean use) {
        getOrCreatePropertyManager(view).b(use);
    }

    @ReactProp(name = "imageAssetsFolder")
    public void setImageAssetsFolder(LottieAnimationView view, String imageAssetsFolder) {
        getOrCreatePropertyManager(view).d(imageAssetsFolder);
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public void setEnableMergePaths(LottieAnimationView view, boolean enableMergePaths) {
        getOrCreatePropertyManager(view).c(enableMergePaths);
    }

    protected void onAfterUpdateTransaction(LottieAnimationView view) {
        super.onAfterUpdateTransaction(view);
        getOrCreatePropertyManager(view).a();
    }

    private a getOrCreatePropertyManager(LottieAnimationView view) {
        a result = (a) this.propManagersMap.get(view);
        if (result != null) {
            return result;
        }
        result = new a(view);
        this.propManagersMap.put(view, result);
        return result;
    }
}
