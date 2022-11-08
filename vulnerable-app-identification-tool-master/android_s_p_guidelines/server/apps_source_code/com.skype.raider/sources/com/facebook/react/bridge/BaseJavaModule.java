package com.facebook.react.bridge;

import java.util.Map;
import javax.annotation.Nullable;

public abstract class BaseJavaModule implements NativeModule {
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";

    @Nullable
    public Map<String, Object> getConstants() {
        return null;
    }

    public void initialize() {
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public void onCatalystInstanceDestroy() {
    }

    public boolean hasConstants() {
        return false;
    }
}
