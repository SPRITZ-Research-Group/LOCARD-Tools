package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Process;
import com.appboy.f.a;
import com.appboy.f.c;
import com.appboy.f.f;
import com.appboy.f.i;
import com.appboy.f.k;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class eq implements em {
    private static final String a = c.a(eq.class);
    private final Context b;
    private final ThreadPoolExecutor c;
    private final SharedPreferences d;
    private Map<String, String> e;
    private Map<String, String> f = new HashMap();

    public eq(Context context, ThreadPoolExecutor threadPoolExecutor, String str) {
        this.b = context;
        this.c = threadPoolExecutor;
        this.d = context.getSharedPreferences("com.appboy.storage.triggers.local_assets." + str, 0);
        this.e = b();
    }

    public final void a(List<dc> list) {
        final Set hashSet = new HashSet();
        Set hashSet2 = new HashSet();
        for (dc dcVar : list) {
            et d = dcVar.d();
            if (!(d == null || i.c(d.b()))) {
                if (dcVar.a()) {
                    c.b(a, "Received new remote path for triggered action " + dcVar.b() + " at " + d.b() + ".");
                    hashSet.add(d);
                    hashSet2.add(d.b());
                } else {
                    c.b(a, "Pre-fetch off for triggered action " + dcVar.b() + ". Not pre-fetching assets at remote path " + d.b() + ".");
                }
            }
        }
        final Editor edit = this.d.edit();
        for (String str : new HashSet(this.e.keySet())) {
            if (this.f.containsKey(str)) {
                c.b(a, "Not removing local path for remote path " + str + " from cache because it is being preserved until the end of the app run.");
            } else if (!hashSet2.contains(str)) {
                String str2 = (String) this.e.get(str);
                c.b(a, "Removing obsolete local path " + str2 + " for obsolete remote path " + str + " from cache.");
                this.e.remove(str);
                edit.remove(str);
                a.a(new File(str2));
            }
        }
        edit.apply();
        try {
            File[] listFiles = c().listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    String path = file.getPath();
                    if (this.e.containsValue(path)) {
                        c.b(a, "Asset " + path + " is not obsolete. Not deleting.");
                    } else if (this.f.containsValue(path)) {
                        c.b(a, "Asset " + path + " is being preserved. Not deleting.");
                    } else {
                        c.b(a, "Deleting obsolete asset " + path + " from filesystem.");
                        a.a(file);
                    }
                }
            }
        } catch (Throwable e) {
            c.a(a, "Exception while deleting obsolete assets from filesystem.", e);
        }
        this.c.execute(new Runnable(this) {
            final /* synthetic */ eq c;

            public final void run() {
                Process.setThreadPriority(10);
                for (et etVar : hashSet) {
                    String b = etVar.b();
                    if (!this.c.e.containsKey(b)) {
                        try {
                            String a = this.c.a(etVar);
                            if (!i.c(a)) {
                                c.b(eq.a, "Adding new local path " + a + " for remote path " + b + " to cache.");
                                this.c.e.put(b, a);
                                edit.putString(b, a);
                            }
                        } catch (Throwable e) {
                            c.a(eq.a, "Failed to add new local path for remote path " + b + ".", e);
                        }
                    }
                }
                edit.apply();
            }
        });
    }

    public final String a(dc dcVar) {
        if (dcVar.a()) {
            et d = dcVar.d();
            if (d == null) {
                c.d(a, "Remote path was null or blank. Not retrieving local asset path.");
                return null;
            }
            String b = d.b();
            if (i.c(b)) {
                c.f(a, "Remote asset path string was null or blank. Not retrieving local asset path.");
                return null;
            } else if (this.e.containsKey(b)) {
                String str = (String) this.e.get(b);
                if (new File(str).exists()) {
                    c.d(a, "Retrieving local asset path for remote asset path: " + b);
                    this.f.put(b, str);
                    return str;
                }
                c.f(a, "Local asset for remote asset path did not exist: " + b);
                return null;
            } else {
                c.f(a, "No local asset path found for remote asset path: " + b);
                return null;
            }
        }
        c.b(a, "Prefetch turned off for this triggered action. Not retrieving local asset path.");
        return null;
    }

    public static void a(Context context) {
        File file = new File(context.getCacheDir(), "ab_triggers");
        c.a(a, "Deleting triggers directory at: " + file.getAbsolutePath());
        a.a(file);
    }

    final String a(et etVar) {
        File c = c();
        String b = etVar.b();
        if (etVar.a().equals(ea.ZIP)) {
            String a = k.a(c, b);
            if (i.c(a)) {
                c.b(a, "Failed to store html zip asset for remote path " + b + ". Not storing local asset");
                return null;
            }
            c.d(a, "Storing local triggered action html zip asset at local path " + a + " for remote path " + b);
            return a;
        }
        c = a.a(c.toString(), b, Integer.toString(f.a()), null);
        if (c == null) {
            return null;
        }
        Uri fromFile = Uri.fromFile(c);
        if (fromFile != null) {
            c.d(a, "Storing local triggered action image asset at local path " + fromFile.getPath() + " for remote path " + b);
            return fromFile.getPath();
        }
        c.b(a, "Failed to store image asset for remote path " + b + ". Not storing local asset");
        return null;
    }

    private Map<String, String> b() {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap();
        Map all = this.d.getAll();
        if (all == null || all.size() == 0) {
            return concurrentHashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return concurrentHashMap;
        }
        try {
            for (String str : keySet) {
                String string = this.d.getString(str, null);
                if (!i.c(string)) {
                    c.b(a, "Retrieving trigger local asset path " + string + " from local storage for remote path " + str + ".");
                    concurrentHashMap.put(str, string);
                }
            }
        } catch (Throwable e) {
            c.d(a, "Encountered unexpected exception while parsing stored triggered action local assets.", e);
        }
        return concurrentHashMap;
    }

    private File c() {
        return new File(this.b.getCacheDir().getPath() + "/ab_triggers");
    }
}
