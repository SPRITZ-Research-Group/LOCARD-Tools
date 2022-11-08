package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class n {
    private static Method a;
    private static boolean b;

    /* renamed from: androidx.transition.n$1 */
    final class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ImageView a;

        AnonymousClass1(ImageView imageView) {
            this.a = imageView;
        }

        public final void onAnimationEnd(Animator animator) {
            ScaleType scaleType = (ScaleType) this.a.getTag(t.save_scale_type);
            this.a.setScaleType(scaleType);
            this.a.setTag(t.save_scale_type, null);
            if (scaleType == ScaleType.MATRIX) {
                this.a.setImageMatrix((Matrix) this.a.getTag(t.save_image_matrix));
                this.a.setTag(t.save_image_matrix, null);
            }
            animator.removeListener(this);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(ImageView imageView, Matrix matrix) {
        if (VERSION.SDK_INT < 21) {
            imageView.setImageMatrix(matrix);
            return;
        }
        if (!b) {
            try {
                Method declaredMethod = ImageView.class.getDeclaredMethod("animateTransform", new Class[]{Matrix.class});
                a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ImageViewUtils", "Failed to retrieve animateTransform method", e);
            }
            b = true;
        }
        if (a != null) {
            try {
                a.invoke(imageView, new Object[]{matrix});
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }
}
