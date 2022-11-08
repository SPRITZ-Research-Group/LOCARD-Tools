package jp.naver.line.android.activity.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.linecorp.legy.external.network.h;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.SquareContext;
import com.linecorp.square.common.bo.SquareFeatureBo;
import com.linecorp.square.group.SquareGroupUtils;
import com.linecorp.square.group.ui.main.presenter.impl.SquareMainPresenter;
import com.linecorp.voip.ui.paidcall.a;
import defpackage.fjx;
import defpackage.oyz;
import defpackage.ozh;
import defpackage.ozn;
import defpackage.qaa;
import defpackage.riv;
import defpackage.riw;
import defpackage.rmy;
import defpackage.rna;
import defpackage.rnb;
import defpackage.rnc;
import defpackage.rne;
import defpackage.rts;
import defpackage.rub;
import defpackage.rvr;
import defpackage.rwe;
import defpackage.rwh;
import defpackage.smi;
import defpackage.sua;
import defpackage.tfq;
import defpackage.tgt;
import defpackage.tyg;
import defpackage.tyh;
import defpackage.uej;
import defpackage.uoa;
import defpackage.uqk;
import defpackage.uuk;
import defpackage.vxd;
import defpackage.war;
import defpackage.wgx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.addfriend.AddFriendActivity;
import jp.naver.line.android.activity.callhistory.contacts.CallContactsActivity;
import jp.naver.line.android.activity.chatlist.ChatListEditActivity;
import jp.naver.line.android.activity.choosemember.ChooseMemberActivity;
import jp.naver.line.android.activity.search.SearchMainActivity;
import jp.naver.line.android.activity.search.o;
import jp.naver.line.android.activity.setting.SettingsBaseFragmentActivity;
import jp.naver.line.android.activity.timeline.TimeLineFragment;
import jp.naver.line.android.analytics.ga.bk;
import jp.naver.line.android.analytics.ga.bl;
import jp.naver.line.android.analytics.ga.bm;
import jp.naver.line.android.analytics.ga.bn;
import jp.naver.line.android.analytics.ga.bo;
import jp.naver.line.android.analytics.ga.bp;
import jp.naver.line.android.analytics.ga.bq;
import jp.naver.line.android.analytics.ga.br;
import jp.naver.line.android.analytics.ga.ci;
import jp.naver.line.android.analytics.ga.fc;
import jp.naver.line.android.bo.s;
import jp.naver.line.android.bridgejs.PortalSearchActivity;
import jp.naver.line.android.bridgejs.ak;
import jp.naver.line.android.bridgejs.as;
import jp.naver.line.android.bridgejs.r;
import jp.naver.line.android.common.view.header.Header;
import jp.naver.line.android.common.view.header.e;
import jp.naver.line.android.common.view.header.f;
import jp.naver.line.android.common.view.listview.PopupListView;
import jp.naver.line.android.l;
import jp.naver.line.android.model.g;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.bd;
import jp.naver.line.android.view.j;

public final class x {
    private final int a = -1;
    private Header b;
    private ViewGroup c;
    private Context d;
    private a e;
    private int f = 0;
    private int g = 0;
    private boolean h = true;
    private boolean i = true;
    private TimeLineFragment j;
    private boolean k;
    private boolean l;
    private y m;
    private rne n;
    private uej o;
    private uej p;
    private uej q;
    private final Handler r = new Handler();
    private ozn s;
    private boolean t;
    private boolean u;
    private final f v = new f();

