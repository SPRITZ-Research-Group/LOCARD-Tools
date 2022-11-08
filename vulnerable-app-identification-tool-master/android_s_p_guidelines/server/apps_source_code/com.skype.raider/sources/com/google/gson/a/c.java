package com.google.gson.a;

import com.google.gson.b.a;
import com.google.gson.f;
import com.google.gson.j;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class c {
    private final Map<Type, f<?>> a;

    public c(Map<Type, f<?>> instanceCreators) {
        this.a = instanceCreators;
    }

    public final <T> h<T> a(a<T> typeToken) {
        final Type type = typeToken.b();
        final Class rawType = typeToken.a();
        final f<T> typeCreator = (f) this.a.get(type);
        if (typeCreator != null) {
            return new h<T>(this) {
                final /* synthetic */ c c;

                public final T a() {
                    return typeCreator.a();
                }
            };
        }
        final f<T> rawTypeCreator = (f) this.a.get(rawType);
        if (rawTypeCreator != null) {
            return new h<T>(this) {
                final /* synthetic */ c c;

                public final T a() {
                    return rawTypeCreator.a();
                }
            };
        }
        h<T> defaultConstructor = a(rawType);
        if (defaultConstructor != null) {
            return defaultConstructor;
        }
        h<T> defaultImplementation;
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                defaultImplementation = new h<T>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final T a() {
                        return new TreeSet();
                    }
                };
            } else if (EnumSet.class.isAssignableFrom(rawType)) {
                defaultImplementation = new h<T>(this) {
                    final /* synthetic */ c b;

                    public final T a() {
                        if (type instanceof ParameterizedType) {
                            Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (elementType instanceof Class) {
                                return EnumSet.noneOf((Class) elementType);
                            }
                            throw new j("Invalid EnumSet type: " + type.toString());
                        }
                        throw new j("Invalid EnumSet type: " + type.toString());
                    }
                };
            } else if (Set.class.isAssignableFrom(rawType)) {
                defaultImplementation = new h<T>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final T a() {
                        return new LinkedHashSet();
                    }
                };
            } else if (Queue.class.isAssignableFrom(rawType)) {
                defaultImplementation = new h<T>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final T a() {
                        return new ArrayDeque();
                    }
                };
            } else {
                defaultImplementation = new h<T>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = this$0;
                    }

                    public final T a() {
                        return new ArrayList();
                    }
                };
            }
        } else if (!Map.class.isAssignableFrom(rawType)) {
            defaultImplementation = null;
        } else if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
            defaultImplementation = new h<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final T a() {
                    return new ConcurrentSkipListMap();
                }
            };
        } else if (ConcurrentMap.class.isAssignableFrom(rawType)) {
            defaultImplementation = new h<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final T a() {
                    return new ConcurrentHashMap();
                }
            };
        } else if (SortedMap.class.isAssignableFrom(rawType)) {
            defaultImplementation = new h<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final T a() {
                    return new TreeMap();
                }
            };
        } else if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
            defaultImplementation = new h<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final T a() {
                    return new g();
                }
            };
        } else {
            defaultImplementation = new h<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final T a() {
                    return new LinkedHashMap();
                }
            };
        }
        if (defaultImplementation != null) {
            return defaultImplementation;
        }
        return new h<T>(this) {
            final /* synthetic */ c c;
            private final k d = k.a();

            public final T a() {
                try {
                    return this.d.a(rawType);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    private <T> h<T> a(Class<? super T> rawType) {
        try {
            final Constructor<? super T> constructor = rawType.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return new h<T>(this) {
                final /* synthetic */ c b;

                public final T a() {
                    try {
                        return constructor.newInstance(null);
                    } catch (InstantiationException e) {
                        throw new RuntimeException("Failed to invoke " + constructor + " with no args", e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to invoke " + constructor + " with no args", e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public final String toString() {
        return this.a.toString();
    }
}
