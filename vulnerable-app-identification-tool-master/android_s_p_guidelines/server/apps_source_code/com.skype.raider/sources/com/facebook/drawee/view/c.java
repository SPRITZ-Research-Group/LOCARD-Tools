package com.facebook.drawee.view;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.drawee.interfaces.a;
import java.util.ArrayList;

public final class c<DH extends a> {
    @VisibleForTesting
    boolean a = false;
    @VisibleForTesting
    ArrayList<b<DH>> b = new ArrayList();

    public final void a() {
        if (!this.a) {
            this.a = true;
            for (int i = 0; i < this.b.size(); i++) {
                ((b) this.b.get(i)).b();
            }
        }
    }

    public final void b() {
        if (this.a) {
            this.a = false;
            for (int i = 0; i < this.b.size(); i++) {
                ((b) this.b.get(i)).c();
            }
        }
    }

    public final void c() {
        if (this.a) {
            for (int i = 0; i < this.b.size(); i++) {
                ((b) this.b.get(i)).c();
            }
        }
        this.b.clear();
    }

    public final void a(b<DH> holder) {
        int size = this.b.size();
        h.a((Object) holder);
        h.a(size, this.b.size() + 1);
        this.b.add(size, holder);
        if (this.a) {
            holder.b();
        }
    }
}
