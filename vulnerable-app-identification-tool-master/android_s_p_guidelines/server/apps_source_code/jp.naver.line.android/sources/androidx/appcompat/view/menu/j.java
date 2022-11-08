package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

final class j extends BaseAdapter {
    final /* synthetic */ i a;
    private int b = -1;

    public final long getItemId(int i) {
        return (long) i;
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public j(i iVar) {
        this.a = iVar;
        a();
    }

    public final int getCount() {
        int size = this.a.c.getNonActionItems().size() - this.a.e;
        return this.b < 0 ? size : size - 1;
    }

    public final p a(int i) {
        ArrayList nonActionItems = this.a.c.getNonActionItems();
        i += this.a.e;
        if (this.b >= 0 && i >= this.b) {
            i++;
        }
        return (p) nonActionItems.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.a.b.inflate(this.a.g, viewGroup, false);
        }
        ((ac) view).initialize(a(i), 0);
        return view;
    }

    private void a() {
        p expandedItem = this.a.c.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.a.c.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (((p) nonActionItems.get(i)) == expandedItem) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
