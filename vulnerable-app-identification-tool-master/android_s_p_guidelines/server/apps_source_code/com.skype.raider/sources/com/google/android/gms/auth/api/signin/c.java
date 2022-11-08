package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class c implements Comparator<Scope> {
    c() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((Scope) obj).a().compareTo(((Scope) obj2).a());
    }
}
