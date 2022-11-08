package kotlin.reflect.jvm.internal.impl.resolve;

import defpackage.acnz;
import defpackage.acry;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.v;

public final class InlineClassesUtilsKt {
    public static final ValueParameterDescriptor underlyingRepresentation(ClassDescriptor classDescriptor) {
        if (!classDescriptor.isInline()) {
            return null;
        }
        ClassConstructorDescriptor unsubstitutedPrimaryConstructor = classDescriptor.getUnsubstitutedPrimaryConstructor();
        if (unsubstitutedPrimaryConstructor != null) {
            List valueParameters = unsubstitutedPrimaryConstructor.getValueParameters();
            if (valueParameters != null) {
                return (ValueParameterDescriptor) acnz.j(valueParameters);
            }
        }
        return null;
    }

    public static final boolean isInlineClass(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof ClassDescriptor) && ((ClassDescriptor) declarationDescriptor).isInline();
    }

    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        return classDescriptor != null ? underlyingRepresentation(classDescriptor) : null;
    }

    public static final boolean isInlineClassType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return declarationDescriptor != null ? isInlineClass(declarationDescriptor) : false;
    }

    public static final KotlinType substitutedUnderlyingType(KotlinType kotlinType) {
        ValueParameterDescriptor unsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(kotlinType);
        if (unsubstitutedUnderlyingParameter == null) {
            return null;
        }
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) acnz.f((Iterable) kotlinType.getMemberScope().getContributedVariables(unsubstitutedUnderlyingParameter.getName(), NoLookupLocation.FOR_ALREADY_TRACKED));
        if (propertyDescriptor != null) {
            return propertyDescriptor.getType();
        }
        return null;
    }

    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(CallableDescriptor callableDescriptor) {
        return (callableDescriptor instanceof PropertyGetterDescriptor) && isUnderlyingPropertyOfInlineClass(((PropertyGetterDescriptor) callableDescriptor).getCorrespondingProperty());
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(VariableDescriptor variableDescriptor) {
        DeclarationDescriptor containingDeclaration = variableDescriptor.getContainingDeclaration();
        if (!isInlineClass(containingDeclaration)) {
            return false;
        }
        if (containingDeclaration != null) {
            ValueParameterDescriptor underlyingRepresentation = underlyingRepresentation((ClassDescriptor) containingDeclaration);
            return acry.a(underlyingRepresentation != null ? underlyingRepresentation.getName() : null, variableDescriptor.getName());
        }
        throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }
}
