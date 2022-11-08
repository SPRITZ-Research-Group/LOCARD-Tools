package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public final class DescriptorUtilKt {
    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor moduleDescriptor, FqName fqName, LookupLocation lookupLocation) {
        if (fqName.isRoot()) {
            return null;
        }
        ClassifierDescriptor contributedClassifier = moduleDescriptor.getPackage(fqName.parent()).getMemberScope().getContributedClassifier(fqName.shortName(), lookupLocation);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        ClassifierDescriptor contributedClassifier2;
        ClassDescriptor resolveClassByFqName = resolveClassByFqName(moduleDescriptor, fqName.parent(), lookupLocation);
        if (resolveClassByFqName != null) {
            MemberScope unsubstitutedInnerClassesScope = resolveClassByFqName.getUnsubstitutedInnerClassesScope();
            if (unsubstitutedInnerClassesScope != null) {
                contributedClassifier2 = unsubstitutedInnerClassesScope.getContributedClassifier(fqName.shortName(), lookupLocation);
                if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                    contributedClassifier2 = null;
                }
                return (ClassDescriptor) contributedClassifier2;
            }
        }
        contributedClassifier2 = null;
        if (contributedClassifier2 instanceof ClassDescriptor) {
            contributedClassifier2 = null;
        }
        return (ClassDescriptor) contributedClassifier2;
    }
}
