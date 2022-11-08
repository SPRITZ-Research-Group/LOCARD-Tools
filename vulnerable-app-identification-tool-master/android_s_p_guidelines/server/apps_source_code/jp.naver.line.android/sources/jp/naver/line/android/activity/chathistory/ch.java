package jp.naver.line.android.activity.chathistory;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acqq;
import defpackage.acqr;
import defpackage.acry;
import defpackage.acrz;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuc;
import defpackage.acuo;
import defpackage.cmi;
import defpackage.cmj;
import defpackage.cmp;
import defpackage.cpn;
import defpackage.mne;
import defpackage.mnj;
import defpackage.mnt;
import defpackage.qte;
import defpackage.qtx;
import defpackage.qty;
import defpackage.rhd;
import defpackage.swv;
import defpackage.syq;
import defpackage.tds;
import defpackage.tej;
import defpackage.tlc;
import defpackage.tlt;
import defpackage.uer;
import defpackage.uoa;
import defpackage.uob;
import defpackage.vgt;
import java.util.Arrays;
import jp.naver.line.android.R;
import jp.naver.line.android.customview.sticon.i;
import jp.naver.line.android.customview.sticon.p;
import jp.naver.line.android.model.cz;
import jp.naver.line.android.util.bp;
import jp.naver.line.android.util.ca;
import jp.naver.line.android.util.dv;
import jp.naver.line.android.util.w;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 U2\u00020\u0001:\u0003UVWB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0007J\u0010\u00109\u001a\u0002062\u0006\u00107\u001a\u00020:H\u0007J0\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u00052\u000e\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0BH\u0002J\u000e\u0010D\u001a\u0002062\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010E\u001a\u0002062\u0006\u0010F\u001a\u00020GH\u0007J\u0010\u0010H\u001a\u0002062\u0006\u0010F\u001a\u00020IH\u0007J\u0010\u0010J\u001a\u0002062\u0006\u0010K\u001a\u00020\u0007H\u0002J\u0010\u0010L\u001a\u0002062\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J4\u0010M\u001a\u0002062\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00070\u001b2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020(0\u001b2\u0006\u0010P\u001a\u00020\u00102\u0006\u0010Q\u001a\u00020\u0010H\u0002J\b\u0010R\u001a\u000206H\u0002J\f\u0010S\u001a\u00020\u0010*\u00020\u0007H\u0002J\f\u0010T\u001a\u00020\u0010*\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001bX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b#\u0010\u0017\u001a\u0004\b\"\u0010\u001fR\u001b\u0010$\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b&\u0010\u0017\u001a\u0004\b%\u0010\u0015R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u001bX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b+\u0010\u0017\u001a\u0004\b*\u0010\u0015R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020(0\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00070\u001bX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8BX\u0002¢\u0006\f\n\u0004\b2\u0010\u0017\u001a\u0004\b0\u00101R\u000e\u00103\u001a\u000204X\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Ljp/naver/line/android/activity/chathistory/LinkToBottomViewController;", "", "activity", "Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;", "chatId", "", "rootView", "Landroid/view/View;", "contentsViewController", "Ljp/naver/line/android/activity/chathistory/ChatHistoryContentsViewController;", "messageDataManager", "Ljp/naver/line/android/chathistory/MessageDataManager;", "theme", "Ljp/naver/line/android/common/theme/ThemeManager;", "(Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;Ljava/lang/String;Landroid/view/View;Ljp/naver/line/android/activity/chathistory/ChatHistoryContentsViewController;Ljp/naver/line/android/chathistory/MessageDataManager;Ljp/naver/line/android/common/theme/ThemeManager;)V", "isFullScreen", "", "latestTheme", "myMid", "newMessageColonView", "getNewMessageColonView", "()Landroid/view/View;", "newMessageColonView$delegate", "Lkotlin/Lazy;", "newMessageData", "Ljp/naver/line/android/activity/chathistory/LinkToBottomViewController$NewMessageData;", "newMessageLazyViewStub", "Lkotlin/Lazy;", "newMessageNameView", "Landroid/widget/TextView;", "getNewMessageNameView", "()Landroid/widget/TextView;", "newMessageNameView$delegate", "newMessageTextView", "getNewMessageTextView", "newMessageTextView$delegate", "newMessageView", "getNewMessageView", "newMessageView$delegate", "newMessageViewLazyAnimationController", "Lcom/linecorp/view/animation/SlideAnimationController;", "scrollDownButton", "getScrollDownButton", "scrollDownButton$delegate", "scrollDownButtonLazyAnimationController", "scrollDownLazyViewStub", "sticonAndMentionRenderer", "Ljp/naver/line/android/customview/sticon/SticonAndMentionRenderer;", "getSticonAndMentionRenderer", "()Ljp/naver/line/android/customview/sticon/SticonAndMentionRenderer;", "sticonAndMentionRenderer$delegate", "visibleItem", "Ljp/naver/line/android/activity/chathistory/LinkToBottomViewController$VisibleItem;", "onChatHistoryScrollPositionChanged", "", "event", "Ljp/naver/line/android/activity/chathistory/event/ChatHistoryScrollPositionChangedEvent;", "onCustomThemeLoaded", "Ljp/naver/line/android/event/CustomThemeLoadedEvent;", "onNewMessageTextArrived", "serverMessageId", "", "userInputTextData", "Ljp/naver/line/android/chathistory/model/UserInputTextData;", "virtualSenderName", "userData", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/model/UserData;", "onOnAirVideoScreenModeChanged", "onUnreadMessageFound", "e", "Ljp/naver/line/android/activity/chathistory/event/UnreadMessageFoundEvent;", "onUnsentMessage", "Ljp/naver/line/android/activity/chathistory/event/UnsentMessageEvent;", "scrollDownToBottom", "clickedView", "updateNewMessageContent", "updateVisibility", "lazyViewStub", "lazyAnimationController", "shouldShow", "isShownCurrently", "updateVisibleItem", "applyNewMessageViewTheme", "applyScrollDownButtonTheme", "Companion", "NewMessageData", "VisibleItem", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class ch {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(ch.class), "newMessageView", "getNewMessageView()Landroid/view/View;")), acso.a(new acsi(acso.a(ch.class), "newMessageNameView", "getNewMessageNameView()Landroid/widget/TextView;")), acso.a(new acsi(acso.a(ch.class), "newMessageColonView", "getNewMessageColonView()Landroid/view/View;")), acso.a(new acsi(acso.a(ch.class), "newMessageTextView", "getNewMessageTextView()Landroid/widget/TextView;")), acso.a(new acsi(acso.a(ch.class), "scrollDownButton", "getScrollDownButton()Landroid/view/View;")), acso.a(new acsi(acso.a(ch.class), "sticonAndMentionRenderer", "getSticonAndMentionRenderer()Ljp/naver/line/android/customview/sticon/SticonAndMentionRenderer;"))};
    public static final ci b = new ci();
    private static final tlc[] v;
    private static final tlc[] w;
    private final String c;
    private final kotlin.e<View> d;
    private final kotlin.e e;
    private final kotlin.e<mne> f;
    private final kotlin.e g;
    private final kotlin.e h;
    private final kotlin.e i;
    private final kotlin.e<View> j;
    private final kotlin.e k;
    private final kotlin.e<mne> l;
    private cj m;
    private boolean n;
    private ck o;
    private tlt p;
    private final kotlin.e q;
    private final ChatHistoryActivity r;
    private final String s;
    private final v t;
    private final swv u;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/model/UserData;", "mid", "", "apply"}, k = 3, mv = {1, 1, 13})
    final class c<T, R> implements cpn<P, R> {
        final /* synthetic */ z a;

        c(z zVar) {
            this.a = zVar;
        }

        public final /* synthetic */ Object apply(Object obj) {
            String str = (String) obj;
            ci ciVar = ch.b;
            tej C = this.a.C();
            cmj cmj = cmi.a;
            return cmj.b(C.a(str));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "userData", "Lcom/linecorp/collection/Optional;", "Ljp/naver/line/android/model/UserData;", "apply"}, k = 3, mv = {1, 1, 13})
    final class d<T, R> implements cpn<P, R> {
        final /* synthetic */ ch a;
        final /* synthetic */ syq b;
        final /* synthetic */ tds c;

        d(ch chVar, syq syq, tds tds) {
            this.a = chVar;
            this.b = syq;
            this.c = tds;
        }

        public final /* synthetic */ Object apply(Object obj) {
            ch.a(this.a, this.b.d(), this.c, this.b.p().a(), (cmi) obj);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrz implements acqr<View, y> {
        final /* synthetic */ ch a;

        a(ch chVar) {
            this.a = chVar;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            View view = (View) obj;
            view.setOnClickListener(new cl(new acqr<View, y>(this.a) {
                public final String getName() {
                    return "scrollDownToBottom";
                }

                public final acuc getOwner() {
                    return acso.a(ch.class);
                }

                public final String getSignature() {
                    return "scrollDownToBottom(Landroid/view/View;)V";
                }

                public final /* synthetic */ Object invoke(Object obj) {
                    ch.a((ch) this.receiver);
                    return y.a;
                }
            }));
            this.a.a(view);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/view/animation/SlideAnimationController;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrz implements acqq<mne> {
        final /* synthetic */ ch a;

        b(ch chVar) {
            this.a = chVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            mnt.a(this.a.a(), false);
            return new mne(this.a.a(), mnj.BOTTOM);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/linecorp/view/animation/SlideAnimationController;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class e extends acrz implements acqq<mne> {
        final /* synthetic */ ch a;

        e(ch chVar) {
            this.a = chVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            mnt.a(this.a.d(), false);
            return new mne(this.a.d(), mnj.BOTTOM);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class f extends acrz implements acqr<View, y> {
        final /* synthetic */ ch a;

        f(ch chVar) {
            this.a = chVar;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            View view = (View) obj;
            view.setOnClickListener(new cl(new acqr<View, y>(this.a) {
                public final String getName() {
                    return "scrollDownToBottom";
                }

                public final acuc getOwner() {
                    return acso.a(ch.class);
                }

                public final String getSignature() {
                    return "scrollDownToBottom(Landroid/view/View;)V";
                }

                public final /* synthetic */ Object invoke(Object obj) {
                    ch.a((ch) this.receiver);
                    return y.a;
                }
            }));
            ch.b(this.a, view);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/customview/sticon/SticonAndMentionRenderer;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class g extends acrz implements acqq<jp.naver.line.android.customview.sticon.g> {
        final /* synthetic */ ch a;

        g(ch chVar) {
            this.a = chVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return i.a(this.a.c());
        }
    }

    private final View a() {
        return (View) this.e.d();
    }

    private final TextView b() {
        return (TextView) this.g.d();
    }

    private final TextView c() {
        return (TextView) this.i.d();
    }

    private final View d() {
        return (View) this.k.d();
    }

    public ch(ChatHistoryActivity chatHistoryActivity, String str, View view, v vVar, swv swv, tlt tlt) {
        this.r = chatHistoryActivity;
        this.s = str;
        this.t = vVar;
        this.u = swv;
        uob uob = uoa.a;
        String m = uoa.h.a().m();
        if (m == null) {
            m = "";
        }
        this.c = m;
        this.d = dv.a((ViewStub) view.findViewById(R.id.chathistory_new_message_viewstub), (acqr) new a(this));
        this.e = this.d;
        this.f = h.a(new b(this));
        this.g = dv.a(this.d, R.id.new_message_name, dv.a);
        this.h = dv.a(this.d, R.id.new_message_colon, dv.a);
        this.i = dv.a(this.d, R.id.new_message_text, dv.a);
        this.j = dv.a((ViewStub) view.findViewById(R.id.chathistory_scroll_to_bottom_button), (acqr) new f(this));
        this.k = this.j;
        this.l = h.a(new e(this));
        this.o = ck.NONE;
        this.p = tlt;
        this.q = bp.a(new g(this));
    }

    private final void e() {
        if (!this.r.R() && this.t.l()) {
            ck ckVar;
            boolean z = true;
            Object obj = (this.t.m() && this.t.o()) ? 1 : null;
            jp.naver.line.android.activity.chathistory.list.f u = this.t.u();
            Integer valueOf = u != null ? Integer.valueOf(u.e()) : null;
            Object obj2 = (valueOf == null || valueOf.intValue() <= 0) ? 1 : null;
            if (this.n || obj != null || obj2 != null) {
                ckVar = ck.NONE;
            } else if (this.m != null) {
                ckVar = ck.NEW_MESSAGE_VIEW;
            } else {
                ckVar = ck.SCROLL_DOWN_BUTTON;
            }
            a(this.j, this.l, ckVar.b(), this.o.b());
            a(this.d, this.f, ckVar.a(), this.o.a());
            if (ckVar.a()) {
                cj cjVar = this.m;
                if (cjVar != null) {
                    ch chVar = this;
                    if (cjVar.b().length() <= 0) {
                        z = false;
                    }
                    mnt.a((View) b(), z);
                    mnt.a((View) chVar.h.d(), z);
                    if (z) {
                        b().setText(cjVar.b());
                    }
                    ((jp.naver.line.android.customview.sticon.g) chVar.q.d()).a(new p(cjVar.c(), chVar.c, c(), (byte) 0));
                }
            } else {
                this.m = null;
            }
            this.o = ckVar;
        }
    }

    private static void a(kotlin.e<? extends View> eVar, kotlin.e<mne> eVar2, boolean z, boolean z2) {
        if (z != z2 && (z || eVar.g())) {
            ((mne) eVar2.d()).a(z);
        }
    }

    private final boolean a(View view) {
        tlt tlt = this.p;
        tlc[] tlcArr = v;
        return tlt.a(view, (tlc[]) Arrays.copyOf(tlcArr, tlcArr.length));
    }

    public final void a(boolean z) {
        this.n = z;
        e();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onUnreadMessageFound(qtx qtx) {
        z b = this.r.f().b();
        if (b != null) {
            if (this.o != ck.NONE || !this.t.l()) {
                syq b2 = qtx.b();
                if (!acry.a((Object) b2, syq.a) && (acry.a(b2.e(), this.s) ^ 1) == 0 && b2.n() && b2.a(this.c)) {
                    tds a = rhd.a((Context) this.r, b2);
                    if (!a.e()) {
                        w.a((cpn) new c(b)).a((cmp) ca.a((cpn) new d(this, b2, a))).a(b2.g());
                    }
                }
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onUnsentMessage(qty qty) {
        long a = qty.a();
        cj cjVar = this.m;
        if (cjVar != null && a == cjVar.a()) {
            this.m = null;
            e();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onChatHistoryScrollPositionChanged(qte qte) {
        if (qte.b() > 0) {
            e();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onCustomThemeLoaded(uer uer) {
        if (!acry.a(this.p, uer.a()) && uer.a(this.s)) {
            this.p = uer.a();
            if (this.d.g()) {
                a(a());
            }
            if (this.j.g()) {
                a(d());
            }
        }
    }

    static {
        tlc[] tlcArr = new tlc[4];
        tlcArr[0] = new tlc(R.id.new_message_container, vgt.a);
        tlcArr[1] = new tlc(R.id.new_message_name, vgt.b);
        tlcArr[2] = new tlc(R.id.new_message_colon, vgt.b);
        tlcArr[3] = new tlc(R.id.new_message_text, vgt.b);
        v = tlcArr;
        tlcArr = new tlc[1];
        tlcArr[0] = new tlc(R.id.chathistory_scroll_to_bottom_button, vgt.c);
        w = tlcArr;
    }

    public static final /* synthetic */ void a(ch chVar, long j, tds tds, String str, cmi cmi) {
        if ((((CharSequence) str).length() > 0 ? 1 : null) == null) {
            cz czVar = (cz) cmi.c();
            str = czVar != null ? czVar.b() : null;
            if (str == null) {
                str = "";
            }
        }
        chVar.m = new cj(j, str, tds);
        chVar.e();
    }

    public static final /* synthetic */ void a(ch chVar) {
        if (chVar.t.o()) {
            chVar.t.p();
        } else {
            chVar.t.j();
        }
    }

    public static final /* synthetic */ boolean b(ch chVar, View view) {
        tlt tlt = chVar.p;
        tlc[] tlcArr = w;
        return tlt.a(view, (tlc[]) Arrays.copyOf(tlcArr, tlcArr.length));
    }
}
