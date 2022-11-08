package com.applovin.impl.adview;

class an implements Runnable {
    final /* synthetic */ ap a;
    final /* synthetic */ int b;
    final /* synthetic */ am c;

    an(am amVar, ap apVar, int i) {
        this.c = amVar;
        this.a = apVar;
        this.b = i;
    }

    public void run() {
        ao b = this.a.c();
        StringBuilder stringBuilder;
        if (!b.b()) {
            stringBuilder = new StringBuilder("Ending countdown for ");
            stringBuilder.append(this.a.a());
            this.c.a.d("CountdownManager", stringBuilder.toString());
        } else if (this.c.d.get() == this.b) {
            try {
                b.a();
            } catch (Throwable th) {
                StringBuilder stringBuilder2 = new StringBuilder("Encountered error on countdown step for: ");
                stringBuilder2.append(this.a.a());
                this.c.a.e("CountdownManager", stringBuilder2.toString(), th);
            }
            this.c.a(this.a, this.b);
        } else {
            stringBuilder = new StringBuilder("Killing duplicate countdown from previous generation: ");
            stringBuilder.append(this.a.a());
            this.c.a.w("CountdownManager", stringBuilder.toString());
        }
    }
}
