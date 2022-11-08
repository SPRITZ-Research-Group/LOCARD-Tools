package com.airbnb.lottie.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.lottie.c;
import com.airbnb.lottie.f;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class b {
    private final Context a;
    private String b;
    @Nullable
    private c c;
    private final Map<String, f> d;
    private final Map<String, Bitmap> e = new HashMap();

    public b(Callback callback, String imagesFolder, c delegate, Map<String, f> imageAssets) {
        this.b = imagesFolder;
        if (!(TextUtils.isEmpty(imagesFolder) || this.b.charAt(this.b.length() - 1) == '/')) {
            this.b += '/';
        }
        if (callback instanceof View) {
            this.a = ((View) callback).getContext();
            this.d = imageAssets;
            this.c = delegate;
            return;
        }
        this.d = new HashMap();
        this.a = null;
    }

    public final void a(@Nullable c assetDelegate) {
        this.c = assetDelegate;
    }

    @Nullable
    public final Bitmap a(String id) {
        Bitmap bitmap = (Bitmap) this.e.get(id);
        if (bitmap == null) {
            f imageAsset = (f) this.d.get(id);
            if (imageAsset == null) {
                return null;
            }
            if (this.c != null) {
                bitmap = this.c.a();
                if (bitmap != null) {
                    this.e.put(id, bitmap);
                }
                return bitmap;
            }
            try {
                if (TextUtils.isEmpty(this.b)) {
                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                }
                InputStream is = this.a.getAssets().open(this.b + imageAsset.b());
                Options opts = new Options();
                opts.inScaled = true;
                opts.inDensity = 160;
                bitmap = BitmapFactory.decodeStream(is, null, opts);
                this.e.put(id, bitmap);
            } catch (IOException e) {
                return null;
            }
        }
        return bitmap;
    }

    public final void a() {
        Iterator<Entry<String, Bitmap>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            ((Bitmap) ((Entry) it.next()).getValue()).recycle();
            it.remove();
        }
    }

    public final boolean a(Context context) {
        return (context == null && this.a == null) || (context != null && this.a.equals(context));
    }
}
