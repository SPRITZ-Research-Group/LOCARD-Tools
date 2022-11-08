package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.graphics.drawable.a;
import defpackage.bu;
import defpackage.by;
import defpackage.ch;
import defpackage.ep;
import defpackage.m;
import defpackage.nz;
import defpackage.o;
import defpackage.q;
import defpackage.w;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class y {
    private static final Mode a = Mode.SRC_IN;
    private static y b;
    private static final ab c = new ab();
    private static final int[] d = new int[]{q.abc_textfield_search_default_mtrl_alpha, q.abc_textfield_default_mtrl_alpha, q.abc_ab_share_pack_mtrl_alpha};
    private static final int[] e = new int[]{q.abc_ic_commit_search_api_mtrl_alpha, q.abc_seekbar_tick_mark_material, q.abc_ic_menu_share_mtrl_alpha, q.abc_ic_menu_copy_mtrl_am_alpha, q.abc_ic_menu_cut_mtrl_alpha, q.abc_ic_menu_selectall_mtrl_alpha, q.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] f = new int[]{q.abc_textfield_activated_mtrl_alpha, q.abc_textfield_search_activated_mtrl_alpha, q.abc_cab_background_top_mtrl_alpha, q.abc_text_cursor_material, q.abc_text_select_handle_left_mtrl_dark, q.abc_text_select_handle_middle_mtrl_dark, q.abc_text_select_handle_right_mtrl_dark, q.abc_text_select_handle_left_mtrl_light, q.abc_text_select_handle_middle_mtrl_light, q.abc_text_select_handle_right_mtrl_light};
    private static final int[] g = new int[]{q.abc_popup_background_mtrl_mult, q.abc_cab_background_internal_bg, q.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] h = new int[]{q.abc_tab_indicator_material, q.abc_textfield_search_material};
    private static final int[] i = new int[]{q.abc_btn_check_material, q.abc_btn_radio_material};
    private WeakHashMap<Context, ch<ColorStateList>> j;
    private bu<String, ac> k;
    private ch<String> l;
    private final WeakHashMap<Context, by<WeakReference<ConstantState>>> m = new WeakHashMap(0);
    private TypedValue n;
    private boolean o;

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            if (b == null) {
                yVar = new y();
                b = yVar;
                if (VERSION.SDK_INT < 24) {
                    yVar.a("vector", new ad());
                    yVar.a("animated-vector", new aa());
                    yVar.a("animated-selector", new z());
                }
            }
            yVar = b;
        }
        return yVar;
    }

    public final synchronized Drawable a(Context context, int i) {
        return a(context, i, false);
    }

    public final synchronized void a(Context context) {
        by byVar = (by) this.m.get(context);
        if (byVar != null) {
            byVar.c();
        }
    }

    private static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable a(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList b = b(context, i);
        LayerDrawable layerDrawable;
        if (b != null) {
            if (ap.c(drawable)) {
                drawable = drawable.mutate();
            }
            drawable = a.e(drawable);
            a.a(drawable, b);
            Mode a = a(i);
            if (a == null) {
                return drawable;
            }
            a.a(drawable, a);
            return drawable;
        } else if (i == q.abc_seekbar_track_material) {
            layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), bq.a(context, m.colorControlNormal), a);
            a(layerDrawable.findDrawableByLayerId(16908303), bq.a(context, m.colorControlNormal), a);
            a(layerDrawable.findDrawableByLayerId(16908301), bq.a(context, m.colorControlActivated), a);
            return drawable;
        } else if (i == q.abc_ratingbar_material || i == q.abc_ratingbar_indicator_material || i == q.abc_ratingbar_small_material) {
            layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), bq.c(context, m.colorControlNormal), a);
            a(layerDrawable.findDrawableByLayerId(16908303), bq.a(context, m.colorControlActivated), a);
            a(layerDrawable.findDrawableByLayerId(16908301), bq.a(context, m.colorControlActivated), a);
            return drawable;
        } else if (a(context, i, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
    }

    private Drawable c(Context context, int i) {
        if (this.k == null || this.k.isEmpty()) {
            return null;
        }
        if (this.l != null) {
            String str = (String) this.l.a(i);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.k.get(str) == null)) {
                return null;
            }
        }
        this.l = new ch();
        if (this.n == null) {
            this.n = new TypedValue();
        }
        TypedValue typedValue = this.n;
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        long a = a(typedValue);
        Drawable a2 = a(context, a);
        if (a2 != null) {
            return a2;
        }
        if (typedValue.string != null && typedValue.string.toString().endsWith(".xml")) {
            try {
                int next;
                XmlPullParser xml = resources.getXml(i);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next == 2) {
                    String name = xml.getName();
                    this.l.c(i, name);
                    ac acVar = (ac) this.k.get(name);
                    if (acVar != null) {
                        a2 = acVar.a(context, xml, asAttributeSet, context.getTheme());
                    }
                    if (a2 != null) {
                        a2.setChangingConfigurations(typedValue.changingConfigurations);
                        a(context, a, a2);
                    }
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Throwable e) {
                Log.e("AppCompatDrawableManag", "Exception while inflating drawable", e);
            }
        }
        if (a2 == null) {
            this.l.c(i, "appcompat_skip_skip");
        }
        return a2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized Drawable a(Context context, long j) {
        by byVar = (by) this.m.get(context);
        if (byVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) byVar.a(j);
        if (weakReference != null) {
            ConstantState constantState = (ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            byVar.b(j);
        }
    }

    private synchronized boolean a(Context context, long j, Drawable drawable) {
        ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        by byVar = (by) this.m.get(context);
        if (byVar == null) {
            byVar = new by();
            this.m.put(context, byVar);
        }
        byVar.b(j, new WeakReference(constantState));
        return true;
    }

    final synchronized Drawable a(Context context, cb cbVar, int i) {
        Drawable c = c(context, i);
        if (c == null) {
            c = cbVar.a(i);
        }
        if (c == null) {
            return null;
        }
        return a(context, i, false, c);
    }

    static boolean a(Context context, int i, Drawable drawable) {
        int round;
        Object obj;
        Mode mode = a;
        int i2 = 16842801;
        if (a(d, i)) {
            i2 = m.colorControlNormal;
        } else if (a(f, i)) {
            i2 = m.colorControlActivated;
        } else if (a(g, i)) {
            mode = Mode.MULTIPLY;
        } else {
            if (i == q.abc_list_divider_mtrl_alpha) {
                i2 = 16842800;
                round = Math.round(40.8f);
                obj = 1;
            } else if (i != q.abc_dialog_material_background) {
                obj = null;
                round = -1;
                i2 = 0;
            }
            if (obj != null) {
                return false;
            }
            if (ap.c(drawable)) {
                drawable = drawable.mutate();
            }
            drawable.setColorFilter(a(bq.a(context, i2), mode));
            if (round != -1) {
                drawable.setAlpha(round);
            }
            return true;
        }
        obj = 1;
        round = -1;
        if (obj != null) {
            return false;
        }
        if (ap.c(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(a(bq.a(context, i2), mode));
        if (round != -1) {
            drawable.setAlpha(round);
        }
        return true;
    }

    private void a(String str, ac acVar) {
        if (this.k == null) {
            this.k = new bu();
        }
        this.k.put(str, acVar);
    }

    private static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private static Mode a(int i) {
        return i == q.abc_switch_thumb_material ? Mode.MULTIPLY : null;
    }

    private static ColorStateList d(Context context, int i) {
        r1 = new int[4][];
        r0 = new int[4];
        int a = bq.a(context, m.colorControlHighlight);
        int c = bq.c(context, m.colorButtonNormal);
        r1[0] = bq.a;
        r0[0] = c;
        r1[1] = bq.d;
        r0[1] = ep.a(a, i);
        r1[2] = bq.b;
        r0[2] = ep.a(a, i);
        r1[3] = bq.h;
        r0[3] = i;
        return new ColorStateList(r1, r0);
    }

    static void a(Drawable drawable, bs bsVar, int[] iArr) {
        if (!ap.c(drawable) || drawable.mutate() == drawable) {
            if (bsVar.d || bsVar.c) {
                ColorFilter colorFilter = null;
                ColorStateList colorStateList = bsVar.d ? bsVar.a : null;
                Mode mode = bsVar.c ? bsVar.b : a;
                if (!(colorStateList == null || mode == null)) {
                    colorFilter = a(colorStateList.getColorForState(iArr, 0), mode);
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

    public static synchronized PorterDuffColorFilter a(int i, Mode mode) {
        PorterDuffColorFilter a;
        synchronized (y.class) {
            a = c.a(i, mode);
            if (a == null) {
                a = new PorterDuffColorFilter(i, mode);
                c.a(i, mode, a);
            }
        }
        return a;
    }

    private static void a(Drawable drawable, int i, Mode mode) {
        if (ap.c(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = a;
        }
        drawable.setColorFilter(a(i, mode));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized Drawable a(Context context, int i, boolean z) {
        Drawable a;
        if (!this.o) {
            this.o = true;
            a = a(context, q.abc_vector_test);
            if (a != null) {
                Object obj = ((a instanceof nz) || "android.graphics.drawable.VectorDrawable".equals(a.getClass().getName())) ? 1 : null;
            }
            this.o = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
        a = c(context, i);
        if (a == null) {
            if (this.n == null) {
                this.n = new TypedValue();
            }
            TypedValue typedValue = this.n;
            context.getResources().getValue(i, typedValue, true);
            long a2 = a(typedValue);
            Drawable a3 = a(context, a2);
            if (a3 == null) {
                if (i == q.abc_cab_background_top_material) {
                    a3 = new LayerDrawable(new Drawable[]{a(context, q.abc_cab_background_internal_bg), a(context, q.abc_cab_background_top_mtrl_alpha)});
                }
                if (a3 != null) {
                    a3.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, a2, a3);
                }
            }
            a = a3;
        }
        if (a == null) {
            a = androidx.core.content.a.a(context, i);
        }
        if (a != null) {
            a = a(context, i, z, a);
        }
        if (a != null) {
            ap.b(a);
        }
        return a;
    }

    final synchronized ColorStateList b(Context context, int i) {
        ColorStateList colorStateList;
        ch chVar;
        colorStateList = null;
        if (this.j != null) {
            chVar = (ch) this.j.get(context);
            if (chVar != null) {
                colorStateList = (ColorStateList) chVar.a(i);
            }
        }
        if (colorStateList == null) {
            ColorStateList a;
            if (i == q.abc_edit_text_material) {
                a = w.a(context, o.abc_tint_edittext);
            } else if (i == q.abc_switch_track_mtrl_alpha) {
                a = w.a(context, o.abc_tint_switch_track);
            } else {
                if (i == q.abc_switch_thumb_material) {
                    int[][] iArr = new int[3][];
                    int[] iArr2 = new int[3];
                    ColorStateList b = bq.b(context, m.colorSwitchThumbNormal);
                    if (b == null || !b.isStateful()) {
                        iArr[0] = bq.a;
                        iArr2[0] = bq.c(context, m.colorSwitchThumbNormal);
                        iArr[1] = bq.e;
                        iArr2[1] = bq.a(context, m.colorControlActivated);
                        iArr[2] = bq.h;
                        iArr2[2] = bq.a(context, m.colorSwitchThumbNormal);
                    } else {
                        iArr[0] = bq.a;
                        iArr2[0] = b.getColorForState(iArr[0], 0);
                        iArr[1] = bq.e;
                        iArr2[1] = bq.a(context, m.colorControlActivated);
                        iArr[2] = bq.h;
                        iArr2[2] = b.getDefaultColor();
                    }
                    colorStateList = new ColorStateList(iArr, iArr2);
                } else if (i == q.abc_btn_default_mtrl_shape) {
                    a = d(context, bq.a(context, m.colorButtonNormal));
                } else if (i == q.abc_btn_borderless_material) {
                    a = d(context, 0);
                } else if (i == q.abc_btn_colored_material) {
                    a = d(context, bq.a(context, m.colorAccent));
                } else if (i == q.abc_spinner_mtrl_am_alpha || i == q.abc_spinner_textfield_background_material) {
                    a = w.a(context, o.abc_tint_spinner);
                } else if (a(e, i)) {
                    a = bq.b(context, m.colorControlNormal);
                } else if (a(h, i)) {
                    a = w.a(context, o.abc_tint_default);
                } else if (a(i, i)) {
                    a = w.a(context, o.abc_tint_btn_checkable);
                } else if (i == q.abc_seekbar_thumb_material) {
                    a = w.a(context, o.abc_tint_seek_thumb);
                }
                if (colorStateList != null) {
                    if (this.j == null) {
                        this.j = new WeakHashMap();
                    }
                    chVar = (ch) this.j.get(context);
                    if (chVar == null) {
                        chVar = new ch();
                        this.j.put(context, chVar);
                    }
                    chVar.c(i, colorStateList);
                }
            }
            colorStateList = a;
            if (colorStateList != null) {
                if (this.j == null) {
                    this.j = new WeakHashMap();
                }
                chVar = (ch) this.j.get(context);
                if (chVar == null) {
                    chVar = new ch();
                    this.j.put(context, chVar);
                }
                chVar.c(i, colorStateList);
            }
        }
        return colorStateList;
    }
}
