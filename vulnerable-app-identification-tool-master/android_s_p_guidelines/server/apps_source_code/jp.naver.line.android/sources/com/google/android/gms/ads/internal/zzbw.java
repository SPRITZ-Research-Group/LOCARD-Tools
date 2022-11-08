package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzaju;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzalc;
import com.google.android.gms.internal.ads.zzamj;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzce;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import defpackage.cg;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@zzadh
public final class zzbw implements OnGlobalLayoutListener, OnScrollChangedListener {
    final String zzaco;
    public String zzacp;
    final zzci zzacq;
    public final zzang zzacr;
    zzbx zzacs;
    public zzajx zzact;
    public zzalc zzacu;
    public zzjn zzacv;
    public zzajh zzacw;
    public zzaji zzacx;
    public zzajj zzacy;
    zzke zzacz;
    zzkh zzada;
    zzla zzadb;
    zzkx zzadc;
    zzlg zzadd;
    zzqw zzade;
    zzqz zzadf;
    zzrl zzadg;
    cg<String, zzrc> zzadh;
    cg<String, zzrf> zzadi;
    zzpl zzadj;
    zzmu zzadk;
    zzlu zzadl;
    zzri zzadm;
    List<Integer> zzadn;
    zzod zzado;
    zzahe zzadp;
    zzagx zzadq;
    public String zzadr;
    List<String> zzads;
    public zzaju zzadt;
    View zzadu;
    public int zzadv;
    private HashSet<zzajj> zzadw;
    private int zzadx;
    private int zzady;
    private zzamj zzadz;
    private boolean zzaea;
    private boolean zzaeb;
    private boolean zzaec;
    public final Context zzrt;
    boolean zzze;

    public zzbw(Context context, zzjn zzjn, String str, zzang zzang) {
        this(context, zzjn, str, zzang, null);
    }

    private zzbw(Context context, zzjn zzjn, String str, zzang zzang, zzci zzci) {
        this.zzadt = null;
        this.zzadu = null;
        this.zzadv = 0;
        this.zzze = false;
        this.zzadw = null;
        this.zzadx = -1;
        this.zzady = -1;
        this.zzaea = true;
        this.zzaeb = true;
        this.zzaec = false;
        zznk.initialize(context);
        if (zzbv.zzeo().zzpy() != null) {
            List zzjc = zznk.zzjc();
            if (zzang.zzcve != 0) {
                zzjc.add(Integer.toString(zzang.zzcve));
            }
            zzbv.zzeo().zzpy().zzg(zzjc);
        }
        this.zzaco = UUID.randomUUID().toString();
        if (zzjn.zzarc || zzjn.zzare) {
            this.zzacs = null;
        } else {
            this.zzacs = new zzbx(context, str, zzang.zzcw, this, this);
            this.zzacs.setMinimumWidth(zzjn.widthPixels);
            this.zzacs.setMinimumHeight(zzjn.heightPixels);
            this.zzacs.setVisibility(4);
        }
        this.zzacv = zzjn;
        this.zzacp = str;
        this.zzrt = context;
        this.zzacr = zzang;
        this.zzacq = new zzci(new zzag(this));
        this.zzadz = new zzamj(200);
        this.zzadi = new cg();
    }

    private final void zzf(boolean z) {
        if (this.zzacs != null && this.zzacw != null && this.zzacw.zzbyo != null && this.zzacw.zzbyo.zzuf() != null && (!z || this.zzadz.tryAcquire())) {
            if (this.zzacw.zzbyo.zzuf().zzfz()) {
                int[] iArr = new int[2];
                this.zzacs.getLocationOnScreen(iArr);
                zzkb.zzif();
                int zzb = zzamu.zzb(this.zzrt, iArr[0]);
                zzkb.zzif();
                int zzb2 = zzamu.zzb(this.zzrt, iArr[1]);
                if (!(zzb == this.zzadx && zzb2 == this.zzady)) {
                    this.zzadx = zzb;
                    this.zzady = zzb2;
                    this.zzacw.zzbyo.zzuf().zza(this.zzadx, this.zzady, z ^ true);
                }
            }
            if (this.zzacs != null) {
                View findViewById = this.zzacs.getRootView().findViewById(16908290);
                if (findViewById != null) {
                    Rect rect = new Rect();
                    Rect rect2 = new Rect();
                    this.zzacs.getGlobalVisibleRect(rect);
                    findViewById.getGlobalVisibleRect(rect2);
                    if (rect.top != rect2.top) {
                        this.zzaea = false;
                    }
                    if (rect.bottom != rect2.bottom) {
                        this.zzaeb = false;
                    }
                }
            }
        }
    }

    public final void onGlobalLayout() {
        zzf(false);
    }

    public final void onScrollChanged() {
        zzf(true);
        this.zzaec = true;
    }

    public final void zza(HashSet<zzajj> hashSet) {
        this.zzadw = hashSet;
    }

    public final HashSet<zzajj> zzfl() {
        return this.zzadw;
    }

    public final void zzfm() {
        if (this.zzacw != null && this.zzacw.zzbyo != null) {
            this.zzacw.zzbyo.destroy();
        }
    }

    public final void zzfn() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.ads.internal.zzbw.zzfn():void. bs: []
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
        r1 = this;
        r0 = r1.zzacw;
        if (r0 == 0) goto L_0x0017;
    L_0x0004:
        r0 = r1.zzacw;
        r0 = r0.zzbtx;
        if (r0 == 0) goto L_0x0017;
    L_0x000a:
        r0 = r1.zzacw;	 Catch:{ RemoteException -> 0x0012 }
        r0 = r0.zzbtx;	 Catch:{ RemoteException -> 0x0012 }
        r0.destroy();	 Catch:{ RemoteException -> 0x0012 }
        return;
    L_0x0012:
        r0 = "Could not destroy mediation adapter.";
        com.google.android.gms.internal.ads.zzane.zzdk(r0);
    L_0x0017:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzbw.zzfn():void");
    }

    public final boolean zzfo() {
        return this.zzadv == 0;
    }

    public final boolean zzfp() {
        return this.zzadv == 1;
    }

    public final String zzfq() {
        return (this.zzaea && this.zzaeb) ? "" : this.zzaea ? this.zzaec ? "top-scrollable" : "top-locked" : this.zzaeb ? this.zzaec ? "bottom-scrollable" : "bottom-locked" : "";
    }

    public final void zzg(boolean z) {
        if (!(this.zzadv != 0 || this.zzacw == null || this.zzacw.zzbyo == null)) {
            this.zzacw.zzbyo.stopLoading();
        }
        if (this.zzact != null) {
            this.zzact.cancel();
        }
        if (this.zzacu != null) {
            this.zzacu.cancel();
        }
        if (z) {
            this.zzacw = null;
        }
    }

    final void zzj(View view) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbat)).booleanValue()) {
            zzce zzaa = this.zzacq.zzaa();
            if (zzaa != null) {
                zzaa.zzb(view);
            }
        }
    }
}
