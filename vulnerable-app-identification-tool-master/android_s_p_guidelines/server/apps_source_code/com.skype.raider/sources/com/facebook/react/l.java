package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.aa;
import com.facebook.react.bridge.ab;
import com.facebook.react.bridge.ac;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.o;
import com.facebook.react.bridge.queue.f;
import com.facebook.react.bridge.x;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.c;
import com.facebook.react.devsupport.d;
import com.facebook.react.devsupport.e;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.aj;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

@ThreadSafe
public class l {
    private static final String a = l.class.getSimpleName();
    private final d A = new d(this) {
        final /* synthetic */ l a;

        {
            this.a = this$0;
        }
    };
    private final com.facebook.react.modules.core.b B = new com.facebook.react.modules.core.b(this) {
        final /* synthetic */ l a;

        {
            this.a = this$0;
        }

        public final void c() {
            this.a.h();
        }
    };
    private final List<ReactRootView> b = Collections.synchronizedList(new ArrayList());
    private volatile c c;
    @ThreadConfined("UI")
    @Nullable
    private a d;
    @Nullable
    private volatile Thread e;
    @Nullable
    private final o f;
    @Nullable
    private final String g;
    private final List<q> h;
    private final com.facebook.react.devsupport.a.a i;
    private final boolean j;
    @Nullable
    private final ac k;
    @Nullable
    private volatile ai l;
    private final Context m;
    @ThreadConfined("UI")
    @Nullable
    private com.facebook.react.modules.core.b n;
    @Nullable
    private Activity o;
    private final Collection<b> p = Collections.synchronizedSet(new HashSet());
    private volatile boolean q = false;
    private final aj r;
    private final f s;
    @Nullable
    private final aa t;
    private final d u;
    private final boolean v;
    private final boolean w;
    private final boolean x;
    private final boolean y;
    private final int z;

    private class a {
        final /* synthetic */ l a;
        private final com.facebook.react.bridge.JavaScriptExecutor.a b;
        private final o c;

        public a(l lVar, com.facebook.react.bridge.JavaScriptExecutor.a jsExecutorFactory, o jsBundleLoader) {
            this.a = lVar;
            this.b = (com.facebook.react.bridge.JavaScriptExecutor.a) com.facebook.infer.annotation.a.a((Object) jsExecutorFactory);
            this.c = (o) com.facebook.infer.annotation.a.a((Object) jsBundleLoader);
        }

        public final com.facebook.react.bridge.JavaScriptExecutor.a a() {
            return this.b;
        }

        public final o b() {
            return this.c;
        }
    }

    public interface b {
        void a(ai aiVar);
    }

    static /* synthetic */ void a(l x0, ag x1) {
        int i = 0;
        ReactMarker.logMarker(com.facebook.react.bridge.aj.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(com.facebook.react.bridge.aj.SETUP_REACT_CONTEXT_START);
        com.facebook.systrace.a.a("setupReactContext");
        if (!x0.x) {
            ap.b();
        }
        com.facebook.infer.annotation.a.a(x0.l == null);
        x0.l = (ai) com.facebook.infer.annotation.a.a((Object) x1);
        CatalystInstance catalystInstance = (CatalystInstance) com.facebook.infer.annotation.a.a(x1.a());
        catalystInstance.initialize();
        x0.s.a((x) catalystInstance);
        x0.k();
        ReactMarker.logMarker(com.facebook.react.bridge.aj.ATTACH_MEASURED_ROOT_VIEWS_START);
        synchronized (x0.b) {
            for (ReactRootView a : x0.b) {
                x0.a(a, catalystInstance);
            }
        }
        ReactMarker.logMarker(com.facebook.react.bridge.aj.ATTACH_MEASURED_ROOT_VIEWS_END);
        b[] bVarArr = (b[]) x0.p.toArray(new b[x0.p.size()]);
        int length = bVarArr.length;
        while (i < length) {
            bVarArr[i].a(x1);
            i++;
        }
        com.facebook.systrace.a.a();
        ReactMarker.logMarker(com.facebook.react.bridge.aj.SETUP_REACT_CONTEXT_END);
        x0.l.d(new Runnable(x0) {
            final /* synthetic */ l a;

            {
                this.a = this$0;
            }

            public final void run() {
                Process.setThreadPriority(0);
            }
        });
        x0.l.c(new Runnable(x0) {
            final /* synthetic */ l a;

            {
                this.a = this$0;
            }

            public final void run() {
                Process.setThreadPriority(0);
            }
        });
        if (x0.y) {
            x0.l.b(new Runnable(x0) {
                final /* synthetic */ l a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    Process.setThreadPriority(0);
                }
            });
        }
    }

