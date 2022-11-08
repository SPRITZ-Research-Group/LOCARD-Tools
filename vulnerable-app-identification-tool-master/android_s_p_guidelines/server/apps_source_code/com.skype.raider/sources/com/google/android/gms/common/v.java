package com.google.android.gms.common;

import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.a;
import com.google.android.gms.common.util.i;

final class v extends u {
    private final String b;
    private final a c;
    private final boolean d;
    private final boolean e;

    private v(String str, a aVar, boolean z, boolean z2) {
        super(false, null, null);
        this.b = str;
        this.c = aVar;
        this.d = z;
        this.e = z2;
    }

    /* synthetic */ v(String str, a aVar, boolean z, boolean z2, byte b) {
        this(str, aVar, z, z2);
    }

    final String b() {
        String str = this.e ? "debug cert rejected" : "not whitelisted";
        String str2 = this.b;
        String a = i.a(a.a(Constants.SHA1).digest(this.c.a()));
        return new StringBuilder(((String.valueOf(str).length() + 44) + String.valueOf(str2).length()) + String.valueOf(a).length()).append(str).append(": pkg=").append(str2).append(", sha1=").append(a).append(", atk=").append(this.d).append(", ver=12451009.false").toString();
    }
}
