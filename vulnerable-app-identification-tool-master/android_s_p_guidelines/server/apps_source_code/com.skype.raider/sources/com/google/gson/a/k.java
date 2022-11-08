package com.google.gson.a;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class k {
    public abstract <T> T a(Class<T> cls) throws Exception;

    public static k a() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            final Object unsafe = f.get(null);
            final Method allocateInstance = unsafeClass.getMethod("allocateInstance", new Class[]{Class.class});
            return new k() {
                public final <T> T a(Class<T> c) throws Exception {
                    k.b(c);
                    return allocateInstance.invoke(unsafe, new Object[]{c});
                }
            };
        } catch (Exception e) {
            final Method newInstance;
            try {
                Method getConstructorId = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                getConstructorId.setAccessible(true);
                final int constructorId = ((Integer) getConstructorId.invoke(null, new Object[]{Object.class})).intValue();
                newInstance = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                newInstance.setAccessible(true);
                return new k() {
                    public final <T> T a(Class<T> c) throws Exception {
                        k.b(c);
                        return newInstance.invoke(null, new Object[]{c, Integer.valueOf(constructorId)});
                    }
                };
            } catch (Exception e2) {
                try {
                    newInstance = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                    newInstance.setAccessible(true);
                    return new k() {
                        public final <T> T a(Class<T> c) throws Exception {
                            k.b(c);
                            return newInstance.invoke(null, new Object[]{c, Object.class});
                        }
                    };
                } catch (Exception e3) {
                    return new k() {
                        public final <T> T a(Class<T> c) {
                            throw new UnsupportedOperationException("Cannot allocate " + c);
                        }
                    };
                }
            }
        }
    }

    static /* synthetic */ void b(Class x0) {
        int modifiers = x0.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + x0.getName());
        } else if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + x0.getName());
        }
    }
}
