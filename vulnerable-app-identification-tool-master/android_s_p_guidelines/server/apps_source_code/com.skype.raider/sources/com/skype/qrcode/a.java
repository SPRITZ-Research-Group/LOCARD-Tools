package com.skype.qrcode;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.ae;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.e;
import com.google.a.h;
import java.util.EnumMap;
import javax.annotation.Nullable;

@SuppressLint({"AppCompatCustomView"})
final class a extends ImageView {
    @Nullable
    private String a;
    @Nullable
    private String b;
    private int c = -16777216;
    private int d = -1;

    a(ae context) {
        super(context);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public final void a(int color) {
        this.c = color;
    }

    public final void b(int backgroundColor) {
        this.d = backgroundColor;
    }

    public final void a(@Nullable String message) {
        this.a = message;
    }

    public final void b(@Nullable String correctionLevel) {
        this.b = correctionLevel;
    }

    public final void a() {
        setImageBitmap(b());
    }

    protected final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        a();
    }

    private Bitmap b() {
        if (this.a == null) {
            FLog.w("QRCodeView", "Trying to generated bitmap for not yet set message");
            return null;
        }
        try {
            b bmx = new e().a(this.a, com.google.a.a.QR_CODE, getWidth(), getHeight(), new EnumMap<c, Object>(this, c.class) {
                final /* synthetic */ a a;
            });
            int width = bmx.b();
            int height = bmx.c();
            int[] pixels = new int[(width * height)];
            int offset = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bmx.a(x, y) ? this.c : this.d;
                }
                offset = y * width;
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (h e) {
            FLog.w("QRCodeView", "Failed to write bitmap with: " + e.getMessage());
            return null;
        }
    }
}
