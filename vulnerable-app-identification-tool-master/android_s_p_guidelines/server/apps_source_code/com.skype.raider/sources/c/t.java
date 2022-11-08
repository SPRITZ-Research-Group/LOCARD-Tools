package c;

import java.io.Closeable;
import java.io.IOException;

public interface t extends Closeable {
    void close() throws IOException;

    long read(c cVar, long j) throws IOException;

    u timeout();
}
