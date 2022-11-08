package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collections;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public class DescriptorFactory {

    class DefaultClassConstructorDescriptor extends ClassConstructorDescriptorImpl {
        public DefaultClassConstructorDescriptor(ClassDescriptor classDescriptor, SourceElement sourceElement) {
            super(classDescriptor, null, Annotations.Companion.getEMPTY(), true, Kind.DECLARATION, sourceElement);
            initialize(Collections.emptyList(), DescriptorUtils.getDefaultConstructorVisibility(classDescriptor));
        }
    }

    public static PropertySetterDescriptorImpl createDefaultSetter(PropertyDescriptor propertyDescriptor, Annotations annotations, Annotations annotations2) {
        return createSetter(propertyDescriptor, annotations, annotations2, true, false, false, propertyDescriptor.getSource());
    }

    public static PropertySetterDescriptorImpl createSetter(PropertyDescriptor propertyDescriptor, Annotations annotations, Annotations annotations2, boolean z, boolean z2, boolean z3, SourceElement sourceElement) {
        return createSetter(propertyDescriptor, annotations, annotations2, z, z2, z3, propertyDescriptor.getVisibility(), sourceElement);
    }

    public static PropertySetterDescriptorImpl createSetter(PropertyDescriptor propertyDescriptor, Annotations annotations, Annotations annotations2, boolean z, boolean z2, boolean z3, Visibility visibility, SourceElement sourceElement) {
        PropertySetterDescriptorImpl propertySetterDescriptorImpl = new PropertySetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), visibility, z, z2, z3, Kind.DECLARATION, null, sourceElement);
        Annotations annotations3 = annotations2;
        propertySetterDescriptorImpl.initialize(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl, propertyDescriptor.getType(), annotations2));
        return propertySetterDescriptorImpl;
    }

    public static PropertyGetterDescriptorImpl createDefaultGetter(PropertyDescriptor propertyDescriptor, Annotations annotations) {
        return createGetter(propertyDescriptor, annotations, true, false, false);
    }

    public static PropertyGetterDescriptorImpl createGetter(PropertyDescriptor propertyDescriptor, Annotations annotations, boolean z, boolean z2, boolean z3) {
        return createGetter(propertyDescriptor, annotations, z, z2, z3, propertyDescriptor.getSource());
    }

    public static PropertyGetterDescriptorImpl createGetter(PropertyDescriptor propertyDescriptor, Annotations annotations, boolean z, boolean z2, boolean z3, SourceElement sourceElement) {
        return new PropertyGetterDescriptorImpl(propertyDescriptor, annotations, propertyDescriptor.getModality(), propertyDescriptor.getVisibility(), z, z2, z3, Kind.DECLARATION, null, sourceElement);
    }

    public static ClassConstructorDescriptorImpl createPrimaryConstructorForObject(ClassDescriptor classDescriptor, SourceElement sourceElement) {
        return new DefaultClassConstructorDescriptor(classDescriptor, sourceElement);
    }

    public static SimpleFunctionDescriptor createEnumValuesMethod(ClassDescriptor classDescriptor) {
        return SimpleFunctionDescriptorImpl.create(classDescriptor, Annotations.Companion.getEMPTY(), DescriptorUtils.ENUM_VALUES, Kind.SYNTHESIZED, classDescriptor.getSource()).initialize(null, null, Collections.emptyList(), Collections.emptyList(), DescriptorUtilsKt.getBuiltIns(classDescriptor).getArrayType(Variance.INVARIANT, classDescriptor.getDefaultType()), Modality.FINAL, Visibilities.PUBLIC);
    }

    public static SimpleFunctionDescriptor createEnumValueOfMethod(ClassDescriptor classDescriptor) {
        SimpleFunctionDescriptorImpl create = SimpleFunctionDescriptorImpl.create(classDescriptor, Annotations.Companion.getEMPTY(), DescriptorUtils.ENUM_VALUE_OF, Kind.SYNTHESIZED, classDescriptor.getSource());
        return create.initialize(null, null, Collections.emptyList(), Collections.singletonList(new ValueParameterDescriptorImpl(create, null, 0, Annotations.Companion.getEMPTY(), Name.identifier("value"), DescriptorUtilsKt.getBuiltIns(classDescriptor).getStringType(), false, false, false, null, classDescriptor.getSource())), classDescriptor.getDefaultType(), Modality.FINAL, Visibilities.PUBLIC);
    }

    public static boolean isEnumValuesMethod(FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getName().equals(DescriptorUtils.ENUM_VALUES) && isEnumSpecialMethod(functionDescriptor);
    }

    public static boolean isEnumValueOfMethod(FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getName().equals(DescriptorUtils.ENUM_VALUE_OF) && isEnumSpecialMethod(functionDescriptor);
    }

    private static boolean isEnumSpecialMethod(FunctionDescriptor functionDescriptor) {
        return functionDescriptor.getKind() == Kind.SYNTHESIZED && DescriptorUtils.isEnumClass(functionDescriptor.getContainingDeclaration());
    }

    public static ReceiverParameterDescriptor createExtensionReceiverParameterForCallable(CallableDescriptor callableDescriptor, KotlinType kotlinType, Annotations annotations) {
        return kotlinType == null ? null : new ReceiverParameterDescriptorImpl(callableDescriptor, new ExtensionReceiver(callableDescriptor, kotlinType, null), annotations);
    }
}
