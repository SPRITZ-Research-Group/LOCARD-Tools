package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.s;
import com.facebook.react.uimanager.ai;
import com.facebook.react.uimanager.x;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class m extends b {
    int e = -1;
    private final l f;
    private final Map<String, Integer> g;

    m(am config, l nativeAnimatedNodesManager) {
        am props = config.getMap("props");
        ReadableMapKeySetIterator iter = props.keySetIterator();
        this.g = new HashMap();
        while (iter.hasNextKey()) {
            String propKey = iter.nextKey();
            this.g.put(propKey, Integer.valueOf(props.getInt(propKey)));
        }
        this.f = nativeAnimatedNodesManager;
    }

    public final void a(ai uiImplementation) {
        if (this.e == -1) {
            throw new IllegalStateException("Node has not been attached to a view");
        }
        s propsMap = new s();
        for (Entry<String, Integer> entry : this.g.entrySet()) {
            b node = this.f.a(((Integer) entry.getValue()).intValue());
            if (node == null) {
                throw new IllegalArgumentException("Mapped property node does not exists");
            } else if (node instanceof o) {
                ((o) node).a(propsMap);
            } else if (node instanceof q) {
                propsMap.putDouble((String) entry.getKey(), ((q) node).b());
            } else {
                throw new IllegalArgumentException("Unsupported type of node used in property node " + node.getClass());
            }
        }
        uiImplementation.a(this.e, new x(propsMap));
    }
}
