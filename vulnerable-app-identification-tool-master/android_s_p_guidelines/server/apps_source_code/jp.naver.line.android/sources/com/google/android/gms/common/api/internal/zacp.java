package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zacp {
    public static final Status zakw = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zakx = new BasePendingResult[0];
    private final Map<AnyClientKey<?>, Client> zagy;
    @VisibleForTesting
    final Set<BasePendingResult<?>> zaky = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zacs zakz = new zacq(this);

    public zacp(Map<AnyClientKey<?>, Client> map) {
        this.zagy = map;
    }

    final void zab(BasePendingResult<? extends Result> basePendingResult) {
        this.zaky.add(basePendingResult);
        basePendingResult.zaa(this.zakz);
    }

    public final void release() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.api.internal.zacp.release():void. bs: []
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
        r8 = this;
        r0 = r8.zaky;
        r1 = zakx;
        r0 = r0.toArray(r1);
        r0 = (com.google.android.gms.common.api.internal.BasePendingResult[]) r0;
        r1 = r0.length;
        r2 = 0;
        r3 = 0;
    L_0x000d:
        if (r3 >= r1) goto L_0x0088;
    L_0x000f:
        r4 = r0[r3];
        r5 = 0;
        r4.zaa(r5);
        r6 = r4.zam();
        if (r6 != 0) goto L_0x0027;
    L_0x001b:
        r5 = r4.zat();
        if (r5 == 0) goto L_0x0085;
    L_0x0021:
        r5 = r8.zaky;
        r5.remove(r4);
        goto L_0x0085;
    L_0x0027:
        r4.setResultCallback(r5);
        r6 = r8.zagy;
        r7 = r4;
        r7 = (com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl) r7;
        r7 = r7.getClientKey();
        r6 = r6.get(r7);
        r6 = (com.google.android.gms.common.api.Api.Client) r6;
        r6 = r6.getServiceBrokerBinder();
        r7 = r4.isReady();
        if (r7 == 0) goto L_0x004c;
    L_0x0043:
        r7 = new com.google.android.gms.common.api.internal.zacr;
        r7.<init>(r4, r5, r6, r5);
        r4.zaa(r7);
        goto L_0x0080;
    L_0x004c:
        if (r6 == 0) goto L_0x006f;
    L_0x004e:
        r7 = r6.isBinderAlive();
        if (r7 == 0) goto L_0x006f;
    L_0x0054:
        r7 = new com.google.android.gms.common.api.internal.zacr;
        r7.<init>(r4, r5, r6, r5);
        r4.zaa(r7);
        r6.linkToDeath(r7, r2);	 Catch:{ RemoteException -> 0x0060 }
        goto L_0x0080;
    L_0x0060:
        r4.cancel();
        r6 = r4.zam();
        r6 = r6.intValue();
        r5.remove(r6);
        goto L_0x0080;
    L_0x006f:
        r4.zaa(r5);
        r4.cancel();
        r6 = r4.zam();
        r6 = r6.intValue();
        r5.remove(r6);
    L_0x0080:
        r5 = r8.zaky;
        r5.remove(r4);
    L_0x0085:
        r3 = r3 + 1;
        goto L_0x000d;
    L_0x0088:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zacp.release():void");
    }

    public final void zabx() {
        for (BasePendingResult zab : (BasePendingResult[]) this.zaky.toArray(zakx)) {
            zab.zab(zakw);
        }
    }
}
