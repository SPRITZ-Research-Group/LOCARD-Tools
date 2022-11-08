package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acnz;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();
    private static final HashMap<ClassId, ClassId> arrayClassIdToUnsignedClassId = new HashMap();
    private static final Set<Name> arrayClassesShortNames;
    private static final HashMap<ClassId, ClassId> unsignedClassIdToArrayClassId = new HashMap();
    private static final Set<Name> unsignedTypeNames;

    static {
        UnsignedType[] values = UnsignedType.values();
        Collection arrayList = new ArrayList(values.length);
        for (UnsignedType typeName : values) {
            arrayList.add(typeName.getTypeName());
        }
        unsignedTypeNames = acnz.m((List) arrayList);
        values = UnsignedType.values();
        arrayList = new LinkedHashSet();
        for (UnsignedType typeName2 : values) {
            arrayList.add(typeName2.getArrayClassId().getShortClassName());
        }
        arrayClassesShortNames = (Set) arrayList;
        for (UnsignedType unsignedType : UnsignedType.values()) {
            arrayClassIdToUnsignedClassId.put(unsignedType.getArrayClassId(), unsignedType.getClassId());
            unsignedClassIdToArrayClassId.put(unsignedType.getClassId(), unsignedType.getArrayClassId());
        }
    }

    private UnsignedTypes() {
    }

    public final boolean isShortNameOfUnsignedArray(Name name) {
        return arrayClassesShortNames.contains(name);
    }

    public final ClassId getUnsignedClassIdByArrayClassId(ClassId classId) {
        return (ClassId) arrayClassIdToUnsignedClassId.get(classId);
    }

    public final ClassId getUnsignedArrayClassIdByUnsignedClassId(ClassId classId) {
        return (ClassId) unsignedClassIdToArrayClassId.get(classId);
    }

    public final boolean isUnsignedType(KotlinType kotlinType) {
        if (TypeUtils.noExpectedType(kotlinType)) {
            return false;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return false;
        }
        return isUnsignedClass(declarationDescriptor);
    }

    public final boolean isUnsignedClass(DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        return (containingDeclaration instanceof PackageFragmentDescriptor) && acry.a(((PackageFragmentDescriptor) containingDeclaration).getFqName(), KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(declarationDescriptor.getName());
    }
}
