package com.applovin.impl.adview;

import android.os.Handler;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class am {
    private final AppLovinLogger a;
    private final Handler b;
    private final Set<ap> c = new HashSet();
    private final AtomicInteger d = new AtomicInteger();

    public am(Handler handler, AppLovinSdk appLovinSdk) {
        if (handler == null) {
            throw new IllegalArgumentException("No handler specified.");
        } else if (appLovinSdk != null) {
            this.b = handler;
            this.a = appLovinSdk.getLogger();
        } else {
            throw new IllegalArgumentException("No sdk specified.");
        }
    }

    private void a(ap apVar, int i) {
        this.b.postDelayed(new an(this, apVar, i), apVar.b());
    }

    public final void a() {
        Set<ap> hashSet = new HashSet(this.c);
        StringBuilder stringBuilder = new StringBuilder("Starting ");
        stringBuilder.append(hashSet.size());
        stringBuilder.append(" countdowns...");
        this.a.d("CountdownManager", stringBuilder.toString());
        int incrementAndGet = this.d.incrementAndGet();
        for (ap apVar : hashSet) {
            StringBuilder stringBuilder2 = new StringBuilder("Starting countdown: ");
            stringBuilder2.append(apVar.a());
            stringBuilder2.append(" for generation ");
            stringBuilder2.append(incrementAndGet);
            stringBuilder2.append("...");
            this.a.d("CountdownManager", stringBuilder2.toString());
            a(apVar, incrementAndGet);
        }
    }

    public final void a(String str, long j, ao aoVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid step specified.");
        } else if (this.b != null) {
            this.a.d("CountdownManager", "Adding countdown: ".concat(String.valueOf(str)));
            this.c.add(new ap(str, j, aoVar, null));
        } else {
            throw new IllegalArgumentException("No handler specified.");
        }
    }

    public final void b() {
        this.a.d("CountdownManager", "Removing all countdowns...");
        c();
        this.c.clear();
    }

    public final void c() {
        this.a.d("CountdownManager", "Stopping countdowns...");
        this.d.incrementAndGet();
        this.b.removeCallbacksAndMessages(null);
    }
}
