package com.skype4life.syncadapter;

import android.database.Cursor;
import java.util.Objects;

public final class i {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    private i(String id, String skypeId, String phoneNumber, String actionName, String mimeType) {
        this.a = id;
        this.b = skypeId;
        this.c = phoneNumber;
        this.d = actionName;
        this.e = mimeType;
    }

    public static i a(Cursor cursor, g capability) {
        switch (capability) {
            case FREE:
                return new i(cursor.getString(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("data1")), null, cursor.getString(cursor.getColumnIndex("data4")), cursor.getString(cursor.getColumnIndex("mimetype")));
            case PSTN:
                return new i(cursor.getString(cursor.getColumnIndex("_id")), null, cursor.getString(cursor.getColumnIndex("data1")), cursor.getString(cursor.getColumnIndex("data4")), cursor.getString(cursor.getColumnIndex("mimetype")));
            default:
                return null;
        }
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((i) o).a);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.a});
    }
}
