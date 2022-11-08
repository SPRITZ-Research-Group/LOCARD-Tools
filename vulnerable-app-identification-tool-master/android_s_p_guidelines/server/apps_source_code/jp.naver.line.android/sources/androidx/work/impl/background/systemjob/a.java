package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.d;
import androidx.work.p;
import defpackage.po;

final class a {
    private static final String a = p.a("SystemJobInfoConverter");
    private final ComponentName b;

    a(Context context) {
        this.b = new ComponentName(context.getApplicationContext(), SystemJobService.class);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final JobInfo a(po poVar, int i) {
        d dVar = poVar.j;
        int i2 = 1;
        int i3;
        switch (dVar.a()) {
            case NOT_REQUIRED:
                i3 = 0;
                break;
            case CONNECTED:
                break;
            case UNMETERED:
                i3 = 2;
                break;
            case NOT_ROAMING:
                if (VERSION.SDK_INT >= 24) {
                    i3 = 3;
                    break;
                }
                break;
            case METERED:
                if (VERSION.SDK_INT >= 26) {
                    i3 = 4;
                    break;
                }
                break;
        }
    }
}
