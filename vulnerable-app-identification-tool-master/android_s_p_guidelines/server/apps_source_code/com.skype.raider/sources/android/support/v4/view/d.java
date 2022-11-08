package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.Gravity;

public final class d {
    public static int a(int gravity, int layoutDirection) {
        if (VERSION.SDK_INT >= 17) {
            return Gravity.getAbsoluteGravity(gravity, layoutDirection);
        }
        return -8388609 & gravity;
    }
}
