package com.imagepicker.c;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import java.io.File;

public final class d {
    @Nullable
    public static Uri a(@NonNull Context context, @NonNull File file) {
        Uri result = null;
        if (VERSION.SDK_INT < 21) {
            return Uri.fromFile(file);
        }
        try {
            return FileProvider.a(context, new StringBuilder(context.getApplicationContext().getPackageName()).append(".provider").toString(), file);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return result;
        }
    }

    public static String a(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            return string;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
