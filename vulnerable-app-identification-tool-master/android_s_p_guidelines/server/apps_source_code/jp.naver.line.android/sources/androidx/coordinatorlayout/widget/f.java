package androidx.coordinatorlayout.widget;

import android.view.View;
import defpackage.hs;
import java.util.Comparator;

final class f implements Comparator<View> {
    f() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        View view = (View) obj2;
        float D = hs.D((View) obj);
        float D2 = hs.D(view);
        if (D > D2) {
            return -1;
        }
        return D < D2 ? 1 : 0;
    }
}
