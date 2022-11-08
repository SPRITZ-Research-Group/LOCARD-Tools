package androidx.core.widget;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class e {
    private static Field a;
    private static boolean b;

    public static Drawable a(CompoundButton compoundButton) {
        if (VERSION.SDK_INT >= 23) {
            return compoundButton.getButtonDrawable();
        }
        if (!b) {
            try {
                Field declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable e) {
                Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", e);
            }
            b = true;
        }
        if (a != null) {
            try {
                return (Drawable) a.get(compoundButton);
            } catch (Throwable e2) {
                Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", e2);
                a = null;
            }
        }
        return null;
    }
}
