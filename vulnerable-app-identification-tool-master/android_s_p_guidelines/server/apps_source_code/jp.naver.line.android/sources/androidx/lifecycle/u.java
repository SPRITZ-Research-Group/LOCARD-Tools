package androidx.lifecycle;

abstract class u {
    final ac<? super T> c;
    boolean d;
    int e = -1;
    final /* synthetic */ LiveData f;

    abstract boolean a();

    boolean a(o oVar) {
        return false;
    }

    void b() {
    }

    u(LiveData liveData, ac<? super T> acVar) {
        this.f = liveData;
        this.c = acVar;
    }

    final void a(boolean z) {
        if (z != this.d) {
            this.d = z;
            int i = 1;
            Object obj = this.f.c == 0 ? 1 : null;
            LiveData liveData = this.f;
            int i2 = liveData.c;
            if (!this.d) {
                i = -1;
            }
            liveData.c = i2 + i;
            if (obj != null && this.d) {
                this.f.c();
            }
            if (this.f.c == 0 && !this.d) {
                this.f.d();
            }
            if (this.d) {
                this.f.a(this);
            }
        }
    }
}
