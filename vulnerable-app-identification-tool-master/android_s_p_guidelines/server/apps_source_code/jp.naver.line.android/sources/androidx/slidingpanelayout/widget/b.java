package androidx.slidingpanelayout.widget;

import android.view.View;

final class b implements Runnable {
    final View a;
    final /* synthetic */ SlidingPaneLayout b;

    b(SlidingPaneLayout slidingPaneLayout, View view) {
        this.b = slidingPaneLayout;
        this.a = view;
    }

    public final void run() {
        if (this.a.getParent() == this.b) {
            this.a.setLayerType(0, null);
            this.b.b(this.a);
        }
        this.b.g.remove(this);
    }
}
