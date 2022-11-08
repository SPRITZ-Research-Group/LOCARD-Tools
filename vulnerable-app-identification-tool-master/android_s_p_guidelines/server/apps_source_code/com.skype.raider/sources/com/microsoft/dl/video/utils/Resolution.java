package com.microsoft.dl.video.utils;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Resolution implements Serializable, Comparable<Resolution> {
    private static final Pattern a = Pattern.compile("x");
    private final int b;
    private final int c;

    public Resolution(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("invalid parameters width=" + width + ", height=" + height);
        }
        this.b = width;
        this.c = height;
    }

    public Resolution(String resolution) {
        String[] dimensions = a.split(resolution);
        this.b = Integer.parseInt(dimensions[0]);
        this.c = Integer.parseInt(dimensions[1]);
        if (this.b < 0 || this.c < 0) {
            throw new IllegalArgumentException("invalid parameters width=" + this.b + ", height=" + this.c);
        }
    }

    public final int getWidth() {
        return this.b;
    }

    public final int getHeight() {
        return this.c;
    }

    public final int getNumPixels() {
        return this.b * this.c;
    }

    public final String toString() {
        return this.b + "x" + this.c;
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
        Resolution other = (Resolution) obj;
        if (this.b == other.b && this.c == other.c) {
            return true;
        }
        return false;
    }

    public final int compareTo(Resolution another) {
        if (this.b > another.b) {
            return 1;
        }
        if (this.b < another.b) {
            return -1;
        }
        if (this.c <= another.c) {
            return this.c < another.c ? -1 : 0;
        } else {
            return 1;
        }
    }
}
