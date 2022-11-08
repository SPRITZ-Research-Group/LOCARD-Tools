package net.hockeyapp.android.e;

import android.content.Context;
import java.io.IOException;
import java.net.URL;
import net.hockeyapp.android.b.a;
import net.hockeyapp.android.f.e;

public final class f extends e {
    private long g;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a((Long) obj);
    }

    protected final /* bridge */ /* synthetic */ void onProgressUpdate(Object[] objArr) {
    }

    public f(Context context, String urlString, a notifier) {
        super(context, urlString, notifier);
    }

    protected final Long a() {
        try {
            return Long.valueOf((long) e.a(new URL(b()), 6).getContentLength());
        } catch (IOException e) {
            new StringBuilder("Failed to get size ").append(this.c);
            e.f();
            return Long.valueOf(0);
        }
    }

    protected final void a(Integer... args) {
    }

    protected final void a(Long result) {
        this.g = result.longValue();
        if (this.g > 0) {
            this.b.a((e) this);
        } else {
            this.b.a(Boolean.valueOf(false));
        }
    }

    public final long c() {
        return this.g;
    }
}
