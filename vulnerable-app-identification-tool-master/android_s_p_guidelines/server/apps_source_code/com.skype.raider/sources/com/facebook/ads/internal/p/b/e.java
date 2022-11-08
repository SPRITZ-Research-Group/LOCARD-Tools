package com.facebook.ads.internal.p.b;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.p.b.a.b;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;

final class e extends k {
    private final h a;
    private final b b;
    private b c;

    public e(h hVar, b bVar) {
        super(hVar, bVar);
        this.b = bVar;
        this.a = hVar;
    }

    private void a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a = a(bArr, j);
            if (a != -1) {
                outputStream.write(bArr, 0, a);
                j += (long) a;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private void b(OutputStream outputStream, long j) {
        try {
            h hVar = new h(this.a);
            hVar.a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int a = hVar.a(bArr);
                if (a == -1) {
                    break;
                }
                outputStream.write(bArr, 0, a);
            }
            outputStream.flush();
        } finally {
            this.a.b();
        }
    }

    protected final void a(int i) {
        if (this.c != null) {
            this.c.a(this.b.a, i);
        }
    }

    public final void a(b bVar) {
        this.c = bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(d dVar, Socket socket) {
        int i = 0;
        OutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        int i2 = !TextUtils.isEmpty(this.a.c()) ? 1 : 0;
        int a = this.b.d() ? this.b.a() : this.a.a();
        int i3 = a >= 0 ? 1 : 0;
        long j = dVar.c ? ((long) a) - dVar.b : (long) a;
        int i4 = (i3 == 0 || !dVar.c) ? 0 : 1;
        bufferedOutputStream.write(((dVar.c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (i3 != 0 ? String.format("Content-Length: %d\n", new Object[]{Long.valueOf(j)}) : "") + (i4 != 0 ? String.format("Content-Range: bytes %d-%d/%d\n", new Object[]{Long.valueOf(dVar.b), Integer.valueOf(a - 1), Integer.valueOf(a)}) : "") + (i2 != 0 ? String.format("Content-Type: %s\n", new Object[]{r10}) : "") + "\n").getBytes(Constants.ENCODING));
        long j2 = dVar.b;
        i3 = this.a.a();
        i2 = i3 > 0 ? 1 : 0;
        int a2 = this.b.a();
        if (i2 != 0 && dVar.c) {
        }
        i = 1;
        if (i != 0) {
            a(bufferedOutputStream, j2);
        } else {
            b(bufferedOutputStream, j2);
        }
    }
}
