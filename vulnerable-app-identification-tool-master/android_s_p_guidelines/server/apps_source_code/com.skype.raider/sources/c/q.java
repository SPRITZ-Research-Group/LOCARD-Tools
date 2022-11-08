package c;

import javax.annotation.Nullable;

final class q {
    @Nullable
    static p a;
    static long b;

    private q() {
    }

    static p a() {
        synchronized (q.class) {
            if (a != null) {
                p result = a;
                a = result.f;
                result.f = null;
                b -= 8192;
                return result;
            }
            return new p();
        }
    }

    static void a(p segment) {
        if (segment.f != null || segment.g != null) {
            throw new IllegalArgumentException();
        } else if (!segment.d) {
            synchronized (q.class) {
                if (b + 8192 > 65536) {
                    return;
                }
                b += 8192;
                segment.f = a;
                segment.c = 0;
                segment.b = 0;
                a = segment;
            }
        }
    }
}
