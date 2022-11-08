package com.facebook.ads.internal.view.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.d.c;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.b.e;
import java.lang.ref.WeakReference;

public class d extends AsyncTask<String, Void, Bitmap[]> {
    private static final String b = d.class.getSimpleName();
    public boolean a = false;
    private final WeakReference<Context> c;
    private final int d;
    @Nullable
    private final WeakReference<ImageView> e;
    @Nullable
    private final WeakReference<b> f;
    @Nullable
    private final WeakReference<ViewGroup> g;
    private e h;
    private int i = -1;
    private int j = -1;

    public d(ViewGroup viewGroup, int i) {
        this.c = new WeakReference(viewGroup.getContext());
        this.f = null;
        this.e = null;
        this.g = new WeakReference(viewGroup);
        this.d = i;
    }

    public d(ImageView imageView) {
        this.c = new WeakReference(imageView.getContext());
        this.f = null;
        this.e = new WeakReference(imageView);
        this.g = null;
        this.d = 0;
    }

    public d(b bVar) {
        this.c = new WeakReference(bVar.getContext());
        this.f = new WeakReference(bVar);
        this.e = null;
        this.g = null;
        this.d = 0;
    }

    private Bitmap[] a(String... strArr) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Object obj;
        Throwable th;
        String obj2;
        String str = strArr[0];
        Context context = (Context) this.c.get();
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        try {
            Bitmap a = c.a(context).a(str, this.i, this.j);
            try {
                int i = (this.f == null || this.f.get() == null) ? 0 : 1;
                int i2 = (this.g == null || this.g.get() == null) ? 0 : 1;
                if ((i == 0 && i2 == 0) || a == null || this.a) {
                    bitmap = null;
                } else {
                    e eVar = new e(a);
                    eVar.a(this.d != 0 ? this.d : Math.round(((float) a.getWidth()) / 40.0f));
                    bitmap = eVar.a();
                }
                bitmap2 = a;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                obj2 = a;
                th = th3;
            }
        } catch (Throwable th22) {
            th = th22;
            obj2 = null;
        }
        return new Bitmap[]{bitmap2, bitmap};
        b.a(a.a(th, null));
        Object bitmap22 = obj2;
        obj2 = null;
        return new Bitmap[]{bitmap22, bitmap};
    }

    public final d a() {
        this.i = -1;
        this.j = -1;
        return this;
    }

    public final d a(int i, int i2) {
        this.i = i;
        this.j = i2;
        return this;
    }

    public final d a(e eVar) {
        this.h = eVar;
        return this;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            executeOnExecutor(THREAD_POOL_EXECUTOR, new String[]{str});
        } else if (this.h != null) {
            this.h.a(false);
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        Bitmap[] bitmapArr = (Bitmap[]) obj;
        if (this.e != null) {
            ImageView imageView = (ImageView) this.e.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmapArr[0]);
            }
        }
        if (this.f != null) {
            b bVar = (b) this.f.get();
            if (bVar != null) {
                bVar.a(bitmapArr[0], bitmapArr[1]);
            }
        }
        if (!(this.g == null || this.g.get() == null || bitmapArr[1] == null)) {
            u.a((View) this.g.get(), new BitmapDrawable(((Context) this.c.get()).getResources(), bitmapArr[1]));
        }
        if (this.h != null) {
            this.h.a(bitmapArr[0] != null);
        }
    }
}
