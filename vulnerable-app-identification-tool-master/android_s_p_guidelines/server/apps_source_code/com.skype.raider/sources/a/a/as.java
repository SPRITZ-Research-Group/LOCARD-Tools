package a.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.adjust.sdk.Constants;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class as {
    private static final String a = c.a(as.class);
    private final List<String> b = new ArrayList(32);
    private long c;
    private boolean d = false;
    private final Object e = new Object();
    @Nullable
    private am f;

    public final void a(@NonNull String str, @Nullable String str2, @Nullable Throwable th) {
        if (!this.d) {
            return;
        }
        if (str2 == null || !(str2.contains("device_logs") || str2.contains("test_user_data"))) {
            int i;
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace == null || stackTrace.length == 0) {
                i = 1;
            } else {
                StackTraceElement stackTraceElement = stackTrace[1];
                String methodName = stackTraceElement.getMethodName();
                String className = stackTraceElement.getClassName();
                i = 0;
                for (StackTraceElement stackTraceElement2 : stackTrace) {
                    if (stackTraceElement2.getClassName().equals(className) && stackTraceElement2.getMethodName().equals(methodName)) {
                        i++;
                    }
                }
                i = i != 1 ? 1 : 0;
            }
            if (i == 0) {
                synchronized (this.e) {
                    Object obj;
                    if (this.b.size() >= 32) {
                        b();
                    }
                    if (this.b.isEmpty() || this.c == 0) {
                        this.c = co.a();
                    }
                    if (i.c(str)) {
                        obj = null;
                    } else if (i.c(str2) && (th == null || i.c(th.getMessage()))) {
                        obj = null;
                    } else {
                        String str3 = co.a(new Date(), fq.ANDROID_LOGCAT) + " " + str;
                        if (str2 != null) {
                            str3 = str3 + ": " + str2;
                        }
                        if (th != null) {
                            str3 = str3 + ": " + th.getMessage();
                        }
                        obj = str3.substring(0, Math.min(str3.length(), Constants.ONE_SECOND));
                    }
                    if (obj != null) {
                        this.b.add(obj);
                    }
                }
            }
        }
    }

    public final void a(boolean z) {
        synchronized (this.e) {
            if (z) {
                c.e(a, "Test user device logging is enabled.");
            } else {
                this.b.clear();
            }
        }
        this.d = z;
    }

    public final void a(am amVar) {
        this.f = amVar;
    }

    public final void a(ba baVar) {
        a(baVar.o());
    }

    public final boolean a() {
        return this.d;
    }

    @VisibleForTesting
    private void b() {
        synchronized (this.e) {
            if (this.f != null) {
                List arrayList = new ArrayList();
                for (String add : this.b) {
                    arrayList.add(add);
                }
                this.f.a(arrayList, this.c);
            }
            this.b.clear();
            this.c = 0;
        }
    }
}
