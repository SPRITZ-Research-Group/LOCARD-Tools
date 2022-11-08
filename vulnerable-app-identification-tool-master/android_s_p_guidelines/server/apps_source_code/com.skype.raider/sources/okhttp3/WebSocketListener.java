package okhttp3;

import c.f;
import javax.annotation.Nullable;

public abstract class WebSocketListener {
    public void onOpen(WebSocket webSocket, Response response) {
    }

    public void onMessage(WebSocket webSocket, String text) {
    }

    public void onMessage(WebSocket webSocket, f bytes) {
    }

    public void onClosing(WebSocket webSocket, int code, String reason) {
    }

    public void onClosed(WebSocket webSocket, int code, String reason) {
    }

    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
    }
}
