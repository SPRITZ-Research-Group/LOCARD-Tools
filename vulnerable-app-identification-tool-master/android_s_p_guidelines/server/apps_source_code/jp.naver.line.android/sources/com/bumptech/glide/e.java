package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.n;
import com.bumptech.glide.manager.o;
import defpackage.aac;
import defpackage.abc;
import defpackage.abh;
import defpackage.abi;
import defpackage.abn;
import defpackage.abq;
import defpackage.abz;
import defpackage.aci;
import defpackage.acj;
import defpackage.ack;
import defpackage.acm;
import defpackage.acn;
import defpackage.acs;
import defpackage.ajx;
import defpackage.bu;
import java.util.Map;

public final class e {
    private final Map<Class<?>, z<?, ?>> a = new bu();
    private aac b;
    private abh c;
    private abc d;
    private ack e;
    private acs f;
    private acs g;
    private abz h;
    private acm i;
    private com.bumptech.glide.manager.e j;
    private int k = 4;
    private ajx l = new ajx();
    private o m;
    private acs n;
    private boolean o;

    public final e a(abh abh) {
        this.c = abh;
        return this;
    }

    public final e a(ack ack) {
        this.e = ack;
        return this;
    }

    final void a(o oVar) {
        this.m = oVar;
    }

    final d a(Context context) {
        if (this.f == null) {
            this.f = acs.b();
        }
        if (this.g == null) {
            this.g = acs.a();
        }
        if (this.n == null) {
            this.n = acs.d();
        }
        if (this.i == null) {
            this.i = new acn(context).a();
        }
        if (this.j == null) {
            this.j = new g();
        }
        if (this.c == null) {
            int b = this.i.b();
            if (b > 0) {
                this.c = new abq((long) b);
            } else {
                this.c = new abi();
            }
        }
        if (this.d == null) {
            this.d = new abn(this.i.c());
        }
        if (this.e == null) {
            this.e = new acj((long) this.i.a());
        }
        if (this.h == null) {
            this.h = new aci(context);
        }
        if (this.b == null) {
            this.b = new aac(this.e, this.h, this.g, this.f, acs.c(), acs.d(), this.o);
        }
        return new d(context, this.b, this.e, this.c, this.d, new n(this.m), this.j, this.k, this.l.p(), this.a);
    }
}
