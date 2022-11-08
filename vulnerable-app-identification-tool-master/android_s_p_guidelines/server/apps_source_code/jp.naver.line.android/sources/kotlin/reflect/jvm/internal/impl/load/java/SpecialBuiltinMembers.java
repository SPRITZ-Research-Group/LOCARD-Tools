package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;
import kotlin.v;

public final class SpecialBuiltinMembers {
    private static final FqName child(FqName fqName, String str) {
        return fqName.child(Name.identifier(str));
    }

    private static final FqName childSafe(FqNameUnsafe fqNameUnsafe, String str) {
        return fqNameUnsafe.child(Name.identifier(str)).toSafe();
    }

    private static final NameAndSignature method(String str, String str2, String str3, String str4) {
        Name identifier = Name.identifier(str2);
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append('(');
        stringBuilder.append(str3);
        stringBuilder.append(')');
        stringBuilder.append(str4);
        return new NameAndSignature(identifier, signatureBuildingComponents.signature(str, stringBuilder.toString()));
    }

    public static final <T extends CallableMemberDescriptor> T getOverriddenBuiltinWithDifferentJvmName(T t) {
        if (!BuiltinMethodsWithDifferentJvmName.INSTANCE.getORIGINAL_SHORT_NAMES().contains(t.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES$descriptors_jvm().contains(DescriptorUtilsKt.getPropertyIfAccessor(t).getName())) {
            return null;
        }
        if ((t instanceof PropertyDescriptor) || (t instanceof PropertyAccessorDescriptor)) {
            return DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$1.INSTANCE, 1, null);
        }
        if (t instanceof SimpleFunctionDescriptor) {
            return DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$2.INSTANCE, 1, null);
        }
        return null;
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor) != null;
    }

    public static final <T extends CallableMemberDescriptor> T getOverriddenSpecialBuiltin(T t) {
        T overriddenBuiltinWithDifferentJvmName = getOverriddenBuiltinWithDifferentJvmName(t);
        if (overriddenBuiltinWithDifferentJvmName != null) {
            return overriddenBuiltinWithDifferentJvmName;
        }
        if (BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(t.getName())) {
            return DescriptorUtilsKt.firstOverridden$default(t, false, SpecialBuiltinMembers$getOverriddenSpecialBuiltin$2.INSTANCE, 1, null);
        }
        return null;
    }

    public static final String getJvmMethodNameIfSpecial(CallableMemberDescriptor callableMemberDescriptor) {
        callableMemberDescriptor = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        if (callableMemberDescriptor != null) {
            callableMemberDescriptor = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor);
            if (callableMemberDescriptor != null) {
                if (callableMemberDescriptor instanceof PropertyDescriptor) {
                    return BuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(callableMemberDescriptor);
                }
                if (callableMemberDescriptor instanceof SimpleFunctionDescriptor) {
                    Name jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) callableMemberDescriptor);
                    if (jvmName != null) {
                        return jvmName.asString();
                    }
                }
                return null;
            }
        }
        return null;
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        return KotlinBuiltIns.isBuiltIn((DeclarationDescriptor) callableMemberDescriptor) ? getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor) : null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(ClassDescriptor classDescriptor, CallableDescriptor callableDescriptor) {
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            SimpleType defaultType = ((ClassDescriptor) containingDeclaration).getDefaultType();
            for (classDescriptor = DescriptorUtils.getSuperClassDescriptor(classDescriptor); classDescriptor != null; classDescriptor = DescriptorUtils.getSuperClassDescriptor(classDescriptor)) {
                if (!(classDescriptor instanceof JavaClassDescriptor)) {
                    if ((TypeCheckingProcedure.findCorrespondingSupertype((KotlinType) classDescriptor.getDefaultType(), (KotlinType) defaultType) != null ? 1 : null) != null) {
                        if (KotlinBuiltIns.isBuiltIn(classDescriptor)) {
                            return false;
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    public static final boolean isFromJava(CallableMemberDescriptor callableMemberDescriptor) {
        return DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(CallableMemberDescriptor callableMemberDescriptor) {
        return isFromJava(callableMemberDescriptor) || KotlinBuiltIns.isBuiltIn(callableMemberDescriptor);
    }
}
