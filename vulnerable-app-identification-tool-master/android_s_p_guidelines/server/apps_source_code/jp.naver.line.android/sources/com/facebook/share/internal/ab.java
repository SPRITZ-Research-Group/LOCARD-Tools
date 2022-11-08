package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.bj;
import com.facebook.share.model.ShareMessengerActionButton;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateElement;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.l;
import com.facebook.share.model.m;
import com.facebook.share.model.n;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.regex.Pattern;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ab {
    public static final Pattern a = Pattern.compile("^(.+)\\.(facebook\\.com)$");

    /* renamed from: com.facebook.share.internal.ab$1 */
    final /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[l.values().length];
        static final /* synthetic */ int[] c = new int[m.values().length];

        static {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.share.internal.ab.1.<clinit>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r0 = com.facebook.share.model.m.values();
            r0 = r0.length;
            r0 = new int[r0];
            c = r0;
            r0 = 1;
            r1 = c;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.facebook.share.model.m.VIDEO;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = com.facebook.share.model.l.values();
            r1 = r1.length;
            r1 = new int[r1];
            b = r1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = com.facebook.share.model.l.SQUARE;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0027 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            r1 = com.facebook.share.model.n.values();
            r1 = r1.length;
            r1 = new int[r1];
            a = r1;
            r1 = a;	 Catch:{ NoSuchFieldError -> 0x003a }
            r2 = com.facebook.share.model.n.WebviewHeightRatioCompact;	 Catch:{ NoSuchFieldError -> 0x003a }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x003a }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x003a }
        L_0x003a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0045 }
            r1 = com.facebook.share.model.n.WebviewHeightRatioTall;	 Catch:{ NoSuchFieldError -> 0x0045 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0045 }
            r2 = 2;	 Catch:{ NoSuchFieldError -> 0x0045 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0045 }
        L_0x0045:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.ab.1.<clinit>():void");
        }
    }

    public static void a(Bundle bundle, ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) throws JSONException {
        ShareMessengerGenericTemplateElement c = shareMessengerGenericTemplateContent.c();
        if (c.e() != null) {
            a(bundle, c.e(), false);
        } else if (c.d() != null) {
            a(bundle, c.d(), true);
        }
        bj.a(bundle, "IMAGE", c.c());
        bj.a(bundle, "PREVIEW_TYPE", "DEFAULT");
        bj.a(bundle, "TITLE", c.a());
        bj.a(bundle, "SUBTITLE", c.b());
        String str = "MESSENGER_PLATFORM_CONTENT";
        JSONArray jSONArray = new JSONArray();
        ShareMessengerGenericTemplateElement c2 = shareMessengerGenericTemplateContent.c();
        JSONObject put = new JSONObject().put(MessageBundle.TITLE_ENTRY, c2.a()).put("subtitle", c2.b()).put("image_url", bj.a(c2.c()));
        if (c2.e() != null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(a(c2.e(), false));
            put.put("buttons", jSONArray2);
        }
        if (c2.d() != null) {
            put.put("default_action", a(c2.d(), true));
        }
        jSONArray = jSONArray.put(put);
        JSONObject put2 = new JSONObject().put("template_type", "generic").put("sharable", shareMessengerGenericTemplateContent.a());
        String str2 = "image_aspect_ratio";
        l b = shareMessengerGenericTemplateContent.b();
        Object obj = (b == null || AnonymousClass1.b[b.ordinal()] != 1) ? "horizontal" : "square";
        bj.a(bundle, str, new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", put2.put(str2, obj).put("elements", jSONArray))));
    }

    private static void a(Bundle bundle, ShareMessengerActionButton shareMessengerActionButton, boolean z) throws JSONException {
        if (shareMessengerActionButton != null && (shareMessengerActionButton instanceof ShareMessengerURLActionButton)) {
            a(bundle, (ShareMessengerURLActionButton) shareMessengerActionButton, z);
        }
    }

    private static void a(Bundle bundle, ShareMessengerURLActionButton shareMessengerURLActionButton, boolean z) throws JSONException {
        String a;
        if (z) {
            a = bj.a(shareMessengerURLActionButton.b());
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(shareMessengerURLActionButton.a());
            stringBuilder.append(" - ");
            stringBuilder.append(bj.a(shareMessengerURLActionButton.b()));
            a = stringBuilder.toString();
        }
        bj.a(bundle, "TARGET_DISPLAY", a);
        bj.a(bundle, "ITEM_URL", shareMessengerURLActionButton.b());
    }

    private static JSONObject a(ShareMessengerActionButton shareMessengerActionButton, boolean z) throws JSONException {
        return shareMessengerActionButton instanceof ShareMessengerURLActionButton ? a((ShareMessengerURLActionButton) shareMessengerActionButton, z) : null;
    }

    private static JSONObject a(ShareMessengerURLActionButton shareMessengerURLActionButton, boolean z) throws JSONException {
        Object obj;
        JSONObject put = new JSONObject().put("type", "web_url");
        String str = MessageBundle.TITLE_ENTRY;
        if (z) {
            obj = null;
        } else {
            obj = shareMessengerURLActionButton.a();
        }
        return put.put(str, obj).put(ImagesContract.URL, bj.a(shareMessengerURLActionButton.b())).put("webview_height_ratio", a(shareMessengerURLActionButton.e())).put("messenger_extensions", shareMessengerURLActionButton.c()).put("fallback_url", bj.a(shareMessengerURLActionButton.d())).put("webview_share_button", a(shareMessengerURLActionButton));
    }

    private static String a(n nVar) {
        if (nVar == null) {
            return "full";
        }
        switch (nVar) {
            case WebviewHeightRatioCompact:
                return "compact";
            case WebviewHeightRatioTall:
                return "tall";
            default:
                return "full";
        }
    }

    private static String a(m mVar) {
        if (mVar == null) {
            return "image";
        }
        return AnonymousClass1.c[mVar.ordinal()] != 1 ? "image" : MimeTypes.BASE_TYPE_VIDEO;
    }

    private static String a(ShareMessengerURLActionButton shareMessengerURLActionButton) {
        return shareMessengerURLActionButton.f() ? "hide" : null;
    }

    public static void a(Bundle bundle, ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        a(bundle, shareMessengerOpenGraphMusicTemplateContent.b(), false);
        bj.a(bundle, "PREVIEW_TYPE", "OPEN_GRAPH");
        bj.a(bundle, "OPEN_GRAPH_URL", shareMessengerOpenGraphMusicTemplateContent.a());
        String str = "MESSENGER_PLATFORM_CONTENT";
        JSONArray jSONArray = new JSONArray();
        JSONObject put = new JSONObject().put(ImagesContract.URL, bj.a(shareMessengerOpenGraphMusicTemplateContent.a()));
        if (shareMessengerOpenGraphMusicTemplateContent.b() != null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(a(shareMessengerOpenGraphMusicTemplateContent.b(), false));
            put.put("buttons", jSONArray2);
        }
        bj.a(bundle, str, new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", new JSONObject().put("template_type", "open_graph").put("elements", jSONArray.put(put)))));
    }

    public static void a(Bundle bundle, ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        String host;
        a(bundle, shareMessengerMediaTemplateContent.d(), false);
        bj.a(bundle, "PREVIEW_TYPE", "DEFAULT");
        bj.a(bundle, "ATTACHMENT_ID", shareMessengerMediaTemplateContent.b());
        if (shareMessengerMediaTemplateContent.c() != null) {
            host = shareMessengerMediaTemplateContent.c().getHost();
            host = (bj.a(host) || !a.matcher(host).matches()) ? "IMAGE" : "uri";
            bj.a(bundle, host, shareMessengerMediaTemplateContent.c());
        }
        bj.a(bundle, "type", a(shareMessengerMediaTemplateContent.a()));
        host = "MESSENGER_PLATFORM_CONTENT";
        JSONArray jSONArray = new JSONArray();
        JSONObject put = new JSONObject().put("attachment_id", shareMessengerMediaTemplateContent.b()).put(ImagesContract.URL, bj.a(shareMessengerMediaTemplateContent.c())).put("media_type", a(shareMessengerMediaTemplateContent.a()));
        if (shareMessengerMediaTemplateContent.d() != null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(a(shareMessengerMediaTemplateContent.d(), false));
            put.put("buttons", jSONArray2);
        }
        bj.a(bundle, host, new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", new JSONObject().put("template_type", "media").put("elements", jSONArray.put(put)))));
    }
}
