package defpackage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Pair;

/* renamed from: qnk */
final class qnk extends AsyncTask<Void, Boolean, Bitmap> {
    private final String a;
    private final tes b;

    protected final /* synthetic */ void onPostExecute(Object obj) {
        this.b.a((Bitmap) obj);
    }

    public qnk(String str, tes tes) {
        this.a = str;
        this.b = tes;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        Pair a = qnn.a(this.a, false);
        return a.first != null ? (Bitmap) a.first : vte.a(vtf.PROFILE, this.a);
    }
}
