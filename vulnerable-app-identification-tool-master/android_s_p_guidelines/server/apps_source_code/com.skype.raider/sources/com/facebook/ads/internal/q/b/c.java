package com.facebook.ads.internal.q.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import com.facebook.ads.internal.q.a.u;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class c {
    private static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 >= i2 && i4 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap a(b bVar) {
        byte[] decode = Base64.decode(bVar.a(u.b), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    @WorkerThread
    public static Bitmap a(InputStream inputStream, int i, int i2) {
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(8192);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, null, options);
        try {
            bufferedInputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options.inSampleSize = a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(bufferedInputStream, null, options);
    }

    @WorkerThread
    public static Bitmap a(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }
}