    private static /* synthetic */ void a(Throwable th) throws Exception {
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSelectedTabChangedEvent(rnb rnb) {
        a a = rnb.a();
        if (this.b != null) {
            this.b.setTag(null);
        }
        this.v.e(e.LEFT, 0);
        int i = 8;
        this.v.d(e.LEFT, 8);
        this.v.e(e.MIDDLE, 0);
        this.v.d(e.MIDDLE, 8);
        this.v.d(e.RIGHT, 8);
        if (this.s != null) {
            this.s.dispose();
        }
        if (this.b != null) {
            switch (a) {
                case FRIEND:
                    k();
                    m();
                    if (this.b != null) {
                        this.v.c(e.MIDDLE, (int) R.drawable.header_ic_search);
                        this.v.a(e.MIDDLE, this.d.getString(R.string.search));
                        this.v.a(e.MIDDLE, new -$$Lambda$x$H1mWTIncsHF_D_AxfwQmbbWF5nI());
                        this.v.e(e.MIDDLE, 0);
                        f fVar = this.v;
                        e eVar = e.MIDDLE;
                        if (this.k) {
                            i = 0;
                        }
                        fVar.d(eVar, i);
                        this.v.a(e.MIDDLE, 0);
                    }
                    a(a);
                    break;
                case CHAT:
                    this.v.a((int) R.string.chatlist_title);
                    if (this.b != null) {
                        this.v.c(e.LEFT, (int) R.drawable.header_ic_addchat);
                        this.v.a(e.LEFT, this.d.getString(R.string.access_start_chat));
                        this.v.a(e.LEFT, new -$$Lambda$x$-3J_HTCJySErWNWtcTCTFS_aE18());
                        this.v.e(e.LEFT, 0);
                    }
                    if (this.b != null) {
                        oyz.b(-$$Lambda$x$INLEagwCt0SNbm3rKNNFrdaM6I8.INSTANCE).b(qaa.b()).a(ozh.a()).d(new -$$Lambda$x$OJOIqNFDdHMBQxgo0nlm235sqdI());
                    }
                    q();
                    break;
                case TIMELINE:
                    this.v.a((int) R.string.tab_name_timeline);
                    if (!this.u) {
                        this.v.a(e.LEFT, 8);
                        a(e.MIDDLE);
                        if (this.b != null) {
                            this.v.c(e.RIGHT, (int) R.drawable.header_ic_home02);
                            this.v.a(e.RIGHT, this.d.getString(R.string.myhome_myhome));
                            this.v.a(e.RIGHT, new -$$Lambda$x$PNJt_iO37tEJBt4ta-6hZ3IqRYE());
                            this.v.d(e.RIGHT, 8);
                            break;
                        }
                    }
                    a(e.LEFT);
                    if (this.b != null) {
                        this.v.c(e.MIDDLE, (int) R.drawable.header_ic_home02);
                        this.v.a(e.MIDDLE, this.d.getString(R.string.myhome_myhome));
                        this.v.a(e.MIDDLE, new -$$Lambda$x$PNJt_iO37tEJBt4ta-6hZ3IqRYE());
                        this.v.e(e.MIDDLE, 0);
                    }
                    if (this.b != null) {
                        this.v.c(e.RIGHT, (int) R.drawable.header_ic_square);
                        this.v.a(e.RIGHT, this.d.getString(R.string.square_access_timelinetosquare));
                        this.v.a(e.RIGHT, new -$$Lambda$x$imU-xY25oM4FBB5d6ac20jqe8zM());
                        if (this.o != null) {
                            this.r.postDelayed(new -$$Lambda$x$y3xmpjxdxtTMT9364l4eaWwev2w(), 500);
                        }
                    }
                    c();
                    break;
                    break;
                case NEWS:
                    this.v.a(ak.a(this.b.getContext()));
                    if (this.b != null) {
                        if (uuk.a().settings.ac) {
                            this.v.c(e.MIDDLE, (int) R.drawable.header_ic_search);
                            this.v.a(e.MIDDLE, this.d.getString(R.string.search));
                            this.v.a(e.MIDDLE, new -$$Lambda$x$_YU_-SY3roQQFd-ECvc1CPzfgok());
                            this.v.e(e.MIDDLE, 0);
                            this.v.d(e.MIDDLE, this.k ? 0 : 8);
                            this.v.a(e.MIDDLE, 0);
                        } else {
                            this.v.a(e.MIDDLE, 8);
                        }
                    }
                    if (this.b != null) {
                        this.v.a(e.LEFT, 8);
                        Object f = ak.f();
                        if (TextUtils.isEmpty(f)) {
                            this.v.a(e.RIGHT, 8);
                        } else {
                            this.v.c(e.RIGHT, (int) R.drawable.header_ic_menu);
                            this.v.a(e.RIGHT, this.d.getString(R.string.access_news_tab_hamburger_menu));
                            this.v.a(e.RIGHT, new -$$Lambda$x$yhQ_nkf0wYhp5_HhcIxMG1Kx22g(this, f));
                            this.p = ak.a(this.d, this.r);
                            if (this.p != null) {
                                this.r.postDelayed(new -$$Lambda$x$blFPIxyE82zu8cQ3V4JZajArl3k(), 500);
                            }
                        }
                    }
                    if (ak.k() && this.b != null) {
                        this.v.c(e.LEFT, (int) R.drawable.selector_header_ic_notice);
                        this.v.a(e.LEFT, this.d.getString(R.string.notification_center_title));
                        this.v.a(e.LEFT, new -$$Lambda$x$XxIbA7kMKCILz6R0dzCJYkKjFnA());
                        break;
                    }
                case CALL:
                    this.v.a((int) R.string.tab_name_call);
                    if (this.b != null) {
                        this.v.c(e.LEFT, (int) R.drawable.header_ic_contact);
                        this.v.a(e.LEFT, this.d.getString(R.string.access_calltab_address));
                        this.v.a(e.LEFT, new -$$Lambda$x$9O4raMO7iGzER2AiW87iFLXXYUs());
                        this.v.e(e.LEFT, 0);
                    }
                    if (this.b != null) {
                        if (sua.g()) {
                            this.v.c(e.MIDDLE, (int) R.drawable.header_ic_keypad);
                            this.v.a(e.MIDDLE, this.d.getString(R.string.access_calltab_keypad));
                            this.v.a(e.MIDDLE, new -$$Lambda$x$x5AOte1Er9xIQRrd_v2sEIRjZek());
                            this.v.e(e.MIDDLE, 0);
                            this.v.a(e.MIDDLE, 0);
                        } else {
                            this.v.a(e.MIDDLE, 8);
                        }
                    }
                    r();
                    break;
                case MORE:
                    this.v.a((int) R.string.tab_name_more);
                    this.v.a(e.LEFT, 8);
                    this.v.a(e.MIDDLE, 8);
                    a(a);
                    break;
                case WALLET:
                    this.v.a((int) R.string.tab_name_wallet);
                    this.v.a(e.LEFT, 8);
                    this.v.a(e.MIDDLE, 8);
                    this.v.a(e.RIGHT, 8);
                    break;
            }
        }
        this.e = a;
        war.a();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onUpdatedChatUnreadMessageCount(rmy rmy) {
        this.t = rmy.a();
    }

    public static x a(Context context) {
        return context instanceof aa ? ((aa) context).e() : null;
    }

    protected final void a(ViewGroup viewGroup) {
        this.c = viewGroup;
        this.b = (Header) viewGroup.findViewById(R.id.header);
        this.v.b(this.b);
        this.v.b(false);
        this.d = this.b.getContext();
        this.o = new uej(this.d, tyg.IS_SEEN_SQUARE_TOOLTIP_ON_TIMELINE, R.string.square_timeline_button_tooltip, null);
        this.q = new uej(this.d, tyg.IS_SEEN_SETTING_TOOLTIP_ON_FRIEND, true, R.string.friends_setting_tooltip, null);
        ((LineApplication) this.d.getApplicationContext()).g().n();
        this.u = SquareFeatureBo.a(this.d);
    }

    protected final void a() {
        war.a();
        if (this.o != null) {
            this.o.a();
        }
        if (this.p != null) {
            this.p.a();
        }
        if (this.q != null) {
            this.q.a();
        }
    }

    private void k() {
        if (this.b != null) {
            if (this.f > 0) {
                Context a = this.d == null ? l.a() : this.d;
                if (a != null) {
                    this.v.a(a.getString(R.string.friends_num, new Object[]{String.valueOf(this.f)}));
                }
                return;
            }
            this.v.a((int) R.string.friend_header);
        }
    }

    private /* synthetic */ void m(View view) {
        l();
    }

    private void l() {
        tyh.b(tyg.SEARCH_ICON_NEW_FLAG, false);
        this.k = false;
        this.v.d(e.MIDDLE, 8);
        this.d.startActivity(SearchMainActivity.a(this.d, this.e == a.FRIEND ? o.FRIEND : o.CHAT));
    }

    private void a(e eVar) {
        if (this.b != null) {
            this.v.c(eVar, (int) R.drawable.selector_header_ic_notice);
            this.v.a(eVar, this.d.getString(R.string.notification_center_title));
            this.v.a(eVar, new -$$Lambda$x$qDvYWroAQI4XI6eq_HFnTb_xXOY());
            this.v.e(eVar, wgx.c());
        }
    }

    private /* synthetic */ void l(View view) {
        if (j.a(1)) {
            j();
        }
    }

    private void m() {
        if (this.b != null) {
            this.v.c(e.LEFT, (int) R.drawable.header_ic_addfriends);
            this.v.a(e.LEFT, this.d.getString(R.string.access_add_friends));
            this.v.a(e.LEFT, new -$$Lambda$x$1Cezs9JBGX9LshpfNZNljA4HM98());
            this.v.e(e.LEFT, this.g);
        }
    }

    private /* synthetic */ void k(View view) {
        this.d.startActivity(new Intent(this.d, AddFriendActivity.class));
        rub.b(vxd.FRIENDS_CLICK_RIGHT_TOP_ADD_IN_FRIENDS).a();
        rts.a().a(fc.FRIENDS_FRIEND_ADD);
        Map hashMap = new HashMap();
        hashMap.put("menu", "setting");
        hashMap.put("clickTarget", "add_friends");
        hashMap.put("screenname", "friends_list");
        rwh.a().a("line.friends.click", hashMap);
    }

    private /* synthetic */ void a(Integer num) throws Exception {
        if (num.intValue() <= 0) {
            this.v.a(e.LEFT, 8);
        }
    }

    private /* synthetic */ void j(View view) {
        rub.b(vxd.CHATS_CLICK_RIGHT_TOP_NEW_CHAT_IN_CHATS).a();
        Collection arrayList = new ArrayList();
        arrayList.add(uoa.g().a().m());
        this.d.startActivity(ChooseMemberActivity.b(this.d, arrayList));
    }

    private void n() {
        if (this.b != null) {
            this.v.c(e.MIDDLE, (int) R.drawable.header_ic_search);
            this.v.a(e.MIDDLE, this.d.getString(R.string.search));
            this.v.a(e.MIDDLE, new -$$Lambda$x$8jnrxClvUWYMydFoG9ShNXb6yCw());
            this.v.e(e.MIDDLE, 0);
            this.v.d(e.MIDDLE, this.k ? 0 : 8);
            this.v.a(e.MIDDLE, 0);
        }
    }

    private /* synthetic */ void i(View view) {
        l();
    }

    private void o() {
        if (this.b != null) {
            if (this.h) {
                this.v.c(e.RIGHT, (int) R.drawable.header_ic_more);
                this.v.a(e.RIGHT, this.d.getString(R.string.access_chat_room_more_open));
                this.v.a(e.RIGHT, new -$$Lambda$x$iOwbgYx7AwPOoc70n8d0m7trSQ8());
                this.v.d(e.RIGHT, 8);
                this.v.a(e.RIGHT, 0);
                a(z.CHAT);
                return;
            }
            this.v.a(e.RIGHT, 8);
        }
    }

    private /* synthetic */ void h(View view) {
        if (!p()) {
            rts.a().a(bn.a);
        }
        e();
    }

    private /* synthetic */ void a(Dialog dialog, LineApplication lineApplication, List list) throws Exception {
        dialog.dismiss();
        for (g v : list) {
            uqk.a().a(v.v());
        }
        lineApplication.a().a(riw.CHAT_LIST);
        lineApplication.a().a(riv.REFRESH_TALK_BADGE);
    }

    private void a(a aVar) {
        if (this.b != null) {
            this.v.c(e.RIGHT, (int) R.drawable.header_ic_setting);
            this.v.d(e.RIGHT, this.l ? 0 : 8);
            this.v.a(e.RIGHT, this.d.getString(R.string.access_settings));
            this.v.a(e.RIGHT, new -$$Lambda$x$da16qps5J-l1iLH9cNpJ0ldX_1M(this, aVar));
            if (aVar == a.FRIEND && this.q != null) {
                this.r.postDelayed(new -$$Lambda$x$7slFkvcwRF8DDXDJruR3NOLMFzM(), 500);
            }
        }
    }

    private /* synthetic */ void u() {
        if (this.e == a.FRIEND) {
            View b = this.v.b(e.RIGHT);
            if (b != null) {
                this.q.a(b, 0);
            }
        }
    }

    private /* synthetic */ void a(AdapterView adapterView, View view, int i, long j) {
        if (i == 0) {
            tfq tfq = new tfq(this.d);
            tfq.b((int) R.string.call_history_delete_all).b((int) R.string.call_history_delete_description).a((int) R.string.confirm, -$$Lambda$x$VYvc4fYpA0o-b3lPHjIAj7X13_U.INSTANCE).b((int) R.string.cancel, null);
            tfq.f();
        }
    }

    private void a(List<Pair<Integer, Integer>> list, OnItemClickListener onItemClickListener) {
        if (p()) {
            b();
            return;
        }
        if (!p()) {
            View b = this.v.b(e.RIGHT);
            if (b == null || b.getVisibility() == 0) {
                PopupListView popupListView = (PopupListView) this.c.findViewById(R.id.main_popup_list);
                if (popupListView != null) {
                    popupListView.setVisibility(0);
                    popupListView.setCloseWithClick(true);
                    popupListView.a();
                    popupListView.a((List) list);
                    popupListView.setOnItemClickListener(new -$$Lambda$x$cdaicpzjAoiOPF-V7v0-rDMpPmY(onItemClickListener, popupListView));
                    popupListView.setTag(new Pair(list, onItemClickListener));
                }
            }
        }
    }

    private boolean p() {
        if (this.b == null) {
            return false;
        }
        PopupListView popupListView = (PopupListView) this.c.findViewById(R.id.main_popup_list);
        if (popupListView == null || popupListView.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    private static /* synthetic */ void a(OnItemClickListener onItemClickListener, PopupListView popupListView, AdapterView adapterView, View view, int i, long j) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(adapterView, view, i, j);
        }
        popupListView.setVisibility(8);
    }

    public final boolean b() {
        if (!p()) {
            return false;
        }
        ((PopupListView) this.c.findViewById(R.id.main_popup_list)).b();
        return true;
    }

    public final void a(boolean z) {
        this.h = z;
        if (this.e != null && this.e == a.CHAT) {
            q();
        }
    }

    public final void b(boolean z) {
        this.i = z;
        if (this.e != null && this.e == a.CALL) {
            r();
        }
    }

    private void q() {
        if (this.b != null) {
            n();
            o();
        }
    }

    private void a(View view) {
        if (j.a(1)) {
            fjx.a(this.d, jp.naver.myhome.android.model.x.TIMELINE);
        }
    }

    private /* synthetic */ void g(View view) {
        if (j.a(1)) {
            this.d.startActivity(SquareMainPresenter.a(this.d));
            this.v.d(e.RIGHT, 8);
            rts.a().a(fc.TIMELINE_SQUARE_CLICK);
            rwe.a(rvr.TIMELINE);
            SquareGroupUtils squareGroupUtils = SquareGroupUtils.a;
            SquareGroupUtils.a();
        }
    }

    private /* synthetic */ void t() {
        if (this.e == a.TIMELINE) {
            View b = this.v.b(e.RIGHT);
            if (b != null) {
                this.o.a(b, 0);
                this.o.a(b, 0);
            }
        }
    }

    public final void c() {
        if (this.u) {
            SquareContext g = ((LineApplication) this.d.getApplicationContext()).g();
            this.s = oyz.b(new -$$Lambda$x$BXlfPGGeJo6xekKU_HUBKjgUej4(g)).b(qaa.a(g.l().b())).a(ozh.a()).a(new -$$Lambda$x$RnRUmNgJHN7phSNWJhfmQZQgOIo(), -$$Lambda$x$X8rR4qD4E-v1D-go4y0zpnbL9T4.INSTANCE);
        }
    }

    private /* synthetic */ void a(Boolean bool) throws Exception {
        this.v.d(e.RIGHT, bool.booleanValue() ? 0 : 8);
    }

    private /* synthetic */ void a(java.lang.String r3, android.view.View r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.main.x.a(java.lang.String, android.view.View):void. bs: []
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
        r4 = jp.naver.line.android.urlscheme.e.a;	 Catch:{ Exception -> 0x000f }
        r4 = r2.d;	 Catch:{ Exception -> 0x000f }
        r3 = android.net.Uri.parse(r3);	 Catch:{ Exception -> 0x000f }
        r0 = 1;	 Catch:{ Exception -> 0x000f }
        r1 = jp.naver.line.android.urlscheme.SchemeServiceReferrer.Unknown.b;	 Catch:{ Exception -> 0x000f }
        jp.naver.line.android.urlscheme.e.a(r4, r3, r0, r1);	 Catch:{ Exception -> 0x000f }
        return;
    L_0x000f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.main.x.a(java.lang.String, android.view.View):void");
    }

    private /* synthetic */ void e(View view) {
        ((LineApplication) this.d.getApplicationContext()).a().a(r.NOTIFICATION_BUTTON);
    }

    private /* synthetic */ void s() {
        if (this.e == a.NEWS) {
            View b = this.v.b(e.RIGHT);
            if (b != null) {
                this.p.a(b, 5);
            }
        }
    }

    private /* synthetic */ void d(View view) {
        this.d.startActivity(new Intent(this.d, CallContactsActivity.class));
        rts.a().a(fc.CALLS_CONTACTS_CONTACTS);
    }

    private /* synthetic */ void c(View view) {
        this.d.startActivity(a.a(this.d, "", "", false));
        rts.a().a(fc.CALLS_KEYPAD_KEYPAD);
    }

    private void r() {
        if (this.b != null) {
            if (this.i) {
                this.v.c(e.RIGHT, (int) R.drawable.header_ic_more);
                this.v.a(e.RIGHT, this.d.getString(R.string.access_chat_room_more_open));
                this.v.a(e.RIGHT, new -$$Lambda$x$GmtVSy9fiYtKUVwE0MIaMe1oQIM());
                this.v.d(e.RIGHT, 8);
                this.v.a(e.RIGHT, 0);
                a(z.CALL);
                return;
            }
            this.v.a(e.RIGHT, 8);
        }
    }

    private /* synthetic */ void b(View view) {
        e();
    }

    public final void a(int i) {
        this.f = Math.max(0, i);
        if (this.e != null && this.e == a.FRIEND) {
            k();
            m();
            a(this.e);
        }
    }

    public final void b(int i) {
        this.g = i;
        if (this.b != null && this.e != null && this.e == a.FRIEND) {
            this.v.e(e.LEFT, this.g);
        }
    }

    public final void a(TimeLineFragment timeLineFragment) {
        if (this.j != timeLineFragment) {
            war.a();
        }
        this.j = timeLineFragment;
    }

    public final void b(TimeLineFragment timeLineFragment) {
        if (this.j == timeLineFragment) {
            a(null);
        }
    }

    public final void d() {
        if (this.b != null && this.e != null && this.e == a.TIMELINE) {
            this.v.e(e.LEFT, war.b() ? 0 : wgx.c());
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSearchIconNewFlagLoaded(rna rna) {
        this.k = rna.a();
        if (this.e == a.FRIEND || this.e == a.CHAT) {
            this.v.d(e.MIDDLE, this.k ? 0 : 8);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onNotificationButtonStatusChange(as asVar) {
        this.v.d(e.LEFT, asVar.a() ? 0 : 8);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSettingIconNewFlagUpdate(rnc rnc) {
        int i = 0;
        switch (rnc) {
            case LOAD:
                this.l = tyh.a(tyg.MORE_HEADER_SETTING_NEW_FLAG, Boolean.FALSE).booleanValue();
                break;
            case UPDATE:
                this.l = smi.c() > 0;
                tyh.a(tyg.MORE_HEADER_SETTING_NEW_FLAG, this.l);
                break;
        }
        if (this.e == a.MORE || this.e == a.FRIEND) {
            f fVar = this.v;
            e eVar = e.RIGHT;
            if (!this.l) {
                i = 8;
            }
            fVar.d(eVar, i);
        }
    }

    public final void e() {
        if (this.b != null) {
            Object tag = this.b.getTag();
            if (tag instanceof z) {
                z zVar = (z) tag;
                List arrayList = new ArrayList();
                switch (zVar) {
                    case CHAT:
                        arrayList.add(new Pair(Integer.valueOf(-1), Integer.valueOf(R.string.chat_more_editmessage)));
                        arrayList.add(new Pair(Integer.valueOf(-1), Integer.valueOf(R.string.chat_sorting_option)));
                        if (this.t) {
                            arrayList.add(new Pair(Integer.valueOf(-1), Integer.valueOf(R.string.mark_all_as_read)));
                        }
                        a(arrayList, new -$$Lambda$x$neADCRYVq-LfCU1ur_y4oT2dO0Q());
                        return;
                    case CALL:
                        arrayList.add(new Pair(Integer.valueOf(-1), Integer.valueOf(R.string.call_history_delete)));
                        a(arrayList, new -$$Lambda$x$OO9lcm_pougmKlhLSacm9SZmGO8());
                        break;
                }
            }
        }
    }

    public final void a(float f, float f2) {
        if (p()) {
            Object obj;
            int i = (int) f;
            int i2 = (int) f2;
            if (this.b != null) {
                View view = (PopupListView) this.c.findViewById(R.id.main_popup_list);
                View b = this.v.b(e.RIGHT);
                if (b == null || a(view, i, i2) || a(b, i, i2)) {
                    obj = null;
                    if (obj != null) {
                        e();
                    }
                }
            }
            obj = 1;
            if (obj != null) {
                e();
            }
        }
    }

    private static boolean a(View view, int i, int i2) {
        Rect rect = new Rect();
        if (view != null) {
            view.getHitRect(rect);
        }
        return rect.contains(i, i2);
    }

    private void a(z zVar) {
        if (this.r != null) {
            this.b.setTag(zVar);
        }
    }

    public final void f() {
        if (this.b != null) {
            bd.a(this.d, this.b);
            b();
        }
    }

    public final void g() {
        CharSequence[] charSequenceArr = new String[]{this.d.getString(R.string.chat_sorting_option_time), this.d.getString(R.string.chat_sorting_option_unread), this.d.getString(R.string.chat_sorting_option_favorite)};
        int a = tyh.a(tyg.CHATHISTOY_SORTING_KEY, 0);
        if (a >= 3) {
            tyh.c(tyg.CHATHISTOY_SORTING_KEY, 0);
            a = 0;
        }
        new tfq(this.d).d().a(charSequenceArr, a, new -$$Lambda$x$5uOspLFMqB3-E4rcrF1bU8NVJjU(this, a)).f();
    }

    private /* synthetic */ void a(int i, DialogInterface dialogInterface, int i2) {
        ci ciVar;
        switch (i2) {
            case 1:
                ciVar = br.a;
                break;
            case 2:
                ciVar = bp.a;
                break;
            default:
                ciVar = bq.a;
                break;
        }
        rts.a().a(ciVar);
        if (i2 != i) {
            tyh.b(tyg.CHATHISTOY_SORTING_KEY, i2);
            if (this.m != null) {
                this.m.a();
            }
            dialogInterface.dismiss();
        }
    }

    public final void a(y yVar) {
        this.m = yVar;
    }

    public final f h() {
        return this.v;
    }

    public final void a(rne rne) {
        this.n = rne;
    }

    public final rne i() {
        return this.n;
    }

    public final void j() {
        if (this.j != null) {
            e eVar = this.u ? e.LEFT : e.MIDDLE;
            this.v.e(eVar, 0);
            war.b(this.j.e);
            this.v.b(eVar, true);
        }
    }

    private /* synthetic */ void f(View view) {
        this.k = false;
        this.v.d(e.MIDDLE, 8);
        if (h.a(this.d)) {
            this.d.startActivity(PortalSearchActivity.a(this.d));
            rts.a().a(fc.PORTAL_SEARCH_SEARCH);
            return;
        }
        tgt.a((int) R.string.channel_error_loadfail);
    }

    private /* synthetic */ void b(AdapterView adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                rub.b(vxd.CHATS_CLICK_EDIT_BY_ANDROID_OPTION_EDIT_IN_CHATS).a();
                rts.a().a(bk.a);
                this.d.startActivity(new Intent(this.d, ChatListEditActivity.class));
                return;
            case 1:
                rts.a().a(bo.a);
                g();
                return;
            case 2:
                rts.a().a(bl.a);
                new tfq(this.d).b((int) R.string.mark_all_as_read_dialog_description).a((int) R.string.mark_all_as_read_ok, new -$$Lambda$x$5EBYAt4Q18pzI_KcmzyRP_g5DhM()).b((int) R.string.cancel, null).f();
                break;
        }
    }

    private /* synthetic */ void a(a aVar, View view) {
        if (aVar == a.FRIEND) {
            if (s.c()) {
                rts.a().a(fc.FRIENDS_FRIEND_SETTINGS_WALLET_COUNTRY);
            } else {
                rts.a().a(fc.FRIENDS_FRIEND_SETTINGS);
            }
        } else if (aVar == a.MORE) {
            rts.a().a(fc.MORETAB_SETTINGS_SETTINGS);
        }
        boolean z = this.l;
        Map hashMap;
        if (aVar == a.FRIEND) {
            hashMap = new HashMap();
            hashMap.put("menu", "setting");
            hashMap.put("clickTarget", "setting");
            hashMap.put("newbadge", z ? "y" : "n");
            hashMap.put("screenname", "friends_list");
            rwh.a().a("line.friends.click", hashMap);
        } else if (aVar == a.MORE) {
            hashMap = new HashMap();
            hashMap.put("menu", "setting");
            hashMap.put("clickTarget", "setting");
            hashMap.put("newbadge", z ? "y" : "n");
            rwh.a().a("line.more.menu.click", hashMap);
        }
        int i = 0;
        this.l = false;
        tyh.b(tyg.MORE_HEADER_SETTING_NEW_FLAG, false);
        f fVar = this.v;
        e eVar = e.RIGHT;
        if (!this.l) {
            i = 8;
        }
        fVar.d(eVar, i);
        this.d.startActivity(new Intent(this.d, SettingsBaseFragmentActivity.class).putExtra("extra_id", 3));
    }

    private /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        rts.a().a(bm.a);
        Context context = this.d;
        CharSequence string = this.d.getString(R.string.loading);
        Dialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(string);
        progressDialog.setProgressStyle(0);
        progressDialog.show();
        LineApplication lineApplication = (LineApplication) this.d.getApplicationContext();
        lineApplication.f().a(false).a().b().b(qaa.a(at.b())).a(ozh.a()).d(new -$$Lambda$x$Wqy-IbNbJaMAbX4VRvbckjKaiW8(this, progressDialog, lineApplication));
    }
}
