package com.skypecam.obscura.b;

import java.util.concurrent.CountDownLatch;

public abstract class p extends CountDownLatch implements q {
    protected abstract boolean a_(Object obj);

    public p() {
        super(1);
    }

    public final boolean a(Object feedback) {
        boolean triggers = a_(feedback);
        if (triggers) {
            countDown();
        }
        return triggers;
    }

    public final boolean b(Object feedback) {
        return false;
    }
}
