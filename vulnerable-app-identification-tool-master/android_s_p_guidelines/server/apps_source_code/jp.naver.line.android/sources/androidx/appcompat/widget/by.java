package androidx.appcompat.widget;

import android.os.Build.VERSION;
import android.view.View;

public final class by {
    public static void a(View view, CharSequence charSequence) {
        if (VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            bz.a(view, charSequence);
        }
    }
}
