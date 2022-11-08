package com.facebook.share.model;

import android.os.Bundle;

public abstract class p<P extends ShareOpenGraphValueContainer, E extends p> {
    private Bundle a = new Bundle();

    public final E a(String str, String str2) {
        this.a.putString(str, str2);
        return this;
    }

    public E a(P p) {
        if (p != null) {
            this.a.putAll(p.a());
        }
        return this;
    }
}
