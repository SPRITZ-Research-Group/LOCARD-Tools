package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.aa;
import kotlin.k;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.v;

public final class CapturedTypeApproximationKt {

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Variance.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$0[Variance.OUT_VARIANCE.ordinal()] = 3;
            iArr = new int[Variance.values().length];
            $EnumSwitchMapping$1 = iArr;
            iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 2;
        }
    }

    private static final TypeProjection toTypeProjection(TypeArgument typeArgument) {
        boolean isConsistent = typeArgument.isConsistent();
        if (!aa.a || isConsistent) {
            CapturedTypeApproximationKt$toTypeProjection$2 capturedTypeApproximationKt$toTypeProjection$2 = new CapturedTypeApproximationKt$toTypeProjection$2(typeArgument);
            if (acry.a(typeArgument.getInProjection(), typeArgument.getOutProjection())) {
                return new TypeProjectionImpl(typeArgument.getInProjection());
            }
            if (KotlinBuiltIns.isNothing(typeArgument.getInProjection()) && typeArgument.getTypeParameter().getVariance() != Variance.IN_VARIANCE) {
                return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
            }
            if (KotlinBuiltIns.isNullableAny(typeArgument.getOutProjection())) {
                return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.IN_VARIANCE), typeArgument.getInProjection());
            }
            return new TypeProjectionImpl(capturedTypeApproximationKt$toTypeProjection$2.invoke(Variance.OUT_VARIANCE), typeArgument.getOutProjection());
        }
        DescriptorRenderer withOptions = DescriptorRenderer.Companion.withOptions(CapturedTypeApproximationKt$toTypeProjection$1$descriptorRenderer$1.INSTANCE);
        StringBuilder stringBuilder = new StringBuilder("Only consistent enhanced type projection can be converted to type projection, but [");
        stringBuilder.append(withOptions.render(typeArgument.getTypeParameter()));
        stringBuilder.append(": <");
        stringBuilder.append(withOptions.renderType(typeArgument.getInProjection()));
        stringBuilder.append(", ");
        stringBuilder.append(withOptions.renderType(typeArgument.getOutProjection()));
        stringBuilder.append(">] was found");
        throw new AssertionError(stringBuilder.toString());
    }

    private static final TypeArgument toTypeArgument(TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        switch (WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameterDescriptor.getVariance(), typeProjection).ordinal()]) {
            case 1:
                return new TypeArgument(typeParameterDescriptor, typeProjection.getType(), typeProjection.getType());
            case 2:
                return new TypeArgument(typeParameterDescriptor, typeProjection.getType(), DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNullableAnyType());
            case 3:
                return new TypeArgument(typeParameterDescriptor, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getNothingType(), typeProjection.getType());
            default:
                throw new k();
        }
    }

    public static final TypeProjection approximateCapturedTypesIfNecessary(TypeProjection typeProjection, boolean z) {
        if (typeProjection == null) {
            return null;
        }
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        if (!TypeUtils.contains(type, CapturedTypeApproximationKt$approximateCapturedTypesIfNecessary$1.INSTANCE)) {
            return typeProjection;
        }
        Variance projectionKind = typeProjection.getProjectionKind();
        if (projectionKind == Variance.OUT_VARIANCE) {
            return new TypeProjectionImpl(projectionKind, (KotlinType) approximateCapturedTypes(type).getUpper());
        }
        if (z) {
            return new TypeProjectionImpl(projectionKind, (KotlinType) approximateCapturedTypes(type).getLower());
        }
        return substituteCapturedTypesWithProjections(typeProjection);
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection) {
        return TypeSubstitutor.create((TypeSubstitution) new CapturedTypeApproximationKt$substituteCapturedTypesWithProjections$typeSubstitutor$1()).substituteWithoutApproximation(typeProjection);
    }

    public static final ApproximationBounds<KotlinType> approximateCapturedTypes(KotlinType kotlinType) {
        if (FlexibleTypesKt.isFlexible(kotlinType)) {
            ApproximationBounds approximateCapturedTypes = approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(kotlinType));
            ApproximationBounds approximateCapturedTypes2 = approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(kotlinType));
            return new ApproximationBounds(TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible((KotlinType) approximateCapturedTypes.getLower()), FlexibleTypesKt.upperIfFlexible((KotlinType) approximateCapturedTypes2.getLower())), kotlinType), TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible((KotlinType) approximateCapturedTypes.getUpper()), FlexibleTypesKt.upperIfFlexible((KotlinType) approximateCapturedTypes2.getUpper())), kotlinType));
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (CapturedTypeConstructorKt.isCaptured(kotlinType)) {
            if (constructor != null) {
                TypeProjection typeProjection = ((CapturedTypeConstructor) constructor).getTypeProjection();
                CapturedTypeApproximationKt$approximateCapturedTypes$1 capturedTypeApproximationKt$approximateCapturedTypes$1 = new CapturedTypeApproximationKt$approximateCapturedTypes$1(kotlinType);
                KotlinType invoke = capturedTypeApproximationKt$approximateCapturedTypes$1.invoke(typeProjection.getType());
                switch (WhenMappings.$EnumSwitchMapping$1[typeProjection.getProjectionKind().ordinal()]) {
                    case 1:
                        return new ApproximationBounds(invoke, TypeUtilsKt.getBuiltIns(kotlinType).getNullableAnyType());
                    case 2:
                        return new ApproximationBounds(capturedTypeApproximationKt$approximateCapturedTypes$1.invoke((KotlinType) TypeUtilsKt.getBuiltIns(kotlinType).getNothingType()), invoke);
                    default:
                        throw new AssertionError("Only nontrivial projections should have been captured, not: ".concat(String.valueOf(typeProjection)));
                }
            }
            throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
        } else if (kotlinType.getArguments().isEmpty() || kotlinType.getArguments().size() != constructor.getParameters().size()) {
            return new ApproximationBounds(kotlinType, kotlinType);
        } else {
            TypeArgument toTypeArgument;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (m mVar : acnz.d((Iterable) kotlinType.getArguments(), (Iterable) constructor.getParameters())) {
                TypeProjection typeProjection2 = (TypeProjection) mVar.c();
                toTypeArgument = toTypeArgument(typeProjection2, (TypeParameterDescriptor) mVar.d());
                if (typeProjection2.isStarProjection()) {
                    arrayList.add(toTypeArgument);
                    arrayList2.add(toTypeArgument);
                } else {
                    ApproximationBounds approximateProjection = approximateProjection(toTypeArgument);
                    TypeArgument typeArgument = (TypeArgument) approximateProjection.component1();
                    toTypeArgument = (TypeArgument) approximateProjection.component2();
                    arrayList.add(typeArgument);
                    arrayList2.add(toTypeArgument);
                }
            }
            Iterable<TypeArgument> iterable = arrayList;
            int i = 1;
            if (!((Collection) iterable).isEmpty()) {
                for (TypeArgument toTypeArgument2 : iterable) {
                    if ((toTypeArgument2.isConsistent() ^ 1) != 0) {
                        break;
                    }
                }
            }
            i = 0;
            return new ApproximationBounds(i != 0 ? TypeUtilsKt.getBuiltIns(kotlinType).getNothingType() : replaceTypeArguments(kotlinType, arrayList), replaceTypeArguments(kotlinType, arrayList2));
        }
    }

    private static final KotlinType replaceTypeArguments(KotlinType kotlinType, List<TypeArgument> list) {
        Object obj = kotlinType.getArguments().size() == list.size() ? 1 : null;
        if (aa.a && obj == null) {
            throw new AssertionError("Incorrect type arguments ".concat(String.valueOf(list)));
        }
        Iterable<TypeArgument> iterable = list;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (TypeArgument toTypeProjection : iterable) {
            arrayList.add(toTypeProjection(toTypeProjection));
        }
        return TypeSubstitutionKt.replace$default(kotlinType, (List) arrayList, null, 2, null);
    }

    private static final ApproximationBounds<TypeArgument> approximateProjection(TypeArgument typeArgument) {
        ApproximationBounds approximateCapturedTypes = approximateCapturedTypes(typeArgument.getInProjection());
        KotlinType kotlinType = (KotlinType) approximateCapturedTypes.component1();
        KotlinType kotlinType2 = (KotlinType) approximateCapturedTypes.component2();
        ApproximationBounds approximateCapturedTypes2 = approximateCapturedTypes(typeArgument.getOutProjection());
        return new ApproximationBounds(new TypeArgument(typeArgument.getTypeParameter(), kotlinType2, (KotlinType) approximateCapturedTypes2.component1()), new TypeArgument(typeArgument.getTypeParameter(), kotlinType, (KotlinType) approximateCapturedTypes2.component2()));
    }
}
