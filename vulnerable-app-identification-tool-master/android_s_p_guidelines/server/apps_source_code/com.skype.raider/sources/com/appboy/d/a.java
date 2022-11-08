package com.appboy.d;

import a.a.v;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.util.LruCache;
import android.widget.ImageView;
import com.appboy.f.c;
import com.appboy.g;
import java.io.File;

public class a implements g {
    private static final String a = c.a(a.class);
    private LruCache<String, Bitmap> b = new LruCache<String, Bitmap>(this, com.appboy.f.b.a()) {
        final /* synthetic */ a a;

        protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            return ((Bitmap) obj2).getByteCount();
        }
    };
    private v c;
    private final Object d = new Object();
    private boolean e = true;
    private boolean f = false;

    class a extends AsyncTask<File, Void, Void> {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        /* synthetic */ a(a aVar, byte b) {
            this(aVar);
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((File[]) objArr);
        }

        private Void a(File... fileArr) {
            synchronized (this.a.d) {
                File file = fileArr[0];
                try {
                    c.b(a.a, "Initializing disk cache");
                    this.a.c = new v(file);
                } catch (Throwable e) {
                    c.d(a.a, "Caught exception creating new disk cache. Unable to create new disk cache", e);
                }
                this.a.e = false;
                this.a.d.notifyAll();
            }
            return null;
        }
    }

    class b extends AsyncTask<Void, Void, Bitmap> {
        final /* synthetic */ a a;
        private final ImageView b;
        private final Context c;
        private final com.appboy.b.b d;
        private final String e;

        /* synthetic */ b(a aVar, Context context, ImageView imageView, com.appboy.b.b bVar, String str, byte b) {
            this(aVar, context, imageView, bVar, str);
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            if (this.b != null && ((String) this.b.getTag()).equals(this.e)) {
                this.b.setImageBitmap(bitmap);
            }
        }

        private b(a aVar, Context context, ImageView imageView, com.appboy.b.b bVar, String str) {
            this.a = aVar;
            this.b = imageView;
            this.c = context;
            this.d = bVar;
            this.e = str;
            imageView.setTag(str);
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return this.a.a(this.c, this.e, this.d);
        }
    }

    public a(Context context) {
        File file = new File(context.getCacheDir().getPath() + File.separator + "appboy.imageloader.lru.cache");
        new a().execute(new File[]{file});
    }

    public final void a(Context context, String imageUrl, ImageView imageView, com.appboy.b.b viewBounds) {
        new b(this, context, imageView, viewBounds, imageUrl, (byte) 0).execute(new Void[0]);
    }

    public final Bitmap a(Context context, String imageUrl, com.appboy.b.b viewBounds) {
        Bitmap bitmap = (Bitmap) this.b.get(imageUrl);
        if (bitmap != null) {
            c.a(a, "Got bitmap from mem cache for key " + imageUrl);
        } else {
            bitmap = a(imageUrl);
            if (bitmap != null) {
                c.a(a, "Got bitmap from disk cache for key " + imageUrl);
                this.b.put(imageUrl, bitmap);
            } else {
                c.b(a, "No cache hit for bitmap: " + imageUrl);
                bitmap = null;
            }
        }
        if (bitmap != null) {
            return bitmap;
        }
        if (this.f) {
            c.b(a, "Cache is currently in offline mode. Not downloading bitmap.");
            return null;
        }
        Bitmap a = com.appboy.f.b.a(context, Uri.parse(imageUrl), viewBounds);
        if (a != null) {
            if (((Bitmap) this.b.get(imageUrl)) == null) {
                c.b(a, "Adding bitmap to mem cache for key " + imageUrl);
                this.b.put(imageUrl, a);
            }
            synchronized (this.d) {
                if (!(this.c == null || this.c.b(imageUrl))) {
                    c.b(a, "Adding bitmap to disk cache for key " + imageUrl);
                    this.c.a(imageUrl, a);
                }
            }
        }
        return a;
    }

    public final void a(boolean isOffline) {
        c.d(a, "Appboy image loader outbound network requests are now " + (isOffline ? "disabled" : "enabled"));
        this.f = isOffline;
    }

    public static void a(Context context) {
        File file = new File(context.getCacheDir(), "appboy.imageloader.lru.cache");
        c.a(a, "Deleting lru image cache directory at: " + file.getAbsolutePath());
        com.appboy.f.a.a(file);
    }

    @VisibleForTesting
    private Bitmap a(String str) {
        Bitmap bitmap = null;
        synchronized (this.d) {
            if (this.e) {
            } else if (this.c == null || !this.c.b(str)) {
            } else {
                bitmap = this.c.a(str);
            }
        }
        return bitmap;
    }
}
