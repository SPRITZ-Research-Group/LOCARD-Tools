package c;

import java.io.IOException;

public abstract class g implements s {
    private final s delegate;

    public g(s delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = delegate;
    }

    public final s delegate() {
        return this.delegate;
    }

    public void write(c source, long byteCount) throws IOException {
        this.delegate.write(source, byteCount);
    }

    public void flush() throws IOException {
        this.delegate.flush();
    }

    public u timeout() {
        return this.delegate.timeout();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
