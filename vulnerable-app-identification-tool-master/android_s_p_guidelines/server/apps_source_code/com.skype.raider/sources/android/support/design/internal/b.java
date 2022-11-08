package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.t;
import android.support.v7.widget.RecyclerView.s;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public final class b implements n {
    private NavigationMenuView a;
    private LinearLayout b;
    private android.support.v7.view.menu.n.a c;
    private android.support.v7.view.menu.g d;
    private int e;
    private b f;
    private LayoutInflater g;
    private int h;
    private boolean i;
    private ColorStateList j;
    private ColorStateList k;
    private Drawable l;
    private int m;
    private int n;
    private final OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void onClick(View v) {
            NavigationMenuItemView itemView = (NavigationMenuItemView) v;
            this.a.b(true);
            android.support.v7.view.menu.i item = itemView.b();
            boolean result = this.a.d.a((MenuItem) item, this.a, 0);
            if (item != null && item.isCheckable() && result) {
                this.a.f.a(item);
            }
            this.a.b(false);
            this.a.a(false);
        }
    };

    private static abstract class j extends s {
        public j(View itemView) {
            super(itemView);
        }
    }

    private static class a extends j {
        public a(View itemView) {
            super(itemView);
        }
    }

    private class b extends android.support.v7.widget.RecyclerView.a<j> {
        final /* synthetic */ b a;
        private final ArrayList<d> b = new ArrayList();
        private android.support.v7.view.menu.i c;
        private ColorDrawable d;
        private boolean e;

        public final /* synthetic */ void a(s sVar, int i) {
            j jVar = (j) sVar;
            switch (b(i)) {
                case 0:
                    NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) jVar.a;
                    navigationMenuItemView.a(this.a.k);
                    if (this.a.i) {
                        navigationMenuItemView.setTextAppearance(navigationMenuItemView.getContext(), this.a.h);
                    }
                    if (this.a.j != null) {
                        navigationMenuItemView.setTextColor(this.a.j);
                    }
                    navigationMenuItemView.setBackgroundDrawable(this.a.l != null ? this.a.l.getConstantState().newDrawable() : null);
                    navigationMenuItemView.a(((f) this.b.get(i)).a());
                    return;
                case 1:
                    ((TextView) jVar.a).setText(((f) this.b.get(i)).a().getTitle());
                    return;
                case 2:
                    e eVar = (e) this.b.get(i);
                    jVar.a.setPadding(0, eVar.a(), 0, eVar.b());
                    return;
                default:
                    return;
            }
        }

        b(b bVar) {
            this.a = bVar;
            g();
        }

        public final long a(int position) {
            return (long) position;
        }

        public final int a() {
            return this.b.size();
        }

        public final int b(int position) {
            d item = (d) this.b.get(position);
            if (item instanceof e) {
                return 2;
            }
            if (item instanceof c) {
                return 3;
            }
            if (!(item instanceof f)) {
                throw new RuntimeException("Unknown item type.");
            } else if (((f) item).a().hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        public final void b() {
            g();
            f();
        }

        private void g() {
            if (!this.e) {
                this.e = true;
                this.b.clear();
                this.b.add(new c());
                int currentGroupId = -1;
                int currentGroupStart = 0;
                boolean currentGroupHasIcon = false;
                int totalSize = this.a.d.k().size();
                for (int i = 0; i < totalSize; i++) {
                    android.support.v7.view.menu.i item = (android.support.v7.view.menu.i) this.a.d.k().get(i);
                    if (item.isChecked()) {
                        a(item);
                    }
                    if (item.isCheckable()) {
                        item.a(false);
                    }
                    if (item.hasSubMenu()) {
                        SubMenu subMenu = item.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i != 0) {
                                this.b.add(new e(this.a.n, 0));
                            }
                            this.b.add(new f(item, (byte) 0));
                            boolean subMenuHasIcon = false;
                            int subMenuStart = this.b.size();
                            int size = subMenu.size();
                            for (int j = 0; j < size; j++) {
                                android.support.v7.view.menu.i subMenuItem = (android.support.v7.view.menu.i) subMenu.getItem(j);
                                if (subMenuItem.isVisible()) {
                                    if (!(subMenuHasIcon || subMenuItem.getIcon() == null)) {
                                        subMenuHasIcon = true;
                                    }
                                    if (subMenuItem.isCheckable()) {
                                        subMenuItem.a(false);
                                    }
                                    if (item.isChecked()) {
                                        a(item);
                                    }
                                    this.b.add(new f(subMenuItem, (byte) 0));
                                }
                            }
                            if (subMenuHasIcon) {
                                a(subMenuStart, this.b.size());
                            }
                        }
                    } else {
                        int groupId = item.getGroupId();
                        if (groupId != currentGroupId) {
                            currentGroupStart = this.b.size();
                            currentGroupHasIcon = item.getIcon() != null;
                            if (i != 0) {
                                currentGroupStart++;
                                this.b.add(new e(this.a.n, this.a.n));
                            }
                        } else if (!(currentGroupHasIcon || item.getIcon() == null)) {
                            currentGroupHasIcon = true;
                            a(currentGroupStart, this.b.size());
                        }
                        if (currentGroupHasIcon && item.getIcon() == null) {
                            item.setIcon(17170445);
                        }
                        this.b.add(new f(item, (byte) 0));
                        currentGroupId = groupId;
                    }
                }
                this.e = false;
            }
        }

        private void a(int startIndex, int endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                MenuItem item = ((f) this.b.get(i)).a();
                if (item.getIcon() == null) {
                    if (this.d == null) {
                        this.d = new ColorDrawable(17170445);
                    }
                    item.setIcon(this.d);
                }
            }
        }

        public final void a(android.support.v7.view.menu.i checkedItem) {
            if (this.c != checkedItem && checkedItem.isCheckable()) {
                if (this.c != null) {
                    this.c.setChecked(false);
                }
                this.c = checkedItem;
                checkedItem.setChecked(true);
            }
        }

        public final Bundle c() {
            Bundle state = new Bundle();
            if (this.c != null) {
                state.putInt("android:menu:checked", this.c.getItemId());
            }
            SparseArray<ParcelableSparseArray> actionViewStates = new SparseArray();
            Iterator i$ = this.b.iterator();
            while (i$.hasNext()) {
                d navigationMenuItem = (d) i$.next();
                if (navigationMenuItem instanceof f) {
                    android.support.v7.view.menu.i item = ((f) navigationMenuItem).a();
                    View actionView = item != null ? item.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray container = new ParcelableSparseArray();
                        actionView.saveHierarchyState(container);
                        actionViewStates.put(item.getItemId(), container);
                    }
                }
            }
            state.putSparseParcelableArray("android:menu:action_views", actionViewStates);
            return state;
        }

        public final void a(Bundle state) {
            Iterator i$;
            int checkedItem = state.getInt("android:menu:checked", 0);
            if (checkedItem != 0) {
                this.e = true;
                i$ = this.b.iterator();
                while (i$.hasNext()) {
                    d item = (d) i$.next();
                    if (item instanceof f) {
                        android.support.v7.view.menu.i menuItem = ((f) item).a();
                        if (menuItem != null && menuItem.getItemId() == checkedItem) {
                            a(menuItem);
                            break;
                        }
                    }
                }
                this.e = false;
                g();
            }
            SparseArray<ParcelableSparseArray> actionViewStates = state.getSparseParcelableArray("android:menu:action_views");
            i$ = this.b.iterator();
            while (i$.hasNext()) {
                d navigationMenuItem = (d) i$.next();
                if (navigationMenuItem instanceof f) {
                    android.support.v7.view.menu.i item2 = ((f) navigationMenuItem).a();
                    View actionView = item2 != null ? item2.getActionView() : null;
                    if (actionView != null) {
                        actionView.restoreHierarchyState((SparseArray) actionViewStates.get(item2.getItemId()));
                    }
                }
            }
        }

        public final void a(boolean updateSuspended) {
            this.e = updateSuspended;
        }

        public final /* synthetic */ s a(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                    return new g(this.a.g, viewGroup, this.a.o);
                case 1:
                    return new i(this.a.g, viewGroup);
                case 2:
                    return new h(this.a.g, viewGroup);
                case 3:
                    return new a(this.a.b);
                default:
                    return null;
            }
        }
    }

    private interface d {
    }

    private static class c implements d {
        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }
    }

    private static class e implements d {
        private final int a;
        private final int b;

        public e(int paddingTop, int paddingBottom) {
            this.a = paddingTop;
            this.b = paddingBottom;
        }

        public final int a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }
    }

    private static class f implements d {
        private final android.support.v7.view.menu.i a;

        /* synthetic */ f(android.support.v7.view.menu.i x0, byte b) {
            this(x0);
        }

        private f(android.support.v7.view.menu.i item) {
            this.a = item;
        }

        public final android.support.v7.view.menu.i a() {
            return this.a;
        }
    }

    private static class g extends j {
        public g(LayoutInflater inflater, ViewGroup parent, OnClickListener listener) {
            super(inflater.inflate(android.support.design.a.g.design_navigation_item, parent, false));
            this.a.setOnClickListener(listener);
        }
    }

    private static class h extends j {
        public h(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(android.support.design.a.g.design_navigation_item_separator, parent, false));
        }
    }

    private static class i extends j {
        public i(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(android.support.design.a.g.design_navigation_item_subheader, parent, false));
        }
    }

    public final void a(Context context, android.support.v7.view.menu.g menu) {
        this.g = LayoutInflater.from(context);
        this.d = menu;
        Resources res = context.getResources();
        this.m = res.getDimensionPixelOffset(android.support.design.a.d.design_navigation_padding_top_default);
        this.n = res.getDimensionPixelOffset(android.support.design.a.d.design_navigation_separator_vertical_padding);
    }

    public final o a(ViewGroup root) {
        if (this.a == null) {
            this.a = (NavigationMenuView) this.g.inflate(android.support.design.a.g.design_navigation_menu, root, false);
            if (this.f == null) {
                this.f = new b(this);
            }
            this.b = (LinearLayout) this.g.inflate(android.support.design.a.g.design_navigation_item_header, this.a, false);
            this.a.setAdapter(this.f);
        }
        return this.a;
    }

    public final void a(boolean cleared) {
        if (this.f != null) {
            this.f.b();
        }
    }

    public final void a(android.support.v7.view.menu.n.a cb) {
        this.c = cb;
    }

    public final boolean a(t subMenu) {
        return false;
    }

    public final void a(android.support.v7.view.menu.g menu, boolean allMenusAreClosing) {
        if (this.c != null) {
            this.c.a(menu, allMenusAreClosing);
        }
    }

    public final boolean a() {
        return false;
    }

    public final boolean a(android.support.v7.view.menu.i item) {
        return false;
    }

    public final boolean b(android.support.v7.view.menu.i item) {
        return false;
    }

    public final int b() {
        return this.e;
    }

    public final void c() {
        this.e = 1;
    }

    public final Parcelable d() {
        Bundle state = new Bundle();
        if (this.a != null) {
            SparseArray<Parcelable> hierarchy = new SparseArray();
            this.a.saveHierarchyState(hierarchy);
            state.putSparseParcelableArray("android:menu:list", hierarchy);
        }
        if (this.f != null) {
            state.putBundle("android:menu:adapter", this.f.c());
        }
        return state;
    }

    public final void a(Parcelable parcelable) {
        Bundle state = (Bundle) parcelable;
        SparseArray<Parcelable> hierarchy = state.getSparseParcelableArray("android:menu:list");
        if (hierarchy != null) {
            this.a.restoreHierarchyState(hierarchy);
        }
        Bundle adapterState = state.getBundle("android:menu:adapter");
        if (adapterState != null) {
            this.f.a(adapterState);
        }
    }

    public final void c(android.support.v7.view.menu.i item) {
        this.f.a(item);
    }

    public final View a(@LayoutRes int res) {
        View view = this.g.inflate(res, this.b, false);
        this.b.addView(view);
        this.a.setPadding(0, 0, 0, this.a.getPaddingBottom());
        return view;
    }

    public final void a(@Nullable ColorStateList tint) {
        this.k = tint;
        a(false);
    }

    public final void b(@Nullable ColorStateList textColor) {
        this.j = textColor;
        a(false);
    }

    public final void b(@StyleRes int resId) {
        this.h = resId;
        this.i = true;
        a(false);
    }

    public final void a(Drawable itemBackground) {
        this.l = itemBackground;
    }

    public final void b(boolean updateSuspended) {
        if (this.f != null) {
            this.f.a(updateSuspended);
        }
    }
}
