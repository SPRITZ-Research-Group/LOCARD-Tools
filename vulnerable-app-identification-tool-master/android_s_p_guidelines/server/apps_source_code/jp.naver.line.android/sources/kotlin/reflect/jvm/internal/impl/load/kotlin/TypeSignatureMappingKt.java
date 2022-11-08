package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acnz;
import defpackage.acrg;
import defpackage.acry;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.PlatformMutabilityMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.v;
import kotlin.y;

public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, acrg acrg, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            acrg = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, acrg, z);
    }

    public static final <T> T mapType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration<? extends T> typeMappingConfiguration, JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, acrg<? super KotlinType, ? super T, ? super TypeMappingMode, y> acrg, boolean z) {
        T boxTypeIfNeeded;
        ClassDescriptor classDescriptor;
        JvmTypeFactory<T> jvmTypeFactory2 = jvmTypeFactory;
        TypeMappingConfiguration<? extends T> typeMappingConfiguration2 = typeMappingConfiguration;
        JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter2 = jvmDescriptorTypeWriter;
        acrg<? super KotlinType, ? super T, ? super TypeMappingMode, y> acrg2 = acrg;
        KotlinType kotlinType2 = kotlinType;
        TypeMappingMode typeMappingMode2 = typeMappingMode;
        while (true) {
            KotlinType preprocessType = typeMappingConfiguration.preprocessType(kotlinType2);
            if (preprocessType == null) {
                if (FunctionTypesKt.isSuspendFunctionType(kotlinType2)) {
                    kotlinType2 = SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType2, typeMappingConfiguration.releaseCoroutines());
                } else {
                    Object mapBuiltInType = mapBuiltInType(kotlinType2, jvmTypeFactory, typeMappingMode2);
                    if (mapBuiltInType != null) {
                        boxTypeIfNeeded = boxTypeIfNeeded(jvmTypeFactory, mapBuiltInType, typeMappingMode2.getNeedPrimitiveBoxing());
                        acrg.invoke(kotlinType2, boxTypeIfNeeded, typeMappingMode2);
                        return boxTypeIfNeeded;
                    }
                    TypeConstructor constructor = kotlinType2.getConstructor();
                    if (constructor instanceof IntersectionTypeConstructor) {
                        kotlinType2 = TypeUtilsKt.replaceArgumentsWithStarProjections(typeMappingConfiguration.commonSupertype(((IntersectionTypeConstructor) constructor).getSupertypes()));
                    } else {
                        ClassifierDescriptor declarationDescriptor = constructor.getDeclarationDescriptor();
                        if (declarationDescriptor == null) {
                            throw new UnsupportedOperationException("no descriptor for type constructor of ".concat(String.valueOf(kotlinType2)));
                        } else if (ErrorUtils.isError(declarationDescriptor)) {
                            boxTypeIfNeeded = jvmTypeFactory.createObjectType("error/NonExistentClass");
                            if (declarationDescriptor != null) {
                                typeMappingConfiguration.processErrorType(kotlinType2, (ClassDescriptor) declarationDescriptor);
                                if (jvmDescriptorTypeWriter2 != null) {
                                    jvmDescriptorTypeWriter.writeClass(boxTypeIfNeeded);
                                }
                                return boxTypeIfNeeded;
                            }
                            throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        } else {
                            boolean z2 = declarationDescriptor instanceof ClassDescriptor;
                            if (z2 && KotlinBuiltIns.isArray(kotlinType2)) {
                                if (kotlinType2.getArguments().size() == 1) {
                                    Object createObjectType;
                                    TypeProjection typeProjection = (TypeProjection) kotlinType2.getArguments().get(0);
                                    preprocessType = typeProjection.getType();
                                    if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                                        createObjectType = jvmTypeFactory.createObjectType("java/lang/Object");
                                        if (jvmDescriptorTypeWriter2 != null) {
                                            jvmDescriptorTypeWriter.writeArrayType();
                                            jvmDescriptorTypeWriter.writeClass(createObjectType);
                                            jvmDescriptorTypeWriter.writeArrayEnd();
                                        }
                                    } else {
                                        if (jvmDescriptorTypeWriter2 != null) {
                                            jvmDescriptorTypeWriter.writeArrayType();
                                        }
                                        createObjectType = mapType(preprocessType, jvmTypeFactory, typeMappingMode2.toGenericArgumentMode(typeProjection.getProjectionKind()), typeMappingConfiguration, jvmDescriptorTypeWriter, acrg, z);
                                        if (jvmDescriptorTypeWriter2 != null) {
                                            jvmDescriptorTypeWriter.writeArrayEnd();
                                        }
                                    }
                                    StringBuilder stringBuilder = new StringBuilder("[");
                                    stringBuilder.append(jvmTypeFactory.toString(createObjectType));
                                    return jvmTypeFactory.createFromString(stringBuilder.toString());
                                }
                                throw new UnsupportedOperationException("arrays must have one type argument");
                            } else if (z2) {
                                classDescriptor = (ClassDescriptor) declarationDescriptor;
                                if (!classDescriptor.isInline() || typeMappingMode2.getNeedInlineClassWrapping()) {
                                    break;
                                }
                                preprocessType = computeExpandedTypeForInlineClass(kotlinType2);
                                if (preprocessType == null) {
                                    break;
                                }
                                typeMappingMode2 = typeMappingMode2.wrapInlineClassesMode();
                            } else {
                                boolean z3 = z;
                                if (declarationDescriptor instanceof TypeParameterDescriptor) {
                                    T mapType = mapType(getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor), jvmTypeFactory, typeMappingMode2, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3(), z);
                                    if (jvmDescriptorTypeWriter2 != null) {
                                        jvmDescriptorTypeWriter.writeTypeVariable(declarationDescriptor.getName(), mapType);
                                    }
                                    return mapType;
                                }
                                throw new UnsupportedOperationException("Unknown type ".concat(String.valueOf(kotlinType2)));
                            }
                        }
                    }
                }
            }
            kotlinType2 = preprocessType;
        }
        if (typeMappingMode2.isForAnnotationParameter() && KotlinBuiltIns.isKClass(classDescriptor)) {
            boxTypeIfNeeded = jvmTypeFactory.getJavaLangClassType();
        } else {
            boxTypeIfNeeded = typeMappingConfiguration.getPredefinedTypeForClass(classDescriptor.getOriginal());
            if (boxTypeIfNeeded == null) {
                if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                    DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
                    if (containingDeclaration != null) {
                        classDescriptor = (ClassDescriptor) containingDeclaration;
                    } else {
                        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                }
                boxTypeIfNeeded = jvmTypeFactory.createObjectType(computeInternalName(classDescriptor.getOriginal(), typeMappingConfiguration, z));
            }
        }
        acrg.invoke(kotlinType2, boxTypeIfNeeded, typeMappingMode2);
        return boxTypeIfNeeded;
    }

    public static final boolean hasVoidReturnType(CallableDescriptor callableDescriptor) {
        if (callableDescriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = callableDescriptor.getReturnType();
        if (returnType == null) {
            acry.a();
        }
        if (KotlinBuiltIns.isUnit(returnType)) {
            returnType = callableDescriptor.getReturnType();
            if (returnType == null) {
                acry.a();
            }
            if (!(TypeUtils.isNullableType(returnType) || (callableDescriptor instanceof PropertyGetterDescriptor))) {
                return true;
            }
        }
        return false;
    }

    private static final String continuationInternalName(boolean z) {
        FqName fqName;
        if (z) {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE;
        } else {
            fqName = DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;
        }
        return JvmClassName.byClassId(ClassId.topLevel(fqName)).getInternalName();
    }

    private static final <T> T mapBuiltInType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        Object obj = (ClassDescriptor) declarationDescriptor;
        if (obj == null) {
            return null;
        }
        boolean z = false;
        if (obj == SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL()) {
            return jvmTypeFactory.createObjectType(continuationInternalName(false));
        }
        if (acry.a(obj, SuspendFunctionTypesKt.getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE())) {
            return jvmTypeFactory.createObjectType(continuationInternalName(true));
        }
        DeclarationDescriptor declarationDescriptor2 = (DeclarationDescriptor) obj;
        PrimitiveType primitiveType = KotlinBuiltIns.getPrimitiveType(declarationDescriptor2);
        if (primitiveType != null) {
            Object createFromString = jvmTypeFactory.createFromString(JvmPrimitiveType.get(primitiveType).getDesc());
            if (TypeUtils.isNullableType(kotlinType) || TypeEnhancementKt.hasEnhancedNullability(kotlinType)) {
                z = true;
            }
            return boxTypeIfNeeded(jvmTypeFactory, createFromString, z);
        }
        PrimitiveType primitiveArrayType = KotlinBuiltIns.getPrimitiveArrayType(declarationDescriptor2);
        if (primitiveArrayType != null) {
            StringBuilder stringBuilder = new StringBuilder("[");
            stringBuilder.append(JvmPrimitiveType.get(primitiveArrayType).getDesc());
            return jvmTypeFactory.createFromString(stringBuilder.toString());
        }
        if (KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor2)) {
            Object mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor2));
            if (mapKotlinToJava != null) {
                if (!typeMappingMode.getKotlinCollectionsToJavaCollections()) {
                    Iterable<PlatformMutabilityMapping> mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !((Collection) mutabilityMappings).isEmpty()) {
                        for (PlatformMutabilityMapping javaClass : mutabilityMappings) {
                            if (acry.a(javaClass.getJavaClass(), mapKotlinToJava)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (z) {
                        return null;
                    }
                }
                return jvmTypeFactory.createObjectType(JvmClassName.byClassId(mapKotlinToJava).getInternalName());
            }
        }
        return null;
    }

    public static final KotlinType computeExpandedTypeForInlineClass(KotlinType kotlinType) {
        return computeExpandedTypeInner(kotlinType, new HashSet());
    }

    public static final KotlinType computeExpandedTypeInner(KotlinType kotlinType, HashSet<ClassifierDescriptor> hashSet) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            throw new AssertionError("Type with a declaration expected: ".concat(String.valueOf(kotlinType)));
        } else if (!hashSet.add(declarationDescriptor)) {
            return null;
        } else {
            KotlinType computeExpandedTypeInner;
            if (declarationDescriptor instanceof TypeParameterDescriptor) {
                computeExpandedTypeInner = computeExpandedTypeInner(getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor), hashSet);
                if (computeExpandedTypeInner != null) {
                    return (KotlinTypeKt.isNullable(computeExpandedTypeInner) || !kotlinType.isMarkedNullable()) ? computeExpandedTypeInner : TypeUtilsKt.makeNullable(computeExpandedTypeInner);
                } else {
                    return null;
                }
            } else if (!(declarationDescriptor instanceof ClassDescriptor) || !((ClassDescriptor) declarationDescriptor).isInline()) {
                return kotlinType;
            } else {
                KotlinType substitutedUnderlyingType = InlineClassesUtilsKt.substitutedUnderlyingType(kotlinType);
                if (substitutedUnderlyingType == null) {
                    return null;
                }
                computeExpandedTypeInner = computeExpandedTypeInner(substitutedUnderlyingType, hashSet);
                if (computeExpandedTypeInner == null) {
                    return null;
                }
                if (!KotlinTypeKt.isNullable(kotlinType)) {
                    return computeExpandedTypeInner;
                }
                if (KotlinTypeKt.isNullable(computeExpandedTypeInner) || KotlinBuiltIns.isPrimitiveType(computeExpandedTypeInner)) {
                    return kotlinType;
                }
                return TypeUtilsKt.makeNullable(computeExpandedTypeInner);
            }
        }
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration, z);
    }

    public static final String computeInternalName(ClassDescriptor classDescriptor, TypeMappingConfiguration<?> typeMappingConfiguration, boolean z) {
        Object container = z ? getContainer(classDescriptor.getContainingDeclaration()) : classDescriptor.getContainingDeclaration();
        String identifier = SpecialNames.safeIdentifier(classDescriptor.getName()).getIdentifier();
        StringBuilder stringBuilder;
        if (container instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) container).getFqName();
            if (fqName.isRoot()) {
                return identifier;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(fqName.asString().replace('.', '/'));
            stringBuilder.append('/');
            stringBuilder.append(identifier);
            return stringBuilder.toString();
        }
        ClassDescriptor classDescriptor2 = (ClassDescriptor) (!(container instanceof ClassDescriptor) ? null : container);
        if (classDescriptor2 != null) {
            String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor2);
            if (predefinedInternalNameForClass == null) {
                predefinedInternalNameForClass = computeInternalName(classDescriptor2, typeMappingConfiguration, z);
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(predefinedInternalNameForClass);
            stringBuilder.append('$');
            stringBuilder.append(identifier);
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder("Unexpected container: ");
        stringBuilder2.append(container);
        stringBuilder2.append(" for ");
        stringBuilder2.append(classDescriptor);
        throw new IllegalArgumentException(stringBuilder2.toString());
    }

    private static final DeclarationDescriptor getContainer(DeclarationDescriptor declarationDescriptor) {
        while (true) {
            ClassDescriptor classDescriptor = (ClassDescriptor) (!(declarationDescriptor instanceof ClassDescriptor) ? null : declarationDescriptor);
            if (classDescriptor == null) {
                PackageFragmentDescriptor classDescriptor2 = (PackageFragmentDescriptor) (!(declarationDescriptor instanceof PackageFragmentDescriptor) ? null : declarationDescriptor);
            }
            DeclarationDescriptor declarationDescriptor2 = classDescriptor2;
            if (declarationDescriptor2 != null) {
                return declarationDescriptor2;
            }
            if (declarationDescriptor == null) {
                return null;
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
    }

    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor typeParameterDescriptor) {
        List upperBounds = typeParameterDescriptor.getUpperBounds();
        int isEmpty = upperBounds.isEmpty() ^ 1;
        if (aa.a && isEmpty == 0) {
            throw new AssertionError("Upper bounds should not be empty: ".concat(String.valueOf(typeParameterDescriptor)));
        }
        Object obj;
        Iterator it = upperBounds.iterator();
        Object obj2;
        do {
            ClassifierDescriptor classifierDescriptor = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ClassifierDescriptor declarationDescriptor = ((KotlinType) obj).getConstructor().getDeclarationDescriptor();
            if (declarationDescriptor instanceof ClassDescriptor) {
                classifierDescriptor = declarationDescriptor;
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
            if (classDescriptor == null || classDescriptor.getKind() == ClassKind.INTERFACE || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) {
                obj2 = null;
                continue;
            } else {
                obj2 = 1;
                continue;
            }
        } while (obj2 == null);
        KotlinType kotlinType = (KotlinType) obj;
        return kotlinType == null ? (KotlinType) acnz.e(upperBounds) : kotlinType;
    }
}
