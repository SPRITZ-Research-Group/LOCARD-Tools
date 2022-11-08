package androidx.recyclerview.widget;

import android.database.Observable;

final class ba extends Observable<bb> {
    ba() {
    }

    public final boolean a() {
        return !this.mObservers.isEmpty();
    }

    public final void b() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((bb) this.mObservers.get(size)).a();
        }
    }

    public final void a(int i, int i2) {
        a(i, i2, null);
    }

    public final void a(int i, int i2, Object obj) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((bb) this.mObservers.get(size)).a(i, i2, obj);
        }
    }

    public final void b(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((bb) this.mObservers.get(size)).b(i, i2);
        }
    }

    public final void c(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((bb) this.mObservers.get(size)).c(i, i2);
        }
    }

    public final void d(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((bb) this.mObservers.get(size)).a(i, i2, 1);
        }
    }
}
