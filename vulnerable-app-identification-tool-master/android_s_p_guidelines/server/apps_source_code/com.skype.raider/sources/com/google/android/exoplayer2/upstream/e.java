package com.google.android.exoplayer2.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class e implements f {
    private final ContentResolver a;
    private final p<? super e> b;
    private Uri c;
    private AssetFileDescriptor d;
    private InputStream e;
    private long f;
    private boolean g;

    public static class a extends IOException {
        public a(IOException cause) {
            super(cause);
        }
    }

    public e(Context context, p<? super e> listener) {
        this.a = context.getContentResolver();
        this.b = listener;
    }

    public final long a(DataSpec dataSpec) throws a {
        try {
            this.c = dataSpec.a;
            this.d = this.a.openAssetFileDescriptor(this.c, "r");
            this.e = new FileInputStream(this.d.getFileDescriptor());
            if (this.e.skip(dataSpec.d) < dataSpec.d) {
                throw new EOFException();
            }
            if (dataSpec.e != -1) {
                this.f = dataSpec.e;
            } else {
                this.f = (long) this.e.available();
                if (this.f == 0) {
                    this.f = -1;
                }
            }
            this.g = true;
            if (this.b != null) {
                this.b.b();
            }
            return this.f;
        } catch (IOException e) {
            throw new a(e);
        }
    }

    public final int a(byte[] buffer, int offset, int readLength) throws a {
        if (readLength == 0) {
            return 0;
        }
        if (this.f == 0) {
            return -1;
        }
        try {
            int bytesToRead;
            if (this.f == -1) {
                bytesToRead = readLength;
            } else {
                bytesToRead = (int) Math.min(this.f, (long) readLength);
            }
            int bytesRead = this.e.read(buffer, offset, bytesToRead);
            if (bytesRead != -1) {
                if (this.f != -1) {
                    this.f -= (long) bytesRead;
                }
                if (this.b == null) {
                    return bytesRead;
                }
                this.b.a(bytesRead);
                return bytesRead;
            } else if (this.f == -1) {
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
            if (this.e != null) {
                this.e.close();
            }
            this.e = null;
            try {
                if (this.d != null) {
                    this.d.close();
                }
                this.d = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.c();
                    }
                }
            } catch (IOException e) {
                throw new a(e);
            } catch (Throwable th) {
                this.d = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.c();
                    }
                }
            }
        } catch (IOException e2) {
            throw new a(e2);
        } catch (Throwable th2) {
            this.e = null;
            try {
                if (this.d != null) {
                    this.d.close();
                }
                this.d = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.c();
                    }
                }
            } catch (IOException e22) {
                throw new a(e22);
            } catch (Throwable th3) {
                this.d = null;
                if (this.g) {
                    this.g = false;
                    if (this.b != null) {
                        this.b.c();
                    }
                }
            }
        }
    }
}
