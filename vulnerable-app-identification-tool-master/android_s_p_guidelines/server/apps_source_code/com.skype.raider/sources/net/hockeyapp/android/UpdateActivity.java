package net.hockeyapp.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import net.hockeyapp.android.h.d;

public class UpdateActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            } else {
                getFragmentManager().beginTransaction().add(16908290, Fragment.instantiate(this, extras.getString("fragmentClass", j.class.getName()), extras), j.FRAGMENT_TAG).commit();
            }
        }
        setTitle(d.hockeyapp_update_title);
    }
}
