package com.skype.reactnativesprites;

import android.content.res.Resources;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

public class ManagedRegionDecoder {
    private BitmapRegionDecoder a;
    private Resources b;

    public ManagedRegionDecoder(Resources resources, @NonNull InputStream stream) throws IOException {
        this.b = resources;
        try {
            this.a = BitmapRegionDecoder.newInstance(stream, true);
        } finally {
            stream.close();
        }
    }

    public final int a() {
        return this.a.getHeight();
    }

    public final int b() {
        return this.a.getWidth();
    }

    public final Drawable a(Rect rect) {
        return new BitmapDrawable(this.b, this.a.decodeRegion(rect, null));
    }

    public final void c() {
        if (this.a != null) {
            this.a.recycle();
        }
    }
}
