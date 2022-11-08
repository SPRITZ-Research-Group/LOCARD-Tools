package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class CxxModuleWrapper extends CxxModuleWrapperBase {
    private static native CxxModuleWrapper makeDsoNative(String str, String str2);

    protected CxxModuleWrapper(HybridData hd) {
        super(hd);
    }

    public static CxxModuleWrapper makeDso(String library, String factory) {
        SoLoader.a(library);
        return makeDsoNative(SoLoader.b(library).getAbsolutePath(), factory);
    }
}
