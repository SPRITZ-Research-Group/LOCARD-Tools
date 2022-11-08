package androidx.transition;

import android.os.Build.VERSION;
import android.view.ViewGroup;

final class at {
    static void a(ViewGroup viewGroup, boolean z) {
        if (VERSION.SDK_INT >= 18) {
            av.a(viewGroup, z);
        } else {
            au.a(viewGroup, z);
        }
    }
}
