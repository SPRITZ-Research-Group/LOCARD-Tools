package kotlin.reflect.jvm.internal.impl.load.java;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.name.Name;

final class NameAndSignature {
    private final Name name;
    private final String signature;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NameAndSignature) {
                NameAndSignature nameAndSignature = (NameAndSignature) obj;
                if (acry.a(this.name, nameAndSignature.name)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Name name = this.name;
        int i = 0;
        int hashCode = (name != null ? name.hashCode() : 0) * 31;
        String str = this.signature;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("NameAndSignature(name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.signature);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public NameAndSignature(Name name, String str) {
        this.name = name;
        this.signature = str;
    }

    public final Name getName() {
        return this.name;
    }

    public final String getSignature() {
        return this.signature;
    }
}
