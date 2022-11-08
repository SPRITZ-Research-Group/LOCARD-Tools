package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class JSCJavaScriptExecutor extends JavaScriptExecutor {

    public static class a implements com.facebook.react.bridge.JavaScriptExecutor.a {
        private ReadableNativeArray a;

        public a(WritableNativeMap jscConfig) {
            WritableNativeArray array = new WritableNativeArray();
            array.pushMap(jscConfig);
            this.a = array;
        }

        public final JavaScriptExecutor a() throws Exception {
            return new JSCJavaScriptExecutor(this.a);
        }
    }

    private static native HybridData initHybrid(ReadableNativeArray readableNativeArray);

    static {
        ah.a();
    }

    public JSCJavaScriptExecutor(ReadableNativeArray jscConfig) {
        super(initHybrid(jscConfig));
    }
}
