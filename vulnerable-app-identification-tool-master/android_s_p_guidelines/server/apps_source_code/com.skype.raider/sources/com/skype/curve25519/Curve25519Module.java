package com.skype.curve25519;

import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ag;

public class Curve25519Module extends ReactContextBaseJavaModule {
    public Curve25519Module(ag reactContext) {
        super(reactContext);
    }

    public static CxxModuleWrapper create(ag reactContext) {
        return CxxModuleWrapper.makeDso("curve25519", "createCurve25519");
    }

    public String getName() {
        return "Curve25519Android";
    }
}
