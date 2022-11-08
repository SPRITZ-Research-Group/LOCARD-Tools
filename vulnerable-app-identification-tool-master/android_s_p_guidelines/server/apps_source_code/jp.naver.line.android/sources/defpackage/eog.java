package defpackage;

import jp.naver.line.android.LineApplication;
import kotlin.Metadata;
import kotlin.e;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u000eH\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/linecorp/line/application/ApplicationForegroundOrBackgroundStateChangeHandler;", "", "lineApplication", "Ljp/naver/line/android/LineApplication;", "(Ljp/naver/line/android/LineApplication;)V", "deviceStateReporter", "Ljp/naver/line/android/settings/devicestate/DeviceStateReporter;", "getDeviceStateReporter", "()Ljp/naver/line/android/settings/devicestate/DeviceStateReporter;", "deviceStateReporter$delegate", "Lkotlin/Lazy;", "getLineApplication", "()Ljp/naver/line/android/LineApplication;", "onApplicationBackground", "", "onApplicationForeground", "onApplicationForegroundEvent", "state", "Lcom/linecorp/line/application/ApplicationForegroundOrBackgroundState;", "removeTempCacheFolder", "Companion", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: eog */
public final class eog {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(eog.class), "deviceStateReporter", "getDeviceStateReporter()Ljp/naver/line/android/settings/devicestate/DeviceStateReporter;"))};
    public static final eoh b = new eoh();
    private final e c = h.a(new a(this));
    private final LineApplication d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/settings/devicestate/DeviceStateReporter;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: eog$a */
    final class a extends acrz implements acqq<uuo> {
        final /* synthetic */ eog a;

        a(eog eog) {
            this.a = eog;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new uuo(this.a.a(), this.a.a().f().c(), this.a.a().f().i());
        }
    }

    public eog(LineApplication lineApplication) {
        this.d = lineApplication;
    }

    public final LineApplication a() {
        return this.d;
    }

    @com.linecorp.rxeventbus.Subscribe(a = com.linecorp.rxeventbus.SubscriberType.BACKGROUND)
    public final void onApplicationForegroundEvent(defpackage.eof r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:eog.onApplicationForegroundEvent(eof):void. bs: []
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
        r4 = this;
        r0 = defpackage.eoi.a;
        r5 = r5.ordinal();
        r5 = r0[r5];
        r0 = 3;
        r1 = 0;
        switch(r5) {
            case 1: goto L_0x0094;
            case 2: goto L_0x000f;
            default: goto L_0x000d;
        };
    L_0x000d:
        goto L_0x00f7;
    L_0x000f:
        r5 = defpackage.uyx.p();
        r5.n();
        r5 = jp.naver.line.android.service.f.a();
        r2 = r4.d;
        r5.a(r2);
        r5 = defpackage.smi.b();
        if (r5 == 0) goto L_0x00f7;
    L_0x0025:
        r5 = r4.d;
        r5 = r5.f();
        r5 = r5.f();
        r5.b();
        r5 = r4.d;
        r5 = r5.f();
        r5 = r5.d();
        r5 = r5.e();
        if (r5 == 0) goto L_0x0045;
    L_0x0042:
        r5.b();
    L_0x0045:
        r5 = 1;
        r0 = new java.io.File[r0];	 Catch:{ Exception -> 0x0065 }
        r2 = jp.naver.line.android.obs.e.d();	 Catch:{ Exception -> 0x0065 }
        r0[r1] = r2;	 Catch:{ Exception -> 0x0065 }
        r2 = jp.naver.line.android.obs.e.a();	 Catch:{ Exception -> 0x0065 }
        r0[r5] = r2;	 Catch:{ Exception -> 0x0065 }
        r2 = 2;	 Catch:{ Exception -> 0x0065 }
        r3 = jp.naver.line.android.obs.e.c(r1);	 Catch:{ Exception -> 0x0065 }
        r0[r2] = r3;	 Catch:{ Exception -> 0x0065 }
        r2 = jp.naver.line.android.obs.e.c();	 Catch:{ Exception -> 0x0065 }
        jp.naver.line.android.common.util.io.e.a(r2, r0, r1);	 Catch:{ Exception -> 0x0065 }
        jp.naver.line.android.obs.e.h();	 Catch:{ Exception -> 0x0065 }
    L_0x0065:
        r0 = defpackage.str.a();
        r0.b();
        r0 = r4.d;
        r0 = (android.content.Context) r0;
        r1 = 4;
        jp.naver.line.android.access.remote.LineAccessServiceForWatch.a(r0, r1);
        r0 = jp.naver.line.android.service.n.a();
        r1 = r4.d;
        r1 = (android.content.Context) r1;
        r0.b(r1, r5);
        defpackage.whg.c();
        r0 = r4.d;
        r0 = (android.content.Context) r0;
        jp.naver.line.android.service.LineService.a(r0);
        r0 = jp.naver.line.android.common.access.keep.b.a();
        r0.d();
        jp.naver.line.android.activity.a.a(r5);
        goto L_0x00f7;
    L_0x0094:
        r5 = defpackage.uyx.p();
        r5.m();
        r5 = defpackage.uyp.a();
        r5.d();
        r5 = defpackage.smi.b();
        if (r5 == 0) goto L_0x00f6;
    L_0x00a8:
        r5 = defpackage.uzh.a();
        if (r5 == 0) goto L_0x00b5;
    L_0x00ae:
        r5 = defpackage.uzh.e();
        r5.a(r1);
    L_0x00b5:
        r5 = r4.d;
        r5 = r5.f();
        r5 = r5.f();
        r5.a();
        r5 = r4.d;
        r5 = r5.f();
        r5 = r5.d();
        r5.d();
        r5 = r4.d;
        r5 = (android.content.Context) r5;
        jp.naver.line.android.access.remote.LineAccessServiceForWatch.a(r5, r0);
        r5 = defpackage.utw.a();
        r5.e();
        r5 = jp.naver.line.android.common.access.keep.b.a();
        r5.c();
        r5 = defpackage.vkx.a();
        r5.h();
        r5 = r4.c;
        r5 = r5.d();
        r5 = (defpackage.uuo) r5;
        r5.a();
    L_0x00f6:
        return;
    L_0x00f7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: eog.onApplicationForegroundEvent(eof):void");
    }
}
