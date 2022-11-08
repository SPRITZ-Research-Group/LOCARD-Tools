package jp.naver.line.android.activity.search;

import android.text.TextUtils;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.rqe;
import defpackage.rqg;
import defpackage.rxv;
import defpackage.sko;
import defpackage.wsb;
import jp.naver.line.android.util.dm;

final class l {
    wsb a;
    final /* synthetic */ SearchMainActivity b;

    private l(SearchMainActivity searchMainActivity) {
        this.b = searchMainActivity;
    }

    /* synthetic */ l(SearchMainActivity searchMainActivity, byte b) {
        this(searchMainActivity);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSearchResult(rqe rqe) {
        String h = this.b.n.h();
        if (h.equals(rqe.b())) {
            this.b.k.a(rqe);
            if (rqe.c() != null && rqe.c().f()) {
                sko g = rqe.c().g();
                if (!(g == null || g.b() == null)) {
                    if (this.a == null) {
                        this.a = new wsb(0);
                    }
                    this.a.a(dm.a(g.b()), false);
                }
            }
            SearchMainActivity.h(this.b);
            if (!TextUtils.isEmpty(rqe.b())) {
                rxv.a(rqe, this.b.k.a(this.b.h.a()), h, this.b.h, this.b.x.a());
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSearchFinish(rqg rqg) {
        this.b.B.b(false);
        this.b.L = false;
        this.b.B.b();
    }
}
