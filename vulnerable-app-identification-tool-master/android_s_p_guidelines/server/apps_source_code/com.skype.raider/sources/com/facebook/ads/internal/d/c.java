package com.facebook.ads.internal.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.q.c.d;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class c {
    private static final String a = c.class.getSimpleName();
    private static c b;
    private final Context c;

    private c(Context context) {
        this.c = context;
    }

    public static c a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (c.class) {
                if (b == null) {
                    b = new c(applicationContext);
                }
            }
        }
        return b;
    }

    private static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private void a(String str, Bitmap bitmap) {
        Throwable th;
        Throwable th2;
        Closeable closeable = null;
        File file = new File(this.c.getCacheDir(), str.hashCode() + ".png");
        Closeable byteArrayOutputStream;
        Closeable fileOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                if (byteArrayOutputStream.size() >= LogConfiguration.DATA_PACKAGE_SIZE_LIMITS) {
                    a(byteArrayOutputStream);
                    a(null);
                    return;
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                    fileOutputStream.flush();
                    a(byteArrayOutputStream);
                    a(fileOutputStream);
                } catch (FileNotFoundException e) {
                    closeable = byteArrayOutputStream;
                    try {
                        new StringBuilder("Bad output destination (file=").append(file.getAbsolutePath()).append(").");
                        a(closeable);
                        a(fileOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = closeable;
                        closeable = fileOutputStream;
                        th2 = th;
                        a(byteArrayOutputStream);
                        a(closeable);
                        throw th2;
                    }
                } catch (IOException e2) {
                    closeable = fileOutputStream;
                    try {
                        new StringBuilder("Unable to write bitmap to file (url=").append(str).append(").");
                        a(byteArrayOutputStream);
                        a(closeable);
                    } catch (Throwable th4) {
                        th2 = th4;
                        a(byteArrayOutputStream);
                        a(closeable);
                        throw th2;
                    }
                } catch (OutOfMemoryError e3) {
                    closeable = fileOutputStream;
                    a(byteArrayOutputStream);
                    a(closeable);
                } catch (Throwable th5) {
                    th = th5;
                    closeable = fileOutputStream;
                    th2 = th;
                    a(byteArrayOutputStream);
                    a(closeable);
                    throw th2;
                }
            } catch (FileNotFoundException e4) {
                fileOutputStream = null;
                closeable = byteArrayOutputStream;
                new StringBuilder("Bad output destination (file=").append(file.getAbsolutePath()).append(").");
                a(closeable);
                a(fileOutputStream);
            } catch (IOException e5) {
                new StringBuilder("Unable to write bitmap to file (url=").append(str).append(").");
                a(byteArrayOutputStream);
                a(closeable);
            } catch (OutOfMemoryError e6) {
                a(byteArrayOutputStream);
                a(closeable);
            }
        } catch (FileNotFoundException e7) {
            fileOutputStream = null;
            new StringBuilder("Bad output destination (file=").append(file.getAbsolutePath()).append(").");
            a(closeable);
            a(fileOutputStream);
        } catch (IOException e8) {
            byteArrayOutputStream = null;
            new StringBuilder("Unable to write bitmap to file (url=").append(str).append(").");
            a(byteArrayOutputStream);
            a(closeable);
        } catch (OutOfMemoryError e9) {
            byteArrayOutputStream = null;
            a(byteArrayOutputStream);
            a(closeable);
        } catch (Throwable th6) {
            th2 = th6;
            byteArrayOutputStream = null;
            a(byteArrayOutputStream);
            a(closeable);
            throw th2;
        }
    }

    private boolean a(int i, int i2) {
        return i > 0 && i2 > 0 && a.d(this.c);
    }

    @Nullable
    private Bitmap b(String str, int i, int i2) {
        try {
            Bitmap a = a(i, i2) ? com.facebook.ads.internal.q.b.c.a(str.substring(7), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring(7)), null, null);
            a(str, a);
            return a;
        } catch (IOException e) {
            new StringBuilder("Failed to copy local image into cache (url=").append(str).append(").");
            return null;
        }
    }

    @Nullable
    private Bitmap c(String str, int i, int i2) {
        Closeable open;
        Throwable th;
        Bitmap bitmap = null;
        if (str.startsWith("asset:///")) {
            try {
                open = this.c.getAssets().open(str.substring(9, str.length()));
                try {
                    bitmap = a(i, i2) ? com.facebook.ads.internal.q.b.c.a((InputStream) open, i, i2) : BitmapFactory.decodeStream(open);
                    if (open != null) {
                        a(open);
                    }
                } catch (IOException e) {
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        a(open);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                open = bitmap;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                open = bitmap;
                th = th4;
                if (open != null) {
                    a(open);
                }
                throw th;
            }
        }
        if (a(i, i2)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                open = httpURLConnection.getInputStream();
                bitmap = com.facebook.ads.internal.q.b.c.a((InputStream) open, i, i2);
                a(open);
            } catch (IOException e3) {
            }
        }
        byte[] d = d.a(this.c).a(str).d();
        bitmap = BitmapFactory.decodeByteArray(d, 0, d.length);
        a(str, bitmap);
        return bitmap;
        if (open != null) {
            a(open);
        }
        return bitmap;
    }

    @Nullable
    public final Bitmap a(String str, int i, int i2) {
        File file = new File(this.c.getCacheDir(), str.hashCode() + ".png");
        return !file.exists() ? str.startsWith("file://") ? b(str, i, i2) : c(str, i, i2) : a(i, i2) ? com.facebook.ads.internal.q.b.c.a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
