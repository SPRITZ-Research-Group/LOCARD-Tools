package com.facebook.common.d;

import com.facebook.common.internal.e;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public final class a {
    public static final Map<String, String> a = e.a("mkv", "video/x-matroska", "glb", "model/gltf-binary");

    public static boolean a(@Nullable String mimeType) {
        return mimeType != null && mimeType.startsWith("video/");
    }

    @Nullable
    public static String b(String path) {
        String extension;
        int lastIndexOf = path.lastIndexOf(46);
        if (lastIndexOf < 0 || lastIndexOf == path.length() - 1) {
            extension = null;
        } else {
            extension = path.substring(lastIndexOf + 1);
        }
        if (extension == null) {
            return null;
        }
        extension = extension.toLowerCase(Locale.US);
        String mimeType = b.a(extension);
        if (mimeType == null) {
            return (String) a.get(extension);
        }
        return mimeType;
    }
}
