package jp.naver.line.android.util;

import java.util.concurrent.ThreadFactory;

public final class bu implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable);
    }
}
