package com.facebook.ads.internal.s;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.q.a.r;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.v;
import com.facebook.ads.internal.q.a.w;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

public class a {
    private static final String a = a.class.getSimpleName();
    private final View b;
    private final int c;
    private final int d;
    private final WeakReference<a> e;
    private final Handler f;
    private final boolean g;
    private Runnable h;
    private int i;
    private int j;
    private boolean k;
    private b l;
    private Map<String, Integer> m;
    private long n;
    private int o;

    public static abstract class a {
        public abstract void a();

        public void b() {
        }
    }

    private static final class b extends v<a> {
        b(a aVar) {
            super(aVar);
        }

        public final void run() {
            int i = 0;
            a aVar = (a) a();
            if (aVar != null) {
                View a = aVar.b;
                a aVar2 = (a) aVar.e.get();
                if (a != null && aVar2 != null) {
                    b a2 = a.a(a, aVar.c);
                    if (a2.a()) {
                        aVar.o = aVar.o + 1;
                    } else {
                        aVar.o = 0;
                    }
                    int i2 = aVar.o > aVar.d ? 1 : 0;
                    int i3 = (aVar.l == null || !aVar.l.a()) ? 0 : 1;
                    if (!(i2 == 0 && a2.a())) {
                        aVar.l = a2;
                    }
                    String valueOf = String.valueOf(a2.b());
                    synchronized (aVar) {
                        if (aVar.m.containsKey(valueOf)) {
                            i = ((Integer) aVar.m.get(valueOf)).intValue();
                        }
                        aVar.m.put(valueOf, Integer.valueOf(i + 1));
                    }
                    if (i2 != 0 && i3 == 0) {
                        aVar.n = System.currentTimeMillis();
                        aVar2.a();
                        if (!aVar.g) {
                            return;
                        }
                    } else if (i2 == 0 && i3 != 0) {
                        aVar2.b();
                    }
                    if (!aVar.k && aVar.h != null) {
                        aVar.f.postDelayed(aVar.h, (long) aVar.j);
                    }
                }
            }
        }
    }

    public a(View view, int i, int i2, boolean z, a aVar) {
        this.f = new Handler();
        this.i = 0;
        this.j = Constants.ONE_SECOND;
        this.k = true;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
        this.n = 0;
        this.o = 0;
        this.b = view;
        if (this.b.getId() == -1) {
            u.a(this.b);
        }
        this.c = i;
        this.e = new WeakReference(aVar);
        this.g = z;
        if (i2 < 0) {
            i2 = 0;
        }
        this.d = i2;
    }

    public a(View view, int i, a aVar) {
        this(view, i, 0, false, aVar);
    }

    public a(View view, a aVar) {
        this(view, 50, 0, true, aVar);
    }

    private static int a(Vector<Rect> vector) {
        int i;
        Rect rect;
        int i2;
        int i3;
        int i4;
        int size = vector.size();
        int[] iArr = new int[(size * 2)];
        int[] iArr2 = new int[(size * 2)];
        boolean[][] zArr = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{size * 2, size * 2});
        int i5 = 0;
        int i6 = 0;
        for (i = 0; i < size; i++) {
            rect = (Rect) vector.elementAt(i);
            i2 = i6 + 1;
            iArr[i6] = rect.left;
            i3 = i5 + 1;
            iArr2[i5] = rect.bottom;
            i6 = i2 + 1;
            iArr[i2] = rect.right;
            i5 = i3 + 1;
            iArr2[i3] = rect.top;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (i5 = 0; i5 < size; i5++) {
            rect = (Rect) vector.elementAt(i5);
            i = a(iArr, rect.left);
            i6 = a(iArr, rect.right);
            i2 = a(iArr2, rect.top);
            i3 = a(iArr2, rect.bottom);
            for (i++; i <= i6; i++) {
                for (i4 = i2 + 1; i4 <= i3; i4++) {
                    zArr[i][i4] = true;
                }
            }
        }
        i6 = 0;
        i4 = 0;
        while (i6 < size * 2) {
            i = 0;
            while (true) {
                i5 = i4;
                if (i >= size * 2) {
                    break;
                }
                i5 += zArr[i6][i] ? (iArr[i6] - iArr[i6 - 1]) * (iArr2[i] - iArr2[i - 1]) : 0;
                i4 = i + 1;
            }
            i6++;
            i4 = i5;
        }
        return i4;
    }

