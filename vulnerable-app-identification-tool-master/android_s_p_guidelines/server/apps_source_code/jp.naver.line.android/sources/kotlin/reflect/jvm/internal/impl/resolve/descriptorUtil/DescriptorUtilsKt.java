package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import defpackage.acnz;
import defpackage.acob;
import defpackage.acqr;
import defpackage.acsn;
import defpackage.adbh;
import defpackage.adbi;
import defpackage.adbo;
import defpackage.adbu;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.v;

public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME = Name.identifier("value");

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getFqName(declarationDescriptor);
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getFqNameSafe(declarationDescriptor);
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor declarationDescriptor) {
        return DescriptorUtils.getContainingModule(declarationDescriptor);
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor moduleDescriptor, FqName fqName, LookupLocation lookupLocation) {
        int isRoot = fqName.isRoot() ^ 1;
        if (aa.a && isRoot == 0) {
            throw new AssertionError("Assertion failed");
        }
        ClassifierDescriptor contributedClassifier = moduleDescriptor.getPackage(fqName.parent()).getMemberScope().getContributedClassifier(fqName.shortName(), lookupLocation);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        return (ClassDescriptor) contributedClassifier;
    }

    public static final ClassId getClassId(ClassifierDescriptor classifierDescriptor) {
        if (classifierDescriptor != null) {
            DeclarationDescriptor containingDeclaration = classifierDescriptor.getContainingDeclaration();
            if (containingDeclaration != null) {
                if (containingDeclaration instanceof PackageFragmentDescriptor) {
                    return new ClassId(((PackageFragmentDescriptor) containingDeclaration).getFqName(), classifierDescriptor.getName());
                }
                if (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) {
                    ClassId classId = getClassId((ClassifierDescriptor) containingDeclaration);
                    if (classId != null) {
                        return classId.createNestedClassId(classifierDescriptor.getName());
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor classDescriptor) {
        for (KotlinType kotlinType : classDescriptor.getDefaultType().getConstructor().getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(declarationDescriptor)) {
                    if (declarationDescriptor != null) {
                        return (ClassDescriptor) declarationDescriptor;
                    }
                    throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor declarationDescriptor) {
        return getModule(declarationDescriptor).getBuiltIns();
    }

    public static final adbo<DeclarationDescriptor> getParentsWithSelf(DeclarationDescriptor declarationDescriptor) {
        return adbu.a((Object) declarationDescriptor, (acqr) DescriptorUtilsKt$parentsWithSelf$1.INSTANCE);
    }

    public static final adbo<DeclarationDescriptor> getParents(DeclarationDescriptor declarationDescriptor) {
        adbo parentsWithSelf = getParentsWithSelf(declarationDescriptor);
        if (parentsWithSelf instanceof adbi) {
            return ((adbi) parentsWithSelf).b();
        }
        return new adbh(parentsWithSelf, 1);
    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor callableMemberDescriptor) {
        return callableMemberDescriptor instanceof PropertyAccessorDescriptor ? ((PropertyAccessorDescriptor) callableMemberDescriptor).getCorrespondingProperty() : callableMemberDescriptor;
    }

    public static final FqName fqNameOrNull(DeclarationDescriptor declarationDescriptor) {
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(declarationDescriptor);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        return fqNameUnsafe != null ? fqNameUnsafe.toSafe() : null;
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, acqr acqr, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, acqr);
    }

    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor callableMemberDescriptor, boolean z, acqr<? super CallableMemberDescriptor, Boolean> acqr) {
        acsn acsn = new acsn();
        acsn.a = null;
        return (CallableMemberDescriptor) DFS.dfs(Collections.singletonList(callableMemberDescriptor), new DescriptorUtilsKt$firstOverridden$1(z), new DescriptorUtilsKt$firstOverridden$2(acsn, acqr));
    }

    public static final Collection<ClassDescriptor> computeSealedSubclasses(ClassDescriptor classDescriptor) {
        if (classDescriptor.getModality() != Modality.SEALED) {
            return acob.a;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        DescriptorUtilsKt$computeSealedSubclasses$1 descriptorUtilsKt$computeSealedSubclasses$1 = new DescriptorUtilsKt$computeSealedSubclasses$1(classDescriptor, linkedHashSet);
        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            descriptorUtilsKt$computeSealedSubclasses$1.invoke(((PackageFragmentDescriptor) containingDeclaration).getMemberScope(), false);
        }
        descriptorUtilsKt$computeSealedSubclasses$1.invoke(classDescriptor.getUnsubstitutedInnerClassesScope(), true);
        return linkedHashSet;
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor annotationDescriptor) {
        ClassifierDescriptor declarationDescriptor = annotationDescriptor.getType().getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        return (ClassDescriptor) declarationDescriptor;
    }

    public static final ConstantValue<?> firstArgument(AnnotationDescriptor annotationDescriptor) {
        return (ConstantValue) acnz.c((Iterable) annotationDescriptor.getAllValueArguments().values());
    }

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor valueParameterDescriptor) {
        return DFS.ifAny(Collections.singletonList(valueParameterDescriptor), DescriptorUtilsKt$declaresOrInheritsDefaultValue$1.INSTANCE, DescriptorUtilsKt$declaresOrInheritsDefaultValue$2.INSTANCE).booleanValue();
    }
}
