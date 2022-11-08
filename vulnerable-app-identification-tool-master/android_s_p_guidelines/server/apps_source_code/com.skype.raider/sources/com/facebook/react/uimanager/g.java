package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.a;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.events.f;
import com.facebook.react.uimanager.events.h;

public final class g {
    private int a = -1;
    private final float[] b = new float[2];
    private boolean c = false;
    private long d = Long.MIN_VALUE;
    private final ViewGroup e;
    private final com.facebook.react.uimanager.events.g f = new com.facebook.react.uimanager.events.g();

    public g(ViewGroup viewGroup) {
        this.e = viewGroup;
    }

    public final void a(MotionEvent androidEvent, c eventDispatcher) {
        if (!this.c) {
            c(androidEvent, eventDispatcher);
            this.c = true;
            this.a = -1;
        }
    }

    public final void b(MotionEvent ev, c eventDispatcher) {
        int action = ev.getAction() & 255;
        if (action == 0) {
            if (this.a != -1) {
                FLog.e("React", "Got DOWN touch before receiving UP or CANCEL from last gesture");
            }
            this.c = false;
            this.d = ev.getEventTime();
            this.a = af.a(ev.getX(), ev.getY(), this.e, this.b);
            eventDispatcher.a(f.a(this.a, h.START, ev, this.d, this.b[0], this.b[1], this.f));
        } else if (!this.c) {
            if (this.a == -1) {
                FLog.e("React", "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
            } else if (action == 1) {
                eventDispatcher.a(f.a(this.a, h.END, ev, this.d, this.b[0], this.b[1], this.f));
                this.a = -1;
                this.d = Long.MIN_VALUE;
            } else if (action == 2) {
                eventDispatcher.a(f.a(this.a, h.MOVE, ev, this.d, this.b[0], this.b[1], this.f));
            } else if (action == 5) {
                eventDispatcher.a(f.a(this.a, h.START, ev, this.d, this.b[0], this.b[1], this.f));
            } else if (action == 6) {
                eventDispatcher.a(f.a(this.a, h.END, ev, this.d, this.b[0], this.b[1], this.f));
            } else if (action == 3) {
                if (this.f.e(ev.getDownTime())) {
                    c(ev, eventDispatcher);
                } else {
                    FLog.e("React", "Received an ACTION_CANCEL touch event for which we have no corresponding ACTION_DOWN");
                }
                this.a = -1;
                this.d = Long.MIN_VALUE;
            } else {
                FLog.w("React", "Warning : touch event was ignored. Action=" + action + " Target=" + this.a);
            }
        }
    }

    private void c(MotionEvent androidEvent, c eventDispatcher) {
        if (this.a == -1) {
            FLog.w("React", "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
            return;
        }
        boolean z;
        if (this.c) {
            z = false;
        } else {
            z = true;
        }
        a.a(z, "Expected to not have already sent a cancel for this gesture");
        ((c) a.a((Object) eventDispatcher)).a(f.a(this.a, h.CANCEL, androidEvent, this.d, this.b[0], this.b[1], this.f));
    }
}
