package c;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface s extends Closeable, Flushable {
    void close() throws IOException;

    void flush() throws IOException;

    u timeout();

    void write(c cVar, long j) throws IOException;
}
