package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public final class f extends g {
    private final List<a<?>> a;

    @KeepForSdk
    public f(List<a<?>> list) {
        String str = "Dependency cycle detected: ";
        String valueOf = String.valueOf(Arrays.toString(list.toArray()));
        super(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.a = list;
    }
}
