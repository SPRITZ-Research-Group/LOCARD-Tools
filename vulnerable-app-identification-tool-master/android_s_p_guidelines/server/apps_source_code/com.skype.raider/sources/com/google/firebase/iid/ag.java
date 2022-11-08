package com.google.firebase.iid;

import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;
import java.util.Arrays;

final class ag {
    private final KeyPair a;
    private final long b;

    @VisibleForTesting
    ag(KeyPair keyPair, long j) {
        this.a = keyPair;
        this.b = j;
    }

    final KeyPair a() {
        return this.a;
    }

    final long b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ag)) {
            return false;
        }
        ag agVar = (ag) obj;
        return this.b == agVar.b && this.a.getPublic().equals(agVar.a.getPublic()) && this.a.getPrivate().equals(agVar.a.getPrivate());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a.getPublic(), this.a.getPrivate(), Long.valueOf(this.b)});
    }
}
