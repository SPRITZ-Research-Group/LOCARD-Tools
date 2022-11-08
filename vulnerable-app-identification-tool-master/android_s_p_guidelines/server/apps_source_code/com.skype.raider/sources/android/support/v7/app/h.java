package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

final class h {
    private static Field a;
    private static boolean b;
    private static Class c;
    private static boolean d;
    private static Field e;
    private static boolean f;
    private static Field g;
    private static boolean h;

    static boolean a(@NonNull Resources resources) {
        if (VERSION.SDK_INT >= 24) {
            return d(resources);
        }
        if (VERSION.SDK_INT >= 23) {
            return c(resources);
        }
        if (VERSION.SDK_INT >= 21) {
            return b(resources);
        }
        return false;
    }

    @RequiresApi(21)
    private static boolean b(@NonNull Resources resources) {
        if (!b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            b = true;
        }
        if (a != null) {
            Map drawableCache = null;
            try {
                drawableCache = (Map) a.get(resources);
            } catch (IllegalAccessException e2) {
            }
            if (drawableCache != null) {
                drawableCache.clear();
                return true;
            }
        }
        return false;
    }

    @RequiresApi(23)
    private static boolean c(@NonNull Resources resources) {
        if (!b) {
            try {
                Field declaredField = Resources.class.getDeclaredField("mDrawableCache");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            b = true;
        }
        Object drawableCache = null;
        if (a != null) {
            try {
                drawableCache = a.get(resources);
            } catch (IllegalAccessException e2) {
            }
        }
        if (drawableCache == null || drawableCache == null || !a(drawableCache)) {
            return false;
        }
        return true;
    }

    @RequiresApi(24)
    private static boolean d(@NonNull Resources resources) {
        Field declaredField;
        if (!h) {
            try {
                declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                g = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            h = true;
        }
        if (g == null) {
            return false;
        }
        Object resourcesImpl = null;
        try {
            resourcesImpl = g.get(resources);
        } catch (IllegalAccessException e2) {
        }
        if (resourcesImpl == null) {
            return false;
        }
        if (!b) {
            try {
                declaredField = resourcesImpl.getClass().getDeclaredField("mDrawableCache");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
            }
            b = true;
        }
        Object drawableCache = null;
        if (a != null) {
            try {
                drawableCache = a.get(resourcesImpl);
            } catch (IllegalAccessException e4) {
            }
        }
        if (drawableCache == null || !a(drawableCache)) {
            return false;
        }
        return true;
    }

    @RequiresApi(16)
    private static boolean a(@NonNull Object cache) {
        if (!d) {
            try {
                c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
            }
            d = true;
        }
        if (c == null) {
            return false;
        }
        if (!f) {
            try {
                Field declaredField = c.getDeclaredField("mUnthemedEntries");
                e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
            }
            f = true;
        }
        if (e == null) {
            return false;
        }
        LongSparseArray unthemedEntries = null;
        try {
            unthemedEntries = (LongSparseArray) e.get(cache);
        } catch (IllegalAccessException e3) {
        }
        if (unthemedEntries == null) {
            return false;
        }
        unthemedEntries.clear();
        return true;
    }
}
