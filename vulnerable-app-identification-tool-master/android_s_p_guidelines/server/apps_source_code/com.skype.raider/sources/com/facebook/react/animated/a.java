package com.facebook.react.animated;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.m;

final class a extends q {
    private final l g;
    private final int[] h;

    public a(am config, l nativeAnimatedNodesManager) {
        this.g = nativeAnimatedNodesManager;
        al inputNodes = config.getArray("input");
        this.h = new int[inputNodes.size()];
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = inputNodes.getInt(i);
        }
    }

    public final void a() {
        this.e = 0.0d;
        for (int a : this.h) {
            b animatedNode = this.g.a(a);
            if (animatedNode == null || !(animatedNode instanceof q)) {
                throw new m("Illegal node ID set as an input for Animated.Add node");
            }
            this.e += ((q) animatedNode).b();
        }
    }
}
