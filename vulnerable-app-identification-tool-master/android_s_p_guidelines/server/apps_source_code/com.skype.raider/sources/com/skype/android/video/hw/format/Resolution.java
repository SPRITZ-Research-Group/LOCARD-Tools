package com.skype.android.video.hw.format;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Resolution implements Serializable, Comparable<Resolution> {
    private static final Pattern SPLIT_REGEX = Pattern.compile("x");
    private static final long serialVersionUID = 19636167696232418L;
    private final int height;
    private final int width;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Resolution(String resolution) {
        String[] dimensions = SPLIT_REGEX.split(resolution);
        this.width = Integer.parseInt(dimensions[0]);
        this.height = Integer.parseInt(dimensions[1]);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    public int hashCode() {
        return ((this.height + 31) * 31) + this.width;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resolution other = (Resolution) obj;
        if (this.width == other.width && this.height == other.height) {
            return true;
        }
        return false;
    }

    public int compareTo(Resolution another) {
        if (this.width > another.width) {
            return 1;
        }
        if (this.width < another.width) {
            return -1;
        }
        if (this.height <= another.height) {
            return this.height < another.height ? -1 : 0;
        } else {
            return 1;
        }
    }
}
