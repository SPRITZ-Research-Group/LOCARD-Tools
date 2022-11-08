package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import defpackage.acou;
import defpackage.acry;
import defpackage.acss;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.v;

public final class PackageParts {
    private final Set<String> metadataParts = new LinkedHashSet();
    private final String packageFqName;
    private final LinkedHashMap<String, String> packageParts = new LinkedHashMap();

    public PackageParts(String str) {
        this.packageFqName = str;
    }

    public final Set<String> getParts() {
        return this.packageParts.keySet();
    }

    public final void addPart(String str, String str2) {
        this.packageParts.put(str, str2);
    }

    public final void addMetadataPart(String str) {
        Set set = this.metadataParts;
        if (set != null) {
            acss.e(set).add(str);
            return;
        }
        throw new v("null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.String>");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof PackageParts) {
            PackageParts packageParts = (PackageParts) obj;
            if (acry.a(packageParts.packageFqName, this.packageFqName) && acry.a(packageParts.packageParts, this.packageParts) && acry.a(packageParts.metadataParts, this.metadataParts)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.packageParts.hashCode()) * 31) + this.metadataParts.hashCode();
    }

    public final String toString() {
        return acou.b(getParts(), (Iterable) this.metadataParts).toString();
    }
}
