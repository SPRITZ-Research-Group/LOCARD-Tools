package okhttp3.internal.ws;

import c.c.a;
import c.f;
import com.adjust.sdk.Constants;

public final class WebSocketProtocol {
    static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    static final int B0_FLAG_FIN = 128;
    static final int B0_FLAG_RSV1 = 64;
    static final int B0_FLAG_RSV2 = 32;
    static final int B0_FLAG_RSV3 = 16;
    static final int B0_MASK_OPCODE = 15;
    static final int B1_FLAG_MASK = 128;
    static final int B1_MASK_LENGTH = 127;
    static final int CLOSE_CLIENT_GOING_AWAY = 1001;
    static final long CLOSE_MESSAGE_MAX = 123;
    static final int CLOSE_NO_STATUS_CODE = 1005;
    static final int OPCODE_BINARY = 2;
    static final int OPCODE_CONTINUATION = 0;
    static final int OPCODE_CONTROL_CLOSE = 8;
    static final int OPCODE_CONTROL_PING = 9;
    static final int OPCODE_CONTROL_PONG = 10;
    static final int OPCODE_FLAG_CONTROL = 8;
    static final int OPCODE_TEXT = 1;
    static final long PAYLOAD_BYTE_MAX = 125;
    static final int PAYLOAD_LONG = 127;
    static final int PAYLOAD_SHORT = 126;
    static final long PAYLOAD_SHORT_MAX = 65535;

    static void toggleMask(a cursor, byte[] key) {
        int keyIndex = 0;
        int keyLength = key.length;
        do {
            byte[] buffer = cursor.d;
            int i = cursor.e;
            int end = cursor.f;
            while (i < end) {
                keyIndex %= keyLength;
                buffer[i] = (byte) (buffer[i] ^ key[keyIndex]);
                i++;
                keyIndex++;
            }
        } while (cursor.a() != -1);
    }

    static String closeCodeExceptionMessage(int code) {
        if (code < Constants.ONE_SECOND || code >= 5000) {
            return "Code must be in range [1000,5000): " + code;
        }
        if ((code < 1004 || code > 1006) && (code < 1012 || code > 2999)) {
            return null;
        }
        return "Code " + code + " is reserved and may not be used.";
    }

    static void validateCloseCode(int code) {
        String message = closeCodeExceptionMessage(code);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static String acceptHeader(String key) {
        return f.a(key + ACCEPT_MAGIC).d().b();
    }

    private WebSocketProtocol() {
        throw new AssertionError("No instances.");
    }
}
