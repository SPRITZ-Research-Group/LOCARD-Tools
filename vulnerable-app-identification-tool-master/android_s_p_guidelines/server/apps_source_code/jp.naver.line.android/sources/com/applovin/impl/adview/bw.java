package com.applovin.impl.adview;

class bw implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ bv c;

    bw(bv bvVar, int i, int i2) {
        this.c = bvVar;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        StringBuilder stringBuilder = new StringBuilder("Video view error (");
        stringBuilder.append(this.a);
        stringBuilder.append(",");
        stringBuilder.append(this.b);
        stringBuilder.append(").");
        this.c.a.logger.e("InterActivity", stringBuilder.toString());
        this.c.a.handleMediaError();
    }
}
