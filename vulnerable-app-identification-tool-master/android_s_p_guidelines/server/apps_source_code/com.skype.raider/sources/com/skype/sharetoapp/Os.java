package com.skype.sharetoapp;

import android.os.ParcelFileDescriptor;
import android.os.Process;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;

public class Os {
    private static native int getFileOwner(int i);

    static {
        System.loadLibrary("OsUtils");
    }

    private static int a(FileDescriptor descriptor) {
        try {
            Field field = descriptor.getClass().getDeclaredField("descriptor");
            field.setAccessible(true);
            return getFileOwner(((Integer) field.get(descriptor)).intValue());
        } catch (Throwable e) {
            IllegalStateException illegalStateException = new IllegalStateException(e);
        }
    }

    public static boolean a(File file) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        int fileOwnerId = -1;
        try {
            parcelFileDescriptor = ParcelFileDescriptor.open(file, ErrorDialogData.BINDER_CRASH);
            fileOwnerId = a(parcelFileDescriptor.getFileDescriptor());
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e2) {
                }
            }
        }
        if (fileOwnerId == Process.myUid()) {
            return true;
        }
        return false;
    }
}
