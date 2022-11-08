package com.linecorp.rxeventbus;

import com.google.obf.ly;
import defpackage.lii;
import defpackage.lij;
import defpackage.lik;
import defpackage.oyn;
import defpackage.oyu;
import defpackage.ozh;
import defpackage.ozn;
import defpackage.paj;
import defpackage.qaa;
import defpackage.qak;
import defpackage.qaq;
import defpackage.qaz;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

public final class a {
    private final ConcurrentMap<Class<?>, List<c>> a;
    private final ConcurrentMap<Object, List<b>> b;
    private final ConcurrentMap<Class<?>, qaz<?>> c;
    private final Map<SubscriberType, ConcurrentMap<Class<?>, oyn<?>>> d;
    private final ConcurrentMap<Class<?>, List<Class<?>>> e;
    private final oyu f;
    private final oyu g;
    private final lii h;

    public a(ExecutorService executorService) {
        this(executorService, new lik().a().b());
    }

    private a(ExecutorService executorService, lij lij) {
        this(ozh.a(), qaa.a(executorService), lij);
    }

    private a(oyu oyu, oyu oyu2, lij lij) {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.d = new EnumMap(SubscriberType.class);
        for (Object put : SubscriberType.values()) {
            this.d.put(put, new ConcurrentHashMap());
        }
        this.c = new ConcurrentHashMap();
        this.e = new ConcurrentHashMap();
        this.f = oyu;
        this.g = oyu2;
        this.h = new lii(lij);
    }

    private static boolean a(AnnotatedElement annotatedElement) {
        return annotatedElement.getAnnotation(EnableSticky.class) != null;
    }

    private static void a(Collection<Class<?>> collection) {
        if (!collection.isEmpty()) {
            for (AnnotatedElement a : collection) {
                a(a);
            }
        }
    }

    public final void a(Object obj) {
        AnnotatedElement annotatedElement = obj.getClass();
        Iterable iterable;
        if (this.e.containsKey(annotatedElement)) {
            iterable = (Iterable) this.e.get(annotatedElement);
        } else {
            boolean a = a(annotatedElement);
            Iterable linkedList = new LinkedList();
            for (Class cls = annotatedElement; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                linkedList.add(cls);
                linkedList.addAll(Arrays.asList(cls.getInterfaces()));
            }
            a((Collection) linkedList);
            if (a) {
                a(linkedList);
            }
            this.e.putIfAbsent(annotatedElement, linkedList);
            iterable = (Iterable) this.e.get(annotatedElement);
        }
        for (Class cls2 : iterable) {
            qaz qaz = (qaz) this.c.get(cls2);
            if (qaz != null) {
                qaz.a_(obj);
            }
        }
    }

    public final void b(Object obj) {
        if (!this.b.containsKey(obj)) {
            Class cls = obj.getClass();
            List linkedList = new LinkedList();
            for (c cVar : a(cls)) {
                paj -__lambda_a_ltl3o2ed0gn5xkry1ylidghf6vy = new -$$Lambda$a$ltL3o2ed0Gn5XKRy1YLiDgHF6VY(this, obj, cVar.a, cVar.c);
                linkedList.add(new b(-__lambda_a_ltl3o2ed0gn5xkry1ylidghf6vy, cVar.d.filter(a(cVar.b, cVar.c), cVar.e, a(cVar.c)).d(-__lambda_a_ltl3o2ed0gn5xkry1ylidghf6vy)));
            }
            this.b.putIfAbsent(obj, linkedList);
        }
    }

    public final void c(Object obj) {
        List<b> list = (List) this.b.remove(obj);
        if (list != null) {
            for (b bVar : list) {
                bVar.b.dispose();
            }
        }
    }

    private /* synthetic */ void a(Object obj, Method method, SubscriberType subscriberType, Object obj2) throws Exception {
        this.h.a(obj, method, subscriberType, obj2);
        try {
            method.invoke(obj, new Object[]{obj2});
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder("Exception happens while calling: ");
            stringBuilder.append(obj);
            stringBuilder.append(ly.a);
            stringBuilder.append(method);
            stringBuilder.append("(");
            stringBuilder.append(obj2);
            stringBuilder.append(")");
            throw new RuntimeException(stringBuilder.toString(), e);
        }
    }

    private Iterable<c> a(Class<?> cls) {
        if (this.a.containsKey(cls)) {
            return (Iterable) this.a.get(cls);
        }
        List linkedList = new LinkedList();
        for (Method method : cls.getMethods()) {
            Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
            if (subscribe == null) {
                method.getAnnotation(IntervalFilter.class);
            } else {
                Class cls2 = method.getParameterTypes()[0];
                SubscriberType a = subscribe.a();
                IntervalFilter intervalFilter = (IntervalFilter) method.getAnnotation(IntervalFilter.class);
                linkedList.add(new c(method, cls2, a, intervalFilter != null ? intervalFilter.a() : IntervalFilterType.NONE, intervalFilter != null ? intervalFilter.b() : 1000));
            }
        }
        this.a.putIfAbsent(cls, linkedList);
        return (Iterable) this.a.get(cls);
    }

    private void a(Iterable<Class<?>> iterable) {
        for (Class cls : iterable) {
            for (SubscriberType a : SubscriberType.STICKY_TYPES) {
                a(cls, a);
            }
        }
    }

    private oyn<?> a(Class<?> cls, SubscriberType subscriberType) {
        ConcurrentMap concurrentMap = (ConcurrentMap) this.d.get(subscriberType);
        if (concurrentMap.containsKey(cls)) {
            return (oyn) concurrentMap.get(cls);
        }
        if (subscriberType.b()) {
            cls.getAnnotation(EnableSticky.class);
        }
        this.c.putIfAbsent(cls, qaq.p());
        oyn oyn = (qaz) this.c.get(cls);
        ozn ozn = null;
        if (subscriberType.b()) {
            oyn p = qak.p();
            p.getClass();
            oyn oyn2 = p;
            ozn = oyn.d(new -$$Lambda$hqIvz5kAILZSDWkhlMni4Vh22Tg(p));
            oyn = oyn2;
        }
        if (!(((oyn) concurrentMap.putIfAbsent(cls, oyn.a(a(subscriberType)))) == null || ozn == null)) {
            ozn.dispose();
        }
        return (oyn) concurrentMap.get(cls);
    }

    private oyu a(SubscriberType subscriberType) {
        return subscriberType.a() ? this.g : this.f;
    }
}
