package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

final class t {
    private static Field a;
    private static boolean b;
    private static Class c;
    private static boolean d;
    private static Field e;
    private static boolean f;
    private static Field g;
    private static boolean h;

    static void a(Resources resources) {
        if (VERSION.SDK_INT < 28) {
            Field declaredField;
            Object obj;
            if (VERSION.SDK_INT >= 24) {
                if (!h) {
                    try {
                        declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                        g = declaredField;
                        declaredField.setAccessible(true);
                    } catch (Throwable e) {
                        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e);
                    }
                    h = true;
                }
                if (g != null) {
                    try {
                        obj = g.get(resources);
                    } catch (Throwable e2) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e2);
                        obj = null;
                    }
                    if (obj != null) {
                        if (!b) {
                            try {
                                declaredField = obj.getClass().getDeclaredField("mDrawableCache");
                                a = declaredField;
                                declaredField.setAccessible(true);
                            } catch (Throwable e3) {
                                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e3);
                            }
                            b = true;
                        }
                        if (a != null) {
                            try {
                                obj = a.get(obj);
                            } catch (Throwable e22) {
                                Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e22);
                            }
                            if (obj != null) {
                                a(obj);
                            }
                        }
                        obj = null;
                        if (obj != null) {
                            a(obj);
                        }
                    }
                }
            } else if (VERSION.SDK_INT >= 23) {
                if (!b) {
                    try {
                        declaredField = Resources.class.getDeclaredField("mDrawableCache");
                        a = declaredField;
                        declaredField.setAccessible(true);
                    } catch (Throwable e32) {
                        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e32);
                    }
                    b = true;
                }
                if (a != null) {
                    try {
                        obj = a.get(resources);
                    } catch (Throwable e222) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e222);
                    }
                    if (obj != null) {
                        a(obj);
                    }
                }
                obj = null;
                if (obj != null) {
                    a(obj);
                }
            } else {
                if (VERSION.SDK_INT >= 21) {
                    if (!b) {
                        try {
                            declaredField = Resources.class.getDeclaredField("mDrawableCache");
                            a = declaredField;
                            declaredField.setAccessible(true);
                        } catch (Throwable e322) {
                            Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e322);
                        }
                        b = true;
                    }
                    if (a != null) {
                        Map map;
                        try {
                            map = (Map) a.get(resources);
                        } catch (Throwable e2222) {
                            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e2222);
                            map = null;
                        }
                        if (map != null) {
                            map.clear();
                        }
                    }
                }
            }
        }
    }

    private static void a(Object obj) {
        if (!d) {
            try {
                c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (Throwable e) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e);
            }
            d = true;
        }
        if (c != null) {
            if (!f) {
                try {
                    Field declaredField = c.getDeclaredField("mUnthemedEntries");
                    e = declaredField;
                    declaredField.setAccessible(true);
                } catch (Throwable e2) {
                    Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
                }
                f = true;
            }
            if (e != null) {
                LongSparseArray longSparseArray;
                try {
                    longSparseArray = (LongSparseArray) e.get(obj);
                } catch (Throwable e3) {
                    Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e3);
                    longSparseArray = null;
                }
                if (longSparseArray != null) {
                    longSparseArray.clear();
                }
            }
        }
    }
}
