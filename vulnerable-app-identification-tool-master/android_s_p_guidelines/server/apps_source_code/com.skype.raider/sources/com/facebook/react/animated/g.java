package com.facebook.react.animated;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.m;

final class g extends q {
    private final l g;
    private final int[] h;

    public g(am config, l nativeAnimatedNodesManager) {
        this.g = nativeAnimatedNodesManager;
        al inputNodes = config.getArray("input");
        this.h = new int[inputNodes.size()];
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = inputNodes.getInt(i);
        }
    }

    public final void a() {
        for (int i = 0; i < this.h.length; i++) {
            b animatedNode = this.g.a(this.h[i]);
            if (animatedNode == null || !(animatedNode instanceof q)) {
                throw new m("Illegal node ID set as an input for Animated.divide node");
            }
            double value = ((q) animatedNode).b();
            if (i == 0) {
                this.e = value;
            } else if (value == 0.0d) {
                throw new m("Detected a division by zero in Animated.divide node");
            } else {
                this.e /= value;
            }
        }
    }
}
