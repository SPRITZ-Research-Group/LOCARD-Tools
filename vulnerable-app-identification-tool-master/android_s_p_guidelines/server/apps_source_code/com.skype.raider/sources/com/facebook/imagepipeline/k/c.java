package com.facebook.imagepipeline.k;

import android.net.Uri;
import com.facebook.common.i.f;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.common.e;
import com.facebook.imagepipeline.core.h;
import com.facebook.imagepipeline.k.b.b;
import javax.annotation.Nullable;

public final class c {
    private Uri a = null;
    private b b = b.FULL_FETCH;
    @Nullable
    private e c = null;
    @Nullable
    private RotationOptions d = null;
    private com.facebook.imagepipeline.common.b e = com.facebook.imagepipeline.common.b.a();
    private com.facebook.imagepipeline.k.b.a f = com.facebook.imagepipeline.k.b.a.DEFAULT;
    private boolean g = h.f().a();
    private boolean h = false;
    private d i = d.HIGH;
    @Nullable
    private d j = null;
    private boolean k = true;
    private boolean l = true;
    @Nullable
    private com.facebook.imagepipeline.h.c m;
    @Nullable
    private com.facebook.imagepipeline.common.a n = null;

    public static class a extends RuntimeException {
        public a(String message) {
            super("Invalid request builder: " + message);
        }
    }

    public static c a(Uri uri) {
        c cVar = new c();
        com.facebook.common.internal.h.a((Object) uri);
        cVar.a = uri;
        return cVar;
    }

    public static c a(b imageRequest) {
        c a = a(imageRequest.b());
        a.e = imageRequest.i();
        a.n = imageRequest.h();
        a.f = imageRequest.a();
        a.h = imageRequest.k();
        a.b = imageRequest.m();
        a.j = imageRequest.q();
        a.g = imageRequest.j();
        a.i = imageRequest.l();
        a.c = imageRequest.f();
        a.m = imageRequest.r();
        a.d = imageRequest.g();
        return a;
    }

    private c() {
    }

    public final Uri a() {
        return this.a;
    }

    public final c a(b requestLevel) {
        this.b = requestLevel;
        return this;
    }

    public final b b() {
        return this.b;
    }

    @Deprecated
    public final c c() {
        this.d = RotationOptions.a();
        return this;
    }

    public final c a(@Nullable e resizeOptions) {
        this.c = resizeOptions;
        return this;
    }

    @Nullable
    public final e d() {
        return this.c;
    }

    public final c a(@Nullable RotationOptions rotationOptions) {
        this.d = rotationOptions;
        return this;
    }

    @Nullable
    public final RotationOptions e() {
        return this.d;
    }

    public final c a(@Nullable com.facebook.imagepipeline.common.a bytesRange) {
        this.n = bytesRange;
        return this;
    }

    @Nullable
    public final com.facebook.imagepipeline.common.a f() {
        return this.n;
    }

    public final com.facebook.imagepipeline.common.b g() {
        return this.e;
    }

    public final c a(com.facebook.imagepipeline.k.b.a cacheChoice) {
        this.f = cacheChoice;
        return this;
    }

    public final com.facebook.imagepipeline.k.b.a h() {
        return this.f;
    }

    public final c a(boolean enabled) {
        this.g = enabled;
        return this;
    }

    public final boolean i() {
        return this.g;
    }

    public final c j() {
        this.h = true;
        return this;
    }

    public final boolean k() {
        return this.h;
    }

    public final boolean l() {
        return this.k && f.b(this.a);
    }

    public final boolean m() {
        return this.l;
    }

    public final d n() {
        return this.i;
    }

    public final c a(d postprocessor) {
        this.j = postprocessor;
        return this;
    }

    @Nullable
    public final d o() {
        return this.j;
    }

    @Nullable
    public final com.facebook.imagepipeline.h.c p() {
        return this.m;
    }

    public final b q() {
        if (this.a == null) {
            throw new a("Source must be set!");
        }
        if (f.h(this.a)) {
            if (!this.a.isAbsolute()) {
                throw new a("Resource URI path must be absolute.");
            } else if (this.a.getPath().isEmpty()) {
                throw new a("Resource URI must not be empty");
            } else {
                try {
                    Integer.parseInt(this.a.getPath().substring(1));
                } catch (NumberFormatException e) {
                    throw new a("Resource URI path must be a resource id.");
                }
            }
        }
        if (!f.g(this.a) || this.a.isAbsolute()) {
            return new b(this);
        }
        throw new a("Asset URI path must be absolute.");
    }
}
