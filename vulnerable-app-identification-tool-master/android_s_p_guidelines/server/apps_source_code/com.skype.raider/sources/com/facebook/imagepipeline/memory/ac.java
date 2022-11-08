package com.facebook.imagepipeline.memory;

import com.facebook.common.e.a;
import com.facebook.common.e.i;
import com.facebook.common.e.l;
import com.facebook.common.internal.h;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ac {
    private final ab a;
    private d b;
    private j c;
    private n d;
    private x e;
    private i f;
    private l g;
    private a h;

    public ac(ab config) {
        this.a = (ab) h.a((Object) config);
    }

    public final d a() {
        if (this.b == null) {
            String i = this.a.i();
            Object obj = -1;
            switch (i.hashCode()) {
                case -1868884870:
                    if (i.equals(BitmapPoolType.LEGACY_DEFAULT_PARAMS)) {
                        obj = 2;
                        break;
                    }
                    break;
                case -1106578487:
                    if (i.equals("legacy")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -404562712:
                    if (i.equals(BitmapPoolType.EXPERIMENTAL)) {
                        obj = 1;
                        break;
                    }
                    break;
                case 95945896:
                    if (i.equals(BitmapPoolType.DUMMY)) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    this.b = new m();
                    break;
                case 1:
                    this.b = new p(this.a.j(), this.a.k(), y.a());
                    break;
                case 2:
                    this.b = new h(this.a.c(), k.a(), this.a.b());
                    break;
                default:
                    this.b = new h(this.a.c(), this.a.a(), this.a.b());
                    break;
            }
        }
        return this.b;
    }

    public final n b() {
        if (this.d == null) {
            this.d = new n(this.a.c(), this.a.f());
        }
        return this.d;
    }

    public final int c() {
        return this.a.f().g;
    }

    public final i d() {
        return a(0);
    }

    public final i a(int memoryChunkType) {
        if (this.f == null) {
            s sVar;
            switch (memoryChunkType) {
                case 0:
                    if (this.e == null) {
                        this.e = new x(this.a.c(), this.a.d(), this.a.e());
                    }
                    sVar = this.e;
                    break;
                case 1:
                    if (this.c == null) {
                        this.c = new j(this.a.c(), this.a.d(), this.a.e());
                    }
                    sVar = this.c;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid MemoryChunkType");
            }
            this.f = new v(sVar, e());
        }
        return this.f;
    }

    public final l e() {
        if (this.g == null) {
            this.g = new l(f());
        }
        return this.g;
    }

    public final a f() {
        if (this.h == null) {
            this.h = new o(this.a.c(), this.a.g(), this.a.h());
        }
        return this.h;
    }
}
