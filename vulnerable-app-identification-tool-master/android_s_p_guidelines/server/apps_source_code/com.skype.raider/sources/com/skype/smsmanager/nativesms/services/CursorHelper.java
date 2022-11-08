package com.skype.smsmanager.nativesms.services;

import android.database.Cursor;
import android.support.annotation.NonNull;

public class CursorHelper {
    private final Cursor a;

    public CursorHelper(@NonNull Cursor cursor) {
        this.a = cursor;
    }

    public final String a(@NonNull String columnName) throws IllegalArgumentException {
        return this.a.getString(this.a.getColumnIndexOrThrow(columnName));
    }
}
