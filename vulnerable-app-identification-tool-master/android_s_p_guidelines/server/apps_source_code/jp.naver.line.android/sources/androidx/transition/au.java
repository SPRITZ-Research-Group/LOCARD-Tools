package androidx.transition;

import android.animation.LayoutTransition;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class au {
    private static LayoutTransition a;
    private static Field b;
    private static boolean c;
    private static Method d;
    private static boolean e;

    static void a(android.view.ViewGroup r5, boolean r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.transition.au.a(android.view.ViewGroup, boolean):void. bs: []
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
        r0 = a;
        r1 = 1;
        r2 = 0;
        r3 = 0;
        if (r0 != 0) goto L_0x0028;
    L_0x0007:
        r0 = new androidx.transition.au$1;
        r0.<init>();
        a = r0;
        r4 = 2;
        r0.setAnimator(r4, r3);
        r0 = a;
        r0.setAnimator(r2, r3);
        r0 = a;
        r0.setAnimator(r1, r3);
        r0 = a;
        r4 = 3;
        r0.setAnimator(r4, r3);
        r0 = a;
        r4 = 4;
        r0.setAnimator(r4, r3);
    L_0x0028:
        if (r6 == 0) goto L_0x007d;
    L_0x002a:
        r6 = r5.getLayoutTransition();
        if (r6 == 0) goto L_0x0077;
    L_0x0030:
        r0 = r6.isRunning();
        if (r0 == 0) goto L_0x006e;
    L_0x0036:
        r0 = e;
        if (r0 != 0) goto L_0x0053;
    L_0x003a:
        r0 = android.animation.LayoutTransition.class;	 Catch:{ NoSuchMethodException -> 0x004a }
        r3 = "cancel";	 Catch:{ NoSuchMethodException -> 0x004a }
        r4 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x004a }
        r0 = r0.getDeclaredMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x004a }
        d = r0;	 Catch:{ NoSuchMethodException -> 0x004a }
        r0.setAccessible(r1);	 Catch:{ NoSuchMethodException -> 0x004a }
        goto L_0x0051;
    L_0x004a:
        r0 = "ViewGroupUtilsApi14";
        r3 = "Failed to access cancel method by reflection";
        android.util.Log.i(r0, r3);
    L_0x0051:
        e = r1;
    L_0x0053:
        r0 = d;
        if (r0 == 0) goto L_0x006e;
    L_0x0057:
        r0 = d;	 Catch:{ IllegalAccessException -> 0x0067, InvocationTargetException -> 0x005f }
        r1 = new java.lang.Object[r2];	 Catch:{ IllegalAccessException -> 0x0067, InvocationTargetException -> 0x005f }
        r0.invoke(r6, r1);	 Catch:{ IllegalAccessException -> 0x0067, InvocationTargetException -> 0x005f }
        goto L_0x006e;
    L_0x005f:
        r0 = "ViewGroupUtilsApi14";
        r1 = "Failed to invoke cancel method by reflection";
        android.util.Log.i(r0, r1);
        goto L_0x006e;
    L_0x0067:
        r0 = "ViewGroupUtilsApi14";
        r1 = "Failed to access cancel method by reflection";
        android.util.Log.i(r0, r1);
    L_0x006e:
        r0 = a;
        if (r6 == r0) goto L_0x0077;
    L_0x0072:
        r0 = androidx.transition.t.transition_layout_save;
        r5.setTag(r0, r6);
    L_0x0077:
        r6 = a;
        r5.setLayoutTransition(r6);
        return;
    L_0x007d:
        r5.setLayoutTransition(r3);
        r6 = c;
        if (r6 != 0) goto L_0x009b;
    L_0x0084:
        r6 = android.view.ViewGroup.class;	 Catch:{ NoSuchFieldException -> 0x0092 }
        r0 = "mLayoutSuppressed";	 Catch:{ NoSuchFieldException -> 0x0092 }
        r6 = r6.getDeclaredField(r0);	 Catch:{ NoSuchFieldException -> 0x0092 }
        b = r6;	 Catch:{ NoSuchFieldException -> 0x0092 }
        r6.setAccessible(r1);	 Catch:{ NoSuchFieldException -> 0x0092 }
        goto L_0x0099;
    L_0x0092:
        r6 = "ViewGroupUtilsApi14";
        r0 = "Failed to access mLayoutSuppressed field by reflection";
        android.util.Log.i(r6, r0);
    L_0x0099:
        c = r1;
    L_0x009b:
        r6 = b;
        if (r6 == 0) goto L_0x00b8;
    L_0x009f:
        r6 = b;	 Catch:{ IllegalAccessException -> 0x00b1 }
        r6 = r6.getBoolean(r5);	 Catch:{ IllegalAccessException -> 0x00b1 }
        if (r6 == 0) goto L_0x00af;
    L_0x00a7:
        r0 = b;	 Catch:{ IllegalAccessException -> 0x00ad }
        r0.setBoolean(r5, r2);	 Catch:{ IllegalAccessException -> 0x00ad }
        goto L_0x00af;
    L_0x00ad:
        r2 = r6;
        goto L_0x00b1;
    L_0x00af:
        r2 = r6;
        goto L_0x00b8;
    L_0x00b1:
        r6 = "ViewGroupUtilsApi14";
        r0 = "Failed to get mLayoutSuppressed field by reflection";
        android.util.Log.i(r6, r0);
    L_0x00b8:
        if (r2 == 0) goto L_0x00bd;
    L_0x00ba:
        r5.requestLayout();
    L_0x00bd:
        r6 = androidx.transition.t.transition_layout_save;
        r6 = r5.getTag(r6);
        r6 = (android.animation.LayoutTransition) r6;
        if (r6 == 0) goto L_0x00cf;
    L_0x00c7:
        r0 = androidx.transition.t.transition_layout_save;
        r5.setTag(r0, r3);
        r5.setLayoutTransition(r6);
    L_0x00cf:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.au.a(android.view.ViewGroup, boolean):void");
    }
}
