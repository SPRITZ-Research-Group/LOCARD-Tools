package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution.Companion;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class MappingUtilKt {
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        Object obj = classDescriptor.getDeclaredTypeParameters().size() == classDescriptor2.getDeclaredTypeParameters().size() ? 1 : null;
        if (aa.a && obj == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(classDescriptor);
            stringBuilder.append(" and ");
            stringBuilder.append(classDescriptor2);
            stringBuilder.append(" should have same number of type parameters, but ");
            stringBuilder.append(classDescriptor.getDeclaredTypeParameters().size());
            stringBuilder.append(" / ");
            stringBuilder.append(classDescriptor2.getDeclaredTypeParameters().size());
            stringBuilder.append(" found");
            throw new AssertionError(stringBuilder.toString());
        }
        Companion companion = TypeConstructorSubstitution.Companion;
        Iterable<TypeParameterDescriptor> declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
        Collection arrayList = new ArrayList(acns.a((Iterable) declaredTypeParameters, 10));
        for (TypeParameterDescriptor typeConstructor : declaredTypeParameters) {
            arrayList.add(typeConstructor.getTypeConstructor());
        }
        Iterable iterable = (List) arrayList;
        declaredTypeParameters = classDescriptor2.getDeclaredTypeParameters();
        Collection arrayList2 = new ArrayList(acns.a((Iterable) declaredTypeParameters, 10));
        for (TypeParameterDescriptor defaultType : declaredTypeParameters) {
            arrayList2.add(TypeUtilsKt.asTypeProjection(defaultType.getDefaultType()));
        }
        return Companion.createByConstructorsMap$default(companion, acom.a((Iterable) acnz.d(iterable, (Iterable) (List) arrayList2)), false, 2, null);
    }
}
