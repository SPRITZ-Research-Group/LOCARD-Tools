package com.facebook.imagepipeline.producers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Consumer<T> {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    void b();

    void b(float f);

    void b(T t, int i);

    void b(Throwable th);
}
