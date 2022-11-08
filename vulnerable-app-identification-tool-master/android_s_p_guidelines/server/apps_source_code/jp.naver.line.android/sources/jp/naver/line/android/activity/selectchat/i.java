package jp.naver.line.android.activity.selectchat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Parcelable;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.common.Scopes;
import com.linecorp.line.album.data.model.ShareAlbumContent;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acpu;
import defpackage.acqr;
import defpackage.acrx;
import defpackage.acry;
import defpackage.acrz;
import defpackage.acso;
import defpackage.acuc;
import defpackage.adcn;
import defpackage.addb;
import defpackage.addc;
import defpackage.fex;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jp.naver.gallery.android.media.MediaItem;
import jp.naver.line.android.common.access.keep.KeepContentShareModel;
import jp.naver.line.android.model.Location;
import jp.naver.line.android.obs.model.OBSCopyInfo;
import jp.naver.line.android.util.af;
import jp.naver.myhome.android.activity.mememaker.e;
import kotlin.Metadata;
import org.apache.http.protocol.HTTP;
import org.bouncycastle.i18n.MessageBundle;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J*\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J#\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0007¢\u0006\u0002\u0010\u0018J6\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0007J\u0018\u0010 \u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"H\u0007J8\u0010#\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)H\u0007J \u0010*\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020-H\u0007J\u0018\u0010.\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u0006H\u0007J(\u00100\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u0013H\u0007J \u00104\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0006H\u0007J\u0018\u00107\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u00108\u001a\u00020\u0006H\u0007J \u00109\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H\u0007J$\u0010<\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0018\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u000fH\u0002J0\u0010@\u001a\b\u0012\u0004\u0012\u00020\u000f0A2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0A2\b\u0010C\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010D\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010E\u001a\u00020\u00132\u0006\u0010F\u001a\u00020G2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010H\u001a\u00020\u00132\b\u0010?\u001a\u0004\u0018\u00010\u000fH\u0002J\u001a\u0010I\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010J\u001a\u00020KH\u0002J \u0010L\u001a\u00020M2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010N\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010\u001bH\u0007J\u0010\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u0006H\u0007J\u0012\u0010Q\u001a\u0004\u0018\u00010\u00062\u0006\u0010R\u001a\u00020SH\u0002J\u0016\u0010T\u001a\u0004\u0018\u00010\u0006*\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010U\u001a\u0004\u0018\u00010\u0006*\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010V\u001a\u0004\u0018\u00010\u0006*\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010W\u001a\u0004\u0018\u00010\u0006*\u00020\u000fH\u0002J5\u0010X\u001a\u0004\u0018\u0001HY\"\u0004\b\u0000\u0010Y*\u00020G2\u0006\u0010?\u001a\u00020\u000f2\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u0002HY0[H\u0002¢\u0006\u0002\u0010\\J\u001c\u0010]\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\b\u0010^\u001a\u0004\u0018\u00010\u0006J\u0012\u0010_\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010`\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\t\u001a\u00020\nJ\"\u0010a\u001a\b\u0012\u0004\u0012\u00020\u000f0A*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0A2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Ljp/naver/line/android/activity/selectchat/SelectChatActivityIntentUtility;", "", "()V", "MIME_TYPE_URI_REGEX", "Lkotlin/text/Regex;", "TAG", "", "createAlbumImageSharingIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "albumContent", "Lcom/linecorp/line/album/data/model/ShareAlbumContent;", "createInternalImageSharingIntent", "imageUri", "Landroid/net/Uri;", "obsCopyInfo", "Ljp/naver/line/android/obs/model/OBSCopyInfo;", "isOriginalImage", "", "createKeepIntent", "contents", "", "Ljp/naver/line/android/common/access/keep/KeepContentShareModel;", "(Landroid/content/Context;[Ljp/naver/line/android/common/access/keep/KeepContentShareModel;)Landroid/content/Intent;", "createLCSIntent", "targets", "Ljava/util/ArrayList;", "title", "limit", "", "callbackId", "createLocationSharingIntent", "location", "Ljp/naver/line/android/model/Location;", "createMemeContentSharingIntent", "sourceId", "sourceVersion", "enrollPopular", "caption", "mediaView", "Ljp/naver/myhome/android/activity/mememaker/MediaView;", "createMessageForwardingIntent", "sourceChatId", "forwardLocalMessageIds", "", "createOfficialAccountSharingIntent", "id", "createPlainTextIntent", "message", "fromScheme", "sendMessageDirectly", "createPostSharingIntent", "userMid", "postId", "createProfileSharingIntent", "mid", "createTimelineContentSharingIntent", "contentId", "contentType", "createVideoSharingIntent", "videoUri", "existsImageFile", "uri", "getImageUriListFromUriListWithMimeType", "", "originalUriList", "mimeType", "isGoogleAssistScreenShot", "isImageMimeType", "contentResolver", "Landroid/content/ContentResolver;", "isLocalImageFile", "maybeGetSharingIntent", "mediaItem", "Ljp/naver/gallery/android/media/MediaItem;", "startSelectChatActivityIfMediaItemExisted", "", "mediaItems", "toValidImagePath", "imagePath", "transformToFilename", "cursor", "Landroid/database/Cursor;", "getFileName", "getMimeType", "getMimeTypeFromContentResolver", "getMimeTypeFromUriParameter", "queryMimeType", "T", "run", "Lkotlin/Function1;", "(Landroid/content/ContentResolver;Landroid/net/Uri;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toTempStorageFileUri", "fallbackFileExt", "toTempStorageFileUriIfGoogleAssistScreenShot", "toTempStorageFileUriWithKeepFilename", "toValidImageUris", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class i {
    public static final i a = new i();
    private static final adcn b = new adcn("[?&]mimeType=([a-zA-Z0-9%]*)(?:&|$)");

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "cursor", "Landroid/database/Cursor;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrz implements acqr<Cursor, Boolean> {
        public static final b a = new b();

        b() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            Cursor cursor = (Cursor) obj;
            int columnIndex = cursor.getColumnIndex("mime_type");
            boolean z = false;
            if (columnIndex >= 0 && cursor.moveToFirst()) {
                String string = cursor.getString(columnIndex);
                if (string != null ? addc.e((CharSequence) string, (CharSequence) "image") : false) {
                    z = true;
                }
            }
            return Boolean.valueOf(z);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/database/Cursor;", "Lkotlin/ParameterName;", "name", "cursor", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrx implements acqr<Cursor, String> {
        a(i iVar) {
            super(1, iVar);
        }

        public final String getName() {
            return "transformToFilename";
        }

        public final acuc getOwner() {
            return acso.a(i.class);
        }

        public final String getSignature() {
            return "transformToFilename(Landroid/database/Cursor;)Ljava/lang/String;";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return i.a((Cursor) obj);
        }
    }

    private i() {
    }

    private static List<Uri> a(List<? extends Uri> list, Context context) {
        Object next;
        Collection arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            next = it.next();
            Uri uri = (Uri) next;
            if (uri != null) {
                str = uri.getPath();
            }
            if (fex.a(str)) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Object next2 : (List) arrayList) {
            if (next2 != null) {
                boolean z = true;
                if (!(addb.a(next2.getScheme(), AppLovinEventTypes.USER_VIEWED_CONTENT, true) && addc.e((CharSequence) next2.toString(), (CharSequence) "ScreenshotProvider/ScreenAssistScreenshots"))) {
                    z = false;
                }
                if (z) {
                    next2 = a((Uri) next2, context, ".png");
                }
            } else {
                next2 = null;
            }
            if (next2 != null) {
                arrayList2.add(next2);
            }
        }
        return (List) arrayList2;
    }

    private static boolean a(android.content.ContentResolver r2, android.net.Uri r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.selectchat.i.a(android.content.ContentResolver, android.net.Uri):boolean. bs: []
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
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = jp.naver.line.android.activity.selectchat.i.b.a;	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
        r1 = (defpackage.acqr) r1;	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
        r2 = a(r2, r3, r1);	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
        if (r2 == 0) goto L_0x0015;	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
    L_0x0010:
        r2 = r2.booleanValue();	 Catch:{ IllegalArgumentException -> 0x0016, IllegalArgumentException -> 0x0016 }
        return r2;
    L_0x0015:
        return r0;
    L_0x0016:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.selectchat.i.a(android.content.ContentResolver, android.net.Uri):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T a(ContentResolver contentResolver, Uri uri, acqr<? super Cursor, ? extends T> acqr) {
        Cursor query = contentResolver.query(uri, new String[]{"mime_type"}, null, null, null);
        if (query == null) {
            return null;
        }
        Closeable closeable = query;
        T invoke = acqr.invoke(closeable);
        acpu.a(closeable, null);
        return invoke;
    }

    public final android.net.Uri a(android.net.Uri r13, android.content.Context r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.selectchat.i.a(android.net.Uri, android.content.Context):android.net.Uri. bs: []
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
        r12 = this;
        r0 = 0;
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00dc }
        r2 = "tmp_";	 Catch:{ IOException -> 0x00dc }
        r1.<init>(r2);	 Catch:{ IOException -> 0x00dc }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x00dc }
        r1.append(r2);	 Catch:{ IOException -> 0x00dc }
        r1 = r1.toString();	 Catch:{ IOException -> 0x00dc }
        r2 = "file";	 Catch:{ IOException -> 0x00dc }
        r3 = r13.getScheme();	 Catch:{ IOException -> 0x00dc }
        r4 = 1;	 Catch:{ IOException -> 0x00dc }
        r2 = defpackage.addb.a(r2, r3, r4);	 Catch:{ IOException -> 0x00dc }
        if (r2 == 0) goto L_0x003c;	 Catch:{ IOException -> 0x00dc }
    L_0x0020:
        r2 = new java.io.File;	 Catch:{ IOException -> 0x00dc }
        r3 = r13.getPath();	 Catch:{ IOException -> 0x00dc }
        r2.<init>(r3);	 Catch:{ IOException -> 0x00dc }
        r3 = r2.exists();	 Catch:{ IOException -> 0x00dc }
        if (r3 == 0) goto L_0x0034;	 Catch:{ IOException -> 0x00dc }
    L_0x002f:
        r2 = r2.getName();	 Catch:{ IOException -> 0x00dc }
        goto L_0x0076;	 Catch:{ IOException -> 0x00dc }
    L_0x0034:
        r14 = new java.io.FileNotFoundException;	 Catch:{ IOException -> 0x00dc }
        r14.<init>();	 Catch:{ IOException -> 0x00dc }
        r14 = (java.lang.Throwable) r14;	 Catch:{ IOException -> 0x00dc }
        throw r14;	 Catch:{ IOException -> 0x00dc }
    L_0x003c:
        r2 = "content";	 Catch:{ IOException -> 0x00dc }
        r3 = r13.getScheme();	 Catch:{ IOException -> 0x00dc }
        r2 = defpackage.addb.a(r2, r3, r4);	 Catch:{ IOException -> 0x00dc }
        if (r2 == 0) goto L_0x00ca;	 Catch:{ IOException -> 0x00dc }
    L_0x0048:
        r2 = "_display_name";	 Catch:{ IOException -> 0x00dc }
        r3 = "title";	 Catch:{ IOException -> 0x00dc }
        r4 = "mime_type";	 Catch:{ IOException -> 0x00dc }
        r7 = new java.lang.String[]{r2, r3, r4};	 Catch:{ IOException -> 0x00dc }
        r5 = r14.getContentResolver();	 Catch:{ IOException -> 0x00dc }
        r8 = 0;	 Catch:{ IOException -> 0x00dc }
        r9 = 0;	 Catch:{ IOException -> 0x00dc }
        r10 = 0;	 Catch:{ IOException -> 0x00dc }
        r6 = r13;	 Catch:{ IOException -> 0x00dc }
        r2 = r5.query(r6, r7, r8, r9, r10);	 Catch:{ IOException -> 0x00dc }
        if (r2 == 0) goto L_0x00b8;	 Catch:{ IOException -> 0x00dc }
    L_0x0060:
        r3 = new jp.naver.line.android.activity.selectchat.i$a;	 Catch:{ IOException -> 0x00dc }
        r4 = r12;	 Catch:{ IOException -> 0x00dc }
        r4 = (jp.naver.line.android.activity.selectchat.i) r4;	 Catch:{ IOException -> 0x00dc }
        r3.<init>(r4);	 Catch:{ IOException -> 0x00dc }
        r3 = (defpackage.acqr) r3;	 Catch:{ IOException -> 0x00dc }
        r2 = jp.naver.line.android.util.af.b(r2, r3);	 Catch:{ IOException -> 0x00dc }
        r2 = r2.b();	 Catch:{ IOException -> 0x00dc }
        r2 = (java.lang.String) r2;	 Catch:{ IOException -> 0x00dc }
        if (r2 == 0) goto L_0x00b8;	 Catch:{ IOException -> 0x00dc }
    L_0x0076:
        if (r2 != 0) goto L_0x0079;	 Catch:{ IOException -> 0x00dc }
    L_0x0078:
        r2 = r1;	 Catch:{ IOException -> 0x00dc }
    L_0x0079:
        r3 = new java.io.File;	 Catch:{ IOException -> 0x00dc }
        r4 = defpackage.ufu.b();	 Catch:{ IOException -> 0x00dc }
        r3.<init>(r4, r1);	 Catch:{ IOException -> 0x00dc }
        r1 = r3.exists();	 Catch:{ IOException -> 0x00dc }
        if (r1 != 0) goto L_0x008b;	 Catch:{ IOException -> 0x00dc }
    L_0x0088:
        r3.mkdir();	 Catch:{ IOException -> 0x00dc }
    L_0x008b:
        r1 = new java.io.File;	 Catch:{ IOException -> 0x00dc }
        r1.<init>(r3, r2);	 Catch:{ IOException -> 0x00dc }
        r14 = r14.getContentResolver();	 Catch:{ IOException -> 0x00dc }
        r14 = r14.openInputStream(r13);	 Catch:{ IOException -> 0x00dc }
        r14 = (java.io.Closeable) r14;	 Catch:{ IOException -> 0x00dc }
        r2 = r14;	 Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
        r2 = (java.io.InputStream) r2;	 Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
        defpackage.adek.a(r2, r1);	 Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
        r2 = kotlin.y.a;	 Catch:{ Throwable -> 0x00ae, all -> 0x00ab }
        defpackage.acpu.a(r14, r0);	 Catch:{ IOException -> 0x00dc }
        r14 = android.net.Uri.fromFile(r1);	 Catch:{ IOException -> 0x00dc }
        r13 = r14;
        goto L_0x00df;
    L_0x00ab:
        r1 = move-exception;
        r2 = r0;
        goto L_0x00b4;
    L_0x00ae:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x00b0 }
    L_0x00b0:
        r2 = move-exception;
        r11 = r2;
        r2 = r1;
        r1 = r11;
    L_0x00b4:
        defpackage.acpu.a(r14, r2);	 Catch:{ IOException -> 0x00dc }
        throw r1;	 Catch:{ IOException -> 0x00dc }
    L_0x00b8:
        r14 = new java.io.IOException;	 Catch:{ IOException -> 0x00dc }
        r1 = "no content data. uri=";	 Catch:{ IOException -> 0x00dc }
        r2 = java.lang.String.valueOf(r13);	 Catch:{ IOException -> 0x00dc }
        r1 = r1.concat(r2);	 Catch:{ IOException -> 0x00dc }
        r14.<init>(r1);	 Catch:{ IOException -> 0x00dc }
        r14 = (java.lang.Throwable) r14;	 Catch:{ IOException -> 0x00dc }
        throw r14;	 Catch:{ IOException -> 0x00dc }
    L_0x00ca:
        r14 = new java.io.IOException;	 Catch:{ IOException -> 0x00dc }
        r1 = "unexpected scheme. uri=";	 Catch:{ IOException -> 0x00dc }
        r2 = java.lang.String.valueOf(r13);	 Catch:{ IOException -> 0x00dc }
        r1 = r1.concat(r2);	 Catch:{ IOException -> 0x00dc }
        r14.<init>(r1);	 Catch:{ IOException -> 0x00dc }
        r14 = (java.lang.Throwable) r14;	 Catch:{ IOException -> 0x00dc }
        throw r14;	 Catch:{ IOException -> 0x00dc }
    L_0x00dc:
        defpackage.ugp.b(r0);
    L_0x00df:
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.selectchat.i.a(android.net.Uri, android.content.Context):android.net.Uri");
    }

    public static final Intent a(Context context, String str, boolean z, boolean z2) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("fromScheme", z);
        intent.putExtra("sendMessageDirectly", z2);
        return intent;
    }

    public static final Intent a(Context context, String str) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType(Scopes.PROFILE);
        intent.putExtra(Scopes.PROFILE, str);
        return intent;
    }

    public static final Intent a(Context context, Uri uri, OBSCopyInfo oBSCopyInfo, boolean z) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.putExtra("isOriginalImage", z);
        OBSCopyInfo.a(intent, oBSCopyInfo, true);
        return intent;
    }

    public static final Intent a(Context context, ShareAlbumContent shareAlbumContent) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("album/*");
        intent.putExtra("albumContent", shareAlbumContent);
        intent.putExtra("shareOnlyChat", true);
        return intent;
    }

    public static final Intent b(Context context, String str) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("official_account");
        intent.putExtra("official_account", str);
        return intent;
    }

    public static final Intent a(Context context, Uri uri, OBSCopyInfo oBSCopyInfo) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("video/");
        intent.putExtra("android.intent.extra.STREAM", uri);
        OBSCopyInfo.a(intent, oBSCopyInfo, true);
        return intent;
    }

    public static final Intent a(Context context, Location location) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("location/line");
        intent.putExtra("location", location);
        return intent;
    }

    public static final Intent a(Context context, String str, String str2) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("posttype/*");
        intent.putExtra("jp.naver.line.android.activity.selectchat.userMid", str);
        intent.putExtra("jp.naver.line.android.activity.selectchat.postId", str2);
        return intent;
    }

    public static final Intent b(Context context, String str, String str2) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("contenttype/*");
        intent.putExtra("contentId", str);
        intent.putExtra("contentType", str2);
        return intent;
    }

    public static final Intent a(Context context, int i, int i2, boolean z, String str, e eVar) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("contenttype/*");
        intent.putExtra("EXTRA_TIMELINE_MEME_ID", i);
        intent.putExtra("EXTRA_TIMELINE_MEME_SOURCE_VER", i2);
        intent.putExtra("EXTRA_TIMELINE_MEME_ENROLL_POPULAR", z);
        intent.putExtra("EXTRA_TIMELINE_MEME_CAPTION", str);
        intent.putExtra("EXTRA_TIMELINE_MEME_MEDIA", eVar);
        return intent;
    }

    public static final Intent a(Context context, String str, long[] jArr) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("messageid/*");
        intent.putExtra("sourceChatId", str);
        intent.putExtra("localMessageIds", jArr);
        return intent;
    }

    public static final Intent a(Context context, ArrayList<String> arrayList, String str, int i, String str2) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.CHOOSER");
        intent.putExtra("EXTRA_TITLE", str);
        intent.putExtra("EXTRA_LIMIT", i);
        intent.putStringArrayListExtra("EXTRA_TARGETS", arrayList);
        intent.putExtra("EXTRA_CALLBACK_ID", str2);
        intent.putExtra("EXTRA_SHOW_BUDDIES", false);
        return intent;
    }

    public static final Intent a(Context context, KeepContentShareModel[] keepContentShareModelArr) {
        Intent intent = new Intent(context, SelectChatInnerActivity.class);
        intent.setAction("android.intent.action.SEND");
        intent.setType("keepcont/*");
        intent.putExtra("keepContents", (Parcelable[]) keepContentShareModelArr);
        intent.putExtra("shareOnlyChat", true);
        return intent;
    }

    public static final void a(Context context, ArrayList<MediaItem> arrayList) {
        Object obj = (arrayList == null || arrayList.isEmpty()) ? null : 1;
        if (!(obj == null || arrayList == null)) {
            MediaItem mediaItem = (MediaItem) arrayList.get(0);
            if (mediaItem != null) {
                Intent intent = null;
                String r;
                if (mediaItem.g() == 1) {
                    r = mediaItem.r();
                    if (r == null) {
                        r = mediaItem.m;
                    }
                    intent = a(context, Uri.parse(r), null);
                } else if (mediaItem.g() == 0 && mediaItem.m() != null) {
                    r = String.valueOf(mediaItem.m());
                    if (!r.startsWith("file://")) {
                        r = "file://".concat(String.valueOf(r));
                    }
                    intent = a(context, Uri.parse(r), null, mediaItem.p());
                }
                if (intent != null) {
                    Intent putExtra = intent.putExtra("shareOnlyChat", true);
                    if (putExtra != null) {
                        putExtra.setFlags(67108864);
                        if (putExtra != null) {
                            context.startActivity(putExtra);
                        }
                    }
                }
            }
        }
    }

    public static final List<Uri> a(Context context, List<? extends Uri> list, String str) {
        if (str == null || (!str.startsWith("image/") && !str.startsWith("*/"))) {
            return acob.a;
        }
        Collection arrayList = new ArrayList();
        Iterator it = acnz.h((Iterable) list).iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            boolean startsWith;
            Object next = it.next();
            Uri uri = (Uri) next;
            if (uri != null && (acry.a((Object) "file", uri.getScheme()) ^ 1) == 0) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(uri.getPath(), options);
                String str2 = options.outMimeType;
                if (str2 != null) {
                    startsWith = str2.startsWith("image/");
                    if (!(startsWith || a(context.getContentResolver(), uri))) {
                        z = false;
                    }
                    if (z) {
                        arrayList.add(next);
                    }
                }
            }
            startsWith = false;
            z = false;
            if (z) {
                arrayList.add(next);
            }
        }
        List<Uri> list2 = (List) arrayList;
        return (((Collection) list2).isEmpty() ^ 1) != 0 ? list2 : a((List) list, context);
    }

    public static android.net.Uri a(android.net.Uri r7, android.content.Context r8, java.lang.String r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.selectchat.i.a(android.net.Uri, android.content.Context, java.lang.String):android.net.Uri. bs: []
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
        r0 = r8.getContentResolver();
        r0 = r0.getType(r7);
        r1 = 0;
        if (r0 != 0) goto L_0x0038;
    L_0x000b:
        r0 = b;
        r2 = r7.toString();
        r2 = (java.lang.CharSequence) r2;
        r0 = r0.a(r2, 0);
        if (r0 == 0) goto L_0x0037;
    L_0x0019:
        r0 = r0.c();
        if (r0 == 0) goto L_0x0037;
    L_0x001f:
        r2 = 1;
        r0 = r0.a(r2);
        if (r0 == 0) goto L_0x0037;
    L_0x0026:
        r0 = r0.a();
        if (r0 == 0) goto L_0x0037;
    L_0x002c:
        r2 = defpackage.adcf.a;
        r2 = r2.name();
        r0 = java.net.URLDecoder.decode(r0, r2);
        goto L_0x0038;
    L_0x0037:
        r0 = r1;
    L_0x0038:
        r2 = jp.naver.line.android.activity.selectchat.g.a;
        r2 = jp.naver.line.android.activity.selectchat.g.a();
        r0 = r2.get(r0);
        r0 = (java.lang.String) r0;
        if (r0 != 0) goto L_0x0047;
    L_0x0046:
        goto L_0x0048;
    L_0x0047:
        r9 = r0;
    L_0x0048:
        if (r9 != 0) goto L_0x004b;
    L_0x004a:
        return r7;
    L_0x004b:
        r0 = defpackage.ufu.b();	 Catch:{ IOException -> 0x0091 }
        r2 = new java.io.File;	 Catch:{ IOException -> 0x0091 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0091 }
        r4 = "tmp_";	 Catch:{ IOException -> 0x0091 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x0091 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0091 }
        r3.append(r4);	 Catch:{ IOException -> 0x0091 }
        r3.append(r9);	 Catch:{ IOException -> 0x0091 }
        r9 = r3.toString();	 Catch:{ IOException -> 0x0091 }
        r2.<init>(r0, r9);	 Catch:{ IOException -> 0x0091 }
        r8 = r8.getContentResolver();	 Catch:{ IOException -> 0x0091 }
        r8 = r8.openInputStream(r7);	 Catch:{ IOException -> 0x0091 }
        r8 = (java.io.Closeable) r8;	 Catch:{ IOException -> 0x0091 }
        r9 = r8;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r9 = (java.io.InputStream) r9;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        defpackage.adek.a(r9, r2);	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        r9 = kotlin.y.a;	 Catch:{ Throwable -> 0x0087, all -> 0x0084 }
        defpackage.acpu.a(r8, r1);	 Catch:{ IOException -> 0x0091 }
        r8 = android.net.Uri.fromFile(r2);	 Catch:{ IOException -> 0x0091 }
        r7 = r8;
        goto L_0x0094;
    L_0x0084:
        r9 = move-exception;
        r0 = r1;
        goto L_0x008d;
    L_0x0087:
        r9 = move-exception;
        throw r9;	 Catch:{ all -> 0x0089 }
    L_0x0089:
        r0 = move-exception;
        r6 = r0;
        r0 = r9;
        r9 = r6;
    L_0x008d:
        defpackage.acpu.a(r8, r0);	 Catch:{ IOException -> 0x0091 }
        throw r9;	 Catch:{ IOException -> 0x0091 }
    L_0x0091:
        defpackage.ugp.b(r1);
    L_0x0094:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.selectchat.i.a(android.net.Uri, android.content.Context, java.lang.String):android.net.Uri");
    }

    public static final /* synthetic */ String a(Cursor cursor) {
        String f = af.f(cursor, "_display_name");
        if (acry.a(f != null ? Boolean.valueOf(addc.a((CharSequence) f, (CharSequence) "../", true)) : null, Boolean.TRUE)) {
            return null;
        }
        if (f != null) {
            return f;
        }
        f = af.f(cursor, MessageBundle.TITLE_ENTRY);
        if (f == null) {
            f = String.valueOf(System.currentTimeMillis());
        }
        if (addc.a((CharSequence) f, (CharSequence) "../", true)) {
            return null;
        }
        String f2 = af.f(cursor, "mime_type");
        g gVar = g.a;
        f2 = (String) g.b().get(f2);
        if (f2 == null) {
            return f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f);
        stringBuilder.append(f2);
        return stringBuilder.toString();
    }
}
