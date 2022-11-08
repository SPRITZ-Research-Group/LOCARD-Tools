package com.google.android.gms.location;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.a.g;
import com.google.android.gms.common.api.i;
import com.google.android.gms.internal.location.ae;
import com.google.android.gms.internal.location.al;
import com.google.android.gms.internal.location.e;
import com.google.android.gms.internal.location.w;

public class LocationServices {
    public static final com.google.android.gms.common.api.a<Object> a = new com.google.android.gms.common.api.a("LocationServices.API", f, e);
    @Deprecated
    public static final a b = new al();
    @Deprecated
    public static final d c = new e();
    @Deprecated
    public static final h d = new ae();
    private static final g<w> e = new g();
    private static final com.google.android.gms.common.api.a.a<w, Object> f = new l();

    public static abstract class a<R extends i> extends com.google.android.gms.common.api.internal.c.a<R, w> {
        public a(com.google.android.gms.common.api.e eVar) {
            super(LocationServices.a, eVar);
        }
    }

    public static e a(@NonNull Context context) {
        return new e(context);
    }

    public static b b(@NonNull Context context) {
        return new b(context);
    }
}
