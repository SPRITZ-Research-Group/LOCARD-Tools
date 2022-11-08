package com.facebook.imagepipeline.memory;

import com.facebook.common.f.b;
import java.util.LinkedList;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class z<V> extends f<V> {
    private LinkedList<b<V>> d = new LinkedList();

    public z(int itemSize, int maxLength) {
        super(itemSize, maxLength, 0, false);
    }

    public final V d() {
        b<V> ref = (b) this.c.poll();
        V value = ref.a();
        ref.b();
        this.d.add(ref);
        return value;
    }

    final void b(V value) {
        b<V> ref = (b) this.d.poll();
        if (ref == null) {
            ref = new b();
        }
        ref.a(value);
        this.c.add(ref);
    }
}
