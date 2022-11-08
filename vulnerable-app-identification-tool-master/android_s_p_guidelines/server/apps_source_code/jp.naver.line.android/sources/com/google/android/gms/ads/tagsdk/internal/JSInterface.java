package com.google.android.gms.ads.tagsdk.internal;

import android.webkit.JavascriptInterface;
import com.google.android.gms.common.annotation.KeepForSdk;

final class JSInterface {
    private final zze zzbt;

    JSInterface(zze zze) {
        this.zzbt = zze;
    }

    @JavascriptInterface
    @KeepForSdk
    public final String pm(String str, String str2) {
        return this.zzbt.zza(str, str2);
    }
}
