package kotlin.reflect.jvm.internal.impl.descriptors;

import defpackage.acns;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acqr;
import defpackage.adbo;
import defpackage.adbw;
import defpackage.adbz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

public final class TypeParameterUtilsKt {
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters) {
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        if (!classifierDescriptorWithTypeParameters.isInner() && !(classifierDescriptorWithTypeParameters.getContainingDeclaration() instanceof CallableDescriptor)) {
            return declaredTypeParameters;
        }
        Object obj;
        List list;
        DeclarationDescriptor declarationDescriptor = classifierDescriptorWithTypeParameters;
        List e = adbw.e(adbw.c((adbo) new adbz(DescriptorUtilsKt.getParents(declarationDescriptor), TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1.INSTANCE), (acqr) TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2.INSTANCE));
        Iterator a = DescriptorUtilsKt.getParents(declarationDescriptor).a();
        do {
            list = null;
            if (!a.hasNext()) {
                obj = null;
                break;
            }
            obj = a.next();
        } while (!(obj instanceof ClassDescriptor));
        ClassDescriptor classDescriptor = (ClassDescriptor) obj;
        if (classDescriptor != null) {
            TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
            if (typeConstructor != null) {
                list = typeConstructor.getParameters();
            }
        }
        if (list == null) {
            list = acob.a;
        }
        if (e.isEmpty() && list.isEmpty()) {
            return classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        }
        Iterable<TypeParameterDescriptor> b = acnz.b((Collection) e, (Iterable) list);
        Collection arrayList = new ArrayList(acns.a((Iterable) b, 10));
        for (TypeParameterDescriptor capturedCopyForInnerDeclaration : b) {
            arrayList.add(capturedCopyForInnerDeclaration(capturedCopyForInnerDeclaration, declarationDescriptor, declaredTypeParameters.size()));
        }
        return acnz.b((Collection) declaredTypeParameters, (Iterable) (List) arrayList);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassifierDescriptorWithTypeParameters)) {
            declarationDescriptor = null;
        }
        return buildPossiblyInnerType(kotlinType, (ClassifierDescriptorWithTypeParameters) declarationDescriptor, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        PossiblyInnerType possiblyInnerType = null;
        if (classifierDescriptorWithTypeParameters != null) {
            DeclarationDescriptor declarationDescriptor = classifierDescriptorWithTypeParameters;
            if (!ErrorUtils.isError(declarationDescriptor)) {
                int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
                if (classifierDescriptorWithTypeParameters.isInner()) {
                    List subList = kotlinType.getArguments().subList(i, size);
                    DeclarationDescriptor containingDeclaration = classifierDescriptorWithTypeParameters.getContainingDeclaration();
                    if (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) {
                        possiblyInnerType = containingDeclaration;
                    }
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, subList, buildPossiblyInnerType(kotlinType, (ClassifierDescriptorWithTypeParameters) possiblyInnerType, size));
                }
                Object obj = (size == kotlinType.getArguments().size() || DescriptorUtils.isLocal(declarationDescriptor)) ? 1 : null;
                if (!aa.a || obj != null) {
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), null);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(kotlinType.getArguments().size() - size);
                stringBuilder.append(" trailing arguments were found in ");
                stringBuilder.append(kotlinType);
                stringBuilder.append(" type");
                throw new AssertionError(stringBuilder.toString());
            }
        }
        return null;
    }
}
