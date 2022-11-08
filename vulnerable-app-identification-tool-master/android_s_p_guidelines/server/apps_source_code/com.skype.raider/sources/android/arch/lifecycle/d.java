package android.arch.lifecycle;

import android.arch.lifecycle.b.b;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public final class d extends b {
    private android.arch.a.a.a<Object, a> a = new android.arch.a.a.a();
    private b b;
    private final c c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<b> g = new ArrayList();

    static class a {
        b a;
        a b;

        final void a(c owner, android.arch.lifecycle.b.a event) {
            b newState = d.b(event);
            b bVar = this.a;
            if (newState != null && newState.compareTo(bVar) < 0) {
                bVar = newState;
            }
            this.a = bVar;
            this.b.a(owner, event);
            this.a = newState;
        }
    }

    public d(@NonNull c provider) {
        this.c = provider;
        this.b = b.INITIALIZED;
    }

    public final void a(b state) {
        this.b = state;
    }

    public final void a(android.arch.lifecycle.b.a event) {
        this.b = b(event);
        if (this.e || this.d != 0) {
            this.f = true;
            return;
        }
        this.e = true;
        while (true) {
            boolean z;
            if (this.a.a() == 0) {
                z = true;
            } else {
                b bVar = ((a) this.a.d().getValue()).a;
                b bVar2 = ((a) this.a.e().getValue()).a;
                z = bVar == bVar2 && this.b == bVar2;
            }
            if (z) {
                this.f = false;
                this.e = false;
                return;
            }
            Entry entry;
            this.f = false;
            if (this.b.compareTo(((a) this.a.d().getValue()).a) < 0) {
                Iterator b = this.a.b();
                while (b.hasNext() && !this.f) {
                    entry = (Entry) b.next();
                    a aVar = (a) entry.getValue();
                    while (aVar.a.compareTo(this.b) > 0 && !this.f && this.a.a(entry.getKey())) {
                        android.arch.lifecycle.b.a aVar2;
                        b bVar3 = aVar.a;
                        switch (bVar3) {
                            case INITIALIZED:
                                throw new IllegalArgumentException();
                            case CREATED:
                                aVar2 = android.arch.lifecycle.b.a.ON_DESTROY;
                                break;
                            case STARTED:
                                aVar2 = android.arch.lifecycle.b.a.ON_STOP;
                                break;
                            case RESUMED:
                                aVar2 = android.arch.lifecycle.b.a.ON_PAUSE;
                                break;
                            case DESTROYED:
                                throw new IllegalArgumentException();
                            default:
                                throw new IllegalArgumentException("Unexpected state value " + bVar3);
                        }
                        b(b(aVar2));
                        aVar.a(this.c, aVar2);
                        a();
                    }
                }
            }
            entry = this.a.e();
            if (!(this.f || entry == null || this.b.compareTo(((a) entry.getValue()).a) <= 0)) {
                b();
            }
        }
    }

    private void a() {
        this.g.remove(this.g.size() - 1);
    }

    private void b(b state) {
        this.g.add(state);
    }

    static b b(android.arch.lifecycle.b.a event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return b.CREATED;
            case ON_START:
            case ON_PAUSE:
                return b.STARTED;
            case ON_RESUME:
                return b.RESUMED;
            case ON_DESTROY:
                return b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    private void b() {
        Iterator ascendingIterator = this.a.c();
        while (ascendingIterator.hasNext() && !this.f) {
            Entry entry = (Entry) ascendingIterator.next();
            a observer = (a) entry.getValue();
            while (observer.a.compareTo(this.b) < 0 && !this.f && this.a.a(entry.getKey())) {
                android.arch.lifecycle.b.a aVar;
                b(observer.a);
                c cVar = this.c;
                b bVar = observer.a;
                switch (bVar) {
                    case INITIALIZED:
                    case DESTROYED:
                        aVar = android.arch.lifecycle.b.a.ON_CREATE;
                        break;
                    case CREATED:
                        aVar = android.arch.lifecycle.b.a.ON_START;
                        break;
                    case STARTED:
                        aVar = android.arch.lifecycle.b.a.ON_RESUME;
                        break;
                    case RESUMED:
                        throw new IllegalArgumentException();
                    default:
                        throw new IllegalArgumentException("Unexpected state value " + bVar);
                }
                observer.a(cVar, aVar);
                a();
            }
        }
    }
}
