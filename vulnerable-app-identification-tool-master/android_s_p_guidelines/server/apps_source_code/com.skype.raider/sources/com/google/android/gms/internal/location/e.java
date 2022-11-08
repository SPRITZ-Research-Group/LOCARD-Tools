package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.f;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.d;
import com.google.android.gms.location.zzal;
import java.util.List;

public final class e implements d {
    private static f<Status> a(com.google.android.gms.common.api.e eVar, zzal zzal) {
        return eVar.a(new g(eVar, zzal));
    }

    public final f<Status> a(com.google.android.gms.common.api.e eVar, PendingIntent pendingIntent) {
        return a(eVar, zzal.a(pendingIntent));
    }

    public final f<Status> a(com.google.android.gms.common.api.e eVar, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return eVar.a(new f(eVar, geofencingRequest, pendingIntent));
    }

    public final f<Status> a(com.google.android.gms.common.api.e eVar, List<String> list) {
        return a(eVar, zzal.a((List) list));
    }
}
