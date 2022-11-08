package com.github.alinz.reactnativewebviewbridge;

import android.content.Context;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

final class a {
    private static final Pattern a = Pattern.compile("/{2,}");
    private String b;

    a(String assetRootPath) {
        this.b = assetRootPath;
    }

    @Nullable
    final InputStream a(@Nullable String url, Context context) throws IOException {
        String assetPath = null;
        if (url != null && url.toLowerCase().startsWith("https://")) {
            assetPath = this.b + '/' + a.matcher(url.substring(8)).replaceAll("/");
        }
        if (assetPath != null) {
            return context.getAssets().open(assetPath);
        }
        return null;
    }
}
