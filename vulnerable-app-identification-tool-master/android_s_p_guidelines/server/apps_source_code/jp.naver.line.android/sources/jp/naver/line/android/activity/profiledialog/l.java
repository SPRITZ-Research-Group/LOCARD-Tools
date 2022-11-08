package jp.naver.line.android.activity.profiledialog;

import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.vaq;

final class l {
    final /* synthetic */ c a;

    private l(c cVar) {
        this.a = cVar;
    }

    /* synthetic */ l(c cVar, byte b) {
        this(cVar);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onPermissionApprovalFromProfileResult(b bVar) {
        this.a.D.c(this);
        if (bVar.a) {
            vaq.a().a(c.a(this.a, bVar.b, null));
        } else {
            this.a.m();
        }
    }
}
