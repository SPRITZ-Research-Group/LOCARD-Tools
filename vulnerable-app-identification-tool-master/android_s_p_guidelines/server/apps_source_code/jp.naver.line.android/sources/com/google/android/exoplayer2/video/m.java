package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import defpackage.atg;
import defpackage.bcz;

public final class m {
    private final Handler a;
    private final l b;

    public m(Handler handler, l lVar) {
        this.a = lVar != null ? (Handler) bcz.a((Object) handler) : null;
        this.b = lVar;
    }

    public final void a(atg atg) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$ZkWWX9J2KolGQZg-qOezrCsg_Qs(this, atg));
        }
    }

    private /* synthetic */ void d(atg atg) {
        this.b.a(atg);
    }

    public final void a(String str, long j, long j2) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$VifdeFfmwfu9D6I34Q0OQ0ruxMs(this, str, j, j2));
        }
    }

    private /* synthetic */ void b(String str, long j, long j2) {
        this.b.a(str, j, j2);
    }

    public final void a(Format format) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$-mo8x5R8gtZ3kWChxQOFBHj1aSU(this, format));
        }
    }

    private /* synthetic */ void b(Format format) {
        this.b.a(format);
    }

    public final void a(int i, long j) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$ZZj6Dt39-CyikpLcZ04mHD-cDeg(this, i, j));
        }
    }

    private /* synthetic */ void b(int i, long j) {
        this.b.a(i, j);
    }

    public final void a(int i, int i2, int i3, float f) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$0qAPo7oNQumMzl4ELlGrBS-usb0(this, i, i2, i3, f));
        }
    }

    private /* synthetic */ void b(int i, int i2, int i3, float f) {
        this.b.onVideoSizeChanged(i, i2, i3, f);
    }

    public final void a(Surface surface) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$GXEsn4nTm4s2z4zwVXbgq8uNVpE(this, surface));
        }
    }

    private /* synthetic */ void b(Surface surface) {
        this.b.a(surface);
    }

    public final void b(atg atg) {
        if (this.b != null) {
            this.a.post(new -$$Lambda$m$Oa0cqg0d9Zn43u7VZ3u7P9nI3eQ(this, atg));
        }
    }

    private /* synthetic */ void c(atg atg) {
        this.b.b(atg);
    }
}
