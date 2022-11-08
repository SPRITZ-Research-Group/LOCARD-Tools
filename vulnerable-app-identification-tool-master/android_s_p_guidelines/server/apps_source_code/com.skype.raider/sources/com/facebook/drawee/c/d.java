package com.facebook.drawee.c;

import android.annotation.SuppressLint;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public final class d {
    private int a = -1;
    private boolean b = false;
    private ColorFilter c = null;
    private int d = -1;
    private int e = -1;

    public final void a(int alpha) {
        this.a = alpha;
    }

    public final void a(ColorFilter colorFilter) {
        this.c = colorFilter;
        this.b = true;
    }

    public final void a(boolean dither) {
        this.d = dither ? 1 : 0;
    }

    public final void b(boolean filterBitmap) {
        this.e = filterBitmap ? 1 : 0;
    }

    @SuppressLint({"Range"})
    public final void a(Drawable drawable) {
        boolean z = true;
        if (drawable != null) {
            if (this.a != -1) {
                drawable.setAlpha(this.a);
            }
            if (this.b) {
                drawable.setColorFilter(this.c);
            }
            if (this.d != -1) {
                boolean z2;
                if (this.d != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawable.setDither(z2);
            }
            if (this.e != -1) {
                if (this.e == 0) {
                    z = false;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }
}
