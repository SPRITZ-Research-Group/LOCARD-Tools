package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.a;
import com.google.android.gms.common.internal.e.b;

public final class au extends e<an> {
    public au(Context context, Looper looper, a aVar, b bVar) {
        super(context, looper, aVar, bVar);
    }

    public final /* synthetic */ IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
        return queryLocalInterface instanceof an ? (an) queryLocalInterface : new ap(iBinder);
    }

    @NonNull
    protected final String h() {
        return "com.google.android.gms.measurement.START";
    }

    @NonNull
    protected final String i() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
