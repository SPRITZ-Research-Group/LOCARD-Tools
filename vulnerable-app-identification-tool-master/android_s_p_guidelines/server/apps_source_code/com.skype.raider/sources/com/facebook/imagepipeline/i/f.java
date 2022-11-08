package com.facebook.imagepipeline.i;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.image.e;
import javax.annotation.Nullable;

public interface f {
    a<Bitmap> decodeFromEncodedImageWithColorSpace(e eVar, Config config, @Nullable Rect rect, boolean z);

    a<Bitmap> decodeJPEGFromEncodedImage(e eVar, Config config, @Nullable Rect rect, int i);

    a<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(e eVar, Config config, @Nullable Rect rect, int i, boolean z);
}
