package jp.naver.line.android.cp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import defpackage.qnj;

public class ServerHostProvider extends ContentProvider {
    public String getType(Uri uri) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public static java.lang.Object a(android.content.Context r4, int r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.cp.ServerHostProvider.a(android.content.Context, int):java.lang.Object. bs: []
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
        r4 = r4.getContentResolver();	 Catch:{ Exception -> 0x0016 }
        r0 = "content://jp.naver.line.android.LineStore/";	 Catch:{ Exception -> 0x0016 }
        r0 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x0016 }
        r1 = "CALL_GETSERVERHOST";	 Catch:{ Exception -> 0x0016 }
        r2 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0016 }
        r3 = 0;	 Catch:{ Exception -> 0x0016 }
        r4 = r4.call(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0016 }
        return r4;
    L_0x0016:
        r4 = java.lang.String.valueOf(r5);
        r4 = defpackage.qnj.b(r4);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.cp.ServerHostProvider.a(android.content.Context, int):java.lang.Object");
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        return "CALL_GETSERVERHOST".equals(str) ? qnj.a(str2) : null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }
}
