package com.skypecam.obscura.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.vision.barcode.Barcode;
import com.skypecam.obscura.b.v;
import com.skypecam.obscura.e.f;
import com.skypecam.obscura.e.g;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public final class j {
    private final v a;
    private com.google.android.gms.vision.barcode.a b;
    private a c;

    public interface a {
        void a(String str);
    }

    public j(v playFactory) {
        this.a = playFactory;
    }

    public final void a(Context context) {
        this.b = this.a.a(context);
    }

    public final boolean a(int frame) {
        return frame % 30 == 0 && this.b != null && this.b.a() && this.c != null;
    }

    public final void a(a qrListener) {
        g.a().b("QRCodeDetector", "setOnHasQRCode");
        this.c = qrListener;
    }

    public final void a(final YuvImage yuv, final int width, final int height) {
        f.a().execute(new Runnable(this) {
            final /* synthetic */ j d;

            public final void run() {
                g.a().b("QRCodeDetector", "onPreviewFrame: completion");
                YuvImage yuvImage = yuv;
                int i = width;
                int i2 = height;
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
                if (bitmap == null) {
                    g.a().c("QRCodeDetector", "onPreviewFrame: null bitmap");
                    return;
                }
                String result = j.a(this.d, bitmap);
                bitmap.recycle();
                if (result != null && this.d.c != null) {
                    g.a().b("QRCodeDetector", "onHasQRCode");
                    this.d.c.a(result);
                }
            }
        });
    }

    static /* synthetic */ String a(j x0, Bitmap x1) {
        if (!x0.b.a()) {
            return null;
        }
        SparseArray a = x0.b.a(new com.google.android.gms.vision.b.a().a(x1).a());
        g.a().b("QRCodeDetector", "detectBarcode results:" + a.size());
        if (a.size() <= 0) {
            return null;
        }
        Barcode barcode = (Barcode) a.get(a.keyAt(0));
        g.a().b("QRCodeDetector", "detectBarcode result:" + barcode.c + " (raw " + barcode.b + ")");
        return barcode.c;
    }
}
