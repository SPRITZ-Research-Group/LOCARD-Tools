package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.api.e.b;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.common.internal.l;

public final class ec extends l<ed> {
    public ec(Context context, Looper looper, g gVar, a aVar, b bVar) {
        super(context, looper, 40, gVar, aVar, bVar);
    }

    protected final /* synthetic */ IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
        return queryLocalInterface instanceof ed ? (ed) queryLocalInterface : new ee(iBinder);
    }

    public final int f() {
        return 11925000;
    }

    protected final String h() {
        return "com.google.android.gms.clearcut.service.START";
    }

    protected final String i() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
