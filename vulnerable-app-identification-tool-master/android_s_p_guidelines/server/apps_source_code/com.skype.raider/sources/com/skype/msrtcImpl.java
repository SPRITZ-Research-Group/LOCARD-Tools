package com.skype;

import java.lang.ref.ReferenceQueue;

public class msrtcImpl implements a, msrtc {
    protected int m_nativeObject;

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroymsrtc(this.nativeObject);
        }
    }

    public msrtcImpl() {
        this(SkypeFactory.getInstance());
    }

    public msrtcImpl(ObjectInterfaceFactory factory) {
        this.m_nativeObject = factory.createmsrtc();
        b.getInstance().addDestructibleObject(factory, this);
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }
}
