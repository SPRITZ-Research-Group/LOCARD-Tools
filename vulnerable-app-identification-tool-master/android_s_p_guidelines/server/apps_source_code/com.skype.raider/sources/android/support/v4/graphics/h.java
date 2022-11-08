package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({a.LIBRARY_GROUP})
public final class h {
    public static File a(Context context) {
        String prefix = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), prefix + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    @RequiresApi(19)
    private static ByteBuffer a(File file) {
        Throwable th;
        Throwable th2;
        try {
            FileInputStream fis = new FileInputStream(file);
            try {
                FileChannel channel = fis.getChannel();
                ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                fis.close();
                return map;
            } catch (Throwable th22) {
                Throwable th3 = th22;
                th22 = th;
                th = th3;
            }
            if (th22 != null) {
                try {
                    fis.close();
                } catch (Throwable th4) {
                    th22.addSuppressed(th4);
                }
            } else {
                fis.close();
            }
            throw th;
            throw th;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @RequiresApi(19)
    public static ByteBuffer a(Context context, Uri uri) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r", null);
            try {
                FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
                try {
                    FileChannel channel = fis.getChannel();
                    ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                    fis.close();
                    if (pfd == null) {
                        return map;
                    }
                    pfd.close();
                    return map;
                } catch (Throwable th22) {
                    th3 = th22;
                    th22 = th;
                    th = th3;
                }
                if (pfd != null) {
                    if (th22 == null) {
                        pfd.close();
                    } else {
                        try {
                            pfd.close();
                        } catch (Throwable th4) {
                            th22.addSuppressed(th4);
                        }
                    }
                }
                throw th;
                if (th22 != null) {
                    fis.close();
                } else {
                    fis.close();
                }
                throw th;
                throw th;
                throw th;
            } catch (Throwable th222) {
                th3 = th222;
                th222 = th;
                th = th3;
            }
        } catch (IOException e) {
            return null;
        }
    }

    @RequiresApi(19)
    public static ByteBuffer a(Context context, Resources res, int id) {
        ByteBuffer byteBuffer = null;
        File tmpFile = a(context);
        if (tmpFile != null) {
            try {
                if (a(tmpFile, res, id)) {
                    byteBuffer = a(tmpFile);
                    tmpFile.delete();
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean a(File file, InputStream is) {
        IOException e;
        Throwable th;
        Closeable os = null;
        try {
            Closeable os2 = new FileOutputStream(file, false);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen != -1) {
                        os2.write(buffer, 0, readLen);
                    } else {
                        a(os2);
                        os = os2;
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                os = os2;
                try {
                    new StringBuilder("Error copying resource contents to temp file: ").append(e.getMessage());
                    a(os);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    a(os);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                os = os2;
                a(os);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            new StringBuilder("Error copying resource contents to temp file: ").append(e.getMessage());
            a(os);
            return false;
        }
    }

    public static boolean a(File file, Resources res, int id) {
        Closeable is = null;
        try {
            is = res.openRawResource(id);
            boolean a = a(file, (InputStream) is);
            return a;
        } finally {
            a(is);
        }
    }

    public static void a(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
