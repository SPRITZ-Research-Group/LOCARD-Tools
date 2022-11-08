package com.applovin.impl.sdk;

import java.util.TimerTask;

class gb extends TimerTask {
    final /* synthetic */ ga a;

    gb(ga gaVar) {
        this.a = gaVar;
    }

    public void run() {
        try {
            this.a.e.run();
            synchronized (this.a.g) {
                this.a.b = null;
            }
        } catch (Throwable th) {
            try {
                if (this.a.a != null) {
                    this.a.a.getLogger().e("Timer", "Encountered error while executing timed task", th);
                }
                synchronized (this.a.g) {
                    this.a.b = null;
                }
            } catch (Throwable th2) {
                synchronized (this.a.g) {
                    this.a.b = null;
                }
            }
        }
    }
}
