package com.a.a.a.b;

import com.skype.Defines;
import java.util.Arrays;

public final class a {
    static final int[] a;
    static final int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
    static final int[] f;
    static final int[] g;
    private static final char[] h;
    private static final byte[] i;

    static {
        int i;
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        h = toCharArray;
        int length = toCharArray.length;
        i = new byte[length];
        for (i = 0; i < length; i++) {
            i[i] = (byte) h[i];
        }
        int[] iArr = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        for (i = 0; i < 32; i++) {
            iArr[i] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        a = iArr;
        Object obj = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        System.arraycopy(a, 0, obj, 0, a.length);
        for (length = 128; length < Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE; length++) {
            i = (length & 224) == 192 ? 2 : (length & 240) == 224 ? 3 : (length & 248) == 240 ? 4 : -1;
            obj[length] = i;
        }
        b = obj;
        iArr = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        Arrays.fill(iArr, -1);
        for (i = 33; i < Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE; i++) {
            if (Character.isJavaIdentifierPart((char) i)) {
                iArr[i] = 0;
            }
        }
        iArr[64] = 0;
        iArr[35] = 0;
        iArr[42] = 0;
        iArr[45] = 0;
        iArr[43] = 0;
        c = iArr;
        Object obj2 = new int[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        System.arraycopy(c, 0, obj2, 0, c.length);
        Arrays.fill(obj2, 128, 128, 0);
        d = obj2;
        System.arraycopy(b, 128, e, 128, 128);
        Arrays.fill(e, 0, 32, -1);
        e[9] = 0;
        e[10] = 10;
        e[13] = 13;
        e[42] = 42;
        iArr = new int[128];
        for (i = 0; i < 32; i++) {
            iArr[i] = -1;
        }
        iArr[34] = 34;
        iArr[92] = 92;
        iArr[8] = 98;
        iArr[9] = 116;
        iArr[12] = 102;
        iArr[10] = 110;
        iArr[13] = 114;
        f = iArr;
        int[] iArr2 = new int[128];
        g = iArr2;
        Arrays.fill(iArr2, -1);
        for (i = 0; i < 10; i++) {
            g[i + 48] = i;
        }
        for (i = 0; i < 6; i++) {
            g[i + 97] = i + 10;
            g[i + 65] = i + 10;
        }
    }

    public static int[] a() {
        return f;
    }

    public static char[] b() {
        return (char[]) h.clone();
    }
}
