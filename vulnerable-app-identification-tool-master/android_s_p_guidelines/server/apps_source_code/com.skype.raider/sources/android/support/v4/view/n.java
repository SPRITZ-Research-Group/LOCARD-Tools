package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract class n {
    private final DataSetObservable a = new DataSetObservable();
    private DataSetObserver b;

    public abstract int a();

    public abstract boolean a(View view, Object obj);

    public int a(Object object) {
        return -1;
    }

    public final void b() {
        synchronized (this) {
            if (this.b != null) {
                this.b.onChanged();
            }
        }
        this.a.notifyChanged();
    }

    public final void a(DataSetObserver observer) {
        this.a.registerObserver(observer);
    }

    public final void b(DataSetObserver observer) {
        this.a.unregisterObserver(observer);
    }

    final void c(DataSetObserver observer) {
        synchronized (this) {
            this.b = observer;
        }
    }

    public Object a(ViewGroup container, int position) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public void a(ViewGroup container, Object object) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }
}
