package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR = new Creator<BackStackState>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new BackStackState[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }
    };
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;
    final boolean l;

    public final int describeContents() {
        return 0;
    }

    public BackStackState(a aVar) {
        int size = aVar.b.size();
        this.a = new int[(size * 6)];
        if (aVar.i) {
            int i = 0;
            int i2 = 0;
            while (i < size) {
                b bVar = (b) aVar.b.get(i);
                int i3 = i2 + 1;
                this.a[i2] = bVar.a;
                int i4 = i3 + 1;
                this.a[i3] = bVar.b != null ? bVar.b.mIndex : -1;
                i3 = i4 + 1;
                this.a[i4] = bVar.c;
                i4 = i3 + 1;
                this.a[i3] = bVar.d;
                i3 = i4 + 1;
                this.a[i4] = bVar.e;
                i4 = i3 + 1;
                this.a[i3] = bVar.f;
                i++;
                i2 = i4;
            }
            this.b = aVar.g;
            this.c = aVar.h;
            this.d = aVar.k;
            this.e = aVar.m;
            this.f = aVar.n;
            this.g = aVar.o;
            this.h = aVar.p;
            this.i = aVar.q;
            this.j = aVar.r;
            this.k = aVar.s;
            this.l = aVar.t;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackState(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
        this.l = parcel.readInt() != 0;
    }

    public final a a(o oVar) {
        a aVar = new a(oVar);
        int i = 0;
        int i2 = 0;
        while (i < this.a.length) {
            b bVar = new b();
            int i3 = i + 1;
            bVar.a = this.a[i];
            if (o.a) {
                StringBuilder stringBuilder = new StringBuilder("Instantiate ");
                stringBuilder.append(aVar);
                stringBuilder.append(" op #");
                stringBuilder.append(i2);
                stringBuilder.append(" base fragment #");
                stringBuilder.append(this.a[i3]);
            }
            int i4 = i3 + 1;
            i = this.a[i3];
            if (i >= 0) {
                bVar.b = (Fragment) oVar.f.get(i);
            } else {
                bVar.b = null;
            }
            i3 = i4 + 1;
            bVar.c = this.a[i4];
            i4 = i3 + 1;
            bVar.d = this.a[i3];
            i3 = i4 + 1;
            bVar.e = this.a[i4];
            i4 = i3 + 1;
            bVar.f = this.a[i3];
            aVar.c = bVar.c;
            aVar.d = bVar.d;
            aVar.e = bVar.e;
            aVar.f = bVar.f;
            aVar.a(bVar);
            i2++;
            i = i4;
        }
        aVar.g = this.b;
        aVar.h = this.c;
        aVar.k = this.d;
        aVar.m = this.e;
        aVar.i = true;
        aVar.n = this.f;
        aVar.o = this.g;
        aVar.p = this.h;
        aVar.q = this.i;
        aVar.r = this.j;
        aVar.s = this.k;
        aVar.t = this.l;
        aVar.b(1);
        return aVar;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
        parcel.writeInt(this.l);
    }
}
