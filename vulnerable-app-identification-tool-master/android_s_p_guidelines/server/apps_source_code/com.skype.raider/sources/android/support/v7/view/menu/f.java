package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.appcompat.a.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({a.LIBRARY_GROUP})
public final class f extends BaseAdapter {
    static final int a = g.abc_popup_menu_item_layout;
    g b;
    private int c = -1;
    private boolean d;
    private final boolean e;
    private final LayoutInflater f;

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public f(g menu, LayoutInflater inflater, boolean overflowOnly) {
        this.e = overflowOnly;
        this.f = inflater;
        this.b = menu;
        b();
    }

    public final void a(boolean forceShow) {
        this.d = forceShow;
    }

    public final int getCount() {
        ArrayList<i> items = this.e ? this.b.n() : this.b.k();
        if (this.c < 0) {
            return items.size();
        }
        return items.size() - 1;
    }

    public final g a() {
        return this.b;
    }

    public final i a(int position) {
        ArrayList<i> items = this.e ? this.b.n() : this.b.k();
        if (this.c >= 0 && position >= this.c) {
            position++;
        }
        return (i) items.get(position);
    }

    public final long getItemId(int position) {
        return (long) position;
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.f.inflate(a, parent, false);
        }
        o.a itemView = (o.a) convertView;
        if (this.d) {
            ((ListMenuItemView) convertView).setForceShowIcon(true);
        }
        itemView.a(a(position));
        return convertView;
    }

    private void b() {
        i expandedItem = this.b.q();
        if (expandedItem != null) {
            ArrayList<i> items = this.b.n();
            int count = items.size();
            for (int i = 0; i < count; i++) {
                if (((i) items.get(i)) == expandedItem) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }

    public final void notifyDataSetChanged() {
        b();
        super.notifyDataSetChanged();
    }
}
