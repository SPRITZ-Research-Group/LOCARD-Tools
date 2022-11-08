package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class c {
    private static final c a;

    static class c {
        private static Field a;
        private static boolean b;

        c() {
        }

        public void a(CompoundButton button, ColorStateList tint) {
            if (button instanceof l) {
                ((l) button).setSupportButtonTintList(tint);
            }
        }

        public void a(CompoundButton button, Mode tintMode) {
            if (button instanceof l) {
                ((l) button).setSupportButtonTintMode(tintMode);
            }
        }

        public Drawable a(CompoundButton button) {
            if (!b) {
                try {
                    Field declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    a = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                b = true;
            }
            if (a != null) {
                try {
                    return (Drawable) a.get(button);
                } catch (IllegalAccessException e2) {
                    a = null;
                }
            }
            return null;
        }
    }

    @RequiresApi(21)
    static class a extends c {
        a() {
        }

        public final void a(CompoundButton button, ColorStateList tint) {
            button.setButtonTintList(tint);
        }

        public final void a(CompoundButton button, Mode tintMode) {
            button.setButtonTintMode(tintMode);
        }
    }

    @RequiresApi(23)
    static class b extends a {
        b() {
        }

        public final Drawable a(CompoundButton button) {
            return button.getButtonDrawable();
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            a = new b();
        } else if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new c();
        }
    }

    public static void a(@NonNull CompoundButton button, @Nullable ColorStateList tint) {
        a.a(button, tint);
    }

    public static void a(@NonNull CompoundButton button, @Nullable Mode tintMode) {
        a.a(button, tintMode);
    }

    @Nullable
    public static Drawable a(@NonNull CompoundButton button) {
        return a.a(button);
    }
}
