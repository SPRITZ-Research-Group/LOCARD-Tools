package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public class JavaVisibilities {
    public static final Visibility PACKAGE_VISIBILITY = new Visibility("package", false) {
        public final String getDisplayName() {
            return "public/*package*/";
        }

        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.areInSamePackage(declarationDescriptorWithVisibility, declarationDescriptor);
        }

        protected final Integer compareTo(Visibility visibility) {
            if (this == visibility) {
                return Integer.valueOf(0);
            }
            if (Visibilities.isPrivate(visibility)) {
                return Integer.valueOf(1);
            }
            return Integer.valueOf(-1);
        }

        public final Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };
    public static final Visibility PROTECTED_AND_PACKAGE = new Visibility("protected_and_package", true) {
        public final String getDisplayName() {
            return "protected/*protected and package*/";
        }

        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        protected final Integer compareTo(Visibility visibility) {
            if (this == visibility) {
                return Integer.valueOf(0);
            }
            if (visibility == Visibilities.INTERNAL) {
                return null;
            }
            if (Visibilities.isPrivate(visibility)) {
                return Integer.valueOf(1);
            }
            return Integer.valueOf(-1);
        }

        public final Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };
    public static final Visibility PROTECTED_STATIC_VISIBILITY = new Visibility("protected_static", true) {
        public final String getDisplayName() {
            return "protected/*protected static*/";
        }

        public final boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        public final Visibility normalize() {
            return Visibilities.PROTECTED;
        }
    };

    private static boolean isVisibleForProtectedAndPackage(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor) {
        if (areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility), declarationDescriptor)) {
            return true;
        }
        return Visibilities.PROTECTED.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
    }

    private static boolean areInSamePackage(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor2, PackageFragmentDescriptor.class, false);
        if (packageFragmentDescriptor2 == null || packageFragmentDescriptor == null || !packageFragmentDescriptor.getFqName().equals(packageFragmentDescriptor2.getFqName())) {
            return false;
        }
        return true;
    }
}
