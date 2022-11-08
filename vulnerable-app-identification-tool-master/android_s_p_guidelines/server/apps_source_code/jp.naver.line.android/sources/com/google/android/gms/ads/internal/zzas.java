package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzasd;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.linecorp.yuki.effect.android.g;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzas {
    @VisibleForTesting
    static zzv<zzaqw> zza(zzxz zzxz, zzyc zzyc, zzac zzac) {
        return new zzax(zzxz, zzac, zzyc);
    }

    private static String zza(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzane.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    @com.google.android.gms.common.util.VisibleForTesting
    private static java.lang.String zza(com.google.android.gms.internal.ads.zzpw r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.ads.internal.zzas.zza(com.google.android.gms.internal.ads.zzpw):java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        if (r1 != 0) goto L_0x000a;
    L_0x0002:
        r1 = "Image is null. Returning empty string";
        com.google.android.gms.internal.ads.zzane.zzdk(r1);
        r1 = "";
        return r1;
    L_0x000a:
        r0 = r1.getUri();	 Catch:{ RemoteException -> 0x0015 }
        if (r0 == 0) goto L_0x001a;	 Catch:{ RemoteException -> 0x0015 }
    L_0x0010:
        r0 = r0.toString();	 Catch:{ RemoteException -> 0x0015 }
        return r0;
    L_0x0015:
        r0 = "Unable to get image uri. Trying data uri next";
        com.google.android.gms.internal.ads.zzane.zzdk(r0);
    L_0x001a:
        r1 = zzb(r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzas.zza(com.google.android.gms.internal.ads.zzpw):java.lang.String");
    }

    private static JSONObject zza(Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!(bundle == null || TextUtils.isEmpty(str))) {
            JSONObject jSONObject2 = new JSONObject(str);
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                if (bundle.containsKey(str2)) {
                    Object obj;
                    if ("image".equals(jSONObject2.getString(str2))) {
                        obj = bundle.get(str2);
                        if (obj instanceof Bitmap) {
                            obj = zza((Bitmap) obj);
                        } else {
                            str2 = "Invalid type. An image type extra should return a bitmap";
                            zzane.zzdk(str2);
                        }
                    } else if (bundle.get(str2) instanceof Bitmap) {
                        str2 = "Invalid asset type. Bitmap should be returned only for image type";
                        zzane.zzdk(str2);
                    } else {
                        obj = String.valueOf(bundle.get(str2));
                    }
                    jSONObject.put(str2, obj);
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzoo zzoo, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoo.getHeadline());
            jSONObject.put(TtmlNode.TAG_BODY, zzoo.getBody());
            jSONObject.put("call_to_action", zzoo.getCallToAction());
            jSONObject.put("price", zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzoo.getStarRating()));
            jSONObject.put("store", zzoo.getStore());
            jSONObject.put("icon", zza(zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoo.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzane.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzoq zzoq, String str, zzaqw zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzoq.getHeadline());
            jSONObject.put(TtmlNode.TAG_BODY, zzoq.getBody());
            jSONObject.put("call_to_action", zzoq.getCallToAction());
            jSONObject.put("advertiser", zzoq.getAdvertiser());
            jSONObject.put("logo", zza(zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoq.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", g.a);
            zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzane.zzc("Exception occurred when loading assets", e);
        }
    }

    public static boolean zza(zzaqw zzaqw, zzxe zzxe, CountDownLatch countDownLatch) {
        zzaqw zzaqw2 = zzaqw;
        zzxe zzxe2 = zzxe;
        CountDownLatch countDownLatch2 = countDownLatch;
        boolean z = false;
        try {
            String str;
            View view = zzaqw.getView();
            if (view == null) {
                str = "AdWebView is null";
            } else {
                view.setVisibility(4);
                List list = zzxe2.zzbtw.zzbsi;
                if (list == null || list.isEmpty()) {
                    str = "No template ids present in mediation response";
                } else {
                    String str2;
                    zzasc zzuf;
                    zzasd zzat;
                    zzaqw2.zza("/nativeExpressAssetsLoaded", new zzav(countDownLatch2));
                    zzaqw2.zza("/nativeExpressAssetsLoadingFailed", new zzaw(countDownLatch2));
                    zzxz zzmo = zzxe2.zzbtx.zzmo();
                    zzyc zzmp = zzxe2.zzbtx.zzmp();
                    View view2 = null;
                    String headline;
                    List images;
                    String body;
                    zzpw zzjz;
                    String callToAction;
                    if (list.contains("2") && zzmo != null) {
                        headline = zzmo.getHeadline();
                        images = zzmo.getImages();
                        body = zzmo.getBody();
                        zzjz = zzmo.zzjz();
                        callToAction = zzmo.getCallToAction();
                        double starRating = zzmo.getStarRating();
                        String store = zzmo.getStore();
                        String price = zzmo.getPrice();
                        Bundle extras = zzmo.getExtras();
                        if (zzmo.zzmw() != null) {
                            view2 = (View) ObjectWrapper.unwrap(zzmo.zzmw());
                        }
                        zzoo zzoo = new zzoo(headline, images, body, zzjz, callToAction, starRating, store, price, null, extras, null, view2, zzmo.zzke(), null);
                        str2 = zzxe2.zzbtw.zzbsh;
                        zzuf = zzaqw.zzuf();
                        zzat = new zzat(zzoo, str2, zzaqw2);
                    } else if (!list.contains(g.a) || zzmp == null) {
                        str = "No matching template id and mapper";
                    } else {
                        headline = zzmp.getHeadline();
                        images = zzmp.getImages();
                        body = zzmp.getBody();
                        zzjz = zzmp.zzkg();
                        callToAction = zzmp.getCallToAction();
                        String advertiser = zzmp.getAdvertiser();
                        Bundle extras2 = zzmp.getExtras();
                        if (zzmp.zzmw() != null) {
                            view2 = (View) ObjectWrapper.unwrap(zzmp.zzmw());
                        }
                        zzoq zzoq = new zzoq(headline, images, body, zzjz, callToAction, advertiser, null, extras2, null, view2, zzmp.zzke(), null);
                        str2 = zzxe2.zzbtw.zzbsh;
                        zzuf = zzaqw.zzuf();
                        zzat = new zzau(zzoq, str2, zzaqw2);
                    }
                    zzuf.zza(zzat);
                    str2 = zzxe2.zzbtw.zzbsf;
                    String str3 = zzxe2.zzbtw.zzbsg;
                    if (str3 != null) {
                        zzaqw.loadDataWithBaseURL(str3, str2, "text/html", "UTF-8", null);
                    } else {
                        zzaqw2.loadData(str2, "text/html", "UTF-8");
                    }
                    z = true;
                    if (!z) {
                        countDownLatch.countDown();
                    }
                    return z;
                }
            }
            zzane.zzdk(str);
        } catch (Throwable e) {
            zzane.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static java.lang.String zzb(com.google.android.gms.internal.ads.zzpw r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.ads.internal.zzas.zzb(com.google.android.gms.internal.ads.zzpw):java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = r1.zzjy();	 Catch:{ RemoteException -> 0x002b }
        if (r1 != 0) goto L_0x000e;	 Catch:{ RemoteException -> 0x002b }
    L_0x0006:
        r1 = "Drawable is null. Returning empty string";	 Catch:{ RemoteException -> 0x002b }
        com.google.android.gms.internal.ads.zzane.zzdk(r1);	 Catch:{ RemoteException -> 0x002b }
        r1 = "";	 Catch:{ RemoteException -> 0x002b }
        return r1;	 Catch:{ RemoteException -> 0x002b }
    L_0x000e:
        r1 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r1);	 Catch:{ RemoteException -> 0x002b }
        r1 = (android.graphics.drawable.Drawable) r1;	 Catch:{ RemoteException -> 0x002b }
        r0 = r1 instanceof android.graphics.drawable.BitmapDrawable;
        if (r0 != 0) goto L_0x0020;
    L_0x0018:
        r1 = "Drawable is not an instance of BitmapDrawable. Returning empty string";
        com.google.android.gms.internal.ads.zzane.zzdk(r1);
        r1 = "";
        return r1;
    L_0x0020:
        r1 = (android.graphics.drawable.BitmapDrawable) r1;
        r1 = r1.getBitmap();
        r1 = zza(r1);
        return r1;
    L_0x002b:
        r1 = "Unable to get drawable. Returning empty string";
        com.google.android.gms.internal.ads.zzane.zzdk(r1);
        r1 = "";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzas.zzb(com.google.android.gms.internal.ads.zzpw):java.lang.String");
    }

    private static zzpw zzd(Object obj) {
        return obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
    }

    private static void zzd(zzaqw zzaqw) {
        OnClickListener onClickListener = zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzaqw.getView());
        }
    }

    public static View zze(zzajh zzajh) {
        if (zzajh == null) {
            zzane.e("AdState is null");
            return null;
        } else if (zzf(zzajh) && zzajh.zzbyo != null) {
            return zzajh.zzbyo.getView();
        } else {
            try {
                IObjectWrapper view = zzajh.zzbtx != null ? zzajh.zzbtx.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                zzane.zzdk("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzane.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(zzajh zzajh) {
        return (zzajh == null || !zzajh.zzceq || zzajh.zzbtw == null || zzajh.zzbtw.zzbsf == null) ? false : true;
    }
}
