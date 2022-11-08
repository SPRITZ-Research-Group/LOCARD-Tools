package com.google.android.exoplayer2.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class o {
    private static final Comparator<a> a = new Comparator<a>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((a) obj).a - ((a) obj2).a;
        }
    };
    private static final Comparator<a> b = new Comparator<a>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            a aVar = (a) obj;
            a aVar2 = (a) obj2;
            if (aVar.c < aVar2.c) {
                return -1;
            }
            return aVar2.c < aVar.c ? 1 : 0;
        }
    };
    private final int c = 2000;
    private final ArrayList<a> d = new ArrayList();
    private final a[] e = new a[5];
    private int f = -1;
    private int g;
    private int h;
    private int i;

    private static class a {
        public int a;
        public int b;
        public float c;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    public final void a(int weight, float value) {
        a[] aVarArr;
        int i;
        a newSample;
        if (this.f != 1) {
            Collections.sort(this.d, a);
            this.f = 1;
        }
        if (this.i > 0) {
            aVarArr = this.e;
            i = this.i - 1;
            this.i = i;
            newSample = aVarArr[i];
        } else {
            newSample = new a();
        }
        int i2 = this.g;
        this.g = i2 + 1;
        newSample.a = i2;
        newSample.b = weight;
        newSample.c = value;
        this.d.add(newSample);
        this.h += weight;
        while (this.h > this.c) {
            int excessWeight = this.h - this.c;
            a oldestSample = (a) this.d.get(0);
            if (oldestSample.b <= excessWeight) {
                this.h -= oldestSample.b;
                this.d.remove(0);
                if (this.i < 5) {
                    aVarArr = this.e;
                    i = this.i;
                    this.i = i + 1;
                    aVarArr[i] = oldestSample;
                }
            } else {
                oldestSample.b -= excessWeight;
                this.h -= excessWeight;
            }
        }
    }

    public final float a() {
        if (this.f != 0) {
            Collections.sort(this.d, b);
            this.f = 0;
        }
        float desiredWeight = 0.5f * ((float) this.h);
        int accumulatedWeight = 0;
        for (int i = 0; i < this.d.size(); i++) {
            a currentSample = (a) this.d.get(i);
            accumulatedWeight += currentSample.b;
            if (((float) accumulatedWeight) >= desiredWeight) {
                return currentSample.c;
            }
        }
        return this.d.isEmpty() ? Float.NaN : ((a) this.d.get(this.d.size() - 1)).c;
    }
}
