package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.h;

public final class b {
    @NonNull
    public static com.google.android.gms.common.api.b a(@NonNull Status status) {
        return status.c() ? new h(status) : new com.google.android.gms.common.api.b(status);
    }
}
