package com.facebook.drawee.c;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

public final class q {

    public interface b {
        public static final b a = j.j;
        public static final b b = i.j;
        public static final b c = g.j;
        public static final b d = h.j;
        public static final b e = c.j;
        public static final b f = e.j;
        public static final b g = d.j;
        public static final b h = k.j;
        public static final b i = f.j;

        Matrix a(Matrix matrix, Rect rect, int i, int i2, float f, float f2);
    }

    public static abstract class a implements b {
        public abstract void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4);

        public final Matrix a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY) {
            a(outTransform, parentRect, childWidth, childHeight, focusX, focusY, ((float) parentRect.width()) / ((float) childWidth), ((float) parentRect.height()) / ((float) childHeight));
            return outTransform;
        }
    }

    private static class c extends a {
        public static final b j = new c();

        private c() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            outTransform.setTranslate((float) ((int) ((((float) parentRect.left) + (((float) (parentRect.width() - childWidth)) * 0.5f)) + 0.5f)), (float) ((int) ((((float) parentRect.top) + (((float) (parentRect.height() - childHeight)) * 0.5f)) + 0.5f)));
        }

        public final String toString() {
            return "center";
        }
    }

    private static class d extends a {
        public static final b j = new d();

        private d() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale;
            float dx;
            float dy;
            if (scaleY > scaleX) {
                scale = scaleY;
                dx = ((float) parentRect.left) + ((((float) parentRect.width()) - (((float) childWidth) * scale)) * 0.5f);
                dy = (float) parentRect.top;
            } else {
                scale = scaleX;
                dx = (float) parentRect.left;
                dy = ((float) parentRect.top) + ((((float) parentRect.height()) - (((float) childHeight) * scale)) * 0.5f);
            }
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        }

        public final String toString() {
            return "center_crop";
        }
    }

    private static class e extends a {
        public static final b j = new e();

        private e() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(Math.min(scaleX, scaleY), 1.0f);
            float dx = ((float) parentRect.left) + ((((float) parentRect.width()) - (((float) childWidth) * scale)) * 0.5f);
            float dy = ((float) parentRect.top) + ((((float) parentRect.height()) - (((float) childHeight) * scale)) * 0.5f);
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        }

        public final String toString() {
            return "center_inside";
        }
    }

    private static class f extends a {
        public static final b j = new f();

        private f() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = (float) parentRect.left;
            float dy = ((float) parentRect.top) + (((float) parentRect.height()) - (((float) childHeight) * scale));
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        }

        public final String toString() {
            return "fit_bottom_start";
        }
    }

    private static class g extends a {
        public static final b j = new g();

        private g() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = ((float) parentRect.left) + ((((float) parentRect.width()) - (((float) childWidth) * scale)) * 0.5f);
            float dy = ((float) parentRect.top) + ((((float) parentRect.height()) - (((float) childHeight) * scale)) * 0.5f);
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        }

        public final String toString() {
            return "fit_center";
        }
    }

    private static class h extends a {
        public static final b j = new h();

        private h() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = ((float) parentRect.left) + (((float) parentRect.width()) - (((float) childWidth) * scale));
            float dy = ((float) parentRect.top) + (((float) parentRect.height()) - (((float) childHeight) * scale));
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        }

        public final String toString() {
            return "fit_end";
        }
    }

    private static class i extends a {
        public static final b j = new i();

        private i() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale = Math.min(scaleX, scaleY);
            float dx = (float) parentRect.left;
            float dy = (float) parentRect.top;
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (0.5f + dy)));
        }

        public final String toString() {
            return "fit_start";
        }
    }

    private static class j extends a {
        public static final b j = new j();

        private j() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float dx = (float) parentRect.left;
            float dy = (float) parentRect.top;
            outTransform.setScale(scaleX, scaleY);
            outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (0.5f + dy)));
        }

        public final String toString() {
            return "fit_xy";
        }
    }

    private static class k extends a {
        public static final b j = new k();

        private k() {
        }

        public final void a(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
            float scale;
            float dx;
            float dy;
            if (scaleY > scaleX) {
                scale = scaleY;
                dx = ((float) parentRect.left) + Math.max(Math.min((((float) parentRect.width()) * 0.5f) - ((((float) childWidth) * scale) * focusX), 0.0f), ((float) parentRect.width()) - (((float) childWidth) * scale));
                dy = (float) parentRect.top;
            } else {
                scale = scaleX;
                dx = (float) parentRect.left;
                dy = ((float) parentRect.top) + Math.max(Math.min((((float) parentRect.height()) * 0.5f) - ((((float) childHeight) * scale) * focusY), 0.0f), ((float) parentRect.height()) - (((float) childHeight) * scale));
            }
            outTransform.setScale(scale, scale);
            outTransform.postTranslate((float) ((int) (0.5f + dx)), (float) ((int) (0.5f + dy)));
        }

        public final String toString() {
            return "focus_crop";
        }
    }

    public interface l {
        Object a();
    }

    @Nullable
    public static p a(Drawable drawable) {
        while (drawable != null) {
            if (drawable instanceof p) {
                return (p) drawable;
            }
            if (drawable instanceof c) {
                drawable = ((c) drawable).a();
            } else {
                if (drawable instanceof a) {
                    a fadeDrawable = (a) drawable;
                    int numLayers = fadeDrawable.a();
                    for (int i = 0; i < numLayers; i++) {
                        Drawable result = a(fadeDrawable.a(i));
                        if (result != null) {
                            return result;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }
}
