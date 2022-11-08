package com.microsoft.b.a;

import android.database.Cursor;
import java.io.IOException;

public final class e implements j {
    Cursor a;

    public e(Cursor cursor) {
        this.a = cursor;
    }

    public final boolean a() {
        return this.a.moveToFirst();
    }

    public final boolean b() {
        return this.a.moveToNext();
    }

    public final int c() {
        return this.a.getColumnCount();
    }

    public final String a(int index) {
        return this.a.getColumnName(index);
    }

    public final int b(int index) {
        return this.a.getType(index);
    }

    public final long c(int index) {
        return this.a.getLong(index);
    }

    public final double d(int index) {
        return this.a.getDouble(index);
    }

    public final String e(int index) {
        return this.a.getString(index);
    }

    public final byte[] f(int index) {
        return this.a.getBlob(index);
    }

    public final void close() throws IOException {
        this.a.close();
    }
}
