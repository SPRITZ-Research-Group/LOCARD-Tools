package com.facebook.imageutils;

import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.logging.FLog;
import java.io.InputStream;

public final class HeifExifUtil {

    @DoNotStrip
    private static class HeifExifUtilAndroidN {
        private HeifExifUtilAndroidN() {
        }

        @RequiresApi(api = 24)
        static int getOrientation(InputStream inputStream) {
            try {
                return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
            } catch (Throwable e) {
                FLog.d("HeifExifUtil", "Failed reading Heif Exif orientation -> ignoring", e);
                return 0;
            }
        }
    }

    public static int a(InputStream inputStream) {
        if (VERSION.SDK_INT >= 24) {
            return HeifExifUtilAndroidN.getOrientation(inputStream);
        }
        FLog.d("HeifExifUtil", "Trying to read Heif Exif information before Android N -> ignoring");
        return 0;
    }
}
