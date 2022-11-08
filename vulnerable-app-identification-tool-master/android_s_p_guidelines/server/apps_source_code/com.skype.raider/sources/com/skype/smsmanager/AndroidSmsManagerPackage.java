package com.skype.smsmanager;

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

public final class AndroidSmsManagerPackage implements q {
    private l a;
    private b b;

    public final void a(l instanceManager) {
        this.a = instanceManager;
        this.b = new b(this) {
            final /* synthetic */ AndroidSmsManagerPackage a;

            {
                this.a = this$0;
            }

            public final void a(ai context) {
                SmsHandlingContext.a();
            }
        };
        instanceManager.a(this.b);
    }

    public final void b() {
        SmsHandlingContext.b();
        this.a.b(this.b);
    }

    public final List<NativeModule> a(ag reactContext) {
        return Collections.singletonList(SmsHandlingContext.a(reactContext));
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
