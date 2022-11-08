package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import com.google.obf.ly;
import defpackage.acnn;
import defpackage.acno;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acru;
import defpackage.acry;
import java.util.ArrayList;
import java.util.List;
import org.apache.cordova.networkinformation.NetworkManager;

public abstract class BinaryVersion {
    public static final Companion Companion = new Companion();
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    private final List<Integer> rest;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    public BinaryVersion(int... iArr) {
        List k;
        this.numbers = iArr;
        Integer b = acno.b(this.numbers, 0);
        int i = -1;
        this.major = b != null ? b.intValue() : -1;
        b = acno.b(this.numbers, 1);
        this.minor = b != null ? b.intValue() : -1;
        b = acno.b(this.numbers, 2);
        if (b != null) {
            i = b.intValue();
        }
        this.patch = i;
        if (this.numbers.length > 3) {
            k = acnz.k((Iterable) acnn.a(this.numbers).subList(3, this.numbers.length));
        } else {
            k = acob.a;
        }
        this.rest = k;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int[] toArray() {
        return this.numbers;
    }

    protected final boolean isCompatibleTo(BinaryVersion binaryVersion) {
        return this.major == 0 ? binaryVersion.major == 0 && this.minor == binaryVersion.minor : this.major == binaryVersion.major && this.minor <= binaryVersion.minor;
    }

    public final boolean isAtLeast(BinaryVersion binaryVersion) {
        return isAtLeast(binaryVersion.major, binaryVersion.minor, binaryVersion.patch);
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        if (this.major > i) {
            return true;
        }
        if (this.major < i) {
            return false;
        }
        if (this.minor > i2) {
            return true;
        }
        if (this.minor >= i2 && this.patch >= i3) {
            return true;
        }
        return false;
    }

    public String toString() {
        int[] toArray = toArray();
        ArrayList arrayList = new ArrayList();
        for (int i : toArray) {
            if ((i != -1 ? 1 : null) == null) {
                break;
            }
            arrayList.add(Integer.valueOf(i));
        }
        List list = arrayList;
        return list.isEmpty() ? NetworkManager.TYPE_UNKNOWN : acnz.a((Iterable) list, (CharSequence) ly.a, null, null, 0, null, null, 62);
    }

    public boolean equals(Object obj) {
        if (obj != null && acry.a(getClass(), obj.getClass())) {
            BinaryVersion binaryVersion = (BinaryVersion) obj;
            if (this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && acry.a(this.rest, binaryVersion.rest)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.major;
        i += (i * 31) + this.minor;
        i += (i * 31) + this.patch;
        return i + ((i * 31) + this.rest.hashCode());
    }
}
