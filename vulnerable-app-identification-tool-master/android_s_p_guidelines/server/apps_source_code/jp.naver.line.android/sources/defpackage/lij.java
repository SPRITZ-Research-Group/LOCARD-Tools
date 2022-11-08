package defpackage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: lij */
public final class lij {
    private final boolean a;
    private final boolean b;
    private final Set<Class<?>> c;
    private final int d;

    /* synthetic */ lij(boolean z, boolean z2, Set set, int i, byte b) {
        this(z, z2, set, i);
    }

    private lij(boolean z, boolean z2, Set<Class<?>> set, int i) {
        this.a = z;
        this.b = z2;
        if (set.isEmpty()) {
            this.c = Collections.EMPTY_SET;
        } else {
            this.c = Collections.unmodifiableSet(new HashSet(set));
        }
        this.d = i;
    }

    final boolean a(Class<?> cls) {
        return this.a && (this.b || this.c.contains(cls));
    }

    final int a() {
        return this.d;
    }
}
