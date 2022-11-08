package jp.naver.line.android.activity.chathistory;

import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.qto;
import defpackage.qtx;
import defpackage.sum;
import java.util.LinkedList;
import java.util.List;
import jp.naver.line.android.l;

public final class ca {
    private final sum a;
    private final List<String> b;
    private String c;
    private boolean d;

    public ca() {
        this(l.a().f().a(false).a());
    }

    private ca(sum sum) {
        this.b = new LinkedList();
        this.a = sum;
        this.c = "";
    }

    public final void a() {
        this.c = "";
        this.b.clear();
        this.d = true;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onUnreadMessageFound(qtx qtx) {
        String a = qtx.a();
        String valueOf = String.valueOf(qtx.b().d());
        if (this.d) {
            if (!this.c.equals(a)) {
                this.c = a;
                this.b.clear();
            }
            this.b.add(valueOf);
            return;
        }
        this.a.a(a, valueOf);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onLockScreenVisibilityChanged(qto qto) {
        boolean z = qto == qto.SHOWN;
        this.d = z;
        if (!z) {
            for (String a : this.b) {
                this.a.a(this.c, a);
            }
            this.c = "";
            this.b.clear();
        }
    }
}
