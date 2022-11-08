package com.facebook.share.model;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

public abstract class h<P extends ShareContent, E extends h> {
    private Uri a;
    private List<String> b;
    private String c;
    private String d;
    private String e;
    private ShareHashtag f;

    public E a(P p) {
        if (p == null) {
            return this;
        }
        this.a = p.h();
        List i = p.i();
        this.b = i == null ? null : Collections.unmodifiableList(i);
        this.c = p.j();
        this.d = p.k();
        this.e = p.l();
        return this;
    }
}
