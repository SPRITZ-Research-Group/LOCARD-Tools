package com.facebook.react.bridge;

import javax.annotation.Nullable;

public final class af implements ae {
    @Nullable
    private d a;
    @Nullable
    private d b;

    public af(@Nullable d resolve, @Nullable d reject) {
        this.a = resolve;
        this.b = reject;
    }

    public final void a(Object value) {
        if (this.a != null) {
            this.a.invoke(value);
        }
    }

    public final void a(String code, String message) {
        a(code, message, null);
    }

    @Deprecated
    public final void a(String message) {
        a("EUNSPECIFIED", message, null);
    }

    public final void a(String code, Throwable e) {
        a(code, e.getMessage(), e);
    }

    public final void a(Throwable e) {
        a("EUNSPECIFIED", e.getMessage(), e);
    }

    public final void a(String code, String message, @Nullable Throwable e) {
        if (this.b != null) {
            if (code == null) {
                code = "EUNSPECIFIED";
            }
            WritableNativeMap errorInfo = new WritableNativeMap();
            errorInfo.putString("code", code);
            errorInfo.putString("message", message);
            this.b.invoke(errorInfo);
        }
    }
}
