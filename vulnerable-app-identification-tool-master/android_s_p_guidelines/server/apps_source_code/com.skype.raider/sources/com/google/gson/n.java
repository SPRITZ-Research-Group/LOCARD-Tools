package com.google.gson;

import com.google.gson.a.a;
import com.google.gson.a.f;
import java.math.BigInteger;

public final class n extends i {
    private static final Class<?>[] a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public n(Boolean bool) {
        a((Object) bool);
    }

    public n(Number number) {
        a((Object) number);
    }

    public n(String string) {
        a((Object) string);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(Object primitive) {
        boolean z = false;
        if (primitive instanceof Character) {
            this.b = String.valueOf(((Character) primitive).charValue());
            return;
        }
        if (!(primitive instanceof Number)) {
            boolean z2;
            if (primitive instanceof String) {
                z2 = true;
            } else {
                Class cls = primitive.getClass();
                for (Class isAssignableFrom : a) {
                    if (isAssignableFrom.isAssignableFrom(cls)) {
                        z2 = true;
                        break;
                    }
                }
                z2 = false;
            }
        }
        z = true;
        a.a(z);
        this.b = primitive;
    }

    public final boolean h() {
        return this.b instanceof Boolean;
    }

    public final boolean i() {
        return this.b instanceof Number;
    }

    public final Number a() {
        return this.b instanceof String ? new f((String) this.b) : (Number) this.b;
    }

    public final boolean j() {
        return this.b instanceof String;
    }

    public final int hashCode() {
        if (this.b == null) {
            return 31;
        }
        long longValue;
        if (a(this)) {
            longValue = a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.b instanceof Number)) {
            return this.b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(a().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        n other = (n) obj;
        if (this.b == null) {
            if (other.b != null) {
                return false;
            }
            return true;
        } else if (a(this) && a(other)) {
            if (a().longValue() != other.a().longValue()) {
                return false;
            }
            return true;
        } else if (!(this.b instanceof Number) || !(other.b instanceof Number)) {
            return this.b.equals(other.b);
        } else {
            double a = a().doubleValue();
            double b = other.a().doubleValue();
            if (a == b) {
                return true;
            }
            if (Double.isNaN(a) && Double.isNaN(b)) {
                return true;
            }
            return false;
        }
    }

    private static boolean a(n primitive) {
        if (!(primitive.b instanceof Number)) {
            return false;
        }
        Number number = primitive.b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }

    public final boolean f() {
        if (this.b instanceof Boolean) {
            return ((Boolean) this.b).booleanValue();
        }
        return Boolean.parseBoolean(b());
    }

    public final String b() {
        if (this.b instanceof Number) {
            return a().toString();
        }
        if (this.b instanceof Boolean) {
            return ((Boolean) this.b).toString();
        }
        return (String) this.b;
    }

    public final double c() {
        return this.b instanceof Number ? a().doubleValue() : Double.parseDouble(b());
    }

    public final long d() {
        return this.b instanceof Number ? a().longValue() : Long.parseLong(b());
    }

    public final int e() {
        return this.b instanceof Number ? a().intValue() : Integer.parseInt(b());
    }
}
