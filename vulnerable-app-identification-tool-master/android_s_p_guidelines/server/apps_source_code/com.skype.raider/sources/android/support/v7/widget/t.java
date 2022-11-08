package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.graphics.drawable.b;
import java.lang.reflect.Field;

@RestrictTo({a.LIBRARY_GROUP})
public final class t {
    public static final Rect a = new Rect();
    private static Class<?> b;

    static {
        if (VERSION.SDK_INT >= 18) {
            try {
                b = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public static Rect a(Drawable drawable) {
        if (b != null) {
            try {
                drawable = android.support.v4.graphics.drawable.a.g(drawable);
                Object insets = drawable.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawable, new Object[0]);
                if (insets != null) {
                    Rect result = new Rect();
                    for (Field field : b.getFields()) {
                        String name = field.getName();
                        Object obj = -1;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    obj = 3;
                                    break;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    obj = null;
                                    break;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    obj = 2;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                result.left = field.getInt(insets);
                                break;
                            case 1:
                                result.top = field.getInt(insets);
                                break;
                            case 2:
                                result.right = field.getInt(insets);
                                break;
                            case 3:
                                result.bottom = field.getInt(insets);
                                break;
                            default:
                                break;
                        }
                    }
                    return result;
                }
            } catch (Exception e) {
            }
        }
        return a;
    }

    static void b(@NonNull Drawable drawable) {
        if (VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            int[] state = drawable.getState();
            if (state == null || state.length == 0) {
                drawable.setState(am.e);
            } else {
                drawable.setState(am.h);
            }
            drawable.setState(state);
        }
    }

    public static boolean c(@NonNull Drawable drawable) {
        while (true) {
            if (VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable)) {
                return false;
            }
            if (VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable)) {
                return false;
            }
            if (VERSION.SDK_INT >= 17 || !(drawable instanceof LayerDrawable)) {
                if (!(drawable instanceof DrawableContainer)) {
                    if (!(drawable instanceof b)) {
                        if (!(drawable instanceof android.support.v7.graphics.drawable.a)) {
                            if (!(drawable instanceof ScaleDrawable)) {
                                break;
                            }
                            drawable = ((ScaleDrawable) drawable).getDrawable();
                        } else {
                            drawable = ((android.support.v7.graphics.drawable.a) drawable).b();
                        }
                    } else {
                        drawable = ((b) drawable).a();
                    }
                } else {
                    ConstantState state = drawable.getConstantState();
                    if (state instanceof DrawableContainerState) {
                        for (Drawable c : ((DrawableContainerState) state).getChildren()) {
                            if (!c(c)) {
                                return false;
                            }
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static Mode a(int value, Mode defaultMode) {
        switch (value) {
            case 3:
                return Mode.SRC_OVER;
            case 5:
                return Mode.SRC_IN;
            case 9:
                return Mode.SRC_ATOP;
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                if (VERSION.SDK_INT >= 11) {
                    return Mode.valueOf("ADD");
                }
                return defaultMode;
            default:
                return defaultMode;
        }
    }
}
