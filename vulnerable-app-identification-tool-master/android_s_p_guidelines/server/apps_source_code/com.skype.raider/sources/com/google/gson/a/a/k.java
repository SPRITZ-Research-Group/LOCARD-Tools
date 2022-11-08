package com.google.gson.a.a;

import com.google.gson.b.a;
import com.google.gson.c.b;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class k extends r<Time> {
    public static final s a = new s() {
        public final <T> r<T> a(e gson, a<T> typeToken) {
            return typeToken.a() == Time.class ? new k() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
        return b(aVar);
    }

    private synchronized Time b(com.google.gson.c.a in) throws IOException {
        Time time;
        if (in.f() == b.NULL) {
            in.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(in.h()).getTime());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
        return time;
    }

    private synchronized void a(c out, Time value) throws IOException {
        out.b(value == null ? null : this.b.format(value));
    }
}
