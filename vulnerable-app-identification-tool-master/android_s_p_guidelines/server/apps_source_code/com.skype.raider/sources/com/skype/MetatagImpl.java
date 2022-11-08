package com.skype;

import com.skype.Metatag.TYPE;
import java.lang.ref.ReferenceQueue;

public class MetatagImpl implements Metatag, a {
    protected int m_nativeObject;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyMetatag(this.nativeObject);
        }
    }

    private native byte[] getStrValueNativeString();

    public native int getAllocedSize();

    public native byte[] getBinValue();

    public native long getInt64Value();

    public native int getIntValue();

    public native int getKey();

    public native TYPE getType();

    public native boolean isFullEqual(Metatag metatag);

    public native boolean isKeyEqual(Metatag metatag);

    public native boolean isNull();

    public native boolean isValueEqual(Metatag metatag);

    public MetatagImpl() {
        this(SkypeFactory.getInstance());
    }

    public MetatagImpl(ObjectInterfaceFactory factory) {
        this.m_nativeObject = factory.createMetatag();
        b.getInstance().addDestructibleObject(factory, this);
    }

    public MetatagImpl(int key, long value) {
        this(SkypeFactory.getInstance(), key, value);
    }

    public MetatagImpl(ObjectInterfaceFactory factory, int key, long value) {
        this.m_nativeObject = factory.createMetatag(key, value);
        b.getInstance().addDestructibleObject(factory, this);
    }

    public MetatagImpl(int key, byte[] value, boolean isString) {
        this(SkypeFactory.getInstance(), key, value, isString);
    }

    public MetatagImpl(ObjectInterfaceFactory factory, int key, byte[] value, boolean isString) {
        if (isString) {
            this.m_nativeObject = factory.createMetatag(key, NativeStringConvert.ConvertFromNativeBytes(value));
        } else {
            this.m_nativeObject = factory.createMetatag(key, value);
        }
        b.getInstance().addDestructibleObject(factory, this);
    }

    protected MetatagImpl(int nativeObject) {
        this(SkypeFactory.getInstance(), nativeObject);
    }

    protected MetatagImpl(ObjectInterfaceFactory factory, int nativeObject) {
        this.m_nativeObject = nativeObject;
        b.getInstance().addDestructibleObject(factory, this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }

    public String getStrValue() {
        return NativeStringConvert.ConvertFromNativeBytes(getStrValueNativeString());
    }
}
