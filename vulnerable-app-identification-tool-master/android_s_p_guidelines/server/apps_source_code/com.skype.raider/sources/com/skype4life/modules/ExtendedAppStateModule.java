package com.skype4life.modules;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Debug;
import android.os.Process;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "ExtendedAppState")
public class ExtendedAppStateModule extends ReactContextBaseJavaModule implements ComponentCallbacks2 {
    private static final String ERROR_CODE = "E_INVALID_CONTEXT";
    private static final String ERROR_MSG = "Failed to get memory stats";
    private static final String MEMORY_WARNING = "memoryWarning";
    private static final String TAG = "ExtendedAppState";
    private ActivityManager activityManager;
    private ag applicationContext;

    public ExtendedAppStateModule(ag reactContext) {
        super(reactContext);
        this.applicationContext = reactContext;
        this.activityManager = (ActivityManager) reactContext.getSystemService("activity");
        this.applicationContext.registerComponentCallbacks(this);
    }

    public String getName() {
        return TAG;
    }

    public void onTrimMemory(int level) {
        if (this.applicationContext.b() && level >= 10 && level != 20) {
            ((RCTDeviceEventEmitter) this.applicationContext.a(RCTDeviceEventEmitter.class)).emit(MEMORY_WARNING, null);
        }
    }

    public void onLowMemory() {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onCatalystInstanceDestroy() {
        this.applicationContext.unregisterComponentCallbacks(this);
        super.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void getMemoryStatsAsync(ae promise) {
        if (this.activityManager == null || this.applicationContext == null) {
            promise.a(ERROR_CODE, ERROR_MSG);
            return;
        }
        double usedMemory;
        MemoryInfo totalMemoryInfo = new MemoryInfo();
        int[] pids = new int[]{Process.myPid()};
        this.activityManager.getMemoryInfo(totalMemoryInfo);
        Debug.MemoryInfo[] processMemoryInfoList = this.activityManager.getProcessMemoryInfo(pids);
        if (processMemoryInfoList == null || processMemoryInfoList.length != 1) {
            usedMemory = 0.0d;
        } else {
            usedMemory = (double) (processMemoryInfoList[0].getTotalPss() * 1024);
        }
        Object map = new WritableNativeMap();
        map.putDouble("used", usedMemory);
        map.putDouble("free", (double) totalMemoryInfo.availMem);
        promise.a(map);
        new StringBuilder("getMemoryStatsAsync: used=").append((long) usedMemory).append(", free=").append(totalMemoryInfo.availMem);
    }
}
