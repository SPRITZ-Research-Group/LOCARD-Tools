package com.google.gson.a.a;

import com.google.gson.b.a;
import com.google.gson.c.b;
import com.google.gson.e;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class c extends r<Date> {
    public static final s a = new s() {
        public final <T> r<T> a(e gson, a<T> typeToken) {
            return typeToken.a() == Date.class ? new c() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    private synchronized Date a(String json) {
        Date parse;
        try {
            parse = this.c.parse(json);
        } catch (ParseException e) {
            try {
                parse = this.b.parse(json);
            } catch (ParseException e2) {
                try {
                    parse = com.google.gson.a.a.a.a.a(json, new ParsePosition(0));
                } catch (ParseException e3) {
                    throw new p(json, e3);
                }
            }
        }
        return parse;
    }

    private synchronized void a(com.google.gson.c.c out, Date value) throws IOException {
        if (value == null) {
            out.f();
        } else {
            out.b(this.b.format(value));
        }
    }

    public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
        if (aVar.f() != b.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }
}
