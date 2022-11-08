package com.skype.android.data;

public abstract class DataSource {
    private long nativePtr = nativeInit();

    private native long nativeInit();

    private native void nativeUninit(long j);

    public abstract int getDataId();

    public abstract void onDataSourceEvent(int i, long j);

    public abstract int onInitialized(DataSender dataSender);

    public long getNativePtr() {
        return this.nativePtr;
    }

    public void release() {
        nativeUninit(this.nativePtr);
        this.nativePtr = 0;
    }
}
