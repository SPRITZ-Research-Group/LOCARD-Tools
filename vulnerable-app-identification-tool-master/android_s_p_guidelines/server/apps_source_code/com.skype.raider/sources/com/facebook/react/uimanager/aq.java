package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

final class aq {
    private static final Map<Class, Map<String, j>> a = new HashMap();
    private static final Map<String, j> b = new HashMap();

    static abstract class j {
        private static final Object[] e = new Object[2];
        private static final Object[] f = new Object[3];
        private static final Object[] g = new Object[1];
        private static final Object[] h = new Object[2];
        protected final String a;
        protected final String b;
        protected final Method c;
        @Nullable
        protected final Integer d;

        @Nullable
        protected abstract Object a(x xVar);

        /* synthetic */ j(ReactProp x0, String x1, Method x2, byte b) {
            this(x0, x1, x2);
        }

        /* synthetic */ j(ReactPropGroup x0, String x1, Method x2, int x3, byte b) {
            this(x0, x1, x2, x3);
        }

        private j(ReactProp prop, String defaultType, Method setter) {
            this.a = prop.name();
            if (!"__default_type__".equals(prop.customType())) {
                defaultType = prop.customType();
            }
            this.b = defaultType;
            this.c = setter;
            this.d = null;
        }

        private j(ReactPropGroup prop, String defaultType, Method setter, int index) {
            this.a = prop.names()[index];
            if (!"__default_type__".equals(prop.customType())) {
                defaultType = prop.customType();
            }
            this.b = defaultType;
            this.c = setter;
            this.d = Integer.valueOf(index);
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }

        public final void a(ViewManager viewManager, View viewToUpdate, x props) {
            try {
                if (this.d == null) {
                    e[0] = viewToUpdate;
                    e[1] = a(props);
                    this.c.invoke(viewManager, e);
                    Arrays.fill(e, null);
                    return;
                }
                f[0] = viewToUpdate;
                f[1] = this.d;
                f[2] = a(props);
                this.c.invoke(viewManager, f);
                Arrays.fill(f, null);
            } catch (Throwable t) {
                FLog.e(ViewManager.class, "Error while updating prop " + this.a, t);
                n nVar = new n("Error while updating property '" + this.a + "' of a view managed by: " + viewManager.getName(), t);
            }
        }

        public final void a(w nodeToUpdate, x props) {
            try {
                if (this.d == null) {
                    g[0] = a(props);
                    this.c.invoke(nodeToUpdate, g);
                    Arrays.fill(g, null);
                    return;
                }
                h[0] = this.d;
                h[1] = a(props);
                this.c.invoke(nodeToUpdate, h);
                Arrays.fill(h, null);
            } catch (Throwable t) {
                FLog.e(ViewManager.class, "Error while updating prop " + this.a, t);
                n nVar = new n("Error while updating property '" + this.a + "' in shadow node of type: " + nodeToUpdate.t(), t);
            }
        }
    }

    private static class a extends j {
        public a(ReactProp prop, Method setter) {
            super(prop, "Array", setter, (byte) 0);
        }

        @Nullable
        protected final Object a(x props) {
            return props.d(this.a);
        }
    }

    private static class b extends j {
        private final boolean e;

        public b(ReactProp prop, Method setter, boolean defaultValue) {
            super(prop, "boolean", setter, (byte) 0);
            this.e = defaultValue;
        }

