package com.facebook.login.widget;

import com.facebook.internal.as;
import com.facebook.internal.bj;
import com.facebook.login.a;
import com.facebook.login.d;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class b {
    private a a = a.FRIENDS;
    private List<String> b = Collections.emptyList();
    private as c = null;
    private d d = d.NATIVE_WITH_FALLBACK;
    private String e = "rerequest";

    b() {
    }

    public final void a(a aVar) {
        this.a = aVar;
    }

    public final a a() {
        return this.a;
    }

    public final void a(List<String> list) {
        if (as.PUBLISH.equals(this.c)) {
            throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
        }
        this.b = list;
        this.c = as.READ;
    }

    public final void b(List<String> list) {
        if (as.READ.equals(this.c)) {
            throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
        } else if (bj.a((Collection) list)) {
            throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
        } else {
            this.b = list;
            this.c = as.PUBLISH;
        }
    }

    public final void a(d dVar) {
        this.d = dVar;
    }

    public final d b() {
        return this.d;
    }

    public final String c() {
        return this.e;
    }

    public final void a(String str) {
        this.e = str;
    }
}
