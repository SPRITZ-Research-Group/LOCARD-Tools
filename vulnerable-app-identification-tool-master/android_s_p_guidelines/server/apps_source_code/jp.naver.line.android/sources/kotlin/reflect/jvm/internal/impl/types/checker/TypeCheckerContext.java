package kotlin.reflect.jvm.internal.impl.types.checker;

import defpackage.acru;
import defpackage.acry;
import java.util.ArrayDeque;
import java.util.Set;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class TypeCheckerContext {
    private final boolean allowedTypeVariable;
    private int argumentsDepth;
    private final boolean errorTypeEqualsToAnything;
    private ArrayDeque<SimpleType> supertypesDeque;
    private boolean supertypesLocked;
    private Set<SimpleType> supertypesSet;

    public enum LowerCapturedTypePolicy {
    }

    public enum SeveralSupertypesWithSameConstructorPolicy {
    }

    public abstract class SupertypesPolicy {

        public final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super();
            }

            public final SimpleType transformType(KotlinType kotlinType) {
                return FlexibleTypesKt.lowerIfFlexible(kotlinType);
            }
        }

        public final class LowerIfFlexibleWithCustomSubstitutor extends SupertypesPolicy {
            private final TypeSubstitutor substitutor;

            public LowerIfFlexibleWithCustomSubstitutor(TypeSubstitutor typeSubstitutor) {
                super();
                this.substitutor = typeSubstitutor;
            }

            public final SimpleType transformType(KotlinType kotlinType) {
                return TypeSubstitutionKt.asSimpleType(this.substitutor.safeSubstitute(FlexibleTypesKt.lowerIfFlexible(kotlinType), Variance.INVARIANT));
            }
        }

        public final class None extends SupertypesPolicy {
            public static final None INSTANCE = new None();

            private None() {
                super();
            }

            public final Void transformType(KotlinType kotlinType) {
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        public final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super();
            }

            public final SimpleType transformType(KotlinType kotlinType) {
                return FlexibleTypesKt.upperIfFlexible(kotlinType);
            }
        }

        public abstract SimpleType transformType(KotlinType kotlinType);

        private SupertypesPolicy() {
        }

        public /* synthetic */ SupertypesPolicy(acru acru) {
            this();
        }
    }

    public Boolean addSubtypeConstraint(UnwrappedType unwrappedType, UnwrappedType unwrappedType2) {
        return null;
    }

    public TypeCheckerContext(boolean z, boolean z2) {
        this.errorTypeEqualsToAnything = z;
        this.allowedTypeVariable = z2;
    }

    public /* synthetic */ TypeCheckerContext(boolean z, boolean z2, int i, acru acru) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        this(z, z2);
    }

    public final boolean getErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    public boolean areEqualTypeConstructors(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
        return acry.a((Object) typeConstructor, (Object) typeConstructor2);
    }

    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(SimpleType simpleType, NewCapturedType newCapturedType) {
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    public SeveralSupertypesWithSameConstructorPolicy getSameConstructorPolicy() {
        return SeveralSupertypesWithSameConstructorPolicy.INTERSECT_ARGUMENTS_AND_CHECK_AGAIN;
    }

    private final void initialize() {
        int i = this.supertypesLocked ^ 1;
        if (aa.a && i == 0) {
            throw new AssertionError("Assertion failed");
        }
        this.supertypesLocked = true;
        if (this.supertypesDeque == null) {
            this.supertypesDeque = new ArrayDeque(4);
        }
        if (this.supertypesSet == null) {
            this.supertypesSet = SmartSet.Companion.create();
        }
    }

    private final void clear() {
        ArrayDeque arrayDeque = this.supertypesDeque;
        if (arrayDeque == null) {
            acry.a();
        }
        arrayDeque.clear();
        Set set = this.supertypesSet;
        if (set == null) {
            acry.a();
        }
        set.clear();
        this.supertypesLocked = false;
    }

    public final boolean isAllowedTypeVariable(UnwrappedType unwrappedType) {
        return this.allowedTypeVariable && (unwrappedType.getConstructor() instanceof NewTypeVariableConstructor);
    }
}
