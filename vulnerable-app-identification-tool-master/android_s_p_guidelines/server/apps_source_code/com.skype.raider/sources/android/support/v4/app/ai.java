package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;

public final class ai implements Iterable<Intent> {
    private static final c a;
    private final ArrayList<Intent> b = new ArrayList();
    private final Context c;

    public interface a {
        Intent a();
    }

    static class c {
        c() {
        }
    }

    @RequiresApi(16)
    static class b extends c {
        b() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else {
            a = new c();
        }
    }

    private ai(Context a) {
        this.c = a;
    }

    public static ai a(Context context) {
        return new ai(context);
    }

    public final ai a(Intent nextIntent) {
        this.b.add(nextIntent);
        return this;
    }

    public final ai a(Activity sourceActivity) {
        Intent parent = null;
        if (sourceActivity instanceof a) {
            parent = ((a) sourceActivity).a();
        }
        if (parent == null) {
            parent = r.a(sourceActivity);
        }
        if (parent != null) {
            ComponentName target = parent.getComponent();
            if (target == null) {
                target = parent.resolveActivity(this.c.getPackageManager());
            }
            a(target);
            a(parent);
        }
        return this;
    }

    private ai a(ComponentName sourceActivityName) {
        int insertAt = this.b.size();
        try {
            Intent parent = r.a(this.c, sourceActivityName);
            while (parent != null) {
                this.b.add(insertAt, parent);
                parent = r.a(this.c, parent.getComponent());
            }
            return this;
        } catch (NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public final Iterator<Intent> iterator() {
        return this.b.iterator();
    }

    public final void a() {
        a(null);
    }

    public final void a(Bundle options) {
        if (this.b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intents = (Intent[]) this.b.toArray(new Intent[this.b.size()]);
        intents[0] = new Intent(intents[0]).addFlags(268484608);
        android.support.v4.content.a.a(this.c, intents, options);
    }
}
