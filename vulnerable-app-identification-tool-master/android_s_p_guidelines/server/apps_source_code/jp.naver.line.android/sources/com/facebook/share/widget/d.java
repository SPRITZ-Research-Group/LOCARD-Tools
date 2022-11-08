package com.facebook.share.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.av;
import com.facebook.internal.bj;

final class d extends BroadcastReceiver {
    final /* synthetic */ LikeView a;

    private d(LikeView likeView) {
        this.a = likeView;
    }

    /* synthetic */ d(LikeView likeView, byte b) {
        this(likeView);
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        Object obj = 1;
        if (extras != null) {
            Object string = extras.getString("com.facebook.sdk.LikeActionController.OBJECT_ID");
            if (!(bj.a((String) string) || bj.a(this.a.a, string))) {
                obj = null;
            }
        }
        if (obj != null) {
            if ("com.facebook.sdk.LikeActionController.UPDATED".equals(action)) {
                this.a.b();
                return;
            }
            if ("com.facebook.sdk.LikeActionController.DID_ERROR".equals(action)) {
                if (this.a.h != null) {
                    this.a.h;
                    av.a(extras);
                }
            } else if ("com.facebook.sdk.LikeActionController.DID_RESET".equals(action)) {
                this.a.a(this.a.a, this.a.b);
                this.a.b();
            }
        }
    }
}
