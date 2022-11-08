package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.s;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class o extends b {
    private final l e;
    private final Map<String, Integer> f = new HashMap();

    o(am config, l nativeAnimatedNodesManager) {
        am style = config.getMap("style");
        ReadableMapKeySetIterator iter = style.keySetIterator();
        while (iter.hasNextKey()) {
            String propKey = iter.nextKey();
            this.f.put(propKey, Integer.valueOf(style.getInt(propKey)));
        }
        this.e = nativeAnimatedNodesManager;
    }

    public final void a(s propsMap) {
        for (Entry<String, Integer> entry : this.f.entrySet()) {
            b node = this.e.a(((Integer) entry.getValue()).intValue());
            if (node == null) {
                throw new IllegalArgumentException("Mapped style node does not exists");
            } else if (node instanceof p) {
                ((p) node).a(propsMap);
            } else if (node instanceof q) {
                propsMap.putDouble((String) entry.getKey(), ((q) node).b());
            } else {
                throw new IllegalArgumentException("Unsupported type of node used in property node " + node.getClass());
            }
        }
    }
}
