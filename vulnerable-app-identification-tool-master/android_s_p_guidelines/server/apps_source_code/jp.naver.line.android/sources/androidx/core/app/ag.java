package androidx.core.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import java.util.HashSet;
import java.util.Set;

public final class ag {
    private static final Object a = new Object();
    private static String b;
    private static Set<String> c = new HashSet();
    private static final Object f = new Object();
    private static ak g;
    private final Context d;
    private final NotificationManager e = ((NotificationManager) this.d.getSystemService("notification"));

    public static ag a(Context context) {
        return new ag(context);
    }

    private ag(Context context) {
        this.d = context;
    }

    public final void a(String str, int i) {
        this.e.cancel(str, i);
        if (VERSION.SDK_INT <= 19) {
            a(new ah(this.d.getPackageName(), i, str));
        }
    }

    public final void a() {
        this.e.cancelAll();
        if (VERSION.SDK_INT <= 19) {
            a(new ah(this.d.getPackageName()));
        }
    }

    public final void a(int i, Notification notification) {
        a(null, i, notification);
    }

    public final void a(String str, int i, Notification notification) {
        if (a(notification)) {
            a(new ai(this.d.getPackageName(), i, str, notification));
            this.e.cancel(str, i);
            return;
        }
        this.e.notify(str, i, notification);
    }

    public final boolean b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.core.app.ag.b():boolean. bs: []
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
        r11 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 24;
        if (r0 < r1) goto L_0x000d;
    L_0x0006:
        r0 = r11.e;
        r0 = r0.areNotificationsEnabled();
        return r0;
    L_0x000d:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 19;
        r2 = 1;
        if (r0 < r1) goto L_0x0082;
    L_0x0014:
        r0 = r11.d;
        r1 = "appops";
        r0 = r0.getSystemService(r1);
        r0 = (android.app.AppOpsManager) r0;
        r1 = r11.d;
        r1 = r1.getApplicationInfo();
        r3 = r11.d;
        r3 = r3.getApplicationContext();
        r3 = r3.getPackageName();
        r1 = r1.uid;
        r4 = android.app.AppOpsManager.class;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = r4.getName();	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = java.lang.Class.forName(r4);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r5 = "checkOpNoThrow";	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r6 = 3;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7 = new java.lang.Class[r6];	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r8 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r9 = 0;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7[r9] = r8;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r8 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7[r2] = r8;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r8 = java.lang.String.class;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r10 = 2;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7[r10] = r8;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r5 = r4.getMethod(r5, r7);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7 = "OP_POST_NOTIFICATION";	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = r4.getDeclaredField(r7);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r7 = java.lang.Integer.class;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = r4.get(r7);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = (java.lang.Integer) r4;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = r4.intValue();	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r6 = new java.lang.Object[r6];	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r6[r9] = r4;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r6[r2] = r1;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r6[r10] = r3;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r0 = r5.invoke(r0, r6);	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r0 = (java.lang.Integer) r0;	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        r0 = r0.intValue();	 Catch:{ ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081, ClassNotFoundException -> 0x0081 }
        if (r0 != 0) goto L_0x0080;
    L_0x007f:
        return r2;
    L_0x0080:
        return r9;
    L_0x0081:
        return r2;
    L_0x0082:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ag.b():boolean");
    }

    public static Set<String> b(Context context) {
        String string = Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (a) {
            if (string != null) {
                if (!string.equals(b)) {
                    String[] split = string.split(":", -1);
                    Set hashSet = new HashSet(split.length);
                    for (String unflattenFromString : split) {
                        ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
                        if (unflattenFromString2 != null) {
                            hashSet.add(unflattenFromString2.getPackageName());
                        }
                    }
                    c = hashSet;
                    b = string;
                }
            }
        }
        return c;
    }

    private static boolean a(Notification notification) {
        Bundle a = w.a(notification);
        return a != null && a.getBoolean("android.support.useSideChannel");
    }

    private void a(am amVar) {
        synchronized (f) {
            if (g == null) {
                g = new ak(this.d.getApplicationContext());
            }
            g.a(amVar);
        }
    }
}
