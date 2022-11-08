package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.components.d;
import com.google.firebase.components.e;
import java.util.Arrays;
import java.util.List;

@Keep
@KeepForSdk
public final class Registrar implements d {

    private static class a implements com.google.firebase.iid.a.a {
        private final FirebaseInstanceId a;

        public a(FirebaseInstanceId firebaseInstanceId) {
            this.a = firebaseInstanceId;
        }
    }

    @Keep
    public final List<com.google.firebase.components.a<?>> getComponents() {
        com.google.firebase.components.a b = com.google.firebase.components.a.a(FirebaseInstanceId.class).a(e.a(com.google.firebase.a.class)).a(g.a).a().b();
        com.google.firebase.components.a b2 = com.google.firebase.components.a.a(com.google.firebase.iid.a.a.class).a(e.a(FirebaseInstanceId.class)).a(h.a).b();
        return Arrays.asList(new com.google.firebase.components.a[]{b, b2});
    }
}
