package com.google.gson;

import com.google.gson.a.a.f;
import com.google.gson.c.a;
import com.google.gson.c.b;
import com.google.gson.c.c;
import java.io.IOException;

public abstract class r<T> {
    public abstract T a(a aVar) throws IOException;

    public abstract void a(c cVar, T t) throws IOException;

    public final r<T> a() {
        return new r<T>(this) {
            final /* synthetic */ r a;

            {
                this.a = this$0;
            }

            public final void a(c out, T value) throws IOException {
                if (value == null) {
                    out.f();
                } else {
                    this.a.a(out, value);
                }
            }

            public final T a(a reader) throws IOException {
                if (reader.f() != b.NULL) {
                    return this.a.a(reader);
                }
                reader.j();
                return null;
            }
        };
    }

    public final i a(T value) {
        try {
            f jsonWriter = new f();
            a(jsonWriter, value);
            return jsonWriter.a();
        } catch (Throwable e) {
            throw new j(e);
        }
    }
}
