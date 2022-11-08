package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

final class TypeAndDefaultQualifiers {
    private final JavaTypeQualifiers defaultQualifiers;
    private final KotlinType type;

    public final KotlinType component1() {
        return this.type;
    }

    public final JavaTypeQualifiers component2() {
        return this.defaultQualifiers;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TypeAndDefaultQualifiers) {
                TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) obj;
                if (acry.a(this.type, typeAndDefaultQualifiers.type)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        KotlinType kotlinType = this.type;
        int i = 0;
        int hashCode = (kotlinType != null ? kotlinType.hashCode() : 0) * 31;
        JavaTypeQualifiers javaTypeQualifiers = this.defaultQualifiers;
        if (javaTypeQualifiers != null) {
            i = javaTypeQualifiers.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("TypeAndDefaultQualifiers(type=");
        stringBuilder.append(this.type);
        stringBuilder.append(", defaultQualifiers=");
        stringBuilder.append(this.defaultQualifiers);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public TypeAndDefaultQualifiers(KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers) {
        this.type = kotlinType;
        this.defaultQualifiers = javaTypeQualifiers;
    }

    public final KotlinType getType() {
        return this.type;
    }
}
