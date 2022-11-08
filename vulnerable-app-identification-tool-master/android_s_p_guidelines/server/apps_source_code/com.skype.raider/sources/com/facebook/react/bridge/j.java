package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class j<Params, Progress> extends AsyncTask<Params, Progress, Void> {
    private final ai a;

    protected abstract void a();

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return b();
    }

    protected j(ai reactContext) {
        this.a = reactContext;
    }

    private Void b() {
        try {
            a();
        } catch (RuntimeException e) {
            this.a.a(e);
        }
        return null;
    }
}
