package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

public final class ao {
    private static final Map<Class<?>, e<?, ?>> a = new HashMap();
    private static final Map<Class<?>, d<?>> b = new HashMap();

    public interface c {
        void getProperties(Map<String, String> map);
    }

    public interface e<T extends ViewManager, V extends View> extends c {
        void setProperty(T t, V v, String str, x xVar);
    }

    public interface d<T extends w> extends c {
        void setProperty(T t, String str, x xVar);
    }

    private static class a<T extends w> implements d<T> {
        private final Map<String, j> a;

        /* synthetic */ a(Class x0, byte b) {
            this(x0);
        }

        private a(Class<? extends w> shadowNodeClass) {
            this.a = aq.b(shadowNodeClass);
        }

        public final void setProperty(w node, String name, x props) {
            j setter = (j) this.a.get(name);
            if (setter != null) {
                setter.a(node, props);
            }
        }

        public final void getProperties(Map<String, String> props) {
            for (j setter : this.a.values()) {
                props.put(setter.a(), setter.b());
            }
        }
    }

    private static class b<T extends ViewManager, V extends View> implements e<T, V> {
        private final Map<String, j> a;

        /* synthetic */ b(Class x0, byte b) {
            this(x0);
        }

        private b(Class<? extends ViewManager> viewManagerClass) {
            this.a = aq.a(viewManagerClass);
        }

        public final void setProperty(T manager, V v, String name, x props) {
            j setter = (j) this.a.get(name);
            if (setter != null) {
                setter.a(manager, v, props);
            }
        }

        public final void getProperties(Map<String, String> props) {
            for (j setter : this.a.values()) {
                props.put(setter.a(), setter.b());
            }
        }
    }

    public static void a() {
        aq.a();
        a.clear();
        b.clear();
    }

    public static <T extends ViewManager, V extends View> void a(T manager, V v, x props) {
        e<T, V> setter = a(manager.getClass());
        ReadableMapKeySetIterator iterator = props.a.keySetIterator();
        while (iterator.hasNextKey()) {
            setter.setProperty(manager, v, iterator.nextKey(), props);
        }
    }

    public static <T extends w> void a(T node, x props) {
        d<T> setter = b(node.getClass());
        ReadableMapKeySetIterator iterator = props.a.keySetIterator();
        while (iterator.hasNextKey()) {
            setter.setProperty(node, iterator.nextKey(), props);
        }
    }

    public static Map<String, String> a(Class<? extends ViewManager> viewManagerTopClass, Class<? extends w> shadowNodeTopClass) {
        Map<String, String> props = new HashMap();
        a(viewManagerTopClass).getProperties(props);
        b(shadowNodeTopClass).getProperties(props);
        return props;
    }

    private static <T extends ViewManager, V extends View> e<T, V> a(Class<? extends ViewManager> managerClass) {
        e<T, V> setter = (e) a.get(managerClass);
        if (setter == null) {
            setter = (e) c(managerClass);
            if (setter == null) {
                setter = new b(managerClass, (byte) 0);
            }
            a.put(managerClass, setter);
        }
        return setter;
    }

    private static <T extends w> d<T> b(Class<? extends w> nodeClass) {
        d<T> setter = (d) b.get(nodeClass);
        if (setter == null) {
            setter = (d) c(nodeClass);
            if (setter == null) {
                setter = new a(nodeClass, (byte) 0);
            }
            b.put(nodeClass, setter);
        }
        return setter;
    }

    private static <T> T c(Class<?> cls) {
        ReflectiveOperationException e;
        String clsName = cls.getName();
        try {
            return Class.forName(clsName + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException e2) {
            FLog.w("ViewManagerPropertyUpdater", "Could not find generated setter for " + cls);
            return null;
        } catch (InstantiationException e3) {
            e = e3;
            throw new RuntimeException("Unable to instantiate methods getter for " + clsName, e);
        } catch (IllegalAccessException e4) {
            e = e4;
            throw new RuntimeException("Unable to instantiate methods getter for " + clsName, e);
        }
    }
}
