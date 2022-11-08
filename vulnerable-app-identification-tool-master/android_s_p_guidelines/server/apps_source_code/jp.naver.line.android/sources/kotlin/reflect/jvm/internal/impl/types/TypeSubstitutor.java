package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acqr;
import java.util.ArrayList;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;

public class TypeSubstitutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final TypeSubstitutor EMPTY = create(TypeSubstitution.EMPTY);
    private final TypeSubstitution substitution;

    final class SubstitutionException extends Exception {
        public SubstitutionException(String str) {
            super(str);
        }
    }

    enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION
    }

    public static TypeSubstitutor create(TypeSubstitution typeSubstitution) {
        return new TypeSubstitutor(typeSubstitution);
    }

    public static TypeSubstitutor createChainedSubstitutor(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        return create(DisjointKeysUnionTypeSubstitution.create(typeSubstitution, typeSubstitution2));
    }

    public static TypeSubstitutor create(KotlinType kotlinType) {
        return create(TypeConstructorSubstitution.create(kotlinType.getConstructor(), kotlinType.getArguments()));
    }

    protected TypeSubstitutor(TypeSubstitution typeSubstitution) {
        this.substitution = typeSubstitution;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    public TypeSubstitution getSubstitution() {
        return this.substitution;
    }

    public KotlinType safeSubstitute(KotlinType kotlinType, Variance variance) {
        if (isEmpty()) {
            return kotlinType;
        }
        try {
            return unsafeSubstitute(new TypeProjectionImpl(variance, kotlinType), 0).getType();
        } catch (SubstitutionException e) {
            return ErrorUtils.createErrorType(e.getMessage());
        }
    }

    public KotlinType substitute(KotlinType kotlinType, Variance variance) {
        TypeProjection substitute = substitute(new TypeProjectionImpl(variance, getSubstitution().prepareTopLevelType(kotlinType, variance)));
        if (substitute == null) {
            return null;
        }
        return substitute.getType();
    }

    public TypeProjection substitute(TypeProjection typeProjection) {
        typeProjection = substituteWithoutApproximation(typeProjection);
        if (this.substitution.approximateCapturedTypes() || this.substitution.approximateContravariantCapturedTypes()) {
            return CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(typeProjection, this.substitution.approximateContravariantCapturedTypes());
        }
        return typeProjection;
    }

    public kotlin.reflect.jvm.internal.impl.types.TypeProjection substituteWithoutApproximation(kotlin.reflect.jvm.internal.impl.types.TypeProjection r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.substituteWithoutApproximation(kotlin.reflect.jvm.internal.impl.types.TypeProjection):kotlin.reflect.jvm.internal.impl.types.TypeProjection. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = this;
        r0 = r1.isEmpty();
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return r2;
    L_0x0007:
        r0 = 0;
        r2 = r1.unsafeSubstitute(r2, r0);	 Catch:{ SubstitutionException -> 0x000d }
        return r2;
    L_0x000d:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.substituteWithoutApproximation(kotlin.reflect.jvm.internal.impl.types.TypeProjection):kotlin.reflect.jvm.internal.impl.types.TypeProjection");
    }

    private TypeProjection unsafeSubstitute(TypeProjection typeProjection, int i) throws SubstitutionException {
        assertRecursionDepth(i, typeProjection, this.substitution);
        if (typeProjection.isStarProjection()) {
            return typeProjection;
        }
        KotlinType type = typeProjection.getType();
        TypeProjection unsafeSubstitute;
        if (type instanceof TypeWithEnhancement) {
            TypeWithEnhancement typeWithEnhancement = (TypeWithEnhancement) type;
            KotlinType origin = typeWithEnhancement.getOrigin();
            type = typeWithEnhancement.getEnhancement();
            unsafeSubstitute = unsafeSubstitute(new TypeProjectionImpl(typeProjection.getProjectionKind(), origin), i + 1);
            return new TypeProjectionImpl(unsafeSubstitute.getProjectionKind(), TypeWithEnhancementKt.wrapEnhancement(unsafeSubstitute.getType().unwrap(), substitute(type, typeProjection.getProjectionKind())));
        } else if (DynamicTypesKt.isDynamic(type) || (type.unwrap() instanceof RawType)) {
            return typeProjection;
        } else {
            TypeProjection typeProjection2 = this.substitution.get(type);
            Variance projectionKind = typeProjection.getProjectionKind();
            if (typeProjection2 == null && FlexibleTypesKt.isFlexible(type) && !TypeCapabilitiesKt.isCustomTypeVariable(type)) {
                FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(type);
                i++;
                typeProjection2 = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getLowerBound()), i);
                unsafeSubstitute = unsafeSubstitute(new TypeProjectionImpl(projectionKind, asFlexibleType.getUpperBound()), i);
                Variance projectionKind2 = typeProjection2.getProjectionKind();
                if (typeProjection2.getType() == asFlexibleType.getLowerBound() && unsafeSubstitute.getType() == asFlexibleType.getUpperBound()) {
                    return typeProjection;
                }
                return new TypeProjectionImpl(projectionKind2, KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(typeProjection2.getType()), TypeSubstitutionKt.asSimpleType(unsafeSubstitute.getType())));
            } else if (KotlinBuiltIns.isNothing(type) || KotlinTypeKt.isError(type)) {
                return typeProjection;
            } else {
                if (typeProjection2 == null) {
                    return substituteCompoundType(typeProjection, i);
                }
                VarianceConflictType conflictType = conflictType(projectionKind, typeProjection2.getProjectionKind());
                if (!CapturedTypeConstructorKt.isCaptured(type)) {
                    switch (conflictType) {
                        case OUT_IN_IN_POSITION:
                            throw new SubstitutionException("Out-projection in in-position");
                        case IN_IN_OUT_POSITION:
                            return new TypeProjectionImpl(Variance.OUT_VARIANCE, type.getConstructor().getBuiltIns().getNullableAnyType());
                    }
                }
                CustomTypeVariable customTypeVariable = TypeCapabilitiesKt.getCustomTypeVariable(type);
                if (typeProjection2.isStarProjection()) {
                    return typeProjection2;
                }
                KotlinType substitutionResult;
                if (customTypeVariable != null) {
                    substitutionResult = customTypeVariable.substitutionResult(typeProjection2.getType());
                } else {
                    substitutionResult = TypeUtils.makeNullableIfNeeded(typeProjection2.getType(), type.isMarkedNullable());
                }
                if (!type.getAnnotations().isEmpty()) {
                    Annotations filterOutUnsafeVariance = filterOutUnsafeVariance(this.substitution.filterAnnotations(type.getAnnotations()));
                    substitutionResult = TypeUtilsKt.replaceAnnotations(substitutionResult, new CompositeAnnotations(substitutionResult.getAnnotations(), filterOutUnsafeVariance));
                }
                if (conflictType == VarianceConflictType.NO_CONFLICT) {
                    projectionKind = combine(projectionKind, typeProjection2.getProjectionKind());
                }
                return new TypeProjectionImpl(projectionKind, substitutionResult);
            }
        }
    }

    private static Annotations filterOutUnsafeVariance(Annotations annotations) {
        if (annotations.hasAnnotation(KotlinBuiltIns.FQ_NAMES.unsafeVariance)) {
            return new FilteredAnnotations(annotations, new acqr<FqName, Boolean>() {
                public final Boolean invoke(FqName fqName) {
                    return Boolean.valueOf(fqName.equals(KotlinBuiltIns.FQ_NAMES.unsafeVariance) ^ 1);
                }
            });
        }
        return annotations;
    }

    private TypeProjection substituteCompoundType(TypeProjection typeProjection, int i) throws SubstitutionException {
        KotlinType type = typeProjection.getType();
        Variance projectionKind = typeProjection.getProjectionKind();
        if (type.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return typeProjection;
        }
        KotlinType kotlinType = null;
        KotlinType abbreviation = SpecialTypesKt.getAbbreviation(type);
        if (abbreviation != null) {
            kotlinType = substitute(abbreviation, Variance.INVARIANT);
        }
        KotlinType replace = TypeSubstitutionKt.replace(type, substituteTypeArguments(type.getConstructor().getParameters(), type.getArguments(), i), this.substitution.filterAnnotations(type.getAnnotations()));
        if ((replace instanceof SimpleType) && (kotlinType instanceof SimpleType)) {
            replace = SpecialTypesKt.withAbbreviation((SimpleType) replace, (SimpleType) kotlinType);
        }
        return new TypeProjectionImpl(projectionKind, replace);
    }

    private List<TypeProjection> substituteTypeArguments(List<TypeParameterDescriptor> list, List<TypeProjection> list2, int i) throws SubstitutionException {
        List<TypeProjection> arrayList = new ArrayList(list.size());
        Object obj = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) list.get(i2);
            TypeProjection typeProjection = (TypeProjection) list2.get(i2);
            TypeProjection unsafeSubstitute = unsafeSubstitute(typeProjection, i + 1);
            switch (conflictType(typeParameterDescriptor.getVariance(), unsafeSubstitute.getProjectionKind())) {
                case OUT_IN_IN_POSITION:
                case IN_IN_OUT_POSITION:
                    unsafeSubstitute = TypeUtils.makeStarProjection(typeParameterDescriptor);
                    break;
                case NO_CONFLICT:
                    if (!(typeParameterDescriptor.getVariance() == Variance.INVARIANT || unsafeSubstitute.isStarProjection())) {
                        unsafeSubstitute = new TypeProjectionImpl(Variance.INVARIANT, unsafeSubstitute.getType());
                        break;
                    }
            }
            if (unsafeSubstitute != typeProjection) {
                obj = 1;
            }
            arrayList.add(unsafeSubstitute);
        }
        return obj == null ? list2 : arrayList;
    }

    public static Variance combine(Variance variance, TypeProjection typeProjection) {
        if (typeProjection.isStarProjection()) {
            return Variance.OUT_VARIANCE;
        }
        return combine(variance, typeProjection.getProjectionKind());
    }

    public static Variance combine(Variance variance, Variance variance2) {
        if (variance == Variance.INVARIANT) {
            return variance2;
        }
        if (variance2 == Variance.INVARIANT) {
            return variance;
        }
        if (variance == variance2) {
            return variance2;
        }
        StringBuilder stringBuilder = new StringBuilder("Variance conflict: type parameter variance '");
        stringBuilder.append(variance);
        stringBuilder.append("' and projection kind '");
        stringBuilder.append(variance2);
        stringBuilder.append("' cannot be combined");
        throw new AssertionError(stringBuilder.toString());
    }

    private static VarianceConflictType conflictType(Variance variance, Variance variance2) {
        if (variance == Variance.IN_VARIANCE && variance2 == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        if (variance == Variance.OUT_VARIANCE && variance2 == Variance.IN_VARIANCE) {
            return VarianceConflictType.IN_IN_OUT_POSITION;
        }
        return VarianceConflictType.NO_CONFLICT;
    }

    private static void assertRecursionDepth(int i, TypeProjection typeProjection, TypeSubstitution typeSubstitution) {
        if (i > 100) {
            StringBuilder stringBuilder = new StringBuilder("Recursion too deep. Most likely infinite loop while substituting ");
            stringBuilder.append(safeToString(typeProjection));
            stringBuilder.append("; substitution: ");
            stringBuilder.append(safeToString(typeSubstitution));
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private static String safeToString(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (ExceptionUtilsKt.isProcessCanceledException(th)) {
                RuntimeException runtimeException = (RuntimeException) th;
            } else {
                StringBuilder stringBuilder = new StringBuilder("[Exception while computing toString(): ");
                stringBuilder.append(th);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        }
    }
}
