package androidx.core.widget;

import android.os.Build.VERSION;
import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class k {
    private static Method a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    public static void a(PopupWindow popupWindow, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z);
            return;
        }
        if (VERSION.SDK_INT >= 21) {
            if (!d) {
                try {
                    Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    c = declaredField;
                    declaredField.setAccessible(true);
                } catch (Throwable e) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e);
                }
                d = true;
            }
            if (c != null) {
                try {
                    c.set(popupWindow, Boolean.valueOf(z));
                } catch (Throwable e2) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e2);
                }
            }
        }
    }

    public static void a(android.widget.PopupWindow r6, int r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.core.widget.k.a(android.widget.PopupWindow, int):void. bs: []
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
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 23;
        if (r0 < r1) goto L_0x000a;
    L_0x0006:
        r6.setWindowLayoutType(r7);
        return;
    L_0x000a:
        r0 = b;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0010:
        r0 = android.widget.PopupWindow.class;	 Catch:{ Exception -> 0x0023 }
        r3 = "setWindowLayoutType";	 Catch:{ Exception -> 0x0023 }
        r4 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0023 }
        r5 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0023 }
        r4[r1] = r5;	 Catch:{ Exception -> 0x0023 }
        r0 = r0.getDeclaredMethod(r3, r4);	 Catch:{ Exception -> 0x0023 }
        a = r0;	 Catch:{ Exception -> 0x0023 }
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0023 }
    L_0x0023:
        b = r2;
    L_0x0025:
        r0 = a;
        if (r0 == 0) goto L_0x0037;
    L_0x0029:
        r0 = a;	 Catch:{ Exception -> 0x0037 }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0037 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0037 }
        r2[r1] = r7;	 Catch:{ Exception -> 0x0037 }
        r0.invoke(r6, r2);	 Catch:{ Exception -> 0x0037 }
        return;
    L_0x0037:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.k.a(android.widget.PopupWindow, int):void");
    }
}
