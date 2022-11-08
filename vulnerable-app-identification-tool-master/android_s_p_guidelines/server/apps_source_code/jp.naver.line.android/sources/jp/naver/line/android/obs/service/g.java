package jp.naver.line.android.obs.service;

import android.util.Log;
import android.util.Patterns;
import com.linecorp.square.chat.SquareChatUtils;
import defpackage.adcn;
import defpackage.cmi;
import defpackage.cmj;
import defpackage.ddu;
import defpackage.tew;
import defpackage.utp;
import java.util.Arrays;
import java.util.List;
import jp.naver.line.android.obs.f;
import jp.naver.line.android.util.cl;
import kotlin.Metadata;
import kotlin.k;
import org.apache.http.HttpHost;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002PQB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007J \u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0007J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007J \u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001eH\u0007J*\u0010\"\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001eH\u0007J\"\u0010&\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001eH\u0007J\u0010\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0007J\u0018\u0010)\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J,\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0004H\u0007J8\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0004H\u0007J(\u00107\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0007J\u0018\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\"\u0010=\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\b\u0010>\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020AH\u0002J\u0010\u0010B\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007J \u0010C\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\fH\u0007J\u0018\u0010E\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001eH\u0007J\u0012\u0010F\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020\u001eH\u0002J\u001a\u0010H\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,2\b\u0010/\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0004H\u0007J\u001a\u0010K\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\b\u0010>\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010L\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010M\u001a\u00020\u00042\u0006\u0010@\u001a\u00020AH\u0002J\u0010\u0010N\u001a\u00020\u00042\u0006\u0010@\u001a\u00020AH\u0002J\f\u0010O\u001a\u00020\u001e*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R2\u0010\n\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f\u0018\u00010\u000e0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Ljp/naver/line/android/obs/service/OBSUrlBuilder;", "", "()V", "OBS_CHANNEL_PREFIX", "", "OBS_HOME_PREFIX", "OBS_PROFILE_PREFIX", "OBS_SQUARE_PROFILE_PREFIX", "ORIGINAL_SUFFIX", "PREVIEW_SUFFIX", "PROFILE_MEDIA_CONTENT_TYPES", "", "Ljp/naver/line/android/obs/OBSContentType;", "kotlin.jvm.PlatformType", "", "SERVICE_NAME_SQUARE_TALK", "SERVICE_NAME_TALK", "STORAGE_ID_GROUP_PROFILE", "STORAGE_ID_MESSAGE", "STORAGE_ID_MYHOME", "STORAGE_ID_USER_PROFILE_IMAGE", "STORAGE_ID_USER_PROFILE_VIDEO", "TAG", "VIDEO_PROFILE_SUFFIX", "appendObsPopInfoToUrl", "url", "obsPopInfo", "buildGroupImageUrl", "groupId", "isPreview", "", "isChannel", "buildImageProfileUploadUrl", "mid", "buildMessageImageUrl", "messageId", "isSquare", "obsHost", "buildMessageOriginalImageUrl", "buildMessageYconUrl", "serverMessageId", "buildMyHomeMessageImageUrl", "buildOBS2Url", "type", "Ljp/naver/line/android/obs/service/OBSUrlBuilder$TYPE;", "serviceName", "objectStorageName", "objectId", "buildObsCopyUrl", "fromServiceCode", "fromSid", "fromOid", "targetServiceCode", "targetSid", "targetOid", "buildObsCopyUrlForGroupProfileImage", "serviceCode", "sid", "oid", "buildProfileCdnUrl", "obsHash", "buildProfileImageUrl", "picturePath", "buildUrl", "parameter", "Ljp/naver/line/android/obs/service/OBSUrlBuilder$ObsUrlParameter;", "buildVideoProfileUploadUrl", "buildVideoProfileVideoUrl", "obsContentType", "createFileMessageUrl", "getDefaultHost", "isRest", "getFileNameForType", "getObsCopyUrl", "chatId", "getProfileUrl", "getTalkObsContentInfoUrl", "getUrlPrefixForParameters", "getUrlSuffixForParameters", "isWebUrl", "ObsUrlParameter", "TYPE", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class g {
    public static final g a = new g();
    private static final List<f> b = Arrays.asList(new f[]{f.IMAGE_PROFILE, f.IMAGE_PROFILE_PREVIEW, f.VIDEO_PROFILE, f.VIDEO_PROFILE_SMALL, f.VIDEO_PROFILE_SJPG});

    private g() {
    }

    public static final String a(String str, boolean z, boolean z2) {
        return a(new h(z ? f.IMAGE_GROUP_PREVIEW : f.IMAGE_GROUP, str, null, z2, false, 44));
    }

    public static final String b(String str, boolean z, boolean z2) {
        return a(new h(z ? f.IMAGE_MESSAGE_PREVIEW : f.IMAGE_MESSAGE, str, null, false, z2, 28));
    }

    public static final String c(String str, boolean z, boolean z2) {
        return a(new h(z ? f.IMAGE_MESSAGE_PREVIEW : f.IMAGE_MESSAGE, str, null, false, z2, 24));
    }

    public static final String a(String str, boolean z) {
        return a(new h(f.IMAGE_MESSAGE_ORIGINAL, str, null, false, z, 24));
    }

    public static final String b(String str, boolean z) {
        return a(new h(f.FILE_MESSAGE, str, null, false, z, 28));
    }

    public static final String a(String str) {
        return a(new h(f.EXTIMAGE_YCON, str, null, false, false, 60));
    }

    public static final String a(String str, String str2, f fVar) {
        return a(new h(fVar, str, str2, false, false, 52));
    }

    public static final String b(String str) {
        return cl.b(a(false), "r/talk", "vp", str);
    }

    public static final String c(String str) {
        return cl.b(a(false), "r/talk", TtmlNode.TAG_P, str);
    }

    public static final String d(String str) {
        CharSequence[] charSequenceArr = new CharSequence[2];
        charSequenceArr[0] = a(false);
        charSequenceArr[1] = SquareChatUtils.a(str) ? "g2/m/copy.nhn" : "talk/m/copy.nhn";
        return cl.b(charSequenceArr);
    }

    public static final String a(String str, String str2, String str3, String str4, String str5, String str6) {
        StringBuilder stringBuilder = new StringBuilder("http://");
        stringBuilder.append(cl.b(ddu.b().c(), "r", str4, str5, str6, "copy.nhn?copyFrom=", str, str2, str3));
        return stringBuilder.toString();
    }

    public static final String a(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("http://");
        stringBuilder.append(cl.b(ddu.b().c(), "r/talk", "g", str, "copy.nhn?copyFrom=", str2, str3, str4));
        return stringBuilder.toString();
    }

    private static String a(h hVar) {
        String b = b(hVar);
        if ((((CharSequence) c(hVar)).length() == 0 ? 1 : null) != null) {
            return b;
        }
        return cl.b(b, c(hVar));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String b(h hVar) {
        if (b.contains(hVar.a())) {
            return b(hVar.b(), hVar.d());
        }
        String c = hVar.c();
        if (c != null) {
            if (!c.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                c = "http://".concat(String.valueOf(c));
            }
        }
        c = a(false);
        return c;
    }

    private static String c(h hVar) {
        CharSequence[] charSequenceArr;
        switch (j.a[hVar.a().ordinal()]) {
            case 1:
                return "";
            case 2:
                return "preview";
            case 3:
                return "vp";
            case 4:
                return "vp.small";
            case 5:
                return "vp.sjpg";
            case 6:
                charSequenceArr = new CharSequence[3];
                charSequenceArr[0] = hVar.e() ? "r/ch" : "r/talk";
                charSequenceArr[1] = "g";
                charSequenceArr[2] = hVar.b();
                return cl.b(charSequenceArr);
            case 7:
                charSequenceArr = new CharSequence[4];
                charSequenceArr[0] = hVar.e() ? "r/ch" : "r/talk";
                charSequenceArr[1] = "g";
                charSequenceArr[2] = hVar.b();
                charSequenceArr[3] = "preview";
                return cl.b(charSequenceArr);
            case 8:
                charSequenceArr = new CharSequence[3];
                charSequenceArr[0] = hVar.f() ? "r/g2" : "r/talk";
                charSequenceArr[1] = "m";
                charSequenceArr[2] = hVar.b();
                return cl.b(charSequenceArr);
            case 9:
                charSequenceArr = new CharSequence[4];
                charSequenceArr[0] = hVar.f() ? "r/g2" : "r/talk";
                charSequenceArr[1] = "m";
                charSequenceArr[2] = hVar.b();
                charSequenceArr[3] = "original";
                return cl.b(charSequenceArr);
            case 10:
                charSequenceArr = new CharSequence[4];
                charSequenceArr[0] = hVar.f() ? "r/g2" : "r/talk";
                charSequenceArr[1] = "m";
                charSequenceArr[2] = hVar.b();
                charSequenceArr[3] = "preview";
                return cl.b(charSequenceArr);
            case 11:
                return cl.b("r/myhome", "h", hVar.b());
            case 12:
                return cl.b("r/myhome", "h", hVar.b(), "preview");
            case 13:
                charSequenceArr = new CharSequence[3];
                charSequenceArr[0] = hVar.f() ? "r/g2" : "r/talk";
                charSequenceArr[1] = "m";
                charSequenceArr[2] = hVar.b();
                return cl.b(charSequenceArr);
            case 14:
                return cl.b("r/talk", "m", hVar.b(), "android");
            default:
                return "";
        }
    }

    private static String b(String str, String str2) {
        String p = utp.n().p();
        if (p == null) {
            p = "";
        }
        if ((((CharSequence) p).length() > 0 ? 1 : null) == null) {
            p = utp.n().b(tew.OBS_SERVER);
        }
        CharSequence charSequence = str2;
        Object obj = (charSequence == null || charSequence.length() == 0) ? 1 : null;
        if (obj != null) {
            str = cl.b(p, "r/talk", TtmlNode.TAG_P, str);
            Log.w("OBSUrlBuilder", "getProfileUrl : Request with MID! ".concat(String.valueOf(str)));
            return str;
        }
        return cl.b(p, charSequence);
    }

    public static final String a(i iVar, String str, String str2, String str3) {
        CharSequence charSequence = str;
        if ((charSequence.length() == 0 ? 1 : null) == null) {
            CharSequence charSequence2 = str2;
            if ((charSequence2.length() == 0 ? 1 : null) == null) {
                CharSequence[] charSequenceArr = new CharSequence[4];
                charSequenceArr[0] = a(iVar == i.UPLOAD);
                charSequenceArr[1] = charSequence;
                charSequenceArr[2] = charSequence2;
                switch (j.b[iVar.ordinal()]) {
                    case 1:
                        if (str3 == null || str3 == null) {
                            throw new IllegalArgumentException("UPLOAD type have to NonNull objectId.");
                        }
                    case 2:
                        str3 = "download.nhn";
                        break;
                    case 3:
                        str3 = "delete.nhn";
                        break;
                    case 4:
                        str3 = "object_info.nhn";
                        break;
                    case 5:
                        str3 = "upload_precheck.nhn";
                        break;
                    case 6:
                        str3 = "playback.obs";
                        break;
                    default:
                        throw new k();
                }
                charSequenceArr[3] = str3;
                return cl.b(charSequenceArr);
            }
        }
        throw new IllegalArgumentException("OBSUrlBuilder.buildUrl. key is null.");
    }

    private static String a(boolean z) {
        String d = ddu.b().d();
        if (!z) {
            return d;
        }
        return cl.b(d, "r");
    }

    public static final String c(String str, boolean z) {
        cmj cmj = cmi.a;
        cmi b = cmj.b(utp.n().p());
        StringBuilder stringBuilder = new StringBuilder("http://");
        stringBuilder.append(utp.n().c());
        String str2 = (String) b.a(stringBuilder.toString());
        if (z) {
            return cl.b(str2, str, "preview");
        }
        return cl.b(str2, str);
    }

    public static final String a(boolean z, String str) {
        return a(a(i.OBJECT_INFO, z ? "g2" : "talk", "m", str), str);
    }

    public static final java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.obs.service.g.a(java.lang.String, java.lang.String):java.lang.String. bs: []
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
        r0 = r5;
        r0 = (java.lang.CharSequence) r0;
        r1 = 0;
        r2 = 1;
        if (r0 == 0) goto L_0x0010;
    L_0x0007:
        r0 = r0.length();
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0010;
    L_0x000e:
        r0 = 0;
        goto L_0x0011;
    L_0x0010:
        r0 = 1;
    L_0x0011:
        if (r0 == 0) goto L_0x0014;
    L_0x0013:
        return r4;
    L_0x0014:
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x004f }
        r0.<init>(r4);	 Catch:{ MalformedURLException -> 0x004f }
        r0 = r0.getQuery();	 Catch:{ MalformedURLException -> 0x004f }
        r3 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x004f }
        r3.<init>(r4);	 Catch:{ MalformedURLException -> 0x004f }
        r0 = (java.lang.CharSequence) r0;	 Catch:{ MalformedURLException -> 0x004f }
        if (r0 == 0) goto L_0x002c;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x0026:
        r0 = r0.length();	 Catch:{ MalformedURLException -> 0x004f }
        if (r0 != 0) goto L_0x002d;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x002c:
        r1 = 1;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x002d:
        if (r1 == 0) goto L_0x0035;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x002f:
        r0 = "?";	 Catch:{ MalformedURLException -> 0x004f }
        r3.append(r0);	 Catch:{ MalformedURLException -> 0x004f }
        goto L_0x0042;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x0035:
        r0 = "&";	 Catch:{ MalformedURLException -> 0x004f }
        r0 = defpackage.addb.b(r4, r0);	 Catch:{ MalformedURLException -> 0x004f }
        if (r0 != 0) goto L_0x0042;	 Catch:{ MalformedURLException -> 0x004f }
    L_0x003d:
        r0 = "&";	 Catch:{ MalformedURLException -> 0x004f }
        r3.append(r0);	 Catch:{ MalformedURLException -> 0x004f }
    L_0x0042:
        r0 = "p=";	 Catch:{ MalformedURLException -> 0x004f }
        r3.append(r0);	 Catch:{ MalformedURLException -> 0x004f }
        r3.append(r5);	 Catch:{ MalformedURLException -> 0x004f }
        r5 = r3.toString();	 Catch:{ MalformedURLException -> 0x004f }
        return r5;
    L_0x004f:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.obs.service.g.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public static final String a(String str, String str2, boolean z) {
        if (str2 != null && new adcn(Patterns.WEB_URL).a(str2)) {
            return str2;
        }
        return a(new h(z ? f.IMAGE_PROFILE_PREVIEW : f.IMAGE_PROFILE, str, str2, false, false, 52));
    }

    public static final String a(i iVar, String str, String str2) {
        return a(iVar, str, str2, null);
    }
}
