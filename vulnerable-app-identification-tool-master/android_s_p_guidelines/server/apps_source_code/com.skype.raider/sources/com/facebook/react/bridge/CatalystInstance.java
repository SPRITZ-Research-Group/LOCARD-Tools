package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.queue.d;
import com.facebook.react.common.annotations.VisibleForTesting;
import java.util.Collection;
import javax.annotation.Nullable;

@DoNotStrip
public interface CatalystInstance extends p, x {
    void addBridgeIdleDebugListener(ac acVar);

    @DoNotStrip
    void callFunction(String str, String str2, NativeArray nativeArray);

    void destroy();

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    long getJavaScriptContext();

    <T extends NativeModule> T getNativeModule(Class<T> cls);

    Collection<NativeModule> getNativeModules();

    d getReactQueueConfiguration();

    @Nullable
    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    boolean hasRunJSBundle();

    @VisibleForTesting
    void initialize();

    @DoNotStrip
    void invokeCallback(int i, NativeArray nativeArray);

    boolean isDestroyed();

    void removeBridgeIdleDebugListener(ac acVar);

    void runJSBundle();

    @VisibleForTesting
    void setGlobalVariable(String str, String str2);

    void setReactConfigurationData(WritableNativeMap writableNativeMap);

    void startProfiler(String str);

    void stopProfiler(String str, String str2);

    boolean supportsProfiling();
}
