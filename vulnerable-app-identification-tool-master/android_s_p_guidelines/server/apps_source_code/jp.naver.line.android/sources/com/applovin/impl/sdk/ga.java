package com.applovin.impl.sdk;

import com.google.obf.ly;
import java.util.Timer;
import java.util.TimerTask;

public class ga {
    private final AppLovinSdkImpl a;
    private Timer b;
    private long c;
    private long d;
    private final Runnable e;
    private long f;
    private final Object g = new Object();

    private ga(AppLovinSdkImpl appLovinSdkImpl, Runnable runnable) {
        this.a = appLovinSdkImpl;
        this.e = runnable;
    }

    public static ga a(long j, AppLovinSdkImpl appLovinSdkImpl, Runnable runnable) {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder("Cannot create a scheduled timer. Invalid fire time passed in: ");
            stringBuilder.append(j);
            stringBuilder.append(ly.a);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (runnable != null) {
            ga gaVar = new ga(appLovinSdkImpl, runnable);
            gaVar.c = System.currentTimeMillis();
            gaVar.d = j;
            gaVar.b = new Timer();
            gaVar.b.schedule(gaVar.c(), j);
            return gaVar;
        } else {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Runnable is null.");
        }
    }

    private TimerTask c() {
        return new gb(this);
    }

    public void a() {
        synchronized (this.g) {
            if (this.b != null) {
                try {
                    this.b.cancel();
                    this.f = System.currentTimeMillis() - this.c;
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.getLogger().e("Timer", "Encountered error while pausing timer", th);
                        }
                    } catch (Throwable th2) {
                        this.b = null;
                    }
                }
                this.b = null;
            }
        }
    }

    public void b() {
        synchronized (this.g) {
            if (this.f > 0) {
                try {
                    this.d -= this.f;
                    if (this.d < 0) {
                        this.d = 0;
                    }
                    this.b = new Timer();
                    this.b.schedule(c(), this.d);
                    this.c = System.currentTimeMillis();
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.getLogger().e("Timer", "Encountered error while resuming timer", th);
                        }
                    } catch (Throwable th2) {
                        this.f = 0;
                    }
                }
                this.f = 0;
            }
        }
    }
}
