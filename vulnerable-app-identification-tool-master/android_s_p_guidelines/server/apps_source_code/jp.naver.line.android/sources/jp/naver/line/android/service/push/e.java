package jp.naver.line.android.service.push;

import android.content.Context;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.tzt;
import defpackage.udf;
import defpackage.upb;
import defpackage.upc;
import defpackage.uqk;
import defpackage.vni;
import defpackage.zcd;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.activity.pushdialog.t;
import jp.naver.line.android.l;

public final class e {
    private long a;

    public e(Context context) {
        ((LineApplication) context.getApplicationContext()).a().b(this);
    }

    final void a(long j) {
        this.a = j;
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onPushStatusChanged(t tVar) {
        if (tVar.a != 11) {
            e eVar = this;
            return;
        }
        if (this.a == tVar.b()) {
            g a = tVar.a();
            tzt.a();
            boolean h = tzt.h();
            boolean z = h && vni.a(a.f) == zcd.USER;
            boolean z2 = (!h || udf.a(a.c) == udf.MENTION_MESSAGE || udf.a(a.c) == udf.REPLY_MESSAGE) ? false : true;
            uqk.a().a(l.a(), new upb(upc.RECEIVE_MESSAGE, a.L, a.f, a.E, a.F, a.D, null, a.d, a.u, a.v, a.G, a.e, a.h, false, a.M, a.N, h, z, z2), true, false, true, null);
        }
    }
}
