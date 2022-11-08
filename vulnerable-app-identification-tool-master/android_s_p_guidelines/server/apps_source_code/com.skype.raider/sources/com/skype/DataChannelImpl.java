package com.skype;

import com.skype.DataChannel.DataChannelIListener;
import com.skype.android.data.DataSink;
import com.skype.android.data.DataSource;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class DataChannelImpl extends ObjectInterfaceImpl implements DataChannel, NativeListenable, ObjectInterface {
    private final Set<DataChannelIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyDataChannel(this.nativeObject);
        }
    }

    private native int createSinkDevice(long j);

    private native int createSourceDevice(long j);

    private native boolean registerDataSink(int i, long j);

    private native boolean registerDataSource(int i, long j);

    private native void start(byte[] bArr);

    private native void stop(byte[] bArr);

    private native boolean unregisterDataSink(int i, long j);

    private native boolean unregisterDataSource(int i, long j);

    public native void deleteDevice(int i);

    public native void initializeListener();

    public native void resetDataDevice();

    public native boolean setDataDevice(int i, int i2);

    public DataChannelImpl() {
        this(SkypeFactory.getInstance());
    }

    public DataChannelImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createDataChannel());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public int getStatusProp() {
        return getIntProperty(PROPKEY.DATACHANNEL_STATUS);
    }

    public void addListener(DataChannelIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(DataChannelIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void start(String negotiationTag) {
        start(NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public void start() {
        start("");
    }

    public void stop(String negotiationTag) {
        stop(NativeStringConvert.ConvertToNativeBytes(negotiationTag));
    }

    public void stop() {
        stop("");
    }

    public int createSinkDevice(DataSink dataSink) {
        return createSinkDevice(dataSink != null ? dataSink.getNativePtr() : 0);
    }

    public int createSourceDevice(DataSource dataSource) {
        return createSourceDevice(dataSource != null ? dataSource.getNativePtr() : 0);
    }

    public boolean registerDataSink(int deviceId, DataSink dataSink) {
        return registerDataSink(deviceId, dataSink != null ? dataSink.getNativePtr() : 0);
    }

    public boolean registerDataSource(int deviceId, DataSource dataSource) {
        return registerDataSource(deviceId, dataSource != null ? dataSource.getNativePtr() : 0);
    }

    public boolean unregisterDataSink(int deviceId, DataSink dataSink) {
        return unregisterDataSink(deviceId, dataSink != null ? dataSink.getNativePtr() : 0);
    }

    public boolean unregisterDataSource(int deviceId, DataSource dataSource) {
        return unregisterDataSource(deviceId, dataSource != null ? dataSource.getNativePtr() : 0);
    }
}
