package kotlin.reflect.jvm.internal.impl.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class NoLock implements Lock {
    public static final Lock INSTANCE = new NoLock();

    public void lock() {
    }

    public void unlock() {
    }

    private NoLock() {
    }

    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Should not be called");
    }

    public boolean tryLock() {
        throw new UnsupportedOperationException("Should not be called");
    }

    public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException("Should not be called");
    }

    public Condition newCondition() {
        throw new UnsupportedOperationException("Should not be called");
    }
}
