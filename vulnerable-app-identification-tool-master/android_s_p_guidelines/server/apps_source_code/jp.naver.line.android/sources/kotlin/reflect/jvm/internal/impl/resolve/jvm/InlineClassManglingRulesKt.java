package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import defpackage.acry;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.load.kotlin.TypeSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class InlineClassManglingRulesKt {
    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(CallableMemberDescriptor callableMemberDescriptor) {
        if (!(callableMemberDescriptor instanceof ClassConstructorDescriptor)) {
            callableMemberDescriptor = null;
        }
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) callableMemberDescriptor;
        if (classConstructorDescriptor == null || Visibilities.isPrivate(classConstructorDescriptor.getVisibility()) || classConstructorDescriptor.getConstructedClass().isInline() || DescriptorUtils.isSealedClass(classConstructorDescriptor.getConstructedClass())) {
            return false;
        }
        Iterable<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
        if (!((valueParameters instanceof Collection) && ((Collection) valueParameters).isEmpty())) {
            for (ValueParameterDescriptor type : valueParameters) {
                if (requiresFunctionNameMangling(type.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(DeclarationDescriptor declarationDescriptor) {
        return InlineClassesUtilsKt.isInlineClass(declarationDescriptor) && !isDontMangleClass((ClassDescriptor) declarationDescriptor);
    }

    public static final boolean isInlineClassThatRequiresMangling(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return declarationDescriptor != null && isInlineClassThatRequiresMangling((DeclarationDescriptor) declarationDescriptor);
    }

    private static final boolean requiresFunctionNameMangling(KotlinType kotlinType) {
        return isInlineClassThatRequiresMangling(kotlinType) || isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType);
    }

    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return acry.a(DescriptorUtilsKt.getFqNameSafe(classDescriptor), DescriptorUtils.RESULT_FQ_NAME);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof TypeParameterDescriptor)) {
            declarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) declarationDescriptor;
        if (typeParameterDescriptor == null) {
            return false;
        }
        return requiresFunctionNameMangling(TypeSignatureMappingKt.getRepresentativeUpperBound(typeParameterDescriptor));
    }
}
