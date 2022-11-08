package c;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public interface e extends t, ReadableByteChannel {
    String a(Charset charset) throws IOException;

    void a(long j) throws IOException;

    void a(c cVar, long j) throws IOException;

    void a(byte[] bArr) throws IOException;

    c b();

    boolean b(long j) throws IOException;

    boolean b(f fVar) throws IOException;

    f d(long j) throws IOException;

    String e(long j) throws IOException;

    boolean e() throws IOException;

    InputStream f();

    byte[] g(long j) throws IOException;

    byte h() throws IOException;

    void h(long j) throws IOException;

    short i() throws IOException;

    int j() throws IOException;

    long k() throws IOException;

    short l() throws IOException;

    int m() throws IOException;

    long n() throws IOException;

    long o() throws IOException;

    String r() throws IOException;

    byte[] s() throws IOException;

    long u() throws IOException;
}
