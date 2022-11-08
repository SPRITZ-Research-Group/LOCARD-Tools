package com.google.gson.a.a;

import com.google.gson.a.c;
import com.google.gson.a.h;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.d;
import com.google.gson.e;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class i implements s {
    private final c a;
    private final d b;
    private final com.google.gson.a.d c;
    private final d d;

    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        abstract void a(com.google.gson.c.a aVar, Object obj) throws IOException, IllegalAccessException;

        abstract void a(com.google.gson.c.c cVar, Object obj) throws IOException, IllegalAccessException;

        abstract boolean a(Object obj) throws IOException, IllegalAccessException;

        protected b(String name, boolean serialized, boolean deserialized) {
            this.h = name;
            this.i = serialized;
            this.j = deserialized;
        }
    }

    public static final class a<T> extends r<T> {
        private final h<T> a;
        private final Map<String, b> b;

        a(h<T> constructor, Map<String, b> boundFields) {
            this.a = constructor;
            this.b = boundFields;
        }

        public final T a(com.google.gson.c.a in) throws IOException {
            if (in.f() == com.google.gson.c.b.NULL) {
                in.j();
                return null;
            }
            Object instance = this.a.a();
            try {
                in.c();
                while (in.e()) {
                    b field = (b) this.b.get(in.g());
                    if (field == null || !field.j) {
                        in.n();
                    } else {
                        field.a(in, instance);
                    }
                }
                in.d();
                return instance;
            } catch (Throwable e) {
                throw new p(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public final void a(com.google.gson.c.c out, T value) throws IOException {
            if (value == null) {
                out.f();
                return;
            }
            out.d();
            try {
                for (b boundField : this.b.values()) {
                    if (boundField.a(value)) {
                        out.a(boundField.h);
                        boundField.a(out, (Object) value);
                    }
                }
                out.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public i(c constructorConstructor, d fieldNamingPolicy, com.google.gson.a.d excluder, d jsonAdapterFactory) {
        this.a = constructorConstructor;
        this.b = fieldNamingPolicy;
        this.c = excluder;
        this.d = jsonAdapterFactory;
    }

    private boolean a(Field f, boolean serialize) {
        com.google.gson.a.d dVar = this.c;
        return (dVar.a(f.getType(), serialize) || dVar.a(f, serialize)) ? false : true;
    }

    private List<String> a(Field f) {
        SerializedName annotation = (SerializedName) f.getAnnotation(SerializedName.class);
        if (annotation == null) {
            return Collections.singletonList(this.b.a(f));
        }
        String serializedName = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(serializedName);
        }
        List<String> fieldNames = new ArrayList(alternates.length + 1);
        fieldNames.add(serializedName);
        for (String alternate : alternates) {
            fieldNames.add(alternate);
        }
        return fieldNames;
    }

    public final <T> r<T> a(e gson, com.google.gson.b.a<T> type) {
        Class<? super T> raw = type.a();
        if (Object.class.isAssignableFrom(raw)) {
            return new a(this.a.a((com.google.gson.b.a) type), a(gson, type, raw));
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Map<String, b> a(e context, com.google.gson.b.a<?> type, Class<?> raw) {
        Map<String, b> result = new LinkedHashMap();
        if (!raw.isInterface()) {
            Type declaredType = type.b();
            while (raw != Object.class) {
                Field[] declaredFields = raw.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        break;
                    }
                    final Field field = declaredFields[i2];
                    boolean serialize = a(field, true);
                    boolean deserialize = a(field, false);
                    if (serialize || deserialize) {
                        field.setAccessible(true);
                        Type fieldType = com.google.gson.a.b.a(type.b(), (Class) raw, field.getGenericType());
                        List<String> fieldNames = a(field);
                        b previous = null;
                        for (int i3 = 0; i3 < fieldNames.size(); i3++) {
                            String name = (String) fieldNames.get(i3);
                            if (i3 != 0) {
                                serialize = false;
                            }
                            final com.google.gson.b.a a = com.google.gson.b.a.a(fieldType);
                            final boolean a2 = com.google.gson.a.i.a(a.a());
                            JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
                            r rVar = null;
                            if (jsonAdapter != null) {
                                rVar = d.a(this.a, context, a, jsonAdapter);
                            }
                            final boolean z = rVar != null;
                            if (rVar == null) {
                                rVar = context.a(a);
                            }
                            final e eVar = context;
                            b replaced = (b) result.put(name, new b(this, name, serialize, deserialize) {
                                final /* synthetic */ i g;

                                final void a(com.google.gson.c.c writer, Object value) throws IOException, IllegalAccessException {
                                    r rVar;
                                    Object fieldValue = field.get(value);
                                    if (z) {
                                        rVar = rVar;
                                    } else {
                                        rVar = new m(eVar, rVar, a.b());
                                    }
                                    rVar.a(writer, fieldValue);
                                }

                                final void a(com.google.gson.c.a reader, Object value) throws IOException, IllegalAccessException {
                                    Object fieldValue = rVar.a(reader);
                                    if (fieldValue != null || !a2) {
                                        field.set(value, fieldValue);
                                    }
                                }

                                public final boolean a(Object value) throws IOException, IllegalAccessException {
                                    if (this.i && field.get(value) != value) {
                                        return true;
                                    }
                                    return false;
                                }
                            });
                            if (previous == null) {
                                previous = replaced;
                            }
                        }
                        if (previous != null) {
                            throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous.h);
                        }
                    }
                    i = i2 + 1;
                }
            }
        }
        return result;
    }
}
