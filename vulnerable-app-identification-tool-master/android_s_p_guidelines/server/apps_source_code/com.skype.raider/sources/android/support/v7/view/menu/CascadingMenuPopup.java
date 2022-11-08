package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.d;
import android.support.v7.appcompat.a.g;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.ab;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

final class CascadingMenuPopup extends l implements n, OnKeyListener, OnDismissListener {
    final Handler a;
    final List<a> b = new ArrayList();
    View c;
    boolean d;
    private final Context e;
    private final int f;
    private final int g;
    private final int h;
    private final boolean i;
    private final List<g> j = new LinkedList();
    private final OnGlobalLayoutListener k = new OnGlobalLayoutListener(this) {
        final /* synthetic */ CascadingMenuPopup a;

        {
            this.a = this$0;
        }

        public final void onGlobalLayout() {
            if (this.a.f() && this.a.b.size() > 0 && !((a) this.a.b.get(0)).a.i()) {
                View anchor = this.a.c;
                if (anchor == null || !anchor.isShown()) {
                    this.a.e();
                    return;
                }
                for (a aVar : this.a.b) {
                    aVar.a.c();
                }
            }
        }
    };
    private final OnAttachStateChangeListener l = new OnAttachStateChangeListener(this) {
        final /* synthetic */ CascadingMenuPopup a;

        {
            this.a = this$0;
        }

        public final void onViewAttachedToWindow(View v) {
        }

        public final void onViewDetachedFromWindow(View v) {
            if (this.a.y != null) {
                if (!this.a.y.isAlive()) {
                    this.a.y = v.getViewTreeObserver();
                }
                this.a.y.removeGlobalOnLayoutListener(this.a.k);
            }
            v.removeOnAttachStateChangeListener(this);
        }
    };
    private final ab m = new ab(this) {
        final /* synthetic */ CascadingMenuPopup a;

        {
            this.a = this$0;
        }

        public final void a(@NonNull g menu, @NonNull MenuItem item) {
            this.a.a.removeCallbacksAndMessages(menu);
        }

        public final void b(@NonNull final g menu, @NonNull final MenuItem item) {
            this.a.a.removeCallbacksAndMessages(null);
            int menuIndex = -1;
            int count = this.a.b.size();
            for (int i = 0; i < count; i++) {
                if (menu == ((a) this.a.b.get(i)).b) {
                    menuIndex = i;
                    break;
                }
            }
            if (menuIndex != -1) {
                a nextInfo;
                int nextIndex = menuIndex + 1;
                if (nextIndex < this.a.b.size()) {
                    nextInfo = (a) this.a.b.get(nextIndex);
                } else {
                    nextInfo = null;
                }
                this.a.a.postAtTime(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 d;

                    public final void run() {
                        if (nextInfo != null) {
                            this.d.a.d = true;
                            nextInfo.b.b(false);
                            this.d.a.d = false;
                        }
                        if (item.isEnabled() && item.hasSubMenu()) {
                            menu.a(item, 4);
                        }
                    }
                }, menu, SystemClock.uptimeMillis() + 200);
            }
        }
    };
    private int n = 0;
    private int o = 0;
    private View p;
    private int q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private android.support.v7.view.menu.n.a x;
    private ViewTreeObserver y;
    private OnDismissListener z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    private static class a {
        public final MenuPopupWindow a;
        public final g b;
        public final int c;

        public a(@NonNull MenuPopupWindow window, @NonNull g menu, int position) {
            this.a = window;
            this.b = menu;
            this.c = position;
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View anchor, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes, boolean overflowOnly) {
        this.e = context;
        this.p = anchor;
        this.g = popupStyleAttr;
        this.h = popupStyleRes;
        this.i = overflowOnly;
        this.v = false;
        this.q = j();
        Resources res = context.getResources();
        this.f = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.a = new Handler();
    }

    public final void b(boolean forceShow) {
        this.v = forceShow;
    }

    public final void c() {
        if (!f()) {
            for (g menu : this.j) {
                c(menu);
            }
            this.j.clear();
            this.c = this.p;
            if (this.c != null) {
                boolean addGlobalListener = this.y == null;
                this.y = this.c.getViewTreeObserver();
                if (addGlobalListener) {
                    this.y.addOnGlobalLayoutListener(this.k);
                }
                this.c.addOnAttachStateChangeListener(this.l);
            }
        }
    }

    public final void e() {
        int length = this.b.size();
        if (length > 0) {
            a[] addedMenus = (a[]) this.b.toArray(new a[length]);
            for (int i = length - 1; i >= 0; i--) {
                a info = addedMenus[i];
                if (info.a.f()) {
                    info.a.e();
                }
            }
        }
    }

    public final boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 1 || keyCode != 82) {
            return false;
        }
        e();
        return true;
    }

    private int j() {
        if (ViewCompat.f(this.p) == 1) {
            return 0;
        }
        return 1;
    }

    public final void a(g menu) {
        menu.a((n) this, this.e);
        if (f()) {
            c(menu);
        } else {
            this.j.add(menu);
        }
    }

    private void c(@NonNull g menu) {
        a parentInfo;
        View parentView;
        LayoutInflater inflater = LayoutInflater.from(this.e);
        f adapter = new f(menu, inflater, this.i);
        if (!f() && this.v) {
            adapter.a(true);
        } else if (f()) {
            adapter.a(l.b(menu));
        }
        int menuWidth = l.a(adapter, null, this.e, this.f);
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.e, this.g, this.h);
        menuPopupWindow.a(this.m);
        menuPopupWindow.a((OnItemClickListener) this);
        menuPopupWindow.a((OnDismissListener) this);
        menuPopupWindow.b(this.p);
        menuPopupWindow.c(this.o);
        menuPopupWindow.h();
        menuPopupWindow.o();
        menuPopupWindow.a((ListAdapter) adapter);
        menuPopupWindow.d(menuWidth);
        menuPopupWindow.c(this.o);
        if (this.b.size() > 0) {
            int i;
            MenuItem menuItem;
            parentInfo = (a) this.b.get(this.b.size() - 1);
            g gVar = parentInfo.b;
            int i2 = 0;
            int size = gVar.size();
            while (true) {
                i = i2;
                if (i >= size) {
                    menuItem = null;
                    break;
                }
                MenuItem item = gVar.getItem(i);
                if (item.hasSubMenu() && menu == item.getSubMenu()) {
                    menuItem = item;
                    break;
                }
                i2 = i + 1;
            }
            if (menuItem == null) {
                parentView = null;
            } else {
                f fVar;
                ListView g = parentInfo.a.g();
                ListAdapter adapter2 = g.getAdapter();
                if (adapter2 instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter2;
                    i = headerViewListAdapter.getHeadersCount();
                    fVar = (f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                    fVar = (f) adapter2;
                }
                int count = fVar.getCount();
                for (int i3 = 0; i3 < count; i3++) {
                    if (menuItem == fVar.a(i3)) {
                        i2 = i3;
                        break;
                    }
                }
                i2 = -1;
                if (i2 == -1) {
                    parentView = null;
                } else {
                    i2 = (i2 + i) - g.getFirstVisiblePosition();
                    if (i2 < 0 || i2 >= g.getChildCount()) {
                        parentView = null;
                    } else {
                        parentView = g.getChildAt(i2);
                    }
                }
            }
        } else {
            parentInfo = null;
            parentView = null;
        }
        if (parentView != null) {
            int nextMenuPosition;
            int parentOffsetLeft;
            int parentOffsetTop;
            int x;
            menuPopupWindow.s();
            menuPopupWindow.a();
            ListView g2 = ((a) this.b.get(this.b.size() - 1)).a.g();
            int[] iArr = new int[2];
            g2.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            this.c.getWindowVisibleDisplayFrame(rect);
            if (this.q == 1) {
                if ((g2.getWidth() + iArr[0]) + menuWidth <= rect.right) {
                    nextMenuPosition = 1;
                }
                nextMenuPosition = 0;
            } else {
                if (iArr[0] - menuWidth < 0) {
                    nextMenuPosition = 1;
                }
                nextMenuPosition = 0;
            }
            boolean showOnRight = nextMenuPosition == 1;
            this.q = nextMenuPosition;
            if (VERSION.SDK_INT >= 26) {
                menuPopupWindow.b(parentView);
                parentOffsetLeft = 0;
                parentOffsetTop = 0;
            } else {
                int[] anchorScreenLocation = new int[2];
                this.p.getLocationOnScreen(anchorScreenLocation);
                int[] parentViewScreenLocation = new int[2];
                parentView.getLocationOnScreen(parentViewScreenLocation);
                parentOffsetLeft = parentViewScreenLocation[0] - anchorScreenLocation[0];
                parentOffsetTop = parentViewScreenLocation[1] - anchorScreenLocation[1];
            }
            if ((this.o & 5) == 5) {
                if (showOnRight) {
                    x = parentOffsetLeft + menuWidth;
                } else {
                    x = parentOffsetLeft - parentView.getWidth();
                }
            } else if (showOnRight) {
                x = parentOffsetLeft + parentView.getWidth();
            } else {
                x = parentOffsetLeft - menuWidth;
            }
            menuPopupWindow.a(x);
            menuPopupWindow.r();
            menuPopupWindow.b(parentOffsetTop);
        } else {
            if (this.r) {
                menuPopupWindow.a(this.t);
            }
            if (this.s) {
                menuPopupWindow.b(this.u);
            }
            menuPopupWindow.a(i());
        }
        this.b.add(new a(menuPopupWindow, menu, this.q));
        menuPopupWindow.c();
        ListView listView = menuPopupWindow.g();
        listView.setOnKeyListener(this);
        if (parentInfo == null && this.w && menu.a != null) {
            View titleItemView = (FrameLayout) inflater.inflate(g.abc_popup_menu_header_item_layout, listView, false);
            TextView titleView = (TextView) titleItemView.findViewById(16908310);
            titleItemView.setEnabled(false);
            titleView.setText(menu.a);
            listView.addHeaderView(titleItemView, null, false);
            menuPopupWindow.c();
        }
    }

    public final boolean f() {
        return this.b.size() > 0 && ((a) this.b.get(0)).a.f();
    }

    public final void onDismiss() {
        a dismissedInfo = null;
        int count = this.b.size();
        for (int i = 0; i < count; i++) {
            a info = (a) this.b.get(i);
            if (!info.a.f()) {
                dismissedInfo = info;
                break;
            }
        }
        if (dismissedInfo != null) {
            dismissedInfo.b.b(false);
        }
    }

    public final void a(boolean cleared) {
        for (a aVar : this.b) {
            l.a(aVar.a.g().getAdapter()).notifyDataSetChanged();
        }
    }

    public final void a(android.support.v7.view.menu.n.a cb) {
        this.x = cb;
    }

    public final boolean a(t subMenu) {
        for (a info : this.b) {
            if (subMenu == info.b) {
                info.a.g().requestFocus();
                return true;
            }
        }
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        a((g) subMenu);
        if (this.x == null) {
            return true;
        }
        this.x.a(subMenu);
        return true;
    }

    public final void a(g menu, boolean allMenusAreClosing) {
        int size = this.b.size();
        int menuIndex = 0;
        while (menuIndex < size) {
            if (menu == ((a) this.b.get(menuIndex)).b) {
                break;
            }
            menuIndex++;
        }
        menuIndex = -1;
        if (menuIndex >= 0) {
            int nextMenuIndex = menuIndex + 1;
            if (nextMenuIndex < this.b.size()) {
                ((a) this.b.get(nextMenuIndex)).b.b(false);
            }
            a info = (a) this.b.remove(menuIndex);
            info.b.b((n) this);
            if (this.d) {
                info.a.b();
                info.a.j();
            }
            info.a.e();
            int count = this.b.size();
            if (count > 0) {
                this.q = ((a) this.b.get(count - 1)).c;
            } else {
                this.q = j();
            }
            if (count == 0) {
                e();
                if (this.x != null) {
                    this.x.a(menu, true);
                }
                if (this.y != null) {
                    if (this.y.isAlive()) {
                        this.y.removeGlobalOnLayoutListener(this.k);
                    }
                    this.y = null;
                }
                this.c.removeOnAttachStateChangeListener(this.l);
                this.z.onDismiss();
            } else if (allMenusAreClosing) {
                ((a) this.b.get(0)).b.b(false);
            }
        }
    }

    public final boolean a() {
        return false;
    }

    public final Parcelable d() {
        return null;
    }

    public final void a(Parcelable state) {
    }

    public final void a(int dropDownGravity) {
        if (this.n != dropDownGravity) {
            this.n = dropDownGravity;
            this.o = android.support.v4.view.d.a(dropDownGravity, ViewCompat.f(this.p));
        }
    }

    public final void a(@NonNull View anchor) {
        if (this.p != anchor) {
            this.p = anchor;
            this.o = android.support.v4.view.d.a(this.n, ViewCompat.f(this.p));
        }
    }

    public final void a(OnDismissListener listener) {
        this.z = listener;
    }

    public final ListView g() {
        if (this.b.isEmpty()) {
            return null;
        }
        return ((a) this.b.get(this.b.size() - 1)).a.g();
    }

    public final void b(int x) {
        this.r = true;
        this.t = x;
    }

    public final void c(int y) {
        this.s = true;
        this.u = y;
    }

    public final void c(boolean showTitle) {
        this.w = showTitle;
    }

    protected final boolean h() {
        return false;
    }
}
