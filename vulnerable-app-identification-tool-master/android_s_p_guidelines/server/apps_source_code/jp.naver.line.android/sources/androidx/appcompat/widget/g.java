package androidx.appcompat.widget;

import android.view.View;

final class g implements Runnable {
    final /* synthetic */ ActionMenuPresenter a;
    private i b;

    public g(ActionMenuPresenter actionMenuPresenter, i iVar) {
        this.a = actionMenuPresenter;
        this.b = iVar;
    }

    public final void run() {
        if (this.a.c != null) {
            this.a.c.changeMenuMode();
        }
        View view = (View) this.a.f;
        if (!(view == null || view.getWindowToken() == null || !this.b.d())) {
            this.a.h = this.b;
        }
        this.a.j = null;
    }
}
