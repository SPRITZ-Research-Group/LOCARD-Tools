package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class c implements f {
    private final AssetManager a;
    private final p<? super c> b;
    private Uri c;
    private InputStream d;
    private long e;
    private boolean f;

    public static final class a extends IOException {
        public a(IOException cause) {
            super(cause);
        }
    }

    public c(Context context, p<? super c> listener) {
        this.a = context.getAssets();
        this.b = listener;
    }

    public final long a(DataSpec dataSpec) throws a {
        try {
            this.c = dataSpec.a;
            String path = this.c.getPath();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            this.d = this.a.open(path, 1);
            if (this.d.skip(dataSpec.d) < dataSpec.d) {
                throw new EOFException();
            }
            if (dataSpec.e != -1) {
                this.e = dataSpec.e;
            } else {
                this.e = (long) this.d.available();
                if (this.e == 2147483647L) {
                    this.e = -1;
                }
            }
            this.f = true;
            if (this.b != null) {
                this.b.b();
            }
            return this.e;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public final int a(byte[] buffer, int offset, int readLength) throws a {
        if (readLength == 0) {
            return 0;
        }
        if (this.e == 0) {
            return -1;
        }
        try {
            int bytesToRead;
            if (this.e == -1) {
                bytesToRead = readLength;
            } else {
                bytesToRead = (int) Math.min(this.e, (long) readLength);
            }
            int bytesRead = this.d.read(buffer, offset, bytesToRead);
            if (bytesRead != -1) {
                if (this.e != -1) {
                    this.e -= (long) bytesRead;
                }
                if (this.b == null) {
                    return bytesRead;
                }
                this.b.a(bytesRead);
                return bytesRead;
            } else if (this.e == -1) {
                return -1;
            } else {
                throw new a(new EOFException());
            }
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public final Uri a() {
        return this.c;
    }

    public final void b() throws a {
        this.c = null;
        try {
            if (this.d != null) {
                this.d.close();
            }
            this.d = null;
            if (this.f) {
                this.f = false;
                if (this.b != null) {
                    this.b.c();
                }
            }
        } catch (IOException e) {
            throw new a(e);
        } catch (Throwable th) {
            this.d = null;
            if (this.f) {
                this.f = false;
                if (this.b != null) {
                    this.b.c();
                }
            }
        }
    }
}
