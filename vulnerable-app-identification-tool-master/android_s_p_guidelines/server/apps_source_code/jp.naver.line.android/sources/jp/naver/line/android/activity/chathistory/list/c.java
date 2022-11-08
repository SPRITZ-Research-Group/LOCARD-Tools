package jp.naver.line.android.activity.chathistory.list;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acqq;
import defpackage.acrx;
import defpackage.acrz;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuc;
import defpackage.acuo;
import defpackage.mnl;
import defpackage.mnm;
import defpackage.mnt;
import defpackage.qta;
import defpackage.qte;
import defpackage.qtm;
import defpackage.tlt;
import defpackage.tlu;
import defpackage.uer;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.list.msg.v;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.util.ai;
import jp.naver.line.android.util.aj;
import jp.naver.line.android.util.bp;
import jp.naver.line.android.util.dv;
import kotlin.Metadata;
import kotlin.e;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 <2\u00020\u0001:\u0001<B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\bH\u0007J\u0010\u00102\u001a\u0002002\u0006\u00101\u001a\u000203H\u0007J\u0010\u00104\u001a\u0002002\u0006\u00101\u001a\u000205H\u0007J\u0010\u00106\u001a\u0002002\u0006\u00101\u001a\u000207H\u0007J\u0010\u00108\u001a\u0002002\u0006\u00109\u001a\u00020:H\u0002J\b\u0010;\u001a\u000200H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b \u0010\u0016R\u001b\u0010\"\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b%\u0010\u000e\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b(\u0010\u000e\u001a\u0004\b'\u0010$R\u001b\u0010)\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b+\u0010\u000e\u001a\u0004\b*\u0010$R\u001b\u0010,\u001a\u00020\u001e8BX\u0002¢\u0006\f\n\u0004\b.\u0010\u000e\u001a\u0004\b-\u0010$¨\u0006="}, d2 = {"Ljp/naver/line/android/activity/chathistory/list/ChatDateOverlayViewController;", "", "activity", "Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;", "dateStringCacheDataManager", "Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;", "(Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;Ljp/naver/line/android/util/DateUtilsWorkaroundCachedStringDataManager;)V", "callHeaderState", "Ljp/naver/line/android/activity/chathistory/event/CallHeaderVisibilityChangedEvent;", "dateTextView", "Landroid/widget/TextView;", "getDateTextView", "()Landroid/widget/TextView;", "dateTextView$delegate", "Lkotlin/Lazy;", "handler", "Landroid/os/Handler;", "headerAppendViewHeight", "", "hidingAnimation", "Landroid/view/animation/Animation;", "getHidingAnimation", "()Landroid/view/animation/Animation;", "hidingAnimation$delegate", "hidingAnimationRunnable", "Ljava/lang/Runnable;", "latestAppliedTheme", "Ljp/naver/line/android/common/theme/ThemeManager;", "lazyDateOverlayView", "Lkotlin/Lazy;", "Landroid/view/View;", "showingAnimation", "getShowingAnimation", "showingAnimation$delegate", "spaceForGroupCallOnlineView", "getSpaceForGroupCallOnlineView", "()Landroid/view/View;", "spaceForGroupCallOnlineView$delegate", "spaceForGroupCallReadyView", "getSpaceForGroupCallReadyView", "spaceForGroupCallReadyView$delegate", "spaceForHeaderAppendView", "getSpaceForHeaderAppendView", "spaceForHeaderAppendView$delegate", "spaceForSingleCallView", "getSpaceForSingleCallView", "spaceForSingleCallView$delegate", "onCalViewVisibilityChanged", "", "event", "onChatHistoryScrollChanged", "Ljp/naver/line/android/activity/chathistory/event/ChatHistoryScrollPositionChangedEvent;", "onCustomThemeUpdated", "Ljp/naver/line/android/event/CustomThemeLoadedEvent;", "onHeaderAppendViewHeightChanged", "Ljp/naver/line/android/activity/chathistory/event/HeaderAppendViewHeightChangedEvent;", "showDateOverlay", "messageTimestampMillis", "", "startHidingAnimation", "Companion", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class c {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(c.class), "dateTextView", "getDateTextView()Landroid/widget/TextView;")), acso.a(new acsi(acso.a(c.class), "spaceForHeaderAppendView", "getSpaceForHeaderAppendView()Landroid/view/View;")), acso.a(new acsi(acso.a(c.class), "spaceForGroupCallReadyView", "getSpaceForGroupCallReadyView()Landroid/view/View;")), acso.a(new acsi(acso.a(c.class), "spaceForGroupCallOnlineView", "getSpaceForGroupCallOnlineView()Landroid/view/View;")), acso.a(new acsi(acso.a(c.class), "spaceForSingleCallView", "getSpaceForSingleCallView()Landroid/view/View;")), acso.a(new acsi(acso.a(c.class), "showingAnimation", "getShowingAnimation()Landroid/view/animation/Animation;")), acso.a(new acsi(acso.a(c.class), "hidingAnimation", "getHidingAnimation()Landroid/view/animation/Animation;"))};
    public static final d b = new d();
    private final Handler c = new Handler(Looper.getMainLooper());
    private final e<View> d = dv.a((ViewStub) this.p.findViewById(R.id.chathistory_date_overlay_viewstub), dv.a);
    private final e e = dv.a(this.d, R.id.chathistory_date_indicator_text, dv.a);
    private final e f = dv.a(this.d, R.id.chathistory_date_indicator_header_apend_view_space, dv.a);
    private final e g = dv.a(this.d, R.id.chathistory_date_indicator_group_call_ready_space, dv.a);
    private final e h = dv.a(this.d, R.id.chathistory_date_indicator_group_call_online_space, dv.a);
    private final e i = dv.a(this.d, R.id.chathistory_date_indicator_single_call_online_space, dv.a);
    private final e j = bp.a(new c(this));
    private final e k = bp.a(new a(this));
    private final Runnable l = new e(new b(this));
    private tlt m;
    private int n;
    private qta o = qta.GONE;
    private final ChatHistoryActivity p;
    private final aj q;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrz implements acqq<Animation> {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a.p, R.anim.chathistory_date_overlay_slide_up);
            loadAnimation.setAnimationListener(new mnl((View) this.a.d.d()));
            return loadAnimation;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrz implements acqq<Animation> {
        final /* synthetic */ c a;

        c(c cVar) {
            this.a = cVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a.p, R.anim.chathistory_date_overlay_slide_down);
            loadAnimation.setAnimationListener(new mnm((View) this.a.d.d()));
            return loadAnimation;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrx implements acqq<y> {
        b(c cVar) {
            super(0, cVar);
        }

        public final String getName() {
            return "startHidingAnimation";
        }

        public final acuc getOwner() {
            return acso.a(c.class);
        }

        public final String getSignature() {
            return "startHidingAnimation()V";
        }

        public final /* synthetic */ Object invoke() {
            c.c((c) this.receiver);
            return y.a;
        }
    }

    private final TextView a() {
        return (TextView) this.e.d();
    }

    private final View b() {
        return (View) this.f.d();
    }

    public c(ChatHistoryActivity chatHistoryActivity, aj ajVar) {
        this.p = chatHistoryActivity;
        this.q = ajVar;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onHeaderAppendViewHeightChanged(qtm qtm) {
        this.n = qtm.a();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onCalViewVisibilityChanged(qta qta) {
        this.o = qta;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onChatHistoryScrollChanged(qte qte) {
        if (!qte.a()) {
            Long c = qte.c();
            if (c != null) {
                long longValue = c.longValue();
                View view = (View) this.d.d();
                tlt tlt = this.m;
                if (tlt == null) {
                    tlu tlu = tlt.b;
                    tlt = tlu.a();
                }
                v.a((View) a(), tlt);
                b().getLayoutParams().height = this.n;
                boolean z = false;
                mnt.a(b(), this.n > 0);
                mnt.a((View) this.g.d(), this.o == qta.GROUP_CALL_READY);
                mnt.a((View) this.h.d(), this.o == qta.GROUP_CALL_ONGOING);
                View view2 = (View) this.i.d();
                if (this.o == qta.SINGLE_CALL) {
                    z = true;
                }
                mnt.a(view2, z);
                a().setText(ai.b(this.p, this.q, longValue, System.currentTimeMillis()));
                if (!mnt.a(view)) {
                    view.clearAnimation();
                    view.startAnimation((Animation) this.j.d());
                }
                this.c.removeCallbacks(this.l);
                this.c.postDelayed(this.l, 800);
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onCustomThemeUpdated(uer uer) {
        z b = this.p.f().b();
        if (uer.a(b != null ? b.l() : null)) {
            this.m = uer.a();
            if (this.d.g()) {
                v.a((View) a(), uer.a());
            }
        }
    }

    public static final /* synthetic */ void c(c cVar) {
        View view = (View) cVar.d.d();
        view.clearAnimation();
        view.startAnimation((Animation) cVar.k.d());
    }
}
