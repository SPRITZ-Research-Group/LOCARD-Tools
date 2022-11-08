package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.ai;
import com.facebook.internal.n$com.facebook.internal.o;
import com.facebook.s;
import java.util.List;

public abstract class n<CONTENT, RESULT> {
    protected static final Object a = new Object();
    private final Activity b;
    private final ad c;
    private List<o> d;
    private int e;

    protected abstract List<o> c();

    protected abstract a d();

    protected n(Activity activity, int i) {
        bn.a((Object) activity, "activity");
        this.b = activity;
        this.c = null;
        this.e = i;
    }

    protected n(ad adVar, int i) {
        bn.a((Object) adVar, "fragmentWrapper");
        this.c = adVar;
        this.b = null;
        this.e = i;
        if (adVar.c() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    public final int a() {
        return this.e;
    }

    public final boolean a(CONTENT content) {
        return a((Object) content, a);
    }

    protected boolean a(CONTENT content, Object obj) {
        Object obj2 = obj == a ? 1 : null;
        for (o oVar : e()) {
            if ((obj2 != null || bj.a(oVar.a(), obj)) && oVar.a(content, false)) {
                return true;
            }
        }
        return false;
    }

    public void b(CONTENT content) {
        b(content, a);
    }

    protected void b(CONTENT content, Object obj) {
        a c = c(content, obj);
        if (c == null) {
            String str = "No code path should ever result in a null appCall";
            Log.e("FacebookDialog", str);
            if (s.b()) {
                throw new IllegalStateException(str);
            }
        } else if (this.c != null) {
            this.c.a(c.a(), c.c());
            c.d();
        } else {
            this.b.startActivityForResult(c.a(), c.c());
            c.d();
        }
    }

    protected final Activity b() {
        if (this.b != null) {
            return this.b;
        }
        return this.c != null ? this.c.c() : null;
    }

    protected final void a(Intent intent, int i) {
        String str;
        if (this.b != null) {
            this.b.startActivityForResult(intent, i);
        } else {
            if (this.c == null) {
                str = "Failed to find Activity or Fragment to startActivityForResult ";
            } else if (this.c.a() != null) {
                this.c.a().startActivityForResult(intent, i);
            } else if (this.c.b() != null) {
                this.c.b().startActivityForResult(intent, i);
            } else {
                str = "Failed to find Activity or Fragment to startActivityForResult ";
            }
            if (str != null) {
                ar.a(ai.DEVELOPER_ERRORS, 6, getClass().getName(), str);
            }
        }
        str = null;
        if (str != null) {
            ar.a(ai.DEVELOPER_ERRORS, 6, getClass().getName(), str);
        }
    }

    private a c(CONTENT content, Object obj) {
        Object obj2 = obj == a ? 1 : null;
        a aVar = null;
        for (o oVar : e()) {
            if ((obj2 != null || bj.a(oVar.a(), obj)) && oVar.a(content, true)) {
                try {
                    aVar = oVar.a(content);
                    break;
                } catch (com.facebook.n e) {
                    aVar = d();
                    l.a(aVar, e);
                }
            }
        }
        if (aVar != null) {
            return aVar;
        }
        aVar = d();
        l.a(aVar, new com.facebook.n("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
        return aVar;
    }

    private List<o> e() {
        if (this.d == null) {
            this.d = c();
        }
        return this.d;
    }
}
