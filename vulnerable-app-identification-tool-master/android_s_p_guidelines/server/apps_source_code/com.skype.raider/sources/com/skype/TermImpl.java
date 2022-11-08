package com.skype;

import com.skype.Term.CONDITION;
import java.lang.ref.ReferenceQueue;

public class TermImpl extends MetatagImpl implements Term {

    static class a extends NativeWeakRef<a> {
        private ObjectInterfaceFactory factory;

        a(ObjectInterfaceFactory factory, a ref, ReferenceQueue<a> queue, int nativeObject) {
            super(ref, queue, nativeObject);
            this.factory = factory;
        }

        public final void destroyNativeObject() {
            this.factory.destroyTerm(this.nativeObject);
        }
    }

    public TermImpl() {
        this(SkypeFactory.getInstance());
    }

    public TermImpl(ObjectInterfaceFactory factory) {
        super(factory, factory.createTerm());
    }

    public TermImpl(int key, CONDITION cond, long value) {
        this(SkypeFactory.getInstance(), key, cond, value);
    }

    public TermImpl(ObjectInterfaceFactory factory, int key, CONDITION cond, long value) {
        super(factory, factory.createTerm(key, cond, value));
    }

    public TermImpl(int key, CONDITION cond, byte[] value) {
        this(SkypeFactory.getInstance(), key, cond, value);
    }

    public TermImpl(ObjectInterfaceFactory factory, int key, CONDITION cond, byte[] value) {
        super(factory, factory.createTerm(key, cond, value));
    }

    public TermImpl(int key, CONDITION cond, String value) {
        this(SkypeFactory.getInstance(), key, cond, value);
    }

    public TermImpl(ObjectInterfaceFactory factory, int key, CONDITION cond, String value) {
        super(factory, factory.createTerm(key, cond, value));
    }

    public NativeWeakRef<a> createNativeWeakRef(ObjectInterfaceFactory factory, ReferenceQueue<a> queue) {
        return new a(factory, this, queue, this.m_nativeObject);
    }
}
