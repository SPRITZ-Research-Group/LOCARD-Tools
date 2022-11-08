package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;

class e {
    private static final Class<?> a = e.class;

    private static class a {
        boolean a;
        int b;
        int c;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    e() {
    }

    public static int a(int orientation) {
        switch (orientation) {
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static int a(InputStream is, int length) throws IOException {
        int i;
        boolean z;
        a tiffHeader = new a();
        if (length <= 8) {
            length = 0;
        } else {
            tiffHeader.b = d.a(is, 4, false);
            i = length - 4;
            if (tiffHeader.b == 1229531648 || tiffHeader.b == 1296891946) {
                if (tiffHeader.b == 1229531648) {
                    z = true;
                } else {
                    z = false;
                }
                tiffHeader.a = z;
                tiffHeader.c = d.a(is, 4, tiffHeader.a);
                length = i - 4;
                if (tiffHeader.c < 8 || tiffHeader.c - 8 > length) {
                    FLog.e(a, "Invalid offset");
                    length = 0;
                }
            } else {
                FLog.e(a, "Invalid TIFF header");
                length = 0;
            }
        }
        int toSkip = tiffHeader.c - 8;
        if (length == 0 || toSkip > length) {
            return 0;
        }
        is.skip((long) toSkip);
        length -= toSkip;
        boolean z2 = tiffHeader.a;
        if (length >= 14) {
            int a = d.a(is, 2, z2);
            i = length - 2;
            while (true) {
                int i2 = i;
                i = a - 1;
                if (a <= 0 || i2 < 12) {
                    break;
                }
                length = i2 - 2;
                if (d.a(is, 2, z2) == 274) {
                    break;
                }
                is.skip(10);
                a = length - 10;
                i2 = a;
            }
        }
        length = 0;
        z = tiffHeader.a;
        if (length < 10 || d.a(is, 2, z) != 3 || d.a(is, 4, z) != 1) {
            return 0;
        }
        int a2 = d.a(is, 2, z);
        d.a(is, 2, z);
        return a2;
    }
}
