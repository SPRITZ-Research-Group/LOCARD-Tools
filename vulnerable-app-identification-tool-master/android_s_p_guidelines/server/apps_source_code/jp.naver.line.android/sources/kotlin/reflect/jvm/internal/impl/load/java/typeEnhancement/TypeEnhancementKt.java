package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acnr;
import defpackage.acns;
import defpackage.acnz;
import defpackage.acqr;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.aa;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.v;

public final class TypeEnhancementKt {
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION);
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION);

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MutabilityQualifier.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            $EnumSwitchMapping$0[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            iArr = new int[NullabilityQualifier.values().length];
            $EnumSwitchMapping$1 = iArr;
            iArr[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            $EnumSwitchMapping$1[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
        }
    }

    public static final KotlinType enhance(KotlinType kotlinType, acqr<? super Integer, JavaTypeQualifiers> acqr) {
        return enhancePossiblyFlexible(kotlinType.unwrap(), acqr, 0).getTypeIfChanged();
    }

    public static final boolean hasEnhancedNullability(KotlinType kotlinType) {
        return kotlinType.getAnnotations().findAnnotation(JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION) != null;
    }

    private static final Result enhancePossiblyFlexible(UnwrappedType unwrappedType, acqr<? super Integer, JavaTypeQualifiers> acqr, int i) {
        KotlinType kotlinType = unwrappedType;
        boolean z = false;
        if (KotlinTypeKt.isError(kotlinType)) {
            return new Result(kotlinType, 1, false);
        }
        if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult enhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), acqr, i, TypeComponentPosition.FLEXIBLE_LOWER);
            SimpleResult enhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), acqr, i, TypeComponentPosition.FLEXIBLE_UPPER);
            Object obj = enhanceInflexible.getSubtreeSize() == enhanceInflexible2.getSubtreeSize() ? 1 : null;
            if (aa.a && obj == null) {
                StringBuilder stringBuilder = new StringBuilder("Different tree sizes of bounds: lower = (");
                stringBuilder.append(flexibleType.getLowerBound());
                stringBuilder.append(", ");
                stringBuilder.append(enhanceInflexible.getSubtreeSize());
                stringBuilder.append("), upper = (");
                stringBuilder.append(flexibleType.getUpperBound());
                stringBuilder.append(", ");
                stringBuilder.append(enhanceInflexible2.getSubtreeSize());
                stringBuilder.append(')');
                throw new AssertionError(stringBuilder.toString());
            }
            if (enhanceInflexible.getWereChanges() || enhanceInflexible2.getWereChanges()) {
                z = true;
            }
            KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible.getType());
            if (enhancement == null) {
                enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible2.getType());
            }
            if (z) {
                if (unwrappedType instanceof RawTypeImpl) {
                    unwrappedType = new RawTypeImpl(enhanceInflexible.getType(), enhanceInflexible2.getType());
                } else {
                    unwrappedType = KotlinTypeFactory.flexibleType(enhanceInflexible.getType(), enhanceInflexible2.getType());
                }
                unwrappedType = TypeWithEnhancementKt.wrapEnhancement(unwrappedType, enhancement);
            }
            return new Result(unwrappedType, enhanceInflexible.getSubtreeSize(), z);
        } else if (unwrappedType instanceof SimpleType) {
            return enhanceInflexible((SimpleType) unwrappedType, acqr, i, TypeComponentPosition.INFLEXIBLE);
        } else {
            throw new k();
        }
    }

    private static final SimpleResult enhanceInflexible(SimpleType simpleType, acqr<? super Integer, JavaTypeQualifiers> acqr, int i, TypeComponentPosition typeComponentPosition) {
        SimpleType simpleType2 = simpleType;
        acqr<? super Integer, JavaTypeQualifiers> acqr2 = acqr;
        TypeComponentPosition typeComponentPosition2 = typeComponentPosition;
        if (!shouldEnhance(typeComponentPosition) && simpleType.getArguments().isEmpty()) {
            return new SimpleResult(simpleType2, 1, false);
        }
        ClassifierDescriptor declarationDescriptor = simpleType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return new SimpleResult(simpleType2, 1, false);
        }
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) acqr2.invoke(Integer.valueOf(i));
        EnhancementResult enhanceMutability = enhanceMutability(declarationDescriptor, javaTypeQualifiers, typeComponentPosition2);
        ClassifierDescriptor classifierDescriptor = (ClassifierDescriptor) enhanceMutability.component1();
        Annotations component2 = enhanceMutability.component2();
        TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
        int i2 = i + 1;
        Object obj = component2 != null ? 1 : null;
        Iterable arguments = simpleType.getArguments();
        Collection arrayList = new ArrayList(acns.a(arguments, 10));
        int i3 = i2;
        i2 = 0;
        for (Object next : arguments) {
            Object makeStarProjection;
            int i4 = i2 + 1;
            if (i2 < 0) {
                acnr.a();
            }
            TypeProjection typeProjection = (TypeProjection) next;
            if (typeProjection.isStarProjection()) {
                i3++;
                makeStarProjection = TypeUtils.makeStarProjection((TypeParameterDescriptor) classifierDescriptor.getTypeConstructor().getParameters().get(i2));
            } else {
                Result enhancePossiblyFlexible = enhancePossiblyFlexible(typeProjection.getType().unwrap(), acqr2, i3);
                obj = (obj != null || enhancePossiblyFlexible.getWereChanges()) ? 1 : null;
                i3 += enhancePossiblyFlexible.getSubtreeSize();
                makeStarProjection = TypeUtilsKt.createProjection(enhancePossiblyFlexible.getType(), typeProjection.getProjectionKind(), (TypeParameterDescriptor) typeConstructor.getParameters().get(i2));
            }
            arrayList.add(makeStarProjection);
            i2 = i4;
        }
        List list = (List) arrayList;
        EnhancementResult enhancedNullability = getEnhancedNullability(simpleType2, javaTypeQualifiers, typeComponentPosition2);
        boolean booleanValue = ((Boolean) enhancedNullability.component1()).booleanValue();
        Annotations component22 = enhancedNullability.component2();
        Object obj2 = (obj == null && component22 == null) ? null : 1;
        i3 -= i;
        if (obj2 == null) {
            return new SimpleResult(simpleType2, i3, false);
        }
        int i5 = 0;
        SimpleType simpleType3 = KotlinTypeFactory.simpleType(compositeAnnotationsOrSingle(acnz.h((Iterable) acnr.b((Object[]) new Annotations[]{simpleType.getAnnotations(), component2, component22}))), typeConstructor, list, booleanValue);
        if (javaTypeQualifiers.isNotNullTypeParameter$descriptors_jvm()) {
            simpleType3 = new NotNullTypeParameter(simpleType3);
        }
        if (component22 != null && javaTypeQualifiers.isNullabilityQualifierForWarning$descriptors_jvm()) {
            i5 = 1;
        }
        UnwrappedType wrapEnhancement = i5 != 0 ? TypeWithEnhancementKt.wrapEnhancement(simpleType2, simpleType3) : simpleType3;
        if (wrapEnhancement != null) {
            return new SimpleResult((SimpleType) wrapEnhancement, i3, true);
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }

    private static final Annotations compositeAnnotationsOrSingle(List<? extends Annotations> list) {
        switch (list.size()) {
            case 0:
                throw new IllegalStateException("At least one Annotations object expected".toString());
            case 1:
                return (Annotations) acnz.i((List) list);
            default:
                return new CompositeAnnotations(acnz.k((Iterable) list));
        }
    }

    private static final boolean shouldEnhance(TypeComponentPosition typeComponentPosition) {
        return typeComponentPosition != TypeComponentPosition.INFLEXIBLE;
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult(t, null);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    private static final EnhancementResult<ClassifierDescriptor> enhanceMutability(ClassifierDescriptor classifierDescriptor, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(classifierDescriptor);
        }
        if (!(classifierDescriptor instanceof ClassDescriptor)) {
            return noChange(classifierDescriptor);
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
        if (mutability != null) {
            ClassDescriptor classDescriptor;
            switch (WhenMappings.$EnumSwitchMapping$0[mutability.ordinal()]) {
                case 1:
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_LOWER) {
                        classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isMutable(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMap.convertMutableToReadOnly(classDescriptor));
                        }
                    }
                    break;
                case 2:
                    if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_UPPER) {
                        classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isReadOnly(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMap.convertReadOnlyToMutable(classDescriptor));
                        }
                    }
                    break;
            }
        }
        return noChange(classifierDescriptor);
    }

    private static final EnhancementResult<Boolean> getEnhancedNullability(KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        }
        NullabilityQualifier nullability = javaTypeQualifiers.getNullability();
        if (nullability != null) {
            switch (WhenMappings.$EnumSwitchMapping$1[nullability.ordinal()]) {
                case 1:
                    return enhancedNullability(Boolean.TRUE);
                case 2:
                    return enhancedNullability(Boolean.FALSE);
            }
        }
        return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
    }
}
