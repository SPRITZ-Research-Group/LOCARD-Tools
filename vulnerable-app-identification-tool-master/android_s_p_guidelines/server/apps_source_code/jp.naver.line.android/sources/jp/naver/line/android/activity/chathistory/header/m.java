package jp.naver.line.android.activity.chathistory.header;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.chat.db.model.SquareChatDto;
import com.linecorp.square.chat.ui.view.home.SquareHomeActivity;
import defpackage.acqq;
import defpackage.cmi;
import defpackage.cmv;
import defpackage.fjx;
import defpackage.ozh;
import defpackage.qqe;
import defpackage.qti;
import defpackage.qtj;
import defpackage.qtl;
import defpackage.qts;
import defpackage.qtv;
import defpackage.qug;
import defpackage.quj;
import defpackage.qun;
import defpackage.quo;
import defpackage.qup;
import defpackage.qxm;
import defpackage.rck;
import defpackage.rcl;
import defpackage.rtj;
import defpackage.rub;
import defpackage.siz;
import defpackage.tlt;
import defpackage.tyg;
import defpackage.tyh;
import defpackage.uav;
import defpackage.uek;
import defpackage.uer;
import defpackage.ump;
import defpackage.uuk;
import defpackage.vxd;
import defpackage.vxe;
import defpackage.vxi;
import defpackage.vxj;
import defpackage.vxk;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.ChatHistoryActivity;
import jp.naver.line.android.activity.chathistory.call.groupcall.a;
import jp.naver.line.android.activity.chathistory.list.f;
import jp.naver.line.android.activity.chathistory.messageinput.x;
import jp.naver.line.android.activity.chathistory.p;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.analytics.ga.fc;
import jp.naver.line.android.bo.al;
import jp.naver.line.android.bo.g;
import jp.naver.line.android.db.main.model.ContactDto;
import jp.naver.line.android.db.main.model.am;
import jp.naver.line.android.model.cz;
import jp.naver.line.android.model.h;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.au;

public final class m {
    private final ChatHistoryActivity a;
    private final View b;
    private final p c;
    private final cmv<Boolean, Void> d;
    private final cmv<String, Boolean> e;
    private final g f;
    private final a g;
    private final qug h;
    private final y i;
    private final rcl j;
    private final q k;
    private tlt l;
    private b m;
    private vxj n = vxj.UNKNOWN;
    private vxk o = vxk.UNKNOWN;
    private boolean p;
    private qxm q;
    private final ab r;
    private final j s;
    private final acqq<Boolean> t;
    private final boolean u;
    private final boolean v;
    private final boolean w;

    public m(ChatHistoryActivity chatHistoryActivity, View view, qqe qqe, ump ump, p pVar, uuk uuk, q qVar, g gVar, String str, acqq<Boolean> acqq) {
        ChatHistoryActivity chatHistoryActivity2 = chatHistoryActivity;
        View view2 = view;
        uuk uuk2 = uuk;
        this.a = chatHistoryActivity2;
        this.b = view2;
        this.k = qVar;
        g gVar2 = gVar;
        this.f = gVar2;
        this.t = acqq;
        this.u = uuk2.settings.ba;
        this.v = uuk2.settings.aZ;
        this.w = uuk2.settings.bg;
        this.l = tlt.h();
        com.linecorp.rxeventbus.a h = chatHistoryActivity.h();
        com.linecorp.rxeventbus.a i = chatHistoryActivity.i();
        this.d = new n();
        this.e = new o();
        this.c = new p();
        this.g = new a(this, chatHistoryActivity, qqe, pVar, gVar2, view);
        this.h = new qug(chatHistoryActivity, (ViewStub) view2.findViewById(R.id.chathistory_header_append_viewstub), str);
        this.i = new y(i, view, ump, uuk, new -$$Lambda$m$NpwiCiCh2lr2BLUnD4C7AYXj73w());
        this.j = new rcl(chatHistoryActivity, h, view2);
        this.s = new j((Context) chatHistoryActivity);
        this.r = new ab(this.s);
        h();
        h.b(this);
        i.b(this);
    }

