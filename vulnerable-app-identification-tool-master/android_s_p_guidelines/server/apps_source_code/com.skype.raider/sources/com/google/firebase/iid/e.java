package com.google.firebase.iid;

import android.os.Bundle;

final class e extends c<Bundle> {
    e(int i, Bundle bundle) {
        super(i, 1, bundle);
    }

    final void a(Bundle bundle) {
        Object bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        a(bundle2);
    }

    final boolean a() {
        return false;
    }
}
