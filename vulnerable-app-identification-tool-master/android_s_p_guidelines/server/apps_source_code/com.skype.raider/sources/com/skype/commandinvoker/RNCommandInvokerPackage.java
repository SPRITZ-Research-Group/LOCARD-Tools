package com.skype.commandinvoker;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RNCommandInvokerPackage implements q {
    private static RNCommandInvokerModule a;
    private static final List<CommandProxyExecutor> b = new ArrayList();
    private static final a c = a.a("RNCommandInvokerPackage", b.DEFAULT);

    public interface CommandProxyExecutor {
        void a(RNCommandInvokerModule rNCommandInvokerModule);
    }

    public final List<NativeModule> a(ag reactContext) {
        final RNCommandInvokerModule module = new RNCommandInvokerModule(reactContext);
        c.b(new Runnable(this) {
            final /* synthetic */ RNCommandInvokerPackage b;

            public final void run() {
                RNCommandInvokerPackage.a = module;
                RNCommandInvokerPackage.c.b(new Runnable() {
                    public final void run() {
                        for (CommandProxyExecutor a : RNCommandInvokerPackage.b) {
                            a.a(RNCommandInvokerPackage.a);
                        }
                        RNCommandInvokerPackage.b.clear();
                    }
                });
            }
        });
        return Collections.singletonList(module);
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }

    public static void a(final String causeId, final CommandProxyExecutor executor) {
        FLog.i("RNCommandInvokerPackage", "executeWithCommandProxy begin causeId: %s", (Object) causeId);
        c.b(new Runnable() {
            public final void run() {
                if (RNCommandInvokerPackage.a != null) {
                    FLog.i("RNCommandInvokerPackage", "executeWithCommandProxy executing causeId: %s", causeId);
                    executor.a(RNCommandInvokerPackage.a);
                    return;
                }
                FLog.i("RNCommandInvokerPackage", "executeWithCommandProxy enqueuing causeId: %s", causeId);
                RNCommandInvokerPackage.b.add(executor);
            }
        });
    }
}
