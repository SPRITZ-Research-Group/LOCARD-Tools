package com.google.gson;

import com.google.gson.a.a.g;
import com.google.gson.a.a.h;
import com.google.gson.a.a.i;
import com.google.gson.a.a.j;
import com.google.gson.a.a.k;
import com.google.gson.a.a.n;
import com.google.gson.a.c;
import com.google.gson.a.d;
import com.google.gson.c.b;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class e {
    private static final com.google.gson.b.a<?> a = new com.google.gson.b.a<Object>() {
    };
    private final ThreadLocal<Map<com.google.gson.b.a<?>, a<?>>> b;
    private final Map<com.google.gson.b.a<?>, r<?>> c;
    private final List<s> d;
    private final c e;
    private final d f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final com.google.gson.a.a.d m;

    static class a<T> extends r<T> {
        private r<T> a;

        a() {
        }

        public final void a(r<T> typeAdapter) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = typeAdapter;
        }

        public final T a(com.google.gson.c.a in) throws IOException {
            if (this.a != null) {
                return this.a.a(in);
            }
            throw new IllegalStateException();
        }

        public final void a(com.google.gson.c.c out, T value) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(out, value);
        }
    }

    public e() {
        this(d.a, c.a, Collections.emptyMap(), q.a, Collections.emptyList());
    }

    private e(d excluder, d fieldNamingStrategy, Map<Type, f<?>> instanceCreators, q longSerializationPolicy, List<s> typeAdapterFactories) {
        r<Number> longAdapter;
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new c(instanceCreators);
        this.f = excluder;
        this.g = fieldNamingStrategy;
        this.h = false;
        this.j = false;
        this.i = true;
        this.k = false;
        this.l = false;
        List<s> factories = new ArrayList();
        factories.add(n.Y);
        factories.add(h.a);
        factories.add(excluder);
        factories.addAll(typeAdapterFactories);
        factories.add(n.D);
        factories.add(n.m);
        factories.add(n.g);
        factories.add(n.i);
        factories.add(n.k);
        if (longSerializationPolicy == q.a) {
            longAdapter = n.t;
        } else {
            longAdapter = new r<Number>() {
                public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
                    Number number = (Number) obj;
                    if (number == null) {
                        cVar.f();
                    } else {
                        cVar.b(number.toString());
                    }
                }

                public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                    if (aVar.f() != b.NULL) {
                        return Long.valueOf(aVar.l());
                    }
                    aVar.j();
                    return null;
                }
            };
        }
        factories.add(n.a(Long.TYPE, Long.class, longAdapter));
        factories.add(n.a(Double.TYPE, Double.class, new r<Number>(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
                Number number = (Number) obj;
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a(number.doubleValue());
                cVar.a(number);
            }

            public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                if (aVar.f() != b.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }
        }));
        factories.add(n.a(Float.TYPE, Float.class, new r<Number>(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
                Number number = (Number) obj;
                if (number == null) {
                    cVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                cVar.a(number);
            }

            public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                if (aVar.f() != b.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }
        }));
        factories.add(n.x);
        factories.add(n.o);
        factories.add(n.q);
        factories.add(n.a(AtomicLong.class, new r<AtomicLong>() {
            public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
                longAdapter.a(cVar, Long.valueOf(((AtomicLong) obj).get()));
            }

            public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                return new AtomicLong(((Number) longAdapter.a(aVar)).longValue());
            }
        }.a()));
        factories.add(n.a(AtomicLongArray.class, new r<AtomicLongArray>() {
            public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                cVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    longAdapter.a(cVar, Long.valueOf(atomicLongArray.get(i)));
                }
                cVar.c();
            }

            public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) longAdapter.a(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a()));
        factories.add(n.s);
        factories.add(n.z);
        factories.add(n.F);
        factories.add(n.H);
        factories.add(n.a(BigDecimal.class, n.B));
        factories.add(n.a(BigInteger.class, n.C));
        factories.add(n.J);
        factories.add(n.L);
        factories.add(n.P);
        factories.add(n.R);
        factories.add(n.W);
        factories.add(n.N);
        factories.add(n.d);
        factories.add(com.google.gson.a.a.c.a);
        factories.add(n.U);
        factories.add(k.a);
        factories.add(j.a);
        factories.add(n.S);
        factories.add(com.google.gson.a.a.a.a);
        factories.add(n.b);
        factories.add(new com.google.gson.a.a.b(this.e));
        factories.add(new g(this.e));
        this.m = new com.google.gson.a.a.d(this.e);
        factories.add(this.m);
        factories.add(n.Z);
        factories.add(new i(this.e, fieldNamingStrategy, excluder, this.m));
        this.d = Collections.unmodifiableList(factories);
    }

    static void a(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(value + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final <T> r<T> a(com.google.gson.b.a<T> type) {
        Object obj;
        Map map = this.c;
        if (type == null) {
            obj = a;
        } else {
            com.google.gson.b.a<T> obj2 = type;
        }
        r<?> cached = (r) map.get(obj2);
        if (cached != null) {
            return cached;
        }
        Map<com.google.gson.b.a<?>, a<?>> threadCalls = (Map) this.b.get();
        boolean requiresThreadLocalCleanup = false;
        if (threadCalls == null) {
            threadCalls = new HashMap();
            this.b.set(threadCalls);
            requiresThreadLocalCleanup = true;
        }
        r ongoingCall = (a) threadCalls.get(type);
        if (ongoingCall != null) {
            return ongoingCall;
        }
        try {
            a<T> call = new a();
            threadCalls.put(type, call);
            for (s a : this.d) {
                r<?> candidate = a.a(this, type);
                if (candidate != null) {
                    call.a((r) candidate);
                    this.c.put(type, candidate);
                    return candidate;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + type);
        } finally {
            threadCalls.remove(type);
            if (requiresThreadLocalCleanup) {
                this.b.remove();
            }
        }
    }

    public final <T> r<T> a(s skipPast, com.google.gson.b.a<T> type) {
        if (!this.d.contains(skipPast)) {
            skipPast = this.m;
        }
        boolean skipPastFound = false;
        for (s factory : this.d) {
            if (skipPastFound) {
                r<T> candidate = factory.a(this, type);
                if (candidate != null) {
                    return candidate;
                }
            } else if (factory == skipPast) {
                skipPastFound = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + type);
    }

    public final <T> r<T> a(Class<T> type) {
        return a(com.google.gson.b.a.a((Class) type));
    }

    public final String a(Object src, Type typeOfSrc) {
        StringWriter writer = new StringWriter();
        try {
            Writer writer2 = writer;
            if (this.j) {
                writer2.write(")]}'\n");
            }
            com.google.gson.c.c cVar = new com.google.gson.c.c(writer2);
            if (this.k) {
                cVar.c("  ");
            }
            cVar.d(this.h);
            r a = a(com.google.gson.b.a.a(typeOfSrc));
            boolean g = cVar.g();
            cVar.b(true);
            boolean h = cVar.h();
            cVar.c(this.i);
            boolean i = cVar.i();
            cVar.d(this.h);
            try {
                a.a(cVar, src);
                cVar.b(g);
                cVar.c(h);
                cVar.d(i);
                return writer.toString();
            } catch (Throwable e) {
                throw new j(e);
            } catch (Throwable th) {
                cVar.b(g);
                cVar.c(h);
                cVar.d(i);
            }
        } catch (Throwable e2) {
            throw new j(e2);
        }
    }

    private <T> T a(com.google.gson.c.a reader, Type typeOfT) throws j, p {
        boolean isEmpty = true;
        boolean oldLenient = reader.q();
        reader.a(true);
        try {
            reader.f();
            isEmpty = false;
            T a = a(com.google.gson.b.a.a(typeOfT)).a(reader);
            reader.a(oldLenient);
            return a;
        } catch (Throwable e) {
            if (isEmpty) {
                reader.a(oldLenient);
                return null;
            }
            throw new p(e);
        } catch (Throwable e2) {
            throw new p(e2);
        } catch (Throwable e22) {
            throw new p(e22);
        } catch (Throwable th) {
            reader.a(oldLenient);
        }
    }

    public final String toString() {
        return "{serializeNulls:" + this.h + "factories:" + this.d + ",instanceCreators:" + this.e + "}";
    }

    public final <T> T a(String json, Class<T> classOfT) throws p {
        Object object;
        if (json == null) {
            object = null;
        } else {
            com.google.gson.c.a aVar = new com.google.gson.c.a(new StringReader(json));
            aVar.a(this.l);
            object = a(aVar, (Type) classOfT);
            if (object != null) {
                try {
                    if (aVar.f() != b.END_DOCUMENT) {
                        throw new j("JSON document was not fully consumed.");
                    }
                } catch (Throwable e) {
                    throw new p(e);
                } catch (Throwable e2) {
                    throw new j(e2);
                }
            }
        }
        return com.google.gson.a.i.a((Class) classOfT).cast(object);
    }
}
