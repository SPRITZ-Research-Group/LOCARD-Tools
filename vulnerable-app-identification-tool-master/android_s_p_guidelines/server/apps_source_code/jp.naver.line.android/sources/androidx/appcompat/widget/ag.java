package androidx.appcompat.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.graphics.drawable.g;

class ag {
    private static final int[] a = new int[]{16843067, 16843068};
    private final ProgressBar b;
    private Bitmap c;

    ag(ProgressBar progressBar) {
        this.b = progressBar;
    }

    void a(AttributeSet attributeSet, int i) {
        bu a = bu.a(this.b.getContext(), attributeSet, a, i, 0);
        Drawable b = a.b(0);
        if (b != null) {
            ProgressBar progressBar = this.b;
            if (b instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) b;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                Drawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable a2 = a(animationDrawable.getFrame(i2), true);
                    a2.setLevel(10000);
                    animationDrawable2.addFrame(a2, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                b = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(b);
        }
        b = a.b(1);
        if (b != null) {
            this.b.setProgressDrawable(a(b, false));
        }
        a.a();
    }

    private Drawable a(Drawable drawable, boolean z) {
        Drawable a;
        if (drawable instanceof g) {
            g gVar = (g) drawable;
            a = gVar.a();
            if (a != null) {
                gVar.a(a(a, z));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                Drawable drawable2 = layerDrawable.getDrawable(i);
                boolean z2 = id == 16908301 || id == 16908303;
                drawableArr[i] = a(drawable2, z2);
            }
            a = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                a.setId(i2, layerDrawable.getId(i2));
            }
            return a;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.c == null) {
                this.c = bitmap;
            }
            Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }

    final Bitmap a() {
        return this.c;
    }
}
