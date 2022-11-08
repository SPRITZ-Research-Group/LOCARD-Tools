package com.facebook.react.bridge;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

public final class u {
    private final HashMap<Class<? extends JavaScriptModule>, JavaScriptModule> a = new HashMap();
    private final HashMap<Class<? extends JavaScriptModule>, t> b = new HashMap();

    public static class a {
        private List<t> a = new ArrayList();

        public final a a(Class<? extends JavaScriptModule> moduleInterfaceClass) {
            this.a.add(new t(moduleInterfaceClass));
            return this;
        }

        public final u a() {
            return new u(this.a);
        }
    }

    private static class b implements InvocationHandler {
        private final CatalystInstance a;
        private final t b;

        public b(CatalystInstance catalystInstance, t moduleRegistration) {
            this.a = catalystInstance;
            this.b = moduleRegistration;
        }

        @Nullable
        public final Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
            this.a.callFunction(this.b.b(), method.getName(), args != null ? b.a(args) : new WritableNativeArray());
            return null;
        }
    }

    public u(List<t> config) {
        for (t registration : config) {
            this.b.put(registration.a(), registration);
        }
    }

    public final synchronized <T extends JavaScriptModule> T a(CatalystInstance instance, Class<T> moduleInterface) {
        T module;
        module = (JavaScriptModule) this.a.get(moduleInterface);
        if (module == null) {
            t registration = (t) com.facebook.infer.annotation.a.a(this.b.get(moduleInterface), "JS module " + moduleInterface.getSimpleName() + " hasn't been registered!");
            T interfaceProxy = (JavaScriptModule) Proxy.newProxyInstance(moduleInterface.getClassLoader(), new Class[]{moduleInterface}, new b(instance, registration));
            this.a.put(moduleInterface, interfaceProxy);
            module = interfaceProxy;
        }
        return module;
    }
}
