package com.skpcamera;

import android.app.Activity;
import android.util.DisplayMetrics;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import java.util.HashMap;
import java.util.Map;

public class SkypeCameraModule extends ReactContextBaseJavaModule {
    private static final String FACING_BACK_KEY = "back";
    private static final String FACING_FRONT_KEY = "front";
    private static final String FLASH_AUTO_KEY = "auto";
    private static final String FLASH_OFF_KEY = "off";
    private static final String FLASH_ON_KEY = "on";
    public static final int RCT_CAMERA_FLASH_MODE_AUTO = 2;
    public static final int RCT_CAMERA_FLASH_MODE_OFF = 0;
    public static final int RCT_CAMERA_FLASH_MODE_ON = 1;
    public static final int RCT_CAMERA_TYPE_BACK = 2;
    public static final int RCT_CAMERA_TYPE_FRONT = 1;
    private static final String TAG = "SkypeCameraModule";

    public SkypeCameraModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "SkypeCamera";
    }

    public void initialize() {
        super.initialize();
        FLog.i(TAG, "SkypeCameraModule initialize");
        b.a().a(getReactApplicationContext());
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("CameraMode", getConstantsCameraMode());
        constants.put("FlashMode", getConstantsFlashMode());
        return constants;
    }

    private Map<String, Object> getConstantsCameraMode() {
        Map<String, Object> cameraMode = new HashMap();
        cameraMode.put(FACING_BACK_KEY, Integer.valueOf(2));
        cameraMode.put(FACING_FRONT_KEY, Integer.valueOf(1));
        return cameraMode;
    }

    private Map<String, Object> getConstantsFlashMode() {
        Map<String, Object> flashMode = new HashMap();
        flashMode.put(FLASH_ON_KEY, Integer.valueOf(1));
        flashMode.put(FLASH_OFF_KEY, Integer.valueOf(0));
        flashMode.put(FLASH_AUTO_KEY, Integer.valueOf(2));
        return flashMode;
    }

    @ReactMethod
    public void setVersion(int version) {
        b.a().a(version);
    }

    @Deprecated
    @ReactMethod
    public void fxSceneSupported(ae promise) {
    }

    @ReactMethod
    public void stopSession() {
        FLog.i(TAG, "stopSession");
        b.a().c().a();
    }

    @ReactMethod
    public void startSession() {
        FLog.i(TAG, "startSession");
        b.a().c().b();
    }

    @ReactMethod
    public void pauseSession() {
        FLog.i(TAG, "pauseSession");
        b.a().c().c();
    }

    @ReactMethod
    public void startRecording(ae promise) {
        FLog.i(TAG, "startRecording");
        b.a().c().a(promise);
    }

    @ReactMethod
    public void finishRecording() {
        FLog.i(TAG, "finishRecording");
        b.a().c().d();
    }

    @ReactMethod
    public void cancelRecording() {
        FLog.i(TAG, "cancel");
        b.a().c().e();
    }

    @ReactMethod
    public void capture(@Deprecated boolean freeze, boolean correctForExposure, ae promise) {
        FLog.i(TAG, "capture");
        b.a().c().a(freeze, promise);
    }

    @ReactMethod
    public void supportedCameras(ae promise) {
        b.a().c().b(promise);
    }

    @ReactMethod
    public void hasFlash(ae promise) {
        b.a().c().c(promise);
    }

    @ReactMethod
    public void focus(int x, int y) {
        Activity activity = getReactApplicationContext().j();
        if (activity != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            b.a().c().a((int) (((float) x) * metrics.density), (int) (((float) y) * metrics.density));
        }
    }

    @ReactMethod
    public void zoom(boolean complete, int d, int d0) {
        b.a().c().a(complete, d, d0);
    }

    @ReactMethod
    public void cancel() {
        b.a().c().f();
    }

    @ReactMethod
    public void enterPreview() {
        b.a().c().g();
    }

    @ReactMethod
    public void discardTempFiles() {
        FLog.i(TAG, "discardTempFiles not implemented on Android");
    }

    @ReactMethod
    public void destroy() {
        b.a().c().h();
    }

    private static HashMap<String, String> toHashMap(am readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        HashMap<String, String> hashMap = new HashMap();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    hashMap.put(key, null);
                    break;
                case String:
                    hashMap.put(key, readableMap.getString(key));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }
}
