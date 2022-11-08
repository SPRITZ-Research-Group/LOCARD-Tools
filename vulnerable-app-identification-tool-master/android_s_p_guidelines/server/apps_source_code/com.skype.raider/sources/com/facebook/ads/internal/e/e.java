package com.facebook.ads.internal.e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class e extends SQLiteOpenHelper {
    private final d a;

    public e(Context context, d dVar) {
        super(context, "ads.db", null, 4);
        if (dVar == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.a = dVar;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        g[] c = this.a.c();
        for (int i = 0; i < 2; i++) {
            c[i].a(sQLiteDatabase);
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        g[] c = this.a.c();
        for (int i3 = 0; i3 < 2; i3++) {
            g gVar = c[i3];
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + gVar.a());
            gVar.a(sQLiteDatabase);
        }
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            b bVar = c.i;
            sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN " + bVar.b + " " + bVar.c + " DEFAULT 0");
        }
    }
}
