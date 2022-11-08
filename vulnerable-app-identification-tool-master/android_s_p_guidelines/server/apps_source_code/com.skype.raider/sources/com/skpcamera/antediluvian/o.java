package com.skpcamera.antediluvian;

import com.skpcamera.antediluvian.l.a;

public final class o {
    private final l a = new l(a.FULL_RECTANGLE);
    private w b;

    public o(w program) {
        this.b = program;
    }

    public final void a() {
        if (this.b != null) {
            this.b = null;
        }
    }

    public final int b() {
        return this.b.a();
    }

    public final void a(int textureId, float[] texMatrix) {
        this.b.a(p.a, this.a.a(), this.a.c(), this.a.f(), this.a.d(), texMatrix, this.a.b(), textureId, this.a.e());
    }
}
