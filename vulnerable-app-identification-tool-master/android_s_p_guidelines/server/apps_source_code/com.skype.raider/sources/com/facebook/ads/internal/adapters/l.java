package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.q.a.d;
import com.facebook.ads.internal.r.a;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAdView;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l extends ai implements ac {
    private static final String a = l.class.getSimpleName();
    private View b;
    private NativeAd c;
    private aj d;
    private NativeAdView e;
    private View f;
    private boolean g;
    private Uri h;
    private Uri i;
    private String j;
    private String k;
    private String l;
    private String m;

    private static void a(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    public final List<e> A() {
        return null;
    }

    public final int B() {
        return 0;
    }

    public final int C() {
        return 0;
    }

    public final n D() {
        return n.ADMOB;
    }

    public final void a(int i) {
    }

    public final void a(final Context context, aj ajVar, c cVar, Map<String, Object> map, e.c cVar2) {
        d.a(context, af.a(n.ADMOB) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        Object optString = jSONObject.optString("ad_unit_id");
        JSONArray optJSONArray = jSONObject.optJSONArray("creative_types");
        Object obj = null;
        Object obj2 = null;
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i = 0;
            while (i < length) {
                try {
                    String string = optJSONArray.getString(i);
                    if (string != null) {
                        Object obj3 = -1;
                        switch (string.hashCode()) {
                            case 704091517:
                                if (string.equals("app_install")) {
                                    obj3 = null;
                                    break;
                                }
                                break;
                            case 883765328:
                                if (string.equals("page_post")) {
                                    obj3 = 1;
                                    break;
                                }
                                break;
                        }
                        switch (obj3) {
                            case null:
                                obj = 1;
                                break;
                            case 1:
                                obj2 = 1;
                                break;
                            default:
                                break;
                        }
                    }
                    i++;
                } catch (JSONException e) {
                    d.a(context, af.a(n.ADMOB) + " AN server error");
                    ajVar.a(this, com.facebook.ads.internal.r.c.a(a.SERVER_ERROR, "Server Error"));
                    return;
                }
            }
        }
        if (TextUtils.isEmpty(optString) || (obj == null && obj2 == null)) {
            d.a(context, af.a(n.ADMOB) + " AN server error");
            ajVar.a(this, com.facebook.ads.internal.r.c.a(a.SERVER_ERROR, "Server Error"));
            return;
        }
        this.d = ajVar;
        Builder builder = new Builder(context, optString);
        if (obj != null) {
            builder.forAppInstallAd(new OnAppInstallAdLoadedListener(this) {
                final /* synthetic */ l b;
            });
        }
        if (obj2 != null) {
            builder.forContentAd(new OnContentAdLoadedListener(this) {
                final /* synthetic */ l b;
            });
        }
        builder.withAdListener(new AdListener(this) {
            final /* synthetic */ l b;
        }).withNativeAdOptions(new NativeAdOptions.Builder().setReturnUrlsForImageAssets(true).build()).build().loadAd(new AdRequest.Builder().build());
    }

    public final void a(View view, List<View> list) {
        this.b = view;
        if (g_() && view != null) {
            ViewGroup viewGroup = null;
            int i = -1;
            while (true) {
                int i2 = i;
                ViewGroup viewGroup2 = viewGroup;
                ViewGroup viewGroup3 = (ViewGroup) view.getParent();
                if (viewGroup3 != null) {
                    if (viewGroup3 instanceof NativeAdView) {
                        viewGroup = (ViewGroup) viewGroup3.getParent();
                        if (viewGroup != null) {
                            int indexOfChild = viewGroup.indexOfChild(viewGroup3);
                            viewGroup3.removeView(view);
                            viewGroup.removeView(viewGroup3);
                            viewGroup.addView(view, indexOfChild);
                            i = i2;
                            viewGroup = viewGroup2;
                        } else {
                            return;
                        }
                    }
                    viewGroup = viewGroup3;
                    i = viewGroup3.indexOfChild(view);
                    if (viewGroup != null) {
                        NativeAdView nativeContentAdView = this.c instanceof NativeContentAd ? new NativeContentAdView(view.getContext()) : new NativeAppInstallAdView(view.getContext());
                        if (view instanceof ViewGroup) {
                            nativeContentAdView.setLayoutParams(view.getLayoutParams());
                        }
                        a(view);
                        nativeContentAdView.addView(view);
                        viewGroup.removeView(nativeContentAdView);
                        viewGroup.addView(nativeContentAdView, i);
                        this.e = nativeContentAdView;
                        this.e.setNativeAd(this.c);
                        this.f = new View(view.getContext());
                        this.e.addView(this.f);
                        this.f.setVisibility(8);
                        if (this.e instanceof NativeContentAdView) {
                            ((NativeContentAdView) this.e).setCallToActionView(this.f);
                        } else if (this.e instanceof NativeAppInstallAdView) {
                            ((NativeAppInstallAdView) this.e).setCallToActionView(this.f);
                        }
                        OnClickListener anonymousClass4 = new OnClickListener(this) {
                            final /* synthetic */ l a;

                            {
                                this.a = r1;
                            }

                            public final void onClick(View view) {
                                this.a.f.performClick();
                            }
                        };
                        for (View onClickListener : list) {
                            onClickListener.setOnClickListener(anonymousClass4);
                        }
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public final void a(aj ajVar) {
        this.d = ajVar;
    }

    public final void a(Map<String, String> map) {
        if (g_() && this.d != null) {
            this.d.a();
        }
    }

    public final void b(Map<String, String> map) {
    }

    public final String c() {
        return null;
    }

    public final void e() {
        f_();
        this.d = null;
        this.c = null;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public final boolean f() {
        return false;
    }

    public final void f_() {
        a(this.f);
        this.f = null;
        if (this.b != null) {
            View view = (ViewGroup) this.b.getParent();
            if ((view instanceof NativeContentAdView) || (view instanceof NativeAppInstallAdView)) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    int indexOfChild = viewGroup.indexOfChild(view);
                    a(this.b);
                    a(view);
                    viewGroup.addView(this.b, indexOfChild);
                }
            }
            this.b = null;
        }
        this.e = null;
    }

    public final boolean g() {
        return false;
    }

    public final boolean g_() {
        return this.g && this.c != null;
    }

    public final boolean h() {
        return false;
    }

    public final int i() {
        return 0;
    }

    public final int j() {
        return 0;
    }

    public final int k() {
        return 0;
    }

    public final f l() {
        return (!g_() || this.i == null) ? null : new f(this.i.toString(), 50, 50);
    }

    public final f m() {
        return (!g_() || this.h == null) ? null : new f(this.h.toString(), 1200, 600);
    }

    public final String n() {
        return null;
    }

    public final String o() {
        return this.k;
    }

    public final String p() {
        return this.l;
    }

    public final String q() {
        return this.m;
    }

    public final String r() {
        return null;
    }

    public final f s() {
        return null;
    }

    public final String t() {
        return null;
    }

    public final String u() {
        return null;
    }

    public final String v() {
        return null;
    }

    public final String w() {
        return null;
    }

    public final com.facebook.ads.internal.n.l x() {
        return com.facebook.ads.internal.n.l.DEFAULT;
    }

    public final int y() {
        return 0;
    }

    public final String z() {
        return null;
    }
}
