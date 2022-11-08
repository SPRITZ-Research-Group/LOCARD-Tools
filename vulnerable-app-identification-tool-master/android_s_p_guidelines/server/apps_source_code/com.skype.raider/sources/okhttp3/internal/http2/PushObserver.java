package okhttp3.internal.http2;

import c.e;
import java.io.IOException;
import java.util.List;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        public boolean onRequest(int streamId, List<Header> list) {
            return true;
        }

        public boolean onHeaders(int streamId, List<Header> list, boolean last) {
            return true;
        }

        public boolean onData(int streamId, e source, int byteCount, boolean last) throws IOException {
            source.h((long) byteCount);
            return true;
        }

        public void onReset(int streamId, ErrorCode errorCode) {
        }
    };

    boolean onData(int i, e eVar, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
