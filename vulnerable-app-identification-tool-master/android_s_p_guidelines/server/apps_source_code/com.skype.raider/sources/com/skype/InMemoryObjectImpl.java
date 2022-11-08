package com.skype;

import com.skype.InMemoryObject.InMemoryObjectIListener;
import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class InMemoryObjectImpl implements InMemoryObject, a {
    private final Set<InMemoryObjectIListener> m_listeners;
    protected int m_nativeObject;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyInMemoryObject(this.nativeObject);
        }
    }

    public native int getInMemObjectID();

    public native IN_MEMORY_OBJECTTYPE getInMemObjectType();

    public InMemoryObjectImpl() {
        this(SkypeFactory.getInstance());
    }

    public InMemoryObjectImpl(ObjectInterfaceFactory factory) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = factory.createInMemoryObject();
        b.getInstance().addDestructibleObject(factory, this);
    }

    protected InMemoryObjectImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected InMemoryObjectImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = nativeObject;
        b.getInstance().addDestructibleObject(factory, this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(InMemoryObjectIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(InMemoryObjectIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onDummy() {
        synchronized (this.m_listeners) {
            for (InMemoryObjectIListener onDummy : this.m_listeners) {
                onDummy.onDummy(this);
            }
        }
    }
}
