package com.microsoft.skypemessagetextinput.b;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.facebook.react.bridge.WritableNativeMap;
import com.microsoft.skypemessagetextinput.d.a;
import java.util.concurrent.ScheduledFuture;

public final class f extends i implements TextWatcher, OnFocusChangeListener {
    private boolean a = false;
    private long b = 0;
    private Integer c;
    private boolean d = false;
    private a e = new a();
    private ScheduledFuture<?> f;

    public f(com.microsoft.skypemessagetextinput.view.a view) {
        super(view);
    }

    public final void a() {
        e();
        this.e.a();
    }

    public final void a(@Nullable Integer interval) {
        this.c = interval;
    }

    public final void b() {
        this.d = true;
    }

    public final void c() {
        this.d = false;
    }

    public final void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            f().a(com.microsoft.skypemessagetextinput.view.a.a.onFocus2, new WritableNativeMap());
            return;
        }
        d();
        f().a(com.microsoft.skypemessagetextinput.view.a.a.onBlur2, new WritableNativeMap());
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        this.a = true;
        if (this.c != null) {
            long now = System.currentTimeMillis();
            long eventDueTime = this.b + ((long) this.c.intValue());
            if (now >= eventDueTime) {
                d();
                return;
            }
            e();
            final f _this = this;
            this.f = this.e.a(new Runnable(this) {
                final /* synthetic */ f b;

                public final void run() {
                    _this.d();
                }
            }, eventDueTime - now);
        }
    }

    private void d() {
        if (!this.d && this.a) {
            e();
            this.a = false;
            this.b = System.currentTimeMillis();
            f().f();
        }
    }

    private void e() {
        if (this.f != null) {
            this.f.cancel(false);
            this.f = null;
        }
    }
}
