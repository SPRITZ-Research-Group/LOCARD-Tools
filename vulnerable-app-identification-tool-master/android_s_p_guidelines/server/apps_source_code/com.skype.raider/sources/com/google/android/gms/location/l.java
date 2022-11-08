package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.common.api.a.f;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.e.b;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.internal.location.w;

final class l extends a<w, Object> {
    l() {
    }

    public final /* synthetic */ f a(Context context, Looper looper, g gVar, Object obj, e.a aVar, b bVar) {
        return new w(context, looper, aVar, bVar, "locationServices", gVar);
    }
}
