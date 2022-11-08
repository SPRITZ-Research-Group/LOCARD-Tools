package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.k;
import java.io.IOException;
import java.util.Stack;

final class a implements b {
    private final byte[] a = new byte[8];
    private final Stack<a> b = new Stack();
    private final e c = new e();
    private c d;
    private int e;
    private int f;
    private long g;

    private static final class a {
        private final int a;
        private final long b;

        /* synthetic */ a(int x0, long x1, byte b) {
            this(x0, x1);
        }

        private a(int elementId, long elementEndPosition) {
            this.a = elementId;
            this.b = elementEndPosition;
        }
    }

    a() {
    }

    public final void a(c eventHandler) {
        this.d = eventHandler;
    }

    public final void a() {
        this.e = 0;
        this.b.clear();
        this.c.a();
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        com.google.android.exoplayer2.d.a.b(this.d != null);
        while (true) {
            if (this.b.isEmpty() || input.c() < ((a) this.b.peek()).b) {
                int a;
                if (this.e == 0) {
                    long result = this.c.a(input, true, false, 4);
                    if (result == -2) {
                        input.a();
                        while (true) {
                            input.c(this.a, 0, 4);
                            a = e.a(this.a[0]);
                            if (a != -1 && a <= 4) {
                                int a2 = (int) e.a(this.a, a, false);
                                if (this.d.b(a2)) {
                                    input.b(a);
                                    result = (long) a2;
                                }
                            }
                            input.b(1);
                        }
                    }
                    if (result == -1) {
                        return false;
                    }
                    this.f = (int) result;
                    this.e = 1;
                }
                if (this.e == 1) {
                    this.g = this.c.a(input, false, true, 8);
                    this.e = 2;
                }
                int type = this.d.a(this.f);
                switch (type) {
                    case 0:
                        input.b((int) this.g);
                        this.e = 0;
                    case 1:
                        long elementContentPosition = input.c();
                        this.b.add(new a(this.f, elementContentPosition + this.g, (byte) 0));
                        this.d.a(this.f, elementContentPosition, this.g);
                        this.e = 0;
                        return true;
                    case 2:
                        if (this.g > 8) {
                            throw new k("Invalid integer size: " + this.g);
                        }
                        this.d.a(this.f, a(input, (int) this.g));
                        this.e = 0;
                        return true;
                    case 3:
                        if (this.g > 2147483647L) {
                            throw new k("String element size: " + this.g);
                        }
                        String str;
                        c cVar = this.d;
                        int i = this.f;
                        a = (int) this.g;
                        if (a == 0) {
                            str = "";
                        } else {
                            byte[] bArr = new byte[a];
                            input.b(bArr, 0, a);
                            str = new String(bArr);
                        }
                        cVar.a(i, str);
                        this.e = 0;
                        return true;
                    case 4:
                        this.d.a(this.f, (int) this.g, input);
                        this.e = 0;
                        return true;
                    case 5:
                        if (this.g == 4 || this.g == 8) {
                            double intBitsToFloat;
                            c cVar2 = this.d;
                            int i2 = this.f;
                            a = (int) this.g;
                            long a3 = a(input, a);
                            if (a == 4) {
                                intBitsToFloat = (double) Float.intBitsToFloat((int) a3);
                            } else {
                                intBitsToFloat = Double.longBitsToDouble(a3);
                            }
                            cVar2.a(i2, intBitsToFloat);
                            this.e = 0;
                            return true;
                        }
                        throw new k("Invalid float size: " + this.g);
                    default:
                        throw new k("Invalid element type " + type);
                }
            }
            this.d.c(((a) this.b.pop()).a);
            return true;
        }
    }

    private long a(g input, int byteLength) throws IOException, InterruptedException {
        input.b(this.a, 0, byteLength);
        long value = 0;
        for (int i = 0; i < byteLength; i++) {
            value = (value << 8) | ((long) (this.a[i] & 255));
        }
        return value;
    }
}
