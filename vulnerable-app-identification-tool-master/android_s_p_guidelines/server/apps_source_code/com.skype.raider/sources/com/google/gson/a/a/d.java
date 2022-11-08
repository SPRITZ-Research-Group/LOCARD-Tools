package com.google.gson.a.a;

import com.google.gson.a.c;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.h;
import com.google.gson.o;
import com.google.gson.r;
import com.google.gson.s;

public final class d implements s {
    private final c a;

    public d(c constructorConstructor) {
        this.a = constructorConstructor;
    }

    public final <T> r<T> a(e gson, a<T> targetType) {
        JsonAdapter annotation = (JsonAdapter) targetType.a().getAnnotation(JsonAdapter.class);
        if (annotation == null) {
            return null;
        }
        return a(this.a, gson, targetType, annotation);
    }

    static r<?> a(c constructorConstructor, e gson, a<?> type, JsonAdapter annotation) {
        r<?> typeAdapter;
        Object instance = constructorConstructor.a(a.a(annotation.value())).a();
        if (instance instanceof r) {
            typeAdapter = (r) instance;
        } else if (instance instanceof s) {
            typeAdapter = ((s) instance).a(gson, type);
        } else if ((instance instanceof o) || (instance instanceof h)) {
            o<?> serializer;
            h<?> deserializer;
            if (instance instanceof o) {
                serializer = (o) instance;
            } else {
                serializer = null;
            }
            if (instance instanceof h) {
                deserializer = (h) instance;
            } else {
                deserializer = null;
            }
            typeAdapter = new l(serializer, deserializer, gson, type);
        } else {
            throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
        }
        if (typeAdapter != null) {
            return typeAdapter.a();
        }
        return typeAdapter;
    }
}
