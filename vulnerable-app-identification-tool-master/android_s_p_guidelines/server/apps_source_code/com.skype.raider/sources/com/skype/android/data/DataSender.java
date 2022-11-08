package com.skype.android.data;

public class DataSender {
    private long nativePtr;

    private native int nativeSendData(long j, byte[] bArr, int i, int[] iArr, int i2);

    public synchronized int sendData(byte[] buffer, int size, int[] recipients, int numRecipients) {
        return nativeSendData(this.nativePtr, buffer, size, recipients, numRecipients);
    }

    private DataSender() {
    }

    private synchronized void setNativePtr(long nativePtr) {
        this.nativePtr = nativePtr;
    }
}
