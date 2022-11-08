package com.skypecam.obscura.a;

import com.skypecam.obscura.a.a.a;

public final class f {
    private d a;
    private a b;

    public final void a(a callback) {
        e settings = d.a();
        this.b = new a(callback, new b(settings.b()));
        this.a = new d(this.b, settings);
        this.b.a();
    }

    public final void a() {
        this.a.d();
    }

    public final void b() {
        this.b.b();
        this.a.b();
    }
}
