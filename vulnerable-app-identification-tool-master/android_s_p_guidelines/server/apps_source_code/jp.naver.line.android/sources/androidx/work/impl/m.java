package androidx.work.impl;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.ai;
import androidx.work.b;
import defpackage.qi;
import java.util.List;

public final class m {
    Context a;
    ListenableWorker b;
    qi c;
    b d;
    WorkDatabase e;
    String f;
    List<e> g;
    ai h = new ai();

    public m(Context context, b bVar, qi qiVar, WorkDatabase workDatabase, String str) {
        this.a = context.getApplicationContext();
        this.c = qiVar;
        this.d = bVar;
        this.e = workDatabase;
        this.f = str;
    }
}
