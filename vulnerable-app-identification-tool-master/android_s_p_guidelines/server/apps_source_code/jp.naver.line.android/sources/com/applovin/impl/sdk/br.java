package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class br implements Runnable {
    final /* synthetic */ bm a;

    br(bm bmVar) {
        this.a = bmVar;
    }

    public void run() {
        Builder builder = new Builder(this.a.b);
        builder.setTitle((CharSequence) this.a.a.get(ea.al));
        builder.setMessage((CharSequence) this.a.a.get(ea.am));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.a.a.get(ea.ao), null);
        builder.setNegativeButton((CharSequence) this.a.a.get(ea.an), new bs(this));
        this.a.c = builder.show();
    }
}
