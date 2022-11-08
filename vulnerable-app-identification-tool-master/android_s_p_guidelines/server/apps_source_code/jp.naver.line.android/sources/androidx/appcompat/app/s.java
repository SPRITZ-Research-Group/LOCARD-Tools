package androidx.appcompat.app;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Method;

final class s implements OnClickListener {
    private final View a;
    private final String b;
    private Method c;
    private Context d;

    public s(View view, String str) {
        this.a = view;
        this.b = str;
    }

    public final void onClick(android.view.View r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.appcompat.app.s.onClick(android.view.View):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r7 = this;
        r0 = r7.c;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x008c;
    L_0x0006:
        r0 = r7.a;
        r0 = r0.getContext();
    L_0x000c:
        if (r0 == 0) goto L_0x0038;
    L_0x000e:
        r3 = r0.isRestricted();	 Catch:{ NoSuchMethodException -> 0x002b }
        if (r3 != 0) goto L_0x002b;	 Catch:{ NoSuchMethodException -> 0x002b }
    L_0x0014:
        r3 = r0.getClass();	 Catch:{ NoSuchMethodException -> 0x002b }
        r4 = r7.b;	 Catch:{ NoSuchMethodException -> 0x002b }
        r5 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x002b }
        r6 = android.view.View.class;	 Catch:{ NoSuchMethodException -> 0x002b }
        r5[r1] = r6;	 Catch:{ NoSuchMethodException -> 0x002b }
        r3 = r3.getMethod(r4, r5);	 Catch:{ NoSuchMethodException -> 0x002b }
        if (r3 == 0) goto L_0x002b;	 Catch:{ NoSuchMethodException -> 0x002b }
    L_0x0026:
        r7.c = r3;	 Catch:{ NoSuchMethodException -> 0x002b }
        r7.d = r0;	 Catch:{ NoSuchMethodException -> 0x002b }
        goto L_0x008c;
    L_0x002b:
        r3 = r0 instanceof android.content.ContextWrapper;
        if (r3 == 0) goto L_0x0036;
    L_0x002f:
        r0 = (android.content.ContextWrapper) r0;
        r0 = r0.getBaseContext();
        goto L_0x000c;
    L_0x0036:
        r0 = 0;
        goto L_0x000c;
    L_0x0038:
        r8 = r7.a;
        r8 = r8.getId();
        r0 = -1;
        if (r8 != r0) goto L_0x0044;
    L_0x0041:
        r8 = "";
        goto L_0x0065;
    L_0x0044:
        r0 = new java.lang.StringBuilder;
        r1 = " with id '";
        r0.<init>(r1);
        r1 = r7.a;
        r1 = r1.getContext();
        r1 = r1.getResources();
        r8 = r1.getResourceEntryName(r8);
        r0.append(r8);
        r8 = "'";
        r0.append(r8);
        r8 = r0.toString();
    L_0x0065:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r2 = "Could not find method ";
        r1.<init>(r2);
        r2 = r7.b;
        r1.append(r2);
        r2 = "(View) in a parent or ancestor Context for android:onClick attribute defined on view ";
        r1.append(r2);
        r2 = r7.a;
        r2 = r2.getClass();
        r1.append(r2);
        r1.append(r8);
        r8 = r1.toString();
        r0.<init>(r8);
        throw r0;
    L_0x008c:
        r0 = r7.c;	 Catch:{ IllegalAccessException -> 0x00a1, InvocationTargetException -> 0x0098 }
        r3 = r7.d;	 Catch:{ IllegalAccessException -> 0x00a1, InvocationTargetException -> 0x0098 }
        r2 = new java.lang.Object[r2];	 Catch:{ IllegalAccessException -> 0x00a1, InvocationTargetException -> 0x0098 }
        r2[r1] = r8;	 Catch:{ IllegalAccessException -> 0x00a1, InvocationTargetException -> 0x0098 }
        r0.invoke(r3, r2);	 Catch:{ IllegalAccessException -> 0x00a1, InvocationTargetException -> 0x0098 }
        return;
    L_0x0098:
        r8 = move-exception;
        r0 = new java.lang.IllegalStateException;
        r1 = "Could not execute method for android:onClick";
        r0.<init>(r1, r8);
        throw r0;
    L_0x00a1:
        r8 = move-exception;
        r0 = new java.lang.IllegalStateException;
        r1 = "Could not execute non-public method for android:onClick";
        r0.<init>(r1, r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s.onClick(android.view.View):void");
    }
}
