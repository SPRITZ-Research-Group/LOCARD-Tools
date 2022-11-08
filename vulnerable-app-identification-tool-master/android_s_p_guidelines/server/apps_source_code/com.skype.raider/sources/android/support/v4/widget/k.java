package android.support.v4.widget;

import android.content.Context;
import android.widget.OverScroller;

@Deprecated
public final class k {
    OverScroller a;

    @Deprecated
    public static k a(Context context) {
        return new k(context);
    }

    private k(Context context) {
        this.a = new OverScroller(context);
    }

    @Deprecated
    public final int a() {
        return this.a.getCurrY();
    }

    @Deprecated
    public final boolean b() {
        return this.a.computeScrollOffset();
    }

    @Deprecated
    public final void a(int startY, int velocityY, int minY) {
        this.a.fling(0, startY, 0, velocityY, 0, 0, minY, 0);
    }
}
