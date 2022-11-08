package com.a.a.a.c;

import com.a.a.a.b;
import com.a.a.a.b.a;
import com.a.a.a.b.d;
import com.a.a.a.g;
import com.a.a.a.h;
import com.a.a.a.j;
import java.io.IOException;
import java.io.Writer;

public final class c extends a {
    protected static final char[] m = a.b();
    protected final Writer n;
    protected char[] o;
    protected int p = 0;
    protected int q = 0;
    protected int r;
    protected char[] s;
    protected j t;

    public c(com.a.a.a.b.c cVar, int i, h hVar, Writer writer) {
        super(cVar, i, hVar);
        this.n = writer;
        this.o = cVar.b();
        this.r = this.o.length;
    }

    public final void a(String str) throws IOException, b {
        Object obj = 1;
        int a = this.e.a(str);
        if (a == 4) {
            com.a.a.a.a.a.e("Can not write a field name, expecting a value");
        }
        if (a != 1) {
            obj = null;
        }
        char[] cArr;
        if (this.a != null) {
            if (obj != null) {
                this.a.c(this);
            }
            if (a(com.a.a.a.c.a.QUOTE_FIELD_NAMES)) {
                if (this.q >= this.r) {
                    i();
                }
                cArr = this.o;
                a = this.q;
                this.q = a + 1;
                cArr[a] = '\"';
                f(str);
                if (this.q >= this.r) {
                    i();
                }
                cArr = this.o;
                a = this.q;
                this.q = a + 1;
                cArr[a] = '\"';
                return;
            }
            f(str);
            return;
        }
        if (this.q + 1 >= this.r) {
            i();
        }
        if (obj != null) {
            cArr = this.o;
            a = this.q;
            this.q = a + 1;
            cArr[a] = ',';
        }
        if (a(com.a.a.a.c.a.QUOTE_FIELD_NAMES)) {
            cArr = this.o;
            a = this.q;
            this.q = a + 1;
            cArr[a] = '\"';
            f(str);
            if (this.q >= this.r) {
                i();
            }
            cArr = this.o;
            a = this.q;
            this.q = a + 1;
            cArr[a] = '\"';
            return;
        }
        f(str);
    }

