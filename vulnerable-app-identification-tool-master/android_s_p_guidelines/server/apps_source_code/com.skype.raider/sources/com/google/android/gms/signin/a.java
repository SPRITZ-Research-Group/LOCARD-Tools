package com.google.android.gms.signin;

import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a.d.c;
import com.google.android.gms.common.api.a.g;

public final class a {
    public static final g<com.google.android.gms.signin.internal.g> a = new g();
    public static final g<com.google.android.gms.signin.internal.g> b = new g();
    public static final com.google.android.gms.common.api.a.a<com.google.android.gms.signin.internal.g, c> c = new d();
    public static final Scope d = new Scope("profile");
    public static final Scope e = new Scope("email");
    public static final com.google.android.gms.common.api.a<c> f = new com.google.android.gms.common.api.a("SignIn.API", c, a);
    public static final com.google.android.gms.common.api.a<a> g = new com.google.android.gms.common.api.a("SignIn.INTERNAL_API", h, b);
    private static final com.google.android.gms.common.api.a.a<com.google.android.gms.signin.internal.g, a> h = new e();

    public static class a implements c {
        private final Bundle a;

        public final Bundle a() {
            return this.a;
        }
    }
}
