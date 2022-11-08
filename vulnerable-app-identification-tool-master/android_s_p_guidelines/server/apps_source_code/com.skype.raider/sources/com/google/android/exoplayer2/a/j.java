package com.google.android.exoplayer2.a;

import com.google.android.exoplayer2.d.a;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class j {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e = (this.d * 2);
    private final short[] f = new short[this.e];
    private int g = this.e;
    private short[] h;
    private int i;
    private short[] j;
    private int k;
    private short[] l;
    private int m;
    private int n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;

    public j(int sampleRate, int numChannels) {
        this.a = sampleRate;
        this.b = numChannels;
        this.c = sampleRate / 400;
        this.d = sampleRate / 65;
        this.h = new short[(this.e * numChannels)];
        this.i = this.e;
        this.j = new short[(this.e * numChannels)];
        this.k = this.e;
        this.l = new short[(this.e * numChannels)];
        this.m = 0;
        this.n = 0;
        this.u = 0;
        this.o = 1.0f;
        this.p = 1.0f;
    }

    public final void a(float speed) {
        this.o = speed;
    }

    public final void b(float pitch) {
        this.p = pitch;
    }

    public final void a(ShortBuffer buffer) {
        int samplesToWrite = buffer.remaining() / this.b;
        int bytesToWrite = (this.b * samplesToWrite) * 2;
        b(samplesToWrite);
        buffer.get(this.h, this.q * this.b, bytesToWrite / 2);
        this.q += samplesToWrite;
        c();
    }

    public final void b(ShortBuffer buffer) {
        int samplesToRead = Math.min(buffer.remaining() / this.b, this.r);
        buffer.put(this.j, 0, this.b * samplesToRead);
        this.r -= samplesToRead;
        System.arraycopy(this.j, this.b * samplesToRead, this.j, 0, this.r * this.b);
    }

    public final void a() {
        int remainingSamples = this.q;
        int expectedOutputSamples = this.r + ((int) ((((((float) remainingSamples) / (this.o / this.p)) + ((float) this.s)) / this.p) + 0.5f));
        b((this.e * 2) + remainingSamples);
        for (int xSample = 0; xSample < (this.e * 2) * this.b; xSample++) {
            this.h[(this.b * remainingSamples) + xSample] = (short) 0;
        }
        this.q += this.e * 2;
        c();
        if (this.r > expectedOutputSamples) {
            this.r = expectedOutputSamples;
        }
        this.q = 0;
        this.t = 0;
        this.s = 0;
    }

    public final int b() {
        return this.r;
    }

    private void a(int numSamples) {
        if (this.r + numSamples > this.i) {
            this.i += (this.i / 2) + numSamples;
            this.j = Arrays.copyOf(this.j, this.i * this.b);
        }
    }

    private void b(int numSamples) {
        if (this.q + numSamples > this.g) {
            this.g += (this.g / 2) + numSamples;
            this.h = Arrays.copyOf(this.h, this.g * this.b);
        }
    }

    private void a(short[] samples, int position, int numSamples) {
        a(numSamples);
        System.arraycopy(samples, this.b * position, this.j, this.r * this.b, this.b * numSamples);
        this.r += numSamples;
    }

    private void b(short[] samples, int position, int skip) {
        int numSamples = this.e / skip;
        int samplesPerValue = this.b * skip;
        position *= this.b;
        for (int i = 0; i < numSamples; i++) {
            int value = 0;
            for (int j = 0; j < samplesPerValue; j++) {
                value += samples[((i * samplesPerValue) + position) + j];
            }
            this.f[i] = (short) (value / samplesPerValue);
        }
    }

    private int a(short[] samples, int position, int minPeriod, int maxPeriod) {
        int bestPeriod = 0;
        int worstPeriod = 255;
        int minDiff = 1;
        int maxDiff = 0;
        position *= this.b;
        for (int period = minPeriod; period <= maxPeriod; period++) {
            int diff = 0;
            for (int i = 0; i < period; i++) {
                short sVal = samples[position + i];
                short pVal = samples[(position + period) + i];
                diff += sVal >= pVal ? sVal - pVal : pVal - sVal;
            }
            if (diff * bestPeriod < minDiff * period) {
                minDiff = diff;
                bestPeriod = period;
            }
            if (diff * worstPeriod > maxDiff * period) {
                maxDiff = diff;
                worstPeriod = period;
            }
        }
        this.w = minDiff / bestPeriod;
        this.x = maxDiff / worstPeriod;
        return bestPeriod;
    }

    private void c() {
        int i;
        int min;
        int a;
        int i2;
        short[] sArr;
        int originalNumOutputSamples = this.r;
        float s = this.o / this.p;
        if (((double) s) <= 1.00001d && ((double) s) >= 0.99999d) {
            a(this.h, 0, this.q);
            this.q = 0;
        } else if (this.q >= this.e) {
            int i3 = this.q;
            i = 0;
            do {
                if (this.t > 0) {
                    min = Math.min(this.e, this.t);
                    a(this.h, i, min);
                    this.t -= min;
                    i += min;
                } else {
                    Object obj;
                    int i4;
                    short[] sArr2 = this.h;
                    min = this.a > 4000 ? this.a / 4000 : 1;
                    if (this.b == 1 && min == 1) {
                        min = a(sArr2, i, this.c, this.d);
                    } else {
                        b(sArr2, i, min);
                        a = a(this.f, 0, this.c / min, this.d / min);
                        if (min != 1) {
                            i2 = a * min;
                            a = i2 - (min * 4);
                            i2 += min * 4;
                            if (a < this.c) {
                                min = this.c;
                            } else {
                                min = a;
                            }
                            if (i2 > this.d) {
                                a = this.d;
                            } else {
                                a = i2;
                            }
                            if (this.b == 1) {
                                min = a(sArr2, i, min, a);
                            } else {
                                b(sArr2, i, 1);
                                min = a(this.f, 0, min, a);
                            }
                        } else {
                            min = a;
                        }
                    }
                    a = this.w;
                    i2 = this.x;
                    if (a == 0 || this.u == 0) {
                        obj = null;
                    } else if (i2 > a * 3) {
                        obj = null;
                    } else if (a * 2 <= this.v * 3) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        i4 = this.u;
                    } else {
                        i4 = min;
                    }
                    this.v = this.w;
                    this.u = min;
                    if (((double) s) > 1.0d) {
                        sArr = this.h;
                        if (s >= 2.0f) {
                            min = (int) (((float) i4) / (s - 1.0f));
                        } else {
                            this.t = (int) ((((float) i4) * (2.0f - s)) / (s - 1.0f));
                            min = i4;
                        }
                        a(min);
                        a(min, this.b, this.j, this.r, sArr, i, sArr, i + i4);
                        this.r += min;
                        i += min + i4;
                    } else {
                        int i5;
                        Object obj2 = this.h;
                        if (s < 0.5f) {
                            i5 = (int) ((((float) i4) * s) / (1.0f - s));
                        } else {
                            this.t = (int) ((((float) i4) * ((2.0f * s) - 1.0f)) / (1.0f - s));
                            i5 = i4;
                        }
                        a(i4 + i5);
                        System.arraycopy(obj2, this.b * i, this.j, this.r * this.b, this.b * i4);
                        a(i5, this.b, this.j, this.r + i4, obj2, i + i4, obj2, i);
                        this.r += i4 + i5;
                        i += i5;
                    }
                }
            } while (this.e + i <= i3);
            min = this.q - i;
            System.arraycopy(this.h, this.b * i, this.h, 0, this.b * min);
            this.q = min;
        }
        if (this.p != 1.0f) {
            float f = this.p;
            if (this.r != originalNumOutputSamples) {
                int i6;
                a = (int) (((float) this.a) / f);
                min = this.a;
                while (true) {
                    i2 = min;
                    i6 = a;
                    if (i6 <= 16384 && i2 <= 16384) {
                        break;
                    }
                    a = i6 / 2;
                    min = i2 / 2;
                }
                min = this.r - originalNumOutputSamples;
                if (this.s + min > this.k) {
                    this.k += (this.k / 2) + min;
                    this.l = Arrays.copyOf(this.l, this.k * this.b);
                }
                System.arraycopy(this.j, this.b * originalNumOutputSamples, this.l, this.s * this.b, this.b * min);
                this.r = originalNumOutputSamples;
                this.s = min + this.s;
                for (min = 0; min < this.s - 1; min++) {
                    while ((this.m + 1) * i6 > this.n * i2) {
                        a(1);
                        for (a = 0; a < this.b; a++) {
                            sArr = this.j;
                            i = (this.r * this.b) + a;
                            short[] sArr3 = this.l;
                            int i7 = (this.b * min) + a;
                            short s2 = sArr3[i7];
                            short s3 = sArr3[i7 + this.b];
                            int i8 = (this.m + 1) * i6;
                            i7 = i8 - (this.n * i2);
                            int i9 = i8 - (this.m * i6);
                            sArr[i] = (short) (((s3 * (i9 - i7)) + (s2 * i7)) / i9);
                        }
                        this.n++;
                        this.r++;
                    }
                    this.m++;
                    if (this.m == i2) {
                        this.m = 0;
                        a.b(this.n == i6);
                        this.n = 0;
                    }
                }
                min = this.s - 1;
                if (min != 0) {
                    System.arraycopy(this.l, this.b * min, this.l, 0, (this.s - min) * this.b);
                    this.s -= min;
                }
            }
        }
    }

    private static void a(int numSamples, int numChannels, short[] out, int outPos, short[] rampDown, int rampDownPos, short[] rampUp, int rampUpPos) {
        for (int i = 0; i < numChannels; i++) {
            int o = (outPos * numChannels) + i;
            int u = (rampUpPos * numChannels) + i;
            int d = (rampDownPos * numChannels) + i;
            for (int t = 0; t < numSamples; t++) {
                out[o] = (short) (((rampDown[d] * (numSamples - t)) + (rampUp[u] * t)) / numSamples);
                o += numChannels;
                d += numChannels;
                u += numChannels;
            }
        }
    }
}
