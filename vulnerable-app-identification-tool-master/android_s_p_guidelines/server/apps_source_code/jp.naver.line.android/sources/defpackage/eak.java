package defpackage;

import androidx.fragment.app.FragmentActivity;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J(\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bH\u0002J8\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\bH\u0002J8\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\bH\u0002J2\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001e0\u001dj\b\u0012\u0004\u0012\u00020\u001e`\u001f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J(\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J*\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00102\b\u0010.\u001a\u0004\u0018\u00010/H\u0007¨\u00060"}, d2 = {"Lcom/linecorp/line/ad/handler/service/AdServiceBuilder;", "", "()V", "build", "Lcom/linecorp/line/ad/core/renderer/AdServiceManager;", "spec", "Lcom/linecorp/line/ad/handler/service/AdServiceSpec;", "accessor", "Lcom/linecorp/line/ad/handler/MediaAccessor;", "buildAssetClickEventTracker", "", "tracker", "Lcom/linecorp/line/ad/core/tracker/AdEventCollector;", "trackingIterator", "Lcom/linecorp/line/ad/handler/service/AdEventSpec;", "key", "", "buildClickEventTrackerInternal", "buildEmbededAssetTracker", "trackingSpec", "Lcom/linecorp/line/ad/handler/service/AdEventSpecs;", "buildEventTracker", "layoutSpec", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$LayoutSpec;", "buildEventTrackerInternal", "value", "Lcom/linecorp/line/ad/handler/service/AdInventorySpec$ViewSpec;", "buildImpressionEventTrackerInternal", "buildInventory", "Ljava/util/ArrayList;", "Lcom/linecorp/line/ad/core/renderer/inventory/AdInventoryManager;", "Lkotlin/collections/ArrayList;", "videoPlayer", "Lcom/linecorp/line/ad/core/renderer/video/LineMultimediaVideoPlayer;", "getImpressionEventTracker", "Lcom/linecorp/line/ad/core/tracker/AdImpressionEventEmitter;", "type", "id", "isCheckableImpression", "", "lazyInitialize", "adContext", "Lcom/linecorp/line/ad/handler/AdContext;", "serviceId", "", "statusBarHeight", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "line-android-ladsdk_release"}, k = 1, mv = {1, 1, 13})
/* renamed from: eak */
public final class eak {
    public static final eak a = new eak();

    private eak() {
    }

    public static defpackage.dvy a(defpackage.eam r14, defpackage.dxq r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r13_2 java.lang.Object) in PHI: PHI: (r13_3 java.lang.Object) = (r13_1 java.lang.Object), (r13_2 java.lang.Object) binds: {(r13_1 java.lang.Object)=B:34:0x00b5, (r13_2 java.lang.Object)=B:33:0x00b5}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r0 = new dvu;
        r1 = r14.f();
        r0.<init>(r1, r14);
        r1 = r0.b();
        r9 = r14.f();
        r2 = r14.a();
        r10 = r14.b();
        r11 = new java.util.ArrayList;
        r11.<init>();
        r2 = (java.lang.Iterable) r2;
        r12 = r2.iterator();
    L_0x0024:
        r2 = r12.hasNext();
        if (r2 == 0) goto L_0x00ba;
    L_0x002a:
        r2 = r12.next();
        r8 = r2;
        r8 = (defpackage.dza) r8;
        r2 = r8.j();
        r3 = r8.h();
        r5 = defpackage.eak.a(r2, r3, r15);
        r2 = r8.f();
        if (r2 == 0) goto L_0x0076;
    L_0x0043:
        if (r1 == 0) goto L_0x0068;
    L_0x0045:
        r2 = r8.g();
        r3 = r8.h();
        r2 = r1.a(r2, r3);
        r3 = new dxh;
        r3.<init>(r15);
        r5.a(r3);
        r3 = new com.linecorp.line.ad.core.renderer.video.e;
        r4 = r8.b();
        r3.<init>(r2, r5, r15, r4);
        r2.a(r3);
        r2 = (defpackage.dvv) r2;
        goto L_0x007f;
    L_0x0068:
        r14 = new java.lang.IllegalStateException;
        r15 = "you must set video player";
        r15 = r15.toString();
        r14.<init>(r15);
        r14 = (java.lang.Throwable) r14;
        throw r14;
    L_0x0076:
        r2 = new dvv;
        r3 = r8.h();
        r2.<init>(r3);
    L_0x007f:
        r6 = r2;
        r2 = r8.d();
        if (r2 == 0) goto L_0x009f;
    L_0x0086:
        r13 = new dvz;
        r4 = r8.g();
        r2 = r8.g();
        r2 = r10.get(r2);
        r7 = r2;
        r7 = (defpackage.dwe) r7;
        r2 = r13;
        r3 = r9;
        r2.<init>(r3, r4, r5, r6, r7, r8);
        r13 = (defpackage.dwg) r13;
        goto L_0x00b5;
    L_0x009f:
        r13 = new dwg;
        r4 = r8.g();
        r2 = r8.g();
        r2 = r10.get(r2);
        r7 = r2;
        r7 = (defpackage.dwe) r7;
        r2 = r13;
        r3 = r9;
        r2.<init>(r3, r4, r5, r6, r7, r8);
    L_0x00b5:
        r11.add(r13);
        goto L_0x0024;
    L_0x00ba:
        r0.a = r11;
        r15 = r14.c();
        r0.a(r15);
        r15 = new drf;
        r15.<init>();
        r1 = r14.h();
        r1 = r1.c();
        if (r1 == 0) goto L_0x00d4;
    L_0x00d2:
        if (r1 != 0) goto L_0x00d9;
    L_0x00d4:
        r1 = new dri;
        r1.<init>();
    L_0x00d9:
        r15.b = r1;
        r1 = new drg;
        r2 = r14.h();
        r2 = r2.b();
        r4 = r14.h();
        r4 = r4.a();
        r1.<init>(r2, r4);
        r15.a = r1;
        r0.b = r15;
        r15 = r14.e();
        if (r15 == 0) goto L_0x0136;
    L_0x00fa:
        r1 = "data combiner enabled";
        defpackage.dox.a(r1);
        r1 = r15.a();
        r2 = r1 instanceof defpackage.eas;
        if (r2 == 0) goto L_0x0115;
    L_0x0107:
        r1 = new dwm;
        r15 = r15.a();
        r15 = (defpackage.eas) r15;
        r1.<init>(r15);
        r1 = (defpackage.dwi) r1;
        goto L_0x0133;
    L_0x0115:
        r1 = r1 instanceof defpackage.eap;
        if (r1 == 0) goto L_0x0125;
    L_0x0119:
        r1 = new dwk;
        r15 = r15.a();
        r1.<init>(r15);
        r1 = (defpackage.dwi) r1;
        goto L_0x0133;
    L_0x0125:
        r15 = 0;
        r1 = "unknow combiner was founded";
        defpackage.dox.a(r15, r1);
        r15 = new dwn;
        r15.<init>();
        r1 = r15;
        r1 = (defpackage.dwi) r1;
    L_0x0133:
        r0.a(r1);
    L_0x0136:
        r14 = r14.b();
        r0.c = r14;
        r0 = (defpackage.dvy) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: eak.a(eam, dxq):dvy");
    }

    public static final void a(dxm dxm, String str, int i, FragmentActivity fragmentActivity) {
        dvu dvu = (dvu) dxm.a(str);
        dox.a(dvu != null);
        if (dvu != null) {
            for (dwb dwb : dvu.c()) {
                if (dwb instanceof dwg) {
                    dwg dwg = (dwg) dwb;
                    dwg.b(i);
                    dwg.a(fragmentActivity);
                }
            }
        }
    }

    private static dww a(dyt dyt, dyu dyu, int i, dxq dxq) {
        if (dyu.b()) {
            return new dwx(dyt, i, dxq);
        }
        if (!dyu.a()) {
            return new dwv(dyt, i, dxq);
        }
        return new dwy(dyt, i, dxq, dyu.c(), dyu.d());
    }

    private static dwq a(dyu dyu, dzk dzk, dxq dxq) {
        dyu dyu2 = dyu;
        dxq dxq2 = dxq;
        dwq dwq = new dwq();
        if (dyu.e()) {
            Map b = dwq.b();
            Integer valueOf = Integer.valueOf(dpx.MuteClose.a());
            dyt dyt = new dyt();
            dyt.a = new dyr(dys.Click, false);
            dyt.b = "muteClose";
            dyt.b(true);
            b.put(valueOf, new dwo(dyt, dpx.MuteClose.a(), dxq, true, (byte) 0));
        }
        for (Entry entry : dzk.b().entrySet()) {
            for (dyt dyt2 : dyu.f()) {
                int intValue = ((Number) entry.getKey()).intValue();
                eaf eaf = (eaf) entry.getValue();
                dyw b2 = dyt2.b();
                if (b2 instanceof dyy) {
                    if (dyt2.p()) {
                        Object obj = (eaf.a() == eat.Image || eaf.a() == eat.Video) ? 1 : null;
                        if (obj != null) {
                            if (!dyt2.q()) {
                                dwq.b().put(Integer.valueOf(intValue), eak.a(dyt2, dyu2, intValue, dxq2));
                            } else if (dyt2.s() == intValue) {
                                dwq.b().put(Integer.valueOf(intValue), eak.a(dyt2, dyu2, intValue, dxq2));
                            }
                        }
                    }
                    if (dyt2.t() && dwq.a().get(dyt2) == null) {
                        dwq.a().put(dyt2, eak.a(dyt2, dyu2, dpx.CUSTOM_VIEW.a(), dxq2));
                    }
                } else if (b2 instanceof dyr) {
                    if (dyt2.p() && ((!dyt2.q() || dyt2.s() == intValue) && dwq.b().get(Integer.valueOf(intValue)) == null)) {
                        Map b3 = dwq.b();
                        dwo dwo = r1;
                        Integer valueOf2 = Integer.valueOf(intValue);
                        dwo dwo2 = new dwo(dyt2, intValue, dxq, true, (byte) 0);
                        b3.put(valueOf2, dwo);
                    }
                    if (dyt2.t() && dwq.a().get(dyt2) == null) {
                        dwq.a().put(dyt2, new dwo(dyt2, dpx.CUSTOM_VIEW.a(), dxq, false, (byte) 0));
                    }
                } else {
                    throw new IllegalStateException("Not supported event".toString());
                }
            }
        }
        return dwq;
    }
}
