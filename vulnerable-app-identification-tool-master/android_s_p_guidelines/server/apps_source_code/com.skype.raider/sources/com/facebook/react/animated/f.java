package com.facebook.react.animated;

import com.facebook.react.bridge.am;
import com.facebook.react.bridge.m;

final class f extends q {
    private final l g;
    private final int h;
    private final double i;
    private final double j;
    private double k;

    public f(am config, l nativeAnimatedNodesManager) {
        this.g = nativeAnimatedNodesManager;
        this.h = config.getInt("input");
        this.i = config.getDouble("min");
        this.j = config.getDouble("max");
        this.k = 0.0d;
        this.e = 0.0d;
    }

    public final void a() {
        b a = this.g.a(this.h);
        if (a == null || !(a instanceof q)) {
            throw new m("Illegal node ID set as an input for Animated.DiffClamp node");
        }
        double value = ((q) a).b();
        double diff = value - this.k;
        this.k = value;
        this.e = Math.min(Math.max(this.e + diff, this.i), this.j);
    }
}
