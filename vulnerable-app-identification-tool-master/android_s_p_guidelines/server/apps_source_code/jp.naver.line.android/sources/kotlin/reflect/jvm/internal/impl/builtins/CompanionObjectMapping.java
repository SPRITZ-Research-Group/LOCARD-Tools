package kotlin.reflect.jvm.internal.impl.builtins;

import defpackage.acns;
import defpackage.acnz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class CompanionObjectMapping {
    public static final CompanionObjectMapping INSTANCE = new CompanionObjectMapping();
    private static final LinkedHashSet<ClassId> classIds;

    static {
        Iterable<PrimitiveType> iterable = PrimitiveType.NUMBER_TYPES;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (PrimitiveType primitiveFqName : iterable) {
            arrayList.add(KotlinBuiltIns.getPrimitiveFqName(primitiveFqName));
        }
        Iterable<FqName> a = acnz.a((Collection) acnz.a((Collection) acnz.a((Collection) (List) arrayList, (Object) KotlinBuiltIns.FQ_NAMES.string.toSafe()), (Object) KotlinBuiltIns.FQ_NAMES._boolean.toSafe()), (Object) KotlinBuiltIns.FQ_NAMES._enum.toSafe());
        arrayList = new LinkedHashSet();
        for (FqName topLevel : a) {
            arrayList.add(ClassId.topLevel(topLevel));
        }
        classIds = (LinkedHashSet) arrayList;
    }

    private CompanionObjectMapping() {
    }

    public final Set<ClassId> allClassesWithIntrinsicCompanions() {
        return Collections.unmodifiableSet(classIds);
    }

    public final boolean isMappedIntrinsicCompanionObject(ClassDescriptor classDescriptor) {
        if (DescriptorUtils.isCompanionObject(classDescriptor)) {
            Iterable iterable = classIds;
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (acnz.a(iterable, classId != null ? classId.getOuterClassId() : null)) {
                return true;
            }
        }
        return false;
    }
}
