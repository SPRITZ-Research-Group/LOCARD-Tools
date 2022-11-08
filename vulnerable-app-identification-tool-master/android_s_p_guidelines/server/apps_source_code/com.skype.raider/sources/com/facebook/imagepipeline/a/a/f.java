package com.facebook.imagepipeline.a.a;

import android.graphics.Bitmap;
import com.facebook.common.f.a;
import java.util.Collection;
import java.util.List;

public final class f {
    private final c a;
    private a<Bitmap> b;
    private List<a<Bitmap>> c;
    private int d;

    f(c image) {
        this.a = image;
    }

    public final c a() {
        return this.a;
    }

    public final a<Bitmap> b() {
        return a.b(this.b);
    }

    public final f a(a<Bitmap> previewBitmap) {
        this.b = a.b(previewBitmap);
        return this;
    }

    public final int c() {
        return this.d;
    }

    public final f a(int frameForPreview) {
        this.d = frameForPreview;
        return this;
    }

    public final List<a<Bitmap>> d() {
        return a.a(this.c);
    }

    public final f a(List<a<Bitmap>> decodedFrames) {
        this.c = a.a((Collection) decodedFrames);
        return this;
    }

    public final e e() {
        try {
            e eVar = new e(this);
            return eVar;
        } finally {
            a.c(this.b);
            this.b = null;
            a.a(this.c);
            this.c = null;
        }
    }
}
