package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.app.a;

final class bg extends BaseAdapter {
    final /* synthetic */ ScrollingTabContainerView a;

    public final long getItemId(int i) {
        return (long) i;
    }

    bg(ScrollingTabContainerView scrollingTabContainerView) {
        this.a = scrollingTabContainerView;
    }

    public final int getCount() {
        return this.a.b.getChildCount();
    }

    public final Object getItem(int i) {
        return ((bh) this.a.b.getChildAt(i)).a();
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            return this.a.a((a) getItem(i));
        }
        ((bh) view).a((a) getItem(i));
        return view;
    }
}
