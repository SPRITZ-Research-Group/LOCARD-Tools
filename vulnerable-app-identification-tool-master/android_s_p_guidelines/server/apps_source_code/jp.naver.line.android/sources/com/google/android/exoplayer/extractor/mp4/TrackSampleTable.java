package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;

final class TrackSampleTable {
    public static final int NO_SAMPLE = -1;
    public final int[] flags;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;

    TrackSampleTable(long[] jArr, int[] iArr, long[] jArr2, int[] iArr2) {
        boolean z = false;
        Assertions.checkArgument(iArr.length == jArr2.length);
        Assertions.checkArgument(jArr.length == jArr2.length);
        if (iArr2.length == jArr2.length) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.offsets = jArr;
        this.sizes = iArr;
        this.timestampsUs = jArr2;
        this.flags = iArr2;
        this.sampleCount = jArr.length;
    }

    public final int getIndexOfEarlierOrEqualSynchronizationSample(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.timestampsUs, j, true, false);
        while (binarySearchFloor >= 0) {
            if (this.timestampsUs[binarySearchFloor] <= j && (this.flags[binarySearchFloor] & 1) != 0) {
                return binarySearchFloor;
            }
            binarySearchFloor--;
        }
        return -1;
    }

    public final int getIndexOfLaterOrEqualSynchronizationSample(long j) {
        int binarySearchCeil = Util.binarySearchCeil(this.timestampsUs, j, true, false);
        while (binarySearchCeil < this.timestampsUs.length) {
            if (this.timestampsUs[binarySearchCeil] >= j && (this.flags[binarySearchCeil] & 1) != 0) {
                return binarySearchCeil;
            }
            binarySearchCeil++;
        }
        return -1;
    }
}
