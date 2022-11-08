package com.linecorp.shop.sticker.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acnr;
import defpackage.acns;
import defpackage.acob;
import defpackage.acqq;
import defpackage.acqr;
import defpackage.acrc;
import defpackage.acrx;
import defpackage.acrz;
import defpackage.acso;
import defpackage.acuc;
import defpackage.cmi;
import defpackage.cmp;
import defpackage.cpm;
import defpackage.lil;
import defpackage.lwc;
import defpackage.lxr;
import defpackage.lzd;
import defpackage.lzp;
import defpackage.lzq;
import defpackage.lzr;
import defpackage.maw;
import defpackage.mbm;
import defpackage.mbn;
import defpackage.mbo;
import defpackage.ozh;
import defpackage.paj;
import defpackage.pak;
import defpackage.ubp;
import defpackage.uwu;
import defpackage.uwx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jp.naver.line.android.db.main.model.ao;
import jp.naver.line.android.model.bp;
import jp.naver.line.android.util.ca;
import kotlin.Metadata;
import kotlin.k;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0006\u0010&\u001a\u00020#J\u000e\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020)J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020%0+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0+H\u0002J\f\u0010.\u001a\u00060\u001cj\u0002`\u001dH\u0002J\u0006\u0010/\u001a\u00020#J\u0018\u00100\u001a\u00020#2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0016\u00105\u001a\u00020#2\f\u00106\u001a\b\u0012\u0004\u0012\u00020)0+H\u0002J\b\u00107\u001a\u00020\u0018H\u0002J\u0006\u00108\u001a\u00020#J\u0006\u00109\u001a\u00020#J\u0010\u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020<H\u0007J\b\u0010=\u001a\u00020#H\u0002J\u0010\u0010>\u001a\u00020#2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010?\u001a\u00020#H\u0002J\b\u0010@\u001a\u00020#H\u0002J\b\u0010A\u001a\u00020#H\u0002J\u0016\u0010B\u001a\u00020#2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020%0+H\u0002J\b\u0010C\u001a\u00020#H\u0002J\f\u0010D\u001a\u00020\u0018*\u00020%H\u0002R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001fR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006E"}, d2 = {"Lcom/linecorp/shop/sticker/ui/fragment/MyStickerListPresenter;", "", "context", "Landroid/content/Context;", "view", "Lcom/linecorp/shop/sticker/ui/fragment/MyStickerListScreenView;", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "dataManager", "Lcom/linecorp/shop/sticker/StickerDataManager;", "adapter", "Lcom/linecorp/shop/sticker/ui/fragment/adapter/MyStickerListAdapter;", "packageDownloadQueue", "Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue;", "(Landroid/content/Context;Lcom/linecorp/shop/sticker/ui/fragment/MyStickerListScreenView;Lcom/linecorp/rxeventbus/EventBus;Lcom/linecorp/shop/sticker/StickerDataManager;Lcom/linecorp/shop/sticker/ui/fragment/adapter/MyStickerListAdapter;Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue;)V", "getAdapter", "()Lcom/linecorp/shop/sticker/ui/fragment/adapter/MyStickerListAdapter;", "getContext", "()Landroid/content/Context;", "disposableSet", "Lcom/linecorp/rxjava/DisposableSet;", "getEventBus", "()Lcom/linecorp/rxeventbus/EventBus;", "isFinishing", "", "isLoading", "isSyncing", "packageDownloadListener", "Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue$StickerPackageZipDownloadListener;", "Lcom/linecorp/shop/sticker/ui/fragment/DeprecatedStickerZipDownloadListener;", "packageDownloadListener$annotations", "()V", "getView", "()Lcom/linecorp/shop/sticker/ui/fragment/MyStickerListScreenView;", "addDownloadListener", "", "stickerPackage", "Lcom/linecorp/shop/sticker/ui/fragment/model/StickerPackageListRowViewData;", "cancelAllDownloading", "cancelDownloadStickerPackage", "packageId", "", "convertToViewData", "", "stickerList", "Ljp/naver/line/android/db/main/model/StickerPackageData;", "createStickerPackageDownloadListener", "downloadAllStickerPackages", "downloadStickerPackage", "resourceData", "Ljp/naver/line/android/stickershop/model/DeprecatedStickerResourceData;", "stickerOptionType", "Ljp/naver/line/android/stickershop/model/StickerOptionType;", "loadMyStickerPackages", "ownedStickerServerOrder", "noItemLoaded", "onCreate", "onDestroy", "onStickerPackageSynced", "event", "Lcom/linecorp/shop/sticker/event/StickerPackageSyncedEvent;", "registerDownloadListener", "reloadPackageData", "requestSyncMyStickerPackages", "unregisterDownloadListener", "updateDownloadAllButton", "updateStickerList", "updateUi", "isDownloadable", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class h {
    private final uwx a;
    private final lil b;
    private boolean c;
    private boolean d;
    private boolean e;
    private final Context f;
    private final l g;
    private final com.linecorp.rxeventbus.a h;
    private final lzd i;
    private final maw j;
    private final uwu k;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000=\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001j\u0002`\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J(\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\rH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\u0011\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013H\u0002J \u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"com/linecorp/shop/sticker/ui/fragment/MyStickerListPresenter$createStickerPackageDownloadListener$1", "Ljp/naver/line/android/stickershop/service/StickerPackageZipDownloadQueue$StickerPackageZipDownloadListener;", "Lcom/linecorp/shop/sticker/ui/fragment/DeprecatedStickerZipDownloadListener;", "uiThreadHandler", "Landroid/os/Handler;", "onCanceled", "", "packageId", "", "packageVersion", "onFailed", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSizeReceived", "contentSize", "onSuccess", "runInUiThread", "runnable", "Lkotlin/Function0;", "updateProgress", "progress", "", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class a implements uwx {
        final /* synthetic */ h a;
        private final Handler b = new Handler(Looper.getMainLooper());

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
        final class a extends acrz implements acqq<y> {
            final /* synthetic */ a a;
            final /* synthetic */ long b;

            a(a aVar, long j) {
                this.a = aVar;
                this.b = j;
                super(0);
            }

            public final /* synthetic */ Object invoke() {
                this.a.a.e().a(this.b, ubp.NEED_DOWNLOAD);
                this.a.a.f();
                return y.a;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
        final class b extends acrz implements acqq<y> {
            final /* synthetic */ a a;
            final /* synthetic */ long b;
            final /* synthetic */ long c;

            b(a aVar, long j, long j2) {
                this.a = aVar;
                this.b = j;
                this.c = j2;
                super(0);
            }

            public final /* synthetic */ Object invoke() {
                this.a.a.e().a(this.b, this.c);
                return y.a;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
        final class c extends acrz implements acqq<y> {
            final /* synthetic */ a a;

            c(a aVar) {
                this.a = aVar;
                super(0);
            }

            public final /* synthetic */ Object invoke() {
                this.a.a.f();
                return y.a;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
        final class d extends acrz implements acqq<y> {
            final /* synthetic */ a a;
            final /* synthetic */ long b;
            final /* synthetic */ int c;

            d(a aVar, long j, int i) {
                this.a = aVar;
                this.b = j;
                this.c = i;
                super(0);
            }

            public final /* synthetic */ Object invoke() {
                this.a.a.e().a(this.b, this.c);
                return y.a;
            }
        }

        a(h hVar) {
            this.a = hVar;
        }

        public final void a(long j) {
            this.a.i.b().a((cmp) ca.a((cpm) new d(this.a))).a(Long.valueOf(j));
            a((acqq) new c(this));
        }

        public final void b(long j) {
            this.a.i.b().a((cmp) ca.a((cpm) new d(this.a))).a(Long.valueOf(j));
        }

        public final void c(long j) {
            a((acqq) new a(this, j));
        }

        public final void a(long j, long j2) {
            a((acqq) new b(this, j, j2));
        }

        public final void a(long j, int i) {
            a((acqq) new d(this, j, i));
        }

        private final void a(acqq<y> acqq) {
            this.b.post(new k(acqq));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/db/main/model/StickerPackageData;", "accept"}, k = 3, mv = {1, 1, 13})
    final class d<T> implements cpm<P> {
        final /* synthetic */ h a;

        d(h hVar) {
            this.a = hVar;
        }

        public final /* synthetic */ void accept(Object obj) {
            cmi cmi = (cmi) obj;
            if (cmi.a()) {
                ao aoVar = (ao) cmi.b();
                maw e = this.a.e();
                mbn mbn = mbm.a;
                e.a(mbn.a(aoVar));
                this.a.g();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u001b\u0010\u0003\u001a\u0017\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "Lcom/linecorp/shop/sticker/ui/fragment/model/StickerPackageListRowViewData;", "p1", "Ljp/naver/line/android/db/main/model/StickerPackageData;", "Lkotlin/ParameterName;", "name", "stickerList", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrx implements acqr<List<? extends ao>, List<? extends mbm>> {
        b(h hVar) {
            super(1, hVar);
        }

        public final String getName() {
            return "convertToViewData";
        }

        public final acuc getOwner() {
            return acso.a(h.class);
        }

        public final String getSignature() {
            return "convertToViewData(Ljava/util/List;)Ljava/util/List;";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return h.a((List) obj);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "", "Lcom/linecorp/shop/sticker/ui/fragment/model/StickerPackageListRowViewData;", "Lkotlin/ParameterName;", "name", "stickerList", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrx implements acqr<List<? extends mbm>, y> {
        c(h hVar) {
            super(1, hVar);
        }

        public final String getName() {
            return "updateStickerList";
        }

        public final acuc getOwner() {
            return acso.a(h.class);
        }

        public final String getSignature() {
            return "updateStickerList(Ljava/util/List;)V";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            h.a((h) this.receiver, (List) obj);
            return y.a;
        }
    }

    private h(Context context, l lVar, com.linecorp.rxeventbus.a aVar, lzd lzd, maw maw, uwu uwu) {
        this.f = context;
        this.g = lVar;
        this.h = aVar;
        this.i = lzd;
        this.j = maw;
        this.k = uwu;
        this.a = new a(this);
        this.b = new lil();
        this.d = true;
        this.j.a((acqr) new acqr<Long, y>(this.g) {
            public final String getName() {
                return "navigateToPackageDetail";
            }

            public final acuc getOwner() {
                return acso.a(l.class);
            }

            public final String getSignature() {
                return "navigateToPackageDetail(J)V";
            }

            public final /* synthetic */ Object invoke(Object obj) {
                ((l) this.receiver).b(((Number) obj).longValue());
                return y.a;
            }
        });
        this.j.a((acrc) new acrc<jp.naver.line.android.stickershop.model.a, jp.naver.line.android.stickershop.model.c, y>(this) {
            public final String getName() {
                return "downloadStickerPackage";
            }

            public final acuc getOwner() {
                return acso.a(h.class);
            }

            public final String getSignature() {
                return "downloadStickerPackage(Ljp/naver/line/android/stickershop/model/DeprecatedStickerResourceData;Ljp/naver/line/android/stickershop/model/StickerOptionType;)V";
            }

            public final /* synthetic */ Object invoke(Object obj, Object obj2) {
                ((h) this.receiver).a((jp.naver.line.android.stickershop.model.a) obj, (jp.naver.line.android.stickershop.model.c) obj2);
                return y.a;
            }
        });
        this.j.b(new acqr<Long, y>(this.g) {
            public final String getName() {
                return "showConfirmCancelDialog";
            }

            public final acuc getOwner() {
                return acso.a(l.class);
            }

            public final String getSignature() {
                return "showConfirmCancelDialog(J)V";
            }

            public final /* synthetic */ Object invoke(Object obj) {
                ((l) this.receiver).a(((Number) obj).longValue());
                return y.a;
            }
        });
    }

    public /* synthetic */ h(Context context, l lVar, com.linecorp.rxeventbus.a aVar, lzd lzd) {
        this(context, lVar, aVar, lzd, new maw(new acqq<y>(lVar) {
            public final String getName() {
                return "navigateToStickerShop";
            }

            public final acuc getOwner() {
                return acso.a(l.class);
            }

            public final String getSignature() {
                return "navigateToStickerShop()V";
            }

            public final /* synthetic */ Object invoke() {
                ((l) this.receiver).a();
                return y.a;
            }
        }), uwu.a());
    }

    public final maw e() {
        return this.j;
    }

    public final void a() {
        this.d = false;
        this.h.b(this);
        for (mbm a : this.j.b()) {
            a(a);
        }
        this.e = true;
        g();
        this.i.i();
    }

    public final void b() {
        this.d = true;
        this.k.a(this.a);
        this.b.a();
        this.h.c(this);
    }

    public final void c() {
        Collection arrayList = new ArrayList();
        for (Object next : this.j.b()) {
            if (b((mbm) next)) {
                arrayList.add(next);
            }
        }
        for (mbm mbm : (List) arrayList) {
            a(mbm.e(), mbm.d());
        }
        g();
    }

    public final void d() {
        Collection arrayList = new ArrayList();
        for (Object next : this.j.b()) {
            if ((((mbm) next).f() == ubp.DOWNLOADING ? 1 : null) != null) {
                arrayList.add(next);
            }
        }
        for (mbm b : (List) arrayList) {
            a(b.b());
        }
    }

    public final void a(long j) {
        this.k.a(j);
    }

    private final void a(jp.naver.line.android.stickershop.model.a aVar, jp.naver.line.android.stickershop.model.c cVar) {
        this.j.a(aVar.b());
        this.k.a(aVar, cVar, this.a, false, false);
        g();
    }

    private final void f() {
        l lVar = this.g;
        int b = this.k.b();
        Iterable<mbm> b2 = this.j.b();
        int i = 0;
        if (!((b2 instanceof Collection) && ((Collection) b2).isEmpty())) {
            for (mbm b3 : b2) {
                if (b(b3)) {
                    i++;
                    if (i < 0) {
                        acnr.b();
                    }
                }
            }
        }
        lVar.a(b, i);
    }

    private final void a(mbm mbm) {
        if (this.k.a(mbm.b(), this.a)) {
            bp d = this.k.d(mbm.b());
            if (d != null) {
                this.j.a(mbm.b(), d.a());
            }
        }
    }

    private final void g() {
        if (!this.d) {
            l lVar = this.g;
            boolean z = (this.c && h()) || this.e;
            lVar.a(z);
            this.g.b(this.j.b().isEmpty());
            f();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onStickerPackageSynced(lzp lzp) {
        if (this.e) {
            List a;
            this.e = false;
            if (lzp instanceof lzr) {
                a = ((lzr) lzp).a();
            } else if (lzp instanceof lzq) {
                a = acob.a;
            } else {
                throw new k();
            }
            if (!this.c) {
                this.c = true;
                g();
                h hVar = this;
                this.b.b(this.i.h().a(a).d((pak) new j(new b(hVar))).a(ozh.a()).d((paj) new i(new c(hVar))));
            }
        }
    }

    private final boolean h() {
        return this.j.b().isEmpty();
    }

    private static boolean b(mbm mbm) {
        return (mbm.f() == ubp.NEED_DOWNLOAD || mbm.f() == ubp.DELETED) && mbm.h() == mbo.DOWNLOAD_ICON;
    }

    public static final /* synthetic */ List a(List list) {
        Iterable<ao> iterable = list;
        mbn mbn = mbm.a;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (ao a : iterable) {
            arrayList.add(mbn.a(a));
        }
        return (List) arrayList;
    }

    public static final /* synthetic */ void a(h hVar, List list) {
        hVar.h.a(new lwc(lxr.STICKER, list.size()));
        hVar.j.a(list);
        for (mbm a : list) {
            hVar.a(a);
        }
        hVar.c = false;
        hVar.g();
    }
}
