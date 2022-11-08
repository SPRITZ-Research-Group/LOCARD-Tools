package com.skype.nativeentropy;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aq;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class AndroidNativeEntropyModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "NativeEntropy";
    public static final String TAG = "AndroidNativeEntropyModule";
    private final int SEED_LENGTH;
    private SecureRandom secureRandom;

    public AndroidNativeEntropyModule(ag reactContext) {
        super(reactContext);
        this.SEED_LENGTH = 48;
        this.secureRandom = null;
        this.secureRandom = new SecureRandom();
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("EntropySeed", getEntropySeed());
        return constants;
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getRandomValues(int len, ae promise) {
        byte[] bytes = new byte[len];
        this.secureRandom.nextBytes(bytes);
        Object rval = new WritableNativeArray();
        for (int i = 0; i < len; i++) {
            rval.pushInt(bytes[i] & 255);
        }
        promise.a(rval);
    }

    @ReactMethod
    public void getKeys(int keySize, int algoName, ae promise) {
        promise.a(new IllegalArgumentException("Android is not supported"));
    }

    private aq getEntropySeed() {
        aq rval = new WritableNativeArray();
        byte[] bytes = this.secureRandom.generateSeed(48);
        for (int i = 0; i < 48; i++) {
            rval.pushInt(bytes[i] & 255);
        }
        return rval;
    }
}
