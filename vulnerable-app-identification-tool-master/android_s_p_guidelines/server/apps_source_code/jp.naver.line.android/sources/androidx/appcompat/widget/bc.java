package androidx.appcompat.widget;

import defpackage.hs;

final class bc implements Runnable {
    final /* synthetic */ ListPopupWindow a;

    bc(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final void run() {
        if (this.a.c != null && hs.F(this.a.c) && this.a.c.getCount() > this.a.c.getChildCount() && this.a.c.getChildCount() <= this.a.d) {
            this.a.g.setInputMethodMode(2);
            this.a.a();
        }
    }
}
