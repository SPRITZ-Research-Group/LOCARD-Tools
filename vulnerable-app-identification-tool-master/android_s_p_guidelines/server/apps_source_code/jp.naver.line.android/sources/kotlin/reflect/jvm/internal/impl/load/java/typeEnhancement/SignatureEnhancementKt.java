package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acnz;
import defpackage.acou;
import defpackage.acry;
import java.util.Set;

public final class SignatureEnhancementKt {
    private static final JavaTypeQualifiers createJavaTypeQualifiers(NullabilityQualifier nullabilityQualifier, MutabilityQualifier mutabilityQualifier, boolean z, boolean z2) {
        if (z2 && nullabilityQualifier == NullabilityQualifier.NOT_NULL) {
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, true, z);
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, false, z);
    }

    private static final <T> T select(Set<? extends T> set, T t, T t2, T t3, boolean z) {
        if (z) {
            T t4 = set.contains(t) ? t : set.contains(t2) ? t2 : null;
            if (acry.a((Object) t4, (Object) t) && acry.a((Object) t3, (Object) t2)) {
                return null;
            }
            if (t3 != null) {
                t4 = t3;
            }
            return t4;
        }
        if (t3 != null) {
            Set<? extends T> m = acnz.m(acou.b((Set) set, (Object) t3));
            if (m != null) {
                set = m;
            }
        }
        return acnz.f((Iterable) set);
    }

    private static final NullabilityQualifier select(Set<? extends NullabilityQualifier> set, NullabilityQualifier nullabilityQualifier, boolean z) {
        if (nullabilityQualifier == NullabilityQualifier.FORCE_FLEXIBILITY) {
            return NullabilityQualifier.FORCE_FLEXIBILITY;
        }
        return (NullabilityQualifier) select(set, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
