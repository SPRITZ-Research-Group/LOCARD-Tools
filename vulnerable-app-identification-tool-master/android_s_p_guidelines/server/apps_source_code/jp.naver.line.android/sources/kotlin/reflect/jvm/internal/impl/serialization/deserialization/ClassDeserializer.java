package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acqr;
import defpackage.acru;
import defpackage.acry;
import java.util.Collections;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;

public final class ClassDeserializer {
    private static final Set<ClassId> BLACK_LIST = Collections.singleton(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.cloneable.toSafe()));
    public static final Companion Companion = new Companion();
    private final acqr<ClassKey, ClassDescriptor> classes = this.components.getStorageManager().createMemoizedFunctionWithNullableValues(new ClassDeserializer$classes$1(this));
    private final DeserializationComponents components;

    final class ClassKey {
        private final ClassData classData;
        private final ClassId classId;

        public ClassKey(ClassId classId, ClassData classData) {
            this.classId = classId;
            this.classData = classData;
        }

        public final ClassData getClassData() {
            return this.classData;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof ClassKey) && acry.a(this.classId, ((ClassKey) obj).classId);
        }

        public final int hashCode() {
            return this.classId.hashCode();
        }
    }

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final Set<ClassId> getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }
    }

    public ClassDeserializer(DeserializationComponents deserializationComponents) {
        this.components = deserializationComponents;
    }

    public static /* synthetic */ ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer, ClassId classId, ClassData classData, int i, Object obj) {
        if ((i & 2) != 0) {
            classData = null;
        }
        return classDeserializer.deserializeClass(classId, classData);
    }

    public final ClassDescriptor deserializeClass(ClassId classId, ClassData classData) {
        return (ClassDescriptor) this.classes.invoke(new ClassKey(classId, classData));
    }

    private final ClassDescriptor createClass(ClassKey classKey) {
        ClassId classId = classKey.getClassId();
        for (ClassDescriptorFactory createClass : this.components.getFictitiousClassDescriptorFactories()) {
            ClassDescriptor createClass2 = createClass.createClass(classId);
            if (createClass2 != null) {
                return createClass2;
            }
        }
        if (BLACK_LIST.contains(classId)) {
            return null;
        }
        ClassData classData = classKey.getClassData();
        if (classData == null) {
            classData = this.components.getClassDataFinder().findClassData(classId);
        }
        if (classData == null) {
            return null;
        }
        DeserializationContext c;
        NameResolver component1 = classData.component1();
        Class component2 = classData.component2();
        BinaryVersion component3 = classData.component3();
        SourceElement component4 = classData.component4();
        ClassId outerClassId = classId.getOuterClassId();
        if (outerClassId != null) {
            ClassDescriptor deserializeClass$default = deserializeClass$default(this, outerClassId, null, 2, null);
            if (!(deserializeClass$default instanceof DeserializedClassDescriptor)) {
                deserializeClass$default = null;
            }
            DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) deserializeClass$default;
            if (deserializedClassDescriptor == null || !deserializedClassDescriptor.hasNestedClass$deserialization(classId.getShortClassName())) {
                return null;
            }
            c = deserializedClassDescriptor.getC();
        } else {
            for (Object next : this.components.getPackageFragmentProvider().getPackageFragments(classId.getPackageFqName())) {
                Object obj;
                PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) next;
                if (!(packageFragmentDescriptor instanceof DeserializedPackageFragment) || ((DeserializedPackageFragment) packageFragmentDescriptor).hasTopLevelClass(classId.getShortClassName())) {
                    obj = 1;
                    continue;
                } else {
                    obj = null;
                    continue;
                }
                if (obj != null) {
                    break;
                }
            }
            Object next2 = null;
            PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) next2;
            if (packageFragmentDescriptor2 == null) {
                return null;
            }
            c = this.components.createContext(packageFragmentDescriptor2, component1, new TypeTable(component2.getTypeTable()), VersionRequirementTable.Companion.create(component2.getVersionRequirementTable()), component3, null);
        }
        return new DeserializedClassDescriptor(c, component2, component1, component3, component4);
    }
}
