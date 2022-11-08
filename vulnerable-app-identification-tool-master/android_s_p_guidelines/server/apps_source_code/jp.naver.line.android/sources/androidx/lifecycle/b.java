package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class b {
    static b a = new b();
    private final Map<Class, c> b = new HashMap();
    private final Map<Class, Boolean> c = new HashMap();

    b() {
    }

    final boolean a(Class cls) {
        if (this.c.containsKey(cls)) {
            return ((Boolean) this.c.get(cls)).booleanValue();
        }
        Method[] c = c(cls);
        for (Method annotation : c) {
            if (((ad) annotation.getAnnotation(ad.class)) != null) {
                a(cls, c);
                return true;
            }
        }
        this.c.put(cls, Boolean.FALSE);
        return false;
    }

    private static Method[] c(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (Throwable e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    final c b(Class cls) {
        c cVar = (c) this.b.get(cls);
        if (cVar != null) {
            return cVar;
        }
        return a(cls, null);
    }

    private static void a(Map<d, j> map, d dVar, j jVar, Class cls) {
        j jVar2 = (j) map.get(dVar);
        if (jVar2 != null && jVar != jVar2) {
            Method method = dVar.b;
            StringBuilder stringBuilder = new StringBuilder("Method ");
            stringBuilder.append(method.getName());
            stringBuilder.append(" in ");
            stringBuilder.append(cls.getName());
            stringBuilder.append(" already declared with different @OnLifecycleEvent value: previous value ");
            stringBuilder.append(jVar2);
            stringBuilder.append(", new value ");
            stringBuilder.append(jVar);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (jVar2 == null) {
            map.put(dVar, jVar);
        }
    }

    private c a(Class cls, Method[] methodArr) {
        Class superclass = cls.getSuperclass();
        Map hashMap = new HashMap();
        if (superclass != null) {
            c b = b(superclass);
            if (b != null) {
                hashMap.putAll(b.b);
            }
        }
        for (Class b2 : cls.getInterfaces()) {
            for (Entry entry : b(b2).b.entrySet()) {
                a(hashMap, (d) entry.getKey(), (j) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            ad adVar = (ad) method.getAnnotation(ad.class);
            if (adVar != null) {
                int i;
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(o.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                j a = adVar.a();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(j.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (a == j.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(hashMap, new d(i, method), a, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        c cVar = new c(hashMap);
        this.b.put(cls, cVar);
        this.c.put(cls, Boolean.valueOf(z));
        return cVar;
    }
}
