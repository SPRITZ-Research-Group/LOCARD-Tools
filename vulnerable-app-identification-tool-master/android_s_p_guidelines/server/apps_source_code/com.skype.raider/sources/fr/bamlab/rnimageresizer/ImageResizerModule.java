package fr.bamlab.rnimageresizer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.b.i;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.e;
import com.facebook.imagepipeline.e.b;
import com.facebook.imagepipeline.k.c;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ImageResizerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "ImageResizerModule";
    private Context context;

    public ImageResizerModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public String getName() {
        return "ImageResizerAndroid";
    }

    @ReactMethod
    public void createResizedImage(String imagePath, int newWidth, int newHeight, String compressFormat, int quality, int rotation, String outputPath, d successCb, d failureCb) {
        try {
            createResizedImageWithExceptions(imagePath, newWidth, newHeight, compressFormat, quality, rotation, outputPath, successCb, failureCb);
        } catch (IOException e) {
            failureCb.invoke(e.getMessage());
        }
    }

    private void createResizedImageWithExceptions(String imagePath, int newWidth, int newHeight, String compressFormatString, int quality, int rotation, String outputPath, d successCb, d failureCb) throws IOException {
        final CompressFormat compressFormat = CompressFormat.valueOf(compressFormatString);
        final d dVar = failureCb;
        final String str = outputPath;
        final int i = quality;
        final d dVar2 = successCb;
        Fresco.b().a(c.a(Uri.parse(imagePath)).a(new e(newWidth, newHeight)).a(rotation != 0 ? RotationOptions.a(rotation) : null).q(), null).a(new b(this) {
            final /* synthetic */ ImageResizerModule f;

            protected final void a(@Nullable Bitmap bitmap) {
                if (bitmap == null) {
                    FLog.e(ImageResizerModule.TAG, "loadBitmap onNewResultImpl null bitmap");
                    dVar.invoke("Error decoding bitmap");
                    return;
                }
                FLog.i(ImageResizerModule.TAG, "loadBitmap onNewResultImpl");
                File path = this.f.context.getCacheDir();
                if (str != null) {
                    path = new File(str);
                }
                try {
                    File newFile = ImageResizerModule.saveImage(bitmap, path, Long.toString(new Date().getTime()), compressFormat, i);
                    if (newFile.isFile()) {
                        ar response = new WritableNativeMap();
                        response.putString("path", newFile.getAbsolutePath());
                        response.putString(ReactVideoViewManager.PROP_SRC_URI, Uri.fromFile(newFile).toString());
                        response.putString("name", newFile.getName());
                        response.putDouble("size", (double) newFile.length());
                        dVar2.invoke(response);
                        return;
                    }
                    FLog.e(ImageResizerModule.TAG, "Error getting resized image path");
                    dVar.invoke("Error getting resized image path");
                } catch (IOException exception) {
                    FLog.e(ImageResizerModule.TAG, "Error creating file: %s", exception.toString());
                    dVar.invoke(exception.toString());
                }
            }

            protected final void f(com.facebook.datasource.c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                FLog.e(ImageResizerModule.TAG, "Failed to load %s", dataSource.f().getLocalizedMessage());
                dVar.invoke("Failed to load image");
            }
        }, i.b());
    }

    private static File saveImage(Bitmap bitmap, File saveDirectory, String fileName, CompressFormat compressFormat, int quality) throws IOException {
        if (bitmap == null) {
            throw new IOException("The bitmap couldn't be resized");
        }
        File newFile = new File(saveDirectory, fileName + "." + compressFormat.name());
        if (newFile.createNewFile()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, quality, outputStream);
            byte[] bitmapData = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
            return newFile;
        }
        throw new IOException("The file already exists");
    }
}
