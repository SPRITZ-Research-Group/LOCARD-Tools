package androidx.coordinatorlayout.widget;

import android.view.ViewTreeObserver.OnPreDrawListener;

final class e implements OnPreDrawListener {
    final /* synthetic */ CoordinatorLayout a;

    e(CoordinatorLayout coordinatorLayout) {
        this.a = coordinatorLayout;
    }

    public final boolean onPreDraw() {
        this.a.onChildViewsChanged(0);
        return true;
    }
}
