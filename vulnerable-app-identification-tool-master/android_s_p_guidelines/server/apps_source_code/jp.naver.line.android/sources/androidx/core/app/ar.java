package androidx.core.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public abstract class ar {
    private Matrix a;

    public final Parcelable a(View view, Matrix matrix, RectF rectF) {
        Parcelable parcelable;
        ar arVar = this;
        View view2 = view;
        RectF rectF2 = rectF;
        if (view2 instanceof ImageView) {
            ImageView imageView = (ImageView) view2;
            Drawable drawable = imageView.getDrawable();
            Drawable background = imageView.getBackground();
            if (drawable != null && background == null) {
                Parcelable parcelable2;
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                    parcelable2 = null;
                } else {
                    float min = Math.min(1.0f, 1048576.0f / ((float) (intrinsicWidth * intrinsicHeight)));
                    if ((drawable instanceof BitmapDrawable) && min == 1.0f) {
                        parcelable2 = ((BitmapDrawable) drawable).getBitmap();
                    } else {
                        intrinsicWidth = (int) (((float) intrinsicWidth) * min);
                        intrinsicHeight = (int) (((float) intrinsicHeight) * min);
                        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Config.ARGB_8888);
                        Canvas canvas = new Canvas(createBitmap);
                        Rect bounds = drawable.getBounds();
                        int i = bounds.left;
                        int i2 = bounds.top;
                        int i3 = bounds.right;
                        int i4 = bounds.bottom;
                        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                        drawable.draw(canvas);
                        drawable.setBounds(i, i2, i3, i4);
                        parcelable2 = createBitmap;
                    }
                }
                if (parcelable2 != null) {
                    Parcelable bundle = new Bundle();
                    bundle.putParcelable("sharedElement:snapshot:bitmap", parcelable2);
                    bundle.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
                    if (imageView.getScaleType() == ScaleType.MATRIX) {
                        Matrix imageMatrix = imageView.getImageMatrix();
                        float[] fArr = new float[9];
                        imageMatrix.getValues(fArr);
                        bundle.putFloatArray("sharedElement:snapshot:imageMatrix", fArr);
                    }
                    return bundle;
                }
            }
        }
        int round = Math.round(rectF.width());
        int round2 = Math.round(rectF.height());
        if (round <= 0 || round2 <= 0) {
            parcelable = null;
        } else {
            float min2 = Math.min(1.0f, 1048576.0f / ((float) (round * round2)));
            round = (int) (((float) round) * min2);
            round2 = (int) (((float) round2) * min2);
            if (arVar.a == null) {
                arVar.a = new Matrix();
            }
            arVar.a.set(matrix);
            arVar.a.postTranslate(-rectF2.left, -rectF2.top);
            arVar.a.postScale(min2, min2);
            Bitmap createBitmap2 = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            canvas2.concat(arVar.a);
            view2.draw(canvas2);
            parcelable = createBitmap2;
        }
        return parcelable;
    }
}
