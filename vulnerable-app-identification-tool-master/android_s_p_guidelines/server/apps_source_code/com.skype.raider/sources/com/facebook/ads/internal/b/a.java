package com.facebook.ads.internal.b;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private final View a;
    private final List<d> b;
    private c c;

    public a(View view, List<b> list) {
        this.a = view;
        this.b = new ArrayList(list.size());
        for (b dVar : list) {
            this.b.add(new d(dVar));
        }
        this.c = new c();
    }

    public a(View view, List<b> list, Bundle bundle) {
        this.a = view;
        this.b = new ArrayList(list.size());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                this.b.add(new d((b) list.get(i2), (Bundle) parcelableArrayList.get(i2)));
                i = i2 + 1;
            } else {
                this.c = (c) bundle.getSerializable("STATISTICS");
                return;
            }
        }
    }

    public final void a() {
        this.c.a();
    }

    public final void a(double d, double d2) {
        if (d2 >= 0.0d) {
            this.c.b(d, d2);
        }
        double c = (double) com.facebook.ads.internal.s.a.a(this.a, 0).c();
        this.c.a(d, c);
        for (d a : this.b) {
            a.a(d, c);
        }
    }

    public final void b() {
        this.c.b();
        for (d a : this.b) {
            a.a();
        }
    }

    public final c c() {
        return this.c;
    }

    public final Bundle d() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", this.c);
        ArrayList arrayList = new ArrayList(this.b.size());
        for (d b : this.b) {
            arrayList.add(b.b());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
