package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.b.a.i;
import android.support.v4.util.f;
import android.support.v4.util.g;
import android.support.v4.util.m;
import android.support.v7.appcompat.a.e;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class h {
    private static final Mode a = Mode.SRC_IN;
    private static h b;
    private static final b c = new b();
    private static final int[] d = new int[]{e.abc_textfield_search_default_mtrl_alpha, e.abc_textfield_default_mtrl_alpha, e.abc_ab_share_pack_mtrl_alpha};
    private static final int[] e = new int[]{e.abc_ic_commit_search_api_mtrl_alpha, e.abc_seekbar_tick_mark_material, e.abc_ic_menu_share_mtrl_alpha, e.abc_ic_menu_copy_mtrl_am_alpha, e.abc_ic_menu_cut_mtrl_alpha, e.abc_ic_menu_selectall_mtrl_alpha, e.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] f = new int[]{e.abc_textfield_activated_mtrl_alpha, e.abc_textfield_search_activated_mtrl_alpha, e.abc_cab_background_top_mtrl_alpha, e.abc_text_cursor_material, e.abc_text_select_handle_left_mtrl_dark, e.abc_text_select_handle_middle_mtrl_dark, e.abc_text_select_handle_right_mtrl_dark, e.abc_text_select_handle_left_mtrl_light, e.abc_text_select_handle_middle_mtrl_light, e.abc_text_select_handle_right_mtrl_light};
    private static final int[] g = new int[]{e.abc_popup_background_mtrl_mult, e.abc_cab_background_internal_bg, e.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] h = new int[]{e.abc_tab_indicator_material, e.abc_textfield_search_material};
    private static final int[] i = new int[]{e.abc_btn_check_material, e.abc_btn_radio_material};
    private WeakHashMap<Context, m<ColorStateList>> j;
    private android.support.v4.util.a<String, c> k;
    private m<String> l;
    private final Object m = new Object();
    private final WeakHashMap<Context, f<WeakReference<ConstantState>>> n = new WeakHashMap(0);
    private TypedValue o;
    private boolean p;

    private interface c {
        Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Theme theme);
    }

    @RequiresApi(11)
    private static class a implements c {
        a() {
        }

        public final Drawable a(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) {
            try {
                return android.support.b.a.c.a(context, context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                return null;
            }
        }
    }

    private static class b extends g<Integer, PorterDuffColorFilter> {
        public b() {
            super(6);
        }

        final PorterDuffColorFilter a(int color, Mode mode) {
            return (PorterDuffColorFilter) a((Object) Integer.valueOf(b(color, mode)));
        }

        final PorterDuffColorFilter a(int color, Mode mode, PorterDuffColorFilter filter) {
            return (PorterDuffColorFilter) a(Integer.valueOf(b(color, mode)), filter);
        }

        private static int b(int color, Mode mode) {
            return ((color + 31) * 31) + mode.hashCode();
        }
    }

    private static class d implements c {
        d() {
        }

        public final Drawable a(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) {
            try {
                return i.a(context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static h a() {
        if (b == null) {
            h hVar = new h();
            b = hVar;
            if (VERSION.SDK_INT < 24) {
                hVar.a("vector", new d());
                if (VERSION.SDK_INT >= 11) {
                    hVar.a("animated-vector", new a());
                }
            }
        }
        return b;
    }

    public final Drawable a(@NonNull Context context, @DrawableRes int resId) {
        return a(context, resId, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final Drawable a(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown) {
        if (!this.p) {
            this.p = true;
            Drawable a = a(context, e.abc_vector_test, false);
            if (a != null) {
                boolean z;
                if ((a instanceof i) || "android.graphics.drawable.VectorDrawable".equals(a.getClass().getName())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            this.p = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
        Drawable drawable = c(context, resId);
        if (drawable == null) {
            if (this.o == null) {
                this.o = new TypedValue();
            }
            TypedValue typedValue = this.o;
            context.getResources().getValue(resId, typedValue, true);
            long a2 = a(typedValue);
            drawable = a(context, a2);
            if (drawable == null) {
                if (resId == e.abc_cab_background_top_material) {
                    drawable = new LayerDrawable(new Drawable[]{a(context, e.abc_cab_background_internal_bg, false), a(context, e.abc_cab_background_top_mtrl_alpha, false)});
                }
                if (drawable != null) {
                    drawable.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, a2, drawable);
                }
            }
        }
        if (drawable == null) {
            drawable = android.support.v4.content.a.a(context, resId);
        }
        if (drawable != null) {
            drawable = a(context, resId, failIfNotKnown, drawable);
        }
        if (drawable != null) {
            t.b(drawable);
        }
        return drawable;
    }

    public final void a(@NonNull Context context) {
        synchronized (this.m) {
            f<WeakReference<ConstantState>> cache = (f) this.n.get(context);
            if (cache != null) {
                cache.b();
            }
        }
    }

    private static long a(TypedValue tv) {
        return (((long) tv.assetCookie) << 32) | ((long) tv.data);
    }

    private Drawable a(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown, @NonNull Drawable drawable) {
        ColorStateList tintList = b(context, resId);
        LayerDrawable ld;
        if (tintList != null) {
            if (t.c(drawable)) {
                drawable = drawable.mutate();
            }
            drawable = android.support.v4.graphics.drawable.a.f(drawable);
            android.support.v4.graphics.drawable.a.a(drawable, tintList);
            Mode tintMode = null;
            if (resId == e.abc_switch_thumb_material) {
                tintMode = Mode.MULTIPLY;
            }
            if (tintMode == null) {
                return drawable;
            }
            android.support.v4.graphics.drawable.a.a(drawable, tintMode);
            return drawable;
        } else if (resId == e.abc_seekbar_track_material) {
            ld = (LayerDrawable) drawable;
            a(ld.findDrawableByLayerId(16908288), am.a(context, android.support.v7.appcompat.a.a.colorControlNormal), a);
            a(ld.findDrawableByLayerId(16908303), am.a(context, android.support.v7.appcompat.a.a.colorControlNormal), a);
            a(ld.findDrawableByLayerId(16908301), am.a(context, android.support.v7.appcompat.a.a.colorControlActivated), a);
            return drawable;
        } else if (resId == e.abc_ratingbar_material || resId == e.abc_ratingbar_indicator_material || resId == e.abc_ratingbar_small_material) {
            ld = (LayerDrawable) drawable;
            a(ld.findDrawableByLayerId(16908288), am.c(context, android.support.v7.appcompat.a.a.colorControlNormal), a);
            a(ld.findDrawableByLayerId(16908303), am.a(context, android.support.v7.appcompat.a.a.colorControlActivated), a);
            a(ld.findDrawableByLayerId(16908301), am.a(context, android.support.v7.appcompat.a.a.colorControlActivated), a);
            return drawable;
        } else if (a(context, resId, drawable) || !failIfNotKnown) {
            return drawable;
        } else {
            return null;
        }
    }

    private Drawable c(@NonNull Context context, @DrawableRes int resId) {
        if (this.k == null || this.k.isEmpty()) {
            return null;
        }
        if (this.l != null) {
            String cachedTagName = (String) this.l.a(resId);
            if ("appcompat_skip_skip".equals(cachedTagName) || (cachedTagName != null && this.k.get(cachedTagName) == null)) {
                return null;
            }
        }
        this.l = new m();
        if (this.o == null) {
            this.o = new TypedValue();
        }
        TypedValue tv = this.o;
        Resources res = context.getResources();
        res.getValue(resId, tv, true);
        long key = a(tv);
        Drawable dr = a(context, key);
        if (dr != null) {
            return dr;
        }
        if (tv.string != null && tv.string.toString().endsWith(".xml")) {
            try {
                int type;
                XmlPullParser parser = res.getXml(resId);
                AttributeSet attrs = Xml.asAttributeSet(parser);
                do {
                    type = parser.next();
                    if (type == 2) {
                        break;
                    }
                } while (type != 1);
                if (type != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String tagName = parser.getName();
                this.l.b(resId, tagName);
                c delegate = (c) this.k.get(tagName);
                if (delegate != null) {
                    dr = delegate.a(context, parser, attrs, context.getTheme());
                }
                if (dr != null) {
                    dr.setChangingConfigurations(tv.changingConfigurations);
                    a(context, key, dr);
                }
            } catch (Exception e) {
            }
        }
        if (dr != null) {
            return dr;
        }
        this.l.b(resId, "appcompat_skip_skip");
        return dr;
    }

    private Drawable a(@NonNull Context context, long key) {
        Drawable drawable = null;
        synchronized (this.m) {
            f<WeakReference<ConstantState>> cache = (f) this.n.get(context);
            if (cache == null) {
            } else {
                WeakReference<ConstantState> wr = (WeakReference) cache.a(key);
                if (wr != null) {
                    ConstantState entry = (ConstantState) wr.get();
                    if (entry != null) {
                        drawable = entry.newDrawable(context.getResources());
                    } else {
                        cache.b(key);
                    }
                }
            }
        }
        return drawable;
    }

    private boolean a(@NonNull Context context, long key, @NonNull Drawable drawable) {
        ConstantState cs = drawable.getConstantState();
        if (cs == null) {
            return false;
        }
        synchronized (this.m) {
            f<WeakReference<ConstantState>> cache = (f) this.n.get(context);
            if (cache == null) {
                cache = new f();
                this.n.put(context, cache);
            }
            cache.a(key, new WeakReference(cs));
        }
        return true;
    }

    final Drawable a(@NonNull Context context, @NonNull av resources, @DrawableRes int resId) {
        Drawable drawable = c(context, resId);
        if (drawable == null) {
            drawable = resources.a(resId);
        }
        if (drawable != null) {
            return a(context, resId, false, drawable);
        }
        return null;
    }

    static boolean a(@NonNull Context context, @DrawableRes int resId, @NonNull Drawable drawable) {
        Mode tintMode = a;
        boolean colorAttrSet = false;
        int colorAttr = 0;
        int alpha = -1;
        if (a(d, resId)) {
            colorAttr = android.support.v7.appcompat.a.a.colorControlNormal;
            colorAttrSet = true;
        } else if (a(f, resId)) {
            colorAttr = android.support.v7.appcompat.a.a.colorControlActivated;
            colorAttrSet = true;
        } else if (a(g, resId)) {
            colorAttr = 16842801;
            colorAttrSet = true;
            tintMode = Mode.MULTIPLY;
        } else if (resId == e.abc_list_divider_mtrl_alpha) {
            colorAttr = 16842800;
            colorAttrSet = true;
            alpha = Math.round(40.8f);
        } else if (resId == e.abc_dialog_material_background) {
            colorAttr = 16842801;
            colorAttrSet = true;
        }
        if (!colorAttrSet) {
            return false;
        }
        if (t.c(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(a(am.a(context, colorAttr), tintMode));
        if (alpha != -1) {
            drawable.setAlpha(alpha);
        }
        return true;
    }

    private void a(@NonNull String tagName, @NonNull c delegate) {
        if (this.k == null) {
            this.k = new android.support.v4.util.a();
        }
        this.k.put(tagName, delegate);
    }

    private static boolean a(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    final ColorStateList b(@NonNull Context context, @DrawableRes int resId) {
        m mVar;
        ColorStateList tint = null;
        if (this.j != null) {
            mVar = (m) this.j.get(context);
            if (mVar != null) {
                tint = (ColorStateList) mVar.a(resId);
            }
        }
        if (tint == null) {
            if (resId == e.abc_edit_text_material) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_edittext);
            } else if (resId == e.abc_switch_track_mtrl_alpha) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_switch_track);
            } else if (resId == e.abc_switch_thumb_material) {
                int[][] iArr = new int[3][];
                int[] iArr2 = new int[3];
                ColorStateList b = am.b(context, android.support.v7.appcompat.a.a.colorSwitchThumbNormal);
                if (b == null || !b.isStateful()) {
                    iArr[0] = am.a;
                    iArr2[0] = am.c(context, android.support.v7.appcompat.a.a.colorSwitchThumbNormal);
                    iArr[1] = am.e;
                    iArr2[1] = am.a(context, android.support.v7.appcompat.a.a.colorControlActivated);
                    iArr[2] = am.h;
                    iArr2[2] = am.a(context, android.support.v7.appcompat.a.a.colorSwitchThumbNormal);
                } else {
                    iArr[0] = am.a;
                    iArr2[0] = b.getColorForState(iArr[0], 0);
                    iArr[1] = am.e;
                    iArr2[1] = am.a(context, android.support.v7.appcompat.a.a.colorControlActivated);
                    iArr[2] = am.h;
                    iArr2[2] = b.getDefaultColor();
                }
                tint = new ColorStateList(iArr, iArr2);
            } else if (resId == e.abc_btn_default_mtrl_shape) {
                tint = d(context, am.a(context, android.support.v7.appcompat.a.a.colorButtonNormal));
            } else if (resId == e.abc_btn_borderless_material) {
                tint = d(context, 0);
            } else if (resId == e.abc_btn_colored_material) {
                tint = d(context, am.a(context, android.support.v7.appcompat.a.a.colorAccent));
            } else if (resId == e.abc_spinner_mtrl_am_alpha || resId == e.abc_spinner_textfield_background_material) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_spinner);
            } else if (a(e, resId)) {
                tint = am.b(context, android.support.v7.appcompat.a.a.colorControlNormal);
            } else if (a(h, resId)) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_default);
            } else if (a(i, resId)) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_btn_checkable);
            } else if (resId == e.abc_seekbar_thumb_material) {
                tint = android.support.v7.content.res.b.a(context, android.support.v7.appcompat.a.c.abc_tint_seek_thumb);
            }
            if (tint != null) {
                if (this.j == null) {
                    this.j = new WeakHashMap();
                }
                mVar = (m) this.j.get(context);
                if (mVar == null) {
                    mVar = new m();
                    this.j.put(context, mVar);
                }
                mVar.b(resId, tint);
            }
        }
        return tint;
    }

    private static ColorStateList d(@NonNull Context context, @ColorInt int baseColor) {
        states = new int[4][];
        colors = new int[4];
        int colorControlHighlight = am.a(context, android.support.v7.appcompat.a.a.colorControlHighlight);
        int disabledColor = am.c(context, android.support.v7.appcompat.a.a.colorButtonNormal);
        states[0] = am.a;
        colors[0] = disabledColor;
        states[1] = am.d;
        colors[1] = android.support.v4.graphics.a.a(colorControlHighlight, baseColor);
        states[2] = am.b;
        colors[2] = android.support.v4.graphics.a.a(colorControlHighlight, baseColor);
        states[3] = am.h;
        colors[3] = baseColor;
        return new ColorStateList(states, colors);
    }

    static void a(Drawable drawable, ao tint, int[] state) {
        ColorFilter colorFilter = null;
        if (!t.c(drawable) || drawable.mutate() == drawable) {
            if (tint.d || tint.c) {
                ColorStateList colorStateList;
                Mode mode;
                if (tint.d) {
                    colorStateList = tint.a;
                } else {
                    colorStateList = null;
                }
                if (tint.c) {
                    mode = tint.b;
                } else {
                    mode = a;
                }
                if (!(colorStateList == null || mode == null)) {
                    colorFilter = a(colorStateList.getColorForState(state, 0), mode);
                }
                drawable.setColorFilter(colorFilter);
            } else {
                drawable.clearColorFilter();
            }
            if (VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }

    private static PorterDuffColorFilter a(int color, Mode mode) {
        PorterDuffColorFilter filter = c.a(color, mode);
        if (filter != null) {
            return filter;
        }
        filter = new PorterDuffColorFilter(color, mode);
        c.a(color, mode, filter);
        return filter;
    }

    private static void a(Drawable d, int color, Mode mode) {
        if (t.c(d)) {
            d = d.mutate();
        }
        if (mode == null) {
            mode = a;
        }
        d.setColorFilter(a(color, mode));
    }
}
