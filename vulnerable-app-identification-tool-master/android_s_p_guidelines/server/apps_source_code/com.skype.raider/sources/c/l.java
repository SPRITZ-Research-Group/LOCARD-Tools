package c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class l {
    static final Logger a = Logger.getLogger(l.class.getName());

    private l() {
    }

    public static e a(t source) {
        return new o(source);
    }

    public static d a(s sink) {
        return new n(sink);
    }

    private static s a(OutputStream out) {
        return a(out, new u());
    }

    private static s a(final OutputStream out, final u timeout) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        } else if (timeout != null) {
            return new s() {
                public final void write(c source, long byteCount) throws IOException {
                    v.a(source.b, 0, byteCount);
                    while (byteCount > 0) {
                        timeout.throwIfReached();
                        p head = source.a;
                        int toCopy = (int) Math.min(byteCount, (long) (head.c - head.b));
                        out.write(head.a, head.b, toCopy);
                        head.b += toCopy;
                        byteCount -= (long) toCopy;
                        source.b -= (long) toCopy;
                        if (head.b == head.c) {
                            source.a = head.c();
                            q.a(head);
                        }
                    }
                }

                public final void flush() throws IOException {
                    out.flush();
                }

                public final void close() throws IOException {
                    out.close();
                }

                public final u timeout() {
                    return timeout;
                }

                public final String toString() {
                    return "sink(" + out + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static s a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        } else {
            u timeout = c(socket);
            return timeout.sink(a(socket.getOutputStream(), timeout));
        }
    }

    public static t a(InputStream in) {
        return a(in, new u());
    }

    private static t a(final InputStream in, final u timeout) {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        } else if (timeout != null) {
            return new t() {
                public final long read(c sink, long byteCount) throws IOException {
                    if (byteCount < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + byteCount);
                    } else if (byteCount == 0) {
                        return 0;
                    } else {
                        try {
                            timeout.throwIfReached();
                            p tail = sink.e(1);
                            int bytesRead = in.read(tail.a, tail.c, (int) Math.min(byteCount, (long) (8192 - tail.c)));
                            if (bytesRead == -1) {
                                return -1;
                            }
                            tail.c += bytesRead;
                            sink.b += (long) bytesRead;
                            return (long) bytesRead;
                        } catch (AssertionError e) {
                            if (l.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public final void close() throws IOException {
                    in.close();
                }

                public final u timeout() {
                    return timeout;
                }

                public final String toString() {
                    return "source(" + in + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static t a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s b(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s c(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s a() {
        return new s() {
            public final void write(c source, long byteCount) throws IOException {
                source.h(byteCount);
            }

            public final void flush() throws IOException {
            }

            public final u timeout() {
                return u.NONE;
            }

            public final void close() throws IOException {
            }
        };
    }

    public static t b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        } else {
            u timeout = c(socket);
            return timeout.source(a(socket.getInputStream(), timeout));
        }
    }

    private static a c(final Socket socket) {
        return new a() {
            protected final IOException newTimeoutException(@Nullable IOException cause) {
                InterruptedIOException ioe = new SocketTimeoutException("timeout");
                if (cause != null) {
                    ioe.initCause(cause);
                }
                return ioe;
            }

            protected final void timedOut() {
                try {
                    socket.close();
                } catch (Exception e) {
                    l.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (l.a(e2)) {
                        l.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError e) {
        return (e.getCause() == null || e.getMessage() == null || !e.getMessage().contains("getsockname failed")) ? false : true;
    }
}
