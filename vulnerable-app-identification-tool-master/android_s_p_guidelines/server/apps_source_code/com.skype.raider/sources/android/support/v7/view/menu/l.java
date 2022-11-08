package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;

abstract class l implements n, r, OnItemClickListener {
    private Rect a;

    public abstract void a(int i);

    public abstract void a(g gVar);

    public abstract void a(View view);

    public abstract void a(OnDismissListener onDismissListener);

    public abstract void b(int i);

    public abstract void b(boolean z);

    public abstract void c(int i);

    public abstract void c(boolean z);

    l() {
    }

    public final void a(Rect bounds) {
        this.a = bounds;
    }

    public final Rect i() {
        return this.a;
    }

    public final void a(@NonNull Context context, @Nullable g menu) {
    }

    public final boolean a(i item) {
        return false;
    }

    public final boolean b(i item) {
        return false;
    }

    public final int b() {
        return 0;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListAdapter outerAdapter = (ListAdapter) parent.getAdapter();
        a(outerAdapter).b.a((MenuItem) outerAdapter.getItem(position), (n) this, h() ? 0 : 4);
    }

    protected static int a(ListAdapter adapter, ViewGroup parent, Context context, int maxAllowedWidth) {
        int maxWidth = 0;
        View itemView = null;
        int itemType = 0;
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }
            if (parent == null) {
                parent = new FrameLayout(context);
            }
            itemView = adapter.getView(i, itemView, parent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            int itemWidth = itemView.getMeasuredWidth();
            if (itemWidth >= maxAllowedWidth) {
                return maxAllowedWidth;
            }
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth;
            }
        }
        return maxWidth;
    }

    protected static f a(ListAdapter adapter) {
        if (adapter instanceof HeaderViewListAdapter) {
            return (f) ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        }
        return (f) adapter;
    }

    protected static boolean b(g menu) {
        int count = menu.size();
        for (int i = 0; i < count; i++) {
            MenuItem childItem = menu.getItem(i);
            if (childItem.isVisible() && childItem.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    protected boolean h() {
        return true;
    }
}
