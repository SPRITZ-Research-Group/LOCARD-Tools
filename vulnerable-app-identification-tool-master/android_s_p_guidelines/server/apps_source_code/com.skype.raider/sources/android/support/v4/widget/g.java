package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

public final class g {
    static final b a;

    interface b {
        ColorStateList a(ImageView imageView);

        void a(ImageView imageView, ColorStateList colorStateList);

        void a(ImageView imageView, Mode mode);

        Mode b(ImageView imageView);
    }

    static class a implements b {
        a() {
        }

        public ColorStateList a(ImageView view) {
            return view instanceof m ? ((m) view).b() : null;
        }

        public void a(ImageView view, ColorStateList tintList) {
            if (view instanceof m) {
                ((m) view).setSupportImageTintList(tintList);
            }
        }

        public void a(ImageView view, Mode mode) {
            if (view instanceof m) {
                ((m) view).setSupportImageTintMode(mode);
            }
        }

        public Mode b(ImageView view) {
            return view instanceof m ? ((m) view).c() : null;
        }
    }

    @RequiresApi(21)
    static class c extends a {
        c() {
        }

        public final ColorStateList a(ImageView view) {
            return view.getImageTintList();
        }

        public final void a(ImageView view, ColorStateList tintList) {
            view.setImageTintList(tintList);
            if (VERSION.SDK_INT == 21) {
                Drawable imageViewDrawable = view.getDrawable();
                boolean hasTint = (view.getImageTintList() == null || view.getImageTintMode() == null) ? false : true;
                if (imageViewDrawable != null && hasTint) {
                    if (imageViewDrawable.isStateful()) {
                        imageViewDrawable.setState(view.getDrawableState());
                    }
                    view.setImageDrawable(imageViewDrawable);
                }
            }
        }

        public final void a(ImageView view, Mode mode) {
            view.setImageTintMode(mode);
            if (VERSION.SDK_INT == 21) {
                Drawable imageViewDrawable = view.getDrawable();
                boolean hasTint = (view.getImageTintList() == null || view.getImageTintMode() == null) ? false : true;
                if (imageViewDrawable != null && hasTint) {
                    if (imageViewDrawable.isStateful()) {
                        imageViewDrawable.setState(view.getDrawableState());
                    }
                    view.setImageDrawable(imageViewDrawable);
                }
            }
        }

        public final Mode b(ImageView view) {
            return view.getImageTintMode();
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static ColorStateList a(ImageView view) {
        return a.a(view);
    }

    public static void a(ImageView view, ColorStateList tintList) {
        a.a(view, tintList);
    }

    public static Mode b(ImageView view) {
        return a.b(view);
    }

    public static void a(ImageView view, Mode mode) {
        a.a(view, mode);
    }
}
