package com.facebook.common.i;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract;
import android.provider.MediaStore.Images.Media;
import com.adjust.sdk.Constants;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;

public final class f {
    private static final Uri a = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");

    @Nullable
    public static URL a(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean b(@Nullable Uri uri) {
        String scheme = k(uri);
        return Constants.SCHEME.equals(scheme) || "http".equals(scheme);
    }

    public static boolean c(@Nullable Uri uri) {
        return "file".equals(k(uri));
    }

    public static boolean d(@Nullable Uri uri) {
        return "content".equals(k(uri));
    }

    public static boolean e(Uri uri) {
        return d(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(a.getPath());
    }

    public static boolean f(Uri uri) {
        String uriString = uri.toString();
        return uriString.startsWith(Media.EXTERNAL_CONTENT_URI.toString()) || uriString.startsWith(Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean g(@Nullable Uri uri) {
        return "asset".equals(k(uri));
    }

    public static boolean h(@Nullable Uri uri) {
        return "res".equals(k(uri));
    }

    public static boolean i(@Nullable Uri uri) {
        return "android.resource".equals(k(uri));
    }

    public static boolean j(@Nullable Uri uri) {
        return "data".equals(k(uri));
    }

    @Nullable
    private static String k(@Nullable Uri uri) {
        return uri == null ? null : uri.getScheme();
    }

    @Nullable
    public static String a(ContentResolver contentResolver, Uri srcUri) {
        String result = null;
        if (d(srcUri)) {
            Cursor cursor = null;
            try {
                cursor = contentResolver.query(srcUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int idx = cursor.getColumnIndex("_data");
                    if (idx != -1) {
                        result = cursor.getString(idx);
                    }
                }
                if (cursor == null) {
                    return result;
                }
                cursor.close();
                return result;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else if (c(srcUri)) {
            return srcUri.getPath();
        } else {
            return null;
        }
    }

    public static Uri a(int resourceId) {
        return new Builder().scheme("res").path(String.valueOf(resourceId)).build();
    }
}
