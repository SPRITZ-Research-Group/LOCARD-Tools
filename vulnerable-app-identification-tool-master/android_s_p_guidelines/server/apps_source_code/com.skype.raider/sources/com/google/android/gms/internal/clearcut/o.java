package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.charset.Charset;

class o extends n {
    protected final byte[] b;

    o(byte[] bArr) {
        this.b = bArr;
    }

    public byte a(int i) {
        return this.b[i];
    }

    public int a() {
        return this.b.length;
    }

    protected final int a(int i, int i2) {
        return aj.a(i, this.b, e(), i2);
    }

    protected final String a(Charset charset) {
        return new String(this.b, e(), a(), charset);
    }

    final void a(g gVar) throws IOException {
        gVar.a(this.b, e(), a());
    }

    final boolean a(h hVar, int i) {
        if (i > hVar.a()) {
            throw new IllegalArgumentException("Length too large: " + i + a());
        } else if (i > hVar.a()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i + ", " + hVar.a());
        } else if (!(hVar instanceof o)) {
            return hVar.b(i).equals(b(i));
        } else {
            o oVar = (o) hVar;
            byte[] bArr = this.b;
            byte[] bArr2 = oVar.b;
            int e = e() + i;
            int e2 = e();
            int e3 = oVar.e();
            while (e2 < e) {
                if (bArr[e2] != bArr2[e3]) {
                    return false;
                }
                e2++;
                e3++;
            }
            return true;
        }
    }

    public final h b(int i) {
        int a = h.a(0, i, a());
        return a == 0 ? h.a : new k(this.b, e(), a);
    }

    public final boolean c() {
        int e = e();
        return cz.a(this.b, e, a() + e);
    }

    protected int e() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        if (a() != ((h) obj).a()) {
            return false;
        }
        if (a() == 0) {
            return true;
        }
        if (!(obj instanceof o)) {
            return obj.equals(this);
        }
        o oVar = (o) obj;
        int d = d();
        int d2 = oVar.d();
        return (d == 0 || d2 == 0 || d == d2) ? a((o) obj, a()) : false;
    }
}
