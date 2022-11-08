package com.google.gson.a;

import com.google.gson.a.a.n;
import com.google.gson.c.a;
import com.google.gson.c.c;
import com.google.gson.i;
import com.google.gson.k;
import com.google.gson.m;
import com.google.gson.p;
import java.io.IOException;

public final class j {
    public static i a(a reader) throws m {
        boolean isEmpty = true;
        try {
            reader.f();
            isEmpty = false;
            return (i) n.X.a(reader);
        } catch (Throwable e) {
            if (isEmpty) {
                return k.a;
            }
            throw new p(e);
        } catch (Throwable e2) {
            throw new p(e2);
        } catch (Throwable e22) {
            throw new com.google.gson.j(e22);
        } catch (Throwable e222) {
            throw new p(e222);
        }
    }

    public static void a(i element, c writer) throws IOException {
        n.X.a(writer, element);
    }
}
