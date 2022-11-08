package com.facebook.drawee.e;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.common.internal.VisibleForTesting;

public final class a {
    @VisibleForTesting
    a a;
    @VisibleForTesting
    final float b;
    @VisibleForTesting
    boolean c;
    @VisibleForTesting
    boolean d;
    @VisibleForTesting
    long e;
    @VisibleForTesting
    float f;
    @VisibleForTesting
    float g;

    public interface a {
        boolean l();
    }

    public a(Context context) {
        this.b = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        a();
    }

    public final void a() {
        this.a = null;
        b();
    }

    public final void b() {
        this.c = false;
        this.d = false;
    }

    public final void a(a clickListener) {
        this.a = clickListener;
    }

    public final boolean c() {
        return this.c;
    }

    public final boolean a(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.c = true;
                this.d = true;
                this.e = event.getEventTime();
                this.f = event.getX();
                this.g = event.getY();
                break;
            case 1:
                this.c = false;
                if (Math.abs(event.getX() - this.f) > this.b || Math.abs(event.getY() - this.g) > this.b) {
                    this.d = false;
                }
                if (this.d && event.getEventTime() - this.e <= ((long) ViewConfiguration.getLongPressTimeout()) && this.a != null) {
                    this.a.l();
                    break;
                }
            case 2:
                if (Math.abs(event.getX() - this.f) > this.b || Math.abs(event.getY() - this.g) > this.b) {
                    this.d = false;
                    break;
                }
            case 3:
                this.c = false;
                break;
        }
        this.d = false;
        return true;
    }
}
