package com.microsoft.b.a;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.IOException;

public final class f implements a {
    private final SQLiteDatabase a;

    public f(SQLiteDatabase database) {
        this.a = database;
    }

    public final void a(String sql) {
        this.a.execSQL(sql);
    }

    public final k b(String sql) {
        try {
            return new h(this.a.compileStatement(sql));
        } catch (SQLException e) {
            throw new g(e);
        }
    }

    public final j a(String sql, String[] params) {
        return new e(this.a.rawQuery(sql, params));
    }

    public final void close() throws IOException {
        this.a.close();
    }
}
