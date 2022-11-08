package jp.naver.line.android.activity.chathistory.list;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.linecorp.chathistory.report.data.AbuseReportRequest;
import com.linecorp.chathistory.report.data.AbuseReportRequest.ReportChat;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acqr;
import defpackage.acrx;
import defpackage.acry;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuc;
import defpackage.acuo;
import defpackage.addc;
import defpackage.fab;
import defpackage.fac;
import defpackage.mnt;
import defpackage.rhe;
import defpackage.rhf;
import defpackage.rts;
import defpackage.srq;
import defpackage.stl;
import defpackage.tkq;
import defpackage.tlb;
import defpackage.tlc;
import defpackage.tlt;
import defpackage.tlu;
import defpackage.usm;
import defpackage.usn;
import defpackage.vif;
import java.util.Arrays;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.cs;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.analytics.ga.fc;
import jp.naver.line.android.customview.thumbnail.ThumbImageView;
import jp.naver.line.android.customview.thumbnail.f;
import jp.naver.line.android.model.cz;
import jp.naver.line.android.model.g;
import jp.naver.line.android.model.h;
import jp.naver.line.android.util.dv;
import kotlin.Metadata;
import kotlin.e;
import kotlin.y;
import org.apache.http.HttpStatus;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 ]2\u00020\u0001:\u0004[\\]^B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u00108\u001a\u000209H\u0002J\u0012\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u000100H\u0002J\u0006\u0010=\u001a\u000209J\b\u0010>\u001a\u000209H\u0002J \u0010?\u001a\u0002092\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\b\u0010C\u001a\u0004\u0018\u00010DJ\u0010\u0010E\u001a\u0002092\u0006\u0010F\u001a\u00020\fH\u0002J\u0010\u0010G\u001a\u0002092\u0006\u0010F\u001a\u00020\fH\u0002J\u0010\u0010H\u001a\u0002092\u0006\u0010F\u001a\u00020\fH\u0002J\u0010\u0010I\u001a\u0002092\u0006\u0010J\u001a\u00020KH\u0007J\u000e\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020NJ\u0010\u0010O\u001a\u0002092\u0006\u0010F\u001a\u00020\fH\u0002J\u0010\u0010P\u001a\u0002092\u0006\u0010F\u001a\u00020\fH\u0002J\u0010\u0010Q\u001a\u0002092\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010R\u001a\u000209H\u0002J\u0010\u0010S\u001a\u0002092\u0006\u0010M\u001a\u00020NH\u0002J\b\u0010T\u001a\u000209H\u0002J\b\u0010U\u001a\u000209H\u0002J\u000e\u0010V\u001a\u0002092\u0006\u0010W\u001a\u00020\tJ\b\u0010X\u001a\u000209H\u0002J\b\u0010Y\u001a\u000209H\u0002J\b\u0010Z\u001a\u000209H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u0019\u0010\u000eR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001e\u0010\u000eR\u001b\u0010 \u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\"\u0010\u0010\u001a\u0004\b!\u0010\u0014R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b)\u0010\u0010\u001a\u0004\b(\u0010\u000eR!\u0010*\u001a\u00020\f8BX\u0002¢\u0006\u0012\n\u0004\b.\u0010\u0010\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010\u000eR\u0010\u0010/\u001a\u0004\u0018\u000100X\u000e¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0002¢\u0006\f\n\u0004\b5\u0010\u0010\u001a\u0004\b3\u00104R\u000e\u00106\u001a\u000207X\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Ljp/naver/line/android/activity/chathistory/list/ContactInstructionViewController;", "", "activity", "Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;", "viewStub", "Landroid/view/ViewStub;", "policyAgreementBo", "Ljp/naver/line/android/policyagreement/PolicyAgreementBo;", "latestTheme", "Ljp/naver/line/android/common/theme/ThemeManager;", "(Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;Landroid/view/ViewStub;Ljp/naver/line/android/policyagreement/PolicyAgreementBo;Ljp/naver/line/android/common/theme/ThemeManager;)V", "addButton", "Landroid/view/View;", "getAddButton", "()Landroid/view/View;", "addButton$delegate", "Lkotlin/Lazy;", "blockUnblockButton", "Landroid/widget/TextView;", "getBlockUnblockButton", "()Landroid/widget/TextView;", "blockUnblockButton$delegate", "buddyDetailDto", "Ljp/naver/line/android/buddy/BuddyDetailDto;", "buttonArea", "getButtonArea", "buttonArea$delegate", "chatData", "Ljp/naver/line/android/model/ChatData;", "descArea", "getDescArea", "descArea$delegate", "descText", "getDescText", "descText$delegate", "inviterHighlightColorData", "Ljp/naver/line/android/common/theme/ThemeElementColorData;", "lazyRootView", "Lkotlin/Lazy;", "reportButton", "getReportButton", "reportButton$delegate", "rootView", "rootView$annotations", "()V", "getRootView", "rootView$delegate", "targetContact", "Ljp/naver/line/android/model/UserData;", "thumbView", "Ljp/naver/line/android/customview/thumbnail/ThumbImageView;", "getThumbView", "()Ljp/naver/line/android/customview/thumbnail/ThumbImageView;", "thumbView$delegate", "viewType", "Ljp/naver/line/android/activity/chathistory/list/ContactInstructionViewController$ViewType;", "addContactAfterCheckingAgreement", "", "getContactName", "", "contactDto", "hide", "initializeClickListeners", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onAddBtnClick", "view", "onBlockBtnClick", "onBlockUnblockClick", "onBuddyDetailLoaded", "e", "Ljp/naver/line/android/buddy/event/BuddyDetailLoadedEvent;", "onChatHistoryContextUpdated", "chatHistoryContext", "Ljp/naver/line/android/activity/chathistory/ChatHistoryContext;", "onReportSpammerBtnClick", "onUnblockBtnClick", "updateBuddyData", "updateButtons", "updateChatData", "updateDescription", "updateDescriptionForContact", "updateTheme", "theme", "updateThumbnail", "updateView", "updateViewType", "BlockContactCallback", "ButtonType", "Companion", "ViewType", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class n {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(n.class), "rootView", "getRootView()Landroid/view/View;")), acso.a(new acsi(acso.a(n.class), "descArea", "getDescArea()Landroid/view/View;")), acso.a(new acsi(acso.a(n.class), "thumbView", "getThumbView()Ljp/naver/line/android/customview/thumbnail/ThumbImageView;")), acso.a(new acsi(acso.a(n.class), "descText", "getDescText()Landroid/widget/TextView;")), acso.a(new acsi(acso.a(n.class), "buttonArea", "getButtonArea()Landroid/view/View;")), acso.a(new acsi(acso.a(n.class), "blockUnblockButton", "getBlockUnblockButton()Landroid/widget/TextView;")), acso.a(new acsi(acso.a(n.class), "addButton", "getAddButton()Landroid/view/View;")), acso.a(new acsi(acso.a(n.class), "reportButton", "getReportButton()Landroid/view/View;"))};
    public static final q b = new q();
    private static final tlc[] t;
    private final e<View> c;
    private final e d;
    private final e e;
    private final e f;
    private final e g;
    private final e h;
    private final e i;
    private final e j;
    private final e k;
    private cz l;
    private srq m;
    private g n;
    private r o;
    private final tkq p;
    private final ChatHistoryActivity q;
    private final usn r;
    private tlt s;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrx implements acqr<View, y> {
        a(n nVar) {
            super(1, nVar);
        }

        public final String getName() {
            return "onBlockUnblockClick";
        }

        public final acuc getOwner() {
            return acso.a(n.class);
        }

        public final String getSignature() {
            return "onBlockUnblockClick(Landroid/view/View;)V";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            n.a((n) this.receiver, (View) obj);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "invoke"}, k = 3, mv = {1, 1, 13})
    final class b extends acrx implements acqr<View, y> {
        b(n nVar) {
            super(1, nVar);
        }

        public final String getName() {
            return "onAddBtnClick";
        }

        public final acuc getOwner() {
            return acso.a(n.class);
        }

        public final String getSignature() {
            return "onAddBtnClick(Landroid/view/View;)V";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            n.a((n) this.receiver);
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrx implements acqr<View, y> {
        c(n nVar) {
            super(1, nVar);
        }

        public final String getName() {
            return "onReportSpammerBtnClick";
        }

        public final acuc getOwner() {
            return acso.a(n.class);
        }

        public final String getSignature() {
            return "onReportSpammerBtnClick(Landroid/view/View;)V";
        }

        public final /* synthetic */ Object invoke(Object obj) {
            n.b((n) this.receiver);
            return y.a;
        }
    }

    public n(ChatHistoryActivity chatHistoryActivity, ViewStub viewStub, byte b) {
        this(chatHistoryActivity, viewStub);
    }

    private final View b() {
        return (View) this.d.d();
    }

    private final View c() {
        return (View) this.e.d();
    }

    private final ThumbImageView d() {
        return (ThumbImageView) this.f.d();
    }

    private final TextView e() {
        return (TextView) this.g.d();
    }

    private final View f() {
        return (View) this.h.d();
    }

    private final TextView g() {
        return (TextView) this.i.d();
    }

    private final View h() {
        return (View) this.j.d();
    }

    private final View i() {
        return (View) this.k.d();
    }

    private n(ChatHistoryActivity chatHistoryActivity, ViewStub viewStub, usn usn, tlt tlt) {
        this.q = chatHistoryActivity;
        this.r = usn;
        this.s = tlt;
        this.c = dv.a(viewStub, dv.a);
        this.d = this.c;
        this.e = dv.a(this.c, R.id.chathistory_header_for_spammer_area, dv.a);
        this.f = dv.a(this.c, R.id.chathistory_header_for_spammer_thumb, dv.a);
        this.g = dv.a(this.c, R.id.chathistory_header_for_spammer_desc, dv.a);
        this.h = dv.a(this.c, R.id.chathistory_header_for_spammer_button_area, dv.a);
        this.i = dv.a(this.c, R.id.chathistory_header_for_spammer_block_btn, dv.a);
        this.j = dv.a(this.c, R.id.chathistory_header_for_spammer_add_btn, dv.a);
        this.k = dv.a(this.c, R.id.chathistory_header_for_spammer_spam_btn, dv.a);
        this.o = r.NO_INSTRUCTION;
        tlu tlu = tlt.b;
        tlt a = tlu.a();
        vif vif = vif.g;
        tlb[] a2 = vif.a();
        this.p = a.c((tlb[]) Arrays.copyOf(a2, a2.length)).h();
        n nVar = this;
        g().setOnClickListener(new t(new a(nVar)));
        h().setOnClickListener(new t(new b(nVar)));
        i().setOnClickListener(new t(new c(nVar)));
        a(this.s);
        k();
    }

    private /* synthetic */ n(ChatHistoryActivity chatHistoryActivity, ViewStub viewStub) {
        usn usn = new usn();
        tlu tlu = tlt.b;
        this(chatHistoryActivity, viewStub, usn, tlu.a());
    }

    public final void a(tlt tlt) {
        this.s = tlt;
        tlt = this.s;
        View b = b();
        tlc[] tlcArr = t;
        tlt.a(b, (tlc[]) Arrays.copyOf(tlcArr, tlcArr.length));
    }

    public final void a() {
        mnt.a(b(), false);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onBuddyDetailLoaded(stl stl) {
        this.m = stl.a();
        j();
        k();
    }

    private final void j() {
        if (this.n == null || this.l == null) {
            this.o = r.NO_INSTRUCTION;
            return;
        }
        cz czVar = this.l;
        h hVar = null;
        boolean a = acry.a(czVar != null ? Boolean.valueOf(czVar.f()) : null, Boolean.TRUE);
        srq srq = this.m;
        if (acry.a(srq != null ? Boolean.valueOf(srq.a(a)) : null, Boolean.TRUE)) {
            this.o = r.BUDDY_WITH_STATUS_MESSAGE;
        } else if (a) {
            this.o = r.NO_INSTRUCTION;
        } else {
            r rVar;
            g gVar = this.n;
            if (gVar != null) {
                hVar = gVar.d();
            }
            if (hVar == h.ROOM) {
                rVar = r.ROOM_WITH_NON_FRIEND_INVITEE;
            } else {
                rVar = r.SINGLE_CHAT_WITH_NON_FRIEND;
            }
            this.o = rVar;
        }
    }

    private final void k() {
        if (this.o == r.NO_INSTRUCTION) {
            a();
            return;
        }
        a(this.s);
        b();
        b().setVisibility(0);
        o();
        n();
        l();
    }

    private final void l() {
        if (this.o.equals(r.BUDDY_WITH_STATUS_MESSAGE)) {
            mnt.a(c(), true);
            TextView e = e();
            srq srq = this.m;
            e.setText(srq != null ? srq.p() : null);
        } else if (this.o.b() != -1) {
            mnt.a(c(), true);
            m();
        } else {
            mnt.a(c(), false);
        }
    }

    private final void m() {
        String a = a(this.l);
        CharSequence string = this.q.getString(R.string.chathistory_guide_caution_spammer_for_group, new Object[]{a});
        SpannableString spannableString = new SpannableString(string);
        int a2 = addc.a(string, a, 0, false, 6);
        int length = a.length() + a2;
        tkq tkq = this.p;
        spannableString.setSpan(new ForegroundColorSpan(tkq != null ? tkq.b() : androidx.core.content.a.c(this.q, R.color.white)), a2, length, 17);
        e().setText(spannableString);
    }

    private final void n() {
        mnt.a((View) d(), this.o.equals(r.ROOM_WITH_NON_FRIEND_INVITEE));
        if (mnt.a(d())) {
            ThumbImageView d = d();
            cz czVar = this.l;
            String str = null;
            String a = czVar != null ? czVar.a() : null;
            cz czVar2 = this.l;
            if (czVar2 != null) {
                str = czVar2.d();
            }
            d.setProfileImage(a, str, f.FRIEND_LIST);
        }
    }

    private final void o() {
        View f = f();
        cz czVar = this.l;
        Object obj = null;
        mnt.a(f, acry.a(czVar != null ? Boolean.valueOf(czVar.f()) : null, Boolean.TRUE) ^ 1);
        if (mnt.a(f())) {
            cz czVar2 = this.l;
            if (czVar2 != null) {
                obj = Boolean.valueOf(czVar2.g());
            }
            boolean a = acry.a(obj, Boolean.TRUE);
            g().setTag(a ? p.UNBLOCK : p.BLOCK);
            g().setText(a ? R.string.unblock : R.string.block);
            mnt.a(h(), a ^ 1);
            mnt.a(i(), this.o.a());
        }
    }

    private final String a(cz czVar) {
        if (czVar != null) {
            String b = czVar.b();
            if (b != null) {
                return b;
            }
        }
        return this.q.getString(R.string.unknown_name);
    }

    private final void p() {
        g gVar = this.n;
        if (gVar != null) {
            cz czVar = this.l;
            if (czVar != null) {
                if (gVar.d() == h.SINGLE && czVar.g()) {
                    rhf.a(this.q, czVar.a(), czVar.b());
                } else {
                    rhf.b(this.q, czVar.a(), czVar.b());
                }
            }
        }
    }

    public final void a(int i, int i2) {
        if (i == HttpStatus.SC_CREATED && i2 == -1) {
            p();
        }
    }

    static {
        r0 = new tlc[5];
        r0[0] = new tlc(R.id.chathistory_header_container, vif.a);
        r0[1] = new tlc(R.id.chathistory_header_for_spammer_desc, vif.e);
        r0[2] = new tlc(R.id.chathistory_header_for_spammer_add_btn, vif.c);
        r0[3] = new tlc(R.id.chathistory_header_for_spammer_block_btn, vif.c);
        r0[4] = new tlc(R.id.chathistory_header_for_spammer_spam_btn, vif.c);
        t = r0;
    }

    public final void a(z zVar) {
        this.n = zVar.i();
        g gVar = this.n;
        cz czVar = null;
        h d = gVar != null ? gVar.d() : null;
        if (d == null) {
            this.l = null;
            this.o = r.NO_INSTRUCTION;
        } else {
            switch (s.a[d.ordinal()]) {
                case 1:
                    czVar = zVar.s();
                    break;
                case 2:
                    czVar = zVar.v();
                    break;
            }
            this.l = czVar;
            j();
        }
        k();
    }

    public static final /* synthetic */ void a(n nVar, View view) {
        cz czVar;
        fac fac;
        rhe a;
        fab fab;
        if (view.getTag() == p.BLOCK) {
            if (!nVar.q.isFinishing()) {
                czVar = nVar.l;
                if (czVar != null) {
                    fac = fac.BLOCK;
                    a = nVar.q.a((rhe) new o(nVar.q, czVar), czVar, fac);
                    fab = fab.a;
                    fab.a(czVar, a, fac);
                } else {
                    return;
                }
            }
            return;
        }
        if (!nVar.q.isFinishing()) {
            czVar = nVar.l;
            if (czVar != null) {
                fac = fac.UNBLOCK;
                a = nVar.q.a(new rhe(nVar.q), czVar, fac);
                fab = fab.a;
                fab.a(czVar, a, fac);
            }
        }
    }

    public static final /* synthetic */ void a(n nVar) {
        if (nVar.n != null && nVar.l != null && !nVar.q.isFinishing()) {
            rts.a().a(fc.FRIENDS_ADD_CHATROOM);
            cz czVar = nVar.l;
            if (czVar == null) {
                acry.a();
            }
            if (!czVar.i() || usn.a()) {
                nVar.p();
                return;
            }
            nVar.q.startActivityForResult(usm.a((Context) nVar.q), HttpStatus.SC_CREATED);
        }
    }

    public static final /* synthetic */ void b(n nVar) {
        if (!nVar.q.isFinishing()) {
            g gVar = nVar.n;
            if (gVar != null) {
                cz czVar = nVar.l;
                if (czVar != null) {
                    cs.a((Activity) nVar.q, (AbuseReportRequest) new ReportChat(gVar.v(), gVar.d(), czVar.a(), true, 16));
                }
            }
        }
    }
}
