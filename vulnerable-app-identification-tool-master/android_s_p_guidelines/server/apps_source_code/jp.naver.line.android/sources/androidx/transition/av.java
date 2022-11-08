package androidx.transition;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Method;

final class av {
    private static Method a;
    private static boolean b;

    static void a(ViewGroup viewGroup, boolean z) {
        if (!b) {
            try {
                Method declaredMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", new Class[]{Boolean.TYPE});
                a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", e);
            }
            b = true;
        }
        if (a != null) {
            try {
                a.invoke(viewGroup, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", e2);
            } catch (Throwable e22) {
                Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", e22);
            }
        }
    }
}
