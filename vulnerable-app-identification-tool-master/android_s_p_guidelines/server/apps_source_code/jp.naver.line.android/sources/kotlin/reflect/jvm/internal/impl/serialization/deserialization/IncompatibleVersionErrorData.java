package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class IncompatibleVersionErrorData<T extends BinaryVersion> {
    private final T actualVersion;
    private final ClassId classId;
    private final T expectedVersion;
    private final String filePath;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof IncompatibleVersionErrorData) {
                IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) obj;
                if (acry.a(this.actualVersion, incompatibleVersionErrorData.actualVersion)) {
                    if (acry.a(this.expectedVersion, incompatibleVersionErrorData.expectedVersion)) {
                        if (acry.a(this.filePath, incompatibleVersionErrorData.filePath)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        BinaryVersion binaryVersion = this.actualVersion;
        int i = 0;
        int hashCode = (binaryVersion != null ? binaryVersion.hashCode() : 0) * 31;
        BinaryVersion binaryVersion2 = this.expectedVersion;
        hashCode = (hashCode + (binaryVersion2 != null ? binaryVersion2.hashCode() : 0)) * 31;
        String str = this.filePath;
        hashCode = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        ClassId classId = this.classId;
        if (classId != null) {
            i = classId.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("IncompatibleVersionErrorData(actualVersion=");
        stringBuilder.append(this.actualVersion);
        stringBuilder.append(", expectedVersion=");
        stringBuilder.append(this.expectedVersion);
        stringBuilder.append(", filePath=");
        stringBuilder.append(this.filePath);
        stringBuilder.append(", classId=");
        stringBuilder.append(this.classId);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public IncompatibleVersionErrorData(T t, T t2, String str, ClassId classId) {
        this.actualVersion = t;
        this.expectedVersion = t2;
        this.filePath = str;
        this.classId = classId;
    }
}
