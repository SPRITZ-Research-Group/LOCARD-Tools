package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class aa<FieldDescriptorType extends ac<FieldDescriptorType>> {
    private static final aa d = new aa((byte) 0);
    private final cc<FieldDescriptorType, Object> a = cc.a(16);
    private boolean b;
    private boolean c = false;

    private aa() {
    }

    private aa(byte b) {
        c();
    }

    static int a(de deVar, int i, Object obj) {
        int f;
        int l = s.l(i);
        if (deVar == de.GROUP) {
            aj.a();
            l <<= 1;
        }
        switch (deVar) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                f = s.f();
                break;
            case FLOAT:
                ((Float) obj).floatValue();
                f = s.e();
                break;
            case INT64:
                f = s.d(((Long) obj).longValue());
                break;
            case UINT64:
                f = s.e(((Long) obj).longValue());
                break;
            case INT32:
                f = s.m(((Integer) obj).intValue());
                break;
            case FIXED64:
                ((Long) obj).longValue();
                f = s.c();
                break;
            case FIXED32:
                ((Integer) obj).intValue();
                f = s.a();
                break;
            case BOOL:
                ((Boolean) obj).booleanValue();
                f = s.g();
                break;
            case GROUP:
                f = s.d((bk) obj);
                break;
            case MESSAGE:
                if (!(obj instanceof aq)) {
                    f = s.c((bk) obj);
                    break;
                }
                f = s.a((aq) obj);
                break;
            case STRING:
                if (!(obj instanceof h)) {
                    f = s.b((String) obj);
                    break;
                }
                f = s.b((h) obj);
                break;
            case BYTES:
                if (!(obj instanceof h)) {
                    f = s.b((byte[]) obj);
                    break;
                }
                f = s.b((h) obj);
                break;
            case UINT32:
                f = s.n(((Integer) obj).intValue());
                break;
            case SFIXED32:
                ((Integer) obj).intValue();
                f = s.b();
                break;
            case SFIXED64:
                ((Long) obj).longValue();
                f = s.d();
                break;
            case SINT32:
                f = s.o(((Integer) obj).intValue());
                break;
            case SINT64:
                f = s.f(((Long) obj).longValue());
                break;
            case ENUM:
                if (!(obj instanceof ak)) {
                    f = s.p(((Integer) obj).intValue());
                    break;
                }
                f = s.p(((ak) obj).a());
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return l + f;
    }

    public static <T extends ac<T>> aa<T> a() {
        return d;
    }

    private static Object a(Object obj) {
        if (obj instanceof bq) {
            return ((bq) obj).b();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        Object obj2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj2, 0, bArr.length);
        return obj2;
    }

    private final void a(FieldDescriptorType fieldDescriptorType, Object obj) {
        boolean z = false;
        de b = fieldDescriptorType.b();
        aj.a(obj);
        switch (b.a()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if ((obj instanceof h) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof ak)) {
                    z = true;
                    break;
                }
            case MESSAGE:
                if ((obj instanceof bk) || (obj instanceof aq)) {
                    z = true;
                    break;
                }
        }
        if (z) {
            if (obj instanceof aq) {
                this.c = true;
            }
            this.a.a((Comparable) fieldDescriptorType, obj);
            return;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    static void a(s sVar, de deVar, int i, Object obj) throws IOException {
        if (deVar == de.GROUP) {
            aj.a();
            bk bkVar = (bk) obj;
            sVar.a(i, 3);
            bkVar.a(sVar);
            sVar.a(i, 4);
            return;
        }
        sVar.a(i, deVar.b());
        switch (deVar) {
            case DOUBLE:
                sVar.a(((Double) obj).doubleValue());
                return;
            case FLOAT:
                sVar.a(((Float) obj).floatValue());
                return;
            case INT64:
                sVar.a(((Long) obj).longValue());
                return;
            case UINT64:
                sVar.a(((Long) obj).longValue());
                return;
            case INT32:
                sVar.a(((Integer) obj).intValue());
                return;
            case FIXED64:
                sVar.c(((Long) obj).longValue());
                return;
            case FIXED32:
                sVar.d(((Integer) obj).intValue());
                return;
            case BOOL:
                sVar.a(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                ((bk) obj).a(sVar);
                return;
            case MESSAGE:
                sVar.b((bk) obj);
                return;
            case STRING:
                if (obj instanceof h) {
                    sVar.a((h) obj);
                    return;
                } else {
                    sVar.a((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof h) {
                    sVar.a((h) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                sVar.a(bArr, bArr.length);
                return;
            case UINT32:
                sVar.b(((Integer) obj).intValue());
                return;
            case SFIXED32:
                sVar.d(((Integer) obj).intValue());
                return;
            case SFIXED64:
                sVar.c(((Long) obj).longValue());
                return;
            case SINT32:
                sVar.c(((Integer) obj).intValue());
                return;
            case SINT64:
                sVar.b(((Long) obj).longValue());
                return;
            case ENUM:
                if (obj instanceof ak) {
                    sVar.a(((ak) obj).a());
                    return;
                } else {
                    sVar.a(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private static boolean a(Entry<FieldDescriptorType, Object> entry) {
        if (((ac) entry.getKey()).c() == dj.MESSAGE) {
            Object value = entry.getValue();
            if (value instanceof bk) {
                if (!((bk) value).c()) {
                    return false;
                }
            } else if (value instanceof aq) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    private static int b(ac<?> acVar, Object obj) {
        return a(acVar.b(), acVar.a(), obj);
    }

    private final void b(Entry<FieldDescriptorType, Object> entry) {
        Comparable comparable = (ac) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof aq) {
            value = aq.a();
        }
        if (comparable.c() == dj.MESSAGE) {
            Object obj = this.a.get(comparable);
            if (obj instanceof aq) {
                obj = aq.a();
            }
            if (obj == null) {
                this.a.a(comparable, a(value));
                return;
            } else {
                this.a.a(comparable, obj instanceof bq ? comparable.d() : comparable.a(((bk) obj).e(), (bk) value).d());
                return;
            }
        }
        this.a.a(comparable, a(value));
    }

    private static int c(Entry<FieldDescriptorType, Object> entry) {
        ac acVar = (ac) entry.getKey();
        Object value = entry.getValue();
        return acVar.c() == dj.MESSAGE ? value instanceof aq ? s.b(((ac) entry.getKey()).a(), (aq) value) : s.b(((ac) entry.getKey()).a(), (bk) value) : b(acVar, value);
    }

    public final void a(aa<FieldDescriptorType> aaVar) {
        for (int i = 0; i < aaVar.a.c(); i++) {
            b(aaVar.a.b(i));
        }
        for (Entry b : aaVar.a.d()) {
            b(b);
        }
    }

    final boolean b() {
        return this.a.isEmpty();
    }

    public final void c() {
        if (!this.b) {
            this.a.a();
            this.b = true;
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        aa aaVar = new aa();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.a.c()) {
                break;
            }
            Entry b = this.a.b(i2);
            aaVar.a((ac) b.getKey(), b.getValue());
            i = i2 + 1;
        }
        for (Entry entry : this.a.d()) {
            aaVar.a((ac) entry.getKey(), entry.getValue());
        }
        aaVar.c = this.c;
        return aaVar;
    }

    public final boolean d() {
        return this.b;
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> e() {
        return this.c ? new as(this.a.entrySet().iterator()) : this.a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof aa)) {
            return false;
        }
        return this.a.equals(((aa) obj).a);
    }

    final Iterator<Entry<FieldDescriptorType, Object>> f() {
        return this.c ? new as(this.a.e().iterator()) : this.a.e().iterator();
    }

    public final boolean g() {
        for (int i = 0; i < this.a.c(); i++) {
            if (!a(this.a.b(i))) {
                return false;
            }
        }
        for (Entry a : this.a.d()) {
            if (!a(a)) {
                return false;
            }
        }
        return true;
    }

    public final int h() {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i2;
            if (i3 >= this.a.c()) {
                break;
            }
            Entry b = this.a.b(i3);
            i += b((ac) b.getKey(), b.getValue());
            i2 = i3 + 1;
        }
        for (Entry entry : this.a.d()) {
            i += b((ac) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final int i() {
        int i = 0;
        int i2 = 0;
        while (i < this.a.c()) {
            i2 += c(this.a.b(i));
            i++;
        }
        for (Entry c : this.a.d()) {
            i2 += c(c);
        }
        return i2;
    }
}
