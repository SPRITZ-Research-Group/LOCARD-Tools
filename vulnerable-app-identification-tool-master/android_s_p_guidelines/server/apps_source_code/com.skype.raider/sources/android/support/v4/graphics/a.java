package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

public final class a {
    private static final ThreadLocal<double[]> a = new ThreadLocal();

    public static int a(@ColorInt int foreground, @ColorInt int background) {
        int bgAlpha = Color.alpha(background);
        int fgAlpha = Color.alpha(foreground);
        int a = 255 - (((255 - bgAlpha) * (255 - fgAlpha)) / 255);
        return Color.argb(a, a(Color.red(foreground), fgAlpha, Color.red(background), bgAlpha, a), a(Color.green(foreground), fgAlpha, Color.green(background), bgAlpha, a), a(Color.blue(foreground), fgAlpha, Color.blue(background), bgAlpha, a));
    }

    private static int a(int fgC, int fgA, int bgC, int bgA, int a) {
        if (a == 0) {
            return 0;
        }
        return (((fgC * 255) * fgA) + ((bgC * bgA) * (255 - fgA))) / (a * 255);
    }

    @ColorInt
    public static int b(@ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            return (16777215 & color) | (alpha << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }

    @ColorInt
    public static int a(@ColorInt int color1) {
        return Color.argb((int) ((((float) Color.alpha(color1)) * 0.9f) + (((float) Color.alpha(-16777216)) * 0.1f)), (int) ((((float) Color.red(color1)) * 0.9f) + (((float) Color.red(-16777216)) * 0.1f)), (int) ((((float) Color.green(color1)) * 0.9f) + (((float) Color.green(-16777216)) * 0.1f)), (int) ((((float) Color.blue(color1)) * 0.9f) + (((float) Color.blue(-16777216)) * 0.1f)));
    }
}
