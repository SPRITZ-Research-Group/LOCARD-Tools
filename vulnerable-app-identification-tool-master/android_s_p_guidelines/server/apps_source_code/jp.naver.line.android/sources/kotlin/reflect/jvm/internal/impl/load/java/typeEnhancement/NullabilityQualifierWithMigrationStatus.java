package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acru;
import defpackage.acry;

public final class NullabilityQualifierWithMigrationStatus {
    private final boolean isForWarningOnly;
    private final NullabilityQualifier qualifier;

    public static /* synthetic */ NullabilityQualifierWithMigrationStatus copy$default(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifier nullabilityQualifier, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            nullabilityQualifier = nullabilityQualifierWithMigrationStatus.qualifier;
        }
        if ((i & 2) != 0) {
            z = nullabilityQualifierWithMigrationStatus.isForWarningOnly;
        }
        return nullabilityQualifierWithMigrationStatus.copy(nullabilityQualifier, z);
    }

    public final NullabilityQualifierWithMigrationStatus copy(NullabilityQualifier nullabilityQualifier, boolean z) {
        return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, z);
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NullabilityQualifierWithMigrationStatus) {
                NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = (NullabilityQualifierWithMigrationStatus) obj;
                if (acry.a(this.qualifier, nullabilityQualifierWithMigrationStatus.qualifier)) {
                    if ((this.isForWarningOnly == nullabilityQualifierWithMigrationStatus.isForWarningOnly ? 1 : null) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        NullabilityQualifier nullabilityQualifier = this.qualifier;
        int hashCode = (nullabilityQualifier != null ? nullabilityQualifier.hashCode() : 0) * 31;
        int i = this.isForWarningOnly;
        if (i != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("NullabilityQualifierWithMigrationStatus(qualifier=");
        stringBuilder.append(this.qualifier);
        stringBuilder.append(", isForWarningOnly=");
        stringBuilder.append(this.isForWarningOnly);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier, boolean z) {
        this.qualifier = nullabilityQualifier;
        this.isForWarningOnly = z;
    }

    public final NullabilityQualifier getQualifier() {
        return this.qualifier;
    }

    public /* synthetic */ NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier, boolean z, int i, acru acru) {
        if ((i & 2) != 0) {
            z = false;
        }
        this(nullabilityQualifier, z);
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }
}
