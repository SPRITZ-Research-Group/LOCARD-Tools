package com.facebook.ads.internal.c;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public final class c {

    public static class a {
        public String a;
        public String b;
        public boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    public static a a(ContentResolver contentResolver) {
        a aVar;
        Throwable th;
        Cursor query;
        try {
            String[] strArr = new String[]{"aid", "androidid", "limit_tracking"};
            query = contentResolver.query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        aVar = new a(query.getString(query.getColumnIndex("aid")), query.getString(query.getColumnIndex("androidid")), Boolean.valueOf(query.getString(query.getColumnIndex("limit_tracking"))).booleanValue());
                        if (query != null) {
                            query.close();
                        }
                        return aVar;
                    }
                } catch (Exception e) {
                    try {
                        aVar = new a(null, null, false);
                        if (query != null) {
                            query.close();
                        }
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            aVar = new a(null, null, false);
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            query = null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return aVar;
    }
}
