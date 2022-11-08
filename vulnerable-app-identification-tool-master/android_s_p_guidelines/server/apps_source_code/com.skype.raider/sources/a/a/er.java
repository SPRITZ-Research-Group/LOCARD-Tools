package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import com.adjust.sdk.Constants;
import com.appboy.a.a;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public class er implements en {
    private static final String b = c.a(er.class);
    public final Object a = new Object();
    private final Context c;
    private final am d;
    private final b e;
    private final long f;
    private final SharedPreferences g;
    private final em h;
    private final ep i;
    private Map<String, dc> j;
    private volatile long k = 0;

    public er(Context context, am amVar, ThreadPoolExecutor threadPoolExecutor, b bVar, a aVar, String str, String str2) {
        this.c = context.getApplicationContext();
        this.d = amVar;
        this.e = bVar;
        this.f = aVar.k();
        this.g = context.getSharedPreferences("com.appboy.storage.triggers.actions" + i.a(context, str, str2), 0);
        this.h = new eq(context, threadPoolExecutor, str2);
        this.i = new es(context, str, str2);
        this.j = a();
    }

    public final void a(List<dc> list) {
        Object obj = null;
        ed ejVar = new ej();
        if (list == null) {
            c.f(b, "Received a null list of triggers in registerTriggeredActions(). Doing nothing.");
            return;
        }
        synchronized (this.a) {
            this.j.clear();
            Editor edit = this.g.edit();
            edit.clear();
            c.b(b, "Registering " + list.size() + " new triggered actions.");
            for (dc dcVar : list) {
                Object obj2;
                c.b(b, "Registering triggered action id " + dcVar.b());
                this.j.put(dcVar.b(), dcVar);
                edit.putString(dcVar.b(), ((JSONObject) dcVar.h()).toString());
                if (dcVar.a(ejVar)) {
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            edit.apply();
        }
        this.i.a(list);
        this.h.a(list);
        if (obj != null) {
            c.d(b, "Test triggered actions found, triggering test event.");
            a(ejVar);
            return;
        }
        c.b(b, "No test triggered actions found.");
    }

    public final void a(ed edVar) {
        c.b(b, "New incoming <" + edVar.b() + ">. Searching for matching triggers.");
        final dc b = b(edVar);
        if (b != null) {
            long e;
            b.a(this.h.a(b));
            if (b.c().e() != -1) {
                e = ((long) b.c().e()) + edVar.d();
            } else {
                e = -1;
            }
            final ed edVar2 = edVar;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ er d;

                public final void run() {
                    dc dcVar = b;
                    this.d.c;
                    dcVar.a(this.d.e, edVar2, e);
                }
            }, (long) (b.c().d() * Constants.ONE_SECOND));
            this.i.a(b, edVar.c());
        }
    }

    private dc b(ed edVar) {
        synchronized (this.a) {
            Object obj;
            long a = co.a() - this.k;
            if (edVar instanceof ej) {
                c.b(b, "Ignoring minimum time interval between triggered actions because the trigger event is a test.");
                obj = 1;
            } else {
                c.d(b, a + " seconds have passed since the last trigger action (minimum interval: " + this.f + ").");
                if (a >= this.f) {
                    int obj2 = 1;
                } else {
                    obj2 = null;
                }
            }
            int i = Integer.MIN_VALUE;
            dc dcVar = null;
            for (dc dcVar2 : this.j.values()) {
                dc dcVar3;
                int c;
                if (dcVar2.a(edVar) && this.i.a(dcVar2)) {
                    c.b(b, "Found potential triggered action for incoming trigger event. Action id " + dcVar2.b() + ".");
                    dw c2 = dcVar2.c();
                    if (c2.c() > i) {
                        dcVar3 = dcVar2;
                        c = c2.c();
                        dcVar = dcVar3;
                        i = c;
                    }
                }
                c = i;
                dcVar3 = dcVar;
                dcVar = dcVar3;
                i = c;
            }
            if (dcVar != null) {
                c.b(b, "Found best triggered action for incoming trigger event " + (edVar.e() != null ? cv.a((JSONObject) edVar.e().h()) : "") + ". Matched Action id: " + dcVar.b() + ".");
                if (obj2 != null) {
                    c.d(b, "Minimum time interval requirement met for matched trigger.");
                } else if (dcVar.c().g() < 0 || ((long) dcVar.c().g()) > a) {
                    c.d(b, "Minimum time interval requirement and triggered action override time interval requirement of " + dcVar.c().g() + " not met for matched trigger. Returning null.");
                    return null;
                } else {
                    c.d(b, "Triggered action override time interval requirement met: " + dcVar.c().g());
                }
                this.k = edVar.c();
                return dcVar;
            }
            c.b(b, "Failed to match triggered action for incoming <" + edVar.b() + ">.");
            return null;
        }
    }

    private Map<String, dc> a() {
        Map<String, dc> hashMap = new HashMap();
        Map all = this.g.getAll();
        if (all == null || all.size() == 0) {
            return hashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return hashMap;
        }
        try {
            for (String str : keySet) {
                String string = this.g.getString(str, null);
                if (i.c(string)) {
                    c.f(b, "Received null or blank serialized triggered action string for action id " + str + " from shared preferences. Not parsing.");
                } else {
                    dc b = eu.b(new JSONObject(string), this.d);
                    if (b != null) {
                        hashMap.put(b.b(), b);
                        c.b(b, "Retrieving templated triggered action id " + b.b() + " from local storage.");
                    }
                }
            }
        } catch (Throwable e) {
            c.d(b, "Encountered Json exception while parsing stored triggered actions.", e);
        } catch (Throwable e2) {
            c.d(b, "Encountered unexpected exception while parsing stored triggered actions.", e2);
        }
        return hashMap;
    }
}
