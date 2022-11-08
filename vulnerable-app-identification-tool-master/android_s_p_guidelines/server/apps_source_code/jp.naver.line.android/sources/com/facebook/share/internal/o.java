package com.facebook.share.internal;

import java.util.ArrayList;

final class o implements Runnable {
    private static ArrayList<String> a = new ArrayList();
    private String b;
    private boolean c;

    o(String str, boolean z) {
        this.b = str;
        this.c = z;
    }

    public final void run() {
        if (this.b != null) {
            a.remove(this.b);
            a.add(0, this.b);
        }
        if (this.c && a.size() >= 128) {
            while (64 < a.size()) {
                ArrayList arrayList = a;
                e.c.remove((String) arrayList.remove(arrayList.size() - 1));
            }
        }
    }
}
