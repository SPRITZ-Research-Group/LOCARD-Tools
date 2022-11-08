package com.microsoft.skypemessagetextinput.view;

import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import com.facebook.common.logging.FLog;
import com.microsoft.skypemessagetextinput.b.c;
import com.microsoft.skypemessagetextinput.b.c.a;
import com.microsoft.skypemessagetextinput.d.b;

public final class d implements TextWatcher, a {
    private RNView a;
    private b b;
    private c c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;

    public d(RNView view, b deferredExecutor, c contentSizeEventSender) {
        this.a = view;
        this.b = deferredExecutor;
        this.c = contentSizeEventSender;
        this.c.a((a) this);
    }

    public final void b() {
        this.c.b(this);
    }

    public final void a(int maxHeight) {
        this.d = maxHeight;
        e();
    }

    public final void c() {
        f();
    }

    public final void d() {
        e();
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public final void afterTextChanged(Editable editable) {
        e();
    }

    public final void a() {
        f();
    }

    private void e() {
        if (!this.f) {
            final d _this = this;
            this.f = true;
            this.b.a(new Runnable(this) {
                final /* synthetic */ d b;

                public final void run() {
                    _this.f();
                }
            });
        }
    }

    private void f() {
        boolean scrollingIsEnabled = this.c.a();
        Layout layout = this.a.getLayout();
        if (layout == null) {
            FLog.w("SkypeMsgTextInput/ScrollMgr", "No layout on view!");
            return;
        }
        if (scrollingIsEnabled) {
            boolean z;
            Integer newY = null;
            int scrollY = this.a.getScrollY();
            int selectionLine = layout.getLineForOffset(this.a.getSelectionEnd());
            int selectionLineTop = layout.getLineTop(selectionLine);
            int selectionLineBottom = layout.getLineBottom(selectionLine);
            RNView rNView = this.a;
            if (rNView.getSelectionStart() == rNView.getSelectionEnd() && rNView.getSelectionEnd() == rNView.getText().length()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                newY = Integer.valueOf(Math.max(0, selectionLineBottom - this.d));
            } else if (selectionLineTop < scrollY) {
                newY = Integer.valueOf(Math.max(0, selectionLineTop - (this.d / 4)));
            } else if (selectionLineBottom > this.d + scrollY) {
                newY = Integer.valueOf(Math.max(0, selectionLineBottom - this.d));
            }
            if (!(newY == null || newY.intValue() == scrollY)) {
                this.a.scrollTo(0, newY.intValue());
            }
        } else if (this.e) {
            this.a.scrollTo(0, 0);
        }
        this.e = scrollingIsEnabled;
        this.f = false;
    }
}
