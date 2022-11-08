package com.facebook.imageutils;

import com.facebook.common.internal.h;
import java.io.IOException;
import java.io.InputStream;

public final class c {
    public static int a(int orientation) {
        return e.a(orientation);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(InputStream is) {
        int i = 1;
        try {
            int i2;
            int length;
            h.a((Object) is);
            while (d.a(is, 1, false) == 255) {
                i2 = 255;
                while (i2 == 255) {
                    i2 = d.a(is, 1, false);
                }
                if (i2 == 225) {
                    break;
                } else if (!(i2 == 216 || i2 == 1)) {
                    if (i2 == 217 || i2 == 218) {
                        i = 0;
                        break;
                    }
                    is.skip((long) (d.a(is, 2, false) - 2));
                }
            }
            i = 0;
            if (i != 0) {
                i = d.a(is, 2, false) - 2;
                if (i > 6) {
                    i2 = d.a(is, 4, false);
                    i -= 4;
                    int a = d.a(is, 2, false);
                    length = i - 2;
                    if (i2 == 1165519206) {
                    }
                }
            }
            length = 0;
            if (length == 0) {
                return 0;
            }
            return e.a(is, length);
        } catch (IOException e) {
            return 0;
        }
    }
}
