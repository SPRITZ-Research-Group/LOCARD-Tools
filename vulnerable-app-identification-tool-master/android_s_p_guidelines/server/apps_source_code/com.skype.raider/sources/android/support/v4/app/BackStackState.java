package android.support.v4.app;

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

    public BackStackState(b bse) {
        int numOps = bse.c.size();
        this.a = new int[(numOps * 6)];
        if (bse.j) {
            int pos = 0;
            for (int opNum = 0; opNum < numOps; opNum++) {
                a op = (a) bse.c.get(opNum);
                int i = pos + 1;
                this.a[pos] = op.a;
                pos = i + 1;
                this.a[i] = op.b != null ? op.b.mIndex : -1;
                i = pos + 1;
                this.a[pos] = op.c;
                pos = i + 1;
                this.a[i] = op.d;
                i = pos + 1;
                this.a[pos] = op.e;
                pos = i + 1;
                this.a[i] = op.f;
            }
            this.b = bse.h;
            this.c = bse.i;
            this.d = bse.l;
            this.e = bse.n;
            this.f = bse.o;
            this.g = bse.p;
            this.h = bse.q;
            this.i = bse.r;
            this.j = bse.s;
            this.k = bse.t;
            this.l = bse.u;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackState(Parcel in) {
        this.a = in.createIntArray();
        this.b = in.readInt();
        this.c = in.readInt();
        this.d = in.readString();
        this.e = in.readInt();
        this.f = in.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.h = in.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.j = in.createStringArrayList();
        this.k = in.createStringArrayList();
        this.l = in.readInt() != 0;
    }

    public final b a(j fm) {
        b bse = new b(fm);
        int pos = 0;
        int num = 0;
        while (pos < this.a.length) {
            a op = new a();
            int pos2 = pos + 1;
            op.a = this.a[pos];
            if (j.a) {
                new StringBuilder("Instantiate ").append(bse).append(" op #").append(num).append(" base fragment #").append(this.a[pos2]);
            }
            pos = pos2 + 1;
            int findex = this.a[pos2];
            if (findex >= 0) {
                op.b = (Fragment) fm.f.get(findex);
            } else {
                op.b = null;
            }
            pos2 = pos + 1;
            op.c = this.a[pos];
            pos = pos2 + 1;
            op.d = this.a[pos2];
            pos2 = pos + 1;
            op.e = this.a[pos];
            pos = pos2 + 1;
            op.f = this.a[pos2];
            bse.d = op.c;
            bse.e = op.d;
            bse.f = op.e;
            bse.g = op.f;
            bse.a(op);
            num++;
        }
        bse.h = this.b;
        bse.i = this.c;
        bse.l = this.d;
        bse.n = this.e;
        bse.j = true;
        bse.o = this.f;
        bse.p = this.g;
        bse.q = this.h;
        bse.r = this.i;
        bse.s = this.j;
        bse.t = this.k;
        bse.u = this.l;
        bse.a(1);
        return bse;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int i = 0;
        dest.writeIntArray(this.a);
        dest.writeInt(this.b);
        dest.writeInt(this.c);
        dest.writeString(this.d);
        dest.writeInt(this.e);
        dest.writeInt(this.f);
        TextUtils.writeToParcel(this.g, dest, 0);
        dest.writeInt(this.h);
        TextUtils.writeToParcel(this.i, dest, 0);
        dest.writeStringList(this.j);
        dest.writeStringList(this.k);
        if (this.l) {
            i = 1;
        }
        dest.writeInt(i);
    }
}
