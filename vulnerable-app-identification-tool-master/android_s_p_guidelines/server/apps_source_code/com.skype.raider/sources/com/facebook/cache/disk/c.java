package com.facebook.cache.disk;

import com.facebook.cache.a.i;
import java.io.IOException;
import java.util.Collection;

public interface c {

    public interface a {
        String a();

        long b();

        long d();
    }

    public interface b {
        com.facebook.binaryresource.a a() throws IOException;

        void a(i iVar) throws IOException;

        boolean b();
    }

    long a(a aVar) throws IOException;

    b a(String str, Object obj) throws IOException;

    boolean a();

    long b(String str) throws IOException;

    com.facebook.binaryresource.a b(String str, Object obj) throws IOException;

    void b();

    void c() throws IOException;

    boolean c(String str, Object obj) throws IOException;

    Collection<a> d() throws IOException;
}
