package com.facebook.imageformat;

import com.facebook.common.internal.h;
import java.io.UnsupportedEncodingException;

public final class e {
    public static byte[] a(String value) {
        h.a((Object) value);
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    public static boolean a(byte[] byteArray, byte[] pattern) {
        h.a((Object) byteArray);
        h.a((Object) pattern);
        if (pattern.length > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    public static int a(byte[] byteArray, int byteArrayLen, byte[] pattern, int patternLen) {
        h.a((Object) byteArray);
        h.a((Object) pattern);
        if (patternLen > byteArrayLen) {
            return -1;
        }
        byte first = pattern[0];
        int max = byteArrayLen - patternLen;
        int i = 0;
        while (i <= max) {
            if (byteArray[i] != first) {
                do {
                    i++;
                    if (i > max) {
                        break;
                    }
                } while (byteArray[i] != first);
            }
            if (i <= max) {
                int j = i + 1;
                int end = (j + patternLen) - 1;
                int k = 1;
                while (j < end && byteArray[j] == pattern[k]) {
                    j++;
                    k++;
                }
                if (j == end) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
}