    public static m a() {
        return new m();
    }

    l(Context applicationContext, @Nullable Activity currentActivity, @Nullable com.facebook.react.modules.core.b defaultHardwareBackBtnHandler, @Nullable o bundleLoader, @Nullable String jsMainModuleName, List<q> packages, boolean useDeveloperSupport, @Nullable ac bridgeIdleDebugListener, c initialLifecycleState, aj uiImplementationProvider, aa nativeModuleCallExceptionHandler, d jscConfig, @Nullable e redBoxHandler, boolean lazyNativeModulesEnabled, boolean lazyViewManagersEnabled, boolean setupReactContextInBackgroundEnabled, boolean useSeparateUIBackgroundThread, int minNumShakes) {
        SoLoader.b(applicationContext);
        com.facebook.react.uimanager.b.a(applicationContext);
        this.m = applicationContext;
        this.o = currentActivity;
        this.n = defaultHardwareBackBtnHandler;
        this.f = bundleLoader;
        this.g = jsMainModuleName;
        this.h = packages;
        this.j = useDeveloperSupport;
        this.i = com.facebook.react.devsupport.a.a(applicationContext, this.A, this.g, useDeveloperSupport, redBoxHandler, minNumShakes);
        this.k = bridgeIdleDebugListener;
        this.c = initialLifecycleState;
        this.r = uiImplementationProvider;
        this.s = new f(applicationContext);
        this.t = nativeModuleCallExceptionHandler;
        this.u = jscConfig;
        this.v = lazyNativeModulesEnabled;
        this.w = lazyViewManagersEnabled;
        this.x = setupReactContextInBackgroundEnabled;
        this.y = useSeparateUIBackgroundThread;
        this.z = minNumShakes;
        com.facebook.react.modules.core.e.a();
    }

    public final com.facebook.react.devsupport.a.a b() {
        return this.i;
    }

