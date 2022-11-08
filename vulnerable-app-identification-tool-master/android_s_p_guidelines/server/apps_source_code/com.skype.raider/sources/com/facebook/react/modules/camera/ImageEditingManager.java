package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.j;
import com.facebook.react.bridge.n;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "ImageEditingManager")
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = new String[]{"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList(new String[]{"file://", "content://"});
    protected static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";

    private static class a extends j<Void, Void> {
        private final Context a;

        /* synthetic */ a(ai x0, byte b) {
            this(x0);
        }

        private a(ai context) {
            super(context);
            this.a = context;
        }

        private void a(File directory) {
            File[] toDelete = directory.listFiles(new FilenameFilter(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$0;
                }

                public final boolean accept(File dir, String filename) {
                    return filename.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (toDelete != null) {
                for (File delete : toDelete) {
                    delete.delete();
                }
            }
        }

        protected final /* synthetic */ void a() {
            a(this.a.getCacheDir());
            File externalCacheDir = this.a.getExternalCacheDir();
            if (externalCacheDir != null) {
                a(externalCacheDir);
            }
        }
    }

    private static class b extends j<Void, Void> {
        final Context a;
        final String b;
        final int c;
        final int d;
        final int e;
        final int f;
        int g;
        int h;
        final d i;
        final d j;

        /* synthetic */ b(ai x0, String x1, int x2, int x3, int x4, int x5, d x6, d x7, byte b) {
            this(x0, x1, x2, x3, x4, x5, x6, x7);
        }

        protected final /* synthetic */ void a() {
            try {
                Bitmap a;
                Options options = new Options();
                int i = (this.g <= 0 || this.h <= 0) ? 0 : 1;
                if (i != 0) {
                    a = a(this.g, this.h, options);
                } else {
                    a = a(options);
                }
                String str = options.outMimeType;
                if (str == null || str.isEmpty()) {
                    throw new IOException("Could not determine MIME type");
                }
                File access$300 = ImageEditingManager.createTempFile(this.a, str);
                ImageEditingManager.writeCompressedBitmapToFile(a, str, access$300);
                if (str.equals("image/jpeg")) {
                    ImageEditingManager.copyExif(this.a, Uri.parse(this.b), access$300);
                }
                this.i.invoke(Uri.fromFile(access$300).toString());
            } catch (Exception e) {
                this.j.invoke(e.getMessage());
            }
        }

        private b(ai context, String uri, int x, int y, int width, int height, d success, d error) {
            super(context);
            this.g = 0;
            this.h = 0;
            if (x < 0 || y < 0 || width <= 0 || height <= 0) {
                throw new n(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", new Object[]{Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(width), Integer.valueOf(height)}));
            }
            this.a = context;
            this.b = uri;
            this.c = x;
            this.d = y;
            this.e = width;
            this.f = height;
            this.i = success;
            this.j = error;
        }

        private InputStream b() throws IOException {
            InputStream stream;
            if (ImageEditingManager.isLocalUri(this.b)) {
                stream = this.a.getContentResolver().openInputStream(Uri.parse(this.b));
            } else {
                stream = new URL(this.b).openConnection().getInputStream();
            }
            if (stream != null) {
                return stream;
            }
            throw new IOException("Cannot open bitmap: " + this.b);
        }

        private Bitmap a(Options outOptions) throws IOException {
            InputStream inputStream = b();
            try {
                Bitmap fullResolutionBitmap = BitmapFactory.decodeStream(inputStream, null, outOptions);
                if (fullResolutionBitmap == null) {
                    throw new IOException("Cannot decode bitmap: " + this.b);
                }
                Bitmap createBitmap = Bitmap.createBitmap(fullResolutionBitmap, this.c, this.d, this.e, this.f);
                return createBitmap;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }

        private Bitmap a(int targetWidth, int targetHeight, Options outOptions) throws IOException {
            com.facebook.infer.annotation.a.a((Object) outOptions);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            InputStream inputStream = b();
            try {
                float newWidth;
                float newHeight;
                float newX;
                float newY;
                float scale;
                BitmapFactory.decodeStream(inputStream, null, options);
                float targetRatio = ((float) targetWidth) / ((float) targetHeight);
                if (((float) this.e) / ((float) this.f) > targetRatio) {
                    newWidth = ((float) this.f) * targetRatio;
                    newHeight = (float) this.f;
                    newX = ((float) this.c) + ((((float) this.e) - newWidth) / 2.0f);
                    newY = (float) this.d;
                    scale = ((float) targetHeight) / ((float) this.f);
                } else {
                    newWidth = (float) this.e;
                    newHeight = ((float) this.e) / targetRatio;
                    newX = (float) this.c;
                    newY = ((float) this.d) + ((((float) this.f) - newHeight) / 2.0f);
                    scale = ((float) targetWidth) / ((float) this.e);
                }
                outOptions.inSampleSize = ImageEditingManager.getDecodeSampleSize(this.e, this.f, targetWidth, targetHeight);
                options.inJustDecodeBounds = false;
                inputStream = b();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, outOptions);
                    if (bitmap == null) {
                        throw new IOException("Cannot decode bitmap: " + this.b);
                    }
                    int cropX = (int) Math.floor((double) (newX / ((float) outOptions.inSampleSize)));
                    int cropY = (int) Math.floor((double) (newY / ((float) outOptions.inSampleSize)));
                    int cropWidth = (int) Math.floor((double) (newWidth / ((float) outOptions.inSampleSize)));
                    int cropHeight = (int) Math.floor((double) (newHeight / ((float) outOptions.inSampleSize)));
                    float cropScale = scale * ((float) outOptions.inSampleSize);
                    Matrix scaleMatrix = new Matrix();
                    scaleMatrix.setScale(cropScale, cropScale);
                    return Bitmap.createBitmap(bitmap, cropX, cropY, cropWidth, cropHeight, scaleMatrix, true);
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
    }

    public ImageEditingManager(ag reactContext) {
        super(reactContext);
        new a(getReactApplicationContext(), (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public void onCatalystInstanceDestroy() {
        new a(getReactApplicationContext(), (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void cropImage(String uri, am options, d success, d error) {
        am offset = options.hasKey("offset") ? options.getMap("offset") : null;
        am size = options.hasKey("size") ? options.getMap("size") : null;
        if (offset == null || size == null || !offset.hasKey("x") || !offset.hasKey("y") || !size.hasKey("width") || !size.hasKey("height")) {
            throw new n("Please specify offset and size");
        } else if (uri == null || uri.isEmpty()) {
            throw new n("Please specify a URI");
        } else {
            b cropTask = new b(getReactApplicationContext(), uri, (int) offset.getDouble("x"), (int) offset.getDouble("y"), (int) size.getDouble("width"), (int) size.getDouble("height"), success, error, (byte) 0);
            if (options.hasKey("displaySize")) {
                am targetSize = options.getMap("displaySize");
                int i = targetSize.getInt("width");
                int i2 = targetSize.getInt("height");
                if (i <= 0 || i2 <= 0) {
                    throw new n(String.format("Invalid target size: [%d, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                }
                cropTask.g = i;
                cropTask.h = i2;
            }
            cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private static void copyExif(Context context, Uri oldImage, File newFile) throws IOException {
        File oldFile = getFileFromUri(context, oldImage);
        if (oldFile == null) {
            FLog.w("React", "Couldn't get real path for uri: " + oldImage);
            return;
        }
        ExifInterface oldExif = new ExifInterface(oldFile.getAbsolutePath());
        ExifInterface newExif = new ExifInterface(newFile.getAbsolutePath());
        for (String attribute : EXIF_ATTRIBUTES) {
            String value = oldExif.getAttribute(attribute);
            if (value != null) {
                newExif.setAttribute(attribute, value);
            }
        }
        newExif.saveAttributes();
    }

    @Nullable
    private static File getFileFromUri(Context context, Uri uri) {
        File file = null;
        if (uri.getScheme().equals("file")) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals("content")) {
            return null;
        }
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                String path = cursor.getString(0);
                if (!TextUtils.isEmpty(path)) {
                    file = new File(path);
                    return file;
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    private static boolean isLocalUri(String uri) {
        for (String localPrefix : LOCAL_URI_PREFIXES) {
            if (uri.startsWith(localPrefix)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtensionForType(@Nullable String mimeType) {
        if ("image/png".equals(mimeType)) {
            return ".png";
        }
        if ("image/webp".equals(mimeType)) {
            return ".webp";
        }
        return ".jpg";
    }

    private static CompressFormat getCompressFormatForType(String type) {
        if ("image/png".equals(type)) {
            return CompressFormat.PNG;
        }
        if ("image/webp".equals(type)) {
            return CompressFormat.WEBP;
        }
        return CompressFormat.JPEG;
    }

    private static void writeCompressedBitmapToFile(Bitmap cropped, String mimeType, File tempFile) throws IOException {
        OutputStream out = new FileOutputStream(tempFile);
        try {
            cropped.compress(getCompressFormatForType(mimeType), 90, out);
        } finally {
            out.close();
        }
    }

    private static File createTempFile(Context context, @Nullable String mimeType) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File internalCacheDir = context.getCacheDir();
        if (externalCacheDir == null && internalCacheDir == null) {
            throw new IOException("No cache directory available");
        }
        File cacheDir;
        if (externalCacheDir != null) {
            if (internalCacheDir == null) {
                cacheDir = externalCacheDir;
            } else if (externalCacheDir.getFreeSpace() > internalCacheDir.getFreeSpace()) {
                cacheDir = externalCacheDir;
            }
            return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(mimeType), cacheDir);
        }
        cacheDir = internalCacheDir;
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(mimeType), cacheDir);
    }

    private static int getDecodeSampleSize(int width, int height, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        if (height > targetWidth || width > targetHeight) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfWidth / inSampleSize >= targetWidth && halfHeight / inSampleSize >= targetHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
