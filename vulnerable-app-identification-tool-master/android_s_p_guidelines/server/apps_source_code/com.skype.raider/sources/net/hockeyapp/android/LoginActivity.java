package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.adjust.sdk.Constants;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.e.g;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.h.b;
import net.hockeyapp.android.h.c;
import net.hockeyapp.android.h.d;

public class LoginActivity extends Activity {
    private String a;
    private String b;
    private int c;
    private g d;
    private Handler e;

    private static class a extends Handler {
        private final WeakReference<Activity> a;

        a(Activity activity) {
            this.a = new WeakReference(activity);
        }

        public final void handleMessage(android.os.Message r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
            /*
            r3 = this;
            r1 = r3.a;
            r0 = r1.get();
            r0 = (android.app.Activity) r0;
            if (r0 != 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            r1 = r4.getData();
            r2 = "success";
            r1 = r1.getBoolean(r2);
            if (r1 == 0) goto L_0x001f;
        L_0x0017:
            r0.finish();
            r1 = net.hockeyapp.android.e.b;
            if (r1 == 0) goto L_0x000a;
        L_0x001e:
            goto L_0x000a;
        L_0x001f:
            r1 = "Login failed. Check your credentials.";
            r2 = 1;
            r1 = android.widget.Toast.makeText(r0, r1, r2);
            r1.show();
            goto L_0x000a;
            */
            throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.LoginActivity.a.handleMessage(android.os.Message):void");
        }
    }

    static /* synthetic */ void a(LoginActivity x0) {
        int i = 0;
        if (j.c((Context) x0)) {
            CharSequence obj = ((EditText) x0.findViewById(b.input_email)).getText().toString();
            CharSequence obj2 = ((EditText) x0.findViewById(b.input_password)).getText().toString();
            Map hashMap = new HashMap();
            if (x0.c == 1) {
                int i2 = !TextUtils.isEmpty(obj) ? 1 : 0;
                hashMap.put("email", obj);
                hashMap.put("authcode", a(x0.b + obj));
                i = i2;
            } else if (x0.c == 2) {
                if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2))) {
                    i = 1;
                }
                hashMap.put("email", obj);
                hashMap.put("password", obj2);
            }
            if (i != 0) {
                x0.d = new g(x0, x0.e, x0.a, x0.c, hashMap);
                net.hockeyapp.android.f.a.a(x0.d);
                return;
            }
            Toast.makeText(x0, x0.getString(d.hockeyapp_login_missing_credentials_toast), 1).show();
            return;
        }
        Toast.makeText(x0, d.hockeyapp_error_no_network_message, 1).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(c.hockeyapp_activity_login);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.a = extras.getString(j.FRAGMENT_URL);
            this.b = extras.getString("secret");
            this.c = extras.getInt("mode");
        }
        if (this.c == 1) {
            ((EditText) findViewById(b.input_password)).setVisibility(4);
        }
        ((TextView) findViewById(b.text_headline)).setText(this.c == 1 ? d.hockeyapp_login_headline_text_email_only : d.hockeyapp_login_headline_text);
        ((Button) findViewById(b.button_login)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = this$0;
            }

            public final void onClick(View v) {
                LoginActivity.a(this.a);
            }
        });
        this.e = new a(this);
        Object object = getLastNonConfigurationInstance();
        if (object != null) {
            this.d = (g) object;
            this.d.a(this, this.e);
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.d != null) {
            this.d.b();
        }
        return this.d;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private static String a(String s) {
        try {
            return j.a(j.a(s.getBytes(), Constants.MD5));
        } catch (NoSuchAlgorithmException e) {
            e.f();
            return "";
        }
    }
}
