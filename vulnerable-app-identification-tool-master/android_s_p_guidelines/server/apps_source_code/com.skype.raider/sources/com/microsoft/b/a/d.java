package com.microsoft.b.a;

import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public final class d implements b {

    private class a implements DatabaseErrorHandler {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        /* synthetic */ a(d x0, byte b) {
            this(x0);
        }

        public final void onCorruption(SQLiteDatabase dbObj) {
            throw new SQLException("Database is corrupted");
        }
    }

    public final a a(String databasePath) {
        try {
            return new f(SQLiteDatabase.openDatabase(databasePath, null, ErrorDialogData.BINDER_CRASH, new a()));
        } catch (SQLException e) {
            throw new g(e);
        }
    }
}
