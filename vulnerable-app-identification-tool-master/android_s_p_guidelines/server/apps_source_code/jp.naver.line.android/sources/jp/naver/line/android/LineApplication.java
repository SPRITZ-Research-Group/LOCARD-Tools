package jp.naver.line.android;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.linecorp.square.SquareContext;
import defpackage.acqq;
import defpackage.acry;
import defpackage.acrz;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import defpackage.dnq;
import defpackage.dns;
import defpackage.dnz;
import defpackage.dxm;
import defpackage.eak;
import defpackage.eam;
import defpackage.eoe;
import defpackage.qnj;
import defpackage.rtk;
import defpackage.rua;
import defpackage.ryl;
import defpackage.uha;
import defpackage.uhi;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.naver.line.android.obs.service.OBSDownloadRequest;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.bp;
import kotlin.Metadata;
import kotlin.e;
import kotlin.v;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 l2\u00020\u00012\u00020\u0002:\u0002lmB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020HH\u0014J\u000e\u0010N\u001a\u00020!2\u0006\u0010O\u001a\u000204J\u0006\u0010P\u001a\u00020\u000bJ\u000e\u0010Q\u001a\u00020!2\u0006\u0010R\u001a\u000204J\u0006\u0010S\u001a\u00020LJ\u0016\u0010T\u001a\u00020L2\u0006\u0010R\u001a\u0002042\u0006\u0010U\u001a\u00020=J\u001e\u0010V\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010<2\u0006\u0010R\u001a\u0002042\u0006\u0010U\u001a\u00020=J\u0010\u0010W\u001a\u00020L2\u0006\u0010X\u001a\u00020YH\u0016J\b\u0010Z\u001a\u00020LH\u0016J\u0006\u0010[\u001a\u00020LJ\u0010\u0010\\\u001a\u00020L2\b\u0010]\u001a\u0004\u0018\u00010^J\u0018\u0010_\u001a\u00020L2\b\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010`\u001a\u00020aJ\u0006\u0010b\u001a\u00020LJ\u0011\u0010c\u001a\u00020L2\u0006\u0010d\u001a\u00020\u0000H\u0001J\u0006\u0010e\u001a\u00020LJ\t\u0010f\u001a\u00020LH\u0001J\u0015\u0010g\u001a\u00020L\"\n\b\u0000\u0010h\u0018\u0001*\u00020iH\bJ \u0010g\u001a\u00020L\"\b\b\u0000\u0010h*\u00020i2\f\u0010j\u001a\b\u0012\u0004\u0012\u0002Hh0kH\u0007R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8FX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0011\u001a\u00020\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b \u0010\"R\u001a\u0010#\u001a\u00020!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\"\"\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b&\u0010\"R\u0011\u0010'\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b'\u0010\"R\u0011\u0010(\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b(\u0010\"R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\"\"\u0004\b,\u0010%R\u001b\u0010-\u001a\u00020.8FX\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b/\u00100R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020403X\u0004¢\u0006\u0002\n\u0000R\u001b\u00105\u001a\u0002068FX\u0002¢\u0006\f\n\u0004\b9\u0010\t\u001a\u0004\b7\u00108R \u0010:\u001a\u0014\u0012\u0004\u0012\u000204\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<0;X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020!X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020AX.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0018\u0010F\u001a\u00020G*\u00020H8BX\u0004¢\u0006\u0006\u001a\u0004\bI\u0010J¨\u0006n"}, d2 = {"Ljp/naver/line/android/LineApplication;", "Landroid/app/Application;", "Ljp/naver/line/android/initialization/LineInitializationManagement;", "()V", "activityLifecycleEventManager", "Lcom/linecorp/line/application/ActivityLifecycleEventManager;", "getActivityLifecycleEventManager", "()Lcom/linecorp/line/application/ActivityLifecycleEventManager;", "activityLifecycleEventManager$delegate", "Lkotlin/Lazy;", "adContextHolder", "Lcom/linecorp/line/ad/handler/AdContext;", "commonLineAccess", "Ljp/naver/line/android/access/common/LineAccessForCommon;", "getCommonLineAccess", "()Ljp/naver/line/android/access/common/LineAccessForCommon;", "commonLineAccess$delegate", "createTime", "", "getCreateTime", "()J", "dataManagerHolder", "Ljp/naver/line/android/DataManagerHolder;", "getDataManagerHolder", "()Ljp/naver/line/android/DataManagerHolder;", "dataManagerHolderBackingField", "dataManagerHolderLock", "", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "getEventBus", "()Lcom/linecorp/rxeventbus/EventBus;", "isForeground", "", "()Z", "isIgnoreTalkExceptionTemporarily", "setIgnoreTalkExceptionTemporarily", "(Z)V", "isScreenLocked", "isScreenOn", "isSquareContextInitialized", "isSuppressedNotifiedRedirect", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isThemeValidationFailed", "setThemeValidationFailed", "lineApplicationEnvironment", "Ljp/naver/line/android/LineApplicationEnvironment;", "getLineApplicationEnvironment", "()Ljp/naver/line/android/LineApplicationEnvironment;", "lineApplicationEnvironment$delegate", "notifiedRedirectUrlSet", "Ljava/util/LinkedHashSet;", "", "obsBroadcastReceiver", "Ljp/naver/line/android/OBSApplicationBroadcastReceiver;", "getObsBroadcastReceiver", "()Ljp/naver/line/android/OBSApplicationBroadcastReceiver;", "obsBroadcastReceiver$delegate", "showingChatScreenMap", "", "", "", "showingChatScreenMapLock", "splashed", "squareContext", "Lcom/linecorp/square/SquareContext;", "getSquareContext", "()Lcom/linecorp/square/SquareContext;", "setSquareContext", "(Lcom/linecorp/square/SquareContext;)V", "keyguardManager", "Landroid/app/KeyguardManager;", "Landroid/content/Context;", "getKeyguardManager", "(Landroid/content/Context;)Landroid/app/KeyguardManager;", "attachBaseContext", "", "base", "checkProcessOrSuppressNotifiedRedirect", "redirectUrl", "getAdContext", "isChatRoomScreenShowing", "chatId", "logAppLaunchTime", "onChatRoomScreenHidden", "screenKey", "onChatRoomScreenShown", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "processSuppressedNotifiedRedirect", "requestCancelDownload", "request", "Ljp/naver/line/android/obs/service/OBSDownloadRequest;", "requestDownloadImage", "downloadListener", "Ljp/naver/line/android/OBSApplicationBroadcastReceiver$DownloadListener;", "setSplashed", "startInitializationTask", "application", "suppressNotifiedRedirect", "waitForInitializationToComplete", "waitForTaskToComplete", "T", "Ljp/naver/line/android/initialization/LineInitializationTask;", "cls", "Ljava/lang/Class;", "Companion", "LineApplicationKeeper", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class LineApplication extends Application {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(LineApplication.class), "lineApplicationEnvironment", "getLineApplicationEnvironment()Ljp/naver/line/android/LineApplicationEnvironment;")), acso.a(new acsi(acso.a(LineApplication.class), "commonLineAccess", "getCommonLineAccess()Ljp/naver/line/android/access/common/LineAccessForCommon;")), acso.a(new acsi(acso.a(LineApplication.class), "obsBroadcastReceiver", "getObsBroadcastReceiver()Ljp/naver/line/android/OBSApplicationBroadcastReceiver;")), acso.a(new acsi(acso.a(LineApplication.class), "activityLifecycleEventManager", "getActivityLifecycleEventManager()Lcom/linecorp/line/application/ActivityLifecycleEventManager;"))};
    public static final k c = new k();
    private static LineApplication s;
    private static final uha t = new uha();
    public SquareContext b;
    private final com.linecorp.rxeventbus.a d = new com.linecorp.rxeventbus.a(at.b());
    private final e e = h.a(new c(this));
    private final e f = h.a(new b(this));
    private final e g = bp.a(d.a);
    private final e h = bp.a(new a(this));
    private final AtomicBoolean i = new AtomicBoolean(false);
    private final LinkedHashSet<String> j = new LinkedHashSet();
    private volatile f k;
    private final Object l = new Object();
    private volatile boolean m;
    private final Map<String, Set<Integer>> n = new LinkedHashMap();
    private final Object o = new Object();
    private volatile boolean p;
    private boolean q;
    private dxm r;
    private final /* synthetic */ uha u = t;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/line/application/ActivityLifecycleEventManager;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrz implements acqq<eoe> {
        final /* synthetic */ LineApplication a;

        a(LineApplication lineApplication) {
            this.a = lineApplication;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new eoe(this.a, this.a.a());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/access/common/LineAccessForCommon;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrz implements acqq<qnj> {
        final /* synthetic */ LineApplication a;

        b(LineApplication lineApplication) {
            this.a = lineApplication;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new qnj(this.a);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/LineApplicationEnvironment;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrz implements acqq<m> {
        final /* synthetic */ LineApplication a;

        c(LineApplication lineApplication) {
            this.a = lineApplication;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new m(this.a);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/OBSApplicationBroadcastReceiver;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class d extends acrz implements acqq<OBSApplicationBroadcastReceiver> {
        public static final d a = new d();

        d() {
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return new OBSApplicationBroadcastReceiver();
        }
    }

    public static final LineApplication a(Context context) {
        return k.a(context);
    }

    public static final String w() {
        return BuildConfig.VERSION_NAME;
    }

    public static final int x() {
        return BuildConfig.VERSION_CODE;
    }

    public final m b() {
        return (m) this.e.d();
    }

    public final qnj c() {
        return (qnj) this.f.d();
    }

    public final OBSApplicationBroadcastReceiver d() {
        return (OBSApplicationBroadcastReceiver) this.g.d();
    }

    public final eoe e() {
        return (eoe) this.h.d();
    }

    public final long s() {
        return this.u.a();
    }

    public final void t() {
        this.u.b();
    }

    public final com.linecorp.rxeventbus.a a() {
        return this.d;
    }

    public final f f() {
        f fVar = this.k;
        if (fVar == null) {
            f fVar2;
            synchronized (this.l) {
                fVar2 = this.k;
                if (fVar2 == null) {
                    fVar2 = new f(this, this.d);
                    this.k = fVar2;
                }
                y yVar = y.a;
            }
            fVar = fVar2;
        }
        if (fVar != null) {
            return fVar;
        }
        throw new v("null cannot be cast to non-null type jp.naver.line.android.DataManagerHolder");
    }

    public final SquareContext g() {
        SquareContext squareContext = this.b;
        if (squareContext == null) {
            acry.a("squareContext");
        }
        return squareContext;
    }

    public final boolean h() {
        return ((LineApplication) this).b != null;
    }

    public final void a(boolean z) {
        this.m = z;
    }

    public final boolean i() {
        return this.m;
    }

    public final void b(boolean z) {
        this.p = z;
    }

    public final boolean j() {
        return this.p;
    }

    public final boolean k() {
        return e().a();
    }

    public final boolean m() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.LineApplication.m():boolean. bs: []
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
        r2 = this;
        r0 = "power";	 Catch:{ Exception -> 0x0017 }
        r0 = r2.getSystemService(r0);	 Catch:{ Exception -> 0x0017 }
        if (r0 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x0017 }
    L_0x0008:
        r0 = (android.os.PowerManager) r0;	 Catch:{ Exception -> 0x0017 }
        r0 = r0.isScreenOn();	 Catch:{ Exception -> 0x0017 }
        goto L_0x0018;	 Catch:{ Exception -> 0x0017 }
    L_0x000f:
        r0 = new kotlin.v;	 Catch:{ Exception -> 0x0017 }
        r1 = "null cannot be cast to non-null type android.os.PowerManager";	 Catch:{ Exception -> 0x0017 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0017 }
        throw r0;	 Catch:{ Exception -> 0x0017 }
    L_0x0017:
        r0 = 0;
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.LineApplication.m():boolean");
    }

    protected final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        androidx.multidex.a.a((Context) this);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ryl.b().a((Object) this);
    }

    public final void onCreate() {
        super.onCreate();
        s = this;
        this.u.a(this);
    }

    public final Set<Integer> a(String str, int i) {
        Set<Integer> set;
        synchronized (this.o) {
            Object obj = (Set) this.n.get(str);
            if (obj == null) {
                obj = new LinkedHashSet();
            }
            ((Collection) obj).add(Integer.valueOf(i));
            set = (Set) this.n.put(str, obj);
        }
        return set;
    }

    public final void b(String str, int i) {
        synchronized (this.o) {
            Set set = (Set) this.n.get(str);
            if (set != null) {
                set.remove(Integer.valueOf(i));
            }
            if (set != null && set.size() == 0) {
                this.n.remove(str);
            }
            y yVar = y.a;
        }
    }

    public final boolean a(String str) {
        boolean containsKey;
        synchronized (this.o) {
            containsKey = this.n.containsKey(str);
        }
        return containsKey;
    }

    public final boolean b(String str) {
        boolean z = this.i.get();
        if (z) {
            if ((((CharSequence) str).length() > 0 ? 1 : null) != null) {
                synchronized (this.j) {
                    this.j.add(str);
                }
            }
        }
        return !z;
    }

    public final void n() {
        this.i.set(true);
    }

    public final void o() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.LineApplication.o():void. bs: []
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
        r4 = this;
        r0 = r4.i;
        r1 = 0;
        r0 = r0.getAndSet(r1);
        if (r0 == 0) goto L_0x003f;
    L_0x0009:
        r0 = r4.j;
        monitor-enter(r0);
        r1 = new java.util.LinkedHashSet;	 Catch:{ all -> 0x003c }
        r2 = r4.j;	 Catch:{ all -> 0x003c }
        r2 = (java.util.Collection) r2;	 Catch:{ all -> 0x003c }
        r1.<init>(r2);	 Catch:{ all -> 0x003c }
        r2 = r4.j;	 Catch:{ all -> 0x003c }
        r2.clear();	 Catch:{ all -> 0x003c }
        monitor-exit(r0);
        r1 = (java.util.Set) r1;
        r0 = r1.iterator();
    L_0x0021:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x003f;
    L_0x0027:
        r1 = r0.next();
        r1 = (java.lang.String) r1;
        r2 = jp.naver.line.android.urlscheme.e.a;	 Catch:{ Exception -> 0x003a }
        r2 = r4;	 Catch:{ Exception -> 0x003a }
        r2 = (android.content.Context) r2;	 Catch:{ Exception -> 0x003a }
        r3 = jp.naver.line.android.urlscheme.SchemeServiceReferrer.Unknown.b;	 Catch:{ Exception -> 0x003a }
        r3 = (jp.naver.line.android.urlscheme.SchemeServiceReferrer) r3;	 Catch:{ Exception -> 0x003a }
        jp.naver.line.android.urlscheme.e.a(r2, r1, r3);	 Catch:{ Exception -> 0x003a }
        goto L_0x0021;
        goto L_0x0021;
    L_0x003c:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
    L_0x003f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.LineApplication.o():void");
    }

    public final void p() {
        this.q = true;
    }

    public final void q() {
        if (this.q || this.u.a() > 0) {
            rtk.a(rua.Api_Event_Performance_Launch, System.currentTimeMillis() - this.u.a());
            this.q = false;
        }
    }

    public final void a(OBSDownloadRequest oBSDownloadRequest, r rVar) {
        if (oBSDownloadRequest != null) {
            d().a(oBSDownloadRequest, rVar);
            jp.naver.line.android.obs.service.a.a().a(oBSDownloadRequest);
        }
    }

    public static void a(OBSDownloadRequest oBSDownloadRequest) {
        if (oBSDownloadRequest != null) {
            jp.naver.line.android.obs.service.a.a().b(oBSDownloadRequest);
        }
    }

    public final dxm r() {
        dxm dxm;
        if (this.r != null) {
            dxm = this.r;
            if (dxm == null) {
                acry.a("adContextHolder");
                return dxm;
            }
        }
        dnq dnq = dnq.a;
        Application application = this;
        this.r = dnq.a(application);
        dxm dxm2 = this.r;
        if (dxm2 == null) {
            acry.a("adContextHolder");
        }
        eak eak = eak.a;
        dns dns = dns.a;
        eam a = dns.a(application);
        dxm dxm3 = this.r;
        if (dxm3 == null) {
            acry.a("adContextHolder");
        }
        dxm2.a(eak.a(a, dxm3.d()));
        dxm2 = this.r;
        if (dxm2 == null) {
            acry.a("adContextHolder");
        }
        eak = eak.a;
        dnz dnz = dnz.a;
        eam a2 = dnz.a(application);
        dxm dxm4 = this.r;
        if (dxm4 == null) {
            acry.a("adContextHolder");
        }
        dxm2.a(eak.a(a2, dxm4.d()));
        dxm = this.r;
        if (dxm == null) {
            acry.a("adContextHolder");
        }
        return dxm;
    }

    public static <T extends uhi> void a(Class<T> cls) {
        t.a(cls.getSimpleName());
    }

    public final boolean l() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.LineApplication.l():boolean. bs: []
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
        r2 = this;
        r0 = "keyguard";	 Catch:{ Exception -> 0x0017 }
        r0 = r2.getSystemService(r0);	 Catch:{ Exception -> 0x0017 }
        if (r0 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x0017 }
    L_0x0008:
        r0 = (android.app.KeyguardManager) r0;	 Catch:{ Exception -> 0x0017 }
        r0 = r0.inKeyguardRestrictedInputMode();	 Catch:{ Exception -> 0x0017 }
        goto L_0x0018;	 Catch:{ Exception -> 0x0017 }
    L_0x000f:
        r0 = new kotlin.v;	 Catch:{ Exception -> 0x0017 }
        r1 = "null cannot be cast to non-null type android.app.KeyguardManager";	 Catch:{ Exception -> 0x0017 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0017 }
        throw r0;	 Catch:{ Exception -> 0x0017 }
    L_0x0017:
        r0 = 0;
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.LineApplication.l():boolean");
    }
}
