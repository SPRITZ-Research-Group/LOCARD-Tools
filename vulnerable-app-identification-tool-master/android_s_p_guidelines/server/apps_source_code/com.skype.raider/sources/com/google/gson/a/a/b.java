package com.google.gson.a.a;

import com.google.gson.a.c;
import com.google.gson.a.h;
import com.google.gson.e;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class b implements s {
    private final c a;

    private static final class a<E> extends r<Collection<E>> {
        private final r<E> a;
        private final h<? extends Collection<E>> b;

        public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
            Collection<Object> collection = (Collection) obj;
            if (collection == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (Object a : collection) {
                this.a.a(cVar, a);
            }
            cVar.c();
        }

        public a(e context, Type elementType, r<E> elementTypeAdapter, h<? extends Collection<E>> constructor) {
            this.a = new m(context, elementTypeAdapter, elementType);
            this.b = constructor;
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == com.google.gson.c.b.NULL) {
                aVar.j();
                return null;
            }
            Collection collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.a(aVar));
            }
            aVar.b();
            return collection;
        }
    }

    public b(c constructorConstructor) {
        this.a = constructorConstructor;
    }

    public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
        Type type = typeToken.b();
        Class rawType = typeToken.a();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type elementType = com.google.gson.a.b.a(type, rawType);
        return new a(gson, elementType, gson.a(com.google.gson.b.a.a(elementType)), this.a.a((com.google.gson.b.a) typeToken));
    }
}
