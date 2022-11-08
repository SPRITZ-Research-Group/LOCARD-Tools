package com.facebook.ads.internal.e;

import android.database.sqlite.SQLiteDatabase;

public abstract class g {
    protected final d k;

    protected g(d dVar) {
        this.k = dVar;
    }

    public static String a(String str, b[] bVarArr) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (int i = 0; i < bVarArr.length - 1; i++) {
            stringBuilder.append(bVarArr[i].b);
            stringBuilder.append(", ");
        }
        stringBuilder.append(bVarArr[bVarArr.length - 1].b);
        stringBuilder.append(" FROM ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private String c() {
        b[] b = b();
        if (b.length <= 0) {
            return null;
        }
        String str = "";
        for (int i = 0; i < b.length - 1; i++) {
            str = str + b[i].a() + ", ";
        }
        return str + b[b.length - 1].a();
    }

    public abstract String a();

    public final void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + a() + " (" + c() + ")");
    }

    public abstract b[] b();

    protected final SQLiteDatabase e() {
        return this.k.a();
    }
}
