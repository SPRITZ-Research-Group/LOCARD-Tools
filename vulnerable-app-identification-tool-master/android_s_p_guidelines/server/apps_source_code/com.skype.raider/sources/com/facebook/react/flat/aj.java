package com.facebook.react.flat;

import java.util.Arrays;

final class aj extends e {
    aj(t flatViewGroup, h[] drawCommands) {
        super(flatViewGroup, drawCommands);
    }

    final int a() {
        int start = Arrays.binarySearch(this.a, (float) this.e.top);
        return start < 0 ? start ^ -1 : start;
    }

    final int a(int start) {
        int stop = Arrays.binarySearch(this.b, start, this.b.length, (float) this.e.bottom);
        return stop < 0 ? stop ^ -1 : stop;
    }

    final int a(float touchY) {
        int stop = Arrays.binarySearch(this.d, 1.0E-4f + touchY);
        return stop < 0 ? stop ^ -1 : stop;
    }

    final boolean a(int index, float touchY) {
        return this.c[index] < touchY;
    }
}
