package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.aab;
import defpackage.ajq;
import defpackage.ajr;
import defpackage.ajs;
import defpackage.ajt;
import defpackage.aju;
import defpackage.ajw;
import defpackage.ajx;
import defpackage.ajz;
import defpackage.akb;
import defpackage.akd;
import defpackage.akh;
import defpackage.akm;
import defpackage.akn;
import defpackage.alf;
import defpackage.alt;
import defpackage.alu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class v<TranscodeType> implements Cloneable {
    protected static final ajx a = new ajx().b(aab.c).a(m.LOW).b(true);
    protected ajx b;
    private final Context c;
    private final w d;
    private final Class<TranscodeType> e;
    private final ajx f;
    private final d g;
    private final f h;
    private z<?, ? super TranscodeType> i;
    private Object j;
    private List<ajw<TranscodeType>> k;
    private v<TranscodeType> l;
    private v<TranscodeType> m;
    private Float n;
    private boolean o = true;
    private boolean p;
    private boolean q;

    /* renamed from: com.bumptech.glide.v$2 */
    final /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.bumptech.glide.v.2.<clinit>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
            r0 = com.bumptech.glide.m.values();
            r0 = r0.length;
            r0 = new int[r0];
            b = r0;
            r0 = 1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.bumptech.glide.m.LOW;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.bumptech.glide.m.NORMAL;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = 3;
            r3 = b;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = com.bumptech.glide.m.HIGH;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r3 = 4;
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = com.bumptech.glide.m.IMMEDIATE;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r4 = android.widget.ImageView.ScaleType.values();
            r4 = r4.length;
            r4 = new int[r4];
            a = r4;
            r4 = a;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = android.widget.ImageView.ScaleType.CENTER_CROP;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0048 }
            r4[r5] = r0;	 Catch:{ NoSuchFieldError -> 0x0048 }
        L_0x0048:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0052 }
            r4 = android.widget.ImageView.ScaleType.CENTER_INSIDE;	 Catch:{ NoSuchFieldError -> 0x0052 }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x0052 }
            r0[r4] = r1;	 Catch:{ NoSuchFieldError -> 0x0052 }
        L_0x0052:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = android.widget.ImageView.ScaleType.FIT_CENTER;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x005c }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x005c }
        L_0x005c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0066 }
            r1 = android.widget.ImageView.ScaleType.FIT_START;	 Catch:{ NoSuchFieldError -> 0x0066 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0066 }
            r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x0066 }
        L_0x0066:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0071 }
            r1 = android.widget.ImageView.ScaleType.FIT_END;	 Catch:{ NoSuchFieldError -> 0x0071 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0071 }
            r2 = 5;	 Catch:{ NoSuchFieldError -> 0x0071 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0071 }
        L_0x0071:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = android.widget.ImageView.ScaleType.FIT_XY;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007c }
            r2 = 6;	 Catch:{ NoSuchFieldError -> 0x007c }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007c }
        L_0x007c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0087 }
            r1 = android.widget.ImageView.ScaleType.CENTER;	 Catch:{ NoSuchFieldError -> 0x0087 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0087 }
            r2 = 7;	 Catch:{ NoSuchFieldError -> 0x0087 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0087 }
        L_0x0087:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0093 }
            r1 = android.widget.ImageView.ScaleType.MATRIX;	 Catch:{ NoSuchFieldError -> 0x0093 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0093 }
            r2 = 8;	 Catch:{ NoSuchFieldError -> 0x0093 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0093 }
        L_0x0093:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.v.2.<clinit>():void");
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    protected v(d dVar, w wVar, Class<TranscodeType> cls, Context context) {
        this.g = dVar;
        this.d = wVar;
        this.e = cls;
        this.f = wVar.g();
        this.c = context;
        this.i = wVar.b((Class) cls);
        this.b = this.f;
        this.h = dVar.e();
    }

    protected final ajx a() {
        return this.f == this.b ? this.b.d() : this.b;
    }

    public v<TranscodeType> a(ajw<TranscodeType> ajw) {
        this.k = null;
        return b((ajw) ajw);
    }

    public v<TranscodeType> b(ajw<TranscodeType> ajw) {
        if (ajw != null) {
            if (this.k == null) {
                this.k = new ArrayList();
            }
            this.k.add(ajw);
        }
        return this;
    }

    public v<TranscodeType> a(v<TranscodeType> vVar) {
        this.m = vVar;
        return this;
    }

    public v<TranscodeType> b(v<TranscodeType> vVar) {
        this.l = vVar;
        return this;
    }

    public v<TranscodeType> a(float f) {
        if (f < BitmapDescriptorFactory.HUE_RED || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.n = Float.valueOf(f);
        return this;
    }

    public v<TranscodeType> a(Object obj) {
        return b(obj);
    }

    private v<TranscodeType> b(Object obj) {
        this.j = obj;
        this.p = true;
        return this;
    }

    public v<TranscodeType> a(Bitmap bitmap) {
        return b((Object) bitmap).a(ajx.a(aab.b));
    }

    public v<TranscodeType> a(String str) {
        return b((Object) str);
    }

    public v<TranscodeType> a(Uri uri) {
        return b((Object) uri);
    }

    public v<TranscodeType> a(File file) {
        return b((Object) file);
    }

    public v<TranscodeType> a(Integer num) {
        return b((Object) num).a(ajx.a(alf.a(this.c)));
    }

    public v<TranscodeType> a(byte[] bArr) {
        v<TranscodeType> b = b((Object) bArr);
        if (!b.b.r()) {
            b = b.a(ajx.a(aab.b));
        }
        return !b.b.s() ? b.a(ajx.a()) : b;
    }

    public v<TranscodeType> b() {
        try {
            v<TranscodeType> vVar = (v) super.clone();
            vVar.b = vVar.b.d();
            vVar.i = vVar.i.b();
            return vVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final <Y extends akm<TranscodeType>> Y a(Y y) {
        return a((akm) y, null);
    }

    final <Y extends akm<TranscodeType>> Y a(Y y, ajw<TranscodeType> ajw) {
        return a(y, ajw, a());
    }

    private <Y extends akm<TranscodeType>> Y a(Y y, ajw<TranscodeType> ajw, ajx ajx) {
        alu.a();
        alt.a((Object) y, "Argument must not be null");
        if (this.p) {
            ajx = ajx.q();
            ajs b = b(y, ajw, ajx);
            Object d = y.d();
            if (!b.a(d) || a(ajx, (ajs) d)) {
                this.d.a((akm) y);
                y.a(b);
                this.d.a(y, b);
                return y;
            }
            b.h();
            if (!((ajs) alt.a(d, "Argument must not be null")).c()) {
                d.a();
            }
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    private static boolean a(ajx ajx, ajs ajs) {
        return !ajx.F() && ajs.d();
    }

    public final akn<ImageView, TranscodeType> a(ImageView imageView) {
        akm akd;
        alu.a();
        alt.a((Object) imageView, "Argument must not be null");
        ajx ajx = this.b;
        if (!(ajx.f() || !ajx.e() || imageView.getScaleType() == null)) {
            switch (AnonymousClass2.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    ajx = ajx.d().g();
                    break;
                case 2:
                    ajx = ajx.d().k();
                    break;
                case 3:
                case 4:
                case 5:
                    ajx = ajx.d().i();
                    break;
                case 6:
                    ajx = ajx.d().k();
                    break;
            }
        }
        Class cls = this.e;
        if (Bitmap.class.equals(cls)) {
            akd = new akd(imageView);
        } else if (Drawable.class.isAssignableFrom(cls)) {
            akd = new akh(imageView);
        } else {
            StringBuilder stringBuilder = new StringBuilder("Unhandled class: ");
            stringBuilder.append(cls);
            stringBuilder.append(", try .as*(Class).transcode(ResourceTranscoder)");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        return (akn) a(akd, null, ajx);
    }

    @Deprecated
    public final ajr<TranscodeType> c() {
        return e();
    }

    public final ajr<TranscodeType> d() {
        return e();
    }

    public final ajr<TranscodeType> e() {
        final akm aju = new aju(this.h.b());
        if (alu.d()) {
            this.h.b().post(new Runnable(this) {
                final /* synthetic */ v b;

                public final void run() {
                    if (!aju.isCancelled()) {
                        this.b.a(aju, aju);
                    }
                }
            });
        } else {
            a(aju, (ajw) aju);
        }
        return aju;
    }

    private m a(m mVar) {
        switch (mVar) {
            case LOW:
                return m.NORMAL;
            case NORMAL:
                return m.HIGH;
            case HIGH:
            case IMMEDIATE:
                return m.IMMEDIATE;
            default:
                StringBuilder stringBuilder = new StringBuilder("unknown priority: ");
                stringBuilder.append(this.b.I());
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private ajs b(akm<TranscodeType> akm, ajw<TranscodeType> ajw, ajx ajx) {
        return a((akm) akm, (ajw) ajw, null, this.i, ajx.I(), ajx.J(), ajx.L(), ajx);
    }

    private ajs a(akm<TranscodeType> akm, ajw<TranscodeType> ajw, ajt ajt, z<?, ? super TranscodeType> zVar, m mVar, int i, int i2, ajx ajx) {
        ajt ajq;
        ajt ajt2;
        if (this.m != null) {
            ajq = new ajq(ajt);
            ajt2 = ajq;
        } else {
            ajt2 = null;
            ajq = ajt;
        }
        ajs b = b(akm, ajw, ajq, zVar, mVar, i, i2, ajx);
        if (ajt2 == null) {
            return b;
        }
        int J = r9.m.b.J();
        int L = r9.m.b.L();
        if (alu.a(i, i2) && !r9.m.b.K()) {
            J = ajx.J();
            L = ajx.L();
        }
        int i3 = J;
        int i4 = L;
        ajq = ajt2;
        ajq.a(b, r9.m.a((akm) akm, (ajw) ajw, ajt2, r9.m.i, r9.m.b.I(), i3, i4, r9.m.b));
        return ajq;
    }

    private ajs b(akm<TranscodeType> akm, ajw<TranscodeType> ajw, ajt ajt, z<?, ? super TranscodeType> zVar, m mVar, int i, int i2, ajx ajx) {
        ajt ajt2 = ajt;
        m mVar2 = mVar;
        if (this.l != null) {
            if (r9.q) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            z zVar2 = r9.l.o ? zVar : r9.l.i;
            m I = r9.l.b.H() ? r9.l.b.I() : a(mVar2);
            int J = r9.l.b.J();
            int L = r9.l.b.L();
            if (alu.a(i, i2) && !r9.l.b.K()) {
                J = ajx.J();
                L = ajx.L();
            }
            int i3 = J;
            int i4 = L;
            ajt akb = new akb(ajt2);
            ajs a = a((akm) akm, (ajw) ajw, ajx, akb, (z) zVar, mVar, i, i2);
            r9.q = true;
            ajt ajt3 = akb;
            ajs a2 = r9.l.a((akm) akm, (ajw) ajw, akb, zVar2, I, i3, i4, r9.l.b);
            r9.q = false;
            ajt3.a(a, a2);
            return ajt3;
        } else if (r9.n == null) {
            return a((akm) akm, (ajw) ajw, ajx, ajt, (z) zVar, mVar, i, i2);
        } else {
            ajs akb2 = new akb(ajt2);
            ajw ajw2 = ajw;
            ajt2 = akb2;
            z zVar3 = zVar;
            int i5 = i;
            int i6 = i2;
            akb2.a(a((akm) akm, ajw2, ajx, ajt2, zVar3, mVar, i5, i6), a((akm) akm, ajw2, ajx.d().a(r9.n.floatValue()), ajt2, zVar3, a(mVar2), i5, i6));
            return akb2;
        }
    }

    private ajs a(akm<TranscodeType> akm, ajw<TranscodeType> ajw, ajx ajx, ajt ajt, z<?, ? super TranscodeType> zVar, m mVar, int i, int i2) {
        return ajz.a(this.c, this.h, this.j, this.e, ajx, i, i2, mVar, akm, ajw, this.k, ajt, this.h.c(), zVar.c());
    }

    public v<TranscodeType> a(ajx ajx) {
        alt.a((Object) ajx, "Argument must not be null");
        this.b = a().a(ajx);
        return this;
    }

    public v<TranscodeType> a(z<?, ? super TranscodeType> zVar) {
        this.i = (z) alt.a((Object) zVar, "Argument must not be null");
        this.o = false;
        return this;
    }
}
