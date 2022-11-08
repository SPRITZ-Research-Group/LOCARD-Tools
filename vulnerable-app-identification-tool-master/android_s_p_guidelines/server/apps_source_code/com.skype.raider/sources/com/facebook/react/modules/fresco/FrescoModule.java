package com.facebook.react.modules.fresco;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.h;
import com.facebook.imagepipeline.core.h.a;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.b;
import com.facebook.react.modules.network.e;
import java.util.HashSet;
import java.util.Set;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

@ReactModule(name = "FrescoModule")
public class FrescoModule extends ReactContextBaseJavaModule implements v {
    private static boolean sHasBeenInitialized = false;
    private final boolean mClearOnDestroy;
    @Nullable
    private h mConfig;

    public FrescoModule(ag reactContext) {
        this(reactContext, true, null);
    }

    public FrescoModule(ag reactContext, boolean clearOnDestroy) {
        this(reactContext, clearOnDestroy, null);
    }

    public FrescoModule(ag reactContext, boolean clearOnDestroy, @Nullable h config) {
        super(reactContext);
        this.mClearOnDestroy = clearOnDestroy;
        this.mConfig = config;
    }

    public void initialize() {
        super.initialize();
        getReactApplicationContext().a((v) this);
        if (!hasBeenInitialized()) {
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig(getReactApplicationContext());
            }
            Fresco.a(getReactApplicationContext().getApplicationContext(), this.mConfig);
            sHasBeenInitialized = true;
        } else if (this.mConfig != null) {
            FLog.w("React", "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }

    public String getName() {
        return "FrescoModule";
    }

    public void clearSensitiveData() {
        Fresco.b().b();
    }

    public static boolean hasBeenInitialized() {
        return sHasBeenInitialized;
    }

    private static h getDefaultConfig(ai context) {
        return getDefaultConfigBuilder(context).a();
    }

    public static a getDefaultConfigBuilder(ai context) {
        Set requestListeners = new HashSet();
        requestListeners.add(new c());
        OkHttpClient client = e.b();
        ((com.facebook.react.modules.network.a) client.cookieJar()).a(new JavaNetCookieJar(new b(context)));
        return h.a(context.getApplicationContext()).a(new com.facebook.imagepipeline.b.a.a(client)).a(new b(client)).a(false).a(requestListeners);
    }

    public void onHostResume() {
    }

    public void onHostPause() {
    }

    public void onHostDestroy() {
        if (this.mClearOnDestroy) {
            Fresco.b().a();
        }
    }
}
