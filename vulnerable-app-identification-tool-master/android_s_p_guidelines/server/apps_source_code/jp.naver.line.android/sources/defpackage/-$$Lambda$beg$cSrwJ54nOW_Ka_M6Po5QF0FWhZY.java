package defpackage;

import java.util.concurrent.ThreadFactory;

/* compiled from: lambda */
/* renamed from: -$$Lambda$beg$cSrwJ54nOW_Ka_M6Po5QF0FWhZY */
public final /* synthetic */ class -$$Lambda$beg$cSrwJ54nOW_Ka_M6Po5QF0FWhZY implements ThreadFactory {
    private final /* synthetic */ String f$0;

    public /* synthetic */ -$$Lambda$beg$cSrwJ54nOW_Ka_M6Po5QF0FWhZY(String str) {
        this.f$0 = str;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f$0);
    }
}
