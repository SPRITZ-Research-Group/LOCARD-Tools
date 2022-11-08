package com.facebook.react.uimanager;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.infer.annotation.a;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class b {
    @Nullable
    private static DisplayMetrics a;
    @Nullable
    private static DisplayMetrics b;

    public static void b(Context context) {
        ReflectiveOperationException e;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        a = displayMetrics;
        DisplayMetrics screenDisplayMetrics = new DisplayMetrics();
        screenDisplayMetrics.setTo(displayMetrics);
        Object wm = (WindowManager) context.getSystemService("window");
        a.a(wm, "WindowManager is null!");
        Display display = wm.getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            display.getRealMetrics(screenDisplayMetrics);
        } else {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                screenDisplayMetrics.widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                screenDisplayMetrics.heightPixels = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
            } catch (InvocationTargetException e2) {
                e = e2;
            } catch (IllegalAccessException e3) {
                e = e3;
            } catch (NoSuchMethodException e4) {
                e = e4;
            }
        }
        b = screenDisplayMetrics;
        return;
        throw new RuntimeException("Error getting real dimensions for API level < 17", e);
    }

    @Deprecated
    public static DisplayMetrics a() {
        return a;
    }

    public static DisplayMetrics b() {
        return b;
    }

    public static void a(Context context) {
        if (b == null) {
            b(context);
        }
    }
}
