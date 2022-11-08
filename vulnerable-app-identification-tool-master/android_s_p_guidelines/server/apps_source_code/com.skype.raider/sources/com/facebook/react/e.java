package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aj;
import com.facebook.react.bridge.y;
import com.facebook.react.module.a.b;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class e implements q {
    public abstract b b();

    public abstract List<y> c(ag agVar);

    public static b a(e lazyReactPackage) {
        try {
            Class<?> reactModuleInfoProviderClass = Class.forName(lazyReactPackage.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (reactModuleInfoProviderClass == null) {
                throw new RuntimeException("ReactModuleInfoProvider class for " + lazyReactPackage.getClass().getCanonicalName() + " not found.");
            }
            try {
                return (b) reactModuleInfoProviderClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException(e3);
        }
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        for (y holder : c(reactContext)) {
            com.facebook.systrace.b.a();
            holder.a();
            ReactMarker.logMarker(aj.CREATE_MODULE_START, holder.a().getSimpleName());
            try {
                NativeModule nativeModule = (NativeModule) holder.b().a();
                ReactMarker.logMarker(aj.CREATE_MODULE_END);
                com.facebook.systrace.b.b();
                modules.add(nativeModule);
            } catch (Throwable th) {
                ReactMarker.logMarker(aj.CREATE_MODULE_END);
                com.facebook.systrace.b.b();
                throw th;
            }
        }
        return modules;
    }

    public List<ViewManager> b(ag reactContext) {
        List<y> viewManagerModuleSpecs = Collections.emptyList();
        if (viewManagerModuleSpecs == null || viewManagerModuleSpecs.isEmpty()) {
            return Collections.emptyList();
        }
        List<ViewManager> viewManagers = new ArrayList();
        for (y moduleSpec : viewManagerModuleSpecs) {
            viewManagers.add((ViewManager) moduleSpec.b().a());
        }
        return viewManagers;
    }
}
