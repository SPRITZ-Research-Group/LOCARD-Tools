package okhttp3.internal.cache;

import c.c;
import c.g;
import c.s;
import java.io.IOException;

class FaultHidingSink extends g {
    private boolean hasErrors;

    FaultHidingSink(s delegate) {
        super(delegate);
    }

    public void write(c source, long byteCount) throws IOException {
        if (this.hasErrors) {
            source.h(byteCount);
            return;
        }
        try {
            super.write(source, byteCount);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public void flush() throws IOException {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    public void close() throws IOException {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    protected void onException(IOException e) {
    }
}
