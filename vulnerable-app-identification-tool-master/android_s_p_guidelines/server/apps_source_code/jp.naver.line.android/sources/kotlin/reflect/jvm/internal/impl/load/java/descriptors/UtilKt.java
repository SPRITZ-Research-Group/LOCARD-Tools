package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import defpackage.acns;
import defpackage.acnz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.aa;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class UtilKt {
    public static final List<ValueParameterDescriptor> copyValueParameters(Collection<ValueParameterData> collection, Collection<? extends ValueParameterDescriptor> collection2, CallableDescriptor callableDescriptor) {
        Object obj = collection.size() == collection2.size() ? 1 : null;
        if (aa.a && obj == null) {
            StringBuilder stringBuilder = new StringBuilder("Different value parameters sizes: Enhanced = ");
            stringBuilder.append(collection.size());
            stringBuilder.append(", Old = ");
            stringBuilder.append(collection2.size());
            throw new AssertionError(stringBuilder.toString());
        }
        Iterable<m> d = acnz.d((Iterable) collection, (Iterable) collection2);
        Collection arrayList = new ArrayList(acns.a((Iterable) d, 10));
        for (m mVar : d) {
            ValueParameterData valueParameterData = (ValueParameterData) mVar.c();
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) mVar.d();
            int index = valueParameterDescriptor.getIndex();
            Annotations annotations = valueParameterDescriptor.getAnnotations();
            Name name = valueParameterDescriptor.getName();
            KotlinType type = valueParameterData.getType();
            boolean hasDefaultValue = valueParameterData.getHasDefaultValue();
            boolean isCrossinline = valueParameterDescriptor.isCrossinline();
            boolean isNoinline = valueParameterDescriptor.isNoinline();
            KotlinType arrayElementType = valueParameterDescriptor.getVarargElementType() != null ? DescriptorUtilsKt.getModule(callableDescriptor).getBuiltIns().getArrayElementType(valueParameterData.getType()) : null;
            ValueParameterDescriptorImpl valueParameterDescriptorImpl = r4;
            ValueParameterDescriptorImpl valueParameterDescriptorImpl2 = new ValueParameterDescriptorImpl(callableDescriptor, null, index, annotations, name, type, hasDefaultValue, isCrossinline, isNoinline, arrayElementType, valueParameterDescriptor.getSource());
            arrayList.add(valueParameterDescriptorImpl);
        }
        return (List) arrayList;
    }

    public static final LazyJavaStaticClassScope getParentJavaStaticClassScope(ClassDescriptor classDescriptor) {
        while (true) {
            classDescriptor = DescriptorUtilsKt.getSuperClassNotAny(classDescriptor);
            if (classDescriptor == null) {
                return null;
            }
            MemberScope staticScope = classDescriptor.getStaticScope();
            if (staticScope instanceof LazyJavaStaticClassScope) {
                return (LazyJavaStaticClassScope) staticScope;
            }
        }
    }

    public static final JvmClassName getImplClassNameForDeserialized(DeserializedMemberDescriptor deserializedMemberDescriptor) {
        DeserializedContainerSource containerSource = deserializedMemberDescriptor.getContainerSource();
        if (!(containerSource instanceof JvmPackagePartSource)) {
            containerSource = null;
        }
        JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) containerSource;
        return jvmPackagePartSource != null ? jvmPackagePartSource.getClassName() : null;
    }

    public static final AnnotationDefaultValue getDefaultValueFromAnnotation(ValueParameterDescriptor valueParameterDescriptor) {
        AnnotationDescriptor findAnnotation = valueParameterDescriptor.getAnnotations().findAnnotation(JvmAnnotationNames.DEFAULT_VALUE_FQ_NAME);
        if (findAnnotation != null) {
            ConstantValue firstArgument = DescriptorUtilsKt.firstArgument(findAnnotation);
            if (firstArgument != null) {
                if (!(firstArgument instanceof StringValue)) {
                    firstArgument = null;
                }
                StringValue stringValue = (StringValue) firstArgument;
                if (stringValue != null) {
                    String str = (String) stringValue.getValue();
                    if (str != null) {
                        return new StringDefaultValue(str);
                    }
                }
            }
        }
        if (valueParameterDescriptor.getAnnotations().hasAnnotation(JvmAnnotationNames.DEFAULT_NULL_FQ_NAME)) {
            return NullDefaultValue.INSTANCE;
        }
        return null;
    }
}
