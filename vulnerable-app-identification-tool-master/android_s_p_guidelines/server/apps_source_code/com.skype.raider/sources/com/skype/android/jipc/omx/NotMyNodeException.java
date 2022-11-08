package com.skype.android.jipc.omx;

public class NotMyNodeException extends IllegalStateException {
    private final int a;

    public NotMyNodeException(int nodeId) {
        this.a = nodeId;
    }

    public String getMessage() {
        return String.format("Not my node: %d 0x%08x", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.a)});
    }
}
