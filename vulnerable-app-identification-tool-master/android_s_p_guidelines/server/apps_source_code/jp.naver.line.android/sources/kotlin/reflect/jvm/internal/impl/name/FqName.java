package kotlin.reflect.jvm.internal.impl.name;

import java.util.List;

public final class FqName {
    public static final FqName ROOT = new FqName("");
    private final FqNameUnsafe fqName;
    private transient FqName parent;

    public FqName(String str) {
        this.fqName = new FqNameUnsafe(str, this);
    }

    public FqName(FqNameUnsafe fqNameUnsafe) {
        this.fqName = fqNameUnsafe;
    }

    private FqName(FqNameUnsafe fqNameUnsafe, FqName fqName) {
        this.fqName = fqNameUnsafe;
        this.parent = fqName;
    }

    public final String asString() {
        return this.fqName.asString();
    }

    public final FqNameUnsafe toUnsafe() {
        return this.fqName;
    }

    public final boolean isRoot() {
        return this.fqName.isRoot();
    }

    public final FqName parent() {
        if (this.parent != null) {
            return this.parent;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        this.parent = new FqName(this.fqName.parent());
        return this.parent;
    }

    public final FqName child(Name name) {
        return new FqName(this.fqName.child(name), this);
    }

    public final Name shortName() {
        return this.fqName.shortName();
    }

    public final Name shortNameOrSpecial() {
        return this.fqName.shortNameOrSpecial();
    }

    public final List<Name> pathSegments() {
        return this.fqName.pathSegments();
    }

    public final boolean startsWith(Name name) {
        return this.fqName.startsWith(name);
    }

    public static FqName topLevel(Name name) {
        return new FqName(FqNameUnsafe.topLevel(name));
    }

    public final String toString() {
        return this.fqName.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FqName)) {
            return false;
        }
        return this.fqName.equals(((FqName) obj).fqName);
    }

    public final int hashCode() {
        return this.fqName.hashCode();
    }
}
