package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import defpackage.acns;
import defpackage.acqr;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;

public final class TypeUtilsKt {
    public static final KotlinBuiltIns getBuiltIns(KotlinType kotlinType) {
        return kotlinType.getConstructor().getBuiltIns();
    }

    public static final KotlinType makeNullable(KotlinType kotlinType) {
        return TypeUtils.makeNullable(kotlinType);
    }

    public static final KotlinType makeNotNullable(KotlinType kotlinType) {
        return TypeUtils.makeNotNullable(kotlinType);
    }

    public static final boolean isAnyOrNullableAny(KotlinType kotlinType) {
        return KotlinBuiltIns.isAnyOrNullableAny(kotlinType);
    }

    public static final boolean isTypeParameter(KotlinType kotlinType) {
        return TypeUtils.isTypeParameter(kotlinType);
    }

    public static final boolean isSubtypeOf(KotlinType kotlinType, KotlinType kotlinType2) {
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType, kotlinType2);
    }

    public static final KotlinType replaceAnnotations(KotlinType kotlinType, Annotations annotations) {
        if (kotlinType.getAnnotations().isEmpty() && annotations.isEmpty()) {
            return kotlinType;
        }
        return kotlinType.unwrap().replaceAnnotations(annotations);
    }

    public static final TypeProjection createProjection(KotlinType kotlinType, Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == variance) {
            variance = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(variance, kotlinType);
    }

    public static final TypeProjection asTypeProjection(KotlinType kotlinType) {
        return new TypeProjectionImpl(kotlinType);
    }

    public static final boolean contains(KotlinType kotlinType, acqr<? super UnwrappedType, Boolean> acqr) {
        return TypeUtils.contains(kotlinType, acqr);
    }

    public static final boolean canHaveUndefinedNullability(UnwrappedType unwrappedType) {
        return (unwrappedType.getConstructor() instanceof NewTypeVariableConstructor) || (unwrappedType.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) || (unwrappedType instanceof NewCapturedType);
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType kotlinType) {
        UnwrappedType flexibleType;
        UnwrappedType unwrap = kotlinType.unwrap();
        SimpleType upperBound;
        if (unwrap instanceof FlexibleType) {
            Iterable<TypeParameterDescriptor> parameters;
            Collection arrayList;
            FlexibleType flexibleType2 = (FlexibleType) unwrap;
            SimpleType lowerBound = flexibleType2.getLowerBound();
            if (!(lowerBound.getConstructor().getParameters().isEmpty() || lowerBound.getConstructor().getDeclarationDescriptor() == null)) {
                parameters = lowerBound.getConstructor().getParameters();
                arrayList = new ArrayList(acns.a((Iterable) parameters, 10));
                for (TypeParameterDescriptor starProjectionImpl : parameters) {
                    arrayList.add(new StarProjectionImpl(starProjectionImpl));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, (List) arrayList, null, 2, null);
            }
            upperBound = flexibleType2.getUpperBound();
            if (!(upperBound.getConstructor().getParameters().isEmpty() || upperBound.getConstructor().getDeclarationDescriptor() == null)) {
                parameters = upperBound.getConstructor().getParameters();
                arrayList = new ArrayList(acns.a((Iterable) parameters, 10));
                for (TypeParameterDescriptor starProjectionImpl2 : parameters) {
                    arrayList.add(new StarProjectionImpl(starProjectionImpl2));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList, null, 2, null);
            }
            flexibleType = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrap instanceof SimpleType) {
            upperBound = (SimpleType) unwrap;
            if (!(upperBound.getConstructor().getParameters().isEmpty() || upperBound.getConstructor().getDeclarationDescriptor() == null)) {
                Iterable<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Collection arrayList2 = new ArrayList(acns.a((Iterable) parameters2, 10));
                for (TypeParameterDescriptor starProjectionImpl3 : parameters2) {
                    arrayList2.add(new StarProjectionImpl(starProjectionImpl3));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, (List) arrayList2, null, 2, null);
            }
            flexibleType = upperBound;
        } else {
            throw new k();
        }
        return TypeWithEnhancementKt.inheritEnhancement(flexibleType, unwrap);
    }
}
