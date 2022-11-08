package androidx.work;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class ac {
    private UUID a;
    private ad b;
    private h c;
    private Set<String> d;

    public ac(UUID uuid, ad adVar, h hVar, List<String> list) {
        this.a = uuid;
        this.b = adVar;
        this.c = hVar;
        this.d = new HashSet(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ac acVar = (ac) obj;
        if (!this.a == null ? this.a.equals(acVar.a) : acVar.a == null) {
            return false;
        }
        if (this.b != acVar.b) {
            return false;
        }
        if (!this.c == null ? this.c.equals(acVar.c) : acVar.c == null) {
            return false;
        }
        if (this.d != null) {
            return this.d.equals(acVar.d);
        }
        return acVar.d == null;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("WorkInfo{mId='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", mState=");
        stringBuilder.append(this.b);
        stringBuilder.append(", mOutputData=");
        stringBuilder.append(this.c);
        stringBuilder.append(", mTags=");
        stringBuilder.append(this.d);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
