package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.g;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class e implements n, OnItemClickListener {
    Context a;
    LayoutInflater b;
    g c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    a h;
    private android.support.v7.view.menu.n.a i;
    private int j;

    private class a extends BaseAdapter {
        final /* synthetic */ e a;
        private int b = -1;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(e eVar) {
            this.a = eVar;
            a();
        }

        public final int getCount() {
            int count = this.a.c.n().size() - this.a.e;
            return this.b < 0 ? count : count - 1;
        }

        public final i a(int position) {
            ArrayList<i> items = this.a.c.n();
            position += this.a.e;
            if (this.b >= 0 && position >= this.b) {
                position++;
            }
            return (i) items.get(position);
        }

        public final long getItemId(int position) {
            return (long) position;
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.a.b.inflate(this.a.g, parent, false);
            }
            ((android.support.v7.view.menu.o.a) convertView).a(a(position));
            return convertView;
        }

        private void a() {
            i expandedItem = this.a.c.q();
            if (expandedItem != null) {
                ArrayList<i> items = this.a.c.n();
                int count = items.size();
                for (int i = 0; i < count; i++) {
                    if (((i) items.get(i)) == expandedItem) {
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

    public e(Context context, int itemLayoutRes) {
        this(itemLayoutRes);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
    }

    private e(int itemLayoutRes) {
        this.g = itemLayoutRes;
        this.f = 0;
    }

    public final void a(Context context, g menu) {
        if (this.f != 0) {
            this.a = new ContextThemeWrapper(context, this.f);
            this.b = LayoutInflater.from(this.a);
        } else if (this.a != null) {
            this.a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = menu;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public final o a(ViewGroup root) {
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(g.abc_expanded_menu_layout, root, false);
            if (this.h == null) {
                this.h = new a(this);
            }
            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    public final ListAdapter c() {
        if (this.h == null) {
            this.h = new a(this);
        }
        return this.h;
    }

    public final void a(boolean cleared) {
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public final void a(android.support.v7.view.menu.n.a cb) {
        this.i = cb;
    }

    public final boolean a(t subMenu) {
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        new h(subMenu).a();
        if (this.i != null) {
            this.i.a(subMenu);
        }
        return true;
    }

    public final void a(g menu, boolean allMenusAreClosing) {
        if (this.i != null) {
            this.i.a(menu, allMenusAreClosing);
        }
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.c.a(this.h.a(position), (n) this, 0);
    }

    public final boolean a() {
        return false;
    }

    public final boolean a(i item) {
        return false;
    }

    public final boolean b(i item) {
        return false;
    }

    public final int b() {
        return this.j;
    }

    public final Parcelable d() {
        if (this.d == null) {
            return null;
        }
        Parcelable state = new Bundle();
        SparseArray sparseArray = new SparseArray();
        if (this.d != null) {
            this.d.saveHierarchyState(sparseArray);
        }
        state.putSparseParcelableArray("android:menu:list", sparseArray);
        return state;
    }

    public final void a(Parcelable state) {
        SparseArray sparseParcelableArray = ((Bundle) state).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }
}
