package androidx.appcompat.widget;

import android.view.ViewParent;

final class aw implements Runnable {
    final /* synthetic */ av a;

    aw(av avVar) {
        this.a = avVar;
    }

    public final void run() {
        ViewParent parent = this.a.c.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
