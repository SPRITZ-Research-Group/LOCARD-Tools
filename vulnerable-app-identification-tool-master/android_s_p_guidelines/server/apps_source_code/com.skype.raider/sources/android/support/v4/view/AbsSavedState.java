package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

public abstract class AbsSavedState implements Parcelable {
    public static final Creator<AbsSavedState> CREATOR = new ClassLoaderCreator<AbsSavedState>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return AnonymousClass2.a(parcel, classLoader);
        }

        private static AbsSavedState a(Parcel in, ClassLoader loader) {
            if (in.readParcelable(loader) == null) {
                return AbsSavedState.a;
            }
            throw new IllegalStateException("superState must be null");
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AbsSavedState[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return AnonymousClass2.a(parcel, null);
        }
    };
    public static final AbsSavedState a = new AbsSavedState() {
    };
    private final Parcelable b;

    /* synthetic */ AbsSavedState(byte b) {
        this();
    }

    private AbsSavedState() {
        this.b = null;
    }

    protected AbsSavedState(Parcelable superState) {
        if (superState == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        if (superState == a) {
            superState = null;
        }
        this.b = superState;
    }

    protected AbsSavedState(Parcel source, ClassLoader loader) {
        Parcelable superState = source.readParcelable(loader);
        if (superState == null) {
            superState = a;
        }
        this.b = superState;
    }

    public final Parcelable a() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.b, flags);
    }
}
