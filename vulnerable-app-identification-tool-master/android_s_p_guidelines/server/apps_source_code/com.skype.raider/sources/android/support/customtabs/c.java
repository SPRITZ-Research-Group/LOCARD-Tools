package android.support.customtabs;

import android.os.IBinder;

public final class c {
    private final d a;
    private final a b = new a(this) {
        final /* synthetic */ c a;

        {
            this.a = this$0;
        }
    };

    c(d callbackBinder) {
        this.a = callbackBinder;
    }

    final IBinder a() {
        return this.a.asBinder();
    }

    public final int hashCode() {
        return a().hashCode();
    }

    public final boolean equals(Object o) {
        if (o instanceof c) {
            return ((c) o).a().equals(this.a.asBinder());
        }
        return false;
    }
}
