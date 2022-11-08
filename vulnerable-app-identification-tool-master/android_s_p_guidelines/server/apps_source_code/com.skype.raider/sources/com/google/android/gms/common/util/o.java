package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import java.util.regex.Pattern;

@VisibleForTesting
public final class o {
    private static final Pattern a = Pattern.compile("\\$\\{(.*?)\\}");

    public static boolean a(@Nullable String str) {
        return str == null || str.trim().isEmpty();
    }
}
