package com.skype.android.jipc.omx;

public class NodeAllocationException extends RuntimeException {
    private final String a;
    private final int b;

    public String getMessage() {
        return String.format("Can't allocate node: codecName='%s', errorCode=%d 0x%08x", new Object[]{this.a, Integer.valueOf(this.b), Integer.valueOf(this.b)});
    }
}
