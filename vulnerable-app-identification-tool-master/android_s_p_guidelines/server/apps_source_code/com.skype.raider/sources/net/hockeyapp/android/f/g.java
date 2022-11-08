package net.hockeyapp.android.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class g {
    public static int a(File file) throws IOException {
        Throwable th;
        InputStream input = null;
        try {
            InputStream input2 = new FileInputStream(file);
            try {
                int a = a(input2);
                input2.close();
                return a;
            } catch (Throwable th2) {
                th = th2;
                input = input2;
                if (input != null) {
                    input.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (input != null) {
                input.close();
            }
            throw th;
        }
    }

    public static int a(Context context, Uri uri) {
        InputStream input = null;
        try {
            input = context.getContentResolver().openInputStream(uri);
            int a = a(input);
            if (input == null) {
                return a;
            }
            try {
                input.close();
                return a;
            } catch (IOException e) {
                e.f();
                return a;
            }
        } catch (IOException e2) {
            e.f();
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e3) {
                    e.f();
                }
            }
            return 1;
        } catch (Throwable th) {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e4) {
                    e.f();
                }
            }
        }
    }

    private static int a(InputStream input) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);
        if (options.outWidth == -1 || options.outHeight == -1 || ((float) options.outWidth) / ((float) options.outHeight) <= 1.0f) {
            return 1;
        }
        return 0;
    }

    public static Bitmap a(File file, int reqWidth, int reqHeight) throws IOException {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = a(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap a(Context context, Uri imageUri, int reqWidth, int reqHeight) throws IOException {
        InputStream inputBounds = null;
        InputStream inputBitmap = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            inputBounds = context.getContentResolver().openInputStream(imageUri);
            BitmapFactory.decodeStream(inputBounds, null, options);
            options.inSampleSize = a(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            inputBitmap = context.getContentResolver().openInputStream(imageUri);
            Bitmap decodeStream = BitmapFactory.decodeStream(inputBitmap, null, options);
            return decodeStream;
        } finally {
            if (inputBounds != null) {
                inputBounds.close();
            }
            if (inputBitmap != null) {
                inputBitmap.close();
            }
        }
    }

    private static int a(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
