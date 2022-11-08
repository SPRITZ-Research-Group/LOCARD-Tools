package com.microsoft.react.mediapicker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.facebook.imagepipeline.k.a;
import com.microsoft.react.a.c;

final class f extends a {
    private final c b;

    f(c mediaFileExtendedData) {
        this.b = mediaFileExtendedData;
    }

    public final void a(Bitmap output, Bitmap source) {
        int orientation = this.b.a.e;
        if (orientation != 0) {
            Canvas canvas = new Canvas(output);
            int middleX = canvas.getWidth() / 2;
            int middleY = canvas.getHeight() / 2;
            canvas.rotate((float) orientation, (float) middleX, (float) middleY);
            canvas.drawBitmap(source, 0.0f, 0.0f, null);
            canvas.rotate((float) (-orientation), (float) middleX, (float) middleY);
            return;
        }
        super.a(output, source);
    }
}
