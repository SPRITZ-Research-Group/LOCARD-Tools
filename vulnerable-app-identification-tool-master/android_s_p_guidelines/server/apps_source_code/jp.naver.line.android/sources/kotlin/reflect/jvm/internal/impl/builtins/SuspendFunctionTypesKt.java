package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.MutableClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class SuspendFunctionTypesKt {
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;

    public static final MutableClassDescriptor getFAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL() {
        return FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;
    }

    static {
        MutableClassDescriptor mutableClassDescriptor = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(ErrorUtils.getErrorModule(), DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor.setModality(Modality.ABSTRACT);
        mutableClassDescriptor.setVisibility(Visibilities.PUBLIC);
        mutableClassDescriptor.setTypeParameterDescriptors(Collections.singletonList(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier("T"), 0)));
        mutableClassDescriptor.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL = mutableClassDescriptor;
        MutableClassDescriptor mutableClassDescriptor2 = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(ErrorUtils.getErrorModule(), DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor2.setModality(Modality.ABSTRACT);
        mutableClassDescriptor2.setVisibility(Visibilities.PUBLIC);
        mutableClassDescriptor2.setTypeParameterDescriptors(Collections.singletonList(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor2, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier("T"), 0)));
        mutableClassDescriptor2.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE = mutableClassDescriptor2;
    }

    public static final MutableClassDescriptor getFAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE() {
        return FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;
    }

    public static final SimpleType transformSuspendFunctionToRuntimeFunctionType(KotlinType kotlinType, boolean z) {
        boolean isSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(kotlinType);
        if (!aa.a || isSuspendFunctionType) {
            TypeConstructor typeConstructor;
            KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
            Annotations annotations = kotlinType.getAnnotations();
            KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
            Iterable<TypeProjection> valueParameterTypesFromFunctionType = FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType);
            Collection arrayList = new ArrayList(acns.a((Iterable) valueParameterTypesFromFunctionType, 10));
            for (TypeProjection type : valueParameterTypesFromFunctionType) {
                arrayList.add(type.getType());
            }
            arrayList = (List) arrayList;
            Annotations empty = Annotations.Companion.getEMPTY();
            if (z) {
                typeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE.getTypeConstructor();
            } else {
                typeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL.getTypeConstructor();
            }
            return FunctionTypesKt.createFunctionType$default(builtIns, annotations, receiverTypeFromFunctionType, acnz.a(arrayList, (Object) KotlinTypeFactory.simpleType(empty, typeConstructor, Collections.singletonList(TypeUtilsKt.asTypeProjection(FunctionTypesKt.getReturnTypeFromFunctionType(kotlinType))), false)), null, TypeUtilsKt.getBuiltIns(kotlinType).getNullableAnyType(), false, 64, null).makeNullableAsSpecified(kotlinType.isMarkedNullable());
        }
        throw new AssertionError("This type should be suspend function type: ".concat(String.valueOf(kotlinType)));
    }

    public static final boolean isContinuation(FqName fqName, boolean z) {
        if (z) {
            return acry.a((Object) fqName, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE);
        }
        return acry.a((Object) fqName, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL);
    }
}
