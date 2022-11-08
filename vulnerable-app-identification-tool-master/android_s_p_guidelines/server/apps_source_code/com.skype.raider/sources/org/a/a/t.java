package org.a.a;

import org.a.a.b.c;
import org.a.a.b.f;
import org.a.a.b.m;
import org.a.a.b.n;
import org.a.a.b.p;

public class t extends Exception {
    public transient k d;
    public int e;
    public w f;
    public Object g;
    public int h;
    public int i;
    public int j;
    public boolean k;

    public t(k input) {
        this.d = input;
        this.e = input.b();
        if (input instanceof y) {
            this.f = ((y) input).g(1);
            this.i = this.f.c();
            this.j = this.f.d();
        }
        if (input instanceof p) {
            a(input);
        } else if (input instanceof e) {
            this.h = input.a(1);
            this.i = ((e) input).e();
            this.j = ((e) input).f();
        } else {
            this.h = input.a(1);
        }
    }

    private void a(k input) {
        p nodes = (p) input;
        this.g = nodes.e(1);
        Object positionNode = null;
        if (nodes instanceof f) {
            positionNode = ((f) nodes).a(false);
            if (positionNode == null) {
                boolean z;
                positionNode = ((f) nodes).a(true);
                if (positionNode != null) {
                    z = true;
                } else {
                    z = false;
                }
                this.k = z;
            }
        }
        n adaptor = nodes.j();
        if (positionNode == null) {
            positionNode = this.g;
        }
        w payload = adaptor.h(positionNode);
        if (payload != null) {
            this.f = payload;
            if (payload.c() <= 0) {
                int i = -1;
                Object priorNode = nodes.e(-1);
                while (priorNode != null) {
                    w priorPayload = adaptor.h(priorNode);
                    if (priorPayload == null || priorPayload.c() <= 0) {
                        i--;
                        try {
                            priorNode = nodes.e(i);
                        } catch (UnsupportedOperationException e) {
                            priorNode = null;
                        }
                    } else {
                        this.i = priorPayload.c();
                        this.j = priorPayload.d();
                        this.k = true;
                        return;
                    }
                }
                return;
            }
            this.i = payload.c();
            this.j = payload.d();
        } else if (this.g instanceof m) {
            this.i = ((m) this.g).e();
            this.j = ((m) this.g).f();
            if (this.g instanceof c) {
                this.f = ((c) this.g).b;
            }
        } else {
            this.f = new g(adaptor.d(this.g), adaptor.e(this.g));
        }
    }

    public final int a() {
        if (this.d instanceof y) {
            return this.f.a();
        }
        if (this.d instanceof p) {
            return ((p) this.d).j().d(this.g);
        }
        return this.h;
    }
}
