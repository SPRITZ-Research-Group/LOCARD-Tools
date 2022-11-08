package com.facebook.react;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ab;
import com.facebook.react.bridge.ad;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aj;
import com.facebook.react.bridge.y;
import com.facebook.react.module.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class g {
    private final ag a;
    private final l b;
    private final boolean c;
    private final Map<Class<? extends NativeModule>, ModuleHolder> d = new HashMap();
    private final Map<String, Class<? extends NativeModule>> e = new HashMap();

    public g(ag reactApplicationContext, l reactInstanceManager, boolean lazyNativeModulesEnabled) {
        this.a = reactApplicationContext;
        this.b = reactInstanceManager;
        this.c = lazyNativeModulesEnabled;
    }

    public final void a(q reactPackage) {
        if (!this.c) {
            FLog.d("React", reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
            List<NativeModule> nativeModules;
            if (reactPackage instanceof n) {
                nativeModules = ((n) reactPackage).b();
            } else {
                nativeModules = reactPackage.a(this.a);
            }
            for (NativeModule nativeModule : nativeModules) {
                String name = nativeModule.getName();
                Class cls = nativeModule.getClass();
                if (this.e.containsKey(name)) {
                    Class cls2 = (Class) this.e.get(name);
                    if (nativeModule.canOverrideExistingModule()) {
                        this.d.remove(cls2);
                    } else {
                        throw new IllegalStateException("Native module " + cls.getSimpleName() + " tried to override " + cls2.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
                    }
                }
                this.e.put(name, cls);
                this.d.put(cls, new ModuleHolder(nativeModule));
            }
        } else if (reactPackage instanceof e) {
            e lazyReactPackage = (e) reactPackage;
            List<y> moduleSpecs = lazyReactPackage.c(this.a);
            Map<Class, a> reactModuleInfoMap = lazyReactPackage.b().a();
            Iterator it = moduleSpecs.iterator();
            while (it.hasNext()) {
                ModuleHolder moduleHolder;
                y moduleSpec = (y) it.next();
                Class<? extends NativeModule> type = moduleSpec.a();
                a reactModuleInfo = (a) reactModuleInfoMap.get(type);
                if (reactModuleInfo != null) {
                    moduleHolder = new ModuleHolder(reactModuleInfo, moduleSpec.b());
                } else if (BaseJavaModule.class.isAssignableFrom(type)) {
                    throw new IllegalStateException("Native Java module " + type.getSimpleName() + " should be annotated with @ReactModule and added to a @ReactModuleList.");
                } else {
                    ReactMarker.logMarker(aj.CREATE_MODULE_START, moduleSpec.a().getName());
                    try {
                        NativeModule module = (NativeModule) moduleSpec.b().a();
                        moduleHolder = new ModuleHolder(module);
                    } finally {
                        ReactMarker.logMarker(aj.CREATE_MODULE_END);
                    }
                }
                String name2 = moduleHolder.getName();
                if (this.e.containsKey(name2)) {
                    Class<? extends NativeModule> existingNativeModule = (Class) this.e.get(name2);
                    if (moduleHolder.getCanOverrideExistingModule()) {
                        this.d.remove(existingNativeModule);
                    } else {
                        throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + existingNativeModule.getSimpleName() + " for module name " + name2 + ". If this was your intention, set canOverrideExistingModule=true");
                    }
                }
                this.e.put(name2, type);
                this.d.put(type, moduleHolder);
            }
        } else {
            throw new IllegalStateException("Lazy native modules requires all ReactPackage to inherit from LazyReactPackage");
        }
    }

    public final ab a() {
        ArrayList<ModuleHolder> batchCompleteListenerModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.d.entrySet()) {
            if (ad.class.isAssignableFrom((Class) entry.getKey())) {
                batchCompleteListenerModules.add(entry.getValue());
            }
        }
        return new ab(this.a, this.d, batchCompleteListenerModules);
    }
}
