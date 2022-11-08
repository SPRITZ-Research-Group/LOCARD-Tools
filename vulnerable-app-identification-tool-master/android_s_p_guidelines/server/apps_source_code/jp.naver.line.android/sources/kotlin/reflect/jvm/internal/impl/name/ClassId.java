package kotlin.reflect.jvm.internal.impl.name;

import com.google.obf.ly;
import defpackage.addc;

public final class ClassId {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean local;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    public static ClassId topLevel(FqName fqName) {
        return new ClassId(fqName.parent(), fqName.shortName());
    }

    public ClassId(FqName fqName, FqName fqName2, boolean z) {
        this.packageFqName = fqName;
        this.relativeClassName = fqName2;
        this.local = z;
    }

    public ClassId(FqName fqName, Name name) {
        this(fqName, FqName.topLevel(name), false);
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    public final Name getShortClassName() {
        return this.relativeClassName.shortName();
    }

    public final boolean isLocal() {
        return this.local;
    }

    public final ClassId createNestedClassId(Name name) {
        return new ClassId(getPackageFqName(), this.relativeClassName.child(name), this.local);
    }

    public final ClassId getOuterClassId() {
        FqName parent = this.relativeClassName.parent();
        return parent.isRoot() ? null : new ClassId(getPackageFqName(), parent, this.local);
    }

    public final boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    public final FqName asSingleFqName() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.packageFqName.asString());
        stringBuilder.append(ly.a);
        stringBuilder.append(this.relativeClassName.asString());
        return new FqName(stringBuilder.toString());
    }

    public static ClassId fromString(String str) {
        return fromString(str, false);
    }

    public static ClassId fromString(String str, boolean z) {
        return new ClassId(new FqName(addc.e(str, "").replace('/', '.')), new FqName(addc.a(str, '/', str)), z);
    }

    public final String asString() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName.asString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.packageFqName.asString().replace('.', '/'));
        stringBuilder.append("/");
        stringBuilder.append(this.relativeClassName.asString());
        return stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClassId classId = (ClassId) obj;
        return this.packageFqName.equals(classId.packageFqName) && this.relativeClassName.equals(classId.relativeClassName) && this.local == classId.local;
    }

    public final int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.relativeClassName.hashCode()) * 31) + Boolean.valueOf(this.local).hashCode();
    }

    public final String toString() {
        if (!this.packageFqName.isRoot()) {
            return asString();
        }
        StringBuilder stringBuilder = new StringBuilder("/");
        stringBuilder.append(asString());
        return stringBuilder.toString();
    }
}
