package com.skype.smsmanager.nativesms.contentresolver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.utils.MmsUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class SmsRelayFileProvider extends ContentProvider {
    private static final Pattern a = Pattern.compile(MmsUtils.a + ".\\d{19}." + MmsUtils.b);

    public boolean onCreate() {
        SmsMmsLogger.a("SmsRelayFileProvider", "onCreate()");
        return true;
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    public ParcelFileDescriptor openFile(Uri uri, String fileMode) throws FileNotFoundException {
        String filename = uri.toString();
        String[] parts = filename.split("/");
        boolean matches = a.matcher(parts[parts.length - 1]).find();
        SmsMmsLogger.a("SmsRelayFileProvider", "openFile() mode: " + fileMode + " file: " + filename + " matches pattern: " + matches);
        if (matches) {
            return ParcelFileDescriptor.open(new File(getContext().getCacheDir(), uri.getPath()), TextUtils.equals(fileMode, "r") ? ErrorDialogData.BINDER_CRASH : 738197504);
        }
        throw new FileNotFoundException(filename + " is not suppose to be fetched");
    }
}
