package net.hockeyapp.android.f;

import java.io.UnsupportedEncodingException;

public final class b {

    static abstract class a {
        public byte[] a;
        public int b;

        a() {
        }
    }

    static class b extends a {
        private static final byte[] g = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] h = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] i;
        private int j;
        private final byte[] k;

        public b() {
            this.a = null;
            this.d = true;
            this.e = false;
            this.f = false;
            this.k = g;
            this.i = new byte[2];
            this.c = 0;
            this.j = this.e ? 19 : -1;
        }

        public final boolean a(byte[] r14, int r15) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r13 = this;
            r0 = r13.k;
            r4 = r13.a;
            r2 = 0;
            r1 = r13.j;
            r5 = 0;
            r15 = r15 + 0;
            r9 = -1;
            r10 = r13.c;
            switch(r10) {
                case 0: goto L_0x0010;
                case 1: goto L_0x00b1;
                case 2: goto L_0x00d5;
                default: goto L_0x0010;
            };
        L_0x0010:
            r10 = -1;
            if (r9 == r10) goto L_0x0203;
        L_0x0013:
            r10 = 0;
            r2 = r2 + 1;
            r11 = r9 >> 18;
            r11 = r11 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r10 = 1;
            r2 = r2 + 1;
            r11 = r9 >> 12;
            r11 = r11 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r10 = 2;
            r2 = r2 + 1;
            r11 = r9 >> 6;
            r11 = r11 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r10 = 3;
            r2 = r2 + 1;
            r11 = r9 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r1 = r1 + -1;
            if (r1 != 0) goto L_0x0203;
        L_0x0041:
            r10 = r13.f;
            if (r10 == 0) goto L_0x004c;
        L_0x0045:
            r10 = 4;
            r2 = r2 + 1;
            r11 = 13;
            r4[r10] = r11;
        L_0x004c:
            r3 = r2 + 1;
            r10 = 10;
            r4[r2] = r10;
            r1 = 19;
            r6 = r5;
        L_0x0055:
            r10 = r6 + 3;
            if (r10 > r15) goto L_0x00f8;
        L_0x0059:
            r10 = r14[r6];
            r10 = r10 & 255;
            r10 = r10 << 16;
            r11 = r6 + 1;
            r11 = r14[r11];
            r11 = r11 & 255;
            r11 = r11 << 8;
            r10 = r10 | r11;
            r11 = r6 + 2;
            r11 = r14[r11];
            r11 = r11 & 255;
            r9 = r10 | r11;
            r10 = r9 >> 18;
            r10 = r10 & 63;
            r10 = r0[r10];
            r4[r3] = r10;
            r10 = r3 + 1;
            r11 = r9 >> 12;
            r11 = r11 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r10 = r3 + 2;
            r11 = r9 >> 6;
            r11 = r11 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r10 = r3 + 3;
            r11 = r9 & 63;
            r11 = r0[r11];
            r4[r10] = r11;
            r5 = r6 + 3;
            r2 = r3 + 4;
            r1 = r1 + -1;
            if (r1 != 0) goto L_0x0203;
        L_0x009c:
            r10 = r13.f;
            if (r10 == 0) goto L_0x00a7;
        L_0x00a0:
            r3 = r2 + 1;
            r10 = 13;
            r4[r2] = r10;
            r2 = r3;
        L_0x00a7:
            r3 = r2 + 1;
            r10 = 10;
            r4[r2] = r10;
            r1 = 19;
            r6 = r5;
            goto L_0x0055;
        L_0x00b1:
            r10 = 2;
            if (r10 > r15) goto L_0x0010;
        L_0x00b4:
            r10 = r13.i;
            r11 = 0;
            r10 = r10[r11];
            r10 = r10 & 255;
            r10 = r10 << 16;
            r11 = 0;
            r5 = r5 + 1;
            r11 = r14[r11];
            r11 = r11 & 255;
            r11 = r11 << 8;
            r10 = r10 | r11;
            r11 = 1;
            r5 = r5 + 1;
            r11 = r14[r11];
            r11 = r11 & 255;
            r9 = r10 | r11;
            r10 = 0;
            r13.c = r10;
            goto L_0x0010;
        L_0x00d5:
            if (r15 <= 0) goto L_0x0010;
        L_0x00d7:
            r10 = r13.i;
            r11 = 0;
            r10 = r10[r11];
            r10 = r10 & 255;
            r10 = r10 << 16;
            r11 = r13.i;
            r12 = 1;
            r11 = r11[r12];
            r11 = r11 & 255;
            r11 = r11 << 8;
            r10 = r10 | r11;
            r11 = 0;
            r5 = r5 + 1;
            r11 = r14[r11];
            r11 = r11 & 255;
            r9 = r10 | r11;
            r10 = 0;
            r13.c = r10;
            goto L_0x0010;
        L_0x00f8:
            r10 = r13.c;
            r10 = r6 - r10;
            r11 = r15 + -1;
            if (r10 != r11) goto L_0x016a;
        L_0x0100:
            r7 = 0;
            r10 = r13.c;
            if (r10 <= 0) goto L_0x0165;
        L_0x0105:
            r10 = r13.i;
            r11 = 0;
            r7 = r7 + 1;
            r10 = r10[r11];
            r5 = r6;
        L_0x010d:
            r10 = r10 & 255;
            r9 = r10 << 4;
            r10 = r13.c;
            r10 = r10 - r7;
            r13.c = r10;
            r2 = r3 + 1;
            r10 = r9 >> 6;
            r10 = r10 & 63;
            r10 = r0[r10];
            r4[r3] = r10;
            r3 = r2 + 1;
            r10 = r9 & 63;
            r10 = r0[r10];
            r4[r2] = r10;
            r10 = r13.d;
            if (r10 == 0) goto L_0x0138;
        L_0x012c:
            r2 = r3 + 1;
            r10 = 61;
            r4[r3] = r10;
            r3 = r2 + 1;
            r10 = 61;
            r4[r2] = r10;
        L_0x0138:
            r2 = r3;
            r10 = r13.e;
            if (r10 == 0) goto L_0x014f;
        L_0x013d:
            r10 = r13.f;
            if (r10 == 0) goto L_0x0148;
        L_0x0141:
            r3 = r2 + 1;
            r10 = 13;
            r4[r2] = r10;
            r2 = r3;
        L_0x0148:
            r3 = r2 + 1;
            r10 = 10;
            r4[r2] = r10;
        L_0x014e:
            r2 = r3;
        L_0x014f:
            r10 = r13.c;
            if (r10 == 0) goto L_0x0158;
        L_0x0153:
            r10 = "BASE64";
            net.hockeyapp.android.f.e.f(r10);
        L_0x0158:
            if (r5 == r15) goto L_0x015f;
        L_0x015a:
            r10 = "BASE64";
            net.hockeyapp.android.f.e.f(r10);
        L_0x015f:
            r13.b = r2;
            r13.j = r1;
            r10 = 1;
            return r10;
        L_0x0165:
            r5 = r6 + 1;
            r10 = r14[r6];
            goto L_0x010d;
        L_0x016a:
            r10 = r13.c;
            r10 = r6 - r10;
            r11 = r15 + -2;
            if (r10 != r11) goto L_0x01e3;
        L_0x0172:
            r7 = 0;
            r10 = r13.c;
            r11 = 1;
            if (r10 <= r11) goto L_0x01d8;
        L_0x0178:
            r10 = r13.i;
            r11 = 0;
            r7 = r7 + 1;
            r10 = r10[r11];
            r5 = r6;
        L_0x0180:
            r10 = r10 & 255;
            r11 = r10 << 10;
            r10 = r13.c;
            if (r10 <= 0) goto L_0x01dd;
        L_0x0188:
            r10 = r13.i;
            r8 = r7 + 1;
            r10 = r10[r7];
            r7 = r8;
        L_0x018f:
            r10 = r10 & 255;
            r10 = r10 << 2;
            r9 = r11 | r10;
            r10 = r13.c;
            r10 = r10 - r7;
            r13.c = r10;
            r2 = r3 + 1;
            r10 = r9 >> 12;
            r10 = r10 & 63;
            r10 = r0[r10];
            r4[r3] = r10;
            r3 = r2 + 1;
            r10 = r9 >> 6;
            r10 = r10 & 63;
            r10 = r0[r10];
            r4[r2] = r10;
            r2 = r3 + 1;
            r10 = r9 & 63;
            r10 = r0[r10];
            r4[r3] = r10;
            r10 = r13.d;
            if (r10 == 0) goto L_0x01c1;
        L_0x01ba:
            r3 = r2 + 1;
            r10 = 61;
            r4[r2] = r10;
            r2 = r3;
        L_0x01c1:
            r10 = r13.e;
            if (r10 == 0) goto L_0x014f;
        L_0x01c5:
            r10 = r13.f;
            if (r10 == 0) goto L_0x01d0;
        L_0x01c9:
            r3 = r2 + 1;
            r10 = 13;
            r4[r2] = r10;
            r2 = r3;
        L_0x01d0:
            r3 = r2 + 1;
            r10 = 10;
            r4[r2] = r10;
            goto L_0x014e;
        L_0x01d8:
            r5 = r6 + 1;
            r10 = r14[r6];
            goto L_0x0180;
        L_0x01dd:
            r6 = r5 + 1;
            r10 = r14[r5];
            r5 = r6;
            goto L_0x018f;
        L_0x01e3:
            r10 = r13.e;
            if (r10 == 0) goto L_0x01fd;
        L_0x01e7:
            if (r3 <= 0) goto L_0x01fd;
        L_0x01e9:
            r10 = 19;
            if (r1 == r10) goto L_0x01fd;
        L_0x01ed:
            r10 = r13.f;
            if (r10 == 0) goto L_0x0201;
        L_0x01f1:
            r2 = r3 + 1;
            r10 = 13;
            r4[r3] = r10;
        L_0x01f7:
            r3 = r2 + 1;
            r10 = 10;
            r4[r2] = r10;
        L_0x01fd:
            r5 = r6;
            r2 = r3;
            goto L_0x014f;
        L_0x0201:
            r2 = r3;
            goto L_0x01f7;
        L_0x0203:
            r6 = r5;
            r3 = r2;
            goto L_0x0055;
            */
            throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.f.b.b.a(byte[], int):boolean");
        }
    }

    public static String a(byte[] input) {
        try {
            int i;
            int length = input.length;
            b bVar = new b();
            int i2 = (length / 3) * 4;
            if (!bVar.d) {
                switch (length % 3) {
                    case 1:
                        i2 += 2;
                        break;
                    case 2:
                        i2 += 3;
                        break;
                }
            } else if (length % 3 > 0) {
                i2 += 4;
            }
            if (!bVar.e || length <= 0) {
                i = i2;
            } else {
                i = ((bVar.f ? 2 : 1) * (((length - 1) / 57) + 1)) + i2;
            }
            bVar.a = new byte[i];
            bVar.a(input, length);
            if (bVar.b == i) {
                return new String(bVar.a, "US-ASCII");
            }
            throw new AssertionError();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
