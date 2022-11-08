package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.view.menu.g.b;
import android.support.v7.widget.aq;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@RestrictTo({a.LIBRARY_GROUP})
public final class ExpandedMenuView extends ListView implements b, o, OnItemClickListener {
    private static final int[] a = new int[]{16842964, 16843049};
    private g b;

    public ExpandedMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        setOnItemClickListener(this);
        aq a = aq.a(context, attrs, a, defStyleAttr, 0);
        if (a.h(0)) {
            setBackgroundDrawable(a.a(0));
        }
        if (a.h(1)) {
            setDivider(a.a(1));
        }
        a.a();
    }

    public final void a(g menu) {
        this.b = menu;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public final boolean a(i item) {
        return this.b.a((MenuItem) item, 0);
    }

    public final void onItemClick(AdapterView parent, View v, int position, long id) {
        a((i) getAdapter().getItem(position));
    }
}
