package com.facebook.drawee.view;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.c.t;
import com.facebook.drawee.c.u;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.a;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import javax.annotation.Nullable;

public final class b<DH extends a> implements u {
    private boolean a = false;
    private boolean b = false;
    private boolean c = true;
    private DH d;
    private DraweeController e = null;
    private final com.facebook.drawee.a.b f = com.facebook.drawee.a.b.a();

    public static <DH extends a> b<DH> a(@Nullable DH hierarchy) {
        return new b(hierarchy);
    }

    public b(@Nullable DH hierarchy) {
        if (hierarchy != null) {
            b(hierarchy);
        }
    }

    public final void b() {
        this.f.a(com.facebook.drawee.a.b.a.ON_HOLDER_ATTACH);
        this.b = true;
        j();
    }

    public final void c() {
        this.f.a(com.facebook.drawee.a.b.a.ON_HOLDER_DETACH);
        this.b = false;
        j();
    }

    public final boolean a(MotionEvent event) {
        if (g()) {
            return this.e.a(event);
        }
        return false;
    }

    public final void a(boolean isVisible) {
        if (this.c != isVisible) {
            this.f.a(isVisible ? com.facebook.drawee.a.b.a.ON_DRAWABLE_SHOW : com.facebook.drawee.a.b.a.ON_DRAWABLE_HIDE);
            this.c = isVisible;
            j();
        }
    }

    public final void a() {
        if (!this.a) {
            FLog.w(com.facebook.drawee.a.b.class, "%x: Draw requested for a non-attached controller %x. %s", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.e)), toString());
            this.b = true;
            this.c = true;
            j();
        }
    }

    private void a(@Nullable u visibilityCallback) {
        Drawable drawable = f();
        if (drawable instanceof t) {
            ((t) drawable).a(visibilityCallback);
        }
    }

    public final void a(@Nullable DraweeController draweeController) {
        boolean wasAttached = this.a;
        if (wasAttached) {
            i();
        }
        if (g()) {
            this.f.a(com.facebook.drawee.a.b.a.ON_CLEAR_OLD_CONTROLLER);
            this.e.a(null);
        }
        this.e = draweeController;
        if (this.e != null) {
            this.f.a(com.facebook.drawee.a.b.a.ON_SET_CONTROLLER);
            this.e.a(this.d);
        } else {
            this.f.a(com.facebook.drawee.a.b.a.ON_CLEAR_CONTROLLER);
        }
        if (wasAttached) {
            h();
        }
    }

    @Nullable
    public final DraweeController d() {
        return this.e;
    }

    public final void b(DH hierarchy) {
        this.f.a(com.facebook.drawee.a.b.a.ON_SET_HIERARCHY);
        boolean isControllerValid = g();
        a(null);
        this.d = (a) h.a((Object) hierarchy);
        Drawable drawable = this.d.a();
        boolean z = drawable == null || drawable.isVisible();
        a(z);
        a((u) this);
        if (isControllerValid) {
            this.e.a((a) hierarchy);
        }
    }

    public final DH e() {
        return (a) h.a(this.d);
    }

    public final Drawable f() {
        return this.d == null ? null : this.d.a();
    }

    private boolean g() {
        return this.e != null && this.e.h() == this.d;
    }

    private void h() {
        if (!this.a) {
            this.f.a(com.facebook.drawee.a.b.a.ON_ATTACH_CONTROLLER);
            this.a = true;
            if (this.e != null && this.e.h() != null) {
                this.e.j();
            }
        }
    }

    private void i() {
        if (this.a) {
            this.f.a(com.facebook.drawee.a.b.a.ON_DETACH_CONTROLLER);
            this.a = false;
            if (g()) {
                this.e.k();
            }
        }
    }

    private void j() {
        if (this.b && this.c) {
            h();
        } else {
            i();
        }
    }

    public final String toString() {
        return g.a(this).a("controllerAttached", this.a).a("holderAttached", this.b).a("drawableVisible", this.c).a(EventsEntry.TABLE_NAME, this.f.toString()).toString();
    }
}
