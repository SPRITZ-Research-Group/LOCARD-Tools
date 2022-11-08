package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class b extends Exception {
    protected final Status a;

    public b(@NonNull Status status) {
        int e = status.e();
        String b = status.b() != null ? status.b() : "";
        super(new StringBuilder(String.valueOf(b).length() + 13).append(e).append(": ").append(b).toString());
        this.a = status;
    }

    public final int a() {
        return this.a.e();
    }
}
