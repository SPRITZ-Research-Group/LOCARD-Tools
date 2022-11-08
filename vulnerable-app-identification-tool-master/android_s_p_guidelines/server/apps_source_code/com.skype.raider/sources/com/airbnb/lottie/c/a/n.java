package com.airbnb.lottie.c.a;

import com.airbnb.lottie.f.a;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class n<V, O> implements m<V, O> {
    final List<a<V>> a;

    n(V value) {
        this(Collections.singletonList(new a(value)));
    }

    n(List<a<V>> keyframes) {
        this.a = keyframes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.a.isEmpty()) {
            sb.append("values=").append(Arrays.toString(this.a.toArray()));
        }
        return sb.toString();
    }
}
