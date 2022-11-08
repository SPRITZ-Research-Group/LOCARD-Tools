package com.facebook.imagepipeline.d;

import com.facebook.common.internal.j;

public final class k implements j<q> {
    public final /* synthetic */ Object a() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            min = 1048576;
        } else if (min < 33554432) {
            min = 2097152;
        } else {
            min = 4194304;
        }
        return new q(min, Integer.MAX_VALUE, min, min / 8);
    }
}
