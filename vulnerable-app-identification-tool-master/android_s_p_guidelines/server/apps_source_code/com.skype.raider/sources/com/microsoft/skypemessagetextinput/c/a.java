package com.microsoft.skypemessagetextinput.c;

import com.facebook.react.bridge.al;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private List<String> a = new ArrayList();
    private List<String> b = new ArrayList();

    public final void a(al triggers) {
        this.a.clear();
        for (int index = 0; index < triggers.size(); index++) {
            this.a.add(triggers.getString(index));
        }
    }

    public final void b(al triggers) {
        this.b.clear();
        for (int index = 0; index < triggers.size(); index++) {
            this.b.add(triggers.getString(index));
        }
    }

    public final List<String> a() {
        return this.a;
    }

    public final List<String> b() {
        return this.b;
    }
}
