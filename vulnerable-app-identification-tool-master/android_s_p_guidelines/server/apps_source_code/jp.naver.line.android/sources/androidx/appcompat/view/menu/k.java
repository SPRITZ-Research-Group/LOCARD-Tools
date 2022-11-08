package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public final class k extends BaseAdapter {
    l a;
    private int b = -1;
    private boolean c;
    private final boolean d;
    private final LayoutInflater e;
    private final int f;

    public final long getItemId(int i) {
        return (long) i;
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public k(l lVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.e = layoutInflater;
        this.a = lVar;
        this.f = i;
        b();
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final int getCount() {
        ArrayList nonActionItems = this.d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        if (this.b < 0) {
            return nonActionItems.size();
        }
        return nonActionItems.size() - 1;
    }

    public final l a() {
        return this.a;
    }

    public final p a(int i) {
        ArrayList nonActionItems = this.d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        if (this.b >= 0 && i >= this.b) {
            i++;
        }
        return (p) nonActionItems.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = a(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        boolean z = this.a.isGroupDividerEnabled() && groupId != (i2 >= 0 ? a(i2).getGroupId() : groupId);
        listMenuItemView.setGroupDividerEnabled(z);
        ac acVar = (ac) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        acVar.initialize(a(i), 0);
        return view;
    }

    private void b() {
        p expandedItem = this.a.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.a.getNonActionItems();
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
        b();
        super.notifyDataSetChanged();
    }
}
