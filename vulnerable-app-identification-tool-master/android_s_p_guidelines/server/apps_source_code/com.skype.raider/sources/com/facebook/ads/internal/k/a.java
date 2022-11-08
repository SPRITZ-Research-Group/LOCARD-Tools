package com.facebook.ads.internal.k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.facebook.ads.internal.q.d.b;

public final class a {
    private final Context a;
    private final String b;
    private final String c;
    private boolean d = false;
    private Messenger e;
    private final ServiceConnection f = new ServiceConnection(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.a.d = true;
            this.a.e = new Messenger(iBinder);
            Message obtain = Message.obtain(null, 1);
            obtain.setData(a.a(this.a));
            try {
                this.a.e.send(obtain);
            } catch (Exception e) {
                com.facebook.ads.internal.q.d.a.a(this.a.a, "generic", b.m, e);
            }
            this.a.a.unbindService(this);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            try {
                this.a.a.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
            this.a.e = null;
            this.a.d = false;
        }
    };

    public a(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public final void a() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.a.bindService(intent, this.f, 1)) {
                this.a.unbindService(this.f);
            }
        } catch (Exception e) {
            com.facebook.ads.internal.q.d.a.a(this.a, "generic", b.n, e);
        }
    }

    static /* synthetic */ Bundle a(a aVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", aVar.c);
        bundle.putString("PARAM_REQUEST_ID", aVar.b);
        return bundle;
    }
}
