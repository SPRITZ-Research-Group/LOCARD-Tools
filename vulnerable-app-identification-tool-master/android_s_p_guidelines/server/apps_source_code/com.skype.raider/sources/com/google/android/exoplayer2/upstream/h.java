package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;
import java.util.Arrays;

public final class h implements b {
    private final boolean a;
    private final int b;
    private final byte[] c;
    private final a[] d;
    private int e;
    private int f;
    private int g;
    private a[] h;

    public h() {
        this((byte) 0);
    }

    private h(byte b) {
        a.a(true);
        a.a(true);
        this.a = true;
        this.b = 65536;
        this.g = 0;
        this.h = new a[100];
        this.c = null;
        this.d = new a[1];
    }

    public final synchronized void d() {
        if (this.a) {
            a(0);
        }
    }

    public final synchronized void a(int targetBufferSize) {
        boolean targetBufferSizeReduced = targetBufferSize < this.e;
        this.e = targetBufferSize;
        if (targetBufferSizeReduced) {
            b();
        }
    }

    public final synchronized a a() {
        a allocation;
        this.f++;
        if (this.g > 0) {
            a[] aVarArr = this.h;
            int i = this.g - 1;
            this.g = i;
            allocation = aVarArr[i];
            this.h[this.g] = null;
        } else {
            allocation = new a(new byte[this.b]);
        }
        return allocation;
    }

    public final synchronized void a(a allocation) {
        this.d[0] = allocation;
        a(this.d);
    }

    public final synchronized void a(a[] allocations) {
        if (this.g + allocations.length >= this.h.length) {
            this.h = (a[]) Arrays.copyOf(this.h, Math.max(this.h.length * 2, this.g + allocations.length));
        }
        for (a allocation : allocations) {
            boolean z;
            if (allocation.a == this.c || allocation.a.length == this.b) {
                z = true;
            } else {
                z = false;
            }
            a.a(z);
            a[] aVarArr = this.h;
            int i = this.g;
            this.g = i + 1;
            aVarArr[i] = allocation;
        }
        this.f -= allocations.length;
        notifyAll();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void b() {
        int targetAvailableCount = Math.max(0, s.a(this.e, this.b) - this.f);
        if (targetAvailableCount < this.g) {
            if (this.c != null) {
                int highIndex = this.g - 1;
                int lowIndex = 0;
                while (lowIndex <= highIndex) {
                    a lowAllocation = this.h[lowIndex];
                    if (lowAllocation.a == this.c) {
                        lowIndex++;
                    } else {
                        a highAllocation = this.h[highIndex];
                        if (highAllocation.a != this.c) {
                            highIndex--;
                        } else {
                            int lowIndex2 = lowIndex + 1;
                            this.h[lowIndex] = highAllocation;
                            int highIndex2 = highIndex - 1;
                            this.h[highIndex] = lowAllocation;
                            highIndex = highIndex2;
                            lowIndex = lowIndex2;
                        }
                    }
                }
                targetAvailableCount = Math.max(targetAvailableCount, lowIndex);
            }
            Arrays.fill(this.h, targetAvailableCount, this.g, null);
            this.g = targetAvailableCount;
        }
    }

    public final synchronized int e() {
        return this.f * this.b;
    }

    public final int c() {
        return this.b;
    }
}
