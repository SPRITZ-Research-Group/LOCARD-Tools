package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

@Deprecated
public final class p {
    private static Method a;

    static {
        if (VERSION.SDK_INT == 25) {
            try {
                a = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
            } catch (Exception e) {
            }
        }
    }

    public static float a(@NonNull ViewConfiguration config, @NonNull Context context) {
        if (VERSION.SDK_INT >= 26) {
            return config.getScaledHorizontalScrollFactor();
        }
        return c(config, context);
    }

    public static float b(@NonNull ViewConfiguration config, @NonNull Context context) {
        if (VERSION.SDK_INT >= 26) {
            return config.getScaledVerticalScrollFactor();
        }
        return c(config, context);
    }

    private static float c(ViewConfiguration config, Context context) {
        if (VERSION.SDK_INT >= 25 && a != null) {
            try {
                return (float) ((Integer) a.invoke(config, new Object[0])).intValue();
            } catch (Exception e) {
            }
        }
        TypedValue outValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16842829, outValue, true)) {
            return outValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }
}
