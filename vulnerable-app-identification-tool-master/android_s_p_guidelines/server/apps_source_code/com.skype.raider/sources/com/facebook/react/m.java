package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.aa;
import com.facebook.react.bridge.ac;
import com.facebook.react.bridge.o;
import com.facebook.react.common.c;
import com.facebook.react.devsupport.e;
import com.facebook.react.modules.core.b;
import com.facebook.react.uimanager.aj;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public final class m {
    protected final List<q> a = new ArrayList();
    @Nullable
    protected String b;
    @Nullable
    protected o c;
    @Nullable
    protected String d;
    @Nullable
    protected ac e;
    @Nullable
    protected Application f;
    protected boolean g;
    @Nullable
    protected c h;
    @Nullable
    protected aj i;
    @Nullable
    protected aa j;
    protected d k = d.a;
    @Nullable
    protected Activity l;
    @Nullable
    protected b m;
    @Nullable
    protected e n;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    protected boolean r;
    protected int s = 1;

    m() {
    }

    public final m a(@Nullable aj uiImplementationProvider) {
        this.i = uiImplementationProvider;
        return this;
    }

    public final m a(String bundleAssetName) {
        this.b = bundleAssetName == null ? null : "assets://" + bundleAssetName;
        this.c = null;
        return this;
    }

    public final m b(String jsMainModuleName) {
        this.d = jsMainModuleName;
        return this;
    }

    public final m a(q reactPackage) {
        this.a.add(reactPackage);
        return this;
    }

    public final m a(Application application) {
        this.f = application;
        return this;
    }

    public final m a() {
        this.g = false;
        return this;
    }

    public final m a(c initialLifecycleState) {
        this.h = initialLifecycleState;
        return this;
    }

    public final m b() {
        this.n = null;
        return this;
    }

    public final l c() {
        a.a(this.f, "Application property has not been set with this builder");
        boolean z = (!this.g && this.b == null && this.c == null) ? false : true;
        a.a(z, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        z = (this.d == null && this.b == null && this.c == null) ? false : true;
        a.a(z, "Either MainModuleName or JS Bundle File needs to be provided");
        if (this.i == null) {
            this.i = new aj();
        }
        Context context = this.f;
        Activity activity = this.l;
        b bVar = this.m;
        o a = (this.c != null || this.b == null) ? this.c : o.a(this.f, this.b);
        return new l(context, activity, bVar, a, this.d, this.a, this.g, this.e, (c) a.a(this.h, "Initial lifecycle state was not set"), this.i, this.j, this.k, this.n, this.o, this.p, this.q, this.r, this.s);
    }
}
