package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({a.LIBRARY_GROUP})
public final class ax {
    private static Method a;

    static {
        if (VERSION.SDK_INT >= 18) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});
                a = declaredMethod;
                if (!declaredMethod.isAccessible()) {
                    a.setAccessible(true);
                }
            } catch (NoSuchMethodException e) {
            }
        }
    }

    public static boolean a(View view) {
        return ViewCompat.f(view) == 1;
    }

    public static void a(View view, Rect inoutInsets, Rect outLocalInsets) {
        if (a != null) {
            try {
                a.invoke(view, new Object[]{inoutInsets, outLocalInsets});
            } catch (Exception e) {
            }
        }
    }

    public static void b(View view) {
        if (VERSION.SDK_INT >= 16) {
            try {
                Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(view, new Object[0]);
            } catch (NoSuchMethodException e) {
            } catch (InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
            }
        }
    }
}
