package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acnr;
import defpackage.acnz;
import defpackage.acol;
import defpackage.acom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.aa;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import kotlin.u;

public final class FunctionTypesKt {
    public static final boolean isFunctionType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null) == Kind.Function;
    }

    public static final boolean isSuspendFunctionType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return (declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null) == Kind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(KotlinType kotlinType) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        Kind functionalClassKind = declarationDescriptor != null ? getFunctionalClassKind((DeclarationDescriptor) declarationDescriptor) : null;
        return functionalClassKind == Kind.Function || functionalClassKind == Kind.SuspendFunction;
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType kotlinType) {
        return isBuiltinFunctionalType(kotlinType) && isTypeAnnotatedWithExtensionFunctionType(kotlinType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType) {
        return kotlinType.getAnnotations().findAnnotation(KotlinBuiltIns.FQ_NAMES.extensionFunctionType) != null;
    }

    public static final Kind getFunctionalClassKind(DeclarationDescriptor declarationDescriptor) {
        if ((declarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor));
        }
        return null;
    }

    private static final Kind getFunctionalClassKind(FqNameUnsafe fqNameUnsafe) {
        return (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) ? null : BuiltInFictitiousFunctionClassFactory.Companion.getFunctionalClassKind(fqNameUnsafe.shortName().asString(), fqNameUnsafe.toSafe().parent());
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType kotlinType) {
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (!aa.a || isBuiltinFunctionalType) {
            return isTypeAnnotatedWithExtensionFunctionType(kotlinType) ? ((TypeProjection) acnz.e(kotlinType.getArguments())).getType() : null;
        } else {
            throw new AssertionError("Not a function type: ".concat(String.valueOf(kotlinType)));
        }
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType kotlinType) {
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (!aa.a || isBuiltinFunctionalType) {
            return ((TypeProjection) acnz.g(kotlinType.getArguments())).getType();
        }
        throw new AssertionError("Not a function type: ".concat(String.valueOf(kotlinType)));
    }

    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(KotlinType kotlinType) {
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(kotlinType);
        if (!aa.a || isBuiltinFunctionalType) {
            List arguments = kotlinType.getArguments();
            boolean isBuiltinExtensionFunctionalType = isBuiltinExtensionFunctionalType(kotlinType);
            int i = 1;
            boolean size = arguments.size() - 1;
            if (isBuiltinExtensionFunctionalType > size) {
                i = 0;
            }
            if (!aa.a || i != 0) {
                return arguments.subList(isBuiltinExtensionFunctionalType, size);
            }
            throw new AssertionError("Not an exact function type: ".concat(String.valueOf(kotlinType)));
        }
        throw new AssertionError("Not a function type: ".concat(String.valueOf(kotlinType)));
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType kotlinType) {
        AnnotationDescriptor findAnnotation = kotlinType.getAnnotations().findAnnotation(KotlinBuiltIns.FQ_NAMES.parameterName);
        if (findAnnotation == null) {
            return null;
        }
        Object f = acnz.f((Iterable) findAnnotation.getAllValueArguments().values());
        if (!(f instanceof StringValue)) {
            f = null;
        }
        StringValue stringValue = (StringValue) f;
        if (stringValue != null) {
            String str = (String) stringValue.getValue();
            if (str != null) {
                if (!Name.isValidIdentifier(str)) {
                    str = null;
                }
                if (str != null) {
                    return Name.identifier(str);
                }
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final List<TypeProjection> getFunctionTypeArgumentProjections(KotlinType kotlinType, List<? extends KotlinType> list, List<Name> list2, KotlinType kotlinType2, KotlinBuiltIns kotlinBuiltIns) {
        int i = 0;
        ArrayList arrayList = new ArrayList((list.size() + (kotlinType != null ? 1 : 0)) + 1);
        Collection collection = arrayList;
        CollectionsKt.addIfNotNull(collection, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (Object next : list) {
            Name name;
            int i2 = i + 1;
            if (i < 0) {
                acnr.a();
            }
            KotlinType kotlinType3 = (KotlinType) next;
            if (list2 != null) {
                name = (Name) list2.get(i);
                if (name != null) {
                }
            }
            name = null;
            if (name != null) {
                kotlinType3 = TypeUtilsKt.replaceAnnotations(kotlinType3, Annotations.Companion.create(acnz.c((Iterable) kotlinType3.getAnnotations(), (Object) new BuiltInAnnotationDescriptor(kotlinBuiltIns, KotlinBuiltIns.FQ_NAMES.parameterName, acol.a(u.a(Name.identifier("name"), new StringValue(name.asString())))))));
            }
            collection.add(TypeUtilsKt.asTypeProjection(kotlinType3));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(kotlinType2));
        return arrayList;
    }

    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, (i & 64) != 0 ? false : z);
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List<? extends KotlinType> list, List<Name> list2, KotlinType kotlinType2, boolean z) {
        List functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, list, list2, kotlinType2, kotlinBuiltIns);
        int size = list.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor suspendFunction = z ? kotlinBuiltIns.getSuspendFunction(size) : kotlinBuiltIns.getFunction(size);
        if (kotlinType != null && annotations.findAnnotation(KotlinBuiltIns.FQ_NAMES.extensionFunctionType) == null) {
            annotations = Annotations.Companion.create(acnz.c((Iterable) annotations, (Object) new BuiltInAnnotationDescriptor(kotlinBuiltIns, KotlinBuiltIns.FQ_NAMES.extensionFunctionType, acom.a())));
        }
        return KotlinTypeFactory.simpleNotNullType(annotations, suspendFunction, functionTypeArgumentProjections);
    }
}
