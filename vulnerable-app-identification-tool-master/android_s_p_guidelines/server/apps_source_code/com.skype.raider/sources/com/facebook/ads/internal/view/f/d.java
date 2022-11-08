package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.d.c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.ref.WeakReference;

public class d extends RelativeLayout {
    private final c a;
    @Nullable
    private g b;
    private WeakReference<a> c;

    public interface a {
        void a();
    }

    public d(Context context, c cVar) {
        super(context);
        this.a = cVar;
        u.b((View) this.a);
        addView(this.a.k(), new LayoutParams(-1, -1));
    }

    public final void a(com.facebook.ads.internal.view.f.a.c cVar) {
        addView(cVar, new LayoutParams(-1, -1));
        this.b = (g) cVar;
    }

    public final void b(com.facebook.ads.internal.view.f.a.c cVar) {
        u.b(cVar);
        this.b = null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ((View) this.a).layout(0, 0, getWidth(), getHeight());
        if (this.b != null) {
            this.b.layout(0, 0, getWidth(), getHeight());
        }
    }

    protected void onMeasure(int i, int i2) {
        Object obj = null;
        int j = this.a.j();
        int i3 = this.a.i();
        int defaultSize = getDefaultSize(j, i);
        int defaultSize2 = getDefaultSize(i3, i2);
        if (j > 0 && i3 > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == ErrorDialogData.SUPPRESSED && mode2 == ErrorDialogData.SUPPRESSED) {
                if (j * defaultSize2 < size * i3) {
                    defaultSize = (defaultSize2 * j) / i3;
                    obj = 1;
                } else if (j * defaultSize2 > size * i3) {
                    defaultSize2 = (size * i3) / j;
                    defaultSize = size;
                    size = 1;
                } else {
                    defaultSize = size;
                    size = 1;
                }
            } else if (mode == ErrorDialogData.SUPPRESSED) {
                defaultSize = (size * i3) / j;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                    size = 1;
                } else {
                    defaultSize = size;
                    size = 1;
                }
            } else {
                if (mode2 == ErrorDialogData.SUPPRESSED) {
                    defaultSize = (defaultSize2 * j) / i3;
                    if (mode == Integer.MIN_VALUE && defaultSize > size) {
                        defaultSize = size;
                        size = 1;
                    }
                } else {
                    if (mode2 != Integer.MIN_VALUE || i3 <= defaultSize2) {
                        defaultSize2 = i3;
                        defaultSize = j;
                    } else {
                        defaultSize = (defaultSize2 * j) / i3;
                    }
                    if (mode == Integer.MIN_VALUE && defaultSize > size) {
                        defaultSize2 = (size * i3) / j;
                        defaultSize = size;
                        size = 1;
                    }
                }
                size = 1;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (obj != null && this.c != null && this.c.get() != null) {
            ((a) this.c.get()).a();
        }
    }

    public void setViewImplInflationListener(a aVar) {
        this.c = new WeakReference(aVar);
    }
}
