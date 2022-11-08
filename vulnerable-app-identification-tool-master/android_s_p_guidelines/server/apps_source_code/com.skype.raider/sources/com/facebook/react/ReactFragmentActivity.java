package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.core.c;

public abstract class ReactFragmentActivity extends FragmentActivity implements b, c {
    private final i a = new i(this);

    protected ReactFragmentActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.a.b();
    }

    protected void onPause() {
        super.onPause();
        this.a.c();
    }

    protected void onResume() {
        super.onResume();
        this.a.d();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.e();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.a.a(requestCode, resultCode, data);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        this.a.f();
        return super.onKeyUp(keyCode, event);
    }

    public void onBackPressed() {
        if (!this.a.g()) {
            super.onBackPressed();
        }
    }

    public void onNewIntent(Intent intent) {
        if (!this.a.a(intent)) {
            super.onNewIntent(intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.a.a(requestCode, permissions, grantResults);
    }
}
