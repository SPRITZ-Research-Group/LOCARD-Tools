package com.google.android.exoplayer.audio;

import android.annotation.TargetApi;
import java.util.Arrays;

@TargetApi(21)
public final class AudioCapabilities {
    private final int maxChannelCount;
    private final int[] supportedEncodings;

    public AudioCapabilities(int[] iArr, int i) {
        if (iArr != null) {
            this.supportedEncodings = Arrays.copyOf(iArr, iArr.length);
            Arrays.sort(this.supportedEncodings);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = i;
    }

    public final boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }

    public final int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    public final int hashCode() {
        return this.maxChannelCount + (Arrays.hashCode(this.supportedEncodings) * 31);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("AudioCapabilities[maxChannelCount=");
        stringBuilder.append(this.maxChannelCount);
        stringBuilder.append(", supportedEncodings=");
        stringBuilder.append(Arrays.toString(this.supportedEncodings));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
