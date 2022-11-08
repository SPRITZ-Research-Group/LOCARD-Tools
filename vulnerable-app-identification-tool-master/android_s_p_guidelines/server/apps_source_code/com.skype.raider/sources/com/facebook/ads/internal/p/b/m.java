package com.facebook.ads.internal.p.b;

import com.adjust.sdk.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

public final class m {
    static String a(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Throwable e) {
            throw new RuntimeException("Error encoding url", e);
        }
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    static String b(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Throwable e) {
            throw new RuntimeException("Error decoding url", e);
        }
    }

    public static String c(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuffer.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
