package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class CxxModuleWrapperBase implements NativeModule {
    @DoNotStrip
    private HybridData mHybridData;

    public native String getName();

    static {
        ah.a();
    }

    public void initialize() {
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public void onCatalystInstanceDestroy() {
        this.mHybridData.resetNative();
    }

    protected CxxModuleWrapperBase(HybridData hd) {
        this.mHybridData = hd;
    }

    protected void resetModule(HybridData hd) {
        if (hd != this.mHybridData) {
            this.mHybridData.resetNative();
            this.mHybridData = hd;
        }
    }
}
