package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

public class ck {
    private static final String a = c.a(ck.class);
    private final SharedPreferences b;
    private final Object c = new Object();
    private AtomicBoolean d = new AtomicBoolean(false);
    private ba e;

    class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ck a;

        private a(ck ckVar) {
            this.a = ckVar;
        }

        /* synthetic */ a(ck ckVar, byte b) {
            this(ckVar);
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        private Void a() {
            ba baVar = new ba();
            baVar.b(this.a.m());
            baVar.a(this.a.l());
            baVar.c(this.a.n());
            baVar.a(this.a.k());
            baVar.c(this.a.i());
            baVar.a(this.a.d());
            baVar.b(this.a.c());
            baVar.b(this.a.h());
            baVar.a(this.a.j());
            baVar.a(this.a.e());
            baVar.b(this.a.f());
            baVar.c(this.a.g());
            baVar.c(this.a.b());
            baVar.d(this.a.a());
            baVar.e(this.a.o());
            synchronized (this.a.c) {
                this.a.e = baVar;
            }
            return null;
        }
    }

    public ck(Context context, String str) {
        String str2;
        if (str == null) {
            c.g(a, "ServerConfigStorageProvider received null api key.");
            str2 = "";
        } else {
            str2 = "." + str;
        }
        this.b = context.getSharedPreferences("com.appboy.storage.serverconfigstorageprovider" + str2, 0);
        new a().execute(new Void[0]);
    }

    public final void a(ba baVar) {
        synchronized (this.c) {
            this.e = baVar;
        }
        try {
            Editor edit = this.b.edit();
            if (baVar.b() != null) {
                edit.putString("blacklisted_events", new JSONArray(baVar.b()).toString());
            }
            if (baVar.c() != null) {
                edit.putString("blacklisted_attributes", new JSONArray(baVar.c()).toString());
            }
            if (baVar.d() != null) {
                edit.putString("blacklisted_purchases", new JSONArray(baVar.d()).toString());
            }
            edit.putLong("config_time", baVar.a());
            edit.putBoolean("location_enabled", baVar.f());
            edit.putBoolean("location_enabled_set", baVar.g());
            edit.putLong("location_time", baVar.h());
            edit.putFloat("location_distance", baVar.i());
            edit.putInt("geofences_min_time_since_last_request", baVar.j());
            edit.putInt("geofences_min_time_since_last_report", baVar.k());
            edit.putInt("geofences_max_num_to_register", baVar.l());
            edit.putBoolean("geofences_enabled", baVar.m());
            edit.putBoolean("geofences_enabled_set", baVar.n());
            edit.putLong("messaging_session_timeout", baVar.e());
            edit.putBoolean("test_user_device_logging_enabled", baVar.o());
            edit.apply();
        } catch (Throwable e) {
            c.c(a, "Could not persist server config to shared preferences.", e);
        }
    }

    public final boolean a() {
        boolean n;
        synchronized (this.c) {
            if (this.e != null) {
                n = this.e.n();
            } else {
                n = this.b.getBoolean("geofences_enabled_set", false);
            }
        }
        return n;
    }

    public final boolean b() {
        boolean m;
        synchronized (this.c) {
            if (this.e != null) {
                m = this.e.m();
            } else {
                m = this.b.getBoolean("geofences_enabled", false);
            }
        }
        return m;
    }

    public final boolean c() {
        boolean f;
        synchronized (this.c) {
            if (this.e != null) {
                f = this.e.f();
            } else {
                f = this.b.getBoolean("location_enabled_set", false);
            }
        }
        return f;
    }

    public final boolean d() {
        boolean f;
        synchronized (this.c) {
            if (this.e != null) {
                f = this.e.f();
            } else {
                f = this.b.getBoolean("location_enabled", false);
            }
        }
        return f;
    }

    public final int e() {
        int j;
        synchronized (this.c) {
            if (this.e != null) {
                j = this.e.j();
            } else {
                j = this.b.getInt("geofences_min_time_since_last_request", -1);
            }
        }
        return j;
    }

    public final int f() {
        int k;
        synchronized (this.c) {
            if (this.e != null) {
                k = this.e.k();
            } else {
                k = this.b.getInt("geofences_min_time_since_last_report", -1);
            }
        }
        return k;
    }

    public final int g() {
        int l;
        synchronized (this.c) {
            if (this.e != null) {
                l = this.e.l();
            } else {
                l = this.b.getInt("geofences_max_num_to_register", -1);
            }
        }
        return l;
    }

    public final long h() {
        long h;
        synchronized (this.c) {
            if (this.e != null) {
                h = this.e.h();
            } else {
                h = this.b.getLong("location_time", -1);
            }
        }
        return h;
    }

    public final long i() {
        long e;
        synchronized (this.c) {
            if (this.e != null) {
                e = this.e.e();
            } else {
                e = this.b.getLong("messaging_session_timeout", -1);
            }
        }
        return e;
    }

    public final float j() {
        float i;
        synchronized (this.c) {
            if (this.e != null) {
                i = this.e.i();
            } else {
                i = this.b.getFloat("location_distance", -1.0f);
            }
        }
        return i;
    }

    public final long k() {
        long a;
        synchronized (this.c) {
            if (this.e != null) {
                a = this.e.a();
            } else {
                a = this.b.getLong("config_time", 0);
            }
        }
        return a;
    }

    public final Set<String> l() {
        Set<String> b;
        synchronized (this.c) {
            if (this.e != null) {
                b = this.e.b();
            } else {
                b = a("blacklisted_events");
            }
            if (b != null) {
            } else {
                b = new HashSet();
            }
        }
        return b;
    }

    public final Set<String> m() {
        Set<String> c;
        synchronized (this.c) {
            if (this.e != null) {
                c = this.e.c();
            } else {
                c = a("blacklisted_attributes");
            }
            if (c != null) {
            } else {
                c = new HashSet();
            }
        }
        return c;
    }

    public final Set<String> n() {
        Set<String> d;
        synchronized (this.c) {
            if (this.e != null) {
                d = this.e.d();
            } else {
                d = a("blacklisted_purchases");
            }
            if (d != null) {
            } else {
                d = new HashSet();
            }
        }
        return d;
    }

    public final boolean o() {
        boolean o;
        synchronized (this.c) {
            if (this.e != null) {
                o = this.e.o();
            } else {
                o = this.b.getBoolean("test_user_device_logging_enabled", false);
            }
        }
        return o;
    }

    public final boolean p() {
        return this.d.get();
    }

    public final void a(boolean z) {
        this.d.set(z);
    }

    private Set<String> a(String str) {
        try {
            String string = this.b.getString(str, "");
            if (i.c(string)) {
                return null;
            }
            JSONArray jSONArray = new JSONArray(string);
            Set<String> hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
            return hashSet;
        } catch (Throwable e) {
            c.c(a, "Experienced exception retrieving blacklisted strings from local storage. Returning null.", e);
            return null;
        }
    }
}
