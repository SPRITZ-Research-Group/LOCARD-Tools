package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.net.HttpURLConnection;
import java.util.List;

public class ab extends AsyncTask<Void, Void, List<af>> {
    private static final String a = ab.class.getCanonicalName();
    private final HttpURLConnection b;
    private final ac c;
    private Exception d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        super.onPostExecute((List) obj);
        if (this.d != null) {
            String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.d.getMessage()});
        }
    }

    public ab(ac acVar) {
        this(acVar, (byte) 0);
    }

    private ab(ac acVar, byte b) {
        this.c = acVar;
        this.b = null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{RequestAsyncTask:  connection: ");
        stringBuilder.append(this.b);
        stringBuilder.append(", requests: ");
        stringBuilder.append(this.c);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (s.b()) {
            String.format("execute async task: %s", new Object[]{this});
        }
        if (this.c.c() == null) {
            Handler handler;
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.c.a(handler);
        }
    }

    private List<af> a() {
        try {
            if (this.b == null) {
                return GraphRequest.a(this.c);
            }
            return GraphRequest.a(this.b, this.c);
        } catch (Exception e) {
            this.d = e;
            return null;
        }
    }
}
