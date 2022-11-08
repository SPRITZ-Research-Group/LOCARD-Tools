package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acqr;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.aa;
import kotlin.k;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionN;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.NullDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.StringDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import kotlin.u;
import kotlin.v;

public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final Jsr305State jsr305State;

    class PartEnhancementResult {
        private final boolean containsFunctionN;
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(KotlinType kotlinType, boolean z, boolean z2) {
            this.type = kotlinType;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    final class SignatureParts {
        private final QualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        private final Annotated typeContainer;

        public SignatureParts(Annotated annotated, KotlinType kotlinType, Collection<? extends KotlinType> collection, boolean z, LazyJavaResolverContext lazyJavaResolverContext, QualifierApplicabilityType qualifierApplicabilityType) {
            this.typeContainer = annotated;
            this.fromOverride = kotlinType;
            this.fromOverridden = collection;
            this.isCovariant = z;
            this.containerContext = lazyJavaResolverContext;
            this.containerApplicabilityType = qualifierApplicabilityType;
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            KotlinType kotlinType = null;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            if (valueParameterDescriptor != null) {
                kotlinType = valueParameterDescriptor.getVarargElementType();
            }
            return kotlinType != null;
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        public final PartEnhancementResult enhance(TypeEnhancementInfo typeEnhancementInfo) {
            acqr computeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride();
            acqr signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = typeEnhancementInfo != null ? new SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1(typeEnhancementInfo, computeIndexedQualifiersForOverride) : null;
            boolean contains = TypeUtils.contains(this.fromOverride, SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1.INSTANCE);
            KotlinType kotlinType = this.fromOverride;
            if (signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 == null) {
                signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = computeIndexedQualifiersForOverride;
            }
            KotlinType enhance = TypeEnhancementKt.enhance(kotlinType, signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1);
            if (enhance != null) {
                return new PartEnhancementResult(enhance, true, contains);
            }
            return new PartEnhancementResult(this.fromOverride, false, contains);
        }

        private final JavaTypeQualifiers extractQualifiers(KotlinType kotlinType) {
            m mVar;
            NullabilityQualifier nullabilityQualifier;
            NullabilityQualifier nullabilityQualifier2;
            MutabilityQualifier mutabilityQualifier;
            if (FlexibleTypesKt.isFlexible(kotlinType)) {
                FlexibleType asFlexibleType = FlexibleTypesKt.asFlexibleType(kotlinType);
                mVar = new m(asFlexibleType.getLowerBound(), asFlexibleType.getUpperBound());
            } else {
                mVar = new m(kotlinType, kotlinType);
            }
            KotlinType kotlinType2 = (KotlinType) mVar.c();
            KotlinType kotlinType3 = (KotlinType) mVar.d();
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            if (kotlinType2.isMarkedNullable()) {
                nullabilityQualifier = NullabilityQualifier.NULLABLE;
            } else if (kotlinType3.isMarkedNullable()) {
                nullabilityQualifier2 = null;
                mutabilityQualifier = javaToKotlinClassMap.isReadOnly(kotlinType2) ? MutabilityQualifier.READ_ONLY : javaToKotlinClassMap.isMutable(kotlinType3) ? MutabilityQualifier.MUTABLE : null;
                return new JavaTypeQualifiers(nullabilityQualifier2, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
            } else {
                nullabilityQualifier = NullabilityQualifier.NOT_NULL;
            }
            nullabilityQualifier2 = nullabilityQualifier;
            if (javaToKotlinClassMap.isReadOnly(kotlinType2)) {
            }
            return new JavaTypeQualifiers(nullabilityQualifier2, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
        }

        private final JavaTypeQualifiers extractQualifiersFromAnnotations(KotlinType kotlinType, boolean z, JavaTypeQualifiers javaTypeQualifiers) {
            Annotations annotations;
            if (!z || this.typeContainer == null) {
                annotations = kotlinType.getAnnotations();
            } else {
                annotations = AnnotationsKt.composeAnnotations(this.typeContainer.getAnnotations(), kotlinType.getAnnotations());
            }
            SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 = new SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1(annotations);
            SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 = SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.INSTANCE;
            NullabilityQualifier nullabilityQualifier = null;
            if (z) {
                JavaTypeQualifiersByElementType defaultTypeQualifiers = this.containerContext.getDefaultTypeQualifiers();
                javaTypeQualifiers = defaultTypeQualifiers != null ? defaultTypeQualifiers.get(this.containerApplicabilityType) : null;
            }
            NullabilityQualifierWithMigrationStatus extractNullability = extractNullability(annotations);
            if (extractNullability == null) {
                extractNullability = (javaTypeQualifiers == null || javaTypeQualifiers.getNullability() == null) ? null : new NullabilityQualifierWithMigrationStatus(javaTypeQualifiers.getNullability(), javaTypeQualifiers.isNullabilityQualifierForWarning$descriptors_jvm());
            }
            NullabilityQualifier qualifier = extractNullability != null ? extractNullability.getQualifier() : null;
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.invoke(signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1.invoke(JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS(), MutabilityQualifier.READ_ONLY), signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1.invoke(JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS(), MutabilityQualifier.MUTABLE));
            if (extractNullability != null) {
                nullabilityQualifier = extractNullability.getQualifier();
            }
            boolean z2 = false;
            boolean z3 = nullabilityQualifier == NullabilityQualifier.NOT_NULL && TypeUtilsKt.isTypeParameter(kotlinType);
            if (extractNullability != null && extractNullability.isForWarningOnly()) {
                z2 = true;
            }
            return new JavaTypeQualifiers(qualifier, mutabilityQualifier, z3, z2);
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(Annotations annotations) {
            Iterable<AnnotationDescriptor> iterable = annotations;
            SignatureEnhancement signatureEnhancement = SignatureEnhancement.this;
            for (AnnotationDescriptor extractNullability : iterable) {
                NullabilityQualifierWithMigrationStatus extractNullability2 = signatureEnhancement.extractNullability(extractNullability);
                if (extractNullability2 != null) {
                    return extractNullability2;
                }
            }
            return null;
        }

        private final acqr<Integer, JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            Object obj;
            int i;
            JavaTypeQualifiers[] javaTypeQualifiersArr;
            int length;
            int i2;
            boolean z;
            Object obj2;
            TypeAndDefaultQualifiers typeAndDefaultQualifiers;
            KotlinType component1;
            JavaTypeQualifiers component2;
            Collection arrayList;
            TypeAndDefaultQualifiers typeAndDefaultQualifiers2;
            Object type;
            Iterable<KotlinType> iterable = this.fromOverridden;
            Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable, 10));
            for (KotlinType toIndexed : iterable) {
                arrayList2.add(toIndexed(toIndexed));
            }
            List<List> list = (List) arrayList2;
            List toIndexed2 = toIndexed(this.fromOverride);
            if (this.isCovariant) {
                Iterable<KotlinType> iterable2 = this.fromOverridden;
                if (!((iterable2 instanceof Collection) && ((Collection) iterable2).isEmpty())) {
                    for (KotlinType equalTypes : iterable2) {
                        if ((KotlinTypeChecker.DEFAULT.equalTypes(equalTypes, this.fromOverride) ^ 1) != 0) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj != null) {
                    obj = 1;
                    if (obj == null) {
                        i = 1;
                    } else {
                        i = toIndexed2.size();
                    }
                    javaTypeQualifiersArr = new JavaTypeQualifiers[i];
                    length = javaTypeQualifiersArr.length;
                    i2 = 0;
                    while (i2 < length) {
                        z = i2 != 0;
                        obj2 = (z || obj == null) ? 1 : null;
                        if (aa.a || obj2 != null) {
                            typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) toIndexed2.get(i2);
                            component1 = typeAndDefaultQualifiers.component1();
                            component2 = typeAndDefaultQualifiers.component2();
                            arrayList = new ArrayList();
                            for (List b : list) {
                                typeAndDefaultQualifiers2 = (TypeAndDefaultQualifiers) acnz.b(b, i2);
                                type = typeAndDefaultQualifiers2 == null ? typeAndDefaultQualifiers2.getType() : null;
                                if (type != null) {
                                    arrayList.add(type);
                                }
                            }
                            javaTypeQualifiersArr[i2] = computeQualifiersForOverride(component1, (List) arrayList, component2, z);
                            i2++;
                        } else {
                            throw new AssertionError("Only head type constructors should be computed");
                        }
                    }
                    return new SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1(javaTypeQualifiersArr);
                }
            }
            obj = null;
            if (obj == null) {
                i = toIndexed2.size();
            } else {
                i = 1;
            }
            javaTypeQualifiersArr = new JavaTypeQualifiers[i];
            length = javaTypeQualifiersArr.length;
            i2 = 0;
            while (i2 < length) {
                if (i2 != 0) {
                }
                if (!z) {
                }
                if (aa.a) {
                }
                typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) toIndexed2.get(i2);
                component1 = typeAndDefaultQualifiers.component1();
                component2 = typeAndDefaultQualifiers.component2();
                arrayList = new ArrayList();
                while (r11.hasNext()) {
                    typeAndDefaultQualifiers2 = (TypeAndDefaultQualifiers) acnz.b(b, i2);
                    if (typeAndDefaultQualifiers2 == null) {
                    }
                    if (type != null) {
                        arrayList.add(type);
                    }
                }
                javaTypeQualifiersArr[i2] = computeQualifiersForOverride(component1, (List) arrayList, component2, z);
                i2++;
            }
            return new SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1(javaTypeQualifiersArr);
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(KotlinType kotlinType) {
            ArrayList arrayList = new ArrayList(1);
            new SignatureEnhancement$SignatureParts$toIndexed$1(arrayList).invoke(kotlinType, this.containerContext);
            return arrayList;
        }

        private final JavaTypeQualifiers computeQualifiersForOverride(KotlinType kotlinType, Collection<? extends KotlinType> collection, JavaTypeQualifiers javaTypeQualifiers, boolean z) {
            NullabilityQualifier nullability;
            boolean z2;
            Iterable<KotlinType> iterable = collection;
            Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
            for (KotlinType extractQualifiers : iterable) {
                arrayList.add(extractQualifiers(extractQualifiers));
            }
            Iterable<JavaTypeQualifiers> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList();
            for (JavaTypeQualifiers mutability : iterable2) {
                MutabilityQualifier mutability2 = mutability.getMutability();
                if (mutability2 != null) {
                    arrayList2.add(mutability2);
                }
            }
            Set m = acnz.m((List) arrayList2);
            Collection arrayList3 = new ArrayList();
            for (JavaTypeQualifiers nullability2 : iterable2) {
                nullability = nullability2.getNullability();
                if (nullability != null) {
                    arrayList3.add(nullability);
                }
            }
            Object m2 = acnz.m((List) arrayList3);
            Collection arrayList4 = new ArrayList();
            for (KotlinType unwrapEnhancement : iterable) {
                nullability = extractQualifiers(TypeWithEnhancementKt.unwrapEnhancement(unwrapEnhancement)).getNullability();
                if (nullability != null) {
                    arrayList4.add(nullability);
                }
            }
            Object m3 = acnz.m((List) arrayList4);
            JavaTypeQualifiers extractQualifiersFromAnnotations = extractQualifiersFromAnnotations(kotlinType, z, javaTypeQualifiers);
            boolean z3 = true;
            nullability = null;
            javaTypeQualifiers = (extractQualifiersFromAnnotations.isNullabilityQualifierForWarning$descriptors_jvm() ^ 1) != 0 ? extractQualifiersFromAnnotations : null;
            NullabilityQualifier nullability3 = javaTypeQualifiers != null ? javaTypeQualifiers.getNullability() : null;
            NullabilityQualifier nullability4 = extractQualifiersFromAnnotations.getNullability();
            boolean z4 = this.isCovariant && z;
            NullabilityQualifier access$select = SignatureEnhancementKt.select(m2, nullability3, z4);
            if (access$select != null) {
                Object obj = (isForVarargParameter() && z && access$select == NullabilityQualifier.NULLABLE) ? 1 : null;
                if (obj == null) {
                    nullability = access$select;
                }
            }
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) SignatureEnhancementKt.select(m, MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, extractQualifiersFromAnnotations.getMutability(), z4);
            Object obj2 = (nullability4 == nullability3 && (acry.a(m3, m2) ^ 1) == 0) ? null : 1;
            if (!extractQualifiersFromAnnotations.isNotNullTypeParameter$descriptors_jvm()) {
                Object obj3;
                if (!((Collection) iterable2).isEmpty()) {
                    for (JavaTypeQualifiers isNotNullTypeParameter$descriptors_jvm : iterable2) {
                        if (isNotNullTypeParameter$descriptors_jvm.isNotNullTypeParameter$descriptors_jvm()) {
                            obj3 = 1;
                            break;
                        }
                    }
                }
                obj3 = null;
                if (obj3 == null) {
                    z2 = false;
                    if (nullability != null && obj2 != null) {
                        return SignatureEnhancementKt.createJavaTypeQualifiers(SignatureEnhancementKt.select(m3, nullability4, z4), mutabilityQualifier, true, z2);
                    }
                    if (nullability != null) {
                        z3 = false;
                    }
                    return SignatureEnhancementKt.createJavaTypeQualifiers(nullability, mutabilityQualifier, z3, z2);
                }
            }
            z2 = true;
            if (nullability != null) {
            }
            if (nullability != null) {
                z3 = false;
            }
            return SignatureEnhancementKt.createJavaTypeQualifiers(nullability, mutabilityQualifier, z3, z2);
        }
    }

    final class ValueParameterEnhancementResult extends PartEnhancementResult {
        private final boolean hasDefaultValue;

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        public ValueParameterEnhancementResult(KotlinType kotlinType, boolean z, boolean z2, boolean z3) {
            super(kotlinType, z2, z3);
            this.hasDefaultValue = z;
        }
    }

    public SignatureEnhancement(AnnotationTypeQualifierResolver annotationTypeQualifierResolver, Jsr305State jsr305State) {
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.jsr305State = jsr305State;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(AnnotationDescriptor annotationDescriptor) {
        ConstantValue firstArgument = DescriptorUtilsKt.firstArgument(annotationDescriptor);
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode != 73135176) {
            if (hashCode != 74175084) {
                if (hashCode != 433141802) {
                    if (hashCode == 1933739535 && asString.equals("ALWAYS")) {
                        return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
                    }
                } else if (asString.equals("UNKNOWN")) {
                    return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
                }
            }
            return null;
        }
        return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
    }

    public final NullabilityQualifierWithMigrationStatus extractNullability(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(annotationDescriptor);
        if (extractNullabilityFromKnownAnnotations != null) {
            return extractNullabilityFromKnownAnnotations;
        }
        AnnotationDescriptor resolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (resolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel resolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (resolveJsr305AnnotationState.isIgnore()) {
            return null;
        }
        extractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(resolveTypeQualifierAnnotation);
        if (extractNullabilityFromKnownAnnotations != null) {
            return NullabilityQualifierWithMigrationStatus.copy$default(extractNullabilityFromKnownAnnotations, null, resolveJsr305AnnotationState.isWarning(), 1, null);
        }
        return null;
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor) {
        Object fqName = annotationDescriptor.getFqName();
        if (fqName == null) {
            return null;
        }
        if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        }
        if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }
        if (acry.a(fqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
            return extractNullabilityTypeFromArgument(annotationDescriptor);
        }
        if (acry.a(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        }
        if (acry.a(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }
        if (acry.a(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
        }
        if (acry.a(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true);
        }
        return null;
    }

    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(LazyJavaResolverContext lazyJavaResolverContext, Collection<? extends D> collection) {
        Iterable<CallableMemberDescriptor> iterable = collection;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (CallableMemberDescriptor enhanceSignature : iterable) {
            arrayList.add(enhanceSignature(enhanceSignature, lazyJavaResolverContext));
        }
        return (List) arrayList;
    }

    private final <D extends CallableMemberDescriptor> D enhanceSignature(D d, LazyJavaResolverContext lazyJavaResolverContext) {
        SignatureEnhancement signatureEnhancement = this;
        D d2 = d;
        if (!(d2 instanceof JavaCallableMemberDescriptor)) {
            return d2;
        }
        JavaCallableMemberDescriptor javaCallableMemberDescriptor = (JavaCallableMemberDescriptor) d2;
        if (javaCallableMemberDescriptor.getKind() == Kind.FAKE_OVERRIDE && javaCallableMemberDescriptor.getOriginal().getOverriddenDescriptors().size() == 1) {
            return d2;
        }
        CallableMemberDescriptor callableMemberDescriptor;
        FunctionDescriptor functionDescriptor;
        PartEnhancementResult enhance$default;
        JavaMethodDescriptor javaMethodDescriptor;
        SignatureBuildingComponents signatureBuildingComponents;
        DeclarationDescriptor containingDeclaration;
        String signature;
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo;
        Object obj;
        StringBuilder stringBuilder;
        Iterable<ValueParameterDescriptor> valueParameters;
        Collection arrayList;
        SignatureParts partsForValueParameter;
        List parametersInfo;
        TypeEnhancementInfo typeEnhancementInfo;
        PartEnhancementResult enhance;
        boolean hasDefaultValueInAnnotation;
        boolean z;
        List list;
        Annotated annotated;
        PropertyDescriptor propertyDescriptor;
        QualifierApplicabilityType qualifierApplicabilityType;
        PartEnhancementResult enhance2;
        Iterable<ValueParameterEnhancementResult> iterable;
        Iterable<ValueParameterEnhancementResult> iterable2;
        Object obj2;
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, d.getAnnotations());
        if (d2 instanceof JavaPropertyDescriptor) {
            JavaPropertyDescriptor javaPropertyDescriptor = (JavaPropertyDescriptor) d2;
            PropertyGetterDescriptorImpl getter = javaPropertyDescriptor.getGetter();
            if (!(getter == null || getter.isDefault())) {
                PropertyGetterDescriptorImpl getter2 = javaPropertyDescriptor.getGetter();
                if (getter2 == null) {
                    acry.a();
                }
                callableMemberDescriptor = getter2;
                if (javaCallableMemberDescriptor.getExtensionReceiverParameter() == null) {
                    functionDescriptor = (FunctionDescriptor) (callableMemberDescriptor instanceof FunctionDescriptor ? null : callableMemberDescriptor);
                    enhance$default = SignatureParts.enhance$default(partsForValueParameter(d2, functionDescriptor == null ? (ValueParameterDescriptor) functionDescriptor.getUserData(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER) : null, copyWithNewDefaultTypeQualifiers, SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1.INSTANCE), null, 1, null);
                } else {
                    enhance$default = null;
                }
                javaMethodDescriptor = (JavaMethodDescriptor) (d2 instanceof JavaMethodDescriptor ? null : d2);
                if (javaMethodDescriptor != null) {
                    signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
                    containingDeclaration = javaMethodDescriptor.getContainingDeclaration();
                    if (containingDeclaration == null) {
                        signature = signatureBuildingComponents.signature((ClassDescriptor) containingDeclaration, MethodSignatureMappingKt.computeJvmDescriptor$default(javaMethodDescriptor, false, false, 3, null));
                        if (signature != null) {
                            predefinedFunctionEnhancementInfo = (PredefinedFunctionEnhancementInfo) PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(signature);
                            if (predefinedFunctionEnhancementInfo != null) {
                                obj = predefinedFunctionEnhancementInfo.getParametersInfo().size() != javaCallableMemberDescriptor.getValueParameters().size() ? 1 : null;
                                if (aa.a && obj == null) {
                                    stringBuilder = new StringBuilder("Predefined enhancement info for ");
                                    stringBuilder.append(d2);
                                    stringBuilder.append(" has ");
                                    stringBuilder.append(predefinedFunctionEnhancementInfo.getParametersInfo().size());
                                    stringBuilder.append(", but ");
                                    stringBuilder.append(javaCallableMemberDescriptor.getValueParameters().size());
                                    stringBuilder.append(" expected");
                                    throw new AssertionError(stringBuilder.toString());
                                }
                            }
                            valueParameters = callableMemberDescriptor.getValueParameters();
                            arrayList = new ArrayList(acns.a((Iterable) valueParameters, 10));
                            for (ValueParameterDescriptor valueParameterDescriptor : valueParameters) {
                                partsForValueParameter = partsForValueParameter(d2, valueParameterDescriptor, copyWithNewDefaultTypeQualifiers, new SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1(valueParameterDescriptor));
                                if (predefinedFunctionEnhancementInfo != null) {
                                    parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo();
                                    if (parametersInfo != null) {
                                        typeEnhancementInfo = (TypeEnhancementInfo) acnz.b(parametersInfo, valueParameterDescriptor.getIndex());
                                        enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                                        hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor.getType());
                                        z = enhance.getWereChanges() || hasDefaultValueInAnnotation != valueParameterDescriptor.declaresDefaultValue();
                                        arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                                    }
                                }
                                typeEnhancementInfo = null;
                                enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                                if (enhance.getWereChanges()) {
                                }
                                hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor.getType());
                                if (!enhance.getWereChanges()) {
                                }
                                arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                            }
                            list = (List) arrayList;
                            annotated = callableMemberDescriptor;
                            propertyDescriptor = (PropertyDescriptor) (d2 instanceof PropertyDescriptor ? null : d2);
                            if (propertyDescriptor == null && JavaDescriptorUtilKt.isJavaField(propertyDescriptor)) {
                                qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                            } else {
                                qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                            }
                            enhance2 = parts(d, annotated, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1.INSTANCE).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
                            if ((enhance$default == null || !enhance$default.getContainsFunctionN()) && !enhance2.getContainsFunctionN()) {
                                iterable = list;
                                if (!((Collection) iterable).isEmpty()) {
                                    for (ValueParameterEnhancementResult containsFunctionN : iterable) {
                                        if (containsFunctionN.getContainsFunctionN()) {
                                            obj = 1;
                                            break;
                                        }
                                    }
                                }
                                obj = null;
                                if (obj == null) {
                                    obj = null;
                                    if ((enhance$default == null || !enhance$default.getWereChanges()) && !enhance2.getWereChanges()) {
                                        iterable2 = list;
                                        if (!((Collection) iterable2).isEmpty()) {
                                            for (ValueParameterEnhancementResult wereChanges : iterable2) {
                                                if (wereChanges.getWereChanges()) {
                                                    obj2 = 1;
                                                    break;
                                                }
                                            }
                                        }
                                        obj2 = null;
                                        if (obj2 == null && obj == null) {
                                            return d2;
                                        }
                                    }
                                    m a = obj != null ? u.a(DeprecationKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionN((DeclarationDescriptor) d2)) : null;
                                    KotlinType type = enhance$default != null ? enhance$default.getType() : null;
                                    Iterable<ValueParameterEnhancementResult> iterable3 = list;
                                    Collection arrayList2 = new ArrayList(acns.a((Iterable) iterable3, 10));
                                    for (ValueParameterEnhancementResult valueParameterEnhancementResult : iterable3) {
                                        arrayList2.add(new ValueParameterData(valueParameterEnhancementResult.getType(), valueParameterEnhancementResult.getHasDefaultValue()));
                                    }
                                    JavaCallableMemberDescriptor enhance3 = javaCallableMemberDescriptor.enhance(type, (List) arrayList2, enhance2.getType(), a);
                                    if (enhance3 != null) {
                                        return enhance3;
                                    }
                                    throw new v("null cannot be cast to non-null type D");
                                }
                            }
                            obj = 1;
                            iterable2 = list;
                            if (((Collection) iterable2).isEmpty()) {
                                while (r2.hasNext()) {
                                    if (wereChanges.getWereChanges()) {
                                        obj2 = 1;
                                        break;
                                    }
                                }
                            }
                            obj2 = null;
                            return d2;
                        }
                    }
                    throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
                predefinedFunctionEnhancementInfo = null;
                if (predefinedFunctionEnhancementInfo != null) {
                    if (predefinedFunctionEnhancementInfo.getParametersInfo().size() != javaCallableMemberDescriptor.getValueParameters().size()) {
                    }
                    stringBuilder = new StringBuilder("Predefined enhancement info for ");
                    stringBuilder.append(d2);
                    stringBuilder.append(" has ");
                    stringBuilder.append(predefinedFunctionEnhancementInfo.getParametersInfo().size());
                    stringBuilder.append(", but ");
                    stringBuilder.append(javaCallableMemberDescriptor.getValueParameters().size());
                    stringBuilder.append(" expected");
                    throw new AssertionError(stringBuilder.toString());
                }
                valueParameters = callableMemberDescriptor.getValueParameters();
                arrayList = new ArrayList(acns.a((Iterable) valueParameters, 10));
                for (ValueParameterDescriptor valueParameterDescriptor2 : valueParameters) {
                    partsForValueParameter = partsForValueParameter(d2, valueParameterDescriptor2, copyWithNewDefaultTypeQualifiers, new SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1(valueParameterDescriptor2));
                    if (predefinedFunctionEnhancementInfo != null) {
                        parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo();
                        if (parametersInfo != null) {
                            typeEnhancementInfo = (TypeEnhancementInfo) acnz.b(parametersInfo, valueParameterDescriptor2.getIndex());
                            enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                            if (enhance.getWereChanges()) {
                            }
                            hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor2, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor2.getType());
                            if (enhance.getWereChanges()) {
                            }
                            arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                        }
                    }
                    typeEnhancementInfo = null;
                    enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                    if (enhance.getWereChanges()) {
                    }
                    hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor2, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor2.getType());
                    if (enhance.getWereChanges()) {
                    }
                    arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                }
                list = (List) arrayList;
                annotated = callableMemberDescriptor;
                if (d2 instanceof PropertyDescriptor) {
                }
                propertyDescriptor = (PropertyDescriptor) (d2 instanceof PropertyDescriptor ? null : d2);
                if (propertyDescriptor == null) {
                }
                qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                if (predefinedFunctionEnhancementInfo == null) {
                }
                enhance2 = parts(d, annotated, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1.INSTANCE).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
                iterable = list;
                if (((Collection) iterable).isEmpty()) {
                    while (r1.hasNext()) {
                        if (containsFunctionN.getContainsFunctionN()) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    obj = null;
                    iterable2 = list;
                    if (((Collection) iterable2).isEmpty()) {
                        while (r2.hasNext()) {
                            if (wereChanges.getWereChanges()) {
                                obj2 = 1;
                                break;
                            }
                        }
                    }
                    obj2 = null;
                    return d2;
                }
                obj = 1;
                iterable2 = list;
                if (((Collection) iterable2).isEmpty()) {
                    while (r2.hasNext()) {
                        if (wereChanges.getWereChanges()) {
                            obj2 = 1;
                            break;
                        }
                    }
                }
                obj2 = null;
                return d2;
            }
        }
        callableMemberDescriptor = d2;
        if (javaCallableMemberDescriptor.getExtensionReceiverParameter() == null) {
            enhance$default = null;
        } else {
            if (callableMemberDescriptor instanceof FunctionDescriptor) {
            }
            functionDescriptor = (FunctionDescriptor) (callableMemberDescriptor instanceof FunctionDescriptor ? null : callableMemberDescriptor);
            if (functionDescriptor == null) {
            }
            enhance$default = SignatureParts.enhance$default(partsForValueParameter(d2, functionDescriptor == null ? (ValueParameterDescriptor) functionDescriptor.getUserData(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER) : null, copyWithNewDefaultTypeQualifiers, SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1.INSTANCE), null, 1, null);
        }
        if (d2 instanceof JavaMethodDescriptor) {
        }
        javaMethodDescriptor = (JavaMethodDescriptor) (d2 instanceof JavaMethodDescriptor ? null : d2);
        if (javaMethodDescriptor != null) {
            signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            containingDeclaration = javaMethodDescriptor.getContainingDeclaration();
            if (containingDeclaration == null) {
                throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            signature = signatureBuildingComponents.signature((ClassDescriptor) containingDeclaration, MethodSignatureMappingKt.computeJvmDescriptor$default(javaMethodDescriptor, false, false, 3, null));
            if (signature != null) {
                predefinedFunctionEnhancementInfo = (PredefinedFunctionEnhancementInfo) PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(signature);
                if (predefinedFunctionEnhancementInfo != null) {
                    if (predefinedFunctionEnhancementInfo.getParametersInfo().size() != javaCallableMemberDescriptor.getValueParameters().size()) {
                    }
                    stringBuilder = new StringBuilder("Predefined enhancement info for ");
                    stringBuilder.append(d2);
                    stringBuilder.append(" has ");
                    stringBuilder.append(predefinedFunctionEnhancementInfo.getParametersInfo().size());
                    stringBuilder.append(", but ");
                    stringBuilder.append(javaCallableMemberDescriptor.getValueParameters().size());
                    stringBuilder.append(" expected");
                    throw new AssertionError(stringBuilder.toString());
                }
                valueParameters = callableMemberDescriptor.getValueParameters();
                arrayList = new ArrayList(acns.a((Iterable) valueParameters, 10));
                for (ValueParameterDescriptor valueParameterDescriptor22 : valueParameters) {
                    partsForValueParameter = partsForValueParameter(d2, valueParameterDescriptor22, copyWithNewDefaultTypeQualifiers, new SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1(valueParameterDescriptor22));
                    if (predefinedFunctionEnhancementInfo != null) {
                        parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo();
                        if (parametersInfo != null) {
                            typeEnhancementInfo = (TypeEnhancementInfo) acnz.b(parametersInfo, valueParameterDescriptor22.getIndex());
                            enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                            if (enhance.getWereChanges()) {
                            }
                            hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor22, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor22.getType());
                            if (enhance.getWereChanges()) {
                            }
                            arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                        }
                    }
                    typeEnhancementInfo = null;
                    enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                    if (enhance.getWereChanges()) {
                    }
                    hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor22, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor22.getType());
                    if (enhance.getWereChanges()) {
                    }
                    arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                }
                list = (List) arrayList;
                annotated = callableMemberDescriptor;
                if (d2 instanceof PropertyDescriptor) {
                }
                propertyDescriptor = (PropertyDescriptor) (d2 instanceof PropertyDescriptor ? null : d2);
                if (propertyDescriptor == null) {
                }
                qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                if (predefinedFunctionEnhancementInfo == null) {
                }
                enhance2 = parts(d, annotated, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1.INSTANCE).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
                iterable = list;
                if (((Collection) iterable).isEmpty()) {
                    while (r1.hasNext()) {
                        if (containsFunctionN.getContainsFunctionN()) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    obj = null;
                    iterable2 = list;
                    if (((Collection) iterable2).isEmpty()) {
                        while (r2.hasNext()) {
                            if (wereChanges.getWereChanges()) {
                                obj2 = 1;
                                break;
                            }
                        }
                    }
                    obj2 = null;
                    return d2;
                }
                obj = 1;
                iterable2 = list;
                if (((Collection) iterable2).isEmpty()) {
                    while (r2.hasNext()) {
                        if (wereChanges.getWereChanges()) {
                            obj2 = 1;
                            break;
                        }
                    }
                }
                obj2 = null;
                return d2;
            }
        }
        predefinedFunctionEnhancementInfo = null;
        if (predefinedFunctionEnhancementInfo != null) {
            if (predefinedFunctionEnhancementInfo.getParametersInfo().size() != javaCallableMemberDescriptor.getValueParameters().size()) {
            }
            stringBuilder = new StringBuilder("Predefined enhancement info for ");
            stringBuilder.append(d2);
            stringBuilder.append(" has ");
            stringBuilder.append(predefinedFunctionEnhancementInfo.getParametersInfo().size());
            stringBuilder.append(", but ");
            stringBuilder.append(javaCallableMemberDescriptor.getValueParameters().size());
            stringBuilder.append(" expected");
            throw new AssertionError(stringBuilder.toString());
        }
        valueParameters = callableMemberDescriptor.getValueParameters();
        arrayList = new ArrayList(acns.a((Iterable) valueParameters, 10));
        for (ValueParameterDescriptor valueParameterDescriptor222 : valueParameters) {
            partsForValueParameter = partsForValueParameter(d2, valueParameterDescriptor222, copyWithNewDefaultTypeQualifiers, new SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1(valueParameterDescriptor222));
            if (predefinedFunctionEnhancementInfo != null) {
                parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo();
                if (parametersInfo != null) {
                    typeEnhancementInfo = (TypeEnhancementInfo) acnz.b(parametersInfo, valueParameterDescriptor222.getIndex());
                    enhance = partsForValueParameter.enhance(typeEnhancementInfo);
                    if (enhance.getWereChanges()) {
                    }
                    hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor222, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor222.getType());
                    if (enhance.getWereChanges()) {
                    }
                    arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
                }
            }
            typeEnhancementInfo = null;
            enhance = partsForValueParameter.enhance(typeEnhancementInfo);
            if (enhance.getWereChanges()) {
            }
            hasDefaultValueInAnnotation = hasDefaultValueInAnnotation(valueParameterDescriptor222, enhance.getWereChanges() ? enhance.getType() : valueParameterDescriptor222.getType());
            if (enhance.getWereChanges()) {
            }
            arrayList.add(new ValueParameterEnhancementResult(enhance.getType(), hasDefaultValueInAnnotation, z, enhance.getContainsFunctionN()));
        }
        list = (List) arrayList;
        annotated = callableMemberDescriptor;
        if (d2 instanceof PropertyDescriptor) {
        }
        propertyDescriptor = (PropertyDescriptor) (d2 instanceof PropertyDescriptor ? null : d2);
        if (propertyDescriptor == null) {
        }
        qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
        if (predefinedFunctionEnhancementInfo == null) {
        }
        enhance2 = parts(d, annotated, true, copyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1.INSTANCE).enhance(predefinedFunctionEnhancementInfo == null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
        iterable = list;
        if (((Collection) iterable).isEmpty()) {
            while (r1.hasNext()) {
                if (containsFunctionN.getContainsFunctionN()) {
                    obj = 1;
                    break;
                }
            }
        }
        obj = null;
        if (obj == null) {
            obj = null;
            iterable2 = list;
            if (((Collection) iterable2).isEmpty()) {
                while (r2.hasNext()) {
                    if (wereChanges.getWereChanges()) {
                        obj2 = 1;
                        break;
                    }
                }
            }
            obj2 = null;
            return d2;
        }
        obj = 1;
        iterable2 = list;
        if (((Collection) iterable2).isEmpty()) {
            while (r2.hasNext()) {
                if (wereChanges.getWereChanges()) {
                    obj2 = 1;
                    break;
                }
            }
        }
        obj2 = null;
        return d2;
    }

    private final boolean hasDefaultValueInAnnotation(ValueParameterDescriptor valueParameterDescriptor, KotlinType kotlinType) {
        boolean z;
        Object defaultValueFromAnnotation = UtilKt.getDefaultValueFromAnnotation(valueParameterDescriptor);
        if (defaultValueFromAnnotation instanceof StringDefaultValue) {
            z = UtilsKt.lexicalCastFrom(kotlinType, ((StringDefaultValue) defaultValueFromAnnotation).getValue()) != null;
        } else if (acry.a(defaultValueFromAnnotation, NullDefaultValue.INSTANCE)) {
            z = TypeUtils.acceptsNullable(kotlinType);
        } else if (defaultValueFromAnnotation == null) {
            z = valueParameterDescriptor.declaresDefaultValue();
        } else {
            throw new k();
        }
        return z && valueParameterDescriptor.getOverriddenDescriptors().isEmpty();
    }

    private final SignatureParts partsForValueParameter(CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, acqr<? super CallableMemberDescriptor, ? extends KotlinType> acqr) {
        LazyJavaResolverContext lazyJavaResolverContext2;
        Annotated annotated = valueParameterDescriptor;
        if (valueParameterDescriptor != null) {
            LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.getAnnotations());
            if (copyWithNewDefaultTypeQualifiers != null) {
                lazyJavaResolverContext2 = copyWithNewDefaultTypeQualifiers;
                return parts(callableMemberDescriptor, annotated, false, lazyJavaResolverContext2, QualifierApplicabilityType.VALUE_PARAMETER, acqr);
            }
        }
        lazyJavaResolverContext2 = lazyJavaResolverContext;
        return parts(callableMemberDescriptor, annotated, false, lazyJavaResolverContext2, QualifierApplicabilityType.VALUE_PARAMETER, acqr);
    }

    private final SignatureParts parts(CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, QualifierApplicabilityType qualifierApplicabilityType, acqr<? super CallableMemberDescriptor, ? extends KotlinType> acqr) {
        KotlinType kotlinType = (KotlinType) acqr.invoke(callableMemberDescriptor);
        Iterable<CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Collection arrayList = new ArrayList(acns.a((Iterable) overriddenDescriptors, 10));
        for (CallableMemberDescriptor invoke : overriddenDescriptors) {
            arrayList.add((KotlinType) acqr.invoke(invoke));
        }
        return new SignatureParts(annotated, kotlinType, (List) arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, ((KotlinType) acqr.invoke(callableMemberDescriptor)).getAnnotations()), qualifierApplicabilityType);
    }
}
