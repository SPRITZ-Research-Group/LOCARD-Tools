package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelableVolumeInfo implements Parcelable {
    public static final Creator<ParcelableVolumeInfo> CREATOR = new Creator<ParcelableVolumeInfo>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ParcelableVolumeInfo[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }
    };
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public ParcelableVolumeInfo(int volumeType, int audioStream, int controlType, int maxVolume, int currentVolume) {
        this.a = volumeType;
        this.b = audioStream;
        this.c = controlType;
        this.d = maxVolume;
        this.e = currentVolume;
    }

    public ParcelableVolumeInfo(Parcel from) {
        this.a = from.readInt();
        this.c = from.readInt();
        this.d = from.readInt();
        this.e = from.readInt();
        this.b = from.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeInt(this.c);
        dest.writeInt(this.d);
        dest.writeInt(this.e);
        dest.writeInt(this.b);
    }
}
