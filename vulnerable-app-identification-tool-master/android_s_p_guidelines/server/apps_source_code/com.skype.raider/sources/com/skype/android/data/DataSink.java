package com.skype.android.data;

public abstract class DataSink {
    private long nativePtr = nativeInit();

    private native long nativeInit();

    private native void nativeUninit(long j);

    public abstract int getDataId();

    public abstract void onDataReady(byte[] bArr, int i, int i2);

    public abstract void onDataSinkEvent(int i, long j);

    public long getNativePtr() {
        return this.nativePtr;
    }

    public void release() {
        nativeUninit(this.nativePtr);
        this.nativePtr = 0;
    }
}
