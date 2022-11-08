package com.skype;

import com.skype.ObjectInterface.GetFilenameProperty_Result;
import com.skype.ObjectInterface.ObjectInterfaceIListener;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

public class ObjectInterfaceImpl implements ObjectInterface, a {
    private final Set<ObjectInterfaceIListener> m_listeners;
    protected int m_nativeObject;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyObjectInterface(this.nativeObject);
        }
    }

    private native byte[] getStrPropertyNativeString(PROPKEY propkey);

    private native byte[] getStrPropertyNativeString(PROPKEY propkey, byte[] bArr);

    private native byte[] getStrPropertyWithXmlStrippedNativeString(PROPKEY propkey, byte[] bArr);

    private native boolean setExtendedProperty(PROPKEY propkey, byte[] bArr);

    public native byte[] getBinProperty(PROPKEY propkey);

    public native GetFilenameProperty_Result getFilenameProperty(PROPKEY propkey);

    public native long getInt64Property(PROPKEY propkey);

    public native long getInt64Property(PROPKEY propkey, int i);

    public native int getIntProperty(PROPKEY propkey);

    public native int getIntProperty(PROPKEY propkey, int i);

    public native int getObjectID();

    public native boolean getProperty(PROPKEY propkey, Metatag metatag);

    public native boolean getPropertyOrDefault(PROPKEY propkey, Metatag metatag);

    public native boolean setExtendedProperty(Metatag metatag);

    public native boolean setExtendedProperty(PROPKEY propkey, int i);

    public native void unlink();

    public boolean equals(Object o) {
        if ((o instanceof ObjectInterface) && ((ObjectInterface) o).getObjectID() == getObjectID()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getObjectID();
    }

    public ObjectInterfaceImpl() {
        this(SkypeFactory.getInstance());
    }

    public ObjectInterfaceImpl(ObjectInterfaceFactory factory) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = factory.createObjectInterface();
        b.getInstance().addDestructibleObject(factory, this);
    }

    protected ObjectInterfaceImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected ObjectInterfaceImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_listeners = new HashSet();
        this.m_nativeObject = nativeObject;
        b.getInstance().addDestructibleObject(factory, this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public void addListener(ObjectInterfaceIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ObjectInterfaceIListener listener) {
        synchronized (this.m_listeners) {
            this.m_listeners.remove(listener);
        }
    }

    public void onPropertyChange(PROPKEY propKey) {
        synchronized (this.m_listeners) {
            for (ObjectInterfaceIListener onPropertyChange : this.m_listeners) {
                onPropertyChange.onPropertyChange(this, propKey);
            }
        }
    }

    public String getStrPropertyWithXmlStripped(PROPKEY propKey, String defaultValue) {
        return NativeStringConvert.ConvertFromNativeBytes(getStrPropertyWithXmlStrippedNativeString(propKey, NativeStringConvert.ConvertToNativeBytes(defaultValue)));
    }

    public boolean setExtendedProperty(PROPKEY propKey, String value) {
        return setExtendedProperty(propKey, NativeStringConvert.ConvertToNativeBytes(value));
    }

    public String getStrProperty(PROPKEY propKey) {
        return NativeStringConvert.ConvertFromNativeBytes(getStrPropertyNativeString(propKey));
    }

    public String getStrProperty(PROPKEY propKey, String defaultValue) {
        return NativeStringConvert.ConvertFromNativeBytes(getStrPropertyNativeString(propKey, NativeStringConvert.ConvertToNativeBytes(defaultValue)));
    }
}
