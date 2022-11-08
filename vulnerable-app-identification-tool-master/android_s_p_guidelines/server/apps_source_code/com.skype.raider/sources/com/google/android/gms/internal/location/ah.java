package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.api.e.b;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.common.internal.l;

public class ah extends l<n> {
    protected final ad<n> e = new ai(this);
    private final String f;

    public ah(Context context, Looper looper, a aVar, b bVar, String str, g gVar) {
        super(context, looper, 23, gVar, aVar, bVar);
        this.f = str;
    }

    protected final /* synthetic */ IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof n ? (n) queryLocalInterface : new o(iBinder);
    }

    public final int f() {
        return 11925000;
    }

    protected final String h() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected final String i() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    protected final Bundle n() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.f);
        return bundle;
    }
}
