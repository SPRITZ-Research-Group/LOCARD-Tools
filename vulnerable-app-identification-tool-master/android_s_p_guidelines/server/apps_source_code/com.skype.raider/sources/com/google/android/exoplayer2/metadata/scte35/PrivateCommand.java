package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.d.k;

public final class PrivateCommand extends SpliceCommand {
    public static final Creator<PrivateCommand> CREATOR = new Creator<PrivateCommand>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PrivateCommand[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PrivateCommand(parcel, (byte) 0);
        }
    };
    public final long a;
    public final long b;
    public final byte[] c;

    /* synthetic */ PrivateCommand(Parcel x0, byte b) {
        this(x0);
    }

    private PrivateCommand(long identifier, byte[] commandBytes, long ptsAdjustment) {
        this.a = ptsAdjustment;
        this.b = identifier;
        this.c = commandBytes;
    }

    private PrivateCommand(Parcel in) {
        this.a = in.readLong();
        this.b = in.readLong();
        this.c = new byte[in.readInt()];
        in.readByteArray(this.c);
    }

    static PrivateCommand a(k sectionData, int commandLength, long ptsAdjustment) {
        long identifier = sectionData.l();
        byte[] privateBytes = new byte[(commandLength - 4)];
        sectionData.a(privateBytes, 0, privateBytes.length);
        return new PrivateCommand(identifier, privateBytes, ptsAdjustment);
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.a);
        dest.writeLong(this.b);
        dest.writeInt(this.c.length);
        dest.writeByteArray(this.c);
    }
}
