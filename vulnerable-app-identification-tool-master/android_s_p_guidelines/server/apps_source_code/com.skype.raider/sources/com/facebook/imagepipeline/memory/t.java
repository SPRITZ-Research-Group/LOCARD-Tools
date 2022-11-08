package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.h;

public final class t {
    static int a(int offset, int count, int memorySize) {
        return Math.min(Math.max(0, memorySize - offset), count);
    }

    static void a(int offset, int otherLength, int otherOffset, int count, int memorySize) {
        boolean z;
        boolean z2 = true;
        h.a(count >= 0);
        if (offset >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (otherOffset >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (offset + count <= memorySize) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (otherOffset + count > otherLength) {
            z2 = false;
        }
        h.a(z2);
    }
}
