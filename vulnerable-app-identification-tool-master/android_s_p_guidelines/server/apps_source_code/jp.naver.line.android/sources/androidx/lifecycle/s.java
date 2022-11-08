package androidx.lifecycle;

import com.google.obf.ly;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class s {
    private static Map<Class, Integer> a = new HashMap();
    private static Map<Class, List<Constructor<? extends g>>> b = new HashMap();

    static h a(Object obj) {
        if (obj instanceof f) {
            return new FullLifecycleObserverAdapter((f) obj);
        }
        if (obj instanceof h) {
            return (h) obj;
        }
        Class cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List list = (List) b.get(cls);
        int i = 0;
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a((Constructor) list.get(0), obj));
        }
        g[] gVarArr = new g[list.size()];
        while (i < list.size()) {
            gVarArr[i] = a((Constructor) list.get(i), obj);
            i++;
        }
        return new CompositeGeneratedAdaptersObserver(gVarArr);
    }

    private static g a(Constructor<? extends g> constructor, Object obj) {
        try {
            return (g) constructor.newInstance(new Object[]{obj});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable e22) {
            throw new RuntimeException(e22);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static Constructor<? extends g> a(Class<?> cls) {
        try {
            Package packageR = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = packageR != null ? packageR.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(canonicalName.replace(ly.a, "_"));
            stringBuilder.append("_LifecycleAdapter");
            canonicalName = stringBuilder.toString();
            if (!name.isEmpty()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(name);
                stringBuilder.append(ly.a);
                stringBuilder.append(canonicalName);
                canonicalName = stringBuilder.toString();
            }
            Constructor<? extends g> declaredConstructor = Class.forName(canonicalName).getDeclaredConstructor(new Class[]{cls});
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static int b(Class<?> cls) {
        if (a.containsKey(cls)) {
            return ((Integer) a.get(cls)).intValue();
        }
        int c = c(cls);
        a.put(cls, Integer.valueOf(c));
        return c;
    }

    private static int c(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor a = a((Class) cls);
        if (a != null) {
            b.put(cls, Collections.singletonList(a));
            return 2;
        } else if (b.a.a(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            Object obj = null;
            if (d(superclass)) {
                if (b(superclass) == 1) {
                    return 1;
                }
                obj = new ArrayList((Collection) b.get(superclass));
            }
            for (Class cls2 : cls.getInterfaces()) {
                if (d(cls2)) {
                    if (b(cls2) == 1) {
                        return 1;
                    }
                    if (obj == null) {
                        obj = new ArrayList();
                    }
                    obj.addAll((Collection) b.get(cls2));
                }
            }
            if (obj == null) {
                return 1;
            }
            b.put(cls, obj);
            return 2;
        }
    }

    private static boolean d(Class<?> cls) {
        return cls != null && n.class.isAssignableFrom(cls);
    }
}
