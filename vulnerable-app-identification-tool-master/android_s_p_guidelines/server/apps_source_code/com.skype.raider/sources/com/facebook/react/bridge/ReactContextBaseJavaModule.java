package com.facebook.react.bridge;

import android.app.Activity;
import javax.annotation.Nullable;

public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    private final ag mReactApplicationContext;

    public ReactContextBaseJavaModule(ag reactContext) {
        this.mReactApplicationContext = reactContext;
    }

    protected final ag getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    @Nullable
    protected final Activity getCurrentActivity() {
        return this.mReactApplicationContext.j();
    }
}
