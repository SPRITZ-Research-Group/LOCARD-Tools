package androidx.core.widget;

import android.content.Intent;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.Method;
import org.apache.http.protocol.HTTP;

final class m implements Callback {
    private final Callback a;
    private final TextView b;
    private Class c;
    private Method d;
    private boolean e;
    private boolean f = false;

    m(Callback callback, TextView textView) {
        this.a = callback;
        this.b = textView;
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.a.onCreateActionMode(actionMode, menu);
    }

    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.a.onActionItemClicked(actionMode, menuItem);
    }

    public final void onDestroyActionMode(ActionMode actionMode) {
        this.a.onDestroyActionMode(actionMode);
    }

    private static Intent a() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType(HTTP.PLAIN_TEXT_TYPE);
    }

    public final boolean onPrepareActionMode(android.view.ActionMode r12, android.view.Menu r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.core.widget.m.onPrepareActionMode(android.view.ActionMode, android.view.Menu):boolean. bs: []
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
        r11 = this;
        r0 = r11.b;
        r0 = r0.getContext();
        r1 = r0.getPackageManager();
        r2 = r11.f;
        r3 = 0;
        r4 = 1;
        if (r2 != 0) goto L_0x0034;
    L_0x0010:
        r11.f = r4;
        r2 = "com.android.internal.view.menu.MenuBuilder";	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r2 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r11.c = r2;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r2 = r11.c;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r5 = "removeItemAt";	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r6 = new java.lang.Class[r4];	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r7 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r6[r3] = r7;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r2 = r2.getDeclaredMethod(r5, r6);	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r11.d = r2;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        r11.e = r4;	 Catch:{ ClassNotFoundException -> 0x002d, ClassNotFoundException -> 0x002d }
        goto L_0x0034;
    L_0x002d:
        r2 = 0;
        r11.c = r2;
        r11.d = r2;
        r11.e = r3;
    L_0x0034:
        r2 = r11.e;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        if (r2 == 0) goto L_0x0043;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0038:
        r2 = r11.c;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r2 = r2.isInstance(r13);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        if (r2 == 0) goto L_0x0043;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0040:
        r2 = r11.d;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        goto L_0x0053;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0043:
        r2 = r13.getClass();	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r5 = "removeItemAt";	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6 = new java.lang.Class[r4];	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r7 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6[r3] = r7;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r2 = r2.getDeclaredMethod(r5, r6);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0053:
        r5 = r13.size();	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r5 = r5 - r4;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0058:
        if (r5 < 0) goto L_0x0082;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x005a:
        r6 = r13.getItem(r5);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r7 = r6.getIntent();	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        if (r7 == 0) goto L_0x007f;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0064:
        r7 = "android.intent.action.PROCESS_TEXT";	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6 = r6.getIntent();	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6 = r6.getAction();	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6 = r7.equals(r6);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        if (r6 == 0) goto L_0x007f;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x0074:
        r6 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r7 = java.lang.Integer.valueOf(r5);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r6[r3] = r7;	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
        r2.invoke(r13, r6);	 Catch:{ NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e, NoSuchMethodException -> 0x011e }
    L_0x007f:
        r5 = r5 + -1;
        goto L_0x0058;
    L_0x0082:
        r2 = new java.util.ArrayList;
        r2.<init>();
        r5 = r0 instanceof android.app.Activity;
        if (r5 == 0) goto L_0x00d1;
    L_0x008b:
        r5 = a();
        r5 = r1.queryIntentActivities(r5, r3);
        r5 = r5.iterator();
    L_0x0097:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x00d1;
    L_0x009d:
        r6 = r5.next();
        r6 = (android.content.pm.ResolveInfo) r6;
        r7 = r0.getPackageName();
        r8 = r6.activityInfo;
        r8 = r8.packageName;
        r7 = r7.equals(r8);
        if (r7 == 0) goto L_0x00b3;
    L_0x00b1:
        r7 = 1;
        goto L_0x00cb;
    L_0x00b3:
        r7 = r6.activityInfo;
        r7 = r7.exported;
        if (r7 == 0) goto L_0x00ca;
    L_0x00b9:
        r7 = r6.activityInfo;
        r7 = r7.permission;
        if (r7 == 0) goto L_0x00b1;
    L_0x00bf:
        r7 = r6.activityInfo;
        r7 = r7.permission;
        r7 = r0.checkSelfPermission(r7);
        if (r7 != 0) goto L_0x00ca;
    L_0x00c9:
        goto L_0x00b1;
    L_0x00ca:
        r7 = 0;
    L_0x00cb:
        if (r7 == 0) goto L_0x0097;
    L_0x00cd:
        r2.add(r6);
        goto L_0x0097;
    L_0x00d1:
        r0 = 0;
    L_0x00d2:
        r5 = r2.size();
        if (r0 >= r5) goto L_0x011e;
    L_0x00d8:
        r5 = r2.get(r0);
        r5 = (android.content.pm.ResolveInfo) r5;
        r6 = r0 + 100;
        r7 = r5.loadLabel(r1);
        r6 = r13.add(r3, r3, r6, r7);
        r7 = r11.b;
        r8 = a();
        r9 = "android.intent.extra.PROCESS_TEXT_READONLY";
        r10 = r7 instanceof android.text.Editable;
        if (r10 == 0) goto L_0x0102;
    L_0x00f4:
        r10 = r7.onCheckIsTextEditor();
        if (r10 == 0) goto L_0x0102;
    L_0x00fa:
        r7 = r7.isEnabled();
        if (r7 == 0) goto L_0x0102;
    L_0x0100:
        r7 = 1;
        goto L_0x0103;
    L_0x0102:
        r7 = 0;
    L_0x0103:
        r7 = r7 ^ r4;
        r7 = r8.putExtra(r9, r7);
        r8 = r5.activityInfo;
        r8 = r8.packageName;
        r5 = r5.activityInfo;
        r5 = r5.name;
        r5 = r7.setClassName(r8, r5);
        r5 = r6.setIntent(r5);
        r5.setShowAsAction(r4);
        r0 = r0 + 1;
        goto L_0x00d2;
    L_0x011e:
        r0 = r11.a;
        r12 = r0.onPrepareActionMode(r12, r13);
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.m.onPrepareActionMode(android.view.ActionMode, android.view.Menu):boolean");
    }
}
