package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.facebook.internal.j;
import defpackage.lj;

public class CustomTabMainActivity extends Activity {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    private boolean e = true;
    private BroadcastReceiver f;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabMainActivity.class.getSimpleName());
        stringBuilder.append(".extra_params");
        a = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabMainActivity.class.getSimpleName());
        stringBuilder.append(".extra_chromePackage");
        b = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabMainActivity.class.getSimpleName());
        stringBuilder.append(".extra_url");
        c = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(CustomTabMainActivity.class.getSimpleName());
        stringBuilder.append(".action_refresh");
        d = stringBuilder.toString();
    }

    public static final String a() {
        StringBuilder stringBuilder = new StringBuilder("fb");
        stringBuilder.append(s.j());
        stringBuilder.append("://authorize");
        return stringBuilder.toString();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CustomTabActivity.a.equals(getIntent().getAction())) {
            setResult(0);
            finish();
            return;
        }
        if (bundle == null) {
            bundle = getIntent().getBundleExtra(a);
            new j("oauth", bundle).a(this, getIntent().getStringExtra(b));
            this.e = false;
            this.f = new BroadcastReceiver(this) {
                final /* synthetic */ CustomTabMainActivity a;

                {
                    this.a = r1;
                }

                public final void onReceive(Context context, Intent intent) {
                    Intent intent2 = new Intent(this.a, CustomTabMainActivity.class);
                    intent2.setAction(CustomTabMainActivity.d);
                    intent2.putExtra(CustomTabMainActivity.c, intent.getStringExtra(CustomTabMainActivity.c));
                    intent2.addFlags(603979776);
                    this.a.startActivity(intent2);
                }
            };
            lj.a((Context) this).a(this.f, new IntentFilter(CustomTabActivity.a));
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (d.equals(intent.getAction())) {
            lj.a((Context) this).a(new Intent(CustomTabActivity.b));
            a(-1, intent);
            return;
        }
        if (CustomTabActivity.a.equals(intent.getAction())) {
            a(-1, intent);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.e) {
            a(0, null);
        }
        this.e = true;
    }

    private void a(int i, Intent intent) {
        lj.a((Context) this).a(this.f);
        if (intent != null) {
            setResult(i, intent);
        } else {
            setResult(i);
        }
        finish();
    }
}
