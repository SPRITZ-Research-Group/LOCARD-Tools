package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.r;
import android.support.v7.view.menu.t;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

final class ActionMenuPresenter extends android.support.v7.view.menu.b implements android.support.v4.view.b.a {
    private b A;
    d g;
    e h;
    a i;
    c j;
    final f k = new f(this);
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y = new SparseBooleanArray();
    private View z;

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        public int a;

        SavedState() {
        }

        SavedState(Parcel in) {
            this.a = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.a);
        }
    }

    private class a extends m {
        final /* synthetic */ ActionMenuPresenter a;

        public a(ActionMenuPresenter actionMenuPresenter, Context context, t subMenu, View anchorView) {
            this.a = actionMenuPresenter;
            super(context, subMenu, anchorView, false, android.support.v7.appcompat.a.a.actionOverflowMenuStyle);
            if (!((i) subMenu.getItem()).h()) {
                a(actionMenuPresenter.g == null ? (View) actionMenuPresenter.f : actionMenuPresenter.g);
            }
            a(actionMenuPresenter.k);
        }

        protected final void e() {
            this.a.i = null;
            this.a.l = 0;
            super.e();
        }
    }

    private class b extends android.support.v7.view.menu.ActionMenuItemView.b {
        final /* synthetic */ ActionMenuPresenter a;

        b(ActionMenuPresenter actionMenuPresenter) {
            this.a = actionMenuPresenter;
        }

        public final r a() {
            return this.a.i != null ? this.a.i.b() : null;
        }
    }

    private class c implements Runnable {
        final /* synthetic */ ActionMenuPresenter a;
        private e b;

        public c(ActionMenuPresenter actionMenuPresenter, e popup) {
            this.a = actionMenuPresenter;
            this.b = popup;
        }

        public final void run() {
            if (this.a.c != null) {
                this.a.c.f();
            }
            View menuView = (View) this.a.f;
            if (!(menuView == null || menuView.getWindowToken() == null || !this.b.c())) {
                this.a.h = this.b;
            }
            this.a.j = null;
        }
    }

    private class d extends AppCompatImageView implements android.support.v7.widget.ActionMenuView.a {
        final /* synthetic */ ActionMenuPresenter a;
        private final float[] b = new float[2];

        public d(final ActionMenuPresenter actionMenuPresenter, Context context) {
            this.a = actionMenuPresenter;
            super(context, null, android.support.v7.appcompat.a.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            as.a(this, getContentDescription());
            setOnTouchListener(new w(this, this) {
                final /* synthetic */ d b;

                public final r a() {
                    if (this.b.a.h == null) {
                        return null;
                    }
                    return this.b.a.h.b();
                }

                public final boolean b() {
                    this.b.a.g();
                    return true;
                }

                public final boolean c() {
                    if (this.b.a.j != null) {
                        return false;
                    }
                    this.b.a.h();
                    return true;
                }
            });
        }

        public final boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                this.a.g();
            }
            return true;
        }

        public final boolean f() {
            return false;
        }

        public final boolean g() {
            return false;
        }

        protected final boolean setFrame(int l, int t, int r, int b) {
            boolean changed = super.setFrame(l, t, r, b);
            Drawable d = getDrawable();
            Drawable bg = getBackground();
            if (!(d == null || bg == null)) {
                int width = getWidth();
                int height = getHeight();
                int halfEdge = Math.max(width, height) / 2;
                int centerX = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int centerY = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                android.support.v4.graphics.drawable.a.a(bg, centerX - halfEdge, centerY - halfEdge, centerX + halfEdge, centerY + halfEdge);
            }
            return changed;
        }
    }

    private class e extends m {
        final /* synthetic */ ActionMenuPresenter a;

        public e(ActionMenuPresenter actionMenuPresenter, Context context, g menu, View anchorView) {
            this.a = actionMenuPresenter;
            super(context, menu, anchorView, true, android.support.v7.appcompat.a.a.actionOverflowMenuStyle);
            a(8388613);
            a(actionMenuPresenter.k);
        }

        protected final void e() {
            if (this.a.c != null) {
                this.a.c.close();
            }
            this.a.h = null;
            super.e();
        }
    }

    private class f implements android.support.v7.view.menu.n.a {
        final /* synthetic */ ActionMenuPresenter a;

        f(ActionMenuPresenter actionMenuPresenter) {
            this.a = actionMenuPresenter;
        }

        public final boolean a(g subMenu) {
            if (subMenu == null) {
                return false;
            }
            this.a.l = ((t) subMenu).getItem().getItemId();
            android.support.v7.view.menu.n.a cb = this.a.c();
            return cb != null ? cb.a(subMenu) : false;
        }

        public final void a(g menu, boolean allMenusAreClosing) {
            if (menu instanceof t) {
                menu.o().b(false);
            }
            android.support.v7.view.menu.n.a cb = this.a.c();
            if (cb != null) {
                cb.a(menu, allMenusAreClosing);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, android.support.v7.appcompat.a.g.abc_action_menu_layout, android.support.v7.appcompat.a.g.abc_action_menu_item_layout);
    }

    public final void a(@NonNull Context context, @Nullable g menu) {
        super.a(context, menu);
        Resources res = context.getResources();
        android.support.v7.view.a abp = android.support.v7.view.a.a(context);
        if (!this.p) {
            this.o = abp.b();
        }
        if (!this.v) {
            this.q = abp.c();
        }
        if (!this.t) {
            this.s = abp.a();
        }
        int width = this.q;
        if (this.o) {
            if (this.g == null) {
                this.g = new d(this, this.a);
                if (this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                int spec = MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(spec, spec);
            }
            width -= this.g.getMeasuredWidth();
        } else {
            this.g = null;
        }
        this.r = width;
        this.x = (int) (56.0f * res.getDisplayMetrics().density);
        this.z = null;
    }

    public final void e() {
        if (!this.t) {
            this.s = android.support.v7.view.a.a(this.b).a();
        }
        if (this.c != null) {
            this.c.a(true);
        }
    }

    public final void f() {
        this.o = true;
        this.p = true;
    }

    public final void c(boolean isExclusive) {
        this.w = isExclusive;
    }

    public final void a(Drawable icon) {
        if (this.g != null) {
            this.g.setImageDrawable(icon);
            return;
        }
        this.n = true;
        this.m = icon;
    }

    public final o a(ViewGroup root) {
        o oldMenuView = this.f;
        o result = super.a(root);
        if (oldMenuView != result) {
            ((ActionMenuView) result).setPresenter(this);
        }
        return result;
    }

    public final View a(i item, View convertView, ViewGroup parent) {
        View actionView = item.getActionView();
        if (actionView == null || item.l()) {
            actionView = super.a(item, convertView, parent);
        }
        actionView.setVisibility(item.isActionViewExpanded() ? 8 : 0);
        ActionMenuView menuParent = (ActionMenuView) parent;
        LayoutParams lp = actionView.getLayoutParams();
        if (!menuParent.checkLayoutParams(lp)) {
            actionView.setLayoutParams(ActionMenuView.a(lp));
        }
        return actionView;
    }

    public final void a(i item, android.support.v7.view.menu.o.a itemView) {
        itemView.a(item);
        ActionMenuItemView actionItemView = (ActionMenuItemView) itemView;
        actionItemView.setItemInvoker(this.f);
        if (this.A == null) {
            this.A = new b(this);
        }
        actionItemView.setPopupCallback(this.A);
    }

    public final boolean c(i item) {
        return item.h();
    }

    public final void a(boolean cleared) {
        int count;
        super.a(cleared);
        ((View) this.f).requestLayout();
        if (this.c != null) {
            ArrayList<i> actionItems = this.c.m();
            count = actionItems.size();
            for (int i = 0; i < count; i++) {
                android.support.v4.view.b provider = ((i) actionItems.get(i)).a();
                if (provider != null) {
                    provider.a((android.support.v4.view.b.a) this);
                }
            }
        }
        ArrayList<i> nonActionItems = this.c != null ? this.c.n() : null;
        boolean hasOverflow = false;
        if (this.o && nonActionItems != null) {
            count = nonActionItems.size();
            hasOverflow = count == 1 ? !((i) nonActionItems.get(0)).isActionViewExpanded() : count > 0;
        }
        if (hasOverflow) {
            if (this.g == null) {
                this.g = new d(this, this.a);
            }
            ViewGroup parent = (ViewGroup) this.g.getParent();
            if (parent != this.f) {
                if (parent != null) {
                    parent.removeView(this.g);
                }
                ((ActionMenuView) this.f).addView(this.g, ActionMenuView.b());
            }
        } else if (this.g != null && this.g.getParent() == this.f) {
            ((ViewGroup) this.f).removeView(this.g);
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.o);
    }

    public final boolean a(ViewGroup parent, int childIndex) {
        if (parent.getChildAt(childIndex) == this.g) {
            return false;
        }
        return super.a(parent, childIndex);
    }

    public final boolean a(t subMenu) {
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        View anchor;
        t topSubMenu = subMenu;
        while (topSubMenu.r() != this.c) {
            topSubMenu = (t) topSubMenu.r();
        }
        i item = topSubMenu.getItem();
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof android.support.v7.view.menu.o.a) && ((android.support.v7.view.menu.o.a) childAt).b() == item) {
                    anchor = childAt;
                    break;
                }
            }
        }
        anchor = null;
        if (anchor == null) {
            return false;
        }
        this.l = subMenu.getItem().getItemId();
        boolean preserveIconSpacing = false;
        int count = subMenu.size();
        for (int i2 = 0; i2 < count; i2++) {
            MenuItem childItem = subMenu.getItem(i2);
            if (childItem.isVisible() && childItem.getIcon() != null) {
                preserveIconSpacing = true;
                break;
            }
        }
        this.i = new a(this, this.b, subMenu, anchor);
        this.i.a(preserveIconSpacing);
        this.i.a();
        super.a(subMenu);
        return true;
    }

    public final boolean g() {
        if (!this.o || k() || this.c == null || this.f == null || this.j != null || this.c.n().isEmpty()) {
            return false;
        }
        this.j = new c(this, new e(this, this.b, this.c, this.g));
        ((View) this.f).post(this.j);
        super.a(null);
        return true;
    }

    public final boolean h() {
        if (this.j == null || this.f == null) {
            m popup = this.h;
            if (popup == null) {
                return false;
            }
            popup.d();
            return true;
        }
        ((View) this.f).removeCallbacks(this.j);
        this.j = null;
        return true;
    }

    public final boolean i() {
        return h() | j();
    }

    public final boolean j() {
        if (this.i == null) {
            return false;
        }
        this.i.d();
        return true;
    }

    public final boolean k() {
        return this.h != null && this.h.f();
    }

    public final boolean a() {
        ArrayList<i> visibleItems;
        int itemsSize;
        int i;
        i item;
        if (this.c != null) {
            visibleItems = this.c.k();
            itemsSize = visibleItems.size();
        } else {
            visibleItems = null;
            itemsSize = 0;
        }
        int maxActions = this.s;
        int widthLimit = this.r;
        int querySpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup parent = (ViewGroup) this.f;
        int requiredItems = 0;
        int requestedItems = 0;
        int firstActionWidth = 0;
        boolean hasOverflow = false;
        for (i = 0; i < itemsSize; i++) {
            item = (i) visibleItems.get(i);
            if (item.j()) {
                requiredItems++;
            } else if (item.i()) {
                requestedItems++;
            } else {
                hasOverflow = true;
            }
            if (this.w && item.isActionViewExpanded()) {
                maxActions = 0;
            }
        }
        if (this.o && (hasOverflow || requiredItems + requestedItems > maxActions)) {
            maxActions--;
        }
        maxActions -= requiredItems;
        SparseBooleanArray seenGroups = this.y;
        seenGroups.clear();
        int cellSize = 0;
        int cellsRemaining = 0;
        if (this.u) {
            cellsRemaining = widthLimit / this.x;
            cellSize = this.x + ((widthLimit % this.x) / cellsRemaining);
        }
        for (i = 0; i < itemsSize; i++) {
            item = (i) visibleItems.get(i);
            View v;
            int measuredWidth;
            int groupId;
            if (item.j()) {
                v = a(item, this.z, parent);
                if (this.z == null) {
                    this.z = v;
                }
                if (this.u) {
                    cellsRemaining -= ActionMenuView.a(v, cellSize, cellsRemaining, querySpec, 0);
                } else {
                    v.measure(querySpec, querySpec);
                }
                measuredWidth = v.getMeasuredWidth();
                widthLimit -= measuredWidth;
                if (firstActionWidth == 0) {
                    firstActionWidth = measuredWidth;
                }
                groupId = item.getGroupId();
                if (groupId != 0) {
                    seenGroups.put(groupId, true);
                }
                item.d(true);
            } else if (item.i()) {
                groupId = item.getGroupId();
                boolean inGroup = seenGroups.get(groupId);
                boolean isAction = (maxActions > 0 || inGroup) && widthLimit > 0 && (!this.u || cellsRemaining > 0);
                if (isAction) {
                    v = a(item, this.z, parent);
                    if (this.z == null) {
                        this.z = v;
                    }
                    if (this.u) {
                        int cells = ActionMenuView.a(v, cellSize, cellsRemaining, querySpec, 0);
                        cellsRemaining -= cells;
                        if (cells == 0) {
                            isAction = false;
                        }
                    } else {
                        v.measure(querySpec, querySpec);
                    }
                    measuredWidth = v.getMeasuredWidth();
                    widthLimit -= measuredWidth;
                    if (firstActionWidth == 0) {
                        firstActionWidth = measuredWidth;
                    }
                    if (this.u) {
                        isAction &= widthLimit >= 0 ? 1 : 0;
                    } else {
                        isAction &= widthLimit + firstActionWidth > 0 ? 1 : 0;
                    }
                }
                if (isAction && groupId != 0) {
                    seenGroups.put(groupId, true);
                } else if (inGroup) {
                    seenGroups.put(groupId, false);
                    for (int j = 0; j < i; j++) {
                        i areYouMyGroupie = (i) visibleItems.get(j);
                        if (areYouMyGroupie.getGroupId() == groupId) {
                            if (areYouMyGroupie.h()) {
                                maxActions++;
                            }
                            areYouMyGroupie.d(false);
                        }
                    }
                }
                if (isAction) {
                    maxActions--;
                }
                item.d(isAction);
            } else {
                item.d(false);
            }
        }
        return true;
    }

    public final void a(g menu, boolean allMenusAreClosing) {
        i();
        super.a(menu, allMenusAreClosing);
    }

    public final Parcelable d() {
        SavedState state = new SavedState();
        state.a = this.l;
        return state;
    }

    public final void a(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState saved = (SavedState) state;
            if (saved.a > 0) {
                MenuItem item = this.c.findItem(saved.a);
                if (item != null) {
                    a((t) item.getSubMenu());
                }
            }
        }
    }

    public final void b(boolean isVisible) {
        if (isVisible) {
            super.a(null);
        } else if (this.c != null) {
            this.c.b(false);
        }
    }

    public final void a(ActionMenuView menuView) {
        this.f = menuView;
        menuView.a(this.c);
    }
}
