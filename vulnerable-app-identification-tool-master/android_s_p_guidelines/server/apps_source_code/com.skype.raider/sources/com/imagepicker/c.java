package com.imagepicker;

import android.support.annotation.NonNull;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;

public final class c {
    private ar a = new WritableNativeMap();

    public final void a(@NonNull String key, @NonNull String value) {
        this.a.putString(key, value);
    }

    public final void a(@NonNull String key, int value) {
        this.a.putInt(key, value);
    }

    public final void a(@NonNull String key, boolean value) {
        this.a.putBoolean(key, value);
    }

    public final void a(@NonNull String key, double value) {
        this.a.putDouble(key, value);
    }

    public final void a(@NonNull d callback, @NonNull String action) {
        a();
        this.a.putString("customButton", action);
        b(callback);
    }

    public final void a(@NonNull d callback) {
        a();
        this.a.putBoolean("didCancel", true);
        b(callback);
    }

    public final void b(@NonNull d callback, @NonNull String error) {
        a();
        this.a.putString("error", error);
        b(callback);
    }

    public final void b(@NonNull d callback) {
        callback.invoke(this.a);
    }

    public final void a() {
        this.a = new WritableNativeMap();
    }
}