        protected final Object a(x props) {
            return props.a(this.a, this.e) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private static class c extends j {
        public c(ReactProp prop, Method setter) {
            super(prop, "boolean", setter, (byte) 0);
        }

        @Nullable
        protected final Object a(x props) {
            if (props.b(this.a)) {
                return null;
            }
            return props.a(this.a, false) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private static class d extends j {
        public d(ReactProp prop, Method setter) {
            super(prop, "number", setter, (byte) 0);
        }

        public d(ReactPropGroup prop, Method setter, int index) {
            super(prop, "number", setter, index, (byte) 0);
        }

        @Nullable
        protected final Object a(x props) {
            if (props.b(this.a)) {
                return null;
            }
            return Integer.valueOf(props.a(this.a, 0));
        }
    }

    private static class e extends j {
        private final double e;

        public e(ReactProp prop, Method setter, double defaultValue) {
            super(prop, "number", setter, (byte) 0);
            this.e = defaultValue;
        }

        public e(ReactPropGroup prop, Method setter, int index, double defaultValue) {
            super(prop, "number", setter, index, (byte) 0);
            this.e = defaultValue;
        }

        protected final Object a(x props) {
            return Double.valueOf(props.a(this.a, this.e));
        }
    }

    private static class f extends j {
        public f(ReactProp prop, Method setter) {
            super(prop, "mixed", setter, (byte) 0);
        }

        public f(ReactPropGroup prop, Method setter, int index) {
            super(prop, "mixed", setter, index, (byte) 0);
        }

        protected final Object a(x props) {
            return props.f(this.a);
        }
    }

    private static class g extends j {
        private final float e;

        public g(ReactProp prop, Method setter, float defaultValue) {
            super(prop, "number", setter, (byte) 0);
            this.e = defaultValue;
        }

        public g(ReactPropGroup prop, Method setter, int index, float defaultValue) {
            super(prop, "number", setter, index, (byte) 0);
            this.e = defaultValue;
        }

        protected final Object a(x props) {
            return Float.valueOf(props.a(this.a, this.e));
        }
    }

    private static class h extends j {
        private final int e;

        public h(ReactProp prop, Method setter, int defaultValue) {
            super(prop, "number", setter, (byte) 0);
            this.e = defaultValue;
        }

        public h(ReactPropGroup prop, Method setter, int index, int defaultValue) {
            super(prop, "number", setter, index, (byte) 0);
            this.e = defaultValue;
        }

        protected final Object a(x props) {
            return Integer.valueOf(props.a(this.a, this.e));
        }
    }

    private static class i extends j {
        public i(ReactProp prop, Method setter) {
            super(prop, "Map", setter, (byte) 0);
        }

        @Nullable
        protected final Object a(x props) {
            return props.e(this.a);
        }
    }

    private static class k extends j {
        public k(ReactProp prop, Method setter) {
            super(prop, "String", setter, (byte) 0);
        }

        @Nullable
        protected final Object a(x props) {
            return props.c(this.a);
        }
    }

    public static void a() {
        a.clear();
        b.clear();
    }

    static Map<String, j> a(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return b;
        }
        Map<String, j> props = (Map) a.get(cls);
        if (props != null) {
            return props;
        }
        props = new HashMap(a(cls.getSuperclass()));
        a(cls, props);
        a.put(cls, props);
        return props;
    }

    static Map<String, j> b(Class<? extends w> cls) {
        if (cls == w.class) {
            return b;
        }
        Map<String, j> props = (Map) a.get(cls);
        if (props != null) {
            return props;
        }
        props = new HashMap(b(cls.getSuperclass()));
        b(cls, props);
        a.put(cls, props);
        return props;
    }

    private static j a(ReactProp annotation, Method method, Class<?> propTypeClass) {
        if (propTypeClass == com.facebook.react.bridge.g.class) {
            return new f(annotation, method);
        }
        if (propTypeClass == Boolean.TYPE) {
            return new b(annotation, method, annotation.defaultBoolean());
        }
        if (propTypeClass == Integer.TYPE) {
            return new h(annotation, method, annotation.defaultInt());
        }
        if (propTypeClass == Float.TYPE) {
            return new g(annotation, method, annotation.defaultFloat());
        }
        if (propTypeClass == Double.TYPE) {
            return new e(annotation, method, annotation.defaultDouble());
        }
        if (propTypeClass == String.class) {
            return new k(annotation, method);
        }
        if (propTypeClass == Boolean.class) {
            return new c(annotation, method);
        }
        if (propTypeClass == Integer.class) {
            return new d(annotation, method);
        }
        if (propTypeClass == al.class) {
            return new a(annotation, method);
        }
        if (propTypeClass == am.class) {
            return new i(annotation, method);
        }
        throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }

    private static void a(ReactPropGroup annotation, Method method, Class<?> propTypeClass, Map<String, j> props) {
        String[] names = annotation.names();
        int i;
        if (propTypeClass == com.facebook.react.bridge.g.class) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new f(annotation, method, i));
            }
        } else if (propTypeClass == Integer.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new h(annotation, method, i, annotation.defaultInt()));
            }
        } else if (propTypeClass == Float.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new g(annotation, method, i, annotation.defaultFloat()));
            }
        } else if (propTypeClass == Double.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new e(annotation, method, i, annotation.defaultDouble()));
            }
        } else if (propTypeClass == Integer.class) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new d(annotation, method, i));
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
        }
    }

    private static void a(Class<? extends ViewManager> cls, Map<String, j> props) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Class<?>[] paramTypes;
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                } else if (View.class.isAssignableFrom(paramTypes[0])) {
                    props.put(annotation.name(), a(annotation, method, paramTypes[1]));
                } else {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                }
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 3) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(paramTypes[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes[1] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    a(groupAnnotation, method, paramTypes[2], props);
                }
            }
        }
    }

    private static void b(Class<? extends w> cls, Map<String, j> props) {
        for (Method method : cls.getDeclaredMethods()) {
            Class<?>[] paramTypes;
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 1) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
                props.put(annotation.name(), a(annotation, method, paramTypes[0]));
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes[0] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    a(groupAnnotation, method, paramTypes[1], props);
                }
            }
        }
    }
}
