package com.facebook.react.bridge;

public final class e implements d {
    private final p a;
    private final int b;
    private boolean c = false;

    public e(p jsInstance, int callbackId) {
        this.a = jsInstance;
        this.b = callbackId;
    }

    public final void invoke(Object... args) {
        if (this.c) {
            throw new RuntimeException("Illegal callback invocation from native module. This callback type only permits a single invocation from native code.");
        }
        this.a.invokeCallback(this.b, b.a(args));
        this.c = true;
    }
}
