package com.skype.snapshot;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ah;
import com.facebook.react.uimanager.l;
import com.skype.snapshot.MathUtils.Rect;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class SnapshotModule extends ReactContextBaseJavaModule {
    private static final CompressFormat DEFAULT_FORMAT = CompressFormat.JPEG;
    private static final int DEFAULT_QUALITY = 80;
    private static final long MINIMAL_PICTURE_MEMORY = 4194304;
    private static final String RN_CLASS = "SnapshotManager";
    private final ai reactContext;

    public SnapshotModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return RN_CLASS;
    }

    @ReactMethod
    public void takeWindowSnapshot(final am options, final ae promise) {
        ((UIManagerModule) this.reactContext.b(UIManagerModule.class)).addUIBlock(new ah(this) {
            final /* synthetic */ SnapshotModule c;

            public final void a(l nativeViewHierarchyManager) {
                if (this.c.getCurrentActivity() != null) {
                    this.c.intTakeSnapshot(this.c.getCurrentActivity().getWindow().getDecorView().getRootView(), options, promise);
                    return;
                }
                promise.a(new Throwable("Snapshot failed"));
            }
        });
    }

    @ReactMethod
    public void takeSnapshot(final int reactTag, final am options, final ae promise) {
        ((UIManagerModule) this.reactContext.b(UIManagerModule.class)).addUIBlock(new ah(this) {
            final /* synthetic */ SnapshotModule d;

            public final void a(l nativeViewHierarchyManager) {
                this.d.intTakeSnapshot(nativeViewHierarchyManager.a(reactTag), options, promise);
            }
        });
    }

    private void intTakeSnapshot(View view, am options, ae promise) {
        if (view != null) {
            Bitmap bitmap = bitmapFromView(view);
            if (bitmap != null) {
                CompressFormat format = options != null ? (options.hasKey("format") && options.getString("format").equalsIgnoreCase("png")) ? CompressFormat.PNG : CompressFormat.JPEG : DEFAULT_FORMAT;
                promise.a("file:" + saveImage(bitmap, this.reactContext.getCacheDir(), Long.toString(new Date().getTime()), format, 80));
                return;
            }
        }
        promise.a(new Throwable("Snapshot failed"));
    }

    @Nullable
    private static Bitmap bitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        float scaleFactor = 1.0f;
        Runtime runtime = Runtime.getRuntime();
        long availableMemory = (runtime.maxMemory() - runtime.totalMemory()) + runtime.freeMemory();
        if (availableMemory < ((long) ((width * height) * 4))) {
            if (availableMemory >= MINIMAL_PICTURE_MEMORY) {
                Rect rect = MathUtils.a(width, height, availableMemory);
                scaleFactor = rect.c;
                width = rect.a;
                height = rect.b;
            } else {
                FLog.i("React", "We can't scale that low: available memory is: " + availableMemory);
                return null;
            }
        }
        if (width <= 0 || height <= 0) {
            FLog.i("React", "Width and height should be non 0");
            return null;
        }
        Bitmap returnedBitmap = null;
        try {
            returnedBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else {
                canvas.drawColor(0);
            }
            canvas.scale(scaleFactor, scaleFactor);
            view.draw(canvas);
            return returnedBitmap;
        } catch (Throwable e) {
            FLog.i("React", "Can't allocate memory for screen shot", e);
            return returnedBitmap;
        }
    }

    private static String saveImage(Bitmap bitmap, File saveDirectory, String fileName, CompressFormat compressFormat, int quality) {
        if (bitmap == null) {
            return null;
        }
        try {
            File newFile = new File(saveDirectory, fileName + "." + (compressFormat == CompressFormat.JPEG ? "jpg" : compressFormat.name()));
            if (!newFile.createNewFile()) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, quality, outputStream);
            byte[] bitmapData = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
            return newFile.getAbsolutePath();
        } catch (IOException e) {
            return null;
        }
    }
}
