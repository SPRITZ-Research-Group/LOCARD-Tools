package com.facebook.react.bridge;

import com.facebook.systrace.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class ab {
    private final ag a;
    private final Map<Class<? extends NativeModule>, ModuleHolder> b;
    private final ArrayList<ModuleHolder> c;

    public ab(ag reactApplicationContext, Map<Class<? extends NativeModule>, ModuleHolder> modules, ArrayList<ModuleHolder> batchCompleteListenerModules) {
        this.a = reactApplicationContext;
        this.b = modules;
        this.c = batchCompleteListenerModules;
    }

    final Collection<JavaModuleWrapper> a(p jsInstance) {
        ArrayList<JavaModuleWrapper> javaModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.b.entrySet()) {
            if (!CxxModuleWrapperBase.class.isAssignableFrom((Class) entry.getKey())) {
                javaModules.add(new JavaModuleWrapper(jsInstance, (ModuleHolder) entry.getValue()));
            }
        }
        return javaModules;
    }

    final Collection<ModuleHolder> a() {
        ArrayList<ModuleHolder> cxxModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.b.entrySet()) {
            if (CxxModuleWrapperBase.class.isAssignableFrom((Class) entry.getKey())) {
                cxxModules.add(entry.getValue());
            }
        }
        return cxxModules;
    }

    final void b() {
        this.a.g();
        a.a("NativeModuleRegistry_notifyJSInstanceDestroy");
        try {
            for (ModuleHolder destroy : this.b.values()) {
                destroy.destroy();
            }
        } finally {
            a.a();
        }
    }

    final void c() {
        this.a.a("From version React Native v0.44, native modules are explicitly not initialized on the UI thread. See https://github.com/facebook/react-native/wiki/Breaking-Changes#d4611211-reactnativeandroidbreaking-move-nativemodule-initialization-off-ui-thread---aaachiuuu  for more details.");
        ReactMarker.logMarker(aj.NATIVE_MODULE_INITIALIZE_START);
        a.a("NativeModuleRegistry_notifyJSInstanceInitialized");
        try {
            for (ModuleHolder markInitializable : this.b.values()) {
                markInitializable.markInitializable();
            }
        } finally {
            a.a();
            ReactMarker.logMarker(aj.NATIVE_MODULE_INITIALIZE_END);
        }
    }

    public final void d() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ModuleHolder moduleHolder = (ModuleHolder) it.next();
            if (moduleHolder.hasInstance()) {
                ((ad) moduleHolder.getModule()).onBatchComplete();
            }
        }
    }

    public final <T extends NativeModule> boolean a(Class<T> moduleInterface) {
        return this.b.containsKey(moduleInterface);
    }

    public final <T extends NativeModule> T b(Class<T> moduleInterface) {
        return ((ModuleHolder) com.facebook.infer.annotation.a.a(this.b.get(moduleInterface))).getModule();
    }

    public final List<NativeModule> e() {
        List<NativeModule> modules = new ArrayList();
        for (ModuleHolder module : this.b.values()) {
            modules.add(module.getModule());
        }
        return modules;
    }
}
