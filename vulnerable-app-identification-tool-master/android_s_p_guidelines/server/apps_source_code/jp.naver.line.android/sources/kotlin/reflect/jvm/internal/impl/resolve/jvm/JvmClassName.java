package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    public static JvmClassName byInternalName(String str) {
        return new JvmClassName(str);
    }

    public static JvmClassName byClassId(ClassId classId) {
        FqName packageFqName = classId.getPackageFqName();
        String replace = classId.getRelativeClassName().asString().replace('.', '$');
        if (packageFqName.isRoot()) {
            return new JvmClassName(replace);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(packageFqName.asString().replace('.', '/'));
        stringBuilder.append("/");
        stringBuilder.append(replace);
        return new JvmClassName(stringBuilder.toString());
    }

    public static JvmClassName byFqNameWithoutInnerClasses(FqName fqName) {
        JvmClassName jvmClassName = new JvmClassName(fqName.asString().replace('.', '/'));
        jvmClassName.fqName = fqName;
        return jvmClassName;
    }

    private JvmClassName(String str) {
        this.internalName = str;
    }

    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', '.'));
    }

    public FqName getPackageFqName() {
        int lastIndexOf = this.internalName.lastIndexOf("/");
        if (lastIndexOf == -1) {
            return FqName.ROOT;
        }
        return new FqName(this.internalName.substring(0, lastIndexOf).replace('/', '.'));
    }

    public String getInternalName() {
        return this.internalName;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj == null || getClass() != obj.getClass()) ? false : this.internalName.equals(((JvmClassName) obj).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}
