package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acrc;
import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;

public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    private DescriptorEquivalenceForOverrides() {
    }

    public final boolean areEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        if ((declarationDescriptor instanceof ClassDescriptor) && (declarationDescriptor2 instanceof ClassDescriptor)) {
            return areClassesEquivalent((ClassDescriptor) declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
        }
        if ((declarationDescriptor instanceof TypeParameterDescriptor) && (declarationDescriptor2 instanceof TypeParameterDescriptor)) {
            return areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, null, 4, null);
        }
        if ((declarationDescriptor instanceof CallableDescriptor) && (declarationDescriptor2 instanceof CallableDescriptor)) {
            return areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) declarationDescriptor, (CallableDescriptor) declarationDescriptor2, false, 4, null);
        }
        if ((declarationDescriptor instanceof PackageFragmentDescriptor) && (declarationDescriptor2 instanceof PackageFragmentDescriptor)) {
            return acry.a(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), ((PackageFragmentDescriptor) declarationDescriptor2).getFqName());
        }
        return acry.a((Object) declarationDescriptor, (Object) declarationDescriptor2);
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        return acry.a(classDescriptor.getTypeConstructor(), classDescriptor2.getTypeConstructor());
    }

    static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, acrc acrc, int i, Object obj) {
        if ((i & 4) != 0) {
            acrc = DescriptorEquivalenceForOverrides$areTypeParametersEquivalent$1.INSTANCE;
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, acrc);
    }

    private final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, acrc<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> acrc) {
        if (acry.a((Object) typeParameterDescriptor, (Object) typeParameterDescriptor2)) {
            return true;
        }
        if (!acry.a(typeParameterDescriptor.getContainingDeclaration(), typeParameterDescriptor2.getContainingDeclaration()) && ownersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, acrc) && typeParameterDescriptor.getIndex() == typeParameterDescriptor2.getIndex()) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z);
    }

    public final boolean areCallableDescriptorsEquivalent(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z) {
        if (acry.a((Object) callableDescriptor, (Object) callableDescriptor2)) {
            return true;
        }
        if ((acry.a(callableDescriptor.getName(), callableDescriptor2.getName()) ^ 1) != 0 || acry.a(callableDescriptor.getContainingDeclaration(), callableDescriptor2.getContainingDeclaration())) {
            return false;
        }
        DeclarationDescriptor declarationDescriptor = callableDescriptor;
        if (!DescriptorUtils.isLocal(declarationDescriptor)) {
            DeclarationDescriptor declarationDescriptor2 = callableDescriptor2;
            if (DescriptorUtils.isLocal(declarationDescriptor2) || !ownersEquivalent(declarationDescriptor, declarationDescriptor2, DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1.INSTANCE)) {
                return false;
            }
            OverridingUtil createWithEqualityAxioms = OverridingUtil.createWithEqualityAxioms(new DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1(callableDescriptor, callableDescriptor2));
            if (createWithEqualityAxioms.isOverridableBy(callableDescriptor, callableDescriptor2, null, z ^ 1).getResult() == Result.OVERRIDABLE && createWithEqualityAxioms.isOverridableBy(callableDescriptor2, callableDescriptor, null, z ^ true).getResult() == Result.OVERRIDABLE) {
                return true;
            }
            return false;
        }
        return false;
    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, acrc<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> acrc) {
        declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        declarationDescriptor2 = declarationDescriptor2.getContainingDeclaration();
        if ((declarationDescriptor instanceof CallableMemberDescriptor) || (declarationDescriptor2 instanceof CallableMemberDescriptor)) {
            return ((Boolean) acrc.invoke(declarationDescriptor, declarationDescriptor2)).booleanValue();
        }
        return areEquivalent(declarationDescriptor, declarationDescriptor2);
    }
}
