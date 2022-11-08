package com.google.gson.a.a;

import com.google.gson.b.a;
import com.google.gson.c.b;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class j extends r<Date> {
    public static final s a = new s() {
        public final <T> r<T> a(e gson, a<T> typeToken) {
            return typeToken.a() == Date.class ? new j() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
        return b(aVar);
    }

    private synchronized Date b(com.google.gson.c.a in) throws IOException {
        Date date;
        if (in.f() == b.NULL) {
            in.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(in.h()).getTime());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
        return date;
    }

    private synchronized void a(c out, Date value) throws IOException {
        out.b(value == null ? null : this.b.format(value));
    }
}
