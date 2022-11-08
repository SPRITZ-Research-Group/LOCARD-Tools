package com.google.firebase.iid;

import android.os.Bundle;

final class b extends c<Void> {
    b(int i, Bundle bundle) {
        super(i, 2, bundle);
    }

    final void a(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            a(null);
        } else {
            a(new d(4, "Invalid response to one way request"));
        }
    }

    final boolean a() {
        return true;
    }
}
