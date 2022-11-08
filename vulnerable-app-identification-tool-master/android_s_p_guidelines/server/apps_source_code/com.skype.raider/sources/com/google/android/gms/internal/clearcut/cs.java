package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.ah.e;
import java.io.IOException;
import java.util.Arrays;

public final class cs {
    private static final cs a = new cs(0, new int[0], new Object[0], false);
    private int b;
    private int[] c;
    private Object[] d;
    private int e;
    private boolean f;

    private cs() {
        this(0, new int[8], new Object[8], true);
    }

    private cs(int i, int[] iArr, Object[] objArr, boolean z) {
        this.e = -1;
        this.b = i;
        this.c = iArr;
        this.d = objArr;
        this.f = z;
    }

    public static cs a() {
        return a;
    }

    static cs a(cs csVar, cs csVar2) {
        int i = csVar.b + csVar2.b;
        Object copyOf = Arrays.copyOf(csVar.c, i);
        System.arraycopy(csVar2.c, 0, copyOf, csVar.b, csVar2.b);
        Object copyOf2 = Arrays.copyOf(csVar.d, i);
        System.arraycopy(csVar2.d, 0, copyOf2, csVar.b, csVar2.b);
        return new cs(i, copyOf, copyOf2, true);
    }

    private static void a(int i, Object obj, dk dkVar) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                dkVar.a(i2, ((Long) obj).longValue());
                return;
            case 1:
                dkVar.d(i2, ((Long) obj).longValue());
                return;
            case 2:
                dkVar.a(i2, (h) obj);
                return;
            case 3:
                if (dkVar.a() == e.j) {
                    dkVar.a(i2);
                    ((cs) obj).b(dkVar);
                    dkVar.b(i2);
                    return;
                }
                dkVar.b(i2);
                ((cs) obj).b(dkVar);
                dkVar.a(i2);
                return;
            case 5:
                dkVar.d(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(an.b());
        }
    }

    final void a(dk dkVar) throws IOException {
        int i;
        if (dkVar.a() == e.k) {
            for (i = this.b - 1; i >= 0; i--) {
                dkVar.a(this.c[i] >>> 3, this.d[i]);
            }
            return;
        }
        for (i = 0; i < this.b; i++) {
            dkVar.a(this.c[i] >>> 3, this.d[i]);
        }
    }

    final void a(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.b; i2++) {
            bn.a(stringBuilder, i, String.valueOf(this.c[i2] >>> 3), this.d[i2]);
        }
    }

    public final void b() {
        this.f = false;
    }

    public final void b(dk dkVar) throws IOException {
        if (this.b != 0) {
            int i;
            if (dkVar.a() == e.j) {
                for (i = 0; i < this.b; i++) {
                    a(this.c[i], this.d[i], dkVar);
                }
                return;
            }
            for (i = this.b - 1; i >= 0; i--) {
                a(this.c[i], this.d[i], dkVar);
            }
        }
    }

    public final int c() {
        int i = 0;
        int i2 = this.e;
        if (i2 == -1) {
            int i3 = 0;
            while (true) {
                i2 = i;
                if (i3 >= this.b) {
                    break;
                }
                i2 += s.d(this.c[i3] >>> 3, (h) this.d[i3]);
                i = i3 + 1;
            }
            this.e = i2;
        }
        return i2;
    }

    public final int d() {
        int i = 0;
        int i2 = this.e;
        if (i2 == -1) {
            int i3 = 0;
            while (true) {
                i2 = i;
                if (i3 < this.b) {
                    i = this.c[i3];
                    int i4 = i >>> 3;
                    switch (i & 7) {
                        case 0:
                            i = s.e(i4, ((Long) this.d[i3]).longValue());
                            break;
                        case 1:
                            ((Long) this.d[i3]).longValue();
                            i = s.g(i4);
                            break;
                        case 2:
                            i = s.c(i4, (h) this.d[i3]);
                            break;
                        case 3:
                            i = ((cs) this.d[i3]).d() + (s.l(i4) << 1);
                            break;
                        case 5:
                            ((Integer) this.d[i3]).intValue();
                            i = s.e(i4);
                            break;
                        default:
                            throw new IllegalStateException(an.b());
                    }
                    i2 += i;
                    i = i3 + 1;
                } else {
                    this.e = i2;
                }
            }
        }
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof cs)) {
            return false;
        }
        cs csVar = (cs) obj;
        if (this.b == csVar.b) {
            int i;
            boolean z;
            int[] iArr = this.c;
            int[] iArr2 = csVar.c;
            int i2 = this.b;
            for (i = 0; i < i2; i++) {
                if (iArr[i] != iArr2[i]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                Object[] objArr = this.d;
                Object[] objArr2 = csVar.d;
                i2 = this.b;
                for (i = 0; i < i2; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 17;
        int i3 = 0;
        int i4 = (this.b + 527) * 31;
        int[] iArr = this.c;
        int i5 = 17;
        for (i = 0; i < this.b; i++) {
            i5 = (i5 * 31) + iArr[i];
        }
        i = (i4 + i5) * 31;
        Object[] objArr = this.d;
        while (i3 < this.b) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
            i3++;
        }
        return i + i2;
    }
}
