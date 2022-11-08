package a.a;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public final class u extends ThreadPoolExecutor {
    private static final TimeUnit a = TimeUnit.MILLISECONDS;

    public u(ThreadFactory threadFactory) {
        super(1, 1, 0, a, cz.d(), threadFactory);
        setRejectedExecutionHandler(new DiscardOldestPolicy());
    }
}
