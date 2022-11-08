package com.facebook.react.bridge;

import android.content.res.AssetManager;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.f;
import com.facebook.react.common.annotations.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@DoNotStrip
public class CatalystInstanceImpl implements CatalystInstance {
    private static final AtomicInteger sNextInstanceIdForTrace = new AtomicInteger(1);
    private volatile boolean mAcceptCalls;
    private final CopyOnWriteArrayList<ac> mBridgeIdleListeners;
    private volatile boolean mDestroyed;
    private final HybridData mHybridData;
    private boolean mInitialized;
    private boolean mJSBundleHasLoaded;
    private final o mJSBundleLoader;
    private final ArrayList<e> mJSCallsPendingInit;
    private final Object mJSCallsPendingInitLock;
    private final u mJSModuleRegistry;
    private final ab mJavaRegistry;
    private final String mJsPendingCallsTitleForTrace;
    private final aa mNativeModuleCallExceptionHandler;
    private final MessageQueueThread mNativeModulesQueueThread;
    private final AtomicInteger mPendingJSCalls;
    private final com.facebook.react.bridge.queue.e mReactQueueConfiguration;
    @Nullable
    private String mSourceURL;
    private final com.facebook.systrace.c mTraceListener;
    @Nullable
    private final MessageQueueThread mUIBackgroundQueueThread;

    private static class a implements ReactCallback {
        private final WeakReference<CatalystInstanceImpl> a;

        public a(CatalystInstanceImpl outer) {
            this.a = new WeakReference(outer);
        }

        public final void onBatchComplete() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.a.get();
            if (impl != null) {
                impl.mJavaRegistry.d();
            }
        }

