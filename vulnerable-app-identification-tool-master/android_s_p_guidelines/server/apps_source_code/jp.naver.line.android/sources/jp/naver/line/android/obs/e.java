package jp.naver.line.android.obs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.sdk.AppLovinEventTypes;
import defpackage.adek;
import defpackage.qce;
import defpackage.udx;
import defpackage.vve;
import defpackage.vvj;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import jp.naver.line.android.BuildConfig;
import jp.naver.line.android.b;
import jp.naver.line.android.common.util.io.d;
import jp.naver.line.android.common.util.io.i;
import jp.naver.line.android.l;
import jp.naver.line.android.obs.service.OBSDownloadRequest;

public final class e {
    public static final File a(OBSDownloadRequest oBSDownloadRequest, Bitmap bitmap) throws IOException {
        if (oBSDownloadRequest == null || oBSDownloadRequest.b() == null) {
            throw new IllegalArgumentException("key is empty.");
        }
        File file = null;
        switch (oBSDownloadRequest.b()) {
            case IMAGE_PROFILE_PREVIEW:
                file = a(oBSDownloadRequest.h(), oBSDownloadRequest.c(), oBSDownloadRequest.e());
                vve.a(bitmap, file);
                break;
            case IMAGE_GROUP_PREVIEW:
                if (oBSDownloadRequest.h()) {
                    return null;
                }
                return a(oBSDownloadRequest.c(), bitmap);
            case IMAGE_MESSAGE_PREVIEW:
                if (!oBSDownloadRequest.h()) {
                    file = a(oBSDownloadRequest.d(), oBSDownloadRequest.c(), true);
                    vve.a(bitmap, file);
                    break;
                }
                return null;
            case IMAGE_MESSAGE_MYHOME_PREVIEW:
                if (!oBSDownloadRequest.h()) {
                    file = c(oBSDownloadRequest.c(), ".thumb");
                    vve.a(bitmap, file);
                    break;
                }
                return null;
        }
        return file;
    }

    public static final void a(OBSDownloadRequest oBSDownloadRequest, byte[] bArr) throws IOException {
        if (oBSDownloadRequest == null || oBSDownloadRequest.b() == null) {
            throw new IllegalArgumentException("key is empty.");
        }
        File file = null;
        switch (oBSDownloadRequest.b()) {
            case IMAGE_PROFILE_PREVIEW:
                file = a(oBSDownloadRequest.h(), oBSDownloadRequest.c(), oBSDownloadRequest.e());
                break;
            case IMAGE_GROUP_PREVIEW:
                if (!oBSDownloadRequest.h()) {
                    file = b(oBSDownloadRequest.c(), true);
                    break;
                }
                return;
            case IMAGE_MESSAGE_PREVIEW:
                if (!oBSDownloadRequest.h()) {
                    file = a(oBSDownloadRequest.d(), oBSDownloadRequest.c(), true);
                    break;
                }
                return;
            case IMAGE_MESSAGE_MYHOME_PREVIEW:
                if (!oBSDownloadRequest.h()) {
                    file = c(oBSDownloadRequest.c(), ".thumb");
                    break;
                }
                return;
        }
        if (file != null) {
            adek.a(file, bArr);
        }
    }

    public static final File a(String str, Bitmap bitmap) throws IOException {
        File b = b(str, true);
        vve.a(bitmap, b(str, true));
        return b;
    }

    public static final Bitmap a(f fVar, Uri uri) throws FileNotFoundException {
        if (fVar != null) {
            switch (fVar) {
                case IMAGE_PROFILE_PREVIEW:
                    return a(uri);
                case IMAGE_GROUP_PREVIEW:
                    return a(uri);
                case IMAGE_MESSAGE_PREVIEW:
                    return a(uri);
                case IMAGE_MESSAGE_MYHOME_PREVIEW:
                    return a(uri);
                default:
                    throw new IllegalArgumentException("illegal contentType. contentType=".concat(String.valueOf(fVar)));
            }
        }
        throw new IllegalArgumentException("contentType is null.");
    }

    private static Bitmap a(Uri uri) throws FileNotFoundException {
        if (uri == null) {
            return null;
        }
        Bitmap decodeStream;
        InputStream b = b(uri);
        Options options = new Options();
        options.inScaled = false;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(b, null, options);
        if (options.outWidth > 200 || options.outHeight > 200) {
            int max = Math.max((options.outWidth / 200) + 1, (options.outHeight / 200) + 1);
            options.inJustDecodeBounds = false;
            options.inSampleSize = max;
            decodeStream = BitmapFactory.decodeStream(b(uri), null, options);
        } else {
            decodeStream = BitmapFactory.decodeStream(b);
        }
        float min = Math.min(200.0f / ((float) decodeStream.getWidth()), 200.0f / ((float) decodeStream.getHeight()));
        if (min > 1.0f) {
            min = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(min, min);
        matrix.postRotate(vvj.a(uri));
        Bitmap createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, false);
        if (decodeStream != createBitmap) {
            decodeStream.recycle();
        }
        return createBitmap;
    }

