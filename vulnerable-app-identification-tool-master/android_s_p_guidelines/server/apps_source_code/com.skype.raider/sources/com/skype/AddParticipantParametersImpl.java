package com.skype;

import com.skype.AddParticipantParameters.AddParticipantParametersIListener;
import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class AddParticipantParametersImpl extends InMemoryObjectImpl implements AddParticipantParameters, NativeListenable {
    private final Set<AddParticipantParametersIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyAddParticipantParameters(this.nativeObject);
        }
    }

    private native void setAdditionalData(byte[] bArr);

    private native void setMessageId(byte[] bArr);

    private native void setThreadId(byte[] bArr);

    public native IN_MEMORY_OBJECTTYPE getInMemObjectType();

    public native int getObjectID();

    public native void initializeListener();

    public AddParticipantParametersImpl() {
        this(SkypeFactory.getInstance());
    }

    public AddParticipantParametersImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createAddParticipantParameters());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(AddParticipantParametersIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(AddParticipantParametersIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void setAdditionalData(String additionalData) {
        setAdditionalData(NativeStringConvert.ConvertToNativeBytes(additionalData));
    }

    public void setMessageId(String messageId) {
        setMessageId(NativeStringConvert.ConvertToNativeBytes(messageId));
    }

    public void setThreadId(String threadId) {
        setThreadId(NativeStringConvert.ConvertToNativeBytes(threadId));
    }
}
