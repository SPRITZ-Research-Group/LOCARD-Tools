package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import com.facebook.internal.a;
import com.facebook.internal.at;
import com.facebook.internal.au;
import com.facebook.internal.av;
import com.facebook.internal.bj;
import com.facebook.internal.bl;
import com.facebook.internal.g;
import com.facebook.internal.h;
import com.facebook.l;
import com.facebook.n;
import com.facebook.p;
import com.facebook.s;
import com.facebook.share.e;
import com.facebook.share.model.CameraEffectTextures;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.google.android.gms.common.internal.ImagesContract;
import defpackage.amm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class am {

    /* renamed from: com.facebook.share.internal.am$5 */
    final class AnonymousClass5 implements bl<ShareMedia, Bundle> {
        final /* synthetic */ UUID a;
        final /* synthetic */ List b;

        AnonymousClass5(UUID uuid, List list) {
            this.a = uuid;
            this.b = list;
        }

        public final /* synthetic */ Object a(Object obj) {
            ShareMedia shareMedia = (ShareMedia) obj;
            au a = am.a(this.a, shareMedia);
            this.b.add(a);
            Bundle bundle = new Bundle();
            bundle.putString("type", shareMedia.b().name());
            bundle.putString("uri", a.a());
            return bundle;
        }
    }

    public static void a(final int i) {
        g.a(i, new h() {
            public final boolean a(int i, Intent intent) {
                return am.a(i, intent, new ah() {
                    final /* synthetic */ l a = null;

                    public final void a(a aVar) {
                        am.a(this.a);
                    }

                    public final void a(a aVar, n nVar) {
                        am.a(this.a, nVar);
                    }

                    public final void a(a aVar, Bundle bundle) {
                        if (bundle != null) {
                            String string;
                            if (bundle.containsKey("completionGesture")) {
                                string = bundle.getString("completionGesture");
                            } else {
                                string = bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
                            }
                            if (string == null || "post".equalsIgnoreCase(string)) {
                                if (bundle.containsKey("postId")) {
                                    string = bundle.getString("postId");
                                } else if (bundle.containsKey("com.facebook.platform.extra.POST_ID")) {
                                    string = bundle.getString("com.facebook.platform.extra.POST_ID");
                                } else {
                                    string = bundle.getString("post_id");
                                }
                                am.a(this.a, string);
                            } else if ("cancel".equalsIgnoreCase(string)) {
                                am.a(this.a);
                            } else {
                                am.a(this.a, new n("UnknownError"));
                            }
                        }
                    }
                });
            }
        });
    }

    public static List<String> a(SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            List a = sharePhotoContent.a();
            if (a != null) {
                Collection a2 = bj.a(a, new bl<SharePhoto, au>() {
                    public final /* bridge */ /* synthetic */ Object a(Object obj) {
                        return am.a(uuid, (SharePhoto) obj);
                    }
                });
                List<String> a3 = bj.a((List) a2, new bl<au, String>() {
                    public final /* bridge */ /* synthetic */ Object a(Object obj) {
                        return ((au) obj).a();
                    }
                });
                at.a(a2);
                return a3;
            }
        }
        return null;
    }

    public static Bundle a(ShareCameraEffectContent shareCameraEffectContent, UUID uuid) {
        if (shareCameraEffectContent != null) {
            CameraEffectTextures c = shareCameraEffectContent.c();
            if (c != null) {
                Bundle bundle = new Bundle();
                Collection arrayList = new ArrayList();
                for (String str : c.a()) {
                    au a = a(uuid, c.b(str), c.a(str));
                    arrayList.add(a);
                    bundle.putString(str, a.a());
                }
                at.a(arrayList);
                return bundle;
            }
        }
        return null;
    }

    public static JSONObject a(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        ShareOpenGraphAction a = shareOpenGraphContent.a();
        final Collection arrayList = new ArrayList();
        JSONObject a2 = ae.a(a, new af() {
            public final JSONObject a(SharePhoto sharePhoto) {
                au a = am.a(uuid, (ShareMedia) sharePhoto);
                if (a == null) {
                    return null;
                }
                arrayList.add(a);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(ImagesContract.URL, a.a());
                    if (sharePhoto.e()) {
                        jSONObject.put("user_generated", true);
                    }
                    return jSONObject;
                } catch (Throwable e) {
                    throw new n("Unable to attach images", e);
                }
            }
        });
        at.a(arrayList);
        if (shareOpenGraphContent.j() != null && bj.a(a2.optString("place"))) {
            a2.put("place", shareOpenGraphContent.j());
        }
        if (shareOpenGraphContent.i() != null) {
            Collection hashSet;
            JSONArray optJSONArray = a2.optJSONArray("tags");
            if (optJSONArray == null) {
                hashSet = new HashSet();
            } else {
                hashSet = bj.b(optJSONArray);
            }
            for (String add : shareOpenGraphContent.i()) {
                hashSet.add(add);
            }
            a2.put("tags", new JSONArray(hashSet));
        }
        return a2;
    }

    public static JSONObject a(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return ae.a(shareOpenGraphContent.a(), new af() {
            public final JSONObject a(SharePhoto sharePhoto) {
                Uri d = sharePhoto.d();
                if (bj.b(d)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(ImagesContract.URL, d.toString());
                        return jSONObject;
                    } catch (Throwable e) {
                        throw new n("Unable to attach images", e);
                    }
                }
                throw new n("Only web images may be used in OG objects shared via the web dialog");
            }
        });
    }

    private static JSONArray a(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = a((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = a((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    public static org.json.JSONObject a(org.json.JSONObject r9, boolean r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.share.internal.am.a(org.json.JSONObject, boolean):org.json.JSONObject. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        if (r9 != 0) goto L_0x0004;
    L_0x0002:
        r9 = 0;
        return r9;
    L_0x0004:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0086 }
        r0.<init>();	 Catch:{ JSONException -> 0x0086 }
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0086 }
        r1.<init>();	 Catch:{ JSONException -> 0x0086 }
        r2 = r9.names();	 Catch:{ JSONException -> 0x0086 }
        r3 = 0;	 Catch:{ JSONException -> 0x0086 }
    L_0x0013:
        r4 = r2.length();	 Catch:{ JSONException -> 0x0086 }
        if (r3 >= r4) goto L_0x007a;	 Catch:{ JSONException -> 0x0086 }
    L_0x0019:
        r4 = r2.getString(r3);	 Catch:{ JSONException -> 0x0086 }
        r5 = r9.get(r4);	 Catch:{ JSONException -> 0x0086 }
        r6 = r5 instanceof org.json.JSONObject;	 Catch:{ JSONException -> 0x0086 }
        r7 = 1;	 Catch:{ JSONException -> 0x0086 }
        if (r6 == 0) goto L_0x002d;	 Catch:{ JSONException -> 0x0086 }
    L_0x0026:
        r5 = (org.json.JSONObject) r5;	 Catch:{ JSONException -> 0x0086 }
        r5 = a(r5, r7);	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0037;	 Catch:{ JSONException -> 0x0086 }
    L_0x002d:
        r6 = r5 instanceof org.json.JSONArray;	 Catch:{ JSONException -> 0x0086 }
        if (r6 == 0) goto L_0x0037;	 Catch:{ JSONException -> 0x0086 }
    L_0x0031:
        r5 = (org.json.JSONArray) r5;	 Catch:{ JSONException -> 0x0086 }
        r5 = a(r5, r7);	 Catch:{ JSONException -> 0x0086 }
    L_0x0037:
        r6 = a(r4);	 Catch:{ JSONException -> 0x0086 }
        r7 = r6.first;	 Catch:{ JSONException -> 0x0086 }
        r7 = (java.lang.String) r7;	 Catch:{ JSONException -> 0x0086 }
        r6 = r6.second;	 Catch:{ JSONException -> 0x0086 }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x0086 }
        if (r10 == 0) goto L_0x0066;	 Catch:{ JSONException -> 0x0086 }
    L_0x0045:
        if (r7 == 0) goto L_0x0053;	 Catch:{ JSONException -> 0x0086 }
    L_0x0047:
        r8 = "fbsdk";	 Catch:{ JSONException -> 0x0086 }
        r8 = r7.equals(r8);	 Catch:{ JSONException -> 0x0086 }
        if (r8 == 0) goto L_0x0053;	 Catch:{ JSONException -> 0x0086 }
    L_0x004f:
        r0.put(r4, r5);	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0077;	 Catch:{ JSONException -> 0x0086 }
    L_0x0053:
        if (r7 == 0) goto L_0x0062;	 Catch:{ JSONException -> 0x0086 }
    L_0x0055:
        r4 = "og";	 Catch:{ JSONException -> 0x0086 }
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x0086 }
        if (r4 == 0) goto L_0x005e;	 Catch:{ JSONException -> 0x0086 }
    L_0x005d:
        goto L_0x0062;	 Catch:{ JSONException -> 0x0086 }
    L_0x005e:
        r1.put(r6, r5);	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0077;	 Catch:{ JSONException -> 0x0086 }
    L_0x0062:
        r0.put(r6, r5);	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0077;	 Catch:{ JSONException -> 0x0086 }
    L_0x0066:
        if (r7 == 0) goto L_0x0074;	 Catch:{ JSONException -> 0x0086 }
    L_0x0068:
        r8 = "fb";	 Catch:{ JSONException -> 0x0086 }
        r7 = r7.equals(r8);	 Catch:{ JSONException -> 0x0086 }
        if (r7 == 0) goto L_0x0074;	 Catch:{ JSONException -> 0x0086 }
    L_0x0070:
        r0.put(r4, r5);	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0077;	 Catch:{ JSONException -> 0x0086 }
    L_0x0074:
        r0.put(r6, r5);	 Catch:{ JSONException -> 0x0086 }
    L_0x0077:
        r3 = r3 + 1;	 Catch:{ JSONException -> 0x0086 }
        goto L_0x0013;	 Catch:{ JSONException -> 0x0086 }
    L_0x007a:
        r9 = r1.length();	 Catch:{ JSONException -> 0x0086 }
        if (r9 <= 0) goto L_0x0085;	 Catch:{ JSONException -> 0x0086 }
    L_0x0080:
        r9 = "data";	 Catch:{ JSONException -> 0x0086 }
        r0.put(r9, r1);	 Catch:{ JSONException -> 0x0086 }
    L_0x0085:
        return r0;
    L_0x0086:
        r9 = new com.facebook.n;
        r10 = "Failed to create json object from share content";
        r9.<init>(r10);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.am.a(org.json.JSONObject, boolean):org.json.JSONObject");
    }

    public static Pair<String, String> a(String str) {
        Object substring;
        Object str2;
        int indexOf = str2.indexOf(58);
        if (indexOf != -1) {
            int i = indexOf + 1;
            if (str2.length() > i) {
                substring = str2.substring(0, indexOf);
                str2 = str2.substring(i);
                return new Pair(substring, str2);
            }
        }
        substring = null;
        return new Pair(substring, str2);
    }

    private static au a(UUID uuid, Uri uri, Bitmap bitmap) {
        if (bitmap != null) {
            return at.a(uuid, bitmap);
        }
        return uri != null ? at.a(uuid, uri) : null;
    }

    static void a(l<e> lVar) {
        a("cancelled", null);
        if (lVar != null) {
            lVar.a();
        }
    }

    static void a(l<e> lVar, String str) {
        a("succeeded", null);
        if (lVar != null) {
            lVar.a(new e(str));
        }
    }

    static void a(l<e> lVar, n nVar) {
        a("error", nVar.getMessage());
        if (lVar != null) {
            lVar.a(nVar);
        }
    }

    private static void a(String str, String str2) {
        amm a = amm.a(s.f());
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_outcome", str);
        if (str2 != null) {
            bundle.putString("error_message", str2);
        }
        a.b("fb_share_dialog_result", bundle);
    }

    public static com.facebook.share.widget.e a(com.facebook.share.widget.e eVar, com.facebook.share.widget.e eVar2) {
        if (eVar == eVar2) {
            return eVar;
        }
        if (eVar == com.facebook.share.widget.e.UNKNOWN) {
            return eVar2;
        }
        return eVar2 == com.facebook.share.widget.e.UNKNOWN ? eVar : null;
    }

    public static boolean a(int i, Intent intent, ah ahVar) {
        a aVar;
        UUID a = av.a(intent);
        if (a == null) {
            aVar = null;
        } else {
            aVar = a.a(a, i);
        }
        if (aVar == null) {
            return false;
        }
        at.a(aVar.b());
        n a2 = av.a(av.e(intent));
        if (a2 == null) {
            ahVar.a(aVar, av.c(intent));
        } else if (a2 instanceof p) {
            ahVar.a(aVar);
        } else {
            ahVar.a(aVar, a2);
        }
        return true;
    }

    static /* synthetic */ au a(UUID uuid, ShareMedia shareMedia) {
        Uri d;
        Bitmap bitmap = null;
        if (shareMedia instanceof SharePhoto) {
            SharePhoto sharePhoto = (SharePhoto) shareMedia;
            bitmap = sharePhoto.c();
            d = sharePhoto.d();
        } else {
            d = shareMedia instanceof ShareVideo ? ((ShareVideo) shareMedia).c() : null;
        }
        return a(uuid, d, bitmap);
    }
}
