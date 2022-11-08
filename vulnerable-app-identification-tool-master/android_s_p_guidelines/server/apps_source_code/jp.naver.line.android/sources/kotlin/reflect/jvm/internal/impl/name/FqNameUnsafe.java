package kotlin.reflect.jvm.internal.impl.name;

import com.google.obf.ly;
import defpackage.acno;
import defpackage.acqr;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class FqNameUnsafe {
    private static final Name ROOT_NAME = Name.special("<root>");
    private static final Pattern SPLIT_BY_DOTS = Pattern.compile("\\.");
    private static final acqr<String, Name> STRING_TO_NAME = new acqr<String, Name>() {
        public final Name invoke(String str) {
            return Name.guessByFirstCharacter(str);
        }
    };
    private final String fqName;
    private transient FqNameUnsafe parent;
    private transient FqName safe;
    private transient Name shortName;

    FqNameUnsafe(String str, FqName fqName) {
        this.fqName = str;
        this.safe = fqName;
    }

    public FqNameUnsafe(String str) {
        this.fqName = str;
    }

    private FqNameUnsafe(String str, FqNameUnsafe fqNameUnsafe, Name name) {
        this.fqName = str;
        this.parent = fqNameUnsafe;
        this.shortName = name;
    }

    private void compute() {
        int lastIndexOf = this.fqName.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            this.shortName = Name.guessByFirstCharacter(this.fqName.substring(lastIndexOf + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, lastIndexOf));
            return;
        }
        this.shortName = Name.guessByFirstCharacter(this.fqName);
        this.parent = FqName.ROOT.toUnsafe();
    }

    public final String asString() {
        return this.fqName;
    }

    public final boolean isSafe() {
        return this.safe != null || asString().indexOf(60) < 0;
    }

    public final FqName toSafe() {
        if (this.safe != null) {
            return this.safe;
        }
        this.safe = new FqName(this);
        return this.safe;
    }

    public final boolean isRoot() {
        return this.fqName.isEmpty();
    }

    public final FqNameUnsafe parent() {
        if (this.parent != null) {
            return this.parent;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        return this.parent;
    }

    public final FqNameUnsafe child(Name name) {
        String asString;
        if (isRoot()) {
            asString = name.asString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.fqName);
            stringBuilder.append(ly.a);
            stringBuilder.append(name.asString());
            asString = stringBuilder.toString();
        }
        return new FqNameUnsafe(asString, this, name);
    }

    public final Name shortName() {
        if (this.shortName != null) {
            return this.shortName;
        }
        if (isRoot()) {
            throw new IllegalStateException("root");
        }
        compute();
        return this.shortName;
    }

    public final Name shortNameOrSpecial() {
        if (isRoot()) {
            return ROOT_NAME;
        }
        return shortName();
    }

    public final List<Name> pathSegments() {
        return isRoot() ? Collections.emptyList() : acno.a((Object[]) SPLIT_BY_DOTS.split(this.fqName), STRING_TO_NAME);
    }

    public final boolean startsWith(Name name) {
        int indexOf = this.fqName.indexOf(46);
        if (!isRoot()) {
            String str = this.fqName;
            String asString = name.asString();
            if (indexOf == -1) {
                indexOf = this.fqName.length();
            }
            if (str.regionMatches(0, asString, 0, indexOf)) {
                return true;
            }
        }
        return false;
    }

    public static FqNameUnsafe topLevel(Name name) {
        return new FqNameUnsafe(name.asString(), FqName.ROOT.toUnsafe(), name);
    }

    public final String toString() {
        return isRoot() ? ROOT_NAME.asString() : this.fqName;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FqNameUnsafe)) {
            return false;
        }
        return this.fqName.equals(((FqNameUnsafe) obj).fqName);
    }

    public final int hashCode() {
        return this.fqName.hashCode();
    }
}
