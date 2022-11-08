package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;

public final class TimeSignalCommand extends SpliceCommand {
    public static final Creator<TimeSignalCommand> CREATOR = new Creator<TimeSignalCommand>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new TimeSignalCommand[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new TimeSignalCommand(parcel.readLong(), parcel.readLong(), (byte) 0);
        }
    };
    public final long a;
    public final long b;

    /* synthetic */ TimeSignalCommand(long x0, long x1, byte b) {
        this(x0, x1);
    }

    private TimeSignalCommand(long ptsTime, long playbackPositionUs) {
        this.a = ptsTime;
        this.b = playbackPositionUs;
    }

    static TimeSignalCommand a(k sectionData, long ptsAdjustment, q timestampAdjuster) {
        long ptsTime = a(sectionData, ptsAdjustment);
        return new TimeSignalCommand(ptsTime, timestampAdjuster.a(ptsTime));
    }

    static long a(k sectionData, long ptsAdjustment) {
        long firstByte = (long) sectionData.g();
        if ((128 & firstByte) != 0) {
            return ((((1 & firstByte) << 32) | sectionData.l()) + ptsAdjustment) & 8589934591L;
        }
        return -9223372036854775807L;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.a);
        dest.writeLong(this.b);
    }
}
