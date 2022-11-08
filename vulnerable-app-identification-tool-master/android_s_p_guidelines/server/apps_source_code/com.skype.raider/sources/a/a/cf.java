package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class cf {
    private static final String a = c.a(cf.class);
    private final SharedPreferences b;
    private final Set<String> c;
    private final Set<String> d;
    private ag e;

    @VisibleForTesting
    enum a {
        READ_CARDS("read_cards_set", "read_cards_flat"),
        VIEWED_CARDS("viewed_cards_set", "viewed_cards_flat");
        
        private final String c;
        private final String d;

        private a(String str, String str2) {
            this.c = str;
            this.d = str2;
        }

        public final String a() {
            return this.c;
        }

        public final String b() {
            return this.d;
        }
    }

    public cf(Context context, String str) {
        this.b = context.getSharedPreferences("com.appboy.storage.feedstorageprovider" + i.a(context, str == null ? "" : str), 0);
        this.c = a(a.VIEWED_CARDS);
        this.d = a(a.READ_CARDS);
        Editor edit = this.b.edit();
        edit.putString("uid", str);
        edit.apply();
    }

    public final void a(ag agVar) {
        this.e = agVar;
    }

    public final void a(String str) {
        if (!this.c.contains(str)) {
            this.c.add(str);
            a(this.c, a.VIEWED_CARDS);
        }
    }

    public final void b(String str) {
        if (!this.d.contains(str)) {
            this.d.add(str);
            a(this.d, a.READ_CARDS);
        }
    }

    public final com.appboy.c.a a(JSONArray jSONArray, String str) {
        Object obj;
        if (str == null) {
            obj = "";
        } else {
            String obj2 = str;
        }
        String string = this.b.getString("uid", "");
        if (string.equals(obj2)) {
            c.d(a, "Updating offline feed for user with id: " + str);
            long a = co.a();
            Editor edit = this.b.edit();
            if (jSONArray == null || jSONArray.length() == 0) {
                edit.remove("cards");
            } else {
                edit.putString("cards", jSONArray.toString());
            }
            edit.putLong("cards_timestamp", a);
            edit.apply();
            this.c.retainAll(a(jSONArray));
            a(this.c, a.VIEWED_CARDS);
            this.d.retainAll(a(jSONArray));
            a(this.d, a.READ_CARDS);
            return a(jSONArray, str, false, a);
        }
        c.d(a, "The received cards are for user " + str + " and the current user is " + string + " , the cards will be discarded and no changes will be made.");
        return null;
    }

    public final com.appboy.c.a a() {
        String string = this.b.getString("uid", "");
        return a(new JSONArray(this.b.getString("cards", "[]")), string, true, this.b.getLong("cards_timestamp", -1));
    }

    private com.appboy.c.a a(JSONArray jSONArray, String str, boolean z, long j) {
        List arrayList;
        if (jSONArray == null || jSONArray.length() == 0) {
            arrayList = new ArrayList();
        } else {
            arrayList = ay.a(jSONArray, com.appboy.e.a.c.class, this.e, this);
        }
        for (com.appboy.e.a.c cVar : arrayList) {
            if (this.c.contains(cVar.j())) {
                cVar.n();
                cVar.p();
            }
            if (this.d.contains(cVar.j())) {
                cVar.p();
            }
        }
        return new com.appboy.c.a(arrayList, str, z, j);
    }

    private Set<String> a(a aVar) {
        String a = aVar.a();
        String b = aVar.b();
        if (!this.b.contains(b)) {
            return new ConcurrentSkipListSet(this.b.getStringSet(a, new HashSet()));
        }
        String string = this.b.getString(b, null);
        Set hashSet = new HashSet();
        if (string != null) {
            Collections.addAll(hashSet, string.split(";"));
        }
        Editor edit = this.b.edit();
        edit.remove(b);
        edit.apply();
        a(hashSet, aVar);
        return hashSet;
    }

    private void a(Set<String> set, a aVar) {
        String a = aVar.a();
        Editor edit = this.b.edit();
        if (set == null || set.isEmpty()) {
            edit.remove(a);
        } else {
            edit.putStringSet(a, set);
        }
        edit.apply();
    }

    private static Set<String> a(JSONArray jSONArray) {
        Set<String> hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("id")) {
                    hashSet.add(jSONObject.getString("id"));
                }
            }
        }
        return hashSet;
    }
}
