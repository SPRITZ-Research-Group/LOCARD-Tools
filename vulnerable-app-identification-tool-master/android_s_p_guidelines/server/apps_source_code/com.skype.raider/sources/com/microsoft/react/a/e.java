package com.microsoft.react.a;

import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Thumbnails;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class e {

    private static class a {
        private static int a = 0;
        private static int b = 0;
        private static int c = 0;
        private static int d = 0;

        public static void a() {
            FLog.w("MediaUtilities", "End reading gallery data. Valid size images (media store, Exif): " + a + ", " + b + ", videos: " + c + ", invalid size (0, 0): " + d);
            a = 0;
            b = 0;
            d = 0;
            c = 0;
        }

        public static void b() {
            a++;
        }

        public static void c() {
            b++;
        }

        public static void d() {
            d++;
        }

        public static void e() {
            c++;
        }

        public static void f() {
            FLog.w("MediaUtilities", "Starting reading gallery data.");
        }
    }

    public static List<c> a(@NonNull Context context, boolean allowVideo, boolean disableGifs, @NonNull String album) {
        return a(context, allowVideo, disableGifs, album, 0, false);
    }

    public static java.util.List<com.microsoft.react.a.c> a(@android.support.annotation.NonNull android.content.Context r44, boolean r45, boolean r46, @android.support.annotation.NonNull java.lang.String r47, int r48, boolean r49) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r20_0 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>) in PHI: PHI: (r20_2 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>) = (r20_0 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>), (r20_0 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>), (r20_1 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>) binds: {(r20_0 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>)=B:75:0x0252, (r20_0 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>)=B:96:0x02af, (r20_1 'cameraMediaFiles' java.util.List<com.microsoft.react.a.c>)=B:23:0x00f0}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r35 = new com.microsoft.react.a.a;
        r2 = "MediaUtilities";
        r42 = "getMedia()";
        r0 = r35;
        r1 = r42;
        r0.<init>(r2, r1);
        r2 = "MediaUtilities";
        r42 = "loadCameraImages";
        r0 = r42;
        com.facebook.common.logging.FLog.i(r2, r0);
        r2 = 0;
        r0 = r44;
        r28 = b(r0, r2);
        r39 = 0;
        if (r45 == 0) goto L_0x0028;
    L_0x0021:
        r2 = 0;
        r0 = r44;
        r39 = a(r0, r2);
    L_0x0028:
        r2 = 9;
        r4 = new java.lang.String[r2];
        r2 = 0;
        r42 = "_id";
        r4[r2] = r42;
        r2 = 1;
        r42 = "_data";
        r4[r2] = r42;
        r2 = 2;
        r42 = "width";
        r4[r2] = r42;
        r2 = 3;
        r42 = "height";
        r4[r2] = r42;
        r2 = 4;
        r42 = "_size";
        r4[r2] = r42;
        r2 = 5;
        r42 = "duration";
        r4[r2] = r42;
        r2 = 6;
        r42 = "orientation";
        r4[r2] = r42;
        r2 = 7;
        r42 = "media_type";
        r4[r2] = r42;
        r2 = 8;
        r42 = "date_added";
        r4[r2] = r42;
        r5 = "(media_type=1";
        if (r45 == 0) goto L_0x0073;
    L_0x005e:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r5);
        r42 = " OR media_type=3";
        r0 = r42;
        r2 = r2.append(r0);
        r5 = r2.toString();
    L_0x0073:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r5);
        r42 = ")";
        r0 = r42;
        r2 = r2.append(r0);
        r5 = r2.toString();
        if (r46 == 0) goto L_0x009f;
    L_0x008a:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r5);
        r42 = " AND mime_type!='image/gif'";
        r0 = r42;
        r2 = r2.append(r0);
        r5 = r2.toString();
    L_0x009f:
        r2 = android.text.TextUtils.isEmpty(r47);
        if (r2 != 0) goto L_0x00ce;
    L_0x00a5:
        r2 = "All Photos";
        r0 = r47;
        r2 = r0.equals(r2);
        if (r2 != 0) goto L_0x00ce;
    L_0x00af:
        r23 = android.database.DatabaseUtils.sqlEscapeString(r47);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r5);
        r42 = " AND bucket_display_name=";
        r0 = r42;
        r2 = r2.append(r0);
        r0 = r23;
        r2 = r2.append(r0);
        r5 = r2.toString();
    L_0x00ce:
        r2 = "external";
        r3 = android.provider.MediaStore.Files.getContentUri(r2);
        r21 = 0;
        r2 = r44.getContentResolver();	 Catch:{ all -> 0x0164 }
        r6 = 0;	 Catch:{ all -> 0x0164 }
        r7 = "date_added DESC";	 Catch:{ all -> 0x0164 }
        r21 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0164 }
        if (r21 == 0) goto L_0x00e9;	 Catch:{ all -> 0x0164 }
    L_0x00e3:
        r2 = r21.getCount();	 Catch:{ all -> 0x0164 }
        if (r2 != 0) goto L_0x00f4;	 Catch:{ all -> 0x0164 }
    L_0x00e9:
        r20 = java.util.Collections.EMPTY_LIST;	 Catch:{ all -> 0x0164 }
        if (r21 == 0) goto L_0x00f0;
    L_0x00ed:
        r21.close();
    L_0x00f0:
        com.microsoft.react.a.e.a.a();
    L_0x00f3:
        return r20;
    L_0x00f4:
        r20 = new java.util.ArrayList;	 Catch:{ all -> 0x0164 }
        r2 = r21.getCount();	 Catch:{ all -> 0x0164 }
        r0 = r20;	 Catch:{ all -> 0x0164 }
        r0.<init>(r2);	 Catch:{ all -> 0x0164 }
        r2 = "_id";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r27 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "_data";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r34 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "media_type";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r30 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "width";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r41 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "height";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r26 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "_size";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r36 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "duration";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r22 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "orientation";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r32 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        r2 = "date_added";	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r38 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0164 }
        com.microsoft.react.a.e.a.f();	 Catch:{ all -> 0x0164 }
    L_0x014a:
        r2 = r21.moveToNext();	 Catch:{ all -> 0x0164 }
        if (r2 == 0) goto L_0x02a7;
    L_0x0150:
        r0 = r21;	 Catch:{ Exception -> 0x016e, all -> 0x0179 }
        r1 = r34;	 Catch:{ Exception -> 0x016e, all -> 0x0179 }
        r33 = r0.getString(r1);	 Catch:{ Exception -> 0x016e, all -> 0x0179 }
        if (r33 != 0) goto L_0x0184;
    L_0x015a:
        r2 = "MediaUtilities";	 Catch:{ all -> 0x0164 }
        r42 = "skip file with null path";	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        com.facebook.common.logging.FLog.w(r2, r0);	 Catch:{ all -> 0x0164 }
        goto L_0x014a;
    L_0x0164:
        r2 = move-exception;
        if (r21 == 0) goto L_0x016a;
    L_0x0167:
        r21.close();
    L_0x016a:
        com.microsoft.react.a.e.a.a();
        throw r2;
    L_0x016e:
        r2 = move-exception;
        r2 = "MediaUtilities";	 Catch:{ all -> 0x0164 }
        r42 = "skip file with null path";	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        com.facebook.common.logging.FLog.w(r2, r0);	 Catch:{ all -> 0x0164 }
        goto L_0x014a;	 Catch:{ all -> 0x0164 }
    L_0x0179:
        r2 = move-exception;	 Catch:{ all -> 0x0164 }
        r2 = "MediaUtilities";	 Catch:{ all -> 0x0164 }
        r42 = "skip file with null path";	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        com.facebook.common.logging.FLog.w(r2, r0);	 Catch:{ all -> 0x0164 }
        goto L_0x014a;	 Catch:{ all -> 0x0164 }
    L_0x0184:
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r27;	 Catch:{ all -> 0x0164 }
        r8 = r0.getLong(r1);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r30;	 Catch:{ all -> 0x0164 }
        r29 = r0.getInt(r1);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r41;	 Catch:{ all -> 0x0164 }
        r40 = r0.getInt(r1);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r26;	 Catch:{ all -> 0x0164 }
        r25 = r0.getInt(r1);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r36;	 Catch:{ all -> 0x0164 }
        r10 = r0.getLong(r1);	 Catch:{ all -> 0x0164 }
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x0164 }
        r42 = r21.getLong(r22);	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        r15 = r2.toSeconds(r0);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r32;	 Catch:{ all -> 0x0164 }
        r17 = r0.getInt(r1);	 Catch:{ all -> 0x0164 }
        r0 = r21;	 Catch:{ all -> 0x0164 }
        r1 = r38;	 Catch:{ all -> 0x0164 }
        r18 = r0.getLong(r1);	 Catch:{ all -> 0x0164 }
        r2 = 90;	 Catch:{ all -> 0x0164 }
        r0 = r17;	 Catch:{ all -> 0x0164 }
        if (r0 == r2) goto L_0x01d4;	 Catch:{ all -> 0x0164 }
    L_0x01ce:
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;	 Catch:{ all -> 0x0164 }
        r0 = r17;	 Catch:{ all -> 0x0164 }
        if (r0 != r2) goto L_0x0257;	 Catch:{ all -> 0x0164 }
    L_0x01d4:
        r31 = 1;	 Catch:{ all -> 0x0164 }
    L_0x01d6:
        if (r31 == 0) goto L_0x025b;	 Catch:{ all -> 0x0164 }
    L_0x01d8:
        r12 = r25;	 Catch:{ all -> 0x0164 }
    L_0x01da:
        if (r31 == 0) goto L_0x025f;	 Catch:{ all -> 0x0164 }
    L_0x01dc:
        r13 = r40;	 Catch:{ all -> 0x0164 }
    L_0x01de:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0164 }
        r42 = "file://";	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        r2.<init>(r0);	 Catch:{ all -> 0x0164 }
        r42 = "/";	 Catch:{ all -> 0x0164 }
        r0 = r33;	 Catch:{ all -> 0x0164 }
        r1 = r42;	 Catch:{ all -> 0x0164 }
        r42 = android.net.Uri.encode(r0, r1);	 Catch:{ all -> 0x0164 }
        r0 = r42;	 Catch:{ all -> 0x0164 }
        r2 = r2.append(r0);	 Catch:{ all -> 0x0164 }
        r2 = r2.toString();	 Catch:{ all -> 0x0164 }
        r7 = android.net.Uri.parse(r2);	 Catch:{ all -> 0x0164 }
        r2 = 3;	 Catch:{ all -> 0x0164 }
        r0 = r29;	 Catch:{ all -> 0x0164 }
        if (r0 != r2) goto L_0x0263;	 Catch:{ all -> 0x0164 }
    L_0x0204:
        r14 = 1;	 Catch:{ all -> 0x0164 }
    L_0x0205:
        r6 = new com.microsoft.react.a.b;	 Catch:{ all -> 0x0164 }
        r6.<init>(r7, r8, r10, r12, r13, r14, r15, r17, r18);	 Catch:{ all -> 0x0164 }
        if (r45 == 0) goto L_0x0265;	 Catch:{ all -> 0x0164 }
    L_0x020c:
        if (r14 == 0) goto L_0x0265;	 Catch:{ all -> 0x0164 }
    L_0x020e:
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0164 }
        r0 = r39;	 Catch:{ all -> 0x0164 }
        r2 = r0.get(r2);	 Catch:{ all -> 0x0164 }
        r2 = (com.microsoft.react.a.d) r2;	 Catch:{ all -> 0x0164 }
        r37 = r2;	 Catch:{ all -> 0x0164 }
    L_0x021c:
        r24 = new com.microsoft.react.a.c;	 Catch:{ all -> 0x0164 }
        r0 = r24;	 Catch:{ all -> 0x0164 }
        r1 = r37;	 Catch:{ all -> 0x0164 }
        r0.<init>(r6, r1);	 Catch:{ all -> 0x0164 }
        if (r49 == 0) goto L_0x022e;	 Catch:{ all -> 0x0164 }
    L_0x0227:
        r0 = r44;	 Catch:{ all -> 0x0164 }
        r1 = r24;	 Catch:{ all -> 0x0164 }
        b(r0, r1);	 Catch:{ all -> 0x0164 }
    L_0x022e:
        r0 = r24;	 Catch:{ all -> 0x0164 }
        r2 = r0.a;	 Catch:{ all -> 0x0164 }
        r2 = r2.c;	 Catch:{ all -> 0x0164 }
        if (r2 == 0) goto L_0x0274;	 Catch:{ all -> 0x0164 }
    L_0x0236:
        com.microsoft.react.a.e.a.e();	 Catch:{ all -> 0x0164 }
        r2 = 1;	 Catch:{ all -> 0x0164 }
    L_0x023a:
        if (r2 == 0) goto L_0x0243;	 Catch:{ all -> 0x0164 }
    L_0x023c:
        r0 = r20;	 Catch:{ all -> 0x0164 }
        r1 = r24;	 Catch:{ all -> 0x0164 }
        r0.add(r1);	 Catch:{ all -> 0x0164 }
    L_0x0243:
        if (r48 <= 0) goto L_0x014a;	 Catch:{ all -> 0x0164 }
    L_0x0245:
        r2 = r20.size();	 Catch:{ all -> 0x0164 }
        r0 = r48;
        if (r0 > r2) goto L_0x014a;
    L_0x024d:
        if (r21 == 0) goto L_0x0252;
    L_0x024f:
        r21.close();
    L_0x0252:
        com.microsoft.react.a.e.a.a();
        goto L_0x00f3;
    L_0x0257:
        r31 = 0;
        goto L_0x01d6;
    L_0x025b:
        r12 = r40;
        goto L_0x01da;
    L_0x025f:
        r13 = r25;
        goto L_0x01de;
    L_0x0263:
        r14 = 0;
        goto L_0x0205;
    L_0x0265:
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0164 }
        r0 = r28;	 Catch:{ all -> 0x0164 }
        r2 = r0.get(r2);	 Catch:{ all -> 0x0164 }
        r2 = (com.microsoft.react.a.d) r2;	 Catch:{ all -> 0x0164 }
        r37 = r2;	 Catch:{ all -> 0x0164 }
        goto L_0x021c;	 Catch:{ all -> 0x0164 }
    L_0x0274:
        r0 = r24;	 Catch:{ all -> 0x0164 }
        r2 = r0.a;	 Catch:{ all -> 0x0164 }
        r2 = r2.i;	 Catch:{ all -> 0x0164 }
        if (r2 <= 0) goto L_0x0289;	 Catch:{ all -> 0x0164 }
    L_0x027c:
        r0 = r24;	 Catch:{ all -> 0x0164 }
        r2 = r0.a;	 Catch:{ all -> 0x0164 }
        r2 = r2.h;	 Catch:{ all -> 0x0164 }
        if (r2 <= 0) goto L_0x0289;	 Catch:{ all -> 0x0164 }
    L_0x0284:
        com.microsoft.react.a.e.a.b();	 Catch:{ all -> 0x0164 }
        r2 = 1;	 Catch:{ all -> 0x0164 }
        goto L_0x023a;	 Catch:{ all -> 0x0164 }
    L_0x0289:
        r0 = r24;	 Catch:{ all -> 0x0164 }
        r2 = r0.a;	 Catch:{ all -> 0x0164 }
        r2 = r2.a;	 Catch:{ all -> 0x0164 }
        r2 = a(r2);	 Catch:{ all -> 0x0164 }
        r0 = r2.a;	 Catch:{ all -> 0x0164 }
        r42 = r0;	 Catch:{ all -> 0x0164 }
        if (r42 <= 0) goto L_0x02a2;	 Catch:{ all -> 0x0164 }
    L_0x0299:
        r2 = r2.b;	 Catch:{ all -> 0x0164 }
        if (r2 <= 0) goto L_0x02a2;	 Catch:{ all -> 0x0164 }
    L_0x029d:
        com.microsoft.react.a.e.a.c();	 Catch:{ all -> 0x0164 }
        r2 = 1;	 Catch:{ all -> 0x0164 }
        goto L_0x023a;	 Catch:{ all -> 0x0164 }
    L_0x02a2:
        com.microsoft.react.a.e.a.d();	 Catch:{ all -> 0x0164 }
        r2 = 0;	 Catch:{ all -> 0x0164 }
        goto L_0x023a;	 Catch:{ all -> 0x0164 }
    L_0x02a7:
        r35.a();	 Catch:{ all -> 0x0164 }
        if (r21 == 0) goto L_0x02af;
    L_0x02ac:
        r21.close();
    L_0x02af:
        com.microsoft.react.a.e.a.a();
        goto L_0x00f3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.react.a.e.a(android.content.Context, boolean, boolean, java.lang.String, int, boolean):java.util.List<com.microsoft.react.a.c>");
    }

    @NonNull
    private static Map<Long, d> a(Context context, @Nullable Long id) {
        return a(context, "video_id", "_data", Thumbnails.EXTERNAL_CONTENT_URI, id);
    }

    @NonNull
    private static Map<Long, d> b(Context context, @Nullable Long id) {
        return a(context, "image_id", "_data", Images.Thumbnails.EXTERNAL_CONTENT_URI, id);
    }

    @NonNull
    private static Map<Long, d> a(Context context, String projId, String projData, Uri contentUri, Long id) {
        a aVar = new a("MediaUtilities", "getThumbnailUris()");
        String[] proj = new String[]{projId, projData, "width", "height"};
        String selection = null;
        if (id != null) {
            selection = projId + "=" + id;
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(contentUri, proj, selection, null, null);
            Map<Long, d> map;
            if (cursor == null || cursor.getCount() == 0) {
                map = Collections.EMPTY_MAP;
                return map;
            }
            int dataColumnIndex = cursor.getColumnIndex(projData);
            int idColumnIndex = cursor.getColumnIndex(projId);
            int widthColumnIndex = cursor.getColumnIndex(widthColumn);
            int heightColumnIndex = cursor.getColumnIndex(heightColumn);
            map = new HashMap();
            while (cursor.moveToNext()) {
                String path = cursor.getString(dataColumnIndex);
                long index = cursor.getLong(idColumnIndex);
                int width = cursor.getInt(widthColumnIndex);
                int height = cursor.getInt(heightColumnIndex);
                Uri uri = Uri.parse("file://" + Uri.encode(path, "/"));
                map.put(Long.valueOf(index), new d(uri, width, height));
            }
            aVar.a();
            if (cursor != null) {
                cursor.close();
            }
            return map;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static void a(Context context, c data) {
        Thumbnails.getThumbnail(context.getContentResolver(), data.a.b, 1, null);
        data.b = (d) a(context, Long.valueOf(data.a.b)).get(Long.valueOf(data.a.b));
    }

    public static ar a(Context context, c mediaFile, boolean forSelection) {
        boolean z;
        if (forSelection) {
            b(context, mediaFile);
        }
        ar edge = new WritableNativeMap();
        ar node = new WritableNativeMap();
        ar writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, mediaFile.a.a.toString());
        if (mediaFile.b != null) {
            writableNativeMap.putBoolean("isThumbnail", true);
            writableNativeMap.putString("thumbnailUri", mediaFile.b.a.toString());
        } else {
            writableNativeMap.putBoolean("isThumbnail", false);
            writableNativeMap.putString("thumbnailUri", mediaFile.a.a.toString());
        }
        writableNativeMap.putDouble("width", (double) mediaFile.a.h);
        writableNativeMap.putDouble("height", (double) mediaFile.a.i);
        writableNativeMap.putString("contentType", !mediaFile.a.c ? "image/jpeg" : "video/mp4");
        writableNativeMap.putDouble("duration", mediaFile.a.c ? (double) mediaFile.a.d : 0.0d);
        writableNativeMap.putInt("orientation", mediaFile.a.e);
        writableNativeMap.putDouble("size", (double) mediaFile.a.g);
        node.putMap("image", writableNativeMap);
        String str = "isPhoto";
        if (mediaFile.a.c) {
            z = false;
        } else {
            z = true;
        }
        node.putBoolean(str, z);
        node.putDouble("timestamp", (double) mediaFile.a.f);
        edge.putMap("node", node);
        return edge;
    }

    private static void b(Context context, c mediaFile) {
        f size;
        if (mediaFile.a.c) {
            size = b(mediaFile.a.a);
            mediaFile.a.h = size.b;
            mediaFile.a.i = size.a;
        }
        if (!mediaFile.a.c && (mediaFile.a.h <= 0 || mediaFile.a.i <= 0)) {
            FLog.w("MediaUtilities", "uri " + mediaFile.a.a.toString() + " store width " + mediaFile.a.h + " and store height " + mediaFile.a.i);
            size = a(mediaFile.a.a);
            mediaFile.a.h = size.b;
            mediaFile.a.i = size.a;
            FLog.w("MediaUtilities", "uri " + mediaFile.a.a.toString() + " exif width " + mediaFile.a.h + " and exif height " + mediaFile.a.i);
        }
        if (mediaFile.b != null) {
            return;
        }
        if (mediaFile.a.c) {
            a(context, mediaFile);
            return;
        }
        Images.Thumbnails.getThumbnail(context.getContentResolver(), mediaFile.a.b, 1, null);
        mediaFile.b = (d) b(context, Long.valueOf(mediaFile.a.b)).get(Long.valueOf(mediaFile.a.b));
    }

    public static f a(@NonNull Uri imageUri) {
        try {
            boolean rotationRequired;
            ExifInterface exifInterface = new ExifInterface(imageUri.getPath());
            int orientation = exifInterface.getAttributeInt("Orientation", 0);
            int height = exifInterface.getAttributeInt("ImageLength", 0);
            int width = exifInterface.getAttributeInt("ImageWidth", 0);
            if (orientation == 6 || orientation == 8) {
                rotationRequired = true;
            } else {
                rotationRequired = false;
            }
            return a(width, height, rotationRequired);
        } catch (Throwable e) {
            FLog.e("MediaUtilities", "Exception retrieving image orientation", e);
            return new f(0, 0);
        }
    }

    @NonNull
    private static f a(int width, int height, boolean rotationRequired) {
        if (rotationRequired) {
            return new f(height, width);
        }
        return new f(width, height);
    }

    public static f b(@NonNull Uri videoUri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        f a;
        try {
            boolean rotationRequired;
            mediaMetadataRetriever.setDataSource(videoUri.getPath());
            String strWidth = mediaMetadataRetriever.extractMetadata(18);
            String strHeight = mediaMetadataRetriever.extractMetadata(19);
            int width = Integer.parseInt(strWidth);
            int height = Integer.parseInt(strHeight);
            int orientation = Integer.valueOf(mediaMetadataRetriever.extractMetadata(24)).intValue();
            if (orientation == 90 || orientation == 270) {
                rotationRequired = true;
            } else {
                rotationRequired = false;
            }
            a = a(width, height, rotationRequired);
            return a;
        } catch (Throwable e) {
            a = "MediaUtilities";
            FLog.e((String) a, "getVideoSize failed to get data from " + videoUri.getPath(), e);
            return new f(0, 0);
        } finally {
            mediaMetadataRetriever.release();
        }
    }
}
