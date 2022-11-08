package androidx.core.app;

import android.os.AsyncTask;

final class l extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ JobIntentService a;

    l(JobIntentService jobIntentService) {
        this.a = jobIntentService;
    }

    protected final /* synthetic */ void onCancelled(Object obj) {
        this.a.a();
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        this.a.a();
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        while (true) {
            p b = this.a.b();
            if (b == null) {
                return null;
            }
            this.a.a(b.a());
            b.b();
        }
    }
}