    public final void c() {
        com.facebook.infer.annotation.a.a(!this.q, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.q = true;
        ap.b();
        if (!this.j || this.g == null) {
            com.facebook.react.bridge.JavaScriptExecutor.a aVar = new com.facebook.react.bridge.JSCJavaScriptExecutor.a(this.u.a());
            o oVar = this.f;
            ap.b();
            a aVar2 = new a(this, aVar, oVar);
            if (this.e == null) {
                a(aVar2);
            } else {
                this.d = aVar2;
            }
        } else if (this.f != null) {
            AnonymousClass3 anonymousClass3 = new Object(this) {
                final /* synthetic */ com.facebook.react.modules.debug.a.a a = null;
                final /* synthetic */ l b;

                {
                    this.b = this$0;
                }
            };
        }
    }

    public final boolean d() {
        return this.q;
    }

    public final void e() {
        ap.b();
        Object reactContext = this.l;
        if (this.l == null) {
            FLog.w("React", "Instance detached from instance manager");
            h();
            return;
        }
        ((DeviceEventManagerModule) ((ai) com.facebook.infer.annotation.a.a(reactContext)).b(DeviceEventManagerModule.class)).emitHardwareBackPressed();
    }

    private void h() {
        ap.b();
        if (this.n != null) {
            this.n.c();
        }
    }

    @ThreadConfined("UI")
    public final void a(Intent intent) {
        ap.b();
        if (this.l == null) {
            FLog.w("React", "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri uri = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && uri != null) {
            ((DeviceEventManagerModule) ((ai) com.facebook.infer.annotation.a.a(this.l)).b(DeviceEventManagerModule.class)).emitNewIntentReceived(uri);
        }
        this.l.a(this.o, intent);
    }

    @ThreadConfined("UI")
    public final void a(Activity activity) {
        com.facebook.infer.annotation.a.a(this.o);
        com.facebook.infer.annotation.a.a(activity == this.o, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.o.getClass().getSimpleName() + " Paused activity: " + activity.getClass().getSimpleName());
        ap.b();
        this.n = null;
        i();
    }

    @ThreadConfined("UI")
    public final void a(Activity activity, com.facebook.react.modules.core.b defaultBackButtonImpl) {
        ap.b();
        this.n = defaultBackButtonImpl;
        this.o = activity;
        a(false);
    }

    @ThreadConfined("UI")
    public final void b(Activity activity) {
        if (activity == this.o) {
            ap.b();
            j();
            this.o = null;
        }
    }

    @ThreadConfined("UI")
    public final void f() {
        ap.b();
        j();
        if (this.e != null) {
            this.e.interrupt();
            this.e = null;
        }
        this.s.a(this.m);
        if (this.l != null) {
            this.l.e();
            this.l = null;
            this.q = false;
        }
        this.o = null;
        com.facebook.react.views.a.c.a().b();
    }

    private synchronized void a(boolean force) {
        if (this.l != null && (force || this.c == c.BEFORE_RESUME || this.c == c.BEFORE_CREATE)) {
            this.l.a(this.o);
        }
        this.c = c.RESUMED;
    }

    private synchronized void i() {
        if (this.l != null) {
            if (this.c == c.BEFORE_CREATE) {
                this.l.a(this.o);
                this.l.c();
            } else if (this.c == c.RESUMED) {
                this.l.c();
            }
        }
        this.c = c.BEFORE_RESUME;
    }

    private synchronized void j() {
        if (this.l != null) {
            if (this.c == c.RESUMED) {
                this.l.c();
                this.c = c.BEFORE_RESUME;
            }
            if (this.c == c.BEFORE_RESUME) {
                this.l.d();
            }
        }
        this.c = c.BEFORE_CREATE;
    }

    private synchronized void k() {
        if (this.c == c.RESUMED) {
            a(true);
        }
    }

    @ThreadConfined("UI")
    public final void a(Activity activity, int requestCode, int resultCode, Intent data) {
        if (this.l != null) {
            this.l.a(activity, requestCode, resultCode, data);
        }
    }

    @ThreadConfined("UI")
    public final void a(ReactRootView rootView) {
        ap.b();
        this.b.add(rootView);
        rootView.removeAllViews();
        rootView.setId(-1);
        if (this.e == null && this.l != null) {
            a(rootView, this.l.a());
        }
    }

    @ThreadConfined("UI")
    public final void b(ReactRootView rootView) {
        ap.b();
        if (this.b.remove(rootView) && this.l != null && this.l.b()) {
            CatalystInstance a = this.l.a();
            ap.b();
            ((AppRegistry) a.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(rootView.getId());
        }
    }

    public final List<ViewManager> a(ag catalystApplicationContext) {
        ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_VIEW_MANAGERS_START);
        com.facebook.systrace.a.a("createAllViewManagers");
        try {
            List<ViewManager> allViewManagers = new ArrayList();
            for (q reactPackage : this.h) {
                allViewManagers.addAll(reactPackage.b(catalystApplicationContext));
            }
            return allViewManagers;
        } finally {
            com.facebook.systrace.a.a();
            ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_VIEW_MANAGERS_END);
        }
    }

    public final void a(b listener) {
        this.p.add(listener);
    }

    public final void b(b listener) {
        this.p.remove(listener);
    }

    @VisibleForTesting
    @Nullable
    public final ai g() {
        return this.l;
    }

    @ThreadConfined("UI")
    private void a(final a initParams) {
        ap.b();
        if (this.l != null) {
            ai aiVar = this.l;
            ap.b();
            if (this.c == c.RESUMED) {
                aiVar.c();
            }
            synchronized (this.b) {
                for (ReactRootView reactRootView : this.b) {
                    reactRootView.removeAllViews();
                    reactRootView.setId(-1);
                }
            }
            aiVar.e();
            this.s.b(aiVar.a());
            this.l = null;
        }
        this.e = new Thread(new Runnable(this) {
            final /* synthetic */ l b;

            public final void run() {
                try {
                    Process.setThreadPriority(-4);
                    final ag reactApplicationContext = this.b.a(initParams.a().a(), initParams.b());
                    if (this.b.x) {
                        this.b.e = null;
                    }
                    ReactMarker.logMarker(com.facebook.react.bridge.aj.PRE_SETUP_REACT_CONTEXT_START);
                    final Runnable maybeRecreateReactContextRunnable = new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            if (this.a.b.d != null) {
                                this.a.b.a(this.a.b.d);
                                this.a.b.d = null;
                            }
                        }
                    };
                    Runnable setupReactContextRunnable = new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 c;

                        public final void run() {
                            if (!this.c.b.x) {
                                this.c.b.e = null;
                            }
                            try {
                                l.a(this.c.b, reactApplicationContext);
                                if (!this.c.b.x) {
                                    maybeRecreateReactContextRunnable.run();
                                }
                            } catch (Exception e) {
                                this.c.b.i.a(e);
                            }
                        }
                    };
                    if (this.b.x) {
                        reactApplicationContext.c(setupReactContextRunnable);
                        ap.a(maybeRecreateReactContextRunnable);
                        return;
                    }
                    ap.a(setupReactContextRunnable);
                } catch (Exception e) {
                    this.b.i.a(e);
                }
            }
        });
        this.e.start();
    }

    private void a(final ReactRootView rootView, CatalystInstance catalystInstance) {
        com.facebook.systrace.a.a("attachRootViewToInstance");
        if (!this.x) {
            ap.b();
        }
        final int rootTag = ((UIManagerModule) catalystInstance.getNativeModule(UIManagerModule.class)).addRootView(rootView);
        rootView.setRootViewTag(rootTag);
        rootView.b();
        ap.a(new Runnable(this) {
            final /* synthetic */ l c;

            public final void run() {
            }
        });
        com.facebook.systrace.a.a();
    }

    private ag a(JavaScriptExecutor jsExecutor, o jsBundleLoader) {
        FLog.i("React", "Creating react context.");
        ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_REACT_CONTEXT_START);
        ag reactContext = new ag(this.m);
        g nativeModuleRegistryBuilder = new g(reactContext, this, this.v);
        com.facebook.react.bridge.u.a jsModulesBuilder = new com.facebook.react.bridge.u.a();
        if (this.j) {
            reactContext.a(this.i);
        }
        ReactMarker.logMarker(com.facebook.react.bridge.aj.PROCESS_PACKAGES_START);
        com.facebook.systrace.a.a("createAndProcessCoreModulesPackage");
        try {
            a(new a(this, this.B, this.r, this.w), nativeModuleRegistryBuilder, jsModulesBuilder);
            com.facebook.systrace.a.a();
            for (q reactPackage : this.h) {
                com.facebook.systrace.a.a("createAndProcessCustomReactPackage");
                try {
                    a(reactPackage, nativeModuleRegistryBuilder, jsModulesBuilder);
                    com.facebook.systrace.a.a();
                } catch (Throwable th) {
                    com.facebook.systrace.a.a();
                    throw th;
                }
            }
            ReactMarker.logMarker(com.facebook.react.bridge.aj.PROCESS_PACKAGES_END);
            ReactMarker.logMarker(com.facebook.react.bridge.aj.BUILD_NATIVE_MODULE_REGISTRY_START);
            com.facebook.systrace.a.a("buildNativeModuleRegistry");
            try {
                f a;
                ab nativeModuleRegistry = nativeModuleRegistryBuilder.a();
                com.facebook.systrace.a.a();
                ReactMarker.logMarker(com.facebook.react.bridge.aj.BUILD_NATIVE_MODULE_REGISTRY_END);
                aa exceptionHandler = this.t != null ? this.t : this.i;
                com.facebook.react.bridge.CatalystInstanceImpl.b bVar = new com.facebook.react.bridge.CatalystInstanceImpl.b();
                com.facebook.react.bridge.queue.b c;
                if (this.y) {
                    if (VERSION.SDK_INT < 21) {
                        c = com.facebook.react.bridge.queue.b.c("native_modules");
                    } else {
                        c = com.facebook.react.bridge.queue.b.b("native_modules");
                    }
                    a = new com.facebook.react.bridge.queue.f.a().c(com.facebook.react.bridge.queue.b.b("js")).b(c).a(com.facebook.react.bridge.queue.b.a("ui_background")).a();
                } else {
                    if (VERSION.SDK_INT < 21) {
                        c = com.facebook.react.bridge.queue.b.c("native_modules");
                    } else {
                        c = com.facebook.react.bridge.queue.b.b("native_modules");
                    }
                    a = new com.facebook.react.bridge.queue.f.a().c(com.facebook.react.bridge.queue.b.b("js")).b(c).a();
                }
                com.facebook.react.bridge.CatalystInstanceImpl.b catalystInstanceBuilder = bVar.a(a).a(jsExecutor).a(nativeModuleRegistry).a(jsModulesBuilder.a()).a(jsBundleLoader).a(exceptionHandler);
                ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_CATALYST_INSTANCE_START);
                com.facebook.systrace.a.a("createCatalystInstance");
                try {
                    CatalystInstance catalystInstance = catalystInstanceBuilder.a();
                    com.facebook.systrace.a.a();
                    ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_CATALYST_INSTANCE_END);
                    if (this.k != null) {
                        catalystInstance.addBridgeIdleDebugListener(this.k);
                    }
                    reactContext.a(catalystInstance);
                    ReactMarker.logMarker(com.facebook.react.bridge.aj.PRE_RUN_JS_BUNDLE_START);
                    catalystInstance.runJSBundle();
                    return reactContext;
                } catch (Throwable th2) {
                    com.facebook.systrace.a.a();
                    ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_CATALYST_INSTANCE_END);
                    throw th2;
                }
            } catch (Throwable th22) {
                com.facebook.systrace.a.a();
                ReactMarker.logMarker(com.facebook.react.bridge.aj.BUILD_NATIVE_MODULE_REGISTRY_END);
                throw th22;
            }
        } catch (Throwable th222) {
            com.facebook.systrace.a.a();
            throw th222;
        }
    }

    private static void a(q reactPackage, g nativeModuleRegistryBuilder, com.facebook.react.bridge.u.a jsModulesBuilder) {
        com.facebook.systrace.b.a();
        reactPackage.getClass().getSimpleName();
        if (reactPackage instanceof r) {
            ((r) reactPackage).c();
        }
        nativeModuleRegistryBuilder.a(reactPackage);
        for (Class<? extends JavaScriptModule> jsModuleClass : reactPackage.a()) {
            jsModulesBuilder.a(jsModuleClass);
        }
        if (reactPackage instanceof r) {
            ((r) reactPackage).d();
        }
        com.facebook.systrace.b.b();
    }
}
