package com.microsoft.skypemessagetextinput.b;

import com.microsoft.skypemessagetextinput.view.a;

public class g extends i {
    private boolean a = false;

    public g(a view) {
        super(view);
    }

    public final void c() {
        this.a = true;
    }

    public final void d() {
        this.a = false;
    }

    protected final boolean e() {
        return this.a;
    }
}
