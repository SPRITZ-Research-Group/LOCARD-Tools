package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class y {
    private final int a;
    private final CharSequence b;
    private final PendingIntent c;
    private boolean d;
    private final Bundle e;
    private ArrayList<an> f;
    private int g;
    private boolean h;

    public y(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this(i, charSequence, pendingIntent, new Bundle());
    }

    private y(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        this.d = true;
        this.h = true;
        this.a = i;
        this.b = aa.e(charSequence);
        this.c = pendingIntent;
        this.e = bundle;
        this.f = null;
        this.d = true;
        this.g = 0;
        this.h = true;
    }

    public final y a(an anVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(anVar);
        return this;
    }

    public final x a() {
        an[] anVarArr;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        if (this.f != null) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                an anVar = (an) it.next();
                Object obj = (anVar.e() || (!(anVar.c() == null || anVar.c().length == 0) || anVar.d() == null || anVar.d().isEmpty())) ? null : 1;
                if (obj != null) {
                    arrayList.add(anVar);
                } else {
                    arrayList2.add(anVar);
                }
            }
        }
        an[] anVarArr2 = null;
        if (arrayList.isEmpty()) {
            anVarArr = null;
        } else {
            anVarArr = (an[]) arrayList.toArray(new an[arrayList.size()]);
        }
        if (!arrayList2.isEmpty()) {
            anVarArr2 = (an[]) arrayList2.toArray(new an[arrayList2.size()]);
        }
        return new x(this.a, this.b, this.c, this.e, anVarArr2, anVarArr, this.d, this.g, this.h);
    }
}
