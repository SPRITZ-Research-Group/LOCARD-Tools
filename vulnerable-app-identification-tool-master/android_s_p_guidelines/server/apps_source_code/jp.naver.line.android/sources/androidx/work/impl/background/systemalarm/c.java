package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.p;
import defpackage.om;
import defpackage.po;
import java.util.ArrayList;
import java.util.List;

final class c {
    private static final String a = p.a("ConstraintsCmdHandler");
    private final Context b;
    private final int c;
    private final e d;
    private final om e = new om(this.b, null);

    c(Context context, int i, e eVar) {
        this.b = context;
        this.c = i;
        this.d = eVar;
    }

    final void a() {
        List<po> a = this.d.d().d().l().a(this.d.d().e().f());
        ConstraintProxy.a(this.b, a);
        this.e.a((List) a);
        List<po> arrayList = new ArrayList(a.size());
        long currentTimeMillis = System.currentTimeMillis();
        for (po poVar : a) {
            String str = poVar.a;
            if (currentTimeMillis >= poVar.c() && (!poVar.d() || this.e.a(str))) {
                arrayList.add(poVar);
            }
        }
        for (po poVar2 : arrayList) {
            Intent b = b.b(this.b, poVar2.a);
            p.a();
            String.format("Creating a delay_met command for workSpec with id (%s)", new Object[]{r1});
            Throwable[] thArr = new Throwable[0];
            this.d.a(new f(this.d, b, this.c));
        }
        this.e.a();
    }
}
