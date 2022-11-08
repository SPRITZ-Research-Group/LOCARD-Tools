package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import defpackage.gm;
import java.util.HashMap;
import java.util.List;

final class f implements DeathRecipient {
    public final String a;
    public final int b;
    public final int c;
    public final m d;
    public final Bundle e;
    public final i f;
    public final HashMap<String, List<gm<IBinder, Bundle>>> g = new HashMap();
    public e h;
    final /* synthetic */ MediaBrowserServiceCompat i;

    f(MediaBrowserServiceCompat mediaBrowserServiceCompat, String str, int i, int i2, Bundle bundle, i iVar) {
        this.i = mediaBrowserServiceCompat;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = new m(str, i, i2);
        this.e = bundle;
        this.f = iVar;
    }

    public final void binderDied() {
        this.i.d.post(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.i.b.remove(this.a.f.a());
            }
        });
    }
}
