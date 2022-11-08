package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class ak {
    private static final boolean a = (VERSION.SDK_INT >= 19);
    private static final boolean b = (VERSION.SDK_INT >= 18);
    private static final boolean c;

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 28) {
            z = true;
        }
        c = z;
    }

    static View a(ViewGroup viewGroup, View view, View view2) {
        boolean isAttachedToWindow;
        Bitmap bitmap;
        ViewGroup viewGroup2;
        int i;
        int round;
        int round2;
        float min;
        Canvas canvas;
        Picture picture;
        Canvas beginRecording;
        View view3 = viewGroup;
        View view4 = view;
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
        ba.a(view4, matrix);
        ba.b(view3, matrix);
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) view.getWidth(), (float) view.getHeight());
        matrix.mapRect(rectF);
        int round3 = Math.round(rectF.left);
        int round4 = Math.round(rectF.top);
        int round5 = Math.round(rectF.right);
        int round6 = Math.round(rectF.bottom);
        View imageView = new ImageView(view.getContext());
        imageView.setScaleType(ScaleType.CENTER_CROP);
        int isAttachedToWindow2;
        if (a) {
            isAttachedToWindow2 = view.isAttachedToWindow() ^ 1;
            if (view3 != null) {
                isAttachedToWindow = viewGroup.isAttachedToWindow();
                bitmap = null;
                if (b || isAttachedToWindow2 == 0) {
                    viewGroup2 = null;
                    i = 0;
                } else {
                    if (isAttachedToWindow) {
                        viewGroup2 = (ViewGroup) view.getParent();
                        i = viewGroup2.indexOfChild(view4);
                        viewGroup.getOverlay().add(view4);
                    }
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                    imageView.measure(MeasureSpec.makeMeasureSpec(round5 - round3, 1073741824), MeasureSpec.makeMeasureSpec(round6 - round4, 1073741824));
                    imageView.layout(round3, round4, round5, round6);
                    return imageView;
                }
                round = Math.round(rectF.width());
                round2 = Math.round(rectF.height());
                if (round > 0 && round2 > 0) {
                    min = Math.min(1.0f, 1048576.0f / ((float) (round * round2)));
                    round = Math.round(((float) round) * min);
                    round2 = Math.round(((float) round2) * min);
                    matrix.postTranslate(-rectF.left, -rectF.top);
                    matrix.postScale(min, min);
                    if (c) {
                        bitmap = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                        canvas = new Canvas(bitmap);
                        canvas.concat(matrix);
                        view4.draw(canvas);
                    } else {
                        picture = new Picture();
                        beginRecording = picture.beginRecording(round, round2);
                        beginRecording.concat(matrix);
                        view4.draw(beginRecording);
                        picture.endRecording();
                        bitmap = Bitmap.createBitmap(picture);
                    }
                }
                if (b && isAttachedToWindow2 != 0) {
                    viewGroup.getOverlay().remove(view4);
                    viewGroup2.addView(view4, i);
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
                imageView.measure(MeasureSpec.makeMeasureSpec(round5 - round3, 1073741824), MeasureSpec.makeMeasureSpec(round6 - round4, 1073741824));
                imageView.layout(round3, round4, round5, round6);
                return imageView;
            }
        }
        isAttachedToWindow2 = 0;
        isAttachedToWindow = false;
        bitmap = null;
        if (b) {
        }
        viewGroup2 = null;
        i = 0;
        round = Math.round(rectF.width());
        round2 = Math.round(rectF.height());
        min = Math.min(1.0f, 1048576.0f / ((float) (round * round2)));
        round = Math.round(((float) round) * min);
        round2 = Math.round(((float) round2) * min);
        matrix.postTranslate(-rectF.left, -rectF.top);
        matrix.postScale(min, min);
        if (c) {
            bitmap = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            canvas.concat(matrix);
            view4.draw(canvas);
        } else {
            picture = new Picture();
            beginRecording = picture.beginRecording(round, round2);
            beginRecording.concat(matrix);
            view4.draw(beginRecording);
            picture.endRecording();
            bitmap = Bitmap.createBitmap(picture);
        }
        viewGroup.getOverlay().remove(view4);
        viewGroup2.addView(view4, i);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        imageView.measure(MeasureSpec.makeMeasureSpec(round5 - round3, 1073741824), MeasureSpec.makeMeasureSpec(round6 - round4, 1073741824));
        imageView.layout(round3, round4, round5, round6);
        return imageView;
    }

    static Animator a(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{animator, animator2});
        return animatorSet;
    }
}
