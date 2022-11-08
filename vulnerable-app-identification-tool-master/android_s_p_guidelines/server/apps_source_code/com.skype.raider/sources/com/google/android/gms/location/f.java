package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

public final class f {
    private final int a;
    private final int b;
    private final List<c> c;
    private final Location d;

    private f(int i, int i2, List<c> list, Location location) {
        this.a = i;
        this.b = i2;
        this.c = list;
        this.d = location;
    }

    public static f a(Intent intent) {
        if (intent == null) {
            return null;
        }
        List list;
        int intExtra = intent.getIntExtra("gms_error_code", -1);
        int intExtra2 = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        int i = (intExtra2 == -1 || !(intExtra2 == 1 || intExtra2 == 2 || intExtra2 == 4)) ? -1 : intExtra2;
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (arrayList == null) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            arrayList = arrayList;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                obj = arrayList.get(i2);
                i2++;
                arrayList2.add(zzbh.a((byte[]) obj));
            }
            obj = arrayList2;
        }
        return new f(intExtra, i, list, (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }

    public final boolean a() {
        return this.a != -1;
    }

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final List<c> d() {
        return this.c;
    }
}