    private /* synthetic */ tlt t() {
        return this.l;
    }

    public final void a(boolean z) {
        this.i.a(z);
        if (!z) {
            f();
            this.h.a(false);
        }
    }

    private /* synthetic */ void a(View view) {
        rtj.a(fc.CHATROOM_VMENU_CLOSE);
        f();
    }

    private void q() {
        this.i.a();
        this.h.a(false);
        this.g.g();
    }

    private void b(z zVar) {
        if (zVar != null) {
            a a = a.a(zVar, this.f);
            if (a.a()) {
                this.g.a(zVar);
            }
            if (!this.a.u()) {
                this.i.a(a, this.f.e().b(zVar.k()));
            }
        }
    }

    public final void a(z zVar) {
        if (!this.a.u()) {
            this.i.a(a.a(zVar, this.f), this.f.e().b(zVar.k()));
        }
    }

    public final void a() {
        this.j.d();
    }

    public final void b() {
        this.j.a();
    }

    public final void c() {
        this.j.c();
    }

    private void c(z zVar) {
        h m = zVar.m();
        boolean z = true;
        boolean z2 = zVar.x() != null && zVar.x().h();
        boolean z3 = (zVar.i() == null || TextUtils.isEmpty(zVar.i().k())) ? false : true;
        if (zVar.s() == null || !zVar.s().i()) {
            z = false;
        }
        this.i.a(quj.a(m, z2, z3, z));
        this.i.e();
    }

    private void e(z zVar) {
        this.i.b(aa.a(zVar));
    }

    public final String d() {
        return this.i.d();
    }

    public final void e() {
        this.o = vxk.NONE;
        a(this.n, this.o);
    }

    private void a(vxj vxj, vxk vxk) {
        this.i.b(this.r.a(vxj, vxk));
    }

    public final boolean f() {
        if (!h()) {
            return this.g.g();
        }
        rtj.a(fc.CHATROOM_VMENU_CLOSE);
        return true;
    }

    public final boolean g() {
        return this.m != null && this.m.a();
    }

    public final boolean h() {
        this.i.a(x.CLOSED);
        boolean z = this.m != null && this.m.b();
        if (z) {
            this.a.D();
        }
        return z;
    }

    @SuppressLint({"CheckResult"})
    private void f(z zVar) {
        if (t.a(zVar, cmi.c(this.q)).b()) {
            if (this.m == null) {
                this.m = new b(this.a, ((ViewStub) this.b.findViewById(R.id.chathistory_option_layout_viewstub)).inflate(), this.k, this.l, this.t, this.u, this.v, this.w, new -$$Lambda$m$GOjvqsAAR4A71TuqUDm1qreBQwc());
            }
            this.i.a(x.OPEN);
            if (this.n.b()) {
                vxj vxj = this.n;
                if (vxj.b()) {
                    vxj = vxj.HALF;
                }
                this.n = vxj;
                CharSequence k = zVar.k();
                if (!TextUtils.isEmpty(k)) {
                    this.c.a((Object) k);
                }
            }
            if (this.o.a()) {
                vxk vxk = this.o;
                if (vxk.a()) {
                    vxk = vxk.HALF;
                }
                this.o = vxk;
                if (!TextUtils.isEmpty(zVar.k())) {
                    ((LineApplication) this.a.getApplication()).g().h().a(this.a.i(), new qtj(zVar)).a(ozh.a()).d();
                }
            }
            b bVar = this.m;
            vxj vxj2 = this.n;
            boolean z = false;
            boolean z2 = vxj2 == vxj.FULL || vxj2 == vxj.HALF;
            vxk vxk2 = this.o;
            if (vxk2 == vxk.FULL || vxk2 == vxk.HALF) {
                z = true;
            }
            if (bVar.a(zVar, z2, z)) {
                this.a.E();
            }
        }
    }

