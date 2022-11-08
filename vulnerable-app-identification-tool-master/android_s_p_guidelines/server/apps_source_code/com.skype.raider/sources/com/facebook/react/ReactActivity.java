package com.facebook.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.core.c;
import com.facebook.react.modules.core.d;
import javax.annotation.Nullable;

public abstract class ReactActivity extends Activity implements b, c {
    private final i a = b();
    private final o b = new o();

    protected ReactActivity() {
    }

    @Nullable
    protected String a() {
        return null;
    }

    protected i b() {
        return new i(this, a());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.a.b();
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean didFocus = this.b.a(this, event);
        if (didFocus) {
            return didFocus;
        }
        return super.dispatchKeyEvent(event);
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

    public final void c() {
        super.onBackPressed();
    }

    public void onNewIntent(Intent intent) {
        if (!this.a.a(intent)) {
            super.onNewIntent(intent);
        }
    }

    public final void a(String[] permissions, int requestCode, d listener) {
        this.a.a(permissions, requestCode, listener);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.a.a(requestCode, permissions, grantResults);
    }
}
