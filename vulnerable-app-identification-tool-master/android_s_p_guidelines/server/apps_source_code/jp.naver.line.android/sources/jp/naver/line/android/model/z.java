package jp.naver.line.android.model;

import android.graphics.Bitmap;
import android.text.TextUtils;
import defpackage.yta;
import defpackage.ytd;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Deprecated
public final class z {
    private long A;
    private long B;
    private int C;
    private co D;
    private Map<String, ag> E = new HashMap();
    private Bitmap F;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private long k;
    private long l;
    private ytd m;
    private yta n;
    private cq o;
    private ae p;
    private int q;
    private aa r;
    private jp.naver.line.android.db.main.model.z s;
    private int t;
    private boolean u;
    private boolean v;
    private long w = -1;
    private String x;
    private String y;
    private String z;

    public final String a() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final void b(String str) {
        this.b = str;
    }

    public final void c(String str) {
        this.c = str;
    }

    public final String b() {
        return this.d;
    }

    public final void d(String str) {
        this.d = str;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.i)) {
            return this.e;
        }
        return this.i;
    }

    public final String d() {
        return this.e;
    }

    public final void e(String str) {
        this.e = str;
    }

    public final String e() {
        return this.g;
    }

    public final void f(String str) {
        this.g = str;
    }

    public final String f() {
        return this.h;
    }

    public final void g(String str) {
        this.h = str;
    }

    public final void h(String str) {
        this.i = str;
    }

    public final void a(int i) {
        this.q = i;
    }

    public final void a(aa aaVar) {
        this.r = aaVar;
    }

    public final jp.naver.line.android.db.main.model.z g() {
        return this.s;
    }

    public final void a(jp.naver.line.android.db.main.model.z zVar) {
        this.s = zVar;
    }

    public final void b(int i) {
        this.t = i;
    }

    public final void a(boolean z) {
        this.u = z;
    }

    public final void i(String str) {
        this.f = str;
    }

    public final String h() {
        return this.j;
    }

    public final void j(String str) {
        this.j = str;
    }

    public final String i() {
        return this.x;
    }

    public final void k(String str) {
        this.x = str;
    }

    public final void l(String str) {
        this.z = str;
    }

    public final cq j() {
        return this.o;
    }

    public final void a(cq cqVar) {
        this.o = cqVar;
    }

    public final void a(ae aeVar) {
        this.p = aeVar;
    }

    public final void a(long j) {
        this.k = j;
    }

    public final long k() {
        return this.l;
    }

    public final void b(long j) {
        this.l = j;
    }

    public final void a(ytd ytd) {
        this.m = ytd;
    }

    public final yta l() {
        return this.n;
    }

    public final void a(yta yta) {
        this.n = yta;
    }

    public final void b(boolean z) {
        this.v = z;
    }

    public final void c(long j) {
        this.w = j;
    }

    public final void m(String str) {
        this.y = str;
    }

    public final void d(long j) {
        this.A = j;
    }

    public final void e(long j) {
        this.B = j;
    }

    public final void c(int i) {
        this.C = i;
    }

    public final co m() {
        return this.D;
    }

    public final void a(co coVar) {
        this.D = coVar;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Contact(");
        stringBuilder.append("mId:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("createdTime:");
        stringBuilder.append(this.l);
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        if (this.m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("relation:");
        if (this.n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.n);
        }
        stringBuilder.append(", ");
        stringBuilder.append("displayName:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("phoneticLastName:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serverName:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        stringBuilder.append(", ");
        stringBuilder.append("addressbookName:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("customName:");
        if (this.i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.i);
        }
        stringBuilder.append(", ");
        stringBuilder.append("pictureStatus:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("statusMsg:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("profileThumbnail:");
        if (this.F == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.F);
        }
        stringBuilder.append(", ");
        stringBuilder.append("videoProfile:");
        if (this.z == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.z);
        }
        stringBuilder.append(", ");
        stringBuilder.append("statusType:");
        if (this.o == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.o);
        }
        stringBuilder.append(", ");
        stringBuilder.append("isFirstType:");
        if (this.p == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.p);
        }
        stringBuilder.append(", ");
        stringBuilder.append("capableFlags:");
        stringBuilder.append(this.q);
        stringBuilder.append(", ");
        stringBuilder.append("contactKind:");
        if (this.r == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.r.value);
        }
        stringBuilder.append(", ");
        stringBuilder.append("buddyCategory:");
        if (this.s == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.s);
        }
        stringBuilder.append(", ");
        stringBuilder.append("iconType:");
        stringBuilder.append(this.t);
        stringBuilder.append(", ");
        stringBuilder.append("isOnAir:");
        stringBuilder.append(this.u);
        stringBuilder.append(", ");
        stringBuilder.append("contactKey:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("contactId:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("membershipMap:");
        if (this.E == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("[");
            for (Entry entry : this.E.entrySet()) {
                StringBuilder stringBuilder2 = new StringBuilder("groupId:");
                stringBuilder2.append((String) entry.getKey());
                stringBuilder2.append(",type:");
                stringBuilder2.append(((ag) entry.getValue()).name());
                stringBuilder2.append(",");
                stringBuilder.append(stringBuilder2.toString());
            }
            stringBuilder.append("]");
        }
        stringBuilder.append(", ");
        stringBuilder.append("thumbnailUrl:");
        if (this.x == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.x);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
