package com.skype.reactnativesprites;

public class SameThreadAssert {
    private volatile Long a;

    public final void a() {
        long currentThread = Thread.currentThread().getId();
        if (this.a == null) {
            this.a = Long.valueOf(currentThread);
        } else if (this.a.longValue() != currentThread) {
            throw new IllegalStateException("This method supposed to be called from single thread, but we have at least two: " + this.a + " & " + currentThread);
        }
    }
}
