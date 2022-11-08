package com.facebook.react.modules.debug;

import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.m;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.debug.a.a;
import java.util.Locale;
import javax.annotation.Nullable;

@ReactModule(name = "AnimationsDebugModule")
public class AnimationsDebugModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "AnimationsDebugModule";
    @Nullable
    private final a mCatalystSettings;
    @Nullable
    private b mFrameCallback;

    public AnimationsDebugModule(ag reactContext, a catalystSettings) {
        super(reactContext);
        this.mCatalystSettings = catalystSettings;
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void startRecordingFps() {
        if (this.mCatalystSettings != null && this.mCatalystSettings.a()) {
            if (this.mFrameCallback != null) {
                throw new m("Already recording FPS!");
            }
            this.mFrameCallback = new b(com.facebook.react.modules.core.a.a(), getReactApplicationContext());
            this.mFrameCallback.d();
        }
    }

    @ReactMethod
    public void stopRecordingFps(double animationStopTimeMs) {
        if (this.mFrameCallback != null) {
            this.mFrameCallback.e();
            if (this.mFrameCallback.a((long) animationStopTimeMs) == null) {
                Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
            } else {
                String fpsString = String.format(Locale.US, "FPS: %.2f, %d frames (%d expected)", new Object[]{Double.valueOf(this.mFrameCallback.a((long) animationStopTimeMs).e), Integer.valueOf(this.mFrameCallback.a((long) animationStopTimeMs).a), Integer.valueOf(this.mFrameCallback.a((long) animationStopTimeMs).c)});
                String debugString = fpsString + "\n" + String.format(Locale.US, "JS FPS: %.2f, %d frames (%d expected)", new Object[]{Double.valueOf(fpsInfo.f), Integer.valueOf(fpsInfo.b), Integer.valueOf(fpsInfo.c)}) + "\nTotal Time MS: " + String.format(Locale.US, "%d", new Object[]{Integer.valueOf(fpsInfo.g)});
                FLog.d("React", debugString);
                Toast.makeText(getReactApplicationContext(), debugString, 1).show();
            }
            this.mFrameCallback = null;
        }
    }

    public void onCatalystInstanceDestroy() {
        if (this.mFrameCallback != null) {
            this.mFrameCallback.e();
            this.mFrameCallback = null;
        }
    }
}
