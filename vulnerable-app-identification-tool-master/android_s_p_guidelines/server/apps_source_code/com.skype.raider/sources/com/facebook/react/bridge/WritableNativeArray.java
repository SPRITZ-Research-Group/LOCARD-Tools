package com.facebook.react.bridge;

import com.facebook.infer.annotation.a;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class WritableNativeArray extends ReadableNativeArray implements aq {
    private static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    public native void pushBoolean(boolean z);

    public native void pushDouble(double d);

    public native void pushInt(int i);

    public native void pushNull();

    public native void pushString(String str);

    static {
        ah.a();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    public void pushArray(aq array) {
        boolean z = array == null || (array instanceof WritableNativeArray);
        a.a(z, "Illegal type provided");
        pushNativeArray((WritableNativeArray) array);
    }

    public void pushMap(ar map) {
        boolean z = map == null || (map instanceof WritableNativeMap);
        a.a(z, "Illegal type provided");
        pushNativeMap((WritableNativeMap) map);
    }
}
