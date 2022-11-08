package addon.greenrobot.eventbus;

final class n {
    private m a;
    private m b;

    n() {
    }

    final synchronized void a(m mVar) {
        if (mVar != null) {
            if (this.b != null) {
                this.b.c = mVar;
                this.b = mVar;
            } else if (this.a == null) {
                this.b = mVar;
                this.a = mVar;
            } else {
                throw new IllegalStateException("Head present, but no tail");
            }
            notifyAll();
        } else {
            throw new NullPointerException("null cannot be enqueued");
        }
    }

    final synchronized m a() {
        m mVar;
        mVar = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return mVar;
    }

    final synchronized m b() throws InterruptedException {
        if (this.a == null) {
            wait(1000);
        }
        return a();
    }
}
