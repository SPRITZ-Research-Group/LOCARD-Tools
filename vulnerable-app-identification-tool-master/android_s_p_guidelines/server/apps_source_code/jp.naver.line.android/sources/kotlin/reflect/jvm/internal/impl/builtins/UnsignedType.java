package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public enum UnsignedType {
    ;
    
    private final ClassId arrayClassId;
    private final ClassId classId;
    private final Name typeName;

    private UnsignedType(ClassId classId) {
        this.classId = classId;
        this.typeName = this.classId.getShortClassName();
        FqName packageFqName = this.classId.getPackageFqName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.typeName.asString());
        stringBuilder.append("Array");
        this.arrayClassId = new ClassId(packageFqName, Name.identifier(stringBuilder.toString()));
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }
}
