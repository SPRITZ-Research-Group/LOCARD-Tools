package com.skype4life.syncadapter;

import android.database.Cursor;
import android.support.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class f extends b {
    private final int a;
    @Nullable
    private h b;
    private final String c;
    private final boolean d;
    private Set<String> e = new HashSet();
    private String f;

    private f(String contactId, String displayName, int displayNameSource, boolean hasPhoneNumber) {
        super(contactId);
        this.c = displayName;
        this.a = displayNameSource;
        this.d = hasPhoneNumber;
    }

    public static f a(Cursor cursor) {
        return new f(cursor.getString(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("display_name")), cursor.getInt(cursor.getColumnIndex("display_name_source")), !cursor.getString(cursor.getColumnIndex("has_phone_number")).equals("0"));
    }

    public final int c() {
        return this.a;
    }

    public final boolean d() {
        return this.d;
    }

    public final h e() {
        return this.b;
    }

    public final boolean f() {
        return this.b == null;
    }

    public final void a(h nativeRawContact) {
        this.b = nativeRawContact;
    }

    public final String g() {
        return this.c;
    }

    public final Set<String> h() {
        return this.e;
    }

    public final void a(Set<String> phoneNumbers) {
        this.e = phoneNumbers;
    }

    public final String i() {
        return this.f;
    }

    public final void a(String skypeId) {
        this.f = skypeId;
    }
}
