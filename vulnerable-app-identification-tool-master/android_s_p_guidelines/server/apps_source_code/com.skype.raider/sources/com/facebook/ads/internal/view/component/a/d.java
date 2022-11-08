package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.view.f.c.o;

public final class d {
    private final Context a;
    private final c b;
    private final com.facebook.ads.internal.view.a.a c;
    private final g d;
    private final View e;
    private final com.facebook.ads.internal.s.a f;
    private final s g;
    private final int h;
    private final int i;
    @Nullable
    private final o j;
    @Nullable
    private final View k;

    public static class a {
        private final Context a;
        private final c b;
        private final com.facebook.ads.internal.view.a.a c;
        private final g d;
        private final View e;
        private final com.facebook.ads.internal.s.a f;
        private final s g;
        private int h = 0;
        private int i = 1;
        @Nullable
        private o j;
        @Nullable
        private View k;

        public a(Context context, c cVar, com.facebook.ads.internal.view.a.a aVar, g gVar, View view, com.facebook.ads.internal.s.a aVar2, s sVar) {
            this.a = context;
            this.b = cVar;
            this.c = aVar;
            this.d = gVar;
            this.e = view;
            this.f = aVar2;
            this.g = sVar;
        }

        public final a a(int i) {
            this.h = i;
            return this;
        }

        public final a a(View view) {
            this.k = view;
            return this;
        }

        public final a a(o oVar) {
            this.j = oVar;
            return this;
        }

        public final d a() {
            return new d();
        }

        public final a b(int i) {
            this.i = i;
            return this;
        }
    }

    private d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
    }

    /* synthetic */ d(a aVar, byte b) {
        this(aVar);
    }

    final Context a() {
        return this.a;
    }

    final c b() {
        return this.b;
    }

    final com.facebook.ads.internal.view.a.a c() {
        return this.c;
    }

    final View d() {
        return this.e;
    }

    final com.facebook.ads.internal.s.a e() {
        return this.f;
    }

    final s f() {
        return this.g;
    }

    final g g() {
        return this.d;
    }

    final o h() {
        return this.j;
    }

    final View i() {
        return this.k;
    }

    final int j() {
        return this.h;
    }

    final int k() {
        return this.i;
    }
}
