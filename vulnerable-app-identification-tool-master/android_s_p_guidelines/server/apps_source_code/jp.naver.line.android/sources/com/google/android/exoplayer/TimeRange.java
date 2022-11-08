package com.google.android.exoplayer;

public final class TimeRange {
    public static final int TYPE_SNAPSHOT = 0;
    private final long endTimeUs;
    private final long startTimeUs;
    public final int type;

    public TimeRange(int i, long j, long j2) {
        this.type = i;
        this.startTimeUs = j;
        this.endTimeUs = j2;
    }

    public final long[] getCurrentBoundsMs(long[] jArr) {
        jArr = getCurrentBoundsUs(jArr);
        jArr[0] = jArr[0] / 1000;
        jArr[1] = jArr[1] / 1000;
        return jArr;
    }

    public final long[] getCurrentBoundsUs(long[] jArr) {
        if (jArr == null || jArr.length < 2) {
            jArr = new long[2];
        }
        jArr[0] = this.startTimeUs;
        jArr[1] = this.endTimeUs;
        return jArr;
    }

    public final int hashCode() {
        return (int) (((long) ((this.type << 30) | 0)) | (((this.startTimeUs + this.endTimeUs) / 1000) & 1073741823));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeRange)) {
            return false;
        }
        TimeRange timeRange = (TimeRange) obj;
        return timeRange.type == this.type && timeRange.startTimeUs == this.startTimeUs && timeRange.endTimeUs == this.endTimeUs;
    }
}
