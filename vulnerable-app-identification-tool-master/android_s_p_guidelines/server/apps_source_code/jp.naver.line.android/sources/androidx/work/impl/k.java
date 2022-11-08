package androidx.work.impl;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

final class k {
    final Set<LiveData> a = Collections.newSetFromMap(new IdentityHashMap());

    k() {
    }
}
