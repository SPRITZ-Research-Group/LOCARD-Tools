package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

public abstract class bd implements ServiceConnection {
    private final Context a;
    private final Handler b;
    private be c;
    private boolean d;
    private Messenger e;
    private int f;
    private int g;
    private final String h;
    private final int i;

    protected abstract void a(Bundle bundle);

    public bd(Context context, int i, int i2, int i3, String str) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.a = context;
        this.f = i;
        this.g = i2;
        this.h = str;
        this.i = i3;
        this.b = new Handler(this) {
            final /* synthetic */ bd a;

            {
                this.a = r1;
            }

            public final void handleMessage(Message message) {
                this.a.a(message);
            }
        };
    }

    public final void a(be beVar) {
        this.c = beVar;
    }

    public final boolean a() {
        if (this.d || av.b(this.i) == -1) {
            return false;
        }
        Intent a = av.a(this.a);
        if (a == null) {
            return false;
        }
        this.d = true;
        this.a.bindService(a, this, 1);
        return true;
    }

    public final void b() {
        this.d = false;
    }

    public void onServiceConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bd.onServiceConnected(android.content.ComponentName, android.os.IBinder):void. bs: []
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
        r2 = this;
        r3 = new android.os.Messenger;
        r3.<init>(r4);
        r2.e = r3;
        r3 = new android.os.Bundle;
        r3.<init>();
        r4 = "com.facebook.platform.extra.APPLICATION_ID";
        r0 = r2.h;
        r3.putString(r4, r0);
        r2.a(r3);
        r4 = r2.f;
        r0 = 0;
        r4 = android.os.Message.obtain(r0, r4);
        r1 = r2.i;
        r4.arg1 = r1;
        r4.setData(r3);
        r3 = new android.os.Messenger;
        r1 = r2.b;
        r3.<init>(r1);
        r4.replyTo = r3;
        r3 = r2.e;	 Catch:{ RemoteException -> 0x0033 }
        r3.send(r4);	 Catch:{ RemoteException -> 0x0033 }
        return;
    L_0x0033:
        r2.b(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bd.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onServiceDisconnected(android.content.ComponentName r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bd.onServiceDisconnected(android.content.ComponentName):void. bs: []
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
        r1 = this;
        r2 = 0;
        r1.e = r2;
        r0 = r1.a;	 Catch:{ IllegalArgumentException -> 0x0008 }
        r0.unbindService(r1);	 Catch:{ IllegalArgumentException -> 0x0008 }
    L_0x0008:
        r1.b(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bd.onServiceDisconnected(android.content.ComponentName):void");
    }

    protected final void a(android.os.Message r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bd.a(android.os.Message):void. bs: []
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
        r2 = this;
        r0 = r3.what;
        r1 = r2.g;
        if (r0 != r1) goto L_0x0020;
    L_0x0006:
        r3 = r3.getData();
        r0 = "com.facebook.platform.status.ERROR_TYPE";
        r0 = r3.getString(r0);
        if (r0 == 0) goto L_0x0017;
    L_0x0012:
        r3 = 0;
        r2.b(r3);
        goto L_0x001a;
    L_0x0017:
        r2.b(r3);
    L_0x001a:
        r3 = r2.a;	 Catch:{ IllegalArgumentException -> 0x0020 }
        r3.unbindService(r2);	 Catch:{ IllegalArgumentException -> 0x0020 }
        return;
    L_0x0020:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bd.a(android.os.Message):void");
    }

    private void b(Bundle bundle) {
        if (this.d) {
            this.d = false;
            be beVar = this.c;
            if (beVar != null) {
                beVar.a(bundle);
            }
        }
    }
}