        public final void incrementPendingJSCalls() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.a.get();
            if (impl != null) {
                impl.incrementPendingJSCalls();
            }
        }

        public final void decrementPendingJSCalls() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.a.get();
            if (impl != null) {
                impl.decrementPendingJSCalls();
            }
        }
    }

    public static class b {
        @Nullable
        private f a;
        @Nullable
        private o b;
        @Nullable
        private ab c;
        @Nullable
        private u d;
        @Nullable
        private JavaScriptExecutor e;
        @Nullable
        private aa f;

        public final b a(f ReactQueueConfigurationSpec) {
            this.a = ReactQueueConfigurationSpec;
            return this;
        }

        public final b a(ab registry) {
            this.c = registry;
            return this;
        }

        public final b a(u jsModuleRegistry) {
            this.d = jsModuleRegistry;
            return this;
        }

        public final b a(o jsBundleLoader) {
            this.b = jsBundleLoader;
            return this;
        }

        public final b a(JavaScriptExecutor jsExecutor) {
            this.e = jsExecutor;
            return this;
        }

        public final b a(aa handler) {
            this.f = handler;
            return this;
        }

        public final CatalystInstanceImpl a() {
            return new CatalystInstanceImpl((f) com.facebook.infer.annotation.a.a(this.a), (JavaScriptExecutor) com.facebook.infer.annotation.a.a(this.e), (ab) com.facebook.infer.annotation.a.a(this.c), (u) com.facebook.infer.annotation.a.a(this.d), (o) com.facebook.infer.annotation.a.a(this.b), (aa) com.facebook.infer.annotation.a.a(this.f), null);
        }
    }

    private static class c implements com.facebook.systrace.c {
        private final WeakReference<CatalystInstanceImpl> a;

        public c(CatalystInstanceImpl outer) {
            this.a = new WeakReference(outer);
        }
    }

    private class d implements com.facebook.react.bridge.queue.c {
        final /* synthetic */ CatalystInstanceImpl a;

        private d(CatalystInstanceImpl catalystInstanceImpl) {
            this.a = catalystInstanceImpl;
        }

        /* synthetic */ d(CatalystInstanceImpl x0, byte b) {
            this(x0);
        }

        public final void a(Exception e) {
            this.a.onNativeException(e);
        }
    }

    private static class e {
        public String a;
        public String b;
        public NativeArray c;

        public e(String module, String method, NativeArray arguments) {
            this.a = module;
            this.b = method;
            this.c = arguments;
        }
    }

    private native void handleMemoryPressureCritical();

    private native void handleMemoryPressureModerate();

    private native void handleMemoryPressureUiHidden();

    private static native HybridData initHybrid();

    private native void initializeBridge(ReactCallback reactCallback, JavaScriptExecutor javaScriptExecutor, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, MessageQueueThread messageQueueThread3, Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniCallJSCallback(int i, NativeArray nativeArray);

    private native void jniCallJSFunction(String str, String str2, NativeArray nativeArray);

    private native void jniLoadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    private native void jniLoadScriptFromFile(String str, String str2, boolean z);

    private native void jniReactConfigurationData(WritableNativeMap writableNativeMap);

    private native void jniSetSourceURL(String str);

    public native long getJavaScriptContext();

    public native void setGlobalVariable(String str, String str2);

    public native void startProfiler(String str);

    public native void stopProfiler(String str, String str2);

    public native boolean supportsProfiling();

    /* synthetic */ CatalystInstanceImpl(f x0, JavaScriptExecutor x1, ab x2, u x3, o x4, aa x5, AnonymousClass1 x6) {
        this(x0, x1, x2, x3, x4, x5);
    }

    static {
        ah.a();
    }

    private CatalystInstanceImpl(f reactQueueConfigurationSpec, JavaScriptExecutor jsExecutor, ab registry, u jsModuleRegistry, o jsBundleLoader, aa nativeModuleCallExceptionHandler) {
        this.mPendingJSCalls = new AtomicInteger(0);
        this.mJsPendingCallsTitleForTrace = "pending_js_calls_instance" + sNextInstanceIdForTrace.getAndIncrement();
        this.mDestroyed = false;
        this.mJSCallsPendingInit = new ArrayList();
        this.mJSCallsPendingInitLock = new Object();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        FLog.d("React", "Initializing React Xplat Bridge.");
        this.mHybridData = initHybrid();
        this.mReactQueueConfiguration = com.facebook.react.bridge.queue.e.a(reactQueueConfigurationSpec, new d());
        this.mBridgeIdleListeners = new CopyOnWriteArrayList();
        this.mJavaRegistry = registry;
        this.mJSModuleRegistry = jsModuleRegistry;
        this.mJSBundleLoader = jsBundleLoader;
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        this.mNativeModulesQueueThread = this.mReactQueueConfiguration.c();
        this.mUIBackgroundQueueThread = this.mReactQueueConfiguration.b();
        this.mTraceListener = new c(this);
        FLog.d("React", "Initializing React Xplat Bridge before initializeBridge");
        initializeBridge(new a(this), jsExecutor, this.mReactQueueConfiguration.d(), this.mNativeModulesQueueThread, this.mUIBackgroundQueueThread, this.mJavaRegistry.a((p) this), this.mJavaRegistry.a());
        FLog.d("React", "Initializing React Xplat Bridge after initializeBridge");
    }

    public void setReactConfigurationData(WritableNativeMap reactConfigurationData) {
        jniReactConfigurationData(reactConfigurationData);
    }

    void setSourceURLs(String deviceURL, String remoteURL) {
        this.mSourceURL = deviceURL;
        jniSetSourceURL(remoteURL);
    }

    void loadScriptFromAssets(AssetManager assetManager, String assetURL, boolean loadSynchronously) {
        this.mSourceURL = assetURL;
        jniLoadScriptFromAssets(assetManager, assetURL, loadSynchronously);
    }

    void loadScriptFromFile(String fileName, String sourceURL, boolean loadSynchronously) {
        this.mSourceURL = sourceURL;
        jniLoadScriptFromFile(fileName, sourceURL, loadSynchronously);
    }

    public void runJSBundle() {
        boolean z = true;
        if (this.mJSBundleHasLoaded) {
            z = false;
        }
        com.facebook.infer.annotation.a.a(z, "JS bundle was already loaded!");
        this.mJSBundleLoader.a(this);
        synchronized (this.mJSCallsPendingInitLock) {
            this.mAcceptCalls = true;
            Iterator it = this.mJSCallsPendingInit.iterator();
            while (it.hasNext()) {
                e call = (e) it.next();
                jniCallJSFunction(call.a, call.b, call.c);
            }
            this.mJSCallsPendingInit.clear();
            this.mJSBundleHasLoaded = true;
        }
    }

    public boolean hasRunJSBundle() {
        boolean z;
        synchronized (this.mJSCallsPendingInitLock) {
            z = this.mJSBundleHasLoaded && this.mAcceptCalls;
        }
        return z;
    }

    @Nullable
    public String getSourceURL() {
        return this.mSourceURL;
    }

    public void callFunction(String module, String method, NativeArray arguments) {
        if (this.mDestroyed) {
            FLog.w("React", "Calling JS function after bridge has been destroyed: " + (module + "." + method + "(" + arguments.toString() + ")"));
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                if (!this.mAcceptCalls) {
                    this.mJSCallsPendingInit.add(new e(module, method, arguments));
                    return;
                }
            }
        }
        jniCallJSFunction(module, method, arguments);
    }

    public void invokeCallback(int callbackID, NativeArray arguments) {
        if (this.mDestroyed) {
            FLog.w("React", "Invoking JS callback after bridge has been destroyed.");
        } else {
            jniCallJSCallback(callbackID, arguments);
        }
    }

    public void destroy() {
        ap.b();
        if (!this.mDestroyed) {
            this.mDestroyed = true;
            this.mNativeModulesQueueThread.runOnQueue(new Runnable(this) {
                final /* synthetic */ CatalystInstanceImpl a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    int i = 0;
                    this.a.mJavaRegistry.b();
                    if (this.a.mPendingJSCalls.getAndSet(0) == 0) {
                        i = 1;
                    }
                    if (i == 0 && !this.a.mBridgeIdleListeners.isEmpty()) {
                        Iterator it = this.a.mBridgeIdleListeners.iterator();
                        while (it.hasNext()) {
                            ((ac) it.next()).a();
                        }
                    }
                    ap.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            this.a.a.mHybridData.resetNative();
                            this.a.a.getReactQueueConfiguration().e();
                        }
                    });
                }
            });
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    @VisibleForTesting
    public void initialize() {
        com.facebook.infer.annotation.a.a(!this.mInitialized, "This catalyst instance has already been initialized");
        com.facebook.infer.annotation.a.a(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mNativeModulesQueueThread.runOnQueue(new Runnable(this) {
            final /* synthetic */ CatalystInstanceImpl a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.mJavaRegistry.c();
            }
        });
    }

    public com.facebook.react.bridge.queue.d getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
        return this.mJSModuleRegistry.a(this, jsInterface);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        return this.mJavaRegistry.a((Class) nativeModuleInterface);
    }

    public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        return this.mJavaRegistry.b(nativeModuleInterface);
    }

    public Collection<NativeModule> getNativeModules() {
        return this.mJavaRegistry.e();
    }

    public void handleMemoryPressure(w level) {
        if (!this.mDestroyed) {
            switch (level) {
                case UI_HIDDEN:
                    handleMemoryPressureUiHidden();
                    return;
                case MODERATE:
                    handleMemoryPressureModerate();
                    return;
                case CRITICAL:
                    handleMemoryPressureCritical();
                    return;
                default:
                    return;
            }
        }
    }

    public void addBridgeIdleDebugListener(ac listener) {
        this.mBridgeIdleListeners.add(listener);
    }

    public void removeBridgeIdleDebugListener(ac listener) {
        this.mBridgeIdleListeners.remove(listener);
    }

    private void incrementPendingJSCalls() {
        if ((this.mPendingJSCalls.getAndIncrement() == 0 ? 1 : null) != null && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable(this) {
                final /* synthetic */ CatalystInstanceImpl a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    Iterator it = this.a.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((ac) it.next()).b();
                    }
                }
            });
        }
    }

    private void decrementPendingJSCalls() {
        if ((this.mPendingJSCalls.decrementAndGet() == 0 ? 1 : null) != null && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable(this) {
                final /* synthetic */ CatalystInstanceImpl a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    Iterator it = this.a.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((ac) it.next()).a();
                    }
                }
            });
        }
    }

    private void onNativeException(Exception e) {
        this.mNativeModuleCallExceptionHandler.a(e);
        this.mReactQueueConfiguration.a().runOnQueue(new Runnable(this) {
            final /* synthetic */ CatalystInstanceImpl a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.destroy();
            }
        });
    }
}
