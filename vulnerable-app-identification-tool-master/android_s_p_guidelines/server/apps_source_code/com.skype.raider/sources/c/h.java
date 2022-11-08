package c;

import java.io.IOException;

public abstract class h implements t {
    private final t delegate;

    public h(t delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = delegate;
    }

    public final t delegate() {
        return this.delegate;
    }

    public long read(c sink, long byteCount) throws IOException {
        return this.delegate.read(sink, byteCount);
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