    public static boolean a(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static final File b(OBSDownloadRequest oBSDownloadRequest) throws d {
        return a(oBSDownloadRequest, ".downloading");
    }

    public static final File c(OBSDownloadRequest oBSDownloadRequest) throws d {
        return a(oBSDownloadRequest, "");
    }

    private static final File a(OBSDownloadRequest oBSDownloadRequest, String str) throws d {
        if (oBSDownloadRequest == null || oBSDownloadRequest.b() == null) {
            throw new IllegalArgumentException("request is empty.");
        }
        switch (oBSDownloadRequest.b()) {
            case IMAGE_PROFILE_PREVIEW:
                return a(oBSDownloadRequest.h(), oBSDownloadRequest.c(), oBSDownloadRequest.e(), ".thumb".concat(String.valueOf(str)));
            case IMAGE_GROUP_PREVIEW:
                return b(oBSDownloadRequest.c(), ".thumb".concat(String.valueOf(str)));
            case IMAGE_MESSAGE_PREVIEW:
                return a(oBSDownloadRequest.d(), oBSDownloadRequest.c(), ".thumb".concat(String.valueOf(str)));
            case IMAGE_MESSAGE_MYHOME_PREVIEW:
                return c(oBSDownloadRequest.c(), ".thumb".concat(String.valueOf(str)));
            case IMAGE_PROFILE:
                return a(oBSDownloadRequest.h(), oBSDownloadRequest.c(), oBSDownloadRequest.e(), str);
            case VIDEO_PROFILE_SJPG:
                return a(oBSDownloadRequest.h(), oBSDownloadRequest.c(), oBSDownloadRequest.e(), ".sjpg".concat(String.valueOf(str)));
            case IMAGE_GROUP:
            case IMAGE_SQUARE_GROUP_MEMBER:
                return b(oBSDownloadRequest.c(), str);
            case IMAGE_MESSAGE:
                return a(oBSDownloadRequest.d(), oBSDownloadRequest.c(), str);
            case IMAGE_MESSAGE_MYHOME:
                return c(oBSDownloadRequest.c(), str);
            default:
                StringBuilder stringBuilder = new StringBuilder("unknown contentType.contentType=");
                stringBuilder.append(oBSDownloadRequest.b());
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static boolean a(boolean z, String str, boolean z2) {
        File a;
        if (z2) {
            try {
                a = a(z, str, null, ".thumb");
            } catch (Throwable e) {
                udx.a(e, "LINEAND-16398", "Delete Fail Profile Image File", "OBSCacheFileManager.deleteProfileImageFile()");
            }
        } else {
            a = a(z, str, null, "");
        }
        if (a.exists()) {
            return a.delete();
        }
        return false;
    }

    public static boolean a(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.obs.e.a(java.lang.String):boolean. bs: []
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
        r1 = 0;
        r2 = ".sjpg";	 Catch:{ d -> 0x003a }
        r5 = a(r1, r5, r0, r2);	 Catch:{ d -> 0x003a }
        r0 = r5.exists();	 Catch:{ d -> 0x003a }
        if (r0 == 0) goto L_0x003a;	 Catch:{ d -> 0x003a }
    L_0x000e:
        r0 = new java.io.File;	 Catch:{ d -> 0x003a }
        r2 = new java.lang.StringBuilder;	 Catch:{ d -> 0x003a }
        r2.<init>();	 Catch:{ d -> 0x003a }
        r3 = r5.getAbsolutePath();	 Catch:{ d -> 0x003a }
        r2.append(r3);	 Catch:{ d -> 0x003a }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ d -> 0x003a }
        r2.append(r3);	 Catch:{ d -> 0x003a }
        r2 = r2.toString();	 Catch:{ d -> 0x003a }
        r0.<init>(r2);	 Catch:{ d -> 0x003a }
        r2 = r5.renameTo(r0);	 Catch:{ d -> 0x003a }
        if (r2 == 0) goto L_0x0035;	 Catch:{ d -> 0x003a }
    L_0x0030:
        r5 = r0.delete();	 Catch:{ d -> 0x003a }
        return r5;	 Catch:{ d -> 0x003a }
    L_0x0035:
        r5 = r5.delete();	 Catch:{ d -> 0x003a }
        return r5;
    L_0x003a:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.obs.e.a(java.lang.String):boolean");
    }

    public static boolean a(String str, boolean z) {
        return a(false, str, z);
    }

    public static File b(String str) throws d {
        return a(false, str, null);
    }

    public static File a(boolean z, String str, String str2) throws d {
        return a(z, str, str2, ".thumb");
    }

    private static File a(boolean z, String str, String str2, String str3) throws d {
        File file;
        StringBuilder stringBuilder = new StringBuilder();
        if (!z || TextUtils.isEmpty(str2)) {
            File a = a(z);
            stringBuilder.append(str);
            file = a;
        } else {
            file = a();
            stringBuilder.append(URLEncoder.encode(str2));
        }
        stringBuilder.append(str3);
        File file2 = new File(file, stringBuilder.toString());
        if (z && !TextUtils.isEmpty(str2) && file2.exists()) {
            file2.setLastModified(System.currentTimeMillis());
        }
        return file2;
    }

    public static File b(String str, boolean z) throws d {
        if (z) {
            return b(str, ".thumb");
        }
        return b(str, "");
    }

    private static File b(String str, String str2) throws d {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        return new File(b(), stringBuilder.toString());
    }

    public static File a(String str, String str2, boolean z) throws d {
        if (z) {
            return a(str, str2, ".thumb");
        }
        return a(str, str2, "");
    }

    public static File a(String str, String str2, String str3) throws d {
        return new File(c(str), a(str2, str3));
    }

    public static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    private static File c(String str, String str2) throws d {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        return new File(i(), stringBuilder.toString());
    }

    public static File a(String str, long j, String str2) throws d {
        return new File(d(str), a(j, str2).toString());
    }

    public static boolean b(String str, long j, String str2) {
        try {
            File a = a(str, j, str2);
            if (a.exists() && a.delete()) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            udx.b(e, "OBSCacheFileDeletionFailed", e.getMessage(), "OBSCacheFileManager#safeDeleteTransferredFile");
            return false;
        }
    }

    public static final StringBuilder a(long j, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j);
        stringBuilder.append("_");
        stringBuilder.append(g(str));
        return stringBuilder;
    }

    private static String g(String str) {
        return str == null ? null : str.replaceAll("[<>:\\*\\?\"\\/\\\\\\|]", "");
    }

    public static final File a(boolean z) throws d {
        File c;
        if (z) {
            c = c();
        } else {
            c = b(true);
        }
        File file = new File(c, TtmlNode.TAG_P);
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File a() throws d {
        File file = new File(c(), "pp");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File b() throws d {
        File file = new File(b(true), "g");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File c(String str) throws d {
        File file = new File(b(true), "mo");
        if (!file.exists()) {
            i.a(file);
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            i.a(file2);
        }
        return file2;
    }

    public static final File d(String str) throws d {
        File file = new File(c(str), "f");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final void e(String str) throws d {
        File d = d(str);
        if (d.exists()) {
            jp.naver.line.android.common.util.io.e.a(d, null, true);
        }
    }

    private static File i() throws d {
        File file = new File(b(true), "h");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    @Deprecated
    public static final File b(boolean z) throws d {
        File file = new File(i.h(), "storage");
        if (z && !file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File c() throws d {
        File file = new File(b(true), "temp");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File d() throws d {
        return new File(c(), "temp_camera");
    }

    public static final File c(boolean z) throws d {
        File file = new File(c(), "temp_download");
        if (z && !file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File e() throws d {
        File file = new File(b(true), "pay");
        if (!file.exists()) {
            i.a(file);
        }
        return file;
    }

    public static final File f() throws d {
        return new File(i.h(), "cache");
    }

    public static final File g() throws d {
        return new File(f(), "temp");
    }

    public static void h() throws d, NullPointerException {
        File a = a();
        if (a.exists()) {
            long currentTimeMillis;
            if (b.g == qce.BETA) {
                currentTimeMillis = System.currentTimeMillis() - 86400000;
            } else {
                currentTimeMillis = System.currentTimeMillis() - BuildConfig.MORE_TAB_BOARD_INFO_EXPIRE_TIME;
            }
            for (File file : a.listFiles()) {
                if (file.isFile() && file.lastModified() < currentTimeMillis && !file.delete()) {
                    file.deleteOnExit();
                }
            }
        }
    }

    public static final File f(String str) {
        return new File(new File(new File(new File(Environment.getExternalStorageDirectory(), i.i()), "storage"), "mo"), str);
    }

    private static InputStream b(Uri uri) throws FileNotFoundException {
        if (AppLovinEventTypes.USER_VIEWED_CONTENT.equals(uri.getScheme())) {
            return l.a().getContentResolver().openInputStream(uri);
        }
        return new FileInputStream(new File(uri.getPath()));
    }

    public static final void a(OBSDownloadRequest oBSDownloadRequest) throws d {
        File a = a(oBSDownloadRequest, ".downloading");
        String path = a.getPath();
        int lastIndexOf = path.lastIndexOf(".downloading");
        if (lastIndexOf == -1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(path);
            stringBuilder.append(" doesn't contain .downloading");
            Log.e("OBSCacheFileManager", stringBuilder.toString());
            return;
        }
        a.renameTo(new File(path.substring(0, lastIndexOf)));
    }
}
