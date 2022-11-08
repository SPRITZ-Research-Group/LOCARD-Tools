package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class YogaConfig {
    private YogaLogger mLogger;
    long mNativePointer = jni_YGConfigNew();

    private native void jni_YGConfigFree(long j);

    private native long jni_YGConfigNew();

    private native void jni_YGConfigSetExperimentalFeatureEnabled(long j, int i, boolean z);

    private native void jni_YGConfigSetLogger(long j, Object obj);

    private native void jni_YGConfigSetPointScaleFactor(long j, float f);

    private native void jni_YGConfigSetUseLegacyStretchBehaviour(long j, boolean z);

    private native void jni_YGConfigSetUseWebDefaults(long j, boolean z);

    static {
        SoLoader.a("yoga");
    }

    public YogaConfig() {
        if (this.mNativePointer == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    protected void finalize() throws Throwable {
        try {
            jni_YGConfigFree(this.mNativePointer);
        } finally {
            super.finalize();
        }
    }

    public void setExperimentalFeatureEnabled(YogaExperimentalFeature feature, boolean enabled) {
        jni_YGConfigSetExperimentalFeatureEnabled(this.mNativePointer, feature.intValue(), enabled);
    }

    public void setUseWebDefaults(boolean useWebDefaults) {
        jni_YGConfigSetUseWebDefaults(this.mNativePointer, useWebDefaults);
    }

    public void setPointScaleFactor(float pixelsInPoint) {
        jni_YGConfigSetPointScaleFactor(this.mNativePointer, pixelsInPoint);
    }

    public void setUseLegacyStretchBehaviour(boolean useLegacyStretchBehaviour) {
        jni_YGConfigSetUseLegacyStretchBehaviour(this.mNativePointer, useLegacyStretchBehaviour);
    }

    public void setLogger(YogaLogger logger) {
        this.mLogger = logger;
        jni_YGConfigSetLogger(this.mNativePointer, logger);
    }

    public YogaLogger getLogger() {
        return this.mLogger;
    }
}
