package com.microsoft.react.push;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.l;
import com.facebook.react.l.b;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;

public final class h implements q {
    public final void a(l instanceManager) {
        instanceManager.a(new b(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void a(ai context) {
                e.a();
            }
        });
    }

    public final List<NativeModule> a(ag reactContext) {
        return Collections.singletonList(e.a(reactContext));
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
