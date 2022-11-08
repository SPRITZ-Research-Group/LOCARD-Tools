package c;

import com.adjust.sdk.Constants;
import java.nio.charset.Charset;

final class v {
    public static final Charset a = Charset.forName(Constants.ENCODING);

    public static void a(long size, long offset, long byteCount) {
        if ((offset | byteCount) < 0 || offset > size || size - offset < byteCount) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(size), Long.valueOf(offset), Long.valueOf(byteCount)}));
        }
    }

    public static short a(short s) {
        int i = s & 65535;
        return (short) (((65280 & i) >>> 8) | ((i & 255) << 8));
    }

    public static int a(int i) {
        return ((((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8)) | ((65280 & i) << 8)) | ((i & 255) << 24);
    }

    public static boolean a(byte[] a, int aOffset, byte[] b, int bOffset, int byteCount) {
        for (int i = 0; i < byteCount; i++) {
            if (a[i + aOffset] != b[i + bOffset]) {
                return false;
            }
        }
        return true;
    }

    public static void a(Throwable t) {
        throw t;
    }
}
