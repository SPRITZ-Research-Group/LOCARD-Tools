package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR = new Creator<FragmentManagerState>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new FragmentManagerState[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }
    };
    FragmentState[] a;
    int[] b;
    BackStackState[] c;
    int d = -1;
    int e;

    public FragmentManagerState(Parcel in) {
        this.a = (FragmentState[]) in.createTypedArray(FragmentState.CREATOR);
        this.b = in.createIntArray();
        this.c = (BackStackState[]) in.createTypedArray(BackStackState.CREATOR);
        this.d = in.readInt();
        this.e = in.readInt();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.a, flags);
        dest.writeIntArray(this.b);
        dest.writeTypedArray(this.c, flags);
        dest.writeInt(this.d);
        dest.writeInt(this.e);
    }
}
