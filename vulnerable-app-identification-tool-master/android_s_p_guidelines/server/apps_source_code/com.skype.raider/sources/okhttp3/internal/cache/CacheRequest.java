package okhttp3.internal.cache;

import c.s;
import java.io.IOException;

public interface CacheRequest {
    void abort();

    s body() throws IOException;
}
