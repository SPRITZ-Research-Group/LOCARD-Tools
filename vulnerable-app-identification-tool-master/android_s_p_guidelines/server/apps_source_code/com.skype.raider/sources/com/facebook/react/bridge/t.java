package com.facebook.react.bridge;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class t {
    private final Class<? extends JavaScriptModule> a;
    @Nullable
    private String b;

    public t(Class<? extends JavaScriptModule> moduleInterface) {
        this.a = moduleInterface;
    }

    public final Class<? extends JavaScriptModule> a() {
        return this.a;
    }

    public final String b() {
        if (this.b == null) {
            String name = this.a.getSimpleName();
            int dollarSignIndex = name.lastIndexOf(36);
            if (dollarSignIndex != -1) {
                name = name.substring(dollarSignIndex + 1);
            }
            this.b = name;
        }
        return this.b;
    }
}
