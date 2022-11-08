package kotlin.reflect.jvm.internal.impl.resolve.constants;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class ClassLiteralValue {
    private final int arrayNestedness;
    private final ClassId classId;

    public final ClassId component1() {
        return this.classId;
    }

    public final int component2() {
        return this.arrayNestedness;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ClassLiteralValue) {
                ClassLiteralValue classLiteralValue = (ClassLiteralValue) obj;
                if (acry.a(this.classId, classLiteralValue.classId)) {
                    if ((this.arrayNestedness == classLiteralValue.arrayNestedness ? 1 : null) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        ClassId classId = this.classId;
        return ((classId != null ? classId.hashCode() : 0) * 31) + this.arrayNestedness;
    }

    public ClassLiteralValue(ClassId classId, int i) {
        this.classId = classId;
        this.arrayNestedness = i;
    }

    public final int getArrayNestedness() {
        return this.arrayNestedness;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = this.arrayNestedness;
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("kotlin/Array<");
        }
        stringBuilder.append(this.classId);
        i = this.arrayNestedness;
        for (int i3 = 0; i3 < i; i3++) {
            stringBuilder.append(">");
        }
        return stringBuilder.toString();
    }
}
