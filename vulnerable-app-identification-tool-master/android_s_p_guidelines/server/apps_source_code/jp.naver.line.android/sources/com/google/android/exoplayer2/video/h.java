package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.WindowManager;
import defpackage.beg;

@TargetApi(16)
public final class h {
    private final WindowManager a;
    private final j b;
    private final i c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private boolean i;
    private long j;
    private long k;
    private long l;

    public h() {
        this(null);
    }

    public h(Context context) {
        i iVar = null;
        if (context != null) {
            context = context.getApplicationContext();
            this.a = (WindowManager) context.getSystemService("window");
        } else {
            this.a = null;
        }
        if (this.a != null) {
            if (beg.a >= 17) {
                DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
                if (displayManager != null) {
                    iVar = new i(this, displayManager);
                }
            }
            this.c = iVar;
            this.b = j.a();
        } else {
            this.c = null;
            this.b = null;
        }
        this.d = -9223372036854775807L;
        this.e = -9223372036854775807L;
    }

    public final void a() {
        this.i = false;
        if (this.a != null) {
            this.b.b();
            if (this.c != null) {
                this.c.a();
            }
            c();
        }
    }

    public final void b() {
        if (this.a != null) {
            if (this.c != null) {
                this.c.b();
            }
            this.b.c();
        }
    }

    public final long a(long j, long j2) {
        long j3;
        long j4;
        long j5 = 1000 * j;
        if (this.i) {
            if (j != this.f) {
                this.l++;
                this.g = this.h;
            }
            if (this.l >= 6) {
                j3 = ((j5 - this.k) / this.l) + this.g;
                if (b(j3, j2)) {
                    this.i = false;
                } else {
                    j4 = (this.j + j3) - this.k;
                    if (!this.i) {
                        this.k = j5;
                        this.j = j2;
                        this.l = 0;
                        this.i = true;
                    }
                    this.f = j;
                    this.h = j3;
                    if (this.b != null || this.d == -9223372036854775807L) {
                        return j4;
                    }
                    j = this.b.a;
                    if (j == -9223372036854775807L) {
                        return j4;
                    }
                    j2 = this.d;
                    j += ((j4 - j) / j2) * j2;
                    if (j4 <= j) {
                        j2 = j - j2;
                    } else {
                        long j6 = j;
                        j = j2 + j;
                        j2 = j6;
                    }
                    if (j - j4 >= j4 - j2) {
                        j = j2;
                    }
                    return j - this.e;
                }
            } else if (b(j5, j2)) {
                this.i = false;
            }
        }
        j4 = j2;
        j3 = j5;
        if (this.i) {
            this.k = j5;
            this.j = j2;
            this.l = 0;
            this.i = true;
        }
        this.f = j;
        this.h = j3;
        if (this.b != null) {
        }
        return j4;
    }

    private void c() {
        Display defaultDisplay = this.a.getDefaultDisplay();
        if (defaultDisplay != null) {
            double refreshRate = (double) defaultDisplay.getRefreshRate();
            Double.isNaN(refreshRate);
            this.d = (long) (1.0E9d / refreshRate);
            this.e = (this.d * 80) / 100;
        }
    }

    private boolean b(long j, long j2) {
        return Math.abs((j2 - this.j) - (j - this.k)) > 20000000;
    }
}
