package okhttp3.internal.http2;

import c.f;
import okhttp3.internal.Util;

public final class Header {
    public static final f PSEUDO_PREFIX = f.a(":");
    public static final f RESPONSE_STATUS = f.a(":status");
    public static final f TARGET_AUTHORITY = f.a(":authority");
    public static final f TARGET_METHOD = f.a(":method");
    public static final f TARGET_PATH = f.a(":path");
    public static final f TARGET_SCHEME = f.a(":scheme");
    final int hpackSize;
    public final f name;
    public final f value;

    public Header(String name, String value) {
        this(f.a(name), f.a(value));
    }

    public Header(f name, String value) {
        this(name, f.a(value));
    }

    public Header(f name, f value) {
        this.name = name;
        this.value = value;
        this.hpackSize = (name.h() + 32) + value.h();
    }

    public final boolean equals(Object other) {
        if (!(other instanceof Header)) {
            return false;
        }
        Header that = (Header) other;
        if (this.name.equals(that.name) && this.value.equals(that.value)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((this.name.hashCode() + 527) * 31) + this.value.hashCode();
    }

    public final String toString() {
        return Util.format("%s: %s", this.name.a(), this.value.a());
    }
}
