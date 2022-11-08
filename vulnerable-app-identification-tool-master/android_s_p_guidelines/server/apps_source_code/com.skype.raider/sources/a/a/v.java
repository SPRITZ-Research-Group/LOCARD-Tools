package a.a;

import a.a.w.a;
import a.a.w.b;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.appboy.f.c;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class v {
    private static final String a = c.a(v.class);
    private final w b;

    public v(File file) {
        this.b = w.a(file);
    }

    public final Bitmap a(String str) {
        b a;
        Throwable e;
        Throwable th;
        Bitmap bitmap = null;
        String num = Integer.toString(str.hashCode());
        try {
            a = this.b.a(num);
            try {
                bitmap = BitmapFactory.decodeStream(a.a());
                if (a != null) {
                    a.close();
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            a = bitmap;
        } catch (Throwable e4) {
            a = bitmap;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return bitmap;
        try {
            c.d(a, "Failed to get bitmap from disk cache for key " + num, e4);
            if (a != null) {
                a.close();
            }
            c.b(a, "Failed to load image from disk cache: " + num);
            return bitmap;
        } catch (Throwable th2) {
            th = th2;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public final boolean b(String str) {
        boolean z = false;
        String num = Integer.toString(str.hashCode());
        b bVar = null;
        try {
            b a = this.b.a(num);
            if (a != null) {
                z = true;
            }
            if (a != null) {
                a.close();
            }
        } catch (Throwable e) {
            c.d(a, "Error while retrieving disk for key " + num, e);
            if (bVar != null) {
                bVar.close();
            }
        } catch (Throwable th) {
            if (bVar != null) {
                bVar.close();
            }
        }
        return z;
    }

    public final void a(String str, Bitmap bitmap) {
        String num = Integer.toString(str.hashCode());
        OutputStream outputStream = null;
        try {
            a b = this.b.b(num);
            outputStream = b.a();
            bitmap.compress(CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            b.b();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable e) {
                    c.d(a, "Exception while closing disk cache output stream for key" + num, e);
                }
            }
        } catch (Throwable e2) {
            c.d(a, "Exception while producing output stream or compressing bitmap for key " + num, e2);
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable e22) {
                    c.d(a, "Exception while closing disk cache output stream for key" + num, e22);
                }
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable e3) {
                    c.d(a, "Exception while closing disk cache output stream for key" + num, e3);
                }
            }
        }
    }
}
