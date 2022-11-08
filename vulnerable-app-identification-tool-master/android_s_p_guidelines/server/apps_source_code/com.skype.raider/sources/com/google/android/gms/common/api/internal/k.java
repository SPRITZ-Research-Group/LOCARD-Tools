package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.a.b;

@KeepForSdk
public abstract class k<A extends b, ResultT> {
    private final Feature[] a;
    private final boolean b;

    @Nullable
    public final Feature[] a() {
        return this.a;
    }

    @KeepForSdk
    public final boolean b() {
        return this.b;
    }
}
