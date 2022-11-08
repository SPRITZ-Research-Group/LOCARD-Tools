package a.a;

import com.appboy.e.e;
import java.util.UUID;

public final class bc implements e<String> {
    private final UUID a;
    private final String b;

    public bc(UUID uuid) {
        this.a = uuid;
        this.b = uuid.toString();
    }

    public static bc a(String str) {
        return new bc(UUID.fromString(str));
    }

    public final String toString() {
        return this.b;
    }

    public final String a() {
        return this.b;
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return this.a.equals(((bc) other).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final /* synthetic */ Object h() {
        return this.b;
    }
}
