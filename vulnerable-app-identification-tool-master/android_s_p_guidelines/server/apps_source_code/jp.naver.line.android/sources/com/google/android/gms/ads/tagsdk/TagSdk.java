package com.google.android.gms.ads.tagsdk;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import com.google.android.gms.ads.tagsdk.internal.zzj;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

@KeepForSdk
public final class TagSdk {
    private static TagSdk zzbj;
    private final zzj zzbk;

    public static synchronized void init(Context context) {
        synchronized (TagSdk.class) {
            Preconditions.checkNotNull(context);
            if (zzbj == null) {
                zzbj = new TagSdk(zzj.zza(context));
            }
        }
    }

    public static TagSdk getInstance() {
        return zzbj;
    }

    private TagSdk(zzj zzj) {
        this.zzbk = zzj;
    }

    public final void prepareWebView(WebView webView, List<String> list) {
        Preconditions.checkMainThread("Must be called on the main application thread.");
        this.zzbk.zza(webView, (List) list);
    }

    public final Uri updateGoogleAdClickUrl(Uri uri, WebView webView) {
        return this.zzbk.zza(uri, webView);
    }

    public final boolean isGoogleAdClickUrl(Uri uri) {
        return zzj.zza(uri);
    }
}
