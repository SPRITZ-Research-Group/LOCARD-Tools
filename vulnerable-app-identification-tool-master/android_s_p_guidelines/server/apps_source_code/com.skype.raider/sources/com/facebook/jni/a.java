package com.facebook.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class a {
    private static b a = new b();
    private static c b = new c();
    private static ReferenceQueue c = new ReferenceQueue();
    private static Thread d;

    public static abstract class a extends PhantomReference<Object> {
        private a a;
        private a b;

        abstract void a();

        /* synthetic */ a(byte b) {
            this();
        }

        a(Object referent) {
            super(referent, a.c);
            a.b.a(this);
        }

        private a() {
            super(null, a.c);
        }
    }

    private static class b {
        private a a = new d();

        public b() {
            this.a.a = new d();
            this.a.a.b = this.a;
        }

        public final void a(a current) {
            current.a = this.a.a;
            this.a.a = current;
            current.a.b = current;
            current.b = this.a;
        }
    }

    private static class c {
        private AtomicReference<a> a;

        private c() {
            this.a = new AtomicReference();
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final void a(a newHead) {
            a oldHead;
            do {
                oldHead = (a) this.a.get();
                newHead.a = oldHead;
            } while (!this.a.compareAndSet(oldHead, newHead));
        }

        public final void a() {
            a current = (a) this.a.getAndSet(null);
            while (current != null) {
                a next = current.a;
                a.a.a(current);
                current = next;
            }
        }
    }

    private static class d extends a {
        private d() {
            super();
        }

        /* synthetic */ d(byte b) {
            this();
        }

        final void a() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    static {
        Thread anonymousClass1 = new Thread("HybridData DestructorThread") {
            public final void run() {
                while (true) {
                    try {
                        a current = (a) a.c.remove();
                        current.a();
                        if (current.b == null) {
                            a.b.a();
                        }
                        current.a.b = current.b;
                        current.b.a = current.a;
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        d = anonymousClass1;
        anonymousClass1.start();
    }
}
