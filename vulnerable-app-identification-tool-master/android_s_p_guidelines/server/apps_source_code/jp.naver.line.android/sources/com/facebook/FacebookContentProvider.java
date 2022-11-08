package com.facebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.at;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FacebookContentProvider extends ContentProvider {
    private static final String a = "com.facebook.FacebookContentProvider";

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public static String a(String str, UUID uuid, String str2) {
        return String.format("%s%s/%s/%s", new Object[]{"content://com.facebook.app.FacebookContentProvider", str, uuid.toString(), str2});
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        Pair a = a(uri);
        if (a != null) {
            try {
                File a2 = at.a((UUID) a.first, (String) a.second);
                if (a2 != null) {
                    return ParcelFileDescriptor.open(a2, 268435456);
                }
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                Log.e(a, "Got unexpected exception:".concat(String.valueOf(e)));
                throw e;
            }
        }
        throw new FileNotFoundException();
    }

    private static android.util.Pair<java.util.UUID, java.lang.String> a(android.net.Uri r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.FacebookContentProvider.a(android.net.Uri):android.util.Pair<java.util.UUID, java.lang.String>. bs: []
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
        r2 = r2.getPath();	 Catch:{ Exception -> 0x0034 }
        r0 = 1;	 Catch:{ Exception -> 0x0034 }
        r2 = r2.substring(r0);	 Catch:{ Exception -> 0x0034 }
        r1 = "/";	 Catch:{ Exception -> 0x0034 }
        r2 = r2.split(r1);	 Catch:{ Exception -> 0x0034 }
        r1 = 0;	 Catch:{ Exception -> 0x0034 }
        r1 = r2[r1];	 Catch:{ Exception -> 0x0034 }
        r2 = r2[r0];	 Catch:{ Exception -> 0x0034 }
        r0 = "..";	 Catch:{ Exception -> 0x0034 }
        r0 = r0.contentEquals(r1);	 Catch:{ Exception -> 0x0034 }
        if (r0 != 0) goto L_0x002e;	 Catch:{ Exception -> 0x0034 }
    L_0x001c:
        r0 = "..";	 Catch:{ Exception -> 0x0034 }
        r0 = r0.contentEquals(r2);	 Catch:{ Exception -> 0x0034 }
        if (r0 != 0) goto L_0x002e;	 Catch:{ Exception -> 0x0034 }
    L_0x0024:
        r0 = java.util.UUID.fromString(r1);	 Catch:{ Exception -> 0x0034 }
        r1 = new android.util.Pair;	 Catch:{ Exception -> 0x0034 }
        r1.<init>(r0, r2);	 Catch:{ Exception -> 0x0034 }
        return r1;	 Catch:{ Exception -> 0x0034 }
    L_0x002e:
        r2 = new java.lang.Exception;	 Catch:{ Exception -> 0x0034 }
        r2.<init>();	 Catch:{ Exception -> 0x0034 }
        throw r2;	 Catch:{ Exception -> 0x0034 }
    L_0x0034:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookContentProvider.a(android.net.Uri):android.util.Pair<java.util.UUID, java.lang.String>");
    }
}
