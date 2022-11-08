package com.google.android.gms.location;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.c.g;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.internal.aa;
import java.util.List;

public final class e extends d<Object> {
    public e(@NonNull Context context) {
        super(context, LocationServices.a, new a());
    }

    public final g<Void> a(List<String> list) {
        return aa.a(LocationServices.c.a(c(), (List) list));
    }
}
