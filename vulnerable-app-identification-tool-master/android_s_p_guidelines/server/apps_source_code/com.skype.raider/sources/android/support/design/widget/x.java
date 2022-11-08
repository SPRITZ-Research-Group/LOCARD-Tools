package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout.b;
import android.util.AttributeSet;
import android.view.View;

class x<V extends View> extends b<V> {
    private y a;
    private int b = 0;
    private int c = 0;

    public x(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean a(CoordinatorLayout parent, V child, int layoutDirection) {
        parent.a((View) child, layoutDirection);
        if (this.a == null) {
            this.a = new y(child);
        }
        this.a.a();
        if (this.b != 0) {
            this.a.a(this.b);
            this.b = 0;
        }
        if (this.c != 0) {
            this.a.b(this.c);
            this.c = 0;
        }
        return true;
    }

    public boolean a(int offset) {
        if (this.a != null) {
            return this.a.a(offset);
        }
        this.b = offset;
        return false;
    }

    public int c() {
        return this.a != null ? this.a.b() : 0;
    }
}
