package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acru;

public final class JavaTypeQualifiers {
    public static final Companion Companion = new Companion();
    private static final JavaTypeQualifiers NONE = new JavaTypeQualifiers(null, null, false, false, 8, null);
    private final boolean isNotNullTypeParameter;
    private final boolean isNullabilityQualifierForWarning;
    private final MutabilityQualifier mutability;
    private final NullabilityQualifier nullability;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final JavaTypeQualifiers getNONE() {
            return JavaTypeQualifiers.NONE;
        }
    }

    public JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        this.nullability = nullabilityQualifier;
        this.mutability = mutabilityQualifier;
        this.isNotNullTypeParameter = z;
        this.isNullabilityQualifierForWarning = z2;
    }

    public final NullabilityQualifier getNullability() {
        return this.nullability;
    }

    public final MutabilityQualifier getMutability() {
        return this.mutability;
    }

    public final boolean isNotNullTypeParameter$descriptors_jvm() {
        return this.isNotNullTypeParameter;
    }

    public /* synthetic */ JavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2, int i, acru acru) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        this(nullabilityQualifier, mutabilityQualifier, z, z2);
    }

    public final boolean isNullabilityQualifierForWarning$descriptors_jvm() {
        return this.isNullabilityQualifierForWarning;
    }
}
