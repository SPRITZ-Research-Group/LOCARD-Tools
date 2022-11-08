package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class n implements f {
    private final p<? super n> a;
    private RandomAccessFile b;
    private Uri c;
    private long d;
    private boolean e;

    public static class a extends IOException {
        public a(IOException cause) {
            super(cause);
        }
    }

    public n() {
        this(null);
    }

    public n(p<? super n> listener) {
        this.a = listener;
    }

    public final long a(DataSpec dataSpec) throws a {
        try {
            this.c = dataSpec.a;
            this.b = new RandomAccessFile(dataSpec.a.getPath(), "r");
            this.b.seek(dataSpec.d);
            this.d = dataSpec.e == -1 ? this.b.length() - dataSpec.d : dataSpec.e;
            if (this.d < 0) {
                throw new EOFException();
            }
            this.e = true;
            if (this.a != null) {
                this.a.b();
            }
            return this.d;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public final int a(byte[] buffer, int offset, int readLength) throws a {
        if (readLength == 0) {
            return 0;
        }
        if (this.d == 0) {
            return -1;
        }
        try {
            int bytesRead = this.b.read(buffer, offset, (int) Math.min(this.d, (long) readLength));
            if (bytesRead <= 0) {
                return bytesRead;
            }
            this.d -= (long) bytesRead;
            if (this.a == null) {
                return bytesRead;
            }
            this.a.a(bytesRead);
            return bytesRead;
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
            if (this.b != null) {
                this.b.close();
            }
            this.b = null;
            if (this.e) {
                this.e = false;
                if (this.a != null) {
                    this.a.c();
                }
            }
        } catch (IOException e) {
            throw new a(e);
        } catch (Throwable th) {
            this.b = null;
            if (this.e) {
                this.e = false;
                if (this.a != null) {
                    this.a.c();
                }
            }
        }
    }
}
