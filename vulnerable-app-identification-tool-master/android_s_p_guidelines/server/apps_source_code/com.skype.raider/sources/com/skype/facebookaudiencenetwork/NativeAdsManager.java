package com.skype.facebookaudiencenetwork;

import com.facebook.ads.g;
import com.facebook.ads.h.b;
import com.facebook.ads.i;
import com.facebook.ads.j.a;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NativeAdsManager {
    private final ag a;
    private final String b;
    private final int c;
    private final Map<String, g> d = new ConcurrentHashMap();
    private final AtomicInteger e = new AtomicInteger(0);
    private a f;
    private boolean g = false;

    public class NativeAdListener implements i {
        final /* synthetic */ NativeAdsManager a;
        private final ag b;
        private final String c;
        private final int d;

        /* synthetic */ NativeAdListener(NativeAdsManager x0, ag x1, String x2, int x3, byte b) {
            this(x0, x1, x2, x3);
        }

        private NativeAdListener(NativeAdsManager this$0, ag reactContext, String adId, int adsToRequest) {
            this.a = this$0;
            this.b = reactContext;
            this.c = adId;
            this.d = adsToRequest;
        }

        public final void a(com.facebook.ads.a adError) {
            FLog.w("NativeAdsManager", "Failed to load ads. code=" + adError.a() + " message=" + adError.b());
            c();
            this.a.f.a(adError);
        }

        public final void a() {
            c();
            this.a.f.a();
        }

        public final void b() {
            FLog.d("NativeAdsManager", "onLoggingImpression");
            String str = "NativeAdWrapper_LoggingImpression_" + this.a.b + "_" + this.c;
            if (this.b != null && this.b.b()) {
                ((RCTNativeAppEventEmitter) this.b.a(RCTNativeAppEventEmitter.class)).emit(str, null);
            }
        }

        private void c() {
            if (this.a.e.incrementAndGet() >= this.d) {
                this.a.g = true;
            }
        }
    }

    public NativeAdsManager(ag application, String placementId, int adsToRequest) {
        this.a = application;
        this.b = placementId;
        this.c = Math.max(adsToRequest, 1);
    }

    public final void a(a listener) {
        this.f = listener;
    }

    public final void a(b mediaCacheFlag) {
        FLog.d("NativeAdsManager", "Load ads with mediaCacheFlag=" + mediaCacheFlag);
        for (int i = 0; i < this.c; i++) {
            String adGuid = UUID.randomUUID().toString();
            g nativeAd = new g(this.a, this.b);
            nativeAd.a((i) new NativeAdListener(this, this.a, adGuid, this.c, (byte) 0));
            nativeAd.a(mediaCacheFlag);
            this.d.put(adGuid, nativeAd);
        }
    }

    public final int a() {
        return this.d.size();
    }

    public final g a(String adId) {
        if (this.d.containsKey(adId)) {
            return (g) this.d.get(adId);
        }
        return null;
    }

    public final boolean b() {
        return this.g;
    }

    public final List<String> c() {
        List<String> availableAds = new ArrayList();
        for (Entry<String, g> entry : this.d.entrySet()) {
            if (!((g) entry.getValue()).e()) {
                availableAds.add(entry.getKey());
            }
        }
        return availableAds;
    }
}
