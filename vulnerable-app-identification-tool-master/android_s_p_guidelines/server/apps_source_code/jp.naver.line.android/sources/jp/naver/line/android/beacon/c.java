package jp.naver.line.android.beacon;

import android.content.Context;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import defpackage.che;
import defpackage.eoc;
import defpackage.eod;
import defpackage.sdy;
import defpackage.sdz;
import defpackage.sfl;
import defpackage.sfx;
import defpackage.sgb;
import defpackage.sgo;
import defpackage.sgq;
import defpackage.twj;
import defpackage.twm;
import defpackage.xle;
import defpackage.xnl;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import jp.naver.line.android.beacon.model.e;
import jp.naver.line.android.beacon.model.h;
import jp.naver.line.android.beacon.service.b;
import jp.naver.line.android.beacon.service.i;
import jp.naver.line.android.beacon.service.j;
import jp.naver.line.android.util.at;

public final class c {
    private final Context a;
    private final a b;
    private final sgb c;
    private final j d;
    private final b e;
    private final jp.naver.line.android.beacon.actionchain.a f;
    private final i g;
    private final sdz h;
    private final sdy i;
    private final Executor j;

    public c(Context context, a aVar) {
        this(context, aVar, new sgb(context), new com.linecorp.android.offlinelink.ble.api.b(context), com.linecorp.legy.external.network.a.a(), new sdz(), new che(context, aVar), at.b());
    }

    private c(Context context, a aVar, sgb sgb, com.linecorp.android.offlinelink.ble.api.b bVar, com.linecorp.legy.external.network.a aVar2, sdz sdz, che che, Executor executor) {
        che che2 = che;
        this(context, aVar, sgb, bVar, aVar2, new b(context, sgb), new jp.naver.line.android.beacon.actionchain.a(aVar), new sdy(aVar, che2), sdz, che2, executor);
    }

    private c(Context context, a aVar, sgb sgb, com.linecorp.android.offlinelink.ble.api.b bVar, com.linecorp.legy.external.network.a aVar2, b bVar2, jp.naver.line.android.beacon.actionchain.a aVar3, sdy sdy, sdz sdz, che che, Executor executor) {
        this(context, aVar, sgb, bVar, aVar2, new j(context), bVar2, aVar3, new i(), sdz, sdy, che, executor);
    }

    private c(Context context, a aVar, sgb sgb, com.linecorp.android.offlinelink.ble.api.b bVar, com.linecorp.legy.external.network.a aVar2, j jVar, b bVar2, jp.naver.line.android.beacon.actionchain.a aVar3, i iVar, sdz sdz, sdy sdy, che che, Executor executor) {
        this.a = context;
        this.b = aVar;
        this.c = sgb;
        this.d = jVar;
        this.e = bVar2;
        this.f = aVar3;
        this.g = iVar;
        this.h = sdz;
        this.i = sdy;
        this.j = executor;
        if (bVar2.b()) {
            bVar.a(new f(this, che));
            aVar2.a(new g());
        }
    }

    public final jp.naver.line.android.beacon.actionchain.a a() {
        return this.f;
    }

    public final sgb b() {
        return this.c;
    }

    public static void c() {
        new sfx().a(twj.a(twm.BEACON));
    }

    public final boolean d() {
        return a(this.a, this.c);
    }

    public static boolean a(Context context, sgb sgb) {
        return sgb.c().a() && com.linecorp.android.offlinelink.ble.api.a.a(context);
    }

    @Deprecated
    public final void a(boolean z) {
        this.c.a(z);
    }

    public final boolean e() {
        return this.c.b();
    }

    public final void f() {
        if (this.e.a()) {
            new d().a();
        } else {
            new e().a();
        }
    }

    public final void a(String str, long j, e eVar, String str2, Long l, int i) {
        this.i.a(str, j, eVar, str2, l, i);
    }

    public final sfl a(String str, String str2) {
        return this.i.a(str, str2);
    }

    public final sfl a(e eVar, String str) {
        return this.i.a(eVar, str);
    }

    public final void a(long j, sfl sfl) {
        this.i.a(j, sfl);
    }

    public final void a(long j, String str, boolean z) {
        this.i.a(j, str, z);
    }

    public final void a(long j, sfl sfl, UUID uuid, UUID uuid2) {
        this.i.a(j, sfl, uuid, uuid2);
    }

    public final void b(long j, sfl sfl, UUID uuid, UUID uuid2) {
        this.i.b(j, sfl, uuid, uuid2);
    }

    public final void a(long j, sfl sfl, UUID uuid, UUID uuid2, byte[] bArr) {
        this.i.a(j, sfl, uuid, uuid2, bArr);
    }

    public final void a(String str, long j) {
        this.i.a(str, j);
    }

    public final i g() {
        return this.g;
    }

    public final sdz h() {
        return this.h;
    }

    public final List<h> i() {
        return this.g.d();
    }

    public final h a(e eVar) {
        return this.g.a(eVar);
    }

    @Subscribe(a = SubscriberType.BACKGROUND_STICKY)
    public final void onActivityLifecycleEvent(eoc eoc) {
        eod a = eoc.a();
        if (a != eod.STOPPED && a != eod.RESUMED) {
            return;
        }
        if (this.e.a()) {
            this.d.a();
        } else {
            this.d.b();
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onButtonBeaconDetected(sgo sgo) {
        h a = sgo.a();
        xnl xnl = a.i().c;
        if (xnl != null && xnl.a != null) {
            this.f.a(xle.BUTTON, a.a(), a.j(), xnl.a, null);
        }
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onTouchDetectedEvent(sgq sgq) {
        h a = sgq.a();
        xnl xnl = a.i().c;
        if (xnl != null && xnl.a != null) {
            this.f.a(xle.BUTTON, a.a(), a.j(), xnl.a, null);
        }
    }
}
