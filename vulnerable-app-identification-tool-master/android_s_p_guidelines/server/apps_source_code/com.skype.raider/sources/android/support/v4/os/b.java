package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

@Deprecated
public final class b {

    static class a<T> implements ClassLoaderCreator<T> {
        private final c<T> a;

        a(c<T> callbacks) {
            this.a = callbacks;
        }

        public final T createFromParcel(Parcel in) {
            return this.a.a(in, null);
        }

        public final T createFromParcel(Parcel in, ClassLoader loader) {
            return this.a.a(in, loader);
        }

        public final T[] newArray(int size) {
            return this.a.a(size);
        }
    }

    @Deprecated
    public static <T> Creator<T> a(c<T> callbacks) {
        return new a(callbacks);
    }
}
