package com.facebook.react.a;

import android.util.SparseArray;
import com.facebook.react.bridge.ap;

public final class c {
    private final SparseArray<a> a = new SparseArray();

    public final void a(a animation) {
        ap.b();
        this.a.put(animation.b(), animation);
    }

    public final a a(int animationID) {
        ap.b();
        return (a) this.a.get(animationID);
    }

    public final a b(int animationID) {
        ap.b();
        a animation = (a) this.a.get(animationID);
        if (animation != null) {
            this.a.delete(animationID);
        }
        return animation;
    }
}