    private boolean s() {
        if (!this.i.b()) {
            return false;
        }
        if (this.m == null || !this.m.a()) {
            rtj.a(fc.CHATROOM_VMENU);
            x P = this.a.P();
            z b = this.a.f().b();
            if (!(this.a.L() || b == null || P == null)) {
                h m = b.m();
                if (m != null) {
                    rub.b(vxd.CHATS_CLICK_RIGHT_TOP_OPTION_IN_CHATROOM).a(vxe.CHATS_CHAT_TYPE, String.valueOf(uav.a(m).b().a())).a();
                }
                P.h();
                f(b);
            }
            return true;
        }
        rtj.a(fc.CHATROOM_VMENU_CLOSE);
        h();
        return false;
    }

    public final void a(qxm qxm) {
        z b = this.a.f().b();
        this.q = qxm;
        t a = t.a(b, cmi.b(qxm));
        this.i.a(a);
        if (a.a()) {
            g(b);
            return;
        }
        q();
        this.i.a(b.m() != null ? qxm.a(this.a, b.m()) : "");
    }

    private void g(z zVar) {
        this.r.a(zVar);
        if ((zVar != null ? zVar.m() : null) == null) {
            q();
            r();
            return;
        }
        quo quo = qun.a;
        this.i.a(quo.a(this.a, zVar.i(), zVar.p()));
        d(zVar);
        e(zVar);
        b(zVar);
        c(zVar);
        a(this.n, this.o);
        boolean z = true;
        this.i.c(zVar.o() ^ true);
        r();
        this.h.a(zVar);
        qug qug = this.h;
        if (!this.p || this.a.u()) {
            z = false;
        }
        qug.a(z);
    }

    private /* synthetic */ void c(String str) {
        vxi.a();
        this.a.runOnUiThread(new -$$Lambda$m$OBrGzTavHmRbjgQZezTVdYuVJpk(this, vxi.b(str)));
    }

    private /* synthetic */ void a(vxj vxj) {
        if (!this.a.isFinishing()) {
            this.n = vxj;
            a(vxj, this.o);
        }
    }

    private /* synthetic */ void b(java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.chathistory.header.m.b(java.lang.String):void. bs: []
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
        r2 = defpackage.wjh.e(r2);	 Catch:{ Exception -> 0x0017 }
        if (r2 == 0) goto L_0x000c;	 Catch:{ Exception -> 0x0017 }
    L_0x0006:
        r2 = r2.g;	 Catch:{ Exception -> 0x0017 }
        if (r2 == 0) goto L_0x000c;
    L_0x000a:
        r2 = 1;
        goto L_0x000d;
    L_0x000c:
        r2 = 0;
    L_0x000d:
        r0 = r1.d;
        r2 = java.lang.Boolean.valueOf(r2);
        r0.a(r2);
        return;
    L_0x0017:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.chathistory.header.m.b(java.lang.String):void");
    }

    private /* synthetic */ void a(vxk vxk) throws Exception {
        vxj vxj = this.n;
        this.o = vxk;
        a(vxj, vxk);
    }

