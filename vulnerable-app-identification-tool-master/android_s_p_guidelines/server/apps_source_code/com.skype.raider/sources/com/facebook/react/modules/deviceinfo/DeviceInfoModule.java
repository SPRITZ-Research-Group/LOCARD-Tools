package com.facebook.react.modules.deviceinfo;

import android.content.Context;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.b;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "DeviceInfo")
public class DeviceInfoModule extends BaseJavaModule implements v {
    private float mFontScale;
    @Nullable
    private ag mReactApplicationContext;

    public DeviceInfoModule(ag reactContext) {
        this((Context) reactContext);
        this.mReactApplicationContext = reactContext;
    }

    public DeviceInfoModule(Context context) {
        this.mReactApplicationContext = null;
        b.a(context);
        this.mFontScale = context.getResources().getConfiguration().fontScale;
    }

    public String getName() {
        return "DeviceInfo";
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap();
        constants.put("Dimensions", getDimensionsConstants());
        return constants;
    }

    public void onHostResume() {
        if (this.mReactApplicationContext != null) {
            float fontScale = this.mReactApplicationContext.getResources().getConfiguration().fontScale;
            if (this.mFontScale != fontScale) {
                this.mFontScale = fontScale;
                emitUpdateDimensionsEvent();
            }
        }
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
    }

    public void emitUpdateDimensionsEvent() {
        if (this.mReactApplicationContext != null) {
            ((RCTDeviceEventEmitter) this.mReactApplicationContext.a(RCTDeviceEventEmitter.class)).emit("didUpdateDimensions", getDimensionsConstants());
        }
    }

    private ar getDimensionsConstants() {
        DisplayMetrics windowDisplayMetrics = b.a();
        DisplayMetrics screenDisplayMetrics = b.b();
        ar windowDisplayMetricsMap = new WritableNativeMap();
        windowDisplayMetricsMap.putInt("width", windowDisplayMetrics.widthPixels);
        windowDisplayMetricsMap.putInt("height", windowDisplayMetrics.heightPixels);
        windowDisplayMetricsMap.putDouble("scale", (double) windowDisplayMetrics.density);
        windowDisplayMetricsMap.putDouble("fontScale", (double) this.mFontScale);
        windowDisplayMetricsMap.putDouble("densityDpi", (double) windowDisplayMetrics.densityDpi);
        ar screenDisplayMetricsMap = new WritableNativeMap();
        screenDisplayMetricsMap.putInt("width", screenDisplayMetrics.widthPixels);
        screenDisplayMetricsMap.putInt("height", screenDisplayMetrics.heightPixels);
        screenDisplayMetricsMap.putDouble("scale", (double) screenDisplayMetrics.density);
        screenDisplayMetricsMap.putDouble("fontScale", (double) this.mFontScale);
        screenDisplayMetricsMap.putDouble("densityDpi", (double) screenDisplayMetrics.densityDpi);
        ar dimensionsMap = new WritableNativeMap();
        dimensionsMap.putMap("windowPhysicalPixels", windowDisplayMetricsMap);
        dimensionsMap.putMap("screenPhysicalPixels", screenDisplayMetricsMap);
        return dimensionsMap;
    }
}
