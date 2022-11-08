package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acnz;
import defpackage.acqr;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;

public class TypeUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final SimpleType CANT_INFER_FUNCTION_PARAM_TYPE = ErrorUtils.createErrorType("Cannot be inferred");
    public static final SimpleType DONT_CARE = ErrorUtils.createErrorTypeWithCustomDebugName("DONT_CARE");
    public static final SimpleType NO_EXPECTED_TYPE = new SpecialType("NO_EXPECTED_TYPE");
    public static final SimpleType UNIT_EXPECTED_TYPE = new SpecialType("UNIT_EXPECTED_TYPE");

    public class SpecialType extends DelegatingSimpleType {
        private final String name;

        public SpecialType(String str) {
            this.name = str;
        }

        protected SimpleType getDelegate() {
            throw new IllegalStateException(this.name);
        }

        public SimpleType replaceAnnotations(Annotations annotations) {
            throw new IllegalStateException(this.name);
        }

        public SimpleType makeNullableAsSpecified(boolean z) {
            throw new IllegalStateException(this.name);
        }

        public String toString() {
            return this.name;
        }
    }

    public static boolean noExpectedType(KotlinType kotlinType) {
        return kotlinType == NO_EXPECTED_TYPE || kotlinType == UNIT_EXPECTED_TYPE;
    }

    public static boolean isDontCarePlaceholder(KotlinType kotlinType) {
        return kotlinType != null && kotlinType.getConstructor() == DONT_CARE.getConstructor();
    }

    public static KotlinType makeNullable(KotlinType kotlinType) {
        return makeNullableAsSpecified(kotlinType, true);
    }

    public static KotlinType makeNotNullable(KotlinType kotlinType) {
        return makeNullableAsSpecified(kotlinType, false);
    }

    public static KotlinType makeNullableAsSpecified(KotlinType kotlinType, boolean z) {
        return kotlinType.unwrap().makeNullableAsSpecified(z);
    }

    public static KotlinType makeNullableIfNeeded(KotlinType kotlinType, boolean z) {
        return z ? makeNullable(kotlinType) : kotlinType;
    }

    public static SimpleType makeUnsubstitutedType(ClassifierDescriptor classifierDescriptor, MemberScope memberScope) {
        if (ErrorUtils.isError(classifierDescriptor)) {
            return ErrorUtils.createErrorType("Unsubstituted type for ".concat(String.valueOf(classifierDescriptor)));
        }
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), typeConstructor, getDefaultTypeProjections(typeConstructor.getParameters()), false, memberScope);
    }

    public static List<TypeProjection> getDefaultTypeProjections(List<TypeParameterDescriptor> list) {
        Iterable arrayList = new ArrayList(list.size());
        for (TypeParameterDescriptor defaultType : list) {
            arrayList.add(new TypeProjectionImpl(defaultType.getDefaultType()));
        }
        return acnz.k(arrayList);
    }

    public static List<KotlinType> getImmediateSupertypes(KotlinType kotlinType) {
        TypeSubstitutor create = TypeSubstitutor.create(kotlinType);
        Collection<KotlinType> supertypes = kotlinType.getConstructor().getSupertypes();
        List<KotlinType> arrayList = new ArrayList(supertypes.size());
        for (KotlinType createSubstitutedSupertype : supertypes) {
            KotlinType createSubstitutedSupertype2 = createSubstitutedSupertype(kotlinType, createSubstitutedSupertype2, create);
            if (createSubstitutedSupertype2 != null) {
                arrayList.add(createSubstitutedSupertype2);
            }
        }
        return arrayList;
    }

    public static KotlinType createSubstitutedSupertype(KotlinType kotlinType, KotlinType kotlinType2, TypeSubstitutor typeSubstitutor) {
        kotlinType2 = typeSubstitutor.substitute(kotlinType2, Variance.INVARIANT);
        return kotlinType2 != null ? makeNullableIfNeeded(kotlinType2, kotlinType.isMarkedNullable()) : null;
    }

    public static boolean isNullableType(KotlinType kotlinType) {
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType) && isNullableType(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound())) {
            return true;
        }
        if (isTypeParameter(kotlinType)) {
            return hasNullableSuperType(kotlinType);
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            for (KotlinType isNullableType : constructor.getSupertypes()) {
                if (isNullableType(isNullableType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean acceptsNullable(KotlinType kotlinType) {
        if (kotlinType.isMarkedNullable()) {
            return true;
        }
        if (FlexibleTypesKt.isFlexible(kotlinType) && acceptsNullable(FlexibleTypesKt.asFlexibleType(kotlinType).getUpperBound())) {
            return true;
        }
        return false;
    }

    public static boolean hasNullableSuperType(KotlinType kotlinType) {
        if (kotlinType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
            return false;
        }
        for (KotlinType isNullableType : getImmediateSupertypes(kotlinType)) {
            if (isNullableType(isNullableType)) {
                return true;
            }
        }
        return false;
    }

    public static ClassDescriptor getClassDescriptor(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return declarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) declarationDescriptor : null;
    }

    public static boolean contains(KotlinType kotlinType, acqr<UnwrappedType, Boolean> acqr) {
        if (kotlinType == null) {
            return false;
        }
        UnwrappedType unwrap = kotlinType.unwrap();
        if (((Boolean) acqr.invoke(unwrap)).booleanValue()) {
            return true;
        }
        FlexibleType flexibleType = unwrap instanceof FlexibleType ? (FlexibleType) unwrap : null;
        if (flexibleType != null && (contains(flexibleType.getLowerBound(), acqr) || contains(flexibleType.getUpperBound(), acqr))) {
            return true;
        }
        if ((unwrap instanceof DefinitelyNotNullType) && contains(((DefinitelyNotNullType) unwrap).getOriginal(), acqr)) {
            return true;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            for (KotlinType contains : ((IntersectionTypeConstructor) constructor).getSupertypes()) {
                if (contains(contains, acqr)) {
                    return true;
                }
            }
            return false;
        }
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            if (!typeProjection.isStarProjection()) {
                if (contains(typeProjection.getType(), acqr)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static TypeProjection makeStarProjection(TypeParameterDescriptor typeParameterDescriptor) {
        return new StarProjectionImpl(typeParameterDescriptor);
    }

    public static boolean isTypeParameter(KotlinType kotlinType) {
        return getTypeParameterDescriptorOrNull(kotlinType) != null || (kotlinType.getConstructor() instanceof NewTypeVariableConstructor);
    }

    public static TypeParameterDescriptor getTypeParameterDescriptorOrNull(KotlinType kotlinType) {
        return kotlinType.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) kotlinType.getConstructor().getDeclarationDescriptor() : null;
    }
}
