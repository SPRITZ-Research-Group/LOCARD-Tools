package a.a;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class cz {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b = Math.max(2, Math.min(a - 1, 4));
    private static final int c = ((a * 2) + 1);

    public static int a() {
        return b;
    }

    public static int b() {
        return c;
    }

    public static long c() {
        return 1;
    }

    public static BlockingQueue<Runnable> d() {
        return new LinkedBlockingQueue(128);
    }
}
