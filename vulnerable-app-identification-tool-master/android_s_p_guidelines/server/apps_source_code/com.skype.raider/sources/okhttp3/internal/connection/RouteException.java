package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.internal.Util;

public final class RouteException extends RuntimeException {
    private IOException firstException;
    private IOException lastException;

    public RouteException(IOException cause) {
        super(cause);
        this.firstException = cause;
        this.lastException = cause;
    }

    public final IOException getFirstConnectException() {
        return this.firstException;
    }

    public final IOException getLastConnectException() {
        return this.lastException;
    }

    public final void addConnectException(IOException e) {
        Util.addSuppressedIfPossible(this.firstException, e);
        this.lastException = e;
    }
}
