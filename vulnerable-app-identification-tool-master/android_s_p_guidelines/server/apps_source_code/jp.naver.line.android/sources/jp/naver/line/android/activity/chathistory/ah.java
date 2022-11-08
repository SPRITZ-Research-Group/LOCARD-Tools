package jp.naver.line.android.activity.chathistory;

import com.linecorp.rxeventbus.a;
import defpackage.usp;
import defpackage.usr;

final class ah {
    usp a = usp.a;
    private final a b;
    private final usr c;

    ah(a aVar, usr usr) {
        this.b = aVar;
        this.c = usr;
    }

    @com.linecorp.rxeventbus.Subscribe(a = com.linecorp.rxeventbus.SubscriberType.BACKGROUND)
    public final void onMessageRead(defpackage.uss r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.chathistory.ah.onMessageRead(uss):void. bs: []
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
        r6 = this;
        r0 = r6.a;
        r7 = r7.a(r0);
        if (r7 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r0 = r6.c;	 Catch:{ adfz -> 0x002e }
        r7 = r6.a;	 Catch:{ adfz -> 0x002e }
        r1 = r7.a();	 Catch:{ adfz -> 0x002e }
        r7 = r6.a;	 Catch:{ adfz -> 0x002e }
        r2 = r7.b();	 Catch:{ adfz -> 0x002e }
        r7 = r6.a;	 Catch:{ adfz -> 0x002e }
        r4 = r7.c();	 Catch:{ adfz -> 0x002e }
        r7 = r0.a(r1, r2, r4);	 Catch:{ adfz -> 0x002e }
        r6.a = r7;
        r0 = r6.b;
        r1 = new qth;
        r1.<init>(r7);
        r0.a(r1);
        return;
    L_0x002e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.chathistory.ah.onMessageRead(uss):void");
    }

    @com.linecorp.rxeventbus.Subscribe(a = com.linecorp.rxeventbus.SubscriberType.BACKGROUND)
    public final void onChatMessageListChanged(defpackage.qtg r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.chathistory.ah.onChatMessageListChanged(qtg):void. bs: []
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
        r6 = this;
        r7 = r7.a();
        r0 = r6.c;	 Catch:{ adfz -> 0x0023 }
        r1 = r7.j();	 Catch:{ adfz -> 0x0023 }
        r2 = r7.a();	 Catch:{ adfz -> 0x0023 }
        r4 = r7.b();	 Catch:{ adfz -> 0x0023 }
        r7 = r0.a(r1, r2, r4);	 Catch:{ adfz -> 0x0023 }
        r6.a = r7;
        r0 = r6.b;
        r1 = new qth;
        r1.<init>(r7);
        r0.a(r1);
        return;
    L_0x0023:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.chathistory.ah.onChatMessageListChanged(qtg):void");
    }
}
