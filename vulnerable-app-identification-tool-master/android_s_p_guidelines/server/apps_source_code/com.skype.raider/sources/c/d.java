package c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

public interface d extends s, WritableByteChannel {
    long a(t tVar) throws IOException;

    c b();

    d b(String str) throws IOException;

    d c(f fVar) throws IOException;

    d c(byte[] bArr) throws IOException;

    d c(byte[] bArr, int i, int i2) throws IOException;

    OutputStream c();

    d d() throws IOException;

    d f(int i) throws IOException;

    void flush() throws IOException;

    d g(int i) throws IOException;

    d h(int i) throws IOException;

    d l(long j) throws IOException;

    d m(long j) throws IOException;

    d w() throws IOException;
}
