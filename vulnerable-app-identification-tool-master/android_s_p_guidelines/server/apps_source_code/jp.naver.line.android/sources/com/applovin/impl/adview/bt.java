package com.applovin.impl.adview;

class bt implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ bs c;

    bt(bs bsVar, int i, int i2) {
        this.c = bsVar;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        StringBuilder stringBuilder = new StringBuilder("Media player error (");
        stringBuilder.append(this.a);
        stringBuilder.append(",");
        stringBuilder.append(this.b);
        stringBuilder.append(").");
        this.c.a.a.logger.e("InterActivity", stringBuilder.toString());
        this.c.a.a.handleMediaError();
    }
}
