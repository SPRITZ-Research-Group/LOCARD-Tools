package a.a;

import android.net.Uri;
import com.appboy.f.c;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class ct {
    private static final String a = c.a(ct.class);

    public static URI a(Uri uri) {
        try {
            return new URI(uri.toString());
        } catch (URISyntaxException e) {
            c.g(a, "Could not create URI from uri [" + uri.toString() + "]");
            return null;
        }
    }

    public static URL a(URI uri) {
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            c.g(a, "Unable to parse URI [" + e.getMessage() + "]");
            return null;
        }
    }
}
