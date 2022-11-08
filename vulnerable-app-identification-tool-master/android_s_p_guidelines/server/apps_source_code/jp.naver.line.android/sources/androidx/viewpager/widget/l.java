package androidx.viewpager.widget;

import android.view.View;
import androidx.viewpager.widget.ViewPager.LayoutParams;
import java.util.Comparator;

final class l implements Comparator<View> {
    l() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        LayoutParams layoutParams = (LayoutParams) ((View) obj).getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) ((View) obj2).getLayoutParams();
        if (layoutParams.a != layoutParams2.a) {
            return layoutParams.a ? 1 : -1;
        } else {
            return layoutParams.e - layoutParams2.e;
        }
    }
}