    public final void a() throws IOException, b {
        d("start an array");
        this.e = this.e.g();
        if (this.a != null) {
            this.a.e(this);
            return;
        }
        if (this.q >= this.r) {
            i();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '[';
    }

    public final void b() throws IOException, b {
        if (!this.e.a()) {
            com.a.a.a.a.a.e("Current context not an ARRAY but " + this.e.c());
        }
        if (this.a != null) {
            this.a.b(this, this.e.d());
        } else {
            if (this.q >= this.r) {
                i();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = ']';
        }
        this.e = this.e.i();
    }

    public final void c() throws IOException, b {
        d("start an object");
        this.e = this.e.h();
        if (this.a != null) {
            this.a.b(this);
            return;
        }
        if (this.q >= this.r) {
            i();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '{';
    }

    public final void d() throws IOException, b {
        if (!this.e.b()) {
            com.a.a.a.a.a.e("Current context not an object but " + this.e.c());
        }
        if (this.a != null) {
            this.a.a(this, this.e.d());
        } else {
            if (this.q >= this.r) {
                i();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = '}';
        }
        this.e = this.e.i();
    }

    public final void b(String str) throws IOException, b {
        d("write text value");
        if (str == null) {
            g();
            return;
        }
        if (this.q >= this.r) {
            i();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '\"';
        f(str);
        if (this.q >= this.r) {
            i();
        }
        cArr = this.o;
        i = this.q;
        this.q = i + 1;
        cArr[i] = '\"';
    }

    public final void c(String str) throws IOException, b {
        int length = str.length();
        int i = this.r - this.q;
        if (i == 0) {
            i();
            i = this.r - this.q;
        }
        if (i >= length) {
            str.getChars(0, length, this.o, this.q);
            this.q += length;
            return;
        }
        length = this.r - this.q;
        str.getChars(0, length, this.o, this.q);
        this.q += length;
        i();
        i = str.length() - length;
        while (i > this.r) {
            int i2 = this.r;
            str.getChars(length, length + i2, this.o, 0);
            this.p = 0;
            this.q = i2;
            i();
            length += i2;
            i -= i2;
        }
        str.getChars(length, length + i, this.o, 0);
        this.p = 0;
        this.q = i;
    }

    public final void b(j jVar) throws IOException, b {
        c(jVar.a());
    }

    public final void a(char c) throws IOException, b {
        if (this.q >= this.r) {
            i();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = c;
    }

    public final void a(int i) throws IOException, b {
        d("write number");
        if (this.d) {
            if (this.q + 13 >= this.r) {
                i();
            }
            char[] cArr = this.o;
            int i2 = this.q;
            this.q = i2 + 1;
            cArr[i2] = '\"';
            this.q = d.a(i, this.o, this.q);
            cArr = this.o;
            i2 = this.q;
            this.q = i2 + 1;
            cArr[i2] = '\"';
            return;
        }
        if (this.q + 11 >= this.r) {
            i();
        }
        this.q = d.a(i, this.o, this.q);
    }

    public final void a(long j) throws IOException, b {
        d("write number");
        if (this.d) {
            if (this.q + 23 >= this.r) {
                i();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = '\"';
            this.q = d.a(j, this.o, this.q);
            cArr = this.o;
            i = this.q;
            this.q = i + 1;
            cArr[i] = '\"';
            return;
        }
        if (this.q + 21 >= this.r) {
            i();
        }
        this.q = d.a(j, this.o, this.q);
    }

    public final void a(double d) throws IOException, b {
        if (this.d || ((Double.isNaN(d) || Double.isInfinite(d)) && a(com.a.a.a.c.a.QUOTE_NON_NUMERIC_NUMBERS))) {
            b(String.valueOf(d));
            return;
        }
        d("write number");
        c(String.valueOf(d));
    }

    public final void e() throws IOException, b {
        d("write null value");
        g();
    }

    protected final void d(java.lang.String r4) throws java.io.IOException, com.a.a.a.b {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r3 = this;
        r0 = r3.e;
        r0 = r0.j();
        r1 = 5;
        if (r0 != r1) goto L_0x0021;
    L_0x0009:
        r1 = new java.lang.StringBuilder;
        r2 = "Can not ";
        r1.<init>(r2);
        r1 = r1.append(r4);
        r2 = ", expecting field name";
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.a.a.a.a.a.e(r1);
    L_0x0021:
        r1 = r3.a;
        if (r1 != 0) goto L_0x0052;
    L_0x0025:
        switch(r0) {
            case 1: goto L_0x0029;
            case 2: goto L_0x0041;
            case 3: goto L_0x0044;
            default: goto L_0x0028;
        };
    L_0x0028:
        return;
    L_0x0029:
        r0 = 44;
    L_0x002b:
        r1 = r3.q;
        r2 = r3.r;
        if (r1 < r2) goto L_0x0034;
    L_0x0031:
        r3.i();
    L_0x0034:
        r1 = r3.o;
        r2 = r3.q;
        r1[r2] = r0;
        r0 = r3.q;
        r0 = r0 + 1;
        r3.q = r0;
        goto L_0x0028;
    L_0x0041:
        r0 = 58;
        goto L_0x002b;
    L_0x0044:
        r0 = r3.l;
        if (r0 == 0) goto L_0x0028;
    L_0x0048:
        r0 = r3.l;
        r0 = r0.a();
        r3.c(r0);
        goto L_0x0028;
    L_0x0052:
        switch(r0) {
            case 0: goto L_0x006b;
            case 1: goto L_0x0059;
            case 2: goto L_0x005f;
            case 3: goto L_0x0065;
            default: goto L_0x0055;
        };
    L_0x0055:
        com.a.a.a.e.c.a();
        goto L_0x0028;
    L_0x0059:
        r0 = r3.a;
        r0.f(r3);
        goto L_0x0028;
    L_0x005f:
        r0 = r3.a;
        r0.d(r3);
        goto L_0x0028;
    L_0x0065:
        r0 = r3.a;
        r0.a(r3);
        goto L_0x0028;
    L_0x006b:
        r0 = r3.e;
        r0 = r0.a();
        if (r0 != 0) goto L_0x0028;
    L_0x0073:
        r0 = r3.e;
        r0 = r0.b();
        if (r0 == 0) goto L_0x0028;
    L_0x007b:
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.c.c.d(java.lang.String):void");
    }

    public final void flush() throws IOException {
        i();
        if (this.n != null && a(com.a.a.a.c.a.FLUSH_PASSED_TO_STREAM)) {
            this.n.flush();
        }
    }

    public final void close() throws IOException {
        super.close();
        if (this.o != null && a(com.a.a.a.c.a.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                g f = f();
                if (!f.a()) {
                    if (!f.b()) {
                        break;
                    }
                    d();
                } else {
                    b();
                }
            }
        }
        i();
        if (this.n != null) {
            if (this.h.a() || a(com.a.a.a.c.a.AUTO_CLOSE_TARGET)) {
                this.n.close();
            } else if (a(com.a.a.a.c.a.FLUSH_PASSED_TO_STREAM)) {
                this.n.flush();
            }
        }
        char[] cArr = this.o;
        if (cArr != null) {
            this.o = null;
            this.h.a(cArr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f(String str) throws IOException, b {
        int length = str.length();
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        if (length > this.r) {
            i();
            int length2 = str.length();
            length = 0;
            while (true) {
                i = length;
                i2 = this.r;
                if (i + i2 > length2) {
                    i2 = length2 - i;
                }
                str.getChars(i, i + i2, this.o, 0);
                int[] iArr;
                char c2;
                char min;
                int i5;
                if (this.k != null) {
                    iArr = this.i;
                    c2 = this.j <= 0 ? 65535 : this.j;
                    min = Math.min(iArr.length, c2 + 1);
                    com.a.a.a.b.b bVar = this.k;
                    i3 = 0;
                    i4 = 0;
                    length = 0;
                    while (i3 < i2) {
                        do {
                            c = this.o[i3];
                            if (c < min) {
                                i5 = iArr[c];
                                if (i5 != 0) {
                                    break;
                                }
                                length = i5;
                                i3++;
                            } else if (c > c2) {
                                i5 = -1;
                                break;
                            } else {
                                j b = bVar.b();
                                this.t = b;
                                if (b != null) {
                                    i5 = -2;
                                    break;
                                }
                                i3++;
                            }
                        } while (i3 < i2);
                        i5 = length;
                        length = i3 - i4;
                        if (length > 0) {
                            this.n.write(this.o, i4, length);
                            if (i3 >= i2) {
                                break;
                            }
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                        i4 = a(this.o, i4, i2, c, i5);
                        length = i5;
                    }
                } else if (this.j != 0) {
                    c2 = this.j;
                    iArr = this.i;
                    min = Math.min(iArr.length, c2 + 1);
                    i3 = 0;
                    i4 = 0;
                    length = 0;
                    while (i3 < i2) {
                        do {
                            c = this.o[i3];
                            if (c < min) {
                                i5 = iArr[c];
                                if (i5 != 0) {
                                    break;
                                }
                                length = i5;
                                i3++;
                            } else {
                                if (c > c2) {
                                    i5 = -1;
                                    break;
                                }
                                i3++;
                            }
                        } while (i3 < i2);
                        i5 = length;
                        length = i3 - i4;
                        if (length > 0) {
                            this.n.write(this.o, i4, length);
                            if (i3 >= i2) {
                                break;
                            }
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                        i4 = a(this.o, i4, i2, c, i5);
                        length = i5;
                    }
                } else {
                    int[] iArr2 = this.i;
                    char length3 = iArr2.length;
                    i3 = 0;
                    length = 0;
                    while (length < i2) {
                        do {
                            c = this.o[length];
                            if (c < length3 && iArr2[c] != 0) {
                                break;
                            }
                            length++;
                        } while (length < i2);
                        i4 = length - i3;
                        if (i4 > 0) {
                            this.n.write(this.o, i3, i4);
                            if (length >= i2) {
                                break;
                            }
                        }
                        i4 = length + 1;
                        i3 = a(this.o, i4, i2, c, iArr2[c]);
                        length = i4;
                    }
                }
                length = i + i2;
                if (length >= length2) {
                    return;
                }
            }
        } else {
            if (this.q + length > this.r) {
                i();
            }
            str.getChars(0, length, this.o, this.q);
            int[] iArr3;
            if (this.k != null) {
                i4 = this.q + length;
                iArr3 = this.i;
                char c3 = this.j <= 0 ? 65535 : this.j;
                c = Math.min(iArr3.length, c3 + 1);
                com.a.a.a.b.b bVar2 = this.k;
                while (this.q < i4) {
                    char c4;
                    while (true) {
                        c4 = this.o[this.q];
                        if (c4 < c) {
                            i3 = iArr3[c4];
                            if (i3 != 0) {
                                break;
                            }
                            i3 = this.q + 1;
                            this.q = i3;
                            if (i3 >= i4) {
                                return;
                            }
                        } else if (c4 > c3) {
                            i3 = -1;
                            break;
                        } else {
                            j b2 = bVar2.b();
                            this.t = b2;
                            if (b2 != null) {
                                i3 = -2;
                                break;
                            }
                            i3 = this.q + 1;
                            this.q = i3;
                            if (i3 >= i4) {
                                return;
                            }
                        }
                    }
                    int i6 = this.q - this.p;
                    if (i6 > 0) {
                        this.n.write(this.o, this.p, i6);
                    }
                    this.q++;
                    a(c4, i3);
                }
            } else if (this.j != 0) {
                char c5 = this.j;
                i4 = this.q + length;
                iArr3 = this.i;
                c = Math.min(iArr3.length, c5 + 1);
                while (this.q < i4) {
                    char c6;
                    while (true) {
                        c6 = this.o[this.q];
                        if (c6 < c) {
                            length = iArr3[c6];
                            if (length != 0) {
                                break;
                            }
                            length = this.q + 1;
                            this.q = length;
                            if (length >= i4) {
                                return;
                            }
                        }
                        if (c6 > c5) {
                            length = -1;
                            break;
                        }
                        length = this.q + 1;
                        this.q = length;
                        if (length >= i4) {
                            return;
                        }
                    }
                    i = this.q - this.p;
                    if (i > 0) {
                        this.n.write(this.o, this.p, i);
                    }
                    this.q++;
                    a(c6, length);
                }
            } else {
                length += this.q;
                int[] iArr4 = this.i;
                char length4 = iArr4.length;
                while (this.q < length) {
                    while (true) {
                        char c7 = this.o[this.q];
                        if (c7 < length4 && iArr4[c7] != 0) {
                            break;
                        }
                        i2 = this.q + 1;
                        this.q = i2;
                        if (i2 >= length) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void g() throws IOException {
        if (this.q + 4 >= this.r) {
            i();
        }
        int i = this.q;
        char[] cArr = this.o;
        cArr[i] = 'n';
        i++;
        cArr[i] = 'u';
        i++;
        cArr[i] = 'l';
        i++;
        cArr[i] = 'l';
        this.q = i + 1;
    }

    private void a(char c, int i) throws IOException, b {
        int i2;
        int i3;
        char[] cArr;
        int length;
        if (i >= 0) {
            if (this.q >= 2) {
                i2 = this.q - 2;
                this.p = i2;
                i3 = i2 + 1;
                this.o[i2] = '\\';
                this.o[i3] = (char) i;
                return;
            }
            cArr = this.s;
            if (cArr == null) {
                cArr = h();
            }
            this.p = this.q;
            cArr[1] = (char) i;
            this.n.write(cArr, 0, 2);
        } else if (i == -2) {
            String a;
            if (this.t == null) {
                a = this.k.b().a();
            } else {
                a = this.t.a();
                this.t = null;
            }
            length = a.length();
            if (this.q >= length) {
                i3 = this.q - length;
                this.p = i3;
                a.getChars(0, length, this.o, i3);
                return;
            }
            this.p = this.q;
            this.n.write(a);
        } else if (this.q >= 6) {
            int c2;
            char[] cArr2 = this.o;
            i2 = this.q - 6;
            this.p = i2;
            cArr2[i2] = '\\';
            i2++;
            cArr2[i2] = 'u';
            if (c2 > 255) {
                i3 = (c2 >> 8) & 255;
                i2++;
                cArr2[i2] = m[i3 >> 4];
                i2++;
                cArr2[i2] = m[i3 & 15];
                c2 = (char) (c2 & 255);
            } else {
                i2++;
                cArr2[i2] = '0';
                i2++;
                cArr2[i2] = '0';
            }
            i2++;
            cArr2[i2] = m[c2 >> 4];
            cArr2[i2 + 1] = m[c2 & 15];
        } else {
            cArr = this.s;
            if (cArr == null) {
                cArr = h();
            }
            this.p = this.q;
            if (c2 > 255) {
                length = (c2 >> 8) & 255;
                i3 = c2 & 255;
                cArr[10] = m[length >> 4];
                cArr[11] = m[length & 15];
                cArr[12] = m[i3 >> 4];
                cArr[13] = m[i3 & 15];
                this.n.write(cArr, 8, 6);
                return;
            }
            cArr[6] = m[c2 >> 4];
            cArr[7] = m[c2 & 15];
            this.n.write(cArr, 2, 6);
        }
    }

    private int a(char[] cArr, int i, int i2, char c, int i3) throws IOException, b {
        char[] cArr2;
        int length;
        int i4;
        if (i3 >= 0) {
            if (i <= 1 || i >= i2) {
                cArr2 = this.s;
                if (cArr2 == null) {
                    cArr2 = h();
                }
                cArr2[1] = (char) i3;
                this.n.write(cArr2, 0, 2);
                return i;
            }
            i -= 2;
            cArr[i] = '\\';
            cArr[i + 1] = (char) i3;
            return i;
        } else if (i3 == -2) {
            String a;
            if (this.t == null) {
                a = this.k.b().a();
            } else {
                a = this.t.a();
                this.t = null;
            }
            length = a.length();
            if (i < length || i >= i2) {
                this.n.write(a);
                return i;
            }
            i -= length;
            a.getChars(0, length, cArr, i);
            return i;
        } else if (i <= 5 || i >= i2) {
            cArr2 = this.s;
            if (cArr2 == null) {
                cArr2 = h();
            }
            this.p = this.q;
            if (c > 255) {
                length = (c >> 8) & 255;
                i4 = c & 255;
                cArr2[10] = m[length >> 4];
                cArr2[11] = m[length & 15];
                cArr2[12] = m[i4 >> 4];
                cArr2[13] = m[i4 & 15];
                this.n.write(cArr2, 8, 6);
                return i;
            }
            cArr2[6] = m[c >> 4];
            cArr2[7] = m[c & 15];
            this.n.write(cArr2, 2, 6);
            return i;
        } else {
            int c2;
            int i5 = i - 6;
            length = i5 + 1;
            cArr[i5] = '\\';
            i5 = length + 1;
            cArr[length] = 'u';
            if (c2 > 255) {
                length = (c2 >> 8) & 255;
                i4 = i5 + 1;
                cArr[i5] = m[length >> 4];
                i5 = i4 + 1;
                cArr[i4] = m[length & 15];
                c2 = (char) (c2 & 255);
            } else {
                length = i5 + 1;
                cArr[i5] = '0';
                i5 = length + 1;
                cArr[length] = '0';
            }
            length = i5 + 1;
            cArr[i5] = m[c2 >> 4];
            cArr[length] = m[c2 & 15];
            return length - 5;
        }
    }

    private char[] h() {
        char[] cArr = new char[14];
        cArr[0] = '\\';
        cArr[2] = '\\';
        cArr[3] = 'u';
        cArr[4] = '0';
        cArr[5] = '0';
        cArr[8] = '\\';
        cArr[9] = 'u';
        this.s = cArr;
        return cArr;
    }

    private void i() throws IOException {
        int i = this.q - this.p;
        if (i > 0) {
            int i2 = this.p;
            this.p = 0;
            this.q = 0;
            this.n.write(this.o, i2, i);
        }
    }
}
