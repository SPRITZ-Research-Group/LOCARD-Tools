package com.google.android.gms.common.internal;

import com.google.android.gms.c.h;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.f;
import com.google.android.gms.common.api.f.a;
import com.google.android.gms.common.internal.aa.b;
import java.util.concurrent.TimeUnit;

final class ar implements a {
    private final /* synthetic */ f a;
    private final /* synthetic */ h b;
    private final /* synthetic */ aa.a c;
    private final /* synthetic */ b d;

    ar(f fVar, h hVar, aa.a aVar, b bVar) {
        this.a = fVar;
        this.b = hVar;
        this.c = aVar;
        this.d = bVar;
    }

    public final void a(Status status) {
        if (status.d()) {
            this.a.a(TimeUnit.MILLISECONDS);
            this.b.a(null);
            return;
        }
        this.b.a(this.d.a(status));
    }
}
