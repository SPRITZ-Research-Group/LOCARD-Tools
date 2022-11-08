package androidx.appcompat.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

final class ba implements OnScrollListener {
    final /* synthetic */ ListPopupWindow a;

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    ba(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1 && !this.a.q() && this.a.g.getContentView() != null) {
            this.a.f.removeCallbacks(this.a.e);
            this.a.e.run();
        }
    }
}
