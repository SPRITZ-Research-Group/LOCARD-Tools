package com.google.android.exoplayer2.text.ttml;

import android.text.Layout.Alignment;
import com.google.android.exoplayer2.d.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class TtmlStyle {
    private String a;
    private int b;
    private boolean c;
    private int d;
    private boolean e;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private float k;
    private String l;
    private TtmlStyle m;
    private Alignment n;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FontSizeUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StyleFlags {
    }

    public final int a() {
        int i = 0;
        if (this.h == -1 && this.i == -1) {
            return -1;
        }
        int i2 = this.h == 1 ? 1 : 0;
        if (this.i == 1) {
            i = 2;
        }
        return i2 | i;
    }

    public final boolean b() {
        return this.f == 1;
    }

    public final TtmlStyle a(boolean linethrough) {
        boolean z;
        int i = 1;
        if (this.m == null) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        if (!linethrough) {
            i = 0;
        }
        this.f = i;
        return this;
    }

    public final boolean c() {
        return this.g == 1;
    }

    public final TtmlStyle b(boolean underline) {
        boolean z;
        int i = 1;
        if (this.m == null) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        if (!underline) {
            i = 0;
        }
        this.g = i;
        return this;
    }

    public final TtmlStyle c(boolean bold) {
        boolean z;
        int i = 1;
        if (this.m == null) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        if (!bold) {
            i = 0;
        }
        this.h = i;
        return this;
    }

    public final TtmlStyle d(boolean italic) {
        boolean z;
        int i = 1;
        if (this.m == null) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        if (!italic) {
            i = 0;
        }
        this.i = i;
        return this;
    }

    public final String d() {
        return this.a;
    }

    public final TtmlStyle a(String fontFamily) {
        a.b(this.m == null);
        this.a = fontFamily;
        return this;
    }

    public final int e() {
        if (this.c) {
            return this.b;
        }
        throw new IllegalStateException("Font color has not been defined.");
    }

    public final TtmlStyle a(int fontColor) {
        a.b(this.m == null);
        this.b = fontColor;
        this.c = true;
        return this;
    }

    public final boolean f() {
        return this.c;
    }

    public final int g() {
        if (this.e) {
            return this.d;
        }
        throw new IllegalStateException("Background color has not been defined.");
    }

    public final TtmlStyle b(int backgroundColor) {
        this.d = backgroundColor;
        this.e = true;
        return this;
    }

    public final boolean h() {
        return this.e;
    }

    public final TtmlStyle a(TtmlStyle ancestor) {
        if (ancestor != null) {
            if (!this.c && ancestor.c) {
                a(ancestor.b);
            }
            if (this.h == -1) {
                this.h = ancestor.h;
            }
            if (this.i == -1) {
                this.i = ancestor.i;
            }
            if (this.a == null) {
                this.a = ancestor.a;
            }
            if (this.f == -1) {
                this.f = ancestor.f;
            }
            if (this.g == -1) {
                this.g = ancestor.g;
            }
            if (this.n == null) {
                this.n = ancestor.n;
            }
            if (this.j == -1) {
                this.j = ancestor.j;
                this.k = ancestor.k;
            }
            if (!this.e && ancestor.e) {
                b(ancestor.d);
            }
        }
        return this;
    }

    public final TtmlStyle b(String id) {
        this.l = id;
        return this;
    }

    public final String i() {
        return this.l;
    }

    public final Alignment j() {
        return this.n;
    }

    public final TtmlStyle a(Alignment textAlign) {
        this.n = textAlign;
        return this;
    }

    public final TtmlStyle a(float fontSize) {
        this.k = fontSize;
        return this;
    }

    public final TtmlStyle c(int fontSizeUnit) {
        this.j = fontSizeUnit;
        return this;
    }

    public final int k() {
        return this.j;
    }

    public final float l() {
        return this.k;
    }
}
