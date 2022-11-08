package ezvcard;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.AppMeasurement.Param;
import defpackage.owx;

public class e {
    @b(a = {f.V2_1})
    public static final e a = new e(ImagesContract.URL);
    @b(a = {f.V2_1})
    public static final e b = new e("content-id");
    @b(a = {f.V3_0})
    public static final e c = new e("binary");
    @b(a = {f.V3_0, f.V4_0})
    public static final e d = new e("uri");
    public static final e e = new e("text");
    @b(a = {f.V3_0, f.V4_0})
    public static final e f = new e("date");
    @b(a = {f.V3_0, f.V4_0})
    public static final e g = new e("time");
    @b(a = {f.V3_0, f.V4_0})
    public static final e h = new e("date-time");
    @b(a = {f.V4_0})
    public static final e i = new e("date-and-or-time");
    @b(a = {f.V4_0})
    public static final e j = new e(Param.TIMESTAMP);
    @b(a = {f.V4_0})
    public static final e k = new e("boolean");
    @b(a = {f.V4_0})
    public static final e l = new e("integer");
    @b(a = {f.V4_0})
    public static final e m = new e("float");
    @b(a = {f.V4_0})
    public static final e n = new e("utc-offset");
    @b(a = {f.V4_0})
    public static final e o = new e("language-tag");
    private static final owx<e, String> p = new owx<e, String>(e.class) {
        protected final /* synthetic */ Object a(Object obj) {
            return new e((String) obj, (byte) 0);
        }

        protected final /* synthetic */ boolean a(Object obj, Object obj2) {
            return ((e) obj).q.equalsIgnoreCase((String) obj2);
        }
    };
    private final String q;

    /* synthetic */ e(String str, byte b) {
        this(str);
    }

    private e(String str) {
        this.q = str;
    }

    public final String a() {
        return this.q;
    }

    public String toString() {
        return this.q;
    }

    public static e a(String str) {
        return (e) p.b(str);
    }

    public static e b(String str) {
        return (e) p.c(str);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
