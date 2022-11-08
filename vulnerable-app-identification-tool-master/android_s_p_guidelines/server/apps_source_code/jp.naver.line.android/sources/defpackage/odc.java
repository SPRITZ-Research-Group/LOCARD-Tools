package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import com.linecorp.widget.stickersticoninput.sticker.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.util.dv;
import kotlin.Metadata;
import kotlin.e;
import kotlin.k;
import kotlin.u;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u0005{|}~BY\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\n\u0010O\u001a\u0004\u0018\u00010PH\u0002J\u0006\u0010Q\u001a\u00020\u000bJ\u0006\u0010R\u001a\u00020\u000bJ\u0006\u0010S\u001a\u00020\u000bJ\u0006\u0010T\u001a\u00020\u0010J\u0010\u0010U\u001a\u00020\u00102\u0006\u0010V\u001a\u00020WH\u0002J\u0018\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u000bH\u0002J\u0010\u0010\\\u001a\u00020\u00102\u0006\u0010]\u001a\u00020PH\u0002J\u0010\u0010^\u001a\u00020\u00102\u0006\u0010]\u001a\u00020PH\u0002J\u0006\u0010_\u001a\u00020\u0010J\u0018\u0010`\u001a\u00020\u00102\u0006\u0010]\u001a\u00020P2\u0006\u0010a\u001a\u00020-H\u0002J\u0006\u0010b\u001a\u00020\u0010J\u0010\u0010c\u001a\u00020\u00102\u0006\u0010d\u001a\u00020eH\u0007J\u0018\u0010f\u001a\u00020\u00102\u0006\u0010g\u001a\u00020P2\u0006\u0010h\u001a\u00020\u000bH\u0002J\u0006\u0010i\u001a\u00020\u0010J\u000e\u0010j\u001a\u00020\u00102\u0006\u0010k\u001a\u00020lJ\u000e\u0010m\u001a\u00020\u00102\u0006\u0010n\u001a\u00020oJ\u0010\u0010p\u001a\u00020\u00102\u0006\u0010]\u001a\u00020PH\u0002J\u000e\u0010q\u001a\u00020\u00102\u0006\u0010k\u001a\u00020-J\u000e\u0010r\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u000bJ\u0010\u0010s\u001a\u00020\u00102\b\b\u0001\u0010t\u001a\u00020-J\u0010\u0010u\u001a\u00020\u00102\u0006\u0010]\u001a\u00020PH\u0002J\u0006\u0010v\u001a\u00020\u0010J\u0010\u0010w\u001a\u00020\u00102\u0006\u0010x\u001a\u00020\u001eH\u0002J\b\u0010y\u001a\u00020\u0010H\u0002J\b\u0010z\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010$\u001a\u00020%8BX\u0002¢\u0006\f\n\u0004\b(\u0010\u001c\u001a\u0004\b&\u0010'R\u000e\u0010)\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8BX\u0002¢\u0006\f\n\u0004\b2\u0010\u001c\u001a\u0004\b0\u00101R\u000e\u00103\u001a\u000204X\u0004¢\u0006\u0002\n\u0000R\u001b\u00105\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b7\u0010\u001c\u001a\u0004\b6\u0010\u001aR\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020:09X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010;\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b>\u0010\u001c\u001a\u0004\b<\u0010=R\u000e\u0010?\u001a\u00020@X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010C\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bE\u0010\u001c\u001a\u0004\bD\u0010\u001aR\u000e\u0010F\u001a\u00020GX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010J\u001a\u00060KR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010L\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bN\u0010\u001c\u001a\u0004\bM\u0010\u001a¨\u0006\u0001"}, d2 = {"Lcom/linecorp/widget/stickersticoninput/StickerSticonInputViewController;", "", "application", "Ljp/naver/line/android/LineApplication;", "rootViewStub", "Landroid/view/ViewStub;", "messageInputView", "Landroid/widget/EditText;", "eventListener", "Lcom/linecorp/widget/stickersticoninput/sticker/StickerInputEventListener;", "isStickerUsageAllowed", "", "isPaidSticonAllowed", "onPackageDownloadFailed", "Lkotlin/Function1;", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageDownloadController$Controller;", "", "stickerDataManager", "Lcom/linecorp/shop/sticker/StickerDataManager;", "(Ljp/naver/line/android/LineApplication;Landroid/view/ViewStub;Landroid/widget/EditText;Lcom/linecorp/widget/stickersticoninput/sticker/StickerInputEventListener;ZZLkotlin/jvm/functions/Function1;Lcom/linecorp/shop/sticker/StickerDataManager;)V", "availablePackageItems", "", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageDataDownloadControllerPair;", "backspaceContainer", "Landroid/view/View;", "getBackspaceContainer", "()Landroid/view/View;", "backspaceContainer$delegate", "Lkotlin/Lazy;", "currentPackageType", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageType;", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "hasHistory", "inputSelectorViewController", "Lcom/linecorp/widget/stickersticoninput/StickerSticonInputSelectionController;", "inputSelectorViewPager", "Landroidx/viewpager/widget/ViewPager;", "getInputSelectorViewPager", "()Landroidx/viewpager/widget/ViewPager;", "inputSelectorViewPager$delegate", "isLandscape", "isVisible", "()Z", "maxHeightPx", "", "packageSelectorView", "Landroidx/recyclerview/widget/RecyclerView;", "getPackageSelectorView", "()Landroidx/recyclerview/widget/RecyclerView;", "packageSelectorView$delegate", "packageSelectorViewController", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageSelectorViewController;", "rootView", "getRootView", "rootView$delegate", "selectedPackageMap", "", "Lcom/linecorp/widget/stickersticoninput/StickerSticonInputViewController$SelectedPackageData;", "stickerPreviewGuideView", "getStickerPreviewGuideView", "()Landroid/view/ViewStub;", "stickerPreviewGuideView$delegate", "stickerPreviewGuideViewController", "Lcom/linecorp/widget/stickersticoninput/StickerPreviewGuideViewController;", "stickerResourceRenderer", "Lcom/linecorp/shop/sticker/util/StickerResourceRenderer;", "stickerSwitchInputView", "getStickerSwitchInputView", "stickerSwitchInputView$delegate", "sticonDataManager", "Lcom/linecorp/shop/sticon/SticonDataManager;", "sticonInfoCache", "Ljp/naver/line/android/bo/shop/sticon/SticonInfoCache;", "sticonPackageEventListener", "Lcom/linecorp/widget/stickersticoninput/StickerSticonInputViewController$SticonPackageEventListener;", "sticonSwitchInputView", "getSticonSwitchInputView", "sticonSwitchInputView$delegate", "findPackageToSelect", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackage;", "hide", "hideStickerPreview", "isSticonViewVisible", "onDestroy", "onDownloadStateChanged", "packageItem", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackage$PackageItem;", "onPackageDeleted", "packageData", "Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageData;", "wasDeletionSuccessful", "onPackageSelectionClicked", "stickerSticonPackage", "onPackageSelectionScrolled", "onPause", "onProgressUpdate", "percentage", "onResume", "onStickerPackageChanged", "event", "Ljp/naver/line/android/stickershop/event/StickerPackageChangedEvent;", "populateViews", "packageToSelect", "shouldScrollToPackage", "refresh", "selectPaidSticonPackage", "sticonPackageId", "", "selectStickerPackage", "stickerPackageId", "", "selectStickerSticonPackage", "selectUnpaidSticonPackage", "setLandscape", "setMaxHeightPx", "newHeightPx", "setSelectedPackageFromPackageId", "show", "switchPackageType", "packageType", "updateHeight", "updateViewVisibility", "GetPackageTask", "PackageResults", "RefreshViewTask", "SelectedPackageData", "SticonPackageEventListener", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: odc */
public final class odc {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(odc.class), "rootView", "getRootView()Landroid/view/View;")), acso.a(new acsi(acso.a(odc.class), "inputSelectorViewPager", "getInputSelectorViewPager()Landroidx/viewpager/widget/ViewPager;")), acso.a(new acsi(acso.a(odc.class), "stickerSwitchInputView", "getStickerSwitchInputView()Landroid/view/View;")), acso.a(new acsi(acso.a(odc.class), "sticonSwitchInputView", "getSticonSwitchInputView()Landroid/view/View;")), acso.a(new acsi(acso.a(odc.class), "backspaceContainer", "getBackspaceContainer()Landroid/view/View;")), acso.a(new acsi(acso.a(odc.class), "packageSelectorView", "getPackageSelectorView()Landroidx/recyclerview/widget/RecyclerView;")), acso.a(new acsi(acso.a(odc.class), "stickerPreviewGuideView", "getStickerPreviewGuideView()Landroid/view/ViewStub;"))};
    private final acqr<oec, y> A;
    private final lzd B;
    private final e b;
    private final a c;
    private final e d;
    private final ocy e;
    private final e f;
    private final e g;
    private final e h;
    private final e i;
    private final oem j;
    private final e k;
    private final ocx l;
    private final odm m;
    private final mee n;
    private final soo o;
    private final mcw p;
    private boolean q;
    private boolean r;
    private oev s;
    private final Map<oev, odh> t;
    private List<odw> u;
    private int v;
    private final LineApplication w;
    private final i x;
    private final boolean y;
    private final boolean z;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
    /* renamed from: odc$a */
    final class a implements Runnable {
        final /* synthetic */ odc a;
        final /* synthetic */ odn b;
        final /* synthetic */ boolean c;

        a(odc odc, odn odn, boolean z) {
            this.a = odc;
            this.b = odn;
            this.c = z;
        }

        public final void run() {
            oem j = this.a.j;
            Iterable<odw> e = this.a.u;
            Collection arrayList = new ArrayList(acns.a((Iterable) e, 10));
            for (odw a : e) {
                arrayList.add(a.a());
            }
            j.a((List) arrayList, this.b, this.c);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: odc$b */
    final class b extends acrz implements acqq<View> {
        final /* synthetic */ ViewStub a;

        b(ViewStub viewStub) {
            this.a = viewStub;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            this.a.setLayoutResource(R.layout.sticker_sticon_input_new);
            return this.a.inflate();
        }
    }

    public odc(LineApplication lineApplication, ViewStub viewStub, EditText editText, i iVar, boolean z, boolean z2, acqr<? super oec, y> acqr, byte b) {
        this(lineApplication, viewStub, editText, iVar, z, z2, acqr);
    }

    private final View j() {
        return (View) this.b.d();
    }

    private final View k() {
        return (View) this.f.d();
    }

    private final View l() {
        return (View) this.g.d();
    }

    private final View m() {
        return (View) this.h.d();
    }

    private odc(LineApplication lineApplication, ViewStub viewStub, EditText editText, i iVar, boolean z, boolean z2, acqr<? super oec, y> acqr, lzd lzd) {
        oev oev;
        this.w = lineApplication;
        this.x = iVar;
        this.y = z;
        this.z = z2;
        this.A = acqr;
        this.B = lzd;
        this.b = h.a(new b(viewStub));
        this.c = this.w.a();
        this.d = dv.e(j(), R.id.sticker_sticon_input_selection_view);
        this.f = dv.e(j(), R.id.sticker_input_type_switch);
        this.g = dv.e(j(), R.id.sticon_input_type_switch);
        this.h = dv.e(j(), R.id.sticker_sticon_input_backspace_container);
        this.i = dv.e(j(), R.id.sticker_sticon_input_package_selector);
        this.k = dv.e(j(), R.id.sticker_preview_guide_viewstub);
        this.m = new odm(this);
        this.n = this.w.f().n();
        this.o = this.w.f().p();
        this.p = new mcw(new ufh());
        this.s = oev.STICKER;
        this.t = acom.b(u.a(oev.STICKER, odi.a), u.a(oev.STICON, odi.a));
        this.u = acob.a;
        this.v = BaseClientBuilder.API_PRIORITY_OTHER;
        odc odc = this;
        this.e = new ocy(this.w, (ViewPager) this.d.d(), editText, this.x, this.z, new acqr<odn, y>(odc) {
            public final String getName() {
                return "onPackageSelectionScrolled";
            }

            public final acuc getOwner() {
                return acso.a(odc.class);
            }

            public final String getSignature() {
                return "onPackageSelectionScrolled(Lcom/linecorp/widget/stickersticoninput/StickerSticonPackage;)V";
            }

            public final /* synthetic */ Object invoke(Object obj) {
                odc.a((odc) this.receiver, (odn) obj);
                return y.a;
            }
        }, new acrc<ods, Boolean, y>(odc) {
            public final String getName() {
                return "onPackageDeleted";
            }

            public final acuc getOwner() {
                return acso.a(odc.class);
            }

            public final String getSignature() {
                return "onPackageDeleted(Lcom/linecorp/widget/stickersticoninput/StickerSticonPackageData;Z)V";
            }

            public final /* synthetic */ Object invoke(Object obj, Object obj2) {
                odc.a((odc) this.receiver, (ods) obj, ((Boolean) obj2).booleanValue());
                return y.a;
            }
        }, this.p);
        this.j = new oem((RecyclerView) this.i.d(), this.B.n(), this.w.f().o(), this.n, new acqr<odn, y>(odc) {
            public final String getName() {
                return "onPackageSelectionClicked";
            }

            public final acuc getOwner() {
                return acso.a(odc.class);
            }

            public final String getSignature() {
                return "onPackageSelectionClicked(Lcom/linecorp/widget/stickersticoninput/StickerSticonPackage;)V";
            }

            public final /* synthetic */ Object invoke(Object obj) {
                odc.b((odc) this.receiver, (odn) obj);
                return y.a;
            }
        });
        this.l = new ocx((ViewStub) this.k.d());
        k().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ odc a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.a(oev.STICON);
            }
        });
        l().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ odc a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.a(oev.STICKER);
            }
        });
        final EditText editText2 = editText;
        m().setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                editText2.dispatchKeyEvent(new KeyEvent(0, 67));
            }
        });
        sol.a().a((son) this.m);
        this.c.b(this);
        if (this.y) {
            oev = oev.STICKER;
        } else {
            oev = oev.STICON;
        }
        a(oev);
    }

    private /* synthetic */ odc(LineApplication lineApplication, ViewStub viewStub, EditText editText, i iVar, boolean z, boolean z2, acqr acqr) {
        this(lineApplication, viewStub, editText, iVar, z, z2, acqr, lineApplication.f().o());
    }

    public final boolean a() {
        return mnt.a(j());
    }

    public final void a(int i) {
        if (this.v != i) {
            this.v = i;
            if (mnt.a(j())) {
                o();
            }
        }
    }

    public final void a(boolean z) {
        if (this.q != z) {
            this.q = z;
            o();
        }
    }

    public final void b() {
        new odd(this).a((cmp) new odg(this)).a();
    }

    public final void d() {
        mnt.a(j(), true);
        this.x.a(true);
        o();
    }

    public final void a(long j) {
        if (this.y) {
            this.t.put(oev.STICKER, new odk(j, true));
            odn p = p();
            if (this.s != oev.STICKER || p == null) {
                a(oev.STICKER);
            } else {
                a(p);
            }
        }
    }

    public final void b(int i) {
        this.t.put(oev.STICON, new odl(i, true));
        odn p = p();
        if (this.s != oev.STICON || p == null) {
            a(oev.STICON);
        } else {
            a(p);
        }
    }

    public final void a(String str) {
        this.t.put(oev.STICON, new odj(str, true));
        odn p = p();
        if (this.s != oev.STICON || p == null) {
            a(oev.STICON);
        } else {
            a(p);
        }
    }

    private final void a(odn odn) {
        this.e.a(odn, false);
        this.j.a(odn, true, false);
    }

    private final void a(oev oev) {
        if (oev != oev.STICKER || this.y) {
            if (this.s.a()) {
                this.e.a();
            }
            this.s = oev;
            b();
        }
    }

    private final void n() {
        boolean z = false;
        int i = this.s == oev.STICKER ? 1 : 0;
        mnt.a(m(), i ^ 1);
        View k = k();
        boolean z2 = i != 0 && this.y;
        mnt.a(k, z2);
        k = l();
        if (i == 0 && this.y) {
            z = true;
        }
        mnt.a(k, z);
    }

    public final void f() {
        for (odw b : this.u) {
            ozn b2 = b.b().b();
            if (b2 != null) {
                b2.dispose();
            }
        }
    }

    private final void o() {
        LayoutParams layoutParams = j().getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = Math.min(this.v, this.w.getResources().getDimensionPixelSize(this.q ? R.dimen.message_input_menu_height_landscape : R.dimen.message_input_menu_height_portrait));
            j().setLayoutParams(layoutParams);
        }
    }

    public final void g() {
        this.c.c(this);
        sol.a().b((son) this.m);
        this.p.a();
    }

    public final boolean i() {
        return this.e.a();
    }

    private final void b(odn odn) {
        Object obj;
        if (odn instanceof odo) {
            obj = odi.a;
        } else if (odn instanceof odp) {
            odp odp = (odp) odn;
            ods d = odp.d();
            if (d instanceof odu) {
                obj = new odk(((odu) odp.d()).a());
            } else if (d instanceof odt) {
                obj = new odj(((odt) odp.d()).a());
            } else if (d instanceof odv) {
                obj = new odl(((odv) odp.d()).b());
            } else {
                throw new k();
            }
        } else {
            return;
        }
        this.t.put(odn.c(), obj);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onStickerPackageChanged(uwf uwf) {
        if (this.s != oev.STICKER) {
            return;
        }
        if ((uwf instanceof uwj) || (uwf instanceof uwg) || (uwf instanceof uwk) || (uwf instanceof uwh)) {
            b();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final odn p() {
        odw odw;
        odn a;
        Object obj = (odh) this.t.get(this.s);
        if (obj == null) {
            obj = odi.a;
        }
        odn a2;
        Object obj2;
        odp odp;
        ods d;
        Object obj3;
        if (acry.a(obj, odi.a)) {
            if (this.r) {
                for (Object next : this.u) {
                    if (((odw) next).a() instanceof odo) {
                        break;
                    }
                }
                Object next2 = null;
                odw = (odw) next2;
            } else {
                odw = (odw) this.u.get(1);
            }
        } else if (obj instanceof odk) {
            for (Object obj22 : this.u) {
                a2 = ((odw) obj22).a();
                if (!(a2 instanceof odp)) {
                    a2 = null;
                }
                odp = (odp) a2;
                d = odp != null ? odp.d() : null;
                if (!(d instanceof odu)) {
                    d = null;
                }
                odu odu = (odu) d;
                if (odu == null || odu.a() != ((odk) obj).b()) {
                    obj3 = null;
                    continue;
                } else {
                    obj3 = 1;
                    continue;
                }
                if (obj3 != null) {
                    break;
                }
            }
            obj22 = null;
            odw = (odw) obj22;
        } else if (obj instanceof odj) {
            for (Object next3 : this.u) {
                odn a3 = ((odw) next3).a();
                if (!(a3 instanceof odp)) {
                    a3 = null;
                }
                odp odp2 = (odp) a3;
                ods d2 = odp2 != null ? odp2.d() : null;
                if (!(d2 instanceof odt)) {
                    d2 = null;
                }
                odt odt = (odt) d2;
                if (acry.a(odt != null ? odt.a() : null, ((odj) obj).b())) {
                    break;
                }
            }
            Object next32 = null;
            odw = (odw) next32;
        } else if (obj instanceof odl) {
            for (Object obj222 : this.u) {
                a2 = ((odw) obj222).a();
                if (!(a2 instanceof odp)) {
                    a2 = null;
                }
                odp = (odp) a2;
                d = odp != null ? odp.d() : null;
                if (!(d instanceof odv)) {
                    d = null;
                }
                odv odv = (odv) d;
                if (odv == null || odv.b() != ((odl) obj).b()) {
                    obj3 = null;
                    continue;
                } else {
                    obj3 = 1;
                    continue;
                }
                if (obj3 != null) {
                    break;
                }
            }
            obj222 = null;
            odw = (odw) obj222;
        } else {
            throw new k();
        }
        if (odw != null) {
            a = odw.a();
        }
        odw odw2 = (odw) acnz.f(this.u);
        if (odw2 != null) {
            return odw2.a();
        }
        a = null;
        return a;
    }

    public final boolean c() {
        if (!mnt.a(j())) {
            return false;
        }
        this.e.a();
        mnt.a(j(), false);
        this.x.a(false);
        return true;
    }

    public final void e() {
        if (mnt.a(j())) {
            b();
        }
    }

    public final boolean h() {
        return mnt.a(j()) && this.s == oev.STICON;
    }

    public static final /* synthetic */ void a(odc odc, odn odn, boolean z) {
        odc.l.a(odc.s);
        odc.e.a(odc.u, odn);
        odc.b(odn);
        if (mnt.a(odc.m()) || odn.b()) {
            oem oem = odc.j;
            Iterable<odw> iterable = odc.u;
            Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
            for (odw a : iterable) {
                arrayList.add(a.a());
            }
            oem.a((List) arrayList, odn, z);
            odc.n();
            return;
        }
        odc.n();
        odc.j().post(new a(odc, odn, z));
    }

    public static final /* synthetic */ void a(odc odc, odn odn) {
        odc.b(odn);
        odc.j.a(odn, true, true);
    }

    public static final /* synthetic */ void a(odc odc, ods ods, boolean z) {
        if (z) {
            spm.a(ods);
            odc.b();
        }
    }

    public static final /* synthetic */ void b(odc odc, odn odn) {
        odc.b(odn);
        odc.e.a(odn, true);
    }
}
