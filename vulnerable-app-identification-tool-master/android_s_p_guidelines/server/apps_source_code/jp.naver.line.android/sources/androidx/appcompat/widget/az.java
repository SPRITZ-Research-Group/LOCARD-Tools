package androidx.appcompat.widget;

import android.database.DataSetObserver;

final class az extends DataSetObserver {
    final /* synthetic */ ListPopupWindow a;

    az(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final void onChanged() {
        if (this.a.c()) {
            this.a.a();
        }
    }

    public final void onInvalidated() {
        this.a.b();
    }
}
