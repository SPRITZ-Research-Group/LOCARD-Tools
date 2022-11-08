package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acoe;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class JavaTypeResolver {
    private final LazyJavaResolverContext c;
    private final TypeParameterResolver typeParameterResolver;

    public JavaTypeResolver(LazyJavaResolverContext lazyJavaResolverContext, TypeParameterResolver typeParameterResolver) {
        this.c = lazyJavaResolverContext;
        this.typeParameterResolver = typeParameterResolver;
    }

    public final KotlinType transformJavaType(JavaType javaType, JavaTypeAttributes javaTypeAttributes) {
        if (javaType instanceof JavaPrimitiveType) {
            SimpleType primitiveKotlinType;
            PrimitiveType type = ((JavaPrimitiveType) javaType).getType();
            if (type != null) {
                primitiveKotlinType = this.c.getModule().getBuiltIns().getPrimitiveKotlinType(type);
            } else {
                primitiveKotlinType = this.c.getModule().getBuiltIns().getUnitType();
            }
            return primitiveKotlinType;
        } else if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, javaTypeAttributes);
        } else {
            if (javaType instanceof JavaArrayType) {
                return transformArrayType$default(this, (JavaArrayType) javaType, javaTypeAttributes, false, 4, null);
            }
            if (javaType instanceof JavaWildcardType) {
                javaType = ((JavaWildcardType) javaType).getBound();
                if (javaType != null) {
                    KotlinType transformJavaType = transformJavaType(javaType, javaTypeAttributes);
                    if (transformJavaType != null) {
                        return transformJavaType;
                    }
                }
                return this.c.getModule().getBuiltIns().getDefaultBound();
            }
            throw new UnsupportedOperationException("Unsupported type: ".concat(String.valueOf(javaType)));
        }
    }

    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    public final KotlinType transformArrayType(JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z) {
        JavaType componentType = javaArrayType.getComponentType();
        JavaPrimitiveType javaPrimitiveType = (JavaPrimitiveType) (!(componentType instanceof JavaPrimitiveType) ? null : componentType);
        PrimitiveType type = javaPrimitiveType != null ? javaPrimitiveType.getType() : null;
        if (type != null) {
            SimpleType primitiveArrayKotlinType = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(type);
            if (javaTypeAttributes.isForAnnotationParameter()) {
                return primitiveArrayKotlinType;
            }
            return KotlinTypeFactory.flexibleType(primitiveArrayKotlinType, primitiveArrayKotlinType.makeNullableAsSpecified(true));
        }
        KotlinType transformJavaType = transformJavaType(componentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, javaTypeAttributes.isForAnnotationParameter(), null, 2, null));
        if (!javaTypeAttributes.isForAnnotationParameter()) {
            return KotlinTypeFactory.flexibleType(this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, transformJavaType), this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, transformJavaType).makeNullableAsSpecified(true));
        }
        return this.c.getModule().getBuiltIns().getArrayType(z ? Variance.OUT_VARIANCE : Variance.INVARIANT, transformJavaType);
    }

    private final KotlinType transformJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        JavaTypeResolver$transformJavaClassifierType$1 javaTypeResolver$transformJavaClassifierType$1 = new JavaTypeResolver$transformJavaClassifierType$1(javaClassifierType);
        Object obj = (javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? null : 1;
        boolean isRaw = javaClassifierType.isRaw();
        SimpleType computeSimpleJavaClassifierType;
        if (isRaw || obj != null) {
            SimpleType computeSimpleJavaClassifierType2 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
            if (computeSimpleJavaClassifierType2 == null) {
                return javaTypeResolver$transformJavaClassifierType$1.invoke();
            }
            computeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), computeSimpleJavaClassifierType2);
            if (computeSimpleJavaClassifierType == null) {
                return javaTypeResolver$transformJavaClassifierType$1.invoke();
            }
            if (isRaw) {
                return new RawTypeImpl(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType);
            }
            return KotlinTypeFactory.flexibleType(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType);
        }
        computeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes, null);
        return computeSimpleJavaClassifierType != null ? computeSimpleJavaClassifierType : javaTypeResolver$transformJavaClassifierType$1.invoke();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, SimpleType simpleType) {
        Annotations annotations;
        if (simpleType != null) {
            annotations = simpleType.getAnnotations();
        }
        annotations = new LazyJavaAnnotations(this.c, javaClassifierType);
        Object computeTypeConstructor = computeTypeConstructor(javaClassifierType, javaTypeAttributes);
        Object obj = null;
        if (computeTypeConstructor == null) {
            return null;
        }
        boolean isNullable = isNullable(javaTypeAttributes);
        if (simpleType != null) {
            obj = simpleType.getConstructor();
        }
        if (acry.a(obj, computeTypeConstructor) && !javaClassifierType.isRaw() && isNullable) {
            return simpleType.makeNullableAsSpecified(true);
        }
        return KotlinTypeFactory.simpleType(annotations, computeTypeConstructor, computeArguments(javaClassifierType, javaTypeAttributes, computeTypeConstructor), isNullable);
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        JavaClassifier classifier = javaClassifierType.getClassifier();
        if (classifier == null) {
            return createNotFoundClass(javaClassifierType);
        }
        if (classifier instanceof JavaClass) {
            JavaClass javaClass = (JavaClass) classifier;
            FqName fqName = javaClass.getFqName();
            if (fqName != null) {
                ClassDescriptor mapKotlinClass = mapKotlinClass(javaClassifierType, javaTypeAttributes, fqName);
                if (mapKotlinClass == null) {
                    mapKotlinClass = this.c.getComponents().getModuleClassResolver().resolveClass(javaClass);
                }
                if (mapKotlinClass != null) {
                    TypeConstructor typeConstructor = mapKotlinClass.getTypeConstructor();
                    if (typeConstructor != null) {
                        return typeConstructor;
                    }
                }
                return createNotFoundClass(javaClassifierType);
            }
            throw new AssertionError("Class type should have a FQ name: ".concat(String.valueOf(classifier)));
        } else if (classifier instanceof JavaTypeParameter) {
            TypeParameterDescriptor resolveTypeParameter = this.typeParameterResolver.resolveTypeParameter((JavaTypeParameter) classifier);
            return resolveTypeParameter != null ? resolveTypeParameter.getTypeConstructor() : null;
        } else {
            throw new IllegalStateException("Unknown classifier kind: ".concat(String.valueOf(classifier)));
        }
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType) {
        return this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(ClassId.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName())), Collections.singletonList(Integer.valueOf(0))).getTypeConstructor();
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, FqName fqName) {
        if (javaTypeAttributes.isForAnnotationParameter() && acry.a((Object) fqName, JavaTypeResolverKt.JAVA_LANG_CLASS_FQ_NAME)) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(javaToKotlinClassMap, fqName, this.c.getModule().getBuiltIns(), null, 4, null);
        if (mapJavaToKotlin$default == null) {
            return null;
        }
        return (javaToKotlinClassMap.isReadOnly(mapJavaToKotlin$default) && (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, mapJavaToKotlin$default))) ? javaToKotlinClassMap.convertReadOnlyToMutable(mapJavaToKotlin$default) : mapJavaToKotlin$default;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType javaClassifierType, ClassDescriptor classDescriptor) {
        if (!JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1.INSTANCE.invoke((JavaType) acnz.h(javaClassifierType.getTypeArguments()))) {
            return false;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) acnz.h(JavaToKotlinClassMap.INSTANCE.convertReadOnlyToMutable(classDescriptor).getTypeConstructor().getParameters());
        if (typeParameterDescriptor != null) {
            Variance variance = typeParameterDescriptor.getVariance();
            if (variance == null || variance == Variance.OUT_VARIANCE) {
                return false;
            }
            return true;
        }
        return false;
    }

    private final List<TypeProjection> computeArguments(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, TypeConstructor typeConstructor) {
        boolean isRaw = javaClassifierType.isRaw();
        Object obj = (isRaw || (javaClassifierType.getTypeArguments().isEmpty() && !typeConstructor.getParameters().isEmpty())) ? 1 : null;
        List parameters = typeConstructor.getParameters();
        Iterable<TypeParameterDescriptor> iterable;
        Collection arrayList;
        if (obj != null) {
            iterable = parameters;
            arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
            for (TypeParameterDescriptor typeParameterDescriptor : iterable) {
                JavaTypeAttributes javaTypeAttributes2;
                LazyWrappedType lazyWrappedType = new LazyWrappedType(this.c.getStorageManager(), new JavaTypeResolver$computeArguments$$inlined$map$lambda$1(typeParameterDescriptor, this, javaTypeAttributes, typeConstructor, isRaw));
                RawSubstitution rawSubstitution = RawSubstitution.INSTANCE;
                if (isRaw) {
                    javaTypeAttributes2 = javaTypeAttributes;
                } else {
                    javaTypeAttributes2 = javaTypeAttributes.withFlexibility(JavaTypeFlexibility.INFLEXIBLE);
                }
                arrayList.add(rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes2, lazyWrappedType));
            }
            return acnz.k((Iterable) (List) arrayList);
        } else if (parameters.size() != javaClassifierType.getTypeArguments().size()) {
            iterable = parameters;
            arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
            for (TypeParameterDescriptor name : iterable) {
                arrayList.add(new TypeProjectionImpl(ErrorUtils.createErrorType(name.getName().asString())));
            }
            return acnz.k((Iterable) (List) arrayList);
        } else {
            Iterable<acoe> n = acnz.n(javaClassifierType.getTypeArguments());
            Collection arrayList2 = new ArrayList(acns.a((Iterable) n, 10));
            for (acoe acoe : n) {
                int c = acoe.c();
                JavaType javaType = (JavaType) acoe.d();
                Object obj2 = c < parameters.size() ? 1 : null;
                if (aa.a && obj2 == null) {
                    StringBuilder stringBuilder = new StringBuilder("Argument index should be less then type parameters count, but ");
                    stringBuilder.append(c);
                    stringBuilder.append(" > ");
                    stringBuilder.append(parameters.size());
                    throw new AssertionError(stringBuilder.toString());
                }
                arrayList2.add(transformToTypeProjection(javaType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null), (TypeParameterDescriptor) parameters.get(c)));
            }
            return acnz.k((Iterable) (List) arrayList2);
        }
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes javaTypeAttributes, TypeParameterDescriptor typeParameterDescriptor) {
        if (!(javaType instanceof JavaWildcardType)) {
            return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, javaTypeAttributes));
        }
        JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
        JavaType bound = javaWildcardType.getBound();
        Variance variance = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
        if (bound == null || isConflictingArgumentFor(variance, typeParameterDescriptor)) {
            return JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
        }
        return TypeUtilsKt.createProjection(transformJavaType(bound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), variance, typeParameterDescriptor);
    }

    private final boolean isConflictingArgumentFor(Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor.getVariance() == Variance.INVARIANT || variance == typeParameterDescriptor.getVariance()) {
            return false;
        }
        return true;
    }

    private final boolean isNullable(JavaTypeAttributes javaTypeAttributes) {
        if (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return false;
        }
        return true;
    }
}
