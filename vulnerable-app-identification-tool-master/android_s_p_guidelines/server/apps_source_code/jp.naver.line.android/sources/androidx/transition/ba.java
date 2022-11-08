package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Property;
import android.view.View;
import defpackage.hs;
import java.lang.reflect.Field;

final class ba {
    static final Property<View, Float> a = new Property<View, Float>(Float.class, "translationAlpha") {
        public final /* synthetic */ Object get(Object obj) {
            return Float.valueOf(ba.c((View) obj));
        }

        public final /* synthetic */ void set(Object obj, Object obj2) {
            ba.a((View) obj, ((Float) obj2).floatValue());
        }
    };
    static final Property<View, Rect> b = new Property<View, Rect>(Rect.class, "clipBounds") {
        public final /* synthetic */ Object get(Object obj) {
            return hs.E((View) obj);
        }

        public final /* synthetic */ void set(Object obj, Object obj2) {
            hs.a((View) obj, (Rect) obj2);
        }
    };
    private static final be c;
    private static Field d;
    private static boolean e;

    static {
        if (VERSION.SDK_INT >= 22) {
            c = new bd();
        } else if (VERSION.SDK_INT >= 21) {
            c = new bc();
        } else if (VERSION.SDK_INT >= 19) {
            c = new bb();
        } else {
            c = new be();
        }
    }

    static az a(View view) {
        if (VERSION.SDK_INT >= 18) {
            return new ay(view);
        }
        return aw.c(view);
    }

    static bk b(View view) {
        if (VERSION.SDK_INT >= 18) {
            return new bj(view);
        }
        return new bi(view.getWindowToken());
    }

    static void a(View view, float f) {
        c.a(view, f);
    }

    static float c(View view) {
        return c.a(view);
    }

    static void d(View view) {
        c.b(view);
    }

    static void e(View view) {
        c.c(view);
    }

    static void a(View view, Matrix matrix) {
        c.a(view, matrix);
    }

    static void b(View view, Matrix matrix) {
        c.b(view, matrix);
    }

    static void c(View view, Matrix matrix) {
        c.c(view, matrix);
    }

    static void a(View view, int i, int i2, int i3, int i4) {
        c.a(view, i, i2, i3, i4);
    }

    static void a(android.view.View r3, int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.transition.ba.a(android.view.View, int):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = e;
        if (r0 != 0) goto L_0x001c;
    L_0x0004:
        r0 = 1;
        r1 = android.view.View.class;	 Catch:{ NoSuchFieldException -> 0x0013 }
        r2 = "mViewFlags";	 Catch:{ NoSuchFieldException -> 0x0013 }
        r1 = r1.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x0013 }
        d = r1;	 Catch:{ NoSuchFieldException -> 0x0013 }
        r1.setAccessible(r0);	 Catch:{ NoSuchFieldException -> 0x0013 }
        goto L_0x001a;
    L_0x0013:
        r1 = "ViewUtils";
        r2 = "fetchViewFlagsField: ";
        android.util.Log.i(r1, r2);
    L_0x001a:
        e = r0;
    L_0x001c:
        r0 = d;
        if (r0 == 0) goto L_0x002f;
    L_0x0020:
        r0 = d;	 Catch:{ IllegalAccessException -> 0x002f }
        r0 = r0.getInt(r3);	 Catch:{ IllegalAccessException -> 0x002f }
        r1 = d;	 Catch:{ IllegalAccessException -> 0x002f }
        r0 = r0 & -13;	 Catch:{ IllegalAccessException -> 0x002f }
        r4 = r4 | r0;	 Catch:{ IllegalAccessException -> 0x002f }
        r1.setInt(r3, r4);	 Catch:{ IllegalAccessException -> 0x002f }
        return;
    L_0x002f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ba.a(android.view.View, int):void");
    }
}
