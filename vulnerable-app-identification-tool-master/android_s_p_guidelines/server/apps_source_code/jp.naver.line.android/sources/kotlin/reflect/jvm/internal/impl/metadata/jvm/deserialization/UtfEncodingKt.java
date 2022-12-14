package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.aa;

public final class UtfEncodingKt {
    public static final byte[] stringsToBytes(String[] strArr) {
        Object obj = null;
        int i = 0;
        for (String length : strArr) {
            i += length.length();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (String str : strArr) {
            int length2 = str.length() - 1;
            if (length2 >= 0) {
                int i3;
                int i4 = 0;
                while (true) {
                    i3 = i2 + 1;
                    bArr[i2] = (byte) str.charAt(i4);
                    if (i4 == length2) {
                        break;
                    }
                    i4++;
                    i2 = i3;
                }
                i2 = i3;
            }
        }
        if (i2 == bArr.length) {
            obj = 1;
        }
        if (!aa.a || obj != null) {
            return bArr;
        }
        throw new AssertionError("Should have reached the end");
    }
}
