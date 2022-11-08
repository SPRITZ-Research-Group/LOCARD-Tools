package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import defpackage.acry;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;

public final class NullabilityQualifierWithApplicability {
    private final NullabilityQualifierWithMigrationStatus nullabilityQualifier;
    private final Collection<QualifierApplicabilityType> qualifierApplicabilityTypes;

    public final NullabilityQualifierWithMigrationStatus component1() {
        return this.nullabilityQualifier;
    }

    public final Collection<QualifierApplicabilityType> component2() {
        return this.qualifierApplicabilityTypes;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NullabilityQualifierWithApplicability) {
                NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) obj;
                if (acry.a(this.nullabilityQualifier, nullabilityQualifierWithApplicability.nullabilityQualifier)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = this.nullabilityQualifier;
        int i = 0;
        int hashCode = (nullabilityQualifierWithMigrationStatus != null ? nullabilityQualifierWithMigrationStatus.hashCode() : 0) * 31;
        Collection collection = this.qualifierApplicabilityTypes;
        if (collection != null) {
            i = collection.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("NullabilityQualifierWithApplicability(nullabilityQualifier=");
        stringBuilder.append(this.nullabilityQualifier);
        stringBuilder.append(", qualifierApplicabilityTypes=");
        stringBuilder.append(this.qualifierApplicabilityTypes);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, Collection<? extends QualifierApplicabilityType> collection) {
        this.nullabilityQualifier = nullabilityQualifierWithMigrationStatus;
        this.qualifierApplicabilityTypes = collection;
    }
}
