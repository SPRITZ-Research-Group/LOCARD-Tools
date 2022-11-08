package com.skype;

import com.skype.ExampleInMemoryObject.ExampleInMemoryObjectIListener;
import com.skype.ExampleInMemoryObject.GetName_Result;
import com.skype.SkyLib.IN_MEMORY_OBJECTTYPE;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class ExampleInMemoryObjectImpl extends InMemoryObjectImpl implements ExampleInMemoryObject, NativeListenable {
    private final Set<ExampleInMemoryObjectIListener> m_listeners;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyExampleInMemoryObject(this.nativeObject);
        }
    }

    private native void setName(byte[] bArr);

    public native IN_MEMORY_OBJECTTYPE getInMemObjectType();

    public native GetName_Result getName();

    public native void initializeListener();

    public native void setChildren(int[] iArr);

    public ExampleInMemoryObjectImpl() {
        this(SkypeFactory.getInstance());
    }

    public ExampleInMemoryObjectImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createExampleInMemoryObject());
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public ExampleInMemoryObjectImpl(String name) {
        this(SkypeFactory.getInstance(), name);
    }

    public ExampleInMemoryObjectImpl(ObjectInterfaceFactory factory, String name) {
        super(factory, factory.createExampleInMemoryObject(name));
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public ExampleInMemoryObjectImpl(String name, int[] children) {
        this(SkypeFactory.getInstance(), name, children);
    }

    public ExampleInMemoryObjectImpl(ObjectInterfaceFactory factory, String name, int[] children) {
        super(factory, factory.createExampleInMemoryObject(name, children));
        this.m_listeners = new HashSet();
        factory.initializeListener(this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(ExampleInMemoryObjectIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ExampleInMemoryObjectIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onChildrenChanged(int[] newChildren) {
        synchronized (this.m_listeners) {
            for (ExampleInMemoryObjectIListener onChildrenChanged : this.m_listeners) {
                onChildrenChanged.onChildrenChanged(this, newChildren);
            }
        }
    }

    public void onNameChanged(byte[] newName, byte[] oldName) {
        synchronized (this.m_listeners) {
            for (ExampleInMemoryObjectIListener onNameChanged : this.m_listeners) {
                onNameChanged.onNameChanged(this, NativeStringConvert.ConvertFromNativeBytes(newName), NativeStringConvert.ConvertFromNativeBytes(oldName));
            }
        }
    }

    public void setName(String newName) {
        setName(NativeStringConvert.ConvertToNativeBytes(newName));
    }
}
