package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;

class fh implements Runnable {
    final /* synthetic */ fd a;
    private final String b;
    private final dx c;
    private final fe d;

    fh(fd fdVar, dx dxVar, fe feVar) {
        this.a = fdVar;
        this.b = dxVar.a();
        this.c = dxVar;
        this.d = feVar;
    }

    public void run() {
        AppLovinLogger b;
        String str;
        StringBuilder stringBuilder;
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2;
        StringBuilder stringBuilder2;
        try {
            ab.a();
            if (!this.a.b.e() || this.c.g) {
                this.a.c.i(this.b, "Task started execution...");
                this.c.run();
                currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                AppLovinLogger b2 = this.a.c;
                String str2 = this.b;
                StringBuilder stringBuilder3 = new StringBuilder("Task executed successfully in ");
                stringBuilder3.append(currentTimeMillis2);
                stringBuilder3.append("ms.");
                b2.i(str2, stringBuilder3.toString());
                aw a = this.a.b.a();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.b);
                stringBuilder2.append("_count");
                a.a(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.b);
                stringBuilder2.append("_time");
                a.a(stringBuilder2.toString(), currentTimeMillis2);
            } else {
                this.a.c.i(this.b, "Task re-scheduled...");
                this.a.a(this.c, this.d, 2000);
            }
            currentTimeMillis = this.a.a(this.d) - 1;
            b = this.a.c;
            str = "TaskManager";
            stringBuilder = new StringBuilder();
        } catch (Throwable th) {
            currentTimeMillis2 = this.a.a(this.d) - 1;
            AppLovinLogger b3 = this.a.c;
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(this.d);
            stringBuilder4.append(" queue finished task ");
            stringBuilder4.append(this.c.a());
            stringBuilder4.append(" with queue size ");
            stringBuilder4.append(currentTimeMillis2);
            b3.i("TaskManager", stringBuilder4.toString());
        }
        stringBuilder.append(this.d);
        stringBuilder.append(" queue finished task ");
        stringBuilder.append(this.c.a());
        stringBuilder.append(" with queue size ");
        stringBuilder.append(currentTimeMillis);
        b.i(str, stringBuilder.toString());
    }
}
