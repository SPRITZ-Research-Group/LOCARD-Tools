package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acnz;
import defpackage.acqr;
import defpackage.adbu;
import defpackage.adbw;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class FindClassInModuleKt {
    public static final ClassDescriptor findClassAcrossModuleDependencies(ModuleDescriptor moduleDescriptor, ClassId classId) {
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(classId.getPackageFqName());
        List pathSegments = classId.getRelativeClassName().pathSegments();
        ClassifierDescriptor contributedClassifier = packageViewDescriptor.getMemberScope().getContributedClassifier((Name) acnz.e(pathSegments), NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name contributedClassifier2 : pathSegments.subList(1, pathSegments.size())) {
            contributedClassifier = classDescriptor.getUnsubstitutedInnerClassesScope().getContributedClassifier(contributedClassifier2, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier instanceof ClassDescriptor)) {
                contributedClassifier = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier;
            if (classDescriptor == null) {
                return null;
            }
        }
        return classDescriptor;
    }

    public static final ClassDescriptor findNonGenericClassAcrossDependencies(ModuleDescriptor moduleDescriptor, ClassId classId, NotFoundClasses notFoundClasses) {
        ClassDescriptor findClassAcrossModuleDependencies = findClassAcrossModuleDependencies(moduleDescriptor, classId);
        if (findClassAcrossModuleDependencies != null) {
            return findClassAcrossModuleDependencies;
        }
        return notFoundClasses.getClass(classId, adbw.e(adbw.d(adbu.a((Object) classId, (acqr) FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE), FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2.INSTANCE)));
    }

    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(ModuleDescriptor moduleDescriptor, ClassId classId) {
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(classId.getPackageFqName());
        List pathSegments = classId.getRelativeClassName().pathSegments();
        int size = pathSegments.size() - 1;
        ClassifierDescriptor contributedClassifier = packageViewDescriptor.getMemberScope().getContributedClassifier((Name) acnz.e(pathSegments), NoLookupLocation.FROM_DESERIALIZATION);
        if (size == 0) {
            if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
                contributedClassifier = null;
            }
            return (TypeAliasDescriptor) contributedClassifier;
        }
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name contributedClassifier2 : pathSegments.subList(1, size)) {
            contributedClassifier = classDescriptor.getUnsubstitutedInnerClassesScope().getContributedClassifier(contributedClassifier2, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier instanceof ClassDescriptor)) {
                contributedClassifier = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier;
            if (classDescriptor == null) {
                return null;
            }
        }
        contributedClassifier = classDescriptor.getUnsubstitutedMemberScope().getContributedClassifier((Name) pathSegments.get(size), NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
            contributedClassifier = null;
        }
        return (TypeAliasDescriptor) contributedClassifier;
    }
}
