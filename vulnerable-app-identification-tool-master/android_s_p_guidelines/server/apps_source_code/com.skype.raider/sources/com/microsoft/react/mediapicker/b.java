package com.microsoft.react.mediapicker;

import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ai;
import com.microsoft.react.a.c;
import com.microsoft.react.a.e;
import java.util.ArrayList;
import java.util.List;

final class b {
    private AsyncTask a;
    private List<a> b = new ArrayList();
    private List<c> c = new ArrayList();
    private final ai d;
    private final String e;
    private final boolean f;
    private final boolean g;

    public static class a {
        public void a(b galleryMedia) {
        }
    }

    b(ai context, String album, boolean allowVideo, boolean disableGifs) {
        this.d = context;
        this.e = album;
        this.f = allowVideo;
        this.g = disableGifs;
    }

    public final void a(a listener) {
        this.b.add(listener);
    }

    public final int a() {
        return this.c.size();
    }

    public final int a(c ch) {
        return this.c.indexOf(ch);
    }

    public final void b() {
        if (this.a != null) {
            this.a.cancel(true);
        }
        this.a = new AsyncTask<Object, Void, List<c>>(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ void onPostExecute(Object obj) {
                List list = (List) obj;
                this.a.c.clear();
                this.a.c.addAll(list);
                this.a.a = null;
                for (a a : this.a.b) {
                    a.a(this.a);
                }
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                return e.a(this.a.d, this.a.f, this.a.g, this.a.e);
            }
        };
        this.a.execute(new Object[0]);
        FLog.i("MediaPicker.Gallery", "Loading gallery data");
    }

    public final c a(int index) {
        return (c) this.c.get(index);
    }
}
