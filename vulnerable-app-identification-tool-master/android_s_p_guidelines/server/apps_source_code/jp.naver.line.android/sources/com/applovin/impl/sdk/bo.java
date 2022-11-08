package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class bo implements Runnable {
    final /* synthetic */ bm a;

    bo(bm bmVar) {
        this.a = bmVar;
    }

    public void run() {
        Builder builder = new Builder(this.a.b);
        builder.setTitle((CharSequence) this.a.a.get(ea.ag));
        builder.setMessage((CharSequence) this.a.a.get(ea.ah));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.a.a.get(ea.aj), new bp(this));
        builder.setNegativeButton((CharSequence) this.a.a.get(ea.ai), new bq(this));
        this.a.c = builder.show();
    }
}