    public final void a(String str) {
        this.e.a(this.d).a((Object) str);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onEmoticonButtonClicked(qtl qtl) {
        this.a.j();
        f();
    }

    public final void i() {
        if (this.m != null) {
            this.m.c();
        }
        this.g.h();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onCustomThemeUpdated(uer uer) {
        z b = this.a.f().b();
        if (uer.a(b != null ? b.l() : null)) {
            this.l = uer.a();
            this.i.e();
            if (this.m != null) {
                this.m.a(this.l);
            }
            this.j.a(this.l);
            this.h.a(this.l);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onContactUpdated(qti qti) {
        g(qti.a());
    }

    public final void j() {
        z b = this.a.f().b();
        cz t = b != null ? b.t() : null;
        if (t != null && t.i()) {
            au auVar = au.BASEACTIVITY;
            at.c().execute(new -$$Lambda$m$zS1TR38ZbLbqnH0aYVstTIji1Ak(this, t));
        }
    }

    private /* synthetic */ void a(jp.naver.line.android.model.cz r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.chathistory.header.m.a(jp.naver.line.android.model.cz):void. bs: []
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
        defpackage.qnp.a();	 Catch:{ Exception -> 0x0032 }
        r2 = r2.a();	 Catch:{ Exception -> 0x0032 }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0032 }
        if (r0 == 0) goto L_0x0019;	 Catch:{ Exception -> 0x0032 }
    L_0x000d:
        r2 = defpackage.uoa.g();	 Catch:{ Exception -> 0x0032 }
        r2 = r2.a();	 Catch:{ Exception -> 0x0032 }
        r2 = r2.m();	 Catch:{ Exception -> 0x0032 }
    L_0x0019:
        r0 = new whl;	 Catch:{ Exception -> 0x0032 }
        r0.<init>();	 Catch:{ Exception -> 0x0032 }
        r2 = defpackage.whl.d(r2);	 Catch:{ Exception -> 0x0032 }
        if (r2 != 0) goto L_0x0026;	 Catch:{ Exception -> 0x0032 }
    L_0x0024:
        r2 = 0;	 Catch:{ Exception -> 0x0032 }
        goto L_0x0028;	 Catch:{ Exception -> 0x0032 }
    L_0x0026:
        r2 = r2.a;	 Catch:{ Exception -> 0x0032 }
    L_0x0028:
        r0 = r1.d;	 Catch:{ Exception -> 0x0032 }
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ Exception -> 0x0032 }
        r0.a(r2);	 Catch:{ Exception -> 0x0032 }
        return;
    L_0x0032:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.chathistory.header.m.a(jp.naver.line.android.model.cz):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Subscribe(a = SubscriberType.MAIN)
    public final void onChatRoomUpdateRequest(qtj qtj) {
        z a = qtj.a();
        h hVar = null;
        Object k = a != null ? a.k() : null;
        h m = a != null ? a.m() : null;
        if (!(TextUtils.isEmpty(k) || m == null)) {
            switch (m) {
                case SINGLE:
                    cz s = a.s();
                    if (s != null) {
                        break;
                    }
                    break;
                case GROUP:
                    am x = a.x();
                    if (x != null) {
                        break;
                    }
                    break;
            }
        }
        if (a != null) {
            hVar = a.m();
        }
        if (hVar == h.GROUP || hVar == h.SINGLE || hVar == h.SQUARE_GROUP) {
            if (hVar == h.SINGLE) {
                cz s2 = a.s();
                if (s2 != null) {
                }
            }
            k = a.k();
            if (!TextUtils.isEmpty(k)) {
                if (hVar == h.SQUARE_GROUP) {
                    String k2 = a.i().k();
                    if (!TextUtils.isEmpty(k2)) {
                        a(k2);
                    }
                } else {
                    au auVar = au.BASEACTIVITY;
                    at.c().execute(new -$$Lambda$m$6HfC2DR9iCOHa8rkTUffPEJGJ7U(this, k));
                }
            }
        }
        b(a);
        if (a != null && a.m() == h.SQUARE_GROUP) {
            SquareChatDto squareChatDto = (SquareChatDto) a.i();
            if (squareChatDto.t()) {
                Object x2 = squareChatDto.x();
                if (!TextUtils.isEmpty(x2)) {
                    ((LineApplication) this.a.getApplication()).g().h().g(x2).a(ozh.a()).d(new -$$Lambda$m$54NV6sS8zhl23odbYSUJT7-96uA());
                }
            }
        }
        if (a == null) {
            this.i.a(t.DISABLED);
            return;
        }
        this.g.a(a);
        this.i.a(t.a(a, cmi.c(this.q)));
        this.j.a(rck.a(a.m()));
        if (g()) {
            f(a);
        }
        this.h.a(a);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onNotificationStateChanged(qts qts) {
        a(qts.b(), qts.a());
    }

    public final void a(String str, boolean z) {
        z b = this.a.f().b();
        if (b != null && TextUtils.equals(str, b.k())) {
            this.i.c(z);
            if (g()) {
                b.a(z);
                f(b);
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onGroupCallStatusChangedEvent(siz siz) {
        if (!TextUtils.isEmpty(siz.a())) {
            z b = this.a.f().b();
            if (TextUtils.equals(siz.a(), b != null ? b.l() : null)) {
                a(this.a.f().b());
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onE2EEUsableChanged(uek uek) {
        z b = this.a.f().b();
        if (uek.a().equals(b != null ? b.l() : null)) {
            this.h.a(b);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onHeaderUserActionEventReceived(qup qup) {
        int i = 1;
        cz czVar = null;
        z b;
        switch (qup) {
            case HEADER_MENU_BUTTON:
                s();
                return;
            case HOME_BUTTON:
                if (!this.a.L()) {
                    b = this.a.f().b();
                    if (b != null) {
                        czVar = b.t();
                    }
                    if (czVar != null) {
                        fjx.a(this.a, czVar.a(), jp.naver.myhome.android.model.x.TALKROOM_HOME, czVar.i());
                    }
                }
                return;
            case SQUARE_HOME_BUTTON:
                b = this.a.f().b();
                if (b != null && !this.a.L()) {
                    rtj.a(fc.CHATROOM_SQUARE_HOME);
                    jp.naver.line.android.model.g i2 = b.i();
                    this.a.startActivity(SquareHomeActivity.a(this.a, i2.k(), i2.v()));
                    return;
                }
                return;
            case NOTE_BUTTON:
                if (!this.a.L()) {
                    rub.b(vxd.CHATS_CLICK_NOTE_IN_CHATROOM).a();
                    rtj.a(fc.CHATROOM_NOTE);
                    this.a.O().a(0);
                    this.a.i().a(qtv.a);
                }
                return;
            case HEADER_TITLE:
                if (!this.a.u()) {
                    h n;
                    b = this.a.f().b();
                    if (b != null) {
                        n = b.n();
                    }
                    if (n != null) {
                        rub.b(vxd.CHATS_CLICK_TOP_TITLE_IN_CHATROOM).a(vxe.CHATS_CHAT_TYPE, String.valueOf(uav.a(n).b().a())).a();
                        if (this.h.a()) {
                            rtj.a(fc.CHATROOM_FRIENDNAME);
                            this.p ^= true;
                        }
                    }
                    return;
                }
                break;
            case CALL_BUTTON:
                if (!this.a.L()) {
                    b = this.a.f().b();
                    h m = b != null ? b.m() : null;
                    if (b != null) {
                        String k = b.k();
                    }
                    if (m == h.GROUP && !al.b(k)) {
                        i = 0;
                    }
                    if (i != 0) {
                        if (a.b(b)) {
                            tyh.a(tyg.CHATROOM_IS_GROUPCALL_NEW_FLAG, false);
                        } else if (m == h.SINGLE) {
                            rtj.a(fc.CHATROOM_FRIENDNAME_CALL);
                        }
                        a(b);
                        boolean c = this.g.c();
                        x P = this.a.P();
                        if (c && P != null) {
                            P.h();
                        }
                    }
                }
                return;
            case UP_BUTTON:
                if (!this.a.s()) {
                    this.a.t();
                    break;
                }
                break;
        }
    }

    public final void k() {
        this.j.a();
        this.s.a(false);
        a(this.n, this.o);
    }

    public final void l() {
        this.j.b();
    }

    public final void m() {
        s();
    }

    public final void n() {
        this.g.e();
        this.h.b();
    }

    public final void o() {
        this.g.d();
    }

    public final void p() {
        this.a.h().c(this);
        this.a.i().c(this);
        this.g.f();
        this.i.c();
    }

    public final void a(int i, int i2, Intent intent) {
        this.h.a(i, i2, intent);
    }

    private void r() {
        cmi e;
        f u = this.a.i != null ? this.a.i.u() : null;
        if (u == null || !u.r().b()) {
            e = cmi.e();
        } else {
            e = cmi.b(u.r().e());
        }
        if (e.a()) {
            a((qxm) e.b());
        }
    }

    private void d(z zVar) {
        cmi c;
        h m = zVar.m();
        cz s = zVar.s();
        if (m == h.SINGLE && s != null && s.i() && (s instanceof ContactDto)) {
            ContactDto contactDto = (ContactDto) s;
            c = cmi.c(jp.naver.line.android.customview.friend.m.a(contactDto.H(), contactDto.I()));
        } else {
            c = cmi.e();
        }
        this.i.a(c);
    }
}
