package kotlin.reflect.jvm.internal.impl.name;

public final class Name implements Comparable<Name> {
    private final String name;
    private final boolean special;

    private Name(String str, boolean z) {
        this.name = str;
        this.special = z;
    }

    public final String asString() {
        return this.name;
    }

    public final String getIdentifier() {
        if (!this.special) {
            return asString();
        }
        throw new IllegalStateException("not identifier: ".concat(String.valueOf(this)));
    }

    public final boolean isSpecial() {
        return this.special;
    }

    public final int compareTo(Name name) {
        return this.name.compareTo(name.name);
    }

    public static Name identifier(String str) {
        return new Name(str, false);
    }

    public static boolean isValidIdentifier(String str) {
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '.' || charAt == '/' || charAt == '\\') {
                return false;
            }
        }
        return true;
    }

    public static Name special(String str) {
        if (str.startsWith("<")) {
            return new Name(str, true);
        }
        throw new IllegalArgumentException("special name must start with '<': ".concat(String.valueOf(str)));
    }

    public static Name guessByFirstCharacter(String str) {
        if (str.startsWith("<")) {
            return special(str);
        }
        return identifier(str);
    }

    public final String toString() {
        return this.name;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Name)) {
            return false;
        }
        Name name = (Name) obj;
        return this.special == name.special && this.name.equals(name.name);
    }

    public final int hashCode() {
        return (this.name.hashCode() * 31) + this.special;
    }
}
