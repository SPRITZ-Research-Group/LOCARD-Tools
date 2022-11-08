package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public final class ad {
    private Fragment a;
    private android.app.Fragment b;

    public ad(Fragment fragment) {
        bn.a((Object) fragment, "fragment");
        this.a = fragment;
    }

    public ad(android.app.Fragment fragment) {
        bn.a((Object) fragment, "fragment");
        this.b = fragment;
    }

    public final android.app.Fragment a() {
        return this.b;
    }

    public final Fragment b() {
        return this.a;
    }

    public final void a(Intent intent, int i) {
        if (this.a != null) {
            this.a.startActivityForResult(intent, i);
        } else {
            this.b.startActivityForResult(intent, i);
        }
    }

    public final Activity c() {
        if (this.a != null) {
            return this.a.getActivity();
        }
        return this.b.getActivity();
    }
}
