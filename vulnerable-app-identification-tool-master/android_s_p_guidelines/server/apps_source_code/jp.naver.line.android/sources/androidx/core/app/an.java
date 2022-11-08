package androidx.core.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.os.Bundle;
import java.util.Set;

public final class an {
    private final String a;
    private final CharSequence b;
    private final CharSequence[] c;
    private final boolean d;
    private final Bundle e;
    private final Set<String> f;

    an(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, Bundle bundle, Set<String> set) {
        this.a = str;
        this.b = charSequence;
        this.c = charSequenceArr;
        this.d = z;
        this.e = bundle;
        this.f = set;
    }

    public final String a() {
        return this.a;
    }

    public final CharSequence b() {
        return this.b;
    }

    public final CharSequence[] c() {
        return this.c;
    }

    public final Set<String> d() {
        return this.f;
    }

    public final boolean e() {
        return this.d;
    }

    public final Bundle f() {
        return this.e;
    }

    static RemoteInput[] a(an[] anVarArr) {
        if (anVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[anVarArr.length];
        for (int i = 0; i < anVarArr.length; i++) {
            an anVar = anVarArr[i];
            remoteInputArr[i] = new Builder(anVar.a).setLabel(anVar.b).setChoices(anVar.c).setAllowFreeFormInput(anVar.d).addExtras(anVar.e).build();
        }
        return remoteInputArr;
    }
}
