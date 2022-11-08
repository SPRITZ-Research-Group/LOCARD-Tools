package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class e {

    static class a {
        private static Method a;
        private static boolean b;
        private static Method c;
        private static boolean d;

        public static IBinder a(Bundle bundle, String key) {
            if (!b) {
                try {
                    Method method = Bundle.class.getMethod("getIBinder", new Class[]{String.class});
                    a = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e) {
                }
                b = true;
            }
            if (a != null) {
                try {
                    return (IBinder) a.invoke(bundle, new Object[]{key});
                } catch (InvocationTargetException e2) {
                } catch (IllegalAccessException e3) {
                } catch (IllegalArgumentException e4) {
                }
            }
            return null;
            a = null;
            return null;
        }

        public static void a(Bundle bundle, String key, IBinder binder) {
            if (!d) {
                try {
                    Method method = Bundle.class.getMethod("putIBinder", new Class[]{String.class, IBinder.class});
                    c = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e) {
                }
                d = true;
            }
            if (c != null) {
                try {
                    c.invoke(bundle, new Object[]{key, binder});
                    return;
                } catch (InvocationTargetException e2) {
                } catch (IllegalAccessException e3) {
                } catch (IllegalArgumentException e4) {
                }
            } else {
                return;
            }
            c = null;
        }
    }

    public static IBinder a(Bundle bundle, String key) {
        if (VERSION.SDK_INT >= 18) {
            return bundle.getBinder(key);
        }
        return a.a(bundle, key);
    }

    public static void a(Bundle bundle, String key, IBinder binder) {
        if (VERSION.SDK_INT >= 18) {
            bundle.putBinder(key, binder);
        } else {
            a.a(bundle, key, binder);
        }
    }
}
