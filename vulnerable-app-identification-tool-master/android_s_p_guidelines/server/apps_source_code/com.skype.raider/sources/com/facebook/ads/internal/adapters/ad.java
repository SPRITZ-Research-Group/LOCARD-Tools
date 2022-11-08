package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.a;
import com.facebook.ads.internal.view.f.b.z;

public class ad extends BroadcastReceiver {
    private String a;
    private i b;
    private h c;

    public ad(String str, h hVar, i iVar) {
        this.c = hVar;
        this.b = iVar;
        this.a = str;
    }

    public final IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(z.REWARDED_VIDEO_COMPLETE.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_ERROR.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_AD_CLICK.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_IMPRESSION.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_CLOSED.a(this.a));
        intentFilter.addAction(z.REWARD_SERVER_SUCCESS.a(this.a));
        intentFilter.addAction(z.REWARD_SERVER_FAILED.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a));
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (z.REWARDED_VIDEO_COMPLETE.a(this.a).equals(action)) {
            this.b.c();
        } else if (z.REWARDED_VIDEO_ERROR.a(this.a).equals(action)) {
            i iVar = this.b;
            h hVar = this.c;
            a aVar = a.e;
            iVar.b(hVar);
        } else if (z.REWARDED_VIDEO_AD_CLICK.a(this.a).equals(action)) {
            this.b.a();
        } else if (z.REWARDED_VIDEO_IMPRESSION.a(this.a).equals(action)) {
            this.b.b();
        } else if (z.REWARDED_VIDEO_CLOSED.a(this.a).equals(action)) {
            this.b.d();
        } else if (z.REWARD_SERVER_FAILED.a(this.a).equals(action)) {
            this.b.e();
        } else if (z.REWARD_SERVER_SUCCESS.a(this.a).equals(action)) {
            this.b.f();
        } else if (z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a).equals(action)) {
            this.b.g();
        }
    }
}
