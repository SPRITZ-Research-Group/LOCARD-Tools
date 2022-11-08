package com.microsoft.rnreload;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.List;

public final class RNReloadPackage implements q {
    private RNReloadNativeModule a;
    private Runnable b;

    public class RNReloadNativeModule extends ReactContextBaseJavaModule {
        @ReactMethod
        public void reload() {
            final Activity localActivity = getCurrentActivity();
            if (localActivity != null) {
                final Application application = localActivity.getApplication();
                localActivity.getApplication().registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks(this) {
                    final /* synthetic */ RNReloadNativeModule c;

                    public final void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    }

                    public final void onActivityStarted(Activity activity) {
                    }

                    public final void onActivityResumed(Activity activity) {
                    }

                    public final void onActivityPaused(Activity activity) {
                    }

                    public final void onActivityStopped(Activity activity) {
                    }

                    public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    public final void onActivityDestroyed(Activity activity) {
                        FLog.i("RNReloadPackage", "onActivityDestroyed");
                        if (activity == localActivity) {
                            if (RNReloadPackage.this.b != null) {
                                RNReloadPackage.this.b.run();
                            }
                            Intent intent = new Intent();
                            intent.setClass(activity, activity.getClass());
                            activity.startActivity(intent);
                        }
                        application.unregisterActivityLifecycleCallbacks(this);
                    }
                });
                FLog.i("RNReloadPackage", "App reload runnable posted to message queue: " + Boolean.toString(new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ RNReloadNativeModule b;

                    public final void run() {
                        localActivity.finish();
                    }
                })));
                return;
            }
            Toast.makeText(getReactApplicationContext().getApplicationContext(), "Can't reload - there is no active react activity", 1).show();
        }

        public RNReloadNativeModule(ag reactContext) {
            super(reactContext);
        }

        public String getName() {
            return "RNReload";
        }
    }

    public RNReloadPackage(Runnable destroyJsEngineRunnable) {
        this.b = destroyJsEngineRunnable;
    }

    public final List<NativeModule> a(ag reactApplicationContext) {
        List<NativeModule> nativeModules = new ArrayList();
        this.a = new RNReloadNativeModule(reactApplicationContext);
        nativeModules.add(this.a);
        return nativeModules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return new ArrayList();
    }

    public final List<ViewManager> b(ag reactApplicationContext) {
        return new ArrayList();
    }
}
