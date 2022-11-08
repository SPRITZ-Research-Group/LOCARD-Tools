package android.arch.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ReflectiveGenericLifecycleObserver implements a {
    static final Map<Class, a> a = new HashMap();
    private final Object b;
    private final a c = a(this.b.getClass());

    static class a {
        final Map<android.arch.lifecycle.b.a, List<b>> a = new HashMap();
        final Map<b, android.arch.lifecycle.b.a> b;

        a(Map<b, android.arch.lifecycle.b.a> handlerToEvent) {
            this.b = handlerToEvent;
            for (Entry<b, android.arch.lifecycle.b.a> entry : handlerToEvent.entrySet()) {
                android.arch.lifecycle.b.a event = (android.arch.lifecycle.b.a) entry.getValue();
                List<b> methodReferences = (List) this.a.get(event);
                if (methodReferences == null) {
                    methodReferences = new ArrayList();
                    this.a.put(event, methodReferences);
                }
                methodReferences.add(entry.getKey());
            }
        }
    }

    static class b {
        final int a;
        final Method b;

        b(int callType, Method method) {
            this.a = callType;
            this.b = method;
            this.b.setAccessible(true);
        }

        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            b that = (b) o;
            if (this.a == that.a && this.b.getName().equals(that.b.getName())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.a * 31) + this.b.getName().hashCode();
        }
    }

    ReflectiveGenericLifecycleObserver(Object wrapped) {
        this.b = wrapped;
    }

    public final void a(c source, android.arch.lifecycle.b.a event) {
        a aVar = this.c;
        a((List) aVar.a.get(event), source, event);
        a((List) aVar.a.get(android.arch.lifecycle.b.a.ON_ANY), source, event);
    }

    private void a(List<b> handlers, c source, android.arch.lifecycle.b.a event) {
        if (handlers != null) {
            int i = handlers.size() - 1;
            while (i >= 0) {
                b reference = (b) handlers.get(i);
                try {
                    switch (reference.a) {
                        case 0:
                            reference.b.invoke(this.b, new Object[0]);
                            break;
                        case 1:
                            reference.b.invoke(this.b, new Object[]{source});
                            break;
                        case 2:
                            reference.b.invoke(this.b, new Object[]{source, event});
                            break;
                        default:
                            break;
                    }
                    i--;
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Failed to call observer method", e.getCause());
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }

    private static a a(Class klass) {
        a existing = (a) a.get(klass);
        if (existing != null) {
            return existing;
        }
        Class superclass = klass.getSuperclass();
        Map hashMap = new HashMap();
        if (superclass != null) {
            a a = a(superclass);
            if (a != null) {
                hashMap.putAll(a.b);
            }
        }
        Method[] declaredMethods = klass.getDeclaredMethods();
        for (Class a2 : klass.getInterfaces()) {
            for (Entry entry : a(a2).b.entrySet()) {
                a(hashMap, (b) entry.getKey(), (android.arch.lifecycle.b.a) entry.getValue(), klass);
            }
        }
        for (Method method : declaredMethods) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                int i;
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(c.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                android.arch.lifecycle.b.a value = onLifecycleEvent.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(android.arch.lifecycle.b.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value != android.arch.lifecycle.b.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    } else {
                        i = 2;
                    }
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                a(hashMap, new b(i, method), value, klass);
            }
        }
        existing = new a(hashMap);
        a.put(klass, existing);
        return existing;
    }

    private static void a(Map<b, android.arch.lifecycle.b.a> handlers, b newHandler, android.arch.lifecycle.b.a newEvent, Class klass) {
        android.arch.lifecycle.b.a event = (android.arch.lifecycle.b.a) handlers.get(newHandler);
        if (event != null && newEvent != event) {
            throw new IllegalArgumentException("Method " + newHandler.b.getName() + " in " + klass.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event + ", new value " + newEvent);
        } else if (event == null) {
            handlers.put(newHandler, newEvent);
        }
    }
}
