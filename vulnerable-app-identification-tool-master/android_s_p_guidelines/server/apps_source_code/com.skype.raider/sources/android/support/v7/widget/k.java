package android.support.v7.widget;

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
import android.support.v4.graphics.drawable.b;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class k {
    private static final int[] a = new int[]{16843067, 16843068};
    private final ProgressBar b;
    private Bitmap c;

    k(ProgressBar view) {
        this.b = view;
    }

    void a(AttributeSet attrs, int defStyleAttr) {
        aq a = aq.a(this.b.getContext(), attrs, a, defStyleAttr, 0);
        Drawable b = a.b(0);
        if (b != null) {
            ProgressBar progressBar = this.b;
            if (b instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) b;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                Drawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i = 0; i < numberOfFrames; i++) {
                    Drawable a2 = a(animationDrawable.getFrame(i), true);
                    a2.setLevel(10000);
                    animationDrawable2.addFrame(a2, animationDrawable.getDuration(i));
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

    private Drawable a(Drawable drawable, boolean clip) {
        if (drawable instanceof b) {
            Drawable inner = ((b) drawable).a();
            if (inner != null) {
                ((b) drawable).a(a(inner, clip));
            }
        } else if (drawable instanceof LayerDrawable) {
            int i;
            LayerDrawable background = (LayerDrawable) drawable;
            int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];
            for (i = 0; i < N; i++) {
                int id = background.getId(i);
                Drawable drawable2 = background.getDrawable(i);
                boolean z = id == 16908301 || id == 16908303;
                outDrawables[i] = a(drawable2, z);
            }
            Drawable newBg = new LayerDrawable(outDrawables);
            for (i = 0; i < N; i++) {
                newBg.setId(i, background.getId(i));
            }
            return newBg;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap tileBitmap = bitmapDrawable.getBitmap();
            if (this.c == null) {
                this.c = tileBitmap;
            }
            Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(tileBitmap, TileMode.REPEAT, TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return clip ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }

    final Bitmap a() {
        return this.c;
    }
}
