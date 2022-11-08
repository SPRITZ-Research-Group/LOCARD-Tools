package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import kotlin.e;
import kotlin.j;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.TypeQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

public final class ContextKt {
    public static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext, TypeParameterResolver typeParameterResolver) {
        return new LazyJavaResolverContext(lazyJavaResolverContext.getComponents(), typeParameterResolver, lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static final JavaTypeQualifiersByElementType computeNewDefaultTypeQualifiers(LazyJavaResolverContext lazyJavaResolverContext, Annotations annotations) {
        if (lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver().getDisabled()) {
            return lazyJavaResolverContext.getDefaultTypeQualifiers();
        }
        Collection arrayList = new ArrayList();
        for (AnnotationDescriptor extractDefaultNullabilityQualifier : annotations) {
            NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier2 = extractDefaultNullabilityQualifier(lazyJavaResolverContext, extractDefaultNullabilityQualifier);
            if (extractDefaultNullabilityQualifier2 != null) {
                arrayList.add(extractDefaultNullabilityQualifier2);
            }
        }
        List<NullabilityQualifierWithApplicability> list = (List) arrayList;
        if (list.isEmpty()) {
            return lazyJavaResolverContext.getDefaultTypeQualifiers();
        }
        EnumMap enumMap;
        Object obj;
        NullabilityQualifierWithMigrationStatus component1;
        JavaTypeQualifiersByElementType defaultTypeQualifiers = lazyJavaResolverContext.getDefaultTypeQualifiers();
        if (defaultTypeQualifiers != null) {
            EnumMap nullabilityQualifiers$descriptors_jvm = defaultTypeQualifiers.getNullabilityQualifiers$descriptors_jvm();
            if (nullabilityQualifiers$descriptors_jvm != null) {
                enumMap = new EnumMap(nullabilityQualifiers$descriptors_jvm);
                obj = null;
                for (NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability : list) {
                    component1 = nullabilityQualifierWithApplicability.component1();
                    for (QualifierApplicabilityType put : nullabilityQualifierWithApplicability.component2()) {
                        enumMap.put(put, component1);
                        obj = 1;
                    }
                }
                return obj != null ? lazyJavaResolverContext.getDefaultTypeQualifiers() : new JavaTypeQualifiersByElementType(enumMap);
            }
        }
        enumMap = new EnumMap(QualifierApplicabilityType.class);
        obj = null;
        for (NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability2 : list) {
            component1 = nullabilityQualifierWithApplicability2.component1();
            while (r2.hasNext()) {
                enumMap.put(put, component1);
                obj = 1;
            }
        }
        if (obj != null) {
        }
    }

    private static final NullabilityQualifierWithApplicability extractDefaultNullabilityQualifier(LazyJavaResolverContext lazyJavaResolverContext, AnnotationDescriptor annotationDescriptor) {
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = lazyJavaResolverContext.getComponents().getAnnotationTypeQualifierResolver();
        NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation = annotationTypeQualifierResolver.resolveQualifierBuiltInDefaultAnnotation(annotationDescriptor);
        if (resolveQualifierBuiltInDefaultAnnotation != null) {
            return resolveQualifierBuiltInDefaultAnnotation;
        }
        TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation = annotationTypeQualifierResolver.resolveTypeQualifierDefaultAnnotation(annotationDescriptor);
        if (resolveTypeQualifierDefaultAnnotation == null) {
            return null;
        }
        AnnotationDescriptor component1 = resolveTypeQualifierDefaultAnnotation.component1();
        List component2 = resolveTypeQualifierDefaultAnnotation.component2();
        ReportLevel resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState == null) {
            resolveJsr305CustomState = annotationTypeQualifierResolver.resolveJsr305AnnotationState(component1);
        }
        if (resolveJsr305CustomState.isIgnore()) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus extractNullability = lazyJavaResolverContext.getComponents().getSignatureEnhancement().extractNullability(component1);
        if (extractNullability != null) {
            extractNullability = NullabilityQualifierWithMigrationStatus.copy$default(extractNullability, null, resolveJsr305CustomState.isWarning(), 1, null);
            if (extractNullability != null) {
                return new NullabilityQualifierWithApplicability(extractNullability, component2);
            }
        }
        return null;
    }

    public static final LazyJavaResolverContext replaceComponents(LazyJavaResolverContext lazyJavaResolverContext, JavaResolverComponents javaResolverComponents) {
        return new LazyJavaResolverContext(javaResolverComponents, lazyJavaResolverContext.getTypeParameterResolver(), lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    private static final LazyJavaResolverContext child(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, e<JavaTypeQualifiersByElementType> eVar) {
        TypeParameterResolver lazyJavaTypeParameterResolver;
        JavaResolverComponents components = lazyJavaResolverContext.getComponents();
        if (javaTypeParameterListOwner != null) {
            lazyJavaTypeParameterResolver = new LazyJavaTypeParameterResolver(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
        } else {
            lazyJavaTypeParameterResolver = lazyJavaResolverContext.getTypeParameterResolver();
        }
        return new LazyJavaResolverContext(components, lazyJavaTypeParameterResolver, eVar);
    }

    public static /* synthetic */ LazyJavaResolverContext childForMethod$default(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForMethod(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForMethod(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        return child(lazyJavaResolverContext, declarationDescriptor, javaTypeParameterListOwner, i, lazyJavaResolverContext.getDelegateForDefaultTypeQualifiers$descriptors_jvm());
    }

    public static /* synthetic */ LazyJavaResolverContext childForClassOrPackage$default(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            javaTypeParameterListOwner = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return childForClassOrPackage(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i);
    }

    public static final LazyJavaResolverContext childForClassOrPackage(LazyJavaResolverContext lazyJavaResolverContext, ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor, JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        return child(lazyJavaResolverContext, classOrPackageFragmentDescriptor, javaTypeParameterListOwner, i, h.a(j.NONE, new ContextKt$childForClassOrPackage$1(lazyJavaResolverContext, classOrPackageFragmentDescriptor)));
    }

    public static final LazyJavaResolverContext copyWithNewDefaultTypeQualifiers(LazyJavaResolverContext lazyJavaResolverContext, Annotations annotations) {
        if (annotations.isEmpty()) {
            return lazyJavaResolverContext;
        }
        return new LazyJavaResolverContext(lazyJavaResolverContext.getComponents(), lazyJavaResolverContext.getTypeParameterResolver(), h.a(j.NONE, new ContextKt$copyWithNewDefaultTypeQualifiers$1(lazyJavaResolverContext, annotations)));
    }
}
