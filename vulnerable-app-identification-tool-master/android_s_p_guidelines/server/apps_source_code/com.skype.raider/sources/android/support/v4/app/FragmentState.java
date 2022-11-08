package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new Creator<FragmentState>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new FragmentState[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }
    };
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    Fragment l;

    public FragmentState(Fragment frag) {
        this.a = frag.getClass().getName();
        this.b = frag.mIndex;
        this.c = frag.mFromLayout;
        this.d = frag.mFragmentId;
        this.e = frag.mContainerId;
        this.f = frag.mTag;
        this.g = frag.mRetainInstance;
        this.h = frag.mDetached;
        this.i = frag.mArguments;
        this.j = frag.mHidden;
    }

    public FragmentState(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.a = in.readString();
        this.b = in.readInt();
        this.c = in.readInt() != 0;
        this.d = in.readInt();
        this.e = in.readInt();
        this.f = in.readString();
        if (in.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.g = z;
        if (in.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        this.i = in.readBundle();
        if (in.readInt() == 0) {
            z2 = false;
        }
        this.j = z2;
        this.k = in.readBundle();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeString(this.a);
        dest.writeInt(this.b);
        dest.writeInt(this.c ? 1 : 0);
        dest.writeInt(this.d);
        dest.writeInt(this.e);
        dest.writeString(this.f);
        if (this.g) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (this.h) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeBundle(this.i);
        if (!this.j) {
            i2 = 0;
        }
        dest.writeInt(i2);
        dest.writeBundle(this.k);
    }
}
