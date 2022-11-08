package com.microsoft.skypemessagetextinput.b;

import android.text.Editable;
import android.text.TextWatcher;
import com.facebook.react.bridge.WritableNativeMap;
import com.microsoft.skypemessagetextinput.d.a;
import java.util.concurrent.ScheduledFuture;

public final class b extends g implements TextWatcher {
    private long a = 0;
    private boolean b = false;
    private a c = new a();
    private ScheduledFuture<?> d;
    private boolean e = false;

    public b(com.microsoft.skypemessagetextinput.view.a view) {
        super(view);
    }

    public final void a() {
        g();
        this.e = true;
        h();
        this.c.a();
    }

    public final void b() {
        g();
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        if (!e()) {
            long now = System.currentTimeMillis();
            if (!this.b || now > this.a + 10000) {
                this.b = true;
                this.a = now;
                f().a(com.microsoft.skypemessagetextinput.view.a.a.onComposingActive, new WritableNativeMap());
            }
            h();
            final b _this = this;
            this.d = this.c.a(new Runnable(this) {
                final /* synthetic */ b b;

                public final void run() {
                    _this.g();
                }
            }, 5000);
        }
    }

    private void g() {
        if (!this.e && this.b) {
            this.b = false;
            this.a = 0;
            f().a(com.microsoft.skypemessagetextinput.view.a.a.onComposingInactive, new WritableNativeMap());
        }
    }

    private void h() {
        if (this.d != null) {
            this.d.cancel(false);
            this.d = null;
        }
    }
}
