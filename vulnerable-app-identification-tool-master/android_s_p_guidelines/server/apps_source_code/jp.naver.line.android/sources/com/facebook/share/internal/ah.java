package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.internal.a;
import com.facebook.l;
import com.facebook.n;

public abstract class ah {
    private l a = null;

    public abstract void a(a aVar, Bundle bundle);

    public void a(a aVar) {
        if (this.a != null) {
            this.a.a();
        }
    }

    public void a(a aVar, n nVar) {
        if (this.a != null) {
            this.a.a(nVar);
        }
    }
}
