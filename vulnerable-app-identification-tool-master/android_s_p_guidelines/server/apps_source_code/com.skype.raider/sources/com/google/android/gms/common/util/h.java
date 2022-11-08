package com.google.android.gms.common.util;

import com.adjust.sdk.Constants;
import java.util.regex.Pattern;

public final class h {
    private static Pattern a = null;

    public static int a(int i) {
        return i == -1 ? -1 : i / Constants.ONE_SECOND;
    }
}
