package com.facebook.ads.internal.p.b.a;

import android.text.TextUtils;
import com.facebook.ads.internal.p.b.m;

public final class f implements c {
    public final String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        Object substring = (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
        String c = m.c(str);
        return TextUtils.isEmpty(substring) ? c : c + "." + substring;
    }
}
