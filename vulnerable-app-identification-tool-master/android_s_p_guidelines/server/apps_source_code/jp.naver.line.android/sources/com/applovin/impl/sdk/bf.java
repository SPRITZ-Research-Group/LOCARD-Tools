package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

class bf implements Runnable {
    final /* synthetic */ be a;

    bf(be beVar) {
        this.a = beVar;
    }

    public void run() {
        String str = (String) this.a.a.get(ea.Z);
        String b = this.a.b();
        String str2 = (String) this.a.a.get(ea.ae);
        if (ab.a(AppLovinConfirmationActivity.class, this.a.c)) {
            try {
                Intent intent = new Intent(this.a.c, AppLovinConfirmationActivity.class);
                intent.putExtra("dialog_title", str);
                intent.putExtra("dialog_body", b);
                intent.putExtra("dialog_button_text", str2);
                this.a.c.startActivity(intent);
                return;
            } catch (Throwable th) {
                this.a.a(b, th);
                return;
            }
        }
        this.a.a(b, null);
    }
}
