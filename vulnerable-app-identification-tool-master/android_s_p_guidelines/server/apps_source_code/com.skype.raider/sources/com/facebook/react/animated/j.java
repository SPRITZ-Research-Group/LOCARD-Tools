package com.facebook.react.animated;

import com.facebook.react.bridge.am;
import com.facebook.react.bridge.m;

final class j extends q {
    private final l g;
    private final int h;
    private final int i;

    public j(am config, l nativeAnimatedNodesManager) {
        this.g = nativeAnimatedNodesManager;
        this.h = config.getInt("input");
        this.i = config.getInt("modulus");
    }

    public final void a() {
        b animatedNode = this.g.a(this.h);
        if (animatedNode == null || !(animatedNode instanceof q)) {
            throw new m("Illegal node ID set as an input for Animated.modulus node");
        }
        this.e = ((q) animatedNode).e % ((double) this.i);
    }
}
