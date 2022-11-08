package jp.naver.line.android.urlscheme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import defpackage.acnr;
import defpackage.uoa;
import defpackage.uob;
import defpackage.vpr;
import defpackage.vps;
import defpackage.vpt;
import defpackage.vpu;
import defpackage.vpv;
import defpackage.vpx;
import defpackage.vpy;
import defpackage.vpz;
import defpackage.vqb;
import defpackage.vqc;
import defpackage.vqi;
import defpackage.vqj;
import defpackage.vqk;
import defpackage.vql;
import defpackage.vqm;
import defpackage.vqn;
import defpackage.vsi;
import defpackage.vsj;
import defpackage.vsl;
import defpackage.vsn;
import defpackage.vso;
import defpackage.vsq;
import defpackage.vss;
import defpackage.vst;
import defpackage.vsu;
import defpackage.vsv;
import defpackage.vsw;
import defpackage.vsy;
import defpackage.vsz;
import defpackage.vta;
import defpackage.vtc;
import java.util.List;
import jp.naver.line.android.urlscheme.service.oauth.OAuthWebLoginService;
import jp.naver.line.android.util.bg;
import jp.naver.line.android.util.bh;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0018\u0019\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0014J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J(\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Ljp/naver/line/android/urlscheme/LineSchemeServiceDispatcher;", "", "()V", "serviceList", "", "Ljp/naver/line/android/urlscheme/LineUrlSchemeService;", "serviceList$annotations", "getServiceList", "()Ljava/util/List;", "getUrlSchemeService", "uri", "Landroid/net/Uri;", "isSupportedUri", "", "post", "Ljp/naver/line/android/urlscheme/LineUrlSchemeServiceHandleResult;", "context", "Landroid/content/Context;", "url", "referrer", "Ljp/naver/line/android/urlscheme/SchemeServiceReferrer;", "", "postOrMaybeStartExternalActivity", "mayUseIab", "EnableType", "ServiceFilter", "ServiceInfo", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class e {
    public static final e a = new e();
    @SuppressLint({"VisibleForTests"})
    private static final List<j> b;

    static {
        r0 = new j[32];
        r0[0] = new i("msg/", new vql(), f.REGISTERED, new g[]{g.LINE, g.HTTP, g.MSG_TEXT_OLD});
        r0[1] = new i("ti/", new vtc(), f.REGISTERED, new g[]{g.LINE, g.HTTP, g.TICKET_OLD});
        r0[2] = new i("au/", new vpt(), f.REGISTERED, new g[]{g.LINE, g.HTTP, g.AUTH_QR_OLD});
        r0[3] = new i("run/", new vqb(), f.ALWAYS, new g[]{g.LINE, g.HTTP});
        r0[4] = new i("appauth?", new vps(), f.REGISTERED, new g[]{g.LINE, g.HTTP});
        r0[5] = new i("linecoin/", new vqj(), f.REGISTERED, new g[]{g.LINE, g.HTTP});
        r0[6] = new i("home/", new vqm(), f.REGISTERED, new g[]{g.LINE, g.HTTP});
        r0[7] = new i("call", new vqi(), f.REGISTERED, new g[]{g.LINE, g.HTTP});
        r0[8] = new i("sticker/", new vsu(), f.REGISTERED, new g[]{g.LINE, g.HTTP});
        r0[9] = new vsq();
        r0[10] = new vsj();
        r0[11] = new vpu();
        r0[12] = new vso();
        r0[13] = new OAuthWebLoginService();
        r0[14] = new vpy();
        r0[15] = new vsi();
        r0[16] = new vpr();
        r0[17] = new vqc();
        r0[18] = new vpv();
        r0[19] = new vst();
        r0[20] = new vsn();
        r0[21] = new vqk();
        r0[22] = new vpx();
        r0[23] = new vss();
        r0[24] = new vqn();
        r0[25] = new vpz();
        r0[26] = new vta();
        r0[27] = new vsw();
        r0[28] = new vsv();
        r0[29] = new vsz();
        r0[30] = new vsy();
        r0[31] = new vsl();
        b = acnr.b((Object[]) r0);
    }

    private e() {
    }

    @SuppressLint({"VisibleForTests"})
    public static k a(Context context, String str, SchemeServiceReferrer schemeServiceReferrer) throws a {
        if (str == null) {
            return m.d;
        }
        d dVar = d.a;
        Uri j = d.j(str);
        j b = b(j);
        if (b == null) {
            return m.d;
        }
        if (!b.a()) {
            uob uob = uoa.a;
            if (!uob.a().q()) {
                throw new a();
            }
        }
        return b.a(context, j, schemeServiceReferrer);
    }

    public static k a(Context context, Uri uri, SchemeServiceReferrer schemeServiceReferrer) throws a {
        if (uri != null) {
            k a = a(context, uri.toString(), schemeServiceReferrer);
            if (a != null) {
                return a;
            }
        }
        return m.d;
    }

    public static boolean a(Context context, Uri uri, boolean z, SchemeServiceReferrer schemeServiceReferrer) {
        if (uri == null) {
            return false;
        }
        context.startActivity(bg.a(context, uri, z ? bh.DEFAULT : bh.FORCE_EXTERNAL, schemeServiceReferrer, null, 48));
        return true;
    }

    private static j b(Uri uri) {
        for (j jVar : b) {
            if (jVar.a(uri)) {
                return jVar;
            }
        }
        return null;
    }

    @SuppressLint({"VisibleForTests"})
    public static boolean a(Uri uri) {
        d dVar = d.a;
        return b(d.j(uri.toString())) != null;
    }
}
