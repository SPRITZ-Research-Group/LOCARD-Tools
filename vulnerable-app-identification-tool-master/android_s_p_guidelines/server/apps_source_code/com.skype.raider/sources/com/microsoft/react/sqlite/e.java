package com.microsoft.react.sqlite;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.microsoft.b.a.b;
import com.microsoft.b.a.d;
import java.util.Collections;
import java.util.List;

public final class e implements q {
    private b a;

    private e(b connectionProvider) {
        this.a = connectionProvider;
    }

    public e() {
        this(new d());
    }

    public final List<NativeModule> a(ag reactContext) {
        return Collections.singletonList(new SQLiteStorageModule(reactContext, this.a));
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
