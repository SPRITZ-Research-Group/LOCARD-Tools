package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.facebook.react.h.c;
import com.facebook.react.h.e;

public class DevSettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(c.catalyst_settings_title);
        addPreferencesFromResource(e.preferences);
    }
}
