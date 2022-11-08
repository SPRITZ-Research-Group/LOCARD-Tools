package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acns;
import defpackage.acnz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.v;

public final class StarProjectionImplKt {
    public static final KotlinType starProjectionType(TypeParameterDescriptor typeParameterDescriptor) {
        DeclarationDescriptor containingDeclaration = typeParameterDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            Iterable<TypeParameterDescriptor> parameters = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor().getParameters();
            Collection arrayList = new ArrayList(acns.a((Iterable) parameters, 10));
            for (TypeParameterDescriptor typeConstructor : parameters) {
                arrayList.add(typeConstructor.getTypeConstructor());
            }
            KotlinType substitute = TypeSubstitutor.create((TypeSubstitution) new StarProjectionImplKt$starProjectionType$1((List) arrayList)).substitute((KotlinType) acnz.e(typeParameterDescriptor.getUpperBounds()), Variance.OUT_VARIANCE);
            return substitute == null ? DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getDefaultBound() : substitute;
        } else {
            throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
        }
    }
}
