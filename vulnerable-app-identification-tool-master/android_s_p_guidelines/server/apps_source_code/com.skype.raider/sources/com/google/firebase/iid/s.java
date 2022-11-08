package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;

@VisibleForTesting
final class s extends BroadcastReceiver {
    @Nullable
    private r a;

    public s(r rVar) {
        this.a = rVar;
    }

    public final void a() {
        FirebaseInstanceId.zzj();
        this.a.a().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.a != null && this.a.b()) {
            FirebaseInstanceId.zzj();
            FirebaseInstanceId.zza(this.a, 0);
            this.a.a().unregisterReceiver(this);
            this.a = null;
        }
    }
}
