package com.facebook.ads.internal.p.a;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class h extends AsyncTask<l, Void, n> implements c {
    private static Executor a = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private a b;
    private b c;
    private Exception d;

    public h(a aVar, b bVar) {
        this.b = aVar;
        this.c = bVar;
    }

    private n a(l... lVarArr) {
        if (lVarArr != null) {
            try {
                if (lVarArr.length > 0) {
                    return this.b.a(lVarArr[0]);
                }
            } catch (Exception e) {
                this.d = e;
                cancel(true);
                return null;
            }
        }
        throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
    }

    public final void a(l lVar) {
        super.executeOnExecutor(a, new l[]{lVar});
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((l[]) objArr);
    }

    protected final void onCancelled() {
        this.c.a(this.d);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        this.c.a((n) obj);
    }
}
