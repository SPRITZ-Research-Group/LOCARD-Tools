package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.g;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

@RestrictTo({a.LIBRARY_GROUP})
public final class MenuPopupWindow extends aa implements ab {
    private static Method a;
    private ab b;

    @RestrictTo({a.LIBRARY_GROUP})
    public static class MenuDropDownListView extends u {
        final int g;
        final int h;
        private ab i;
        private MenuItem j;

        public final /* bridge */ /* synthetic */ boolean a(MotionEvent motionEvent, int i) {
            return super.a(motionEvent, i);
        }

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public MenuDropDownListView(Context context, boolean hijackFocus) {
            super(context, hijackFocus);
            Configuration config = context.getResources().getConfiguration();
            if (VERSION.SDK_INT < 17 || 1 != config.getLayoutDirection()) {
                this.g = 22;
                this.h = 21;
                return;
            }
            this.g = 21;
            this.h = 22;
        }

        public void setHoverListener(ab hoverListener) {
            this.i = hoverListener;
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            ListMenuItemView selectedItem = (ListMenuItemView) getSelectedView();
            if (selectedItem != null && keyCode == this.g) {
                if (selectedItem.isEnabled() && selectedItem.b().hasSubMenu()) {
                    performItemClick(selectedItem, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (selectedItem == null || keyCode != this.h) {
                return super.onKeyDown(keyCode, event);
            } else {
                setSelection(-1);
                ((f) getAdapter()).a().b(false);
                return true;
            }
        }

        public boolean onHoverEvent(MotionEvent ev) {
            if (this.i != null) {
                int headersCount;
                f menuAdapter;
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerAdapter.getHeadersCount();
                    menuAdapter = (f) headerAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    menuAdapter = (f) adapter;
                }
                MenuItem menuItem = null;
                if (ev.getAction() != 10) {
                    int position = pointToPosition((int) ev.getX(), (int) ev.getY());
                    if (position != -1) {
                        int itemPosition = position - headersCount;
                        if (itemPosition >= 0 && itemPosition < menuAdapter.getCount()) {
                            menuItem = menuAdapter.a(itemPosition);
                        }
                    }
                }
                MenuItem oldMenuItem = this.j;
                if (oldMenuItem != menuItem) {
                    g menu = menuAdapter.a();
                    if (oldMenuItem != null) {
                        this.i.a(menu, oldMenuItem);
                    }
                    this.j = menuItem;
                    if (menuItem != null) {
                        this.i.b(menu, menuItem);
                    }
                }
            }
            return super.onHoverEvent(ev);
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
        }
    }

    public MenuPopupWindow(Context context, int defStyleAttr, int defStyleRes) {
        super(context, null, defStyleAttr, defStyleRes);
    }

    final u a(Context context, boolean hijackFocus) {
        MenuDropDownListView view = new MenuDropDownListView(context, hijackFocus);
        view.setHoverListener(this);
        return view;
    }

    public final void a() {
        if (VERSION.SDK_INT >= 23) {
            this.g.setEnterTransition(null);
        }
    }

    public final void b() {
        if (VERSION.SDK_INT >= 23) {
            this.g.setExitTransition(null);
        }
    }

    public final void a(ab hoverListener) {
        this.b = hoverListener;
    }

    public final void s() {
        if (a != null) {
            try {
                a.invoke(this.g, new Object[]{Boolean.valueOf(false)});
            } catch (Exception e) {
            }
        }
    }

    public final void b(@NonNull g menu, @NonNull MenuItem item) {
        if (this.b != null) {
            this.b.b(menu, item);
        }
    }

    public final void a(@NonNull g menu, @NonNull MenuItem item) {
        if (this.b != null) {
            this.b.a(menu, item);
        }
    }
}
