package android.support.v4.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.provider.FontsContractCompat;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(21)
@RestrictTo({a.LIBRARY_GROUP})
class d extends g {
    d() {
    }

    private static File a(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Typeface a(Context context, @NonNull FontsContractCompat.a[] fonts, int style) {
        ParcelFileDescriptor pfd;
        InputStream fis;
        Throwable th;
        Throwable th2;
        if (fonts.length <= 0) {
            return null;
        }
        FontsContractCompat.a bestFont = a(fonts, style);
        try {
            pfd = context.getContentResolver().openFileDescriptor(bestFont.a(), "r", null);
            try {
                File file = a(pfd);
                Typeface a;
                if (file == null || !file.canRead()) {
                    fis = new FileInputStream(pfd.getFileDescriptor());
                    try {
                        a = g.a(context, fis);
                        fis.close();
                        if (pfd != null) {
                            pfd.close();
                        }
                        return a;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    a = Typeface.createFromFile(file);
                    if (pfd != null) {
                        pfd.close();
                    }
                    return a;
                }
            } catch (Throwable th22) {
                Throwable th4 = th22;
                th22 = th;
                th = th4;
            }
        } catch (IOException e) {
            return null;
        }
        throw th;
        throw th;
        if (pfd != null) {
            if (th22 == null) {
                pfd.close();
            } else {
                try {
                    pfd.close();
                } catch (Throwable th5) {
                    th22.addSuppressed(th5);
                }
            }
        }
        throw th;
        if (th22 != null) {
            fis.close();
        } else {
            fis.close();
        }
        throw th;
    }
}
