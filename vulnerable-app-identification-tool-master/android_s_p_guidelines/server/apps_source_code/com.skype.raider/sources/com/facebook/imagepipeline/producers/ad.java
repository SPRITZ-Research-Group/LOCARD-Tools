package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.l.b;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class ad<K, T extends Closeable> implements aj<T> {
    @GuardedBy("this")
    @VisibleForTesting
    final Map<K, a> a = new HashMap();
    private final aj<T> b;

    @VisibleForTesting
    class a {
        final /* synthetic */ ad a;
        private final K b;
        private final CopyOnWriteArraySet<Pair<Consumer<T>, ak>> c = new CopyOnWriteArraySet();
        @GuardedBy("Multiplexer.this")
        @Nullable
        private T d;
        @GuardedBy("Multiplexer.this")
        private float e;
        @GuardedBy("Multiplexer.this")
        private int f;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private d g;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private com.facebook.imagepipeline.producers.ad$a.a h;

        private class a extends b<T> {
            final /* synthetic */ a a;

            private a(a aVar) {
                this.a = aVar;
            }

            /* synthetic */ a(a x0, byte b) {
                this(x0);
            }

            protected final /* bridge */ /* synthetic */ void a(Object obj, int i) {
                Closeable closeable = (Closeable) obj;
                try {
                    b.a();
                    this.a.a(this, closeable, i);
                } finally {
                    b.a();
                }
            }

            protected final void a(Throwable t) {
                try {
                    b.a();
                    this.a.a(this, t);
                } finally {
                    b.a();
                }
            }

            protected final void a() {
                try {
                    b.a();
                    this.a.a(this);
                } finally {
                    b.a();
                }
            }

            protected final void a(float progress) {
                try {
                    b.a();
                    this.a.a(this, progress);
                } finally {
                    b.a();
                }
            }
        }

        public a(ad this$0, K key) {
            this.a = this$0;
            this.b = key;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(Consumer<T> consumer, ak producerContext) {
            final Pair<Consumer<T>, ak> consumerContextPair = Pair.create(consumer, producerContext);
            synchronized (this) {
                if (this.a.a(this.b) != this) {
                    return false;
                }
                this.c.add(consumerContextPair);
                List prefetchCallbacks = b();
                List<al> priorityCallbacks = f();
                List intermediateResultsCallbacks = d();
                Closeable lastIntermediateResult = this.d;
                float lastProgress = this.e;
                int lastStatus = this.f;
            }
        }

        private void a() {
            boolean z = true;
            synchronized (this) {
                h.a(this.g == null);
                if (this.h != null) {
                    z = false;
                }
                h.a(z);
                if (this.c.isEmpty()) {
                    this.a.a(this.b, this);
                    return;
                }
                ak producerContext = ((Pair) this.c.iterator().next()).second;
                this.g = new d(producerContext.a(), producerContext.b(), producerContext.c(), producerContext.d(), producerContext.e(), c(), e(), g());
                this.h = new a();
                d multiplexProducerContext = this.g;
                com.facebook.imagepipeline.producers.ad$a.a forwardingConsumer = this.h;
                this.a.b.a(forwardingConsumer, multiplexProducerContext);
            }
        }

        @Nullable
        private synchronized List<al> b() {
            List<al> list;
            if (this.g == null) {
                list = null;
            } else {
                list = this.g.a(c());
            }
            return list;
        }

        private synchronized boolean c() {
            boolean z;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                if (!((ak) ((Pair) it.next()).second).f()) {
                    z = false;
                    break;
                }
            }
            z = true;
            return z;
        }

        @Nullable
        private synchronized List<al> d() {
            List<al> list;
            if (this.g == null) {
                list = null;
            } else {
                list = this.g.b(e());
            }
            return list;
        }

        private synchronized boolean e() {
            boolean z;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                if (((ak) ((Pair) it.next()).second).h()) {
                    z = true;
                    break;
                }
            }
            z = false;
            return z;
        }

        @Nullable
        private synchronized List<al> f() {
            List<al> list;
            if (this.g == null) {
                list = null;
            } else {
                list = this.g.a(g());
            }
            return list;
        }

        private synchronized d g() {
            d priority;
            priority = d.LOW;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                priority = d.a(priority, ((ak) ((Pair) it.next()).second).g());
            }
            return priority;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.facebook.imagepipeline.producers.ad$a.a consumer, Throwable t) {
            synchronized (this) {
                if (this.h != consumer) {
                    return;
                }
                Iterator<Pair<Consumer<T>, ak>> iterator = this.c.iterator();
                this.c.clear();
                this.a.a(this.b, this);
                a(this.d);
                this.d = null;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.facebook.imagepipeline.producers.ad$a.a consumer, T closeableObject, int status) {
            synchronized (this) {
                if (this.h != consumer) {
                    return;
                }
                a(this.d);
                this.d = null;
                Iterator<Pair<Consumer<T>, ak>> iterator = this.c.iterator();
                if (b.b(status)) {
                    this.d = this.a.a((Closeable) closeableObject);
                    this.f = status;
                } else {
                    this.c.clear();
                    this.a.a(this.b, this);
                }
            }
        }

        public final void a(com.facebook.imagepipeline.producers.ad$a.a forwardingConsumer) {
            synchronized (this) {
                if (this.h != forwardingConsumer) {
                    return;
                }
                this.h = null;
                this.g = null;
                a(this.d);
                this.d = null;
                a();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.facebook.imagepipeline.producers.ad$a.a forwardingConsumer, float progress) {
            synchronized (this) {
                if (this.h != forwardingConsumer) {
                } else {
                    this.e = progress;
                    Iterator<Pair<Consumer<T>, ak>> iterator = this.c.iterator();
                }
            }
        }

        private static void a(Closeable obj) {
            if (obj != null) {
                try {
                    obj.close();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        }
    }

    protected abstract T a(T t);

    protected abstract K a(ak akVar);

    protected ad(aj<T> inputProducer) {
        this.b = inputProducer;
    }

    public final void a(Consumer<T> consumer, ak context) {
        try {
            a multiplexer;
            boolean createdNewMultiplexer;
            b.a();
            Object key = a(context);
            do {
                createdNewMultiplexer = false;
                synchronized (this) {
                    multiplexer = a(key);
                    if (multiplexer == null) {
                        multiplexer = b(key);
                        createdNewMultiplexer = true;
                    }
                }
            } while (!multiplexer.a((Consumer) consumer, context));
            if (createdNewMultiplexer) {
                multiplexer.a();
            }
            b.a();
        } catch (Throwable th) {
            b.a();
        }
    }

    private synchronized a a(K key) {
        return (a) this.a.get(key);
    }

    private synchronized a b(K key) {
        a multiplexer;
        multiplexer = new a(this, key);
        this.a.put(key, multiplexer);
        return multiplexer;
    }

    private synchronized void a(K key, a multiplexer) {
        if (this.a.get(key) == multiplexer) {
            this.a.remove(key);
        }
    }
}
