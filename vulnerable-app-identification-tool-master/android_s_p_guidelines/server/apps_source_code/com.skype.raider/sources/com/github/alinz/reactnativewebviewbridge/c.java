package com.github.alinz.reactnativewebviewbridge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public enum c {
    JAVASCRIPT("js", "application/javascript"),
    PNG("png", "image/png"),
    SVG("svg", "image/svg+xml"),
    CSS("css", "text/css"),
    FONT("woff", "application/x-font-woff");
    
    private String f;
    private String g;

    private c(String extension, String mimetype) {
        this.f = extension;
        this.g = mimetype;
    }

    @NonNull
    public final String toString() {
        return this.g;
    }

    @Nullable
    public static c a(@Nullable String fileName) {
        if (fileName == null) {
            return null;
        }
        int i;
        String extension = "";
        if (!TextUtils.isEmpty(fileName)) {
            if (TextUtils.isEmpty(fileName)) {
                i = -1;
            } else {
                i = fileName.lastIndexOf(46);
                if (fileName.lastIndexOf(47) > i) {
                    i = -1;
                }
            }
            if (i != -1) {
                extension = fileName.substring(i + 1, fileName.length());
            }
        }
        for (c type : values()) {
            if (extension.equals(type.f)) {
                return type;
            }
        }
        return null;
    }
}