    private static int a(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        while (i2 < length) {
            int i3 = ((length - i2) / 2) + i2;
            if (iArr[i3] == i) {
                return i3;
            }
            if (iArr[i3] > i) {
                length = i3;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public static b a(View view, int i) {
        if (view == null) {
            return new b(c.AD_IS_NULL);
        }
        if (view.getParent() == null) {
            return new b(c.INVALID_PARENT);
        }
        if (!view.isShown()) {
            return new b(c.INVALID_PARENT);
        }
        if (view.getWindowVisibility() != 0) {
            return new b(c.INVALID_WINDOW);
        }
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            new StringBuilder("mAdView has invisible dimensions (w=").append(view.getMeasuredWidth()).append(", h=").append(view.getMeasuredHeight());
            return new b(c.INVALID_DIMENSIONS);
        } else if (c(view) < 0.9f) {
            return new b(c.AD_IS_TRANSPARENT);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                if (!view.getGlobalVisibleRect(rect)) {
                    return new b(c.AD_IS_NOT_VISIBLE);
                }
                Context context = view.getContext();
                DisplayMetrics displayMetrics;
                if (VERSION.SDK_INT >= 17) {
                    Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    displayMetrics = new DisplayMetrics();
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    displayMetrics = context.getResources().getDisplayMetrics();
                }
                Vector a = a(view);
                int a2 = a(a);
                a.add(rect);
                float a3 = (((float) (a(a) - a2)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                int width2 = view.getWidth() * view.getHeight();
                width2 = (int) Math.max((double) i, Math.ceil((double) (width2 > 0 ? 100.0f / ((float) width2) : 100.0f)));
                float f = ((float) width2) / 100.0f;
                if (com.facebook.ads.internal.l.a.t(context)) {
                    if (a3 < f) {
                        String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(a3), Float.valueOf(f)});
                        return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3);
                    }
                } else if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < width) {
                    return new b(c.AD_OFFSCREEN_HORIZONTALLY, a3);
                } else {
                    int i2 = (int) (((100.0d - ((double) width2)) * ((double) height)) / 100.0d);
                    if (rect.top - iArr[1] > i2) {
                        return new b(c.AD_OFFSCREEN_TOP, a3);
                    }
                    if ((iArr[1] + height) - rect.bottom > i2) {
                        return new b(c.AD_OFFSCREEN_BOTTOM, a3);
                    }
                }
                if (!com.facebook.ads.internal.q.e.a.b(context)) {
                    return new b(c.SCREEN_NOT_INTERACTIVE, a3);
                }
                Map a4 = com.facebook.ads.internal.q.e.b.a(context);
                if (w.b(a4)) {
                    return new b(c.AD_IS_OBSTRUCTED_BY_KEYGUARD, a3);
                }
                if (com.facebook.ads.internal.l.a.c(context) && w.a(a4)) {
                    return new b(c.AD_IN_LOCKSCREEN, a3, a4);
                }
                Float f2 = null;
                if (com.facebook.ads.internal.l.a.r(context)) {
                    Activity a5 = com.facebook.ads.internal.q.a.a.a();
                    if (a5 == null) {
                        f2 = null;
                    } else {
                        View findViewById = a5.findViewById(16908290);
                        if (findViewById == null) {
                            findViewById = a5.getWindow().getDecorView().findViewById(16908290);
                        }
                        f2 = findViewById == null ? null : (view == null || view.getId() == -1) ? null : findViewById.findViewById(view.getId()) == null ? Float.valueOf(-1.0f) : d.a(findViewById, view);
                    }
                }
                if (f2 != null) {
                    if (f2.floatValue() == -1.0f) {
                        return new b(c.AD_IS_NOT_IN_ACTIVITY);
                    }
                    if (f2.floatValue() == 0.0f) {
                        return new b(c.AD_IS_NOT_VISIBLE);
                    }
                }
                if (!com.facebook.ads.internal.l.a.s(context) || f2 == null || f2.floatValue() >= f) {
                    return new b(c.IS_VIEWABLE, a3, a4);
                }
                String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{f2, Float.valueOf(f)});
                return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3, a4);
            } catch (NullPointerException e) {
                return new b(c.INVALID_DIMENSIONS);
            }
        }
    }

    private static Vector<Rect> a(View view) {
        Vector<Rect> vector = new Vector();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        View view2 = (ViewGroup) view.getParent();
        int indexOfChild = view2.indexOfChild(view);
        while (true) {
            indexOfChild++;
            if (indexOfChild < view2.getChildCount()) {
                vector.addAll(b(view2.getChildAt(indexOfChild)));
            } else {
                vector.addAll(a(view2));
                return vector;
            }
        }
    }

    private static Vector<Rect> b(View view) {
        int i = 0;
        Vector<Rect> vector = new Vector();
        if (!view.isShown() || (VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if (view instanceof ViewGroup) {
            int i2 = (view.getBackground() == null || (VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0)) ? 1 : 0;
            if (i2 != 0) {
                ViewGroup viewGroup = (ViewGroup) view;
                while (i < viewGroup.getChildCount()) {
                    vector.addAll(b(viewGroup.getChildAt(i)));
                    i++;
                }
                return vector;
            }
        }
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            vector.add(rect);
        }
        return vector;
    }

    @VisibleForTesting
    private static float c(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            View view2 = (View) view.getParent();
            float alpha2 = view2.getAlpha();
            if (alpha2 < 0.0f) {
                alpha2 = 0.0f;
            }
            if (alpha2 > 1.0f) {
                alpha2 = 1.0f;
            }
            alpha *= alpha2;
            view = view2;
        }
        return alpha;
    }

    public final synchronized void a() {
        if (this.h != null) {
            c();
        }
        this.h = new b(this);
        this.f.postDelayed(this.h, (long) this.i);
        this.k = false;
        this.o = 0;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
    }

    public final void a(int i) {
        this.i = i;
    }

    public final synchronized void a(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.l.b()));
        map.put("vp", String.valueOf(this.l.c()));
        map.put("vh", new JSONObject(this.m).toString());
        map.put("vt", r.a(this.n));
        map.putAll(this.l.d());
    }

    public final void b(int i) {
        this.j = i;
    }

    public final synchronized boolean b() {
        return this.k;
    }

    public final synchronized void c() {
        this.f.removeCallbacks(this.h);
        this.h = null;
        this.k = true;
        this.o = 0;
    }

    public final synchronized String d() {
        return c.values()[this.l.b()].toString() + String.format(" (%.1f%%)", new Object[]{Float.valueOf(this.l.c() * 100.0f)});
    }
}
