package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.d;
import androidx.work.p;
import androidx.work.r;
import defpackage.po;
import java.util.List;

abstract class ConstraintProxy extends BroadcastReceiver {
    private static final String a = p.a("ConstraintProxy");

    public class BatteryChargingProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    }

    public class BatteryNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    }

    public class NetworkStateProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    }

    public class StorageNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
        }
    }

    ConstraintProxy() {
    }

    public void onReceive(Context context, Intent intent) {
        p.a();
        String.format("onReceive : %s", new Object[]{intent});
        Throwable[] thArr = new Throwable[0];
        context.startService(b.a(context));
    }

    static void a(Context context, List<po> list) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        for (po poVar : list) {
            d dVar = poVar.j;
            z |= dVar.d();
            z2 |= dVar.b();
            z3 |= dVar.e();
            z4 |= dVar.a() != r.NOT_REQUIRED ? 1 : 0;
            if (z && z2 && z3) {
                if (z4) {
                    break;
                }
            }
        }
        context.sendBroadcast(ConstraintProxyUpdateReceiver.a(context, z, z2, z3, z4));
    }
}
