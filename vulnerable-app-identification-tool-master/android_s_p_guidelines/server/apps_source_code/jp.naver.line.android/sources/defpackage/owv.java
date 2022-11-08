package defpackage;

import ezvcard.a;
import ezvcard.b;
import ezvcard.f;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: owv */
public abstract class owv implements Comparable<owv> {
    protected String d;
    protected oul e = new oul();

    public /* synthetic */ int compareTo(Object obj) {
        owv owv = (owv) obj;
        Integer b = this.e.b();
        Integer b2 = owv.e.b();
        if (b == null && b2 == null) {
            return 0;
        }
        if (b == null) {
            return 1;
        }
        if (b2 == null) {
            return -1;
        }
        return b2.compareTo(b);
    }

    public final oul k() {
        return this.e;
    }

    public final void a(oul oul) {
        if (oul != null) {
            this.e = oul;
            return;
        }
        throw new NullPointerException(a.INSTANCE.b(42, new Object[0]));
    }

    public final String l() {
        return this.d;
    }

    public final void c(String str) {
        this.d = str;
    }

    protected Map<String, Object> j() {
        return Collections.emptyMap();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName());
        stringBuilder.append(" [ group=");
        stringBuilder.append(this.d);
        stringBuilder.append(" | parameters=");
        stringBuilder.append(this.e);
        for (Entry entry : j().entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            stringBuilder.append(" | ");
            stringBuilder.append(str);
            stringBuilder.append('=');
            stringBuilder.append(value);
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public int hashCode() {
        return (((this.d == null ? 0 : this.d.toLowerCase().hashCode()) + 31) * 31) + this.e.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        owv owv = (owv) obj;
        if (this.d == null) {
            if (owv.d != null) {
                return false;
            }
        } else if (!this.d.equalsIgnoreCase(owv.d)) {
            return false;
        }
        return this.e.equals(owv.e);
    }

    public final boolean a(f fVar) {
        b bVar = (b) getClass().getAnnotation(b.class);
        f[] values;
        if (bVar == null) {
            values = f.values();
        } else {
            values = bVar.a();
        }
        for (f fVar2 : values) {
            if (fVar2 == fVar) {
                return true;
            }
        }
        return false;
    }
}
