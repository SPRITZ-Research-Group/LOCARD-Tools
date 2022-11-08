package com.facebook.ads.internal.q.c;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.p.a.p;
import com.facebook.ads.internal.q.a.k;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class e extends AsyncTask<String, Void, f> {
    private static final String a = e.class.getSimpleName();
    private static final Set<String> b;
    private Context c;
    private Map<String, String> d;
    private Map<String, String> e;
    private n f;
    private a g;

    public interface a {
        void a();

        void a(f fVar);
    }

    static {
        Set hashSet = new HashSet();
        b = hashSet;
        hashSet.add("#");
        b.add("null");
    }

    public e(Context context) {
        this(context, null, (byte) 0);
    }

    public e(Context context, Map<String, String> map) {
        this(context, map, (byte) 0);
    }

    private e(Context context, Map<String, String> map, byte b) {
        this.c = context;
        this.d = map != null ? new HashMap(map) : null;
        this.e = null;
    }

    private static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        return str + (str.contains("?") ? "&" : "?") + str2 + "=" + URLEncoder.encode(str3);
    }

    private boolean a(String str) {
        com.facebook.ads.internal.p.a.a a = d.a(this.c);
        try {
            if (this.e == null || this.e.size() == 0) {
                this.f = a.a(str);
            } else {
                p pVar = new p();
                pVar.putAll(this.e);
                this.f = a.a(str, pVar);
            }
            return this.f != null && this.f.a() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    private static String b(String str) {
        try {
            return a(str, "analog", k.a(com.facebook.ads.internal.g.a.a()));
        } catch (Exception e) {
            return str;
        }
    }

    public final void a(a aVar) {
        this.g = aVar;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        Object obj = ((String[]) objArr)[0];
        if (TextUtils.isEmpty(obj) || b.contains(obj)) {
            return null;
        }
        String b = b(obj);
        if (!(this.d == null || this.d.isEmpty())) {
            String str;
            Iterator it = this.d.entrySet().iterator();
            while (true) {
                str = b;
                if (!it.hasNext()) {
                    break;
                }
                Entry entry = (Entry) it.next();
                b = a(str, (String) entry.getKey(), (String) entry.getValue());
            }
            b = str;
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                return null;
            }
            if (a(b)) {
                return new f(this.f);
            }
            i = i2;
        }
    }

    protected void onCancelled() {
        if (this.g != null) {
            this.g.a();
        }
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        f fVar = (f) obj;
        if (this.g != null) {
            this.g.a(fVar);
        }
    }
}
