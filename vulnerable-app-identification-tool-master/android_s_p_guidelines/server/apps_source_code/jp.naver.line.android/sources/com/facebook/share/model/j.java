package com.facebook.share.model;

import android.os.Bundle;

public abstract class j<M extends ShareMedia, B extends j> {
    private Bundle a = new Bundle();

    public B a(M m) {
        if (m == null) {
            return this;
        }
        this.a.putAll(m.a());
        return this;
    }
}
