package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class k<Result> extends AsyncTask<Void, Void, Result> {
    private final ai a;

    protected abstract Result a();

    protected abstract void a(Result result);

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return b();
    }

    protected k(ai reactContext) {
        this.a = reactContext;
    }

    private Result b() {
        try {
            return a();
        } catch (RuntimeException e) {
            this.a.a(e);
            throw e;
        }
    }

    protected final void onPostExecute(Result result) {
        try {
            a(result);
        } catch (RuntimeException e) {
            this.a.a(e);
        }
    }
}
