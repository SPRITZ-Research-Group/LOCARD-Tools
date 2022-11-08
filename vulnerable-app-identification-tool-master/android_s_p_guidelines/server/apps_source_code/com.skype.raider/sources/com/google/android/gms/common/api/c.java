package com.google.android.gms.common.api;

import android.support.v4.util.a;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.ap;
import java.util.ArrayList;

public final class c extends Exception {
    private final a<ap<?>, ConnectionResult> a;

    public c(a<ap<?>, ConnectionResult> aVar) {
        this.a = aVar;
    }

    public final String getMessage() {
        Iterable arrayList = new ArrayList();
        Object obj = 1;
        for (ap apVar : this.a.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) this.a.get(apVar);
            if (connectionResult.b()) {
                obj = null;
            }
            String a = apVar.a();
            String valueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder((String.valueOf(a).length() + 2) + String.valueOf(valueOf).length()).append(a).append(": ").append(valueOf).toString());
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (obj != null) {
            stringBuilder.append("None of the queried APIs are available. ");
        } else {
            stringBuilder.append("Some of the queried APIs are unavailable. ");
        }
        stringBuilder.append(TextUtils.join("; ", arrayList));
        return stringBuilder.toString();
    }
}
