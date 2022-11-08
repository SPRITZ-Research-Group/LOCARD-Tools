package androidx.viewpager.widget;

import android.database.DataSetObserver;

final class j extends DataSetObserver {
    final /* synthetic */ ViewPager a;

    j(ViewPager viewPager) {
        this.a = viewPager;
    }

    public final void onChanged() {
        this.a.dataSetChanged();
    }

    public final void onInvalidated() {
        this.a.dataSetChanged();
    }
}
