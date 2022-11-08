package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.MenuItem;

public final class f {
    static final c a;

    interface c {
        void a(MenuItem menuItem, char c, int i);

        void a(MenuItem menuItem, ColorStateList colorStateList);

        void a(MenuItem menuItem, Mode mode);

        void a(MenuItem menuItem, CharSequence charSequence);

        void b(MenuItem menuItem, char c, int i);

        void b(MenuItem menuItem, CharSequence charSequence);
    }

    static class b implements c {
        b() {
        }

        public void a(MenuItem item, CharSequence contentDescription) {
        }

        public void b(MenuItem item, CharSequence tooltipText) {
        }

        public void a(MenuItem item, char alphaChar, int alphaModifiers) {
        }

        public void b(MenuItem item, char numericChar, int numericModifiers) {
        }

        public void a(MenuItem item, ColorStateList tint) {
        }

        public void a(MenuItem item, Mode tintMode) {
        }
    }

    @RequiresApi(26)
    static class a extends b {
        a() {
        }

        public final void a(MenuItem item, CharSequence contentDescription) {
            item.setContentDescription(contentDescription);
        }

        public final void b(MenuItem item, CharSequence tooltipText) {
            item.setTooltipText(tooltipText);
        }

        public final void a(MenuItem item, char alphaChar, int alphaModifiers) {
            item.setAlphabeticShortcut(alphaChar, alphaModifiers);
        }

        public final void b(MenuItem item, char numericChar, int numericModifiers) {
            item.setNumericShortcut(numericChar, numericModifiers);
        }

        public final void a(MenuItem item, ColorStateList tint) {
            item.setIconTintList(tint);
        }

        public final void a(MenuItem item, Mode tintMode) {
            item.setIconTintMode(tintMode);
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static MenuItem a(MenuItem item, b provider) {
        if (item instanceof android.support.v4.internal.view.b) {
            return ((android.support.v4.internal.view.b) item).a(provider);
        }
        return item;
    }

    public static void a(MenuItem item, CharSequence contentDescription) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).a(contentDescription);
        } else {
            a.a(item, contentDescription);
        }
    }

    public static void b(MenuItem item, CharSequence tooltipText) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).b(tooltipText);
        } else {
            a.b(item, tooltipText);
        }
    }

    public static void a(MenuItem item, char numericChar, int numericModifiers) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).setNumericShortcut(numericChar, numericModifiers);
        } else {
            a.b(item, numericChar, numericModifiers);
        }
    }

    public static void b(MenuItem item, char alphaChar, int alphaModifiers) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).setAlphabeticShortcut(alphaChar, alphaModifiers);
        } else {
            a.a(item, alphaChar, alphaModifiers);
        }
    }

    public static void a(MenuItem item, ColorStateList tint) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).setIconTintList(tint);
        } else {
            a.a(item, tint);
        }
    }

    public static void a(MenuItem item, Mode tintMode) {
        if (item instanceof android.support.v4.internal.view.b) {
            ((android.support.v4.internal.view.b) item).setIconTintMode(tintMode);
        } else {
            a.a(item, tintMode);
        }
    }
}
