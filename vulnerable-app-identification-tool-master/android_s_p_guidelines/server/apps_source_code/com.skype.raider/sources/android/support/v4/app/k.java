package android.support.v4.app;

import java.util.List;

public final class k {
    private final List<Fragment> a;
    private final List<k> b;

    k(List<Fragment> fragments, List<k> childNonConfigs) {
        this.a = fragments;
        this.b = childNonConfigs;
    }

    final List<Fragment> a() {
        return this.a;
    }

    final List<k> b() {
        return this.b;
    }
}
