package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class GmsLogger {
    private static final int zzef = 15;
    private static final String zzeg = null;
    private final String zzeh;
    private final String zzei;

    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, Integer.valueOf(23));
        this.zzeh = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzei = null;
        } else {
            this.zzei = str2;
        }
    }

    @KeepForSdk
    public final boolean canLogPii() {
        return false;
    }

    public GmsLogger(String str) {
        this(str, null);
    }

    @KeepForSdk
    public final boolean canLog(int i) {
        return Log.isLoggable(this.zzeh, i);
    }

    @KeepForSdk
    public final void d(String str, String str2) {
        if (canLog(3)) {
            zzh(str2);
        }
    }

    @KeepForSdk
    public final void d(String str, String str2, Throwable th) {
        if (canLog(3)) {
            zzh(str2);
        }
    }

    @KeepForSdk
    public final void v(String str, String str2) {
        if (canLog(2)) {
            zzh(str2);
        }
    }

    @KeepForSdk
    public final void v(String str, String str2, Throwable th) {
        if (canLog(2)) {
            zzh(str2);
        }
    }

    @KeepForSdk
    public final void i(String str, String str2) {
        if (canLog(4)) {
            Log.i(str, zzh(str2));
        }
    }

    @KeepForSdk
    public final void i(String str, String str2, Throwable th) {
        if (canLog(4)) {
            Log.i(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void w(String str, String str2) {
        if (canLog(5)) {
            Log.w(str, zzh(str2));
        }
    }

    @KeepForSdk
    public final void w(String str, String str2, Throwable th) {
        if (canLog(5)) {
            Log.w(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void wfmt(String str, String str2, Object... objArr) {
        if (canLog(5)) {
            Log.w(this.zzeh, zza(str2, objArr));
        }
    }

    @KeepForSdk
    public final void e(String str, String str2) {
        if (canLog(6)) {
            Log.e(str, zzh(str2));
        }
    }

    @KeepForSdk
    public final void e(String str, String str2, Throwable th) {
        if (canLog(6)) {
            Log.e(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void efmt(String str, String str2, Object... objArr) {
        if (canLog(6)) {
            Log.e(str, zza(str2, objArr));
        }
    }

    @KeepForSdk
    public final void wtf(String str, String str2, Throwable th) {
        if (canLog(7)) {
            Log.e(str, zzh(str2), th);
            Log.wtf(str, zzh(str2), th);
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2) {
        if (canLogPii()) {
            str = String.valueOf(str);
            String valueOf = String.valueOf(" PII_LOG");
            Log.i(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), zzh(str2));
        }
    }

    @KeepForSdk
    public final void pii(String str, String str2, Throwable th) {
        if (canLogPii()) {
            str = String.valueOf(str);
            String valueOf = String.valueOf(" PII_LOG");
            Log.i(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), zzh(str2), th);
        }
    }

    private final String zzh(String str) {
        if (this.zzei == null) {
            return str;
        }
        return this.zzei.concat(str);
    }

    private final String zza(String str, Object... objArr) {
        str = String.format(str, objArr);
        if (this.zzei == null) {
            return str;
        }
        return this.zzei.concat(str);
    }
}
