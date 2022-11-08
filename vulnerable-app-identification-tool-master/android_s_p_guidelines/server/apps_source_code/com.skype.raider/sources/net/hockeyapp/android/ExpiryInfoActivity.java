package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import net.hockeyapp.android.f.j;
import net.hockeyapp.android.h.b;
import net.hockeyapp.android.h.c;
import net.hockeyapp.android.h.d;

public class ExpiryInfoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(d.hockeyapp_expiry_info_title);
        setContentView(c.hockeyapp_activity_expiry_info);
        String appName = j.d((Context) this);
        ((TextView) findViewById(b.label_message)).setText(getString(d.hockeyapp_expiry_info_text, new Object[]{appName}));
    }
}
