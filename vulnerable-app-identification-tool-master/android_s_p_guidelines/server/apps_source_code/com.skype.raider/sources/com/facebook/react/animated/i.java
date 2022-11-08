package com.facebook.react.animated;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.n;
import javax.annotation.Nullable;

final class i extends q {
    private final double[] g;
    private final double[] h;
    private final String i;
    private final String j;
    @Nullable
    private q k;

    private static double[] a(al ary) {
        double[] res = new double[ary.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ary.getDouble(i);
        }
        return res;
    }

    public i(am config) {
        this.g = a(config.getArray("inputRange"));
        this.h = a(config.getArray("outputRange"));
        this.i = config.getString("extrapolateLeft");
        this.j = config.getString("extrapolateRight");
    }

    public final void a(b parent) {
        if (this.k != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (parent instanceof q) {
            this.k = (q) parent;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    public final void b(b parent) {
        if (parent != this.k) {
            throw new IllegalArgumentException("Invalid parent node provided");
        }
        this.k = null;
    }

    public final void a() {
        if (this.k == null) {
            throw new IllegalStateException("Trying to update interpolation node that has not been attached to the parent");
        }
        this.e = a(this.k.b(), this.g, this.h, this.i, this.j);
    }

    private static double a(double value, double[] inputRange, double[] outputRange, String extrapolateLeft, String extrapolateRight) {
        Object obj;
        double d;
        int i = 1;
        while (i < inputRange.length - 1 && inputRange[i] < value) {
            i++;
        }
        int rangeIndex = i - 1;
        double d2 = inputRange[rangeIndex];
        double d3 = inputRange[rangeIndex + 1];
        double d4 = outputRange[rangeIndex];
        double d5 = outputRange[rangeIndex + 1];
        if (value < d2) {
            obj = -1;
            switch (extrapolateLeft.hashCode()) {
                case -1289044198:
                    if (extrapolateLeft.equals("extend")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -135761730:
                    if (extrapolateLeft.equals("identity")) {
                        obj = null;
                        break;
                    }
                    break;
                case 94742715:
                    if (extrapolateLeft.equals("clamp")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return value;
                case 1:
                    d = d2;
                    break;
                case 2:
                    d = value;
                    break;
                default:
                    throw new n("Invalid extrapolation type " + extrapolateLeft + "for left extrapolation");
            }
        }
        d = value;
        if (d > d3) {
            obj = -1;
            switch (extrapolateRight.hashCode()) {
                case -1289044198:
                    if (extrapolateRight.equals("extend")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -135761730:
                    if (extrapolateRight.equals("identity")) {
                        obj = null;
                        break;
                    }
                    break;
                case 94742715:
                    if (extrapolateRight.equals("clamp")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return d;
                case 1:
                    d = d3;
                    break;
                case 2:
                    break;
                default:
                    throw new n("Invalid extrapolation type " + extrapolateRight + "for right extrapolation");
            }
        }
        return d4 + (((d - d2) * (d5 - d4)) / (d3 - d2));
    }
}
