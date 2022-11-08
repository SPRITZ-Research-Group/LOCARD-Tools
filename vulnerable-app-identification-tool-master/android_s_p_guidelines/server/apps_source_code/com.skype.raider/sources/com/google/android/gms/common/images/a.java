package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.internal.c;
import com.google.android.gms.common.internal.z;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public abstract class a {
    final b a;
    protected int b;
    protected int c;

    public static final class a extends a {
        private WeakReference<com.google.android.gms.common.images.ImageManager.a> d;

        protected final void a() {
            this.d.get();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            com.google.android.gms.common.images.ImageManager.a aVar2 = (com.google.android.gms.common.images.ImageManager.a) this.d.get();
            com.google.android.gms.common.images.ImageManager.a aVar3 = (com.google.android.gms.common.images.ImageManager.a) aVar.d.get();
            return aVar3 != null && aVar2 != null && z.a(aVar3, aVar2) && z.a(aVar.a, this.a);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a});
        }
    }

    static final class b {
        public final Uri a;

        public b(Uri uri) {
            this.a = uri;
        }

        public final boolean equals(Object obj) {
            return !(obj instanceof b) ? false : this == obj ? true : z.a(((b) obj).a, this.a);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a});
        }
    }

    protected abstract void a();

    final void a(Context context, c cVar) {
        if (this.b != 0) {
            int i = this.b;
            Resources resources = context.getResources();
            if (this.c > 0) {
                com.google.android.gms.common.images.internal.c.a aVar = new com.google.android.gms.common.images.internal.c.a(i, this.c);
                if (((Drawable) cVar.a((Object) aVar)) == null) {
                    Object drawable = resources.getDrawable(i);
                    if ((this.c & 1) != 0) {
                        Bitmap bitmap;
                        if (drawable == null) {
                            bitmap = null;
                        } else if (drawable instanceof BitmapDrawable) {
                            bitmap = ((BitmapDrawable) drawable).getBitmap();
                        } else {
                            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap);
                            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                            drawable.draw(canvas);
                            bitmap = createBitmap;
                        }
                        drawable = new BitmapDrawable(resources, com.google.android.gms.common.images.internal.b.a(bitmap));
                    }
                    cVar.a(aVar, drawable);
                }
            } else {
                resources.getDrawable(i);
            }
        }
        a();
    }

    final void a(Context context, Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("null reference");
        }
        if ((this.c & 1) != 0) {
            bitmap = com.google.android.gms.common.images.internal.b.a(bitmap);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        a();
    }
}
