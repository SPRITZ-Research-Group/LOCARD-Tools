package com.microsoft.dl.video.capture.api;

import java.io.Serializable;
import java.util.regex.Pattern;

public class FpsRange implements Serializable, Comparable<FpsRange> {
    private static final Pattern a = Pattern.compile("-");
    private final int b;
    private final int c;

    public FpsRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("invalid parameters min=" + min + ", max=" + max);
        }
        this.b = min;
        this.c = max;
    }

    public FpsRange(String range) {
        String[] dimensions = a.split(range);
        this.b = Integer.parseInt(dimensions[0]);
        this.c = Integer.parseInt(dimensions[1]);
        if (this.b > this.c) {
            throw new IllegalArgumentException("invalid parameters min=" + this.b + ", max=" + this.c);
        }
    }

    public final int getMin() {
        return this.b;
    }

    public final int getMax() {
        return this.c;
    }

    public final int hashCode() {
        return ((this.c + 31) * 31) + this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FpsRange other = (FpsRange) obj;
        if (this.c == other.c && this.b == other.b) {
            return true;
        }
        return false;
    }

    public final int compareTo(FpsRange another) {
        int minDiff = this.b - another.b;
        int maxDiff = this.c - another.c;
        if (minDiff < 0) {
            return -1;
        }
        if (minDiff > 0) {
            return 1;
        }
        if (maxDiff >= 0) {
            return maxDiff > 0 ? 1 : 0;
        } else {
            return -1;
        }
    }

    public final String toString() {
        return (((float) this.b) / 1000.0f) + "-" + (((float) this.c) / 1000.0f);
    }
}
