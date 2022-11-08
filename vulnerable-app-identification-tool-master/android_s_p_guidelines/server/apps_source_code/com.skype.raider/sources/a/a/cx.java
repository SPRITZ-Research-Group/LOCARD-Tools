package a.a;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class cx {
    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method a(String str, String str2, Class<?>... clsArr) {
        try {
            return a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object a(Object obj, Method method, Object... objArr) {
        Object obj2 = null;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            return obj2;
        } catch (InvocationTargetException e2) {
            return obj2;
        }
    }
}
