package com.facebook.common.d;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.e;
import java.util.Map;

public final class b {
    private static final MimeTypeMap a = MimeTypeMap.getSingleton();
    private static final Map<String, String> b = e.a("image/heif", "heif", "image/heic", "heic");
    private static final Map<String, String> c = e.a("heif", "image/heif", "heic", "image/heic");

    public static String a(String extension) {
        String result = (String) c.get(extension);
        return result != null ? result : a.getMimeTypeFromExtension(extension);
    }
}
