package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.c;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@VisibleForTesting
public final class g {
    private final Account a;
    private final Set<Scope> b;
    private final Set<Scope> c;
    private final Map<com.google.android.gms.common.api.a<?>, b> d;
    private final int e;
    private final View f;
    private final String g;
    private final String h;
    private final c i;
    private Integer j;

    public static final class a {
        private Account a;
        private android.support.v4.util.b<Scope> b;
        private Map<com.google.android.gms.common.api.a<?>, b> c;
        private int d = 0;
        private View e;
        private String f;
        private String g;
        private c h = c.a;

        public final a a(Account account) {
            this.a = account;
            return this;
        }

        public final a a(String str) {
            this.f = str;
            return this;
        }

        public final a a(Collection<Scope> collection) {
            if (this.b == null) {
                this.b = new android.support.v4.util.b();
            }
            this.b.addAll(collection);
            return this;
        }

        public final g a() {
            return new g(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public final a b(String str) {
            this.g = str;
            return this;
        }
    }

    public static final class b {
        public final Set<Scope> a;
    }

    public g(Account account, Set<Scope> set, Map<com.google.android.gms.common.api.a<?>, b> map, int i, View view, String str, String str2, c cVar) {
        Map map2;
        this.a = account;
        this.b = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map2 == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.d = map2;
        this.f = view;
        this.e = i;
        this.g = str;
        this.h = str2;
        this.i = cVar;
        Set hashSet = new HashSet(this.b);
        for (b bVar : this.d.values()) {
            hashSet.addAll(bVar.a);
        }
        this.c = Collections.unmodifiableSet(hashSet);
    }

    @Nullable
    public final Account a() {
        return this.a;
    }

    public final void a(Integer num) {
        this.j = num;
    }

    public final Account b() {
        return this.a != null ? this.a : new Account("<<default account>>", "com.google");
    }

    public final Set<Scope> c() {
        return this.b;
    }

    public final Set<Scope> d() {
        return this.c;
    }

    @Nullable
    public final String e() {
        return this.g;
    }

    @Nullable
    public final String f() {
        return this.h;
    }

    @Nullable
    public final c g() {
        return this.i;
    }

    @Nullable
    public final Integer h() {
        return this.j;
    }
}
