package okhttp3;

import c.f;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String username, String password) {
        return basic(username, password, Util.ISO_8859_1);
    }

    public static String basic(String username, String password, Charset charset) {
        return "Basic " + f.a(username + ":" + password, charset).b();
    }
}
