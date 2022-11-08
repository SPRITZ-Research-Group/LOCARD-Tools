package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import defpackage.lj;

public class CustomTabActivity extends Activity {
    public static final String a;
    public static final String b;
    private BroadcastReceiver c;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabActivity.class.getSimpleName());
        stringBuilder.append(".action_customTabRedirect");
        a = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabActivity.class.getSimpleName());
        stringBuilder.append(".action_destroy");
        b = stringBuilder.toString();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, CustomTabMainActivity.class);
        intent.setAction(a);
        intent.putExtra(CustomTabMainActivity.c, getIntent().getDataString());
        intent.addFlags(603979776);
        startActivityForResult(intent, 2);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            Intent intent2 = new Intent(a);
            intent2.putExtra(CustomTabMainActivity.c, getIntent().getDataString());
            lj.a((Context) this).a(intent2);
            this.c = new BroadcastReceiver(this) {
                final /* synthetic */ CustomTabActivity a;

                {
                    this.a = r1;
                }

                public final void onReceive(Context context, Intent intent) {
                    this.a.finish();
                }
            };
            lj.a((Context) this).a(this.c, new IntentFilter(b));
        }
    }

    protected void onDestroy() {
        lj.a((Context) this).a(this.c);
        super.onDestroy();
    }
}
