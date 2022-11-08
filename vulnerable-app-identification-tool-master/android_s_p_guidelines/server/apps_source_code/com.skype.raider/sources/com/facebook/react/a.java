package com.facebook.react;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.Systrace;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.y;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.JSTimersExecution;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.debug.AnimationsDebugModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.aj;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class a extends e implements r {
    private final l a;
    private final b b;
    private final aj c;
    private final boolean d;

    a(l reactInstanceManager, b hardwareBackBtnHandler, aj uiImplementationProvider, boolean lazyViewManagersEnabled) {
        this.a = reactInstanceManager;
        this.b = hardwareBackBtnHandler;
        this.c = uiImplementationProvider;
        this.d = lazyViewManagersEnabled;
    }

    public final List<y> c(final ag reactContext) {
        List<y> moduleSpecList = new ArrayList();
        moduleSpecList.add(new y(AndroidInfoModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ Object a() {
                return new AndroidInfoModule();
            }
        }));
        moduleSpecList.add(new y(AnimationsDebugModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                ag agVar = reactContext;
                this.b.a.b();
                return new AnimationsDebugModule(agVar, null);
            }
        }));
        moduleSpecList.add(new y(DeviceEventManagerModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                return new DeviceEventManagerModule(reactContext, this.b.b);
            }
        }));
        moduleSpecList.add(new y(ExceptionsManagerModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ Object a() {
                return new ExceptionsManagerModule(this.a.a.b());
            }
        }));
        moduleSpecList.add(new y(HeadlessJsTaskSupportModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                return new HeadlessJsTaskSupportModule(reactContext);
            }
        }));
        moduleSpecList.add(new y(SourceCodeModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                return new SourceCodeModule(reactContext);
            }
        }));
        moduleSpecList.add(new y(Timing.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                return new Timing(reactContext, this.b.a.b());
            }
        }));
        moduleSpecList.add(new y(UIManagerModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* bridge */ /* synthetic */ Object a() {
                return this.b.d(reactContext);
            }
        }));
        moduleSpecList.add(new y(DeviceInfoModule.class, new javax.inject.a<NativeModule>(this) {
            final /* synthetic */ a b;

            public final /* synthetic */ Object a() {
                return new DeviceInfoModule(reactContext);
            }
        }));
        return moduleSpecList;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return new ArrayList(Arrays.asList(new Class[]{RCTDeviceEventEmitter.class, JSTimersExecution.class, RCTEventEmitter.class, RCTNativeAppEventEmitter.class, AppRegistry.class, Systrace.class, HMRClient.class}));
    }

    public final com.facebook.react.module.a.b b() {
        return e.a((e) this);
    }

    private UIManagerModule d(ag reactContext) {
        ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_UI_MANAGER_MODULE_START);
        com.facebook.systrace.a.a("createUIManagerModule");
        try {
            UIManagerModule uIManagerModule = new UIManagerModule(reactContext, this.a.a(reactContext), this.c, this.d);
            return uIManagerModule;
        } finally {
            com.facebook.systrace.a.a();
            ReactMarker.logMarker(com.facebook.react.bridge.aj.CREATE_UI_MANAGER_MODULE_END);
        }
    }

    public final void c() {
        ReactMarker.logMarker(com.facebook.react.bridge.aj.PROCESS_CORE_REACT_PACKAGE_START);
    }

    public final void d() {
        ReactMarker.logMarker(com.facebook.react.bridge.aj.PROCESS_CORE_REACT_PACKAGE_END);
    }
}
