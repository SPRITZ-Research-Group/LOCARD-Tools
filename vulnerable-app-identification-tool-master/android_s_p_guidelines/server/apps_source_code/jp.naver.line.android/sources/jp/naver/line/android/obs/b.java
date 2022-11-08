package jp.naver.line.android.obs;

import java.net.SocketException;
import jp.naver.line.android.common.util.io.d;
import jp.naver.line.android.common.util.io.g;

public enum b {
    UNKNOWN,
    NOT_AVAILABLE_EXTERNAL_STORAGE,
    CAPACITY_SHORTAGE_EXTERNAL_STORAGE,
    EXPIRED_OR_NOTFOUND,
    NOT_CONNECTED;

    public static final b a(Exception exception) {
        if (exception != null) {
            if (exception instanceof d) {
                return NOT_AVAILABLE_EXTERNAL_STORAGE;
            }
            if (exception instanceof g) {
                return CAPACITY_SHORTAGE_EXTERNAL_STORAGE;
            }
            if (exception instanceof SocketException) {
                return NOT_CONNECTED;
            }
            if (exception instanceof jp.naver.line.android.obs.net.b) {
                return EXPIRED_OR_NOTFOUND;
            }
        }
        return UNKNOWN;
    }
}
