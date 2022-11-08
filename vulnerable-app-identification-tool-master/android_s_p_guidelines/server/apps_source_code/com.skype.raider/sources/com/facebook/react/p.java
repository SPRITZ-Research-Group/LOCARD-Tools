package com.facebook.react;

import android.app.Application;
import com.facebook.infer.annotation.a;
import com.facebook.react.common.c;
import com.facebook.react.uimanager.aj;
import java.util.List;
import javax.annotation.Nullable;

public abstract class p {
    private final Application a;
    @Nullable
    private l b;

    protected abstract List<q> e();

    protected p(Application application) {
        this.a = application;
    }

    public final l a() {
        if (this.b == null) {
            this.b = d();
        }
        return this.b;
    }

    public final boolean b() {
        return this.b != null;
    }

    public final void c() {
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
    }

    protected l d() {
        m builder = l.a().a(this.a).b("index.android").a().b().a(new aj()).a(c.BEFORE_CREATE);
        for (q reactPackage : e()) {
            builder.a(reactPackage);
        }
        builder.a((String) a.a((Object) "index.android.bundle"));
        return builder.c();
    }
}
