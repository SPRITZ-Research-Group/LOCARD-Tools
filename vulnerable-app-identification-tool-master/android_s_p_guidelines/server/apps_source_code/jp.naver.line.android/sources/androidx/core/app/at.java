package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.a;
import java.util.ArrayList;
import java.util.Iterator;

public final class at implements Iterable<Intent> {
    private final ArrayList<Intent> a = new ArrayList();
    private final Context b;

    private at(Context context) {
        this.b = context;
    }

    public static at a(Context context) {
        return new at(context);
    }

    public final at a(Activity activity) {
        Intent supportParentActivityIntent = ((au) activity).getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = u.a(activity);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(this.b.getPackageManager());
            }
            a(component);
            this.a.add(supportParentActivityIntent);
        }
        return this;
    }

    private at a(ComponentName componentName) {
        int size = this.a.size();
        try {
            Intent a = u.a(this.b, componentName);
            while (a != null) {
                this.a.add(size, a);
                a = u.a(this.b, a.getComponent());
            }
            return this;
        } catch (Throwable e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public final Iterator<Intent> iterator() {
        return this.a.iterator();
    }

    public final void a() {
        if (this.a.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.a.toArray(new Intent[this.a.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        a.a(this.b, intentArr);
    }
}
