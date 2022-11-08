package com.google.android.gms.ads.tagsdk.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Looper;
import android.webkit.WebView;
import com.google.android.gms.internal.biski.zzac.zzb;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class zzg {
    private final zza zzbw;
    private final WeakReference<WebView> zzbz;
    private final List<Pattern> zzca;
    private final zzi zzcb;
    private ArrayDeque<String> zzcc = new ArrayDeque(8);

    zzg(WebView webView, List<String> list, zza zza, zzi zzi) {
        this.zzbz = new WeakReference(webView);
        this.zzbw = zza;
        this.zzcb = zzi;
        this.zzca = new ArrayList();
        for (String compile : list) {
            this.zzca.add(Pattern.compile(compile, 2));
        }
    }

    final boolean zza(String str, zzb zzb) {
        if (this.zzcb.zzd(str)) {
            return true;
        }
        Object obj = (str == null || "undefined".equals(str) || str.length() < 8) ? null : 1;
        if (obj != null && this.zzcc.contains(str)) {
            return true;
        }
        boolean zza = zza(zzb);
        if (zza && obj != null) {
            if (this.zzcc.size() >= 8) {
                this.zzcc.remove();
            }
            this.zzcc.add(str);
        }
        return zza;
    }

    final boolean zza(zzb zzb) {
        WebView webView = (WebView) this.zzbz.get();
        if (webView == null) {
            this.zzbw.zza(3, zzb);
            return false;
        }
        try {
            String url;
            CharSequence charSequence = null;
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                url = webView.getUrl();
            } else {
                webView.getClass();
                Object futureTask = new FutureTask(zzh.zza(webView));
                url = webView.post(futureTask) ? (String) futureTask.get(8500, TimeUnit.MICROSECONDS) : null;
            }
            if (url != null) {
                Uri parse = Uri.parse(url);
                Builder encodedFragment = parse.buildUpon().encodedFragment(null);
                if (parse.isHierarchical()) {
                    encodedFragment.clearQuery();
                }
                charSequence = encodedFragment.build().toString();
            }
            if (charSequence == null) {
                this.zzbw.zza(4, zzb);
                return false;
            }
            Object obj;
            for (Pattern matcher : this.zzca) {
                if (matcher.matcher(charSequence).matches()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                return true;
            }
            this.zzbw.zza(1, zzb);
            return false;
        } catch (Exception e) {
            this.zzbw.zza(2, zzb, e);
            return false;
        }
    }
}
