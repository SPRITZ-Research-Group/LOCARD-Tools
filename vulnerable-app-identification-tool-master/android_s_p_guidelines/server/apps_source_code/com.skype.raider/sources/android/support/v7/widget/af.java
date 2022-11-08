package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.b;
import android.support.v4.view.accessibility.b.k;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.m;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public final class af extends android.support.v4.view.a {
    final RecyclerView a;
    final android.support.v4.view.a c = new a(this);

    public static class a extends android.support.v4.view.a {
        final af a;

        public a(af recyclerViewDelegate) {
            this.a = recyclerViewDelegate;
        }

        public final void a(View host, b info) {
            super.a(host, info);
            if (!this.a.a.o() && this.a.a.e() != null) {
                this.a.a.e().a(host, info);
            }
        }

        public final boolean a(View host, int action, Bundle args) {
            if (super.a(host, action, args)) {
                return true;
            }
            if (this.a.a.o() || this.a.a.e() == null) {
                return false;
            }
            g e = this.a.a.e();
            m mVar = e.q.d;
            State state = e.q.B;
            return false;
        }
    }

    public af(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final boolean a(View host, int action, Bundle args) {
        if (super.a(host, action, args)) {
            return true;
        }
        if (this.a.o() || this.a.e() == null) {
            return false;
        }
        return this.a.e().k(action);
    }

    public final void a(View host, b info) {
        super.a(host, info);
        info.b(RecyclerView.class.getName());
        if (!this.a.o() && this.a.e() != null) {
            g e = this.a.e();
            m mVar = e.q.d;
            State state = e.q.B;
            if (e.q.canScrollVertically(-1) || e.q.canScrollHorizontally(-1)) {
                info.a(8192);
                info.i(true);
            }
            if (e.q.canScrollVertically(1) || e.q.canScrollHorizontally(1)) {
                info.a(4096);
                info.i(true);
            }
            info.a(k.a(e.a(mVar, state), e.b(mVar, state)));
        }
    }

    public final void a(View host, AccessibilityEvent event) {
        super.a(host, event);
        event.setClassName(RecyclerView.class.getName());
        if ((host instanceof RecyclerView) && !this.a.o()) {
            RecyclerView rv = (RecyclerView) host;
            if (rv.e() != null) {
                rv.e().a(event);
            }
        }
    }
}
