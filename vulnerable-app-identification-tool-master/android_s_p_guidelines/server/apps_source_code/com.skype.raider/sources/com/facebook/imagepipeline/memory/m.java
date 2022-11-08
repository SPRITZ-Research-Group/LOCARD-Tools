package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.e.b;

public final class m implements d {
    public final /* synthetic */ void a(Object obj) {
        ((Bitmap) obj).recycle();
    }

    public final void a(b trimType) {
    }

    public final /* synthetic */ Object a(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Config.RGB_565);
    }
}
