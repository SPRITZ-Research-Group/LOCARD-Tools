package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.StreamRequest.StreamFormat;
import com.google.obf.ic;
import com.google.obf.ii.b;
import com.google.obf.in;
import com.google.obf.iq;
import com.google.obf.jk;
import defpackage.hs;
import java.util.List;
import java.util.Map;

public abstract class k {

    public interface a {
        a adContainerBounds(com.google.ads.interactivemedia.v3.impl.data.a.a aVar);

        a adTagParameters(Map<String, String> map);

        a adTagUrl(String str);

        a adsResponse(String str);

        a apiKey(String str);

        a assetKey(String str);

        a authToken(String str);

        k build();

        a companionSlots(Map<String, String> map);

        a contentDuration(Float f);

        a contentKeywords(List<String> list);

        a contentSourceId(String str);

        a contentTitle(String str);

        a env(String str);

        a extraParameters(Map<String, String> map);

        a format(String str);

        a idType(String str);

        a isAdContainerAttachedToWindow(Boolean bool);

        a isLat(String str);

        a isTv(Boolean bool);

        a liveStreamPrefetchSeconds(Float f);

        a marketAppInfo(b bVar);

        a msParameter(String str);

        a network(String str);

        a rdid(String str);

        a settings(ImaSdkSettings imaSdkSettings);

        a streamActivityMonitorId(String str);

        a useQAStreamBaseUrl(Boolean bool);

        a vastLoadTimeout(Float f);

        a videoId(String str);

        a videoPlayActivation(com.google.obf.in.a aVar);

        a videoPlayMuted(in.b bVar);
    }

    public abstract com.google.ads.interactivemedia.v3.impl.data.a.a adContainerBounds();

    public abstract Map<String, String> adTagParameters();

    public abstract String adTagUrl();

    public abstract String adsResponse();

    public abstract String apiKey();

    public abstract String assetKey();

    public abstract String authToken();

    public abstract Map<String, String> companionSlots();

    public abstract Float contentDuration();

    public abstract List<String> contentKeywords();

    public abstract String contentSourceId();

    public abstract String contentTitle();

    public abstract String env();

    public abstract Map<String, String> extraParameters();

    public abstract String format();

    public abstract String idType();

    public abstract Boolean isAdContainerAttachedToWindow();

    public abstract String isLat();

    public abstract Boolean isTv();

    public abstract Float liveStreamPrefetchSeconds();

    public abstract b marketAppInfo();

    public abstract String msParameter();

    public abstract String network();

    public abstract String rdid();

    public abstract ImaSdkSettings settings();

    public abstract String streamActivityMonitorId();

    public abstract Boolean useQAStreamBaseUrl();

    public abstract Float vastLoadTimeout();

    public abstract String videoId();

    public abstract com.google.obf.in.a videoPlayActivation();

    public abstract in.b videoPlayMuted();

    public static k create(AdsRequest adsRequest, String str, String str2, ImaSdkSettings imaSdkSettings, b bVar, boolean z) {
        String adTagUrl = adsRequest.getAdTagUrl();
        String adsResponse = adsRequest.getAdsResponse();
        Map extraParameters = adsRequest.getExtraParameters();
        in inVar = (in) adsRequest;
        com.google.obf.in.a a = inVar.a();
        in.b b = inVar.b();
        Float c = inVar.c();
        List d = inVar.d();
        String e = inVar.e();
        Float f = inVar.f();
        Float g = inVar.g();
        Map companionSlots = getCompanionSlots((ic) adsRequest.getAdDisplayContainer());
        View adContainer = adsRequest.getAdDisplayContainer().getAdContainer();
        boolean F = hs.F(adContainer);
        com.google.ads.interactivemedia.v3.impl.data.a.a createFromLocationOnScreen = com.google.ads.interactivemedia.v3.impl.data.a.a.createFromLocationOnScreen(adContainer);
        a adsResponse2 = builder().adTagUrl(adTagUrl).adsResponse(adsResponse);
        adsResponse = str;
        return adsResponse2.env(str).network(str2).extraParameters(extraParameters).settings(imaSdkSettings).videoPlayActivation(a).videoPlayMuted(b).contentDuration(c).contentKeywords(d).contentTitle(e).vastLoadTimeout(f).liveStreamPrefetchSeconds(g).companionSlots(companionSlots).marketAppInfo(bVar).isTv(Boolean.valueOf(z)).isAdContainerAttachedToWindow(Boolean.valueOf(F)).adContainerBounds(createFromLocationOnScreen).build();
    }

    public static k createFromStreamRequest(StreamRequest streamRequest, String str, String str2, ImaSdkSettings imaSdkSettings, b bVar, boolean z, String str3, String str4, String str5, String str6) {
        Map companionSlots = getCompanionSlots((jk) streamRequest.getStreamDisplayContainer());
        View adContainer = streamRequest.getStreamDisplayContainer().getAdContainer();
        boolean F = hs.F(adContainer);
        com.google.ads.interactivemedia.v3.impl.data.a.a createFromLocationOnScreen = com.google.ads.interactivemedia.v3.impl.data.a.a.createFromLocationOnScreen(adContainer);
        String str7 = "hls";
        if (streamRequest.getFormat() == StreamFormat.DASH) {
            str7 = "dash";
        }
        return builder().assetKey(streamRequest.getAssetKey()).authToken(streamRequest.getAuthToken()).contentSourceId(streamRequest.getContentSourceId()).videoId(streamRequest.getVideoId()).apiKey(streamRequest.getApiKey()).adTagParameters(streamRequest.getAdTagParameters()).env(str).network(str2).settings(imaSdkSettings).companionSlots(companionSlots).marketAppInfo(bVar).isTv(Boolean.valueOf(z)).msParameter(str3).isAdContainerAttachedToWindow(Boolean.valueOf(F)).adContainerBounds(createFromLocationOnScreen).streamActivityMonitorId(streamRequest.getStreamActivityMonitorId()).format(str7).rdid(str4).idType(str5).isLat(str6).useQAStreamBaseUrl(streamRequest.getUseQAStreamBaseUrl()).build();
    }

    public static a builder() {
        return new a();
    }

    private static Map<String, String> getCompanionSlots(iq iqVar) {
        Map a = iqVar.a();
        if (a == null || a.isEmpty()) {
            return null;
        }
        com.google.obf.lf.a aVar = new com.google.obf.lf.a();
        for (String str : a.keySet()) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) a.get(str);
            int width = companionAdSlot.getWidth();
            int height = companionAdSlot.getHeight();
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(width);
            stringBuilder.append("x");
            stringBuilder.append(height);
            aVar.a(str, stringBuilder.toString());
        }
        return aVar.a();
    }
}
