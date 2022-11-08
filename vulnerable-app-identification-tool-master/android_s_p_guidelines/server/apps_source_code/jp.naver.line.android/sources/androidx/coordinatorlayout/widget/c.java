package androidx.coordinatorlayout.widget;

import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;

final class c implements OnHierarchyChangeListener {
    final /* synthetic */ CoordinatorLayout a;

    c(CoordinatorLayout coordinatorLayout) {
        this.a = coordinatorLayout;
    }

    public final void onChildViewAdded(View view, View view2) {
        if (this.a.mOnHierarchyChangeListener != null) {
            this.a.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    public final void onChildViewRemoved(View view, View view2) {
        this.a.onChildViewsChanged(2);
        if (this.a.mOnHierarchyChangeListener != null) {
            this.a.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
