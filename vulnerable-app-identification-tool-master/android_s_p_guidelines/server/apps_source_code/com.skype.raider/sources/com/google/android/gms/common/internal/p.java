package com.google.android.gms.common.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.a.f;
import com.google.android.gms.common.e;

public final class p {
    private final SparseIntArray a;
    private e b;

    public p() {
        this(GoogleApiAvailability.a());
    }

    public p(@NonNull e eVar) {
        this.a = new SparseIntArray();
        ab.a((Object) eVar);
        this.b = eVar;
    }

    public final int a(@NonNull Context context, @NonNull f fVar) {
        ab.a((Object) context);
        ab.a((Object) fVar);
        int f = fVar.f();
        int i = this.a.get(f, -1);
        if (i != -1) {
            return i;
        }
        int i2;
        for (i2 = 0; i2 < this.a.size(); i2++) {
            int keyAt = this.a.keyAt(i2);
            if (keyAt > f && this.a.get(keyAt) == 0) {
                i2 = 0;
                break;
            }
        }
        i2 = i;
        if (i2 == -1) {
            i2 = this.b.b(context, f);
        }
        this.a.put(f, i2);
        return i2;
    }

    public final void a() {
        this.a.clear();
    }
}
