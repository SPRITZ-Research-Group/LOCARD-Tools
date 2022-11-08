package com.microsoft.b.a;

import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;
import java.io.IOException;

public final class h implements k {
    SQLiteStatement a;

    public h(SQLiteStatement statement) {
        this.a = statement;
    }

    public final void a(int index, double value) {
        this.a.bindDouble(index, value);
    }

    public final void a(int index, String string) {
        this.a.bindString(index, string);
    }

    public final void a(int index) {
        this.a.bindNull(index);
    }

    public final long a() {
        try {
            return this.a.executeInsert();
        } catch (SQLException e) {
            throw new g(e);
        }
    }

    public final int b() {
        try {
            return this.a.executeUpdateDelete();
        } catch (SQLException e) {
            throw new g(e);
        }
    }

    public final void close() throws IOException {
        this.a.close();
    }
}
