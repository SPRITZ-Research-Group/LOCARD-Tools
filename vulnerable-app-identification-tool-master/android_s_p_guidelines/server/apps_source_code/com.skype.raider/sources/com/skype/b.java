package com.skype;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class b {
    private static volatile b instance;
    private final ReferenceQueue<a> queue = new ReferenceQueue();
    private final Set<NativeWeakRef<?>> referencesSet = Collections.newSetFromMap(new ConcurrentHashMap());
    private Thread thread = new Thread("ShutdownManager") {
        public final void run() {
            while (true) {
                try {
                    NativeWeakRef<? extends a> reference = (NativeWeakRef) b.this.queue.remove();
                    b.this.referencesSet.remove(reference);
                    reference.destroyNativeObject();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private b() {
        this.thread.setPriority(1);
        this.thread.setDaemon(true);
        this.thread.start();
    }

    public static b getInstance() {
        if (instance == null) {
            synchronized (b.class) {
                if (instance == null) {
                    instance = new b();
                }
            }
        }
        return instance;
    }

    public void addDestructibleObject(ObjectInterfaceFactory factory, a destructible) {
        this.referencesSet.add(destructible.createNativeWeakRef(factory, this.queue));
    }
}
