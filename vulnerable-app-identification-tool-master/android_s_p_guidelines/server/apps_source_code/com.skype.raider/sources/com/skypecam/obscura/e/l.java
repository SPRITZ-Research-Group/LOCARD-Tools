package com.skypecam.obscura.e;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.concurrent.Callable;

public final class l implements Callable<String> {
    private final String a;
    private final int b;
    private final float c;

    private static String a(String filePath, int compressionRate, float maxDimension) {
        try {
            g.a().b("VideoThumbnailUtil", "generateThumbnail");
            File f = File.createTempFile("scv_" + System.currentTimeMillis(), ".jpg");
            FileOutputStream fos = new FileOutputStream(f.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bmp = BitmapFactory.decodeFile(filePath);
            if (bmp == null) {
                g.a().d("VideoThumbnailUtil", "generateThumbnail file was null");
                return "";
            }
            float max = Math.max((float) bmp.getWidth(), (float) bmp.getHeight()) / maxDimension;
            if (max > 1.0f) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bmp, (int) Math.floor((double) (((float) bmp.getWidth()) / max)), (int) Math.floor((double) (((float) bmp.getHeight()) / max)), false);
                if (createScaledBitmap != bmp) {
                    bmp.recycle();
                }
                bmp = createScaledBitmap;
            }
            bmp.compress(CompressFormat.JPEG, compressionRate, stream);
            bmp.recycle();
            fos.write(stream.toByteArray());
            fos.close();
            g.a().b("VideoThumbnailUtil", "generateThumbnail - done");
            return URI.create(f.getPath()).getPath();
        } catch (Exception e) {
            g.a().a("VideoThumbnailUtil", "generateThumbnail exception ", e);
            return "";
        }
    }

    public l(String filePath, int compressionRate, float maxDimension) {
        this.a = filePath;
        this.b = compressionRate;
        this.c = maxDimension;
    }

    public final /* synthetic */ Object call() throws Exception {
        return a(this.a, this.b, this.c);
    }
}
