package androidx.appcompat.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

final class o {
    final /* synthetic */ AppCompatDelegateImpl a;
    private z b;
    private boolean c;
    private BroadcastReceiver d;
    private IntentFilter e;

    o(AppCompatDelegateImpl appCompatDelegateImpl, z zVar) {
        this.a = appCompatDelegateImpl;
        this.b = zVar;
        this.c = zVar.a();
    }

    final int a() {
        this.c = this.b.a();
        return this.c ? 2 : 1;
    }

    final void b() {
        boolean a = this.b.a();
        if (a != this.c) {
            this.c = a;
            this.a.k();
        }
    }

    final void c() {
        d();
        if (this.d == null) {
            this.d = new BroadcastReceiver(this) {
                final /* synthetic */ o a;

                {
                    this.a = r1;
                }

                public final void onReceive(Context context, Intent intent) {
                    this.a.b();
                }
            };
        }
        if (this.e == null) {
            this.e = new IntentFilter();
            this.e.addAction("android.intent.action.TIME_SET");
            this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
            this.e.addAction("android.intent.action.TIME_TICK");
        }
        this.a.a.registerReceiver(this.d, this.e);
    }

    final void d() {
        if (this.d != null) {
            this.a.a.unregisterReceiver(this.d);
            this.d = null;
        }
    }
}
