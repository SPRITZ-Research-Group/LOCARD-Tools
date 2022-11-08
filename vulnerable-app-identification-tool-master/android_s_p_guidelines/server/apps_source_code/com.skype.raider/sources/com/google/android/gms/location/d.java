package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.f;
import java.util.List;

@Deprecated
public interface d {
    f<Status> a(e eVar, PendingIntent pendingIntent);

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    f<Status> a(e eVar, GeofencingRequest geofencingRequest, PendingIntent pendingIntent);

    f<Status> a(e eVar, List<String> list);
}
