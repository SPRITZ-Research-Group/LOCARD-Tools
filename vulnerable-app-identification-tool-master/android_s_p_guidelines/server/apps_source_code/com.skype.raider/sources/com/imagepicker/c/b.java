package com.imagepicker.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.am;
import com.imagepicker.ImagePickerModule;
import com.imagepicker.c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;

public final class b {

    public static class a {
        public final int a;
        public final Throwable b;

        public a(int currentRotation, @Nullable Throwable error) {
            this.a = currentRotation;
            this.b = error;
        }
    }

    public static class b {
        public final com.imagepicker.a.a a;
        public final Throwable b;

        public b(@NonNull com.imagepicker.a.a imageConfig, @Nullable Throwable error) {
            this.a = imageConfig;
            this.b = error;
        }
    }

    @Nullable
    public static File a(@NonNull Context reactContext, @NonNull am options, @NonNull boolean forceLocal) {
        File path;
        String filename = "image-" + UUID.randomUUID().toString() + ".jpg";
        if (!c.a(am.class, options, "storageOptions") || forceLocal) {
            path = reactContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
        File result = new File(path, filename);
        try {
            path.mkdirs();
            result.createNewFile();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int a(int imageHeight, int maxWidth, int maxHeight) {
        int inSampleSize = 1;
        if (imageHeight > maxHeight || imageHeight > maxWidth) {
            int halfHeight = imageHeight / 2;
            int halfWidth = imageHeight / 2;
            while (halfHeight / inSampleSize > maxHeight && halfWidth / inSampleSize > maxWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @NonNull
    public static com.imagepicker.a.a a(@NonNull Context context, @NonNull am options, @NonNull com.imagepicker.a.a imageConfig, int initialWidth, int initialHeight, int requestCode) {
        Options imageOptions = new Options();
        imageOptions.inSampleSize = a(initialHeight, imageConfig.c, imageConfig.d);
        imageOptions.inScaled = false;
        Bitmap photo = BitmapFactory.decodeFile(imageConfig.a.getAbsolutePath(), imageOptions);
        if (photo == null) {
            return null;
        }
        com.imagepicker.a.a result;
        double ratio;
        com.imagepicker.a.a result2 = imageConfig;
        if (imageConfig.c == 0 || imageConfig.c > initialWidth) {
            result = new com.imagepicker.a.a(result2.a, result2.b, initialWidth, result2.d, result2.e, result2.f, result2.g);
        } else {
            result = result2;
        }
        if (imageConfig.d == 0 || imageConfig.c > initialHeight) {
            result = new com.imagepicker.a.a(result.a, result.b, result.c, initialHeight, result.e, result.f, result.g);
        }
        double widthRatio = ((double) result.c) / ((double) initialWidth);
        double heightRatio = ((double) result.d) / ((double) initialHeight);
        if (widthRatio < heightRatio) {
            ratio = widthRatio;
        } else {
            ratio = heightRatio;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) result.f);
        matrix.postScale((float) ratio, (float) ratio);
        try {
            switch (new ExifInterface(result.a.getAbsolutePath()).getAttributeInt("Orientation", 0)) {
                case 3:
                    matrix.postRotate(180.0f);
                    break;
                case 6:
                    matrix.postRotate(90.0f);
                    break;
                case 8:
                    matrix.postRotate(270.0f);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap scaledPhoto = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        scaledPhoto.compress(CompressFormat.JPEG, result.e, bytes);
        File resized = a(context, options, !(requestCode == 13001));
        if (resized == null) {
            if (photo != null) {
                photo.recycle();
            }
            if (scaledPhoto == null) {
                return imageConfig;
            }
            scaledPhoto.recycle();
            return imageConfig;
        }
        result = result.b(resized);
        try {
            new FileOutputStream(result.b).write(bytes.toByteArray());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (photo != null) {
            photo.recycle();
        }
        if (scaledPhoto != null) {
            scaledPhoto.recycle();
        }
        return result;
    }

    public static void a(int requestCode, @NonNull com.imagepicker.a.a imageConfig) {
        if (requestCode == ImagePickerModule.REQUEST_LAUNCH_IMAGE_CAPTURE) {
            if (imageConfig.a != null && imageConfig.a.exists()) {
                imageConfig.a.delete();
            }
            if (imageConfig.b != null && imageConfig.b.exists()) {
                imageConfig.b.delete();
            }
        }
    }

    public static void a(@Nullable Context reactContext, @NonNull String path) {
        if (reactContext != null) {
            MediaScannerConnection.scanFile(reactContext, new String[]{path}, null, new OnScanCompletedListener() {
                public final void onScanCompleted(String path, Uri uri) {
                }
            });
        }
    }

    public static a a(@NonNull c responseHelper, @NonNull com.imagepicker.a.a imageConfig) {
        int currentRotation = 0;
        try {
            ExifInterface exif = new ExifInterface(imageConfig.a.getAbsolutePath());
            float[] latlng = new float[2];
            exif.getLatLong(latlng);
            float latitude = latlng[0];
            float longitude = latlng[1];
            if (!(latitude == 0.0f && longitude == 0.0f)) {
                responseHelper.a("latitude", (double) latitude);
                responseHelper.a("longitude", (double) longitude);
            }
            String timestamp = exif.getAttribute("DateTime");
            SimpleDateFormat exifDatetimeFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                responseHelper.a("timestamp", new StringBuilder(isoFormat.format(exifDatetimeFormat.parse(timestamp))).append("Z").toString());
            } catch (Exception e) {
            }
            boolean isVertical = true;
            switch (exif.getAttributeInt("Orientation", 1)) {
                case 3:
                    currentRotation = 180;
                    break;
                case 6:
                    isVertical = false;
                    currentRotation = 90;
                    break;
                case 8:
                    isVertical = false;
                    currentRotation = 270;
                    break;
            }
            responseHelper.a("originalRotation", currentRotation);
            responseHelper.a("isVertical", isVertical);
            return new a(currentRotation, null);
        } catch (IOException e2) {
            e2.printStackTrace();
            return new a(0, e2);
        }
    }

    @Nullable
    public static b a(@NonNull com.imagepicker.a.a imageConfig) {
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2 = null;
        File oldFile = imageConfig.b == null ? imageConfig.a : imageConfig.b;
        File newFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath(), oldFile.getName());
        try {
            FileChannel channel = new FileInputStream(oldFile).getChannel();
            try {
                fileChannel2 = new FileOutputStream(newFile).getChannel();
                channel.transferTo(0, channel.size(), fileChannel2);
                oldFile.delete();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileChannel = channel;
                th = th3;
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        throw th;
                    }
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                throw th;
            }
            try {
                com.imagepicker.a.a newImageConfig;
                if (imageConfig.b != null) {
                    newImageConfig = imageConfig.b(newFile);
                } else {
                    newImageConfig = imageConfig.a(newFile);
                }
                return new b(newImageConfig, null);
            } catch (IOException e3) {
                e3.printStackTrace();
                return new b(imageConfig, e3);
            }
        } catch (Throwable th4) {
            th = th4;
            fileChannel = null;
        }
    }
}
