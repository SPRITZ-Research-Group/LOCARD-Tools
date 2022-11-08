package com.facebook.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.n;

public class bp {
    private Context a;
    private String b;
    private String c;
    private int d;
    private br e;
    private Bundle f;
    private AccessToken g;

    public bp(Context context, String str, Bundle bundle) {
        if (!AccessToken.b()) {
            String a = bj.a(context);
            if (a != null) {
                this.b = a;
            } else {
                throw new n("Attempted to create a builder without a valid access token or a valid default Application ID.");
            }
        }
        a(context, str, bundle);
    }

    public bp(Context context, String str, String str2, Bundle bundle) {
        if (str == null) {
            str = bj.a(context);
        }
        bn.a(str, "applicationId");
        this.b = str;
        a(context, str2, bundle);
    }

    public final bp a(br brVar) {
        this.e = brVar;
        return this;
    }

    public bo a() {
        if (this.g != null) {
            this.f.putString("app_id", this.g.j());
            this.f.putString("access_token", this.g.d());
        } else {
            this.f.putString("app_id", this.b);
        }
        return bo.a(this.a, this.c, this.f, this.d, this.e);
    }

    public final String b() {
        return this.b;
    }

    public final Context c() {
        return this.a;
    }

    public final int d() {
        return this.d;
    }

    public final Bundle e() {
        return this.f;
    }

    public final br f() {
        return this.e;
    }

    private void a(Context context, String str, Bundle bundle) {
        this.a = context;
        this.c = str;
        if (bundle != null) {
            this.f = bundle;
        } else {
            this.f = new Bundle();
        }
    }
}
