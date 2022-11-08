package androidx.work;

import androidx.work.impl.j;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class ae {
    public abstract u a(String str);

    public abstract u a(String str, j jVar, List<s> list);

    public abstract u a(UUID uuid);

    public static ae a() {
        ae b = j.b();
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public final u a(String str, j jVar, s sVar) {
        return a(str, jVar, Collections.singletonList(sVar));
    }

    protected ae() {
    }
}
