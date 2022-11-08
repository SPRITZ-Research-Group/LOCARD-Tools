package com.facebook.common.i;

import android.util.Base64;
import com.adjust.sdk.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class c {
    static final byte[] a = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};

    public static String a(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(Constants.SHA1);
            md.update(bytes, 0, bytes.length);
            return Base64.encodeToString(md.digest(), 11);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
