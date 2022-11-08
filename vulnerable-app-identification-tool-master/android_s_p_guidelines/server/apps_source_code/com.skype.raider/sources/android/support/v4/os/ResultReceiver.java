package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ResultReceiver implements Parcelable {
    public static final Creator<ResultReceiver> CREATOR = new Creator<ResultReceiver>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ResultReceiver[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }
    };
    final boolean a = false;
    final Handler b = null;
    a c;

    class a extends android.support.v4.os.a.a {
        final /* synthetic */ ResultReceiver a;

        a(ResultReceiver this$0) {
            this.a = this$0;
        }

        public final void a(int resultCode, Bundle resultData) {
            if (this.a.b != null) {
                this.a.b.post(new b(this.a, resultCode, resultData));
            } else {
                this.a.a(resultCode, resultData);
            }
        }
    }

    class b implements Runnable {
        final int a;
        final Bundle b;
        final /* synthetic */ ResultReceiver c;

        b(ResultReceiver this$0, int resultCode, Bundle resultData) {
            this.c = this$0;
            this.a = resultCode;
            this.b = resultData;
        }

        public final void run() {
            this.c.a(this.a, this.b);
        }
    }

    public final void b(int resultCode, Bundle resultData) {
        if (this.a) {
            if (this.b != null) {
                this.b.post(new b(this, resultCode, resultData));
            } else {
                a(resultCode, resultData);
            }
        } else if (this.c != null) {
            try {
                this.c.a(resultCode, resultData);
            } catch (RemoteException e) {
            }
        }
    }

    protected void a(int resultCode, Bundle resultData) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        synchronized (this) {
            if (this.c == null) {
                this.c = new a(this);
            }
            out.writeStrongBinder(this.c.asBinder());
        }
    }

    ResultReceiver(Parcel in) {
        this.c = android.support.v4.os.a.a.a(in.readStrongBinder());
    }
}
