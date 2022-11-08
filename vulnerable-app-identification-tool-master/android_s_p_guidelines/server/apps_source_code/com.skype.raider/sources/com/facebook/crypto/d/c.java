package com.facebook.crypto.d;

import com.facebook.crypto.a.a;
import java.util.ArrayList;
import java.util.Iterator;

public final class c implements b {
    private static final ArrayList<String> a = new ArrayList<String>() {
        {
            add("conceal");
        }
    };
    private boolean b = true;
    private boolean c = false;
    private volatile UnsatisfiedLinkError d = null;

    public final synchronized void a() throws a {
        if (!b()) {
            throw new a(this.d);
        }
    }

    private synchronized boolean b() {
        boolean z;
        if (this.b) {
            try {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    System.loadLibrary((String) it.next());
                }
                this.c = true;
            } catch (UnsatisfiedLinkError error) {
                this.d = error;
                this.c = false;
            }
            this.b = false;
            z = this.c;
        } else {
            z = this.c;
        }
        return z;
    }
}
