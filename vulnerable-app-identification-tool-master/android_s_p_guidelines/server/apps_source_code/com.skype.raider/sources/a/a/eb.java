package a.a;

import com.appboy.f.c;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class eb extends SSLSocketFactory {
    private static final String a = c.a(eb.class);
    private SSLSocketFactory b;

    public eb() {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, null, null);
        this.b = instance.getSocketFactory();
    }

    public String[] getDefaultCipherSuites() {
        return this.b.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.b.getSupportedCipherSuites();
    }

    public Socket createSocket() {
        return a(this.b.createSocket());
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) {
        return a(this.b.createSocket(socket, host, port, autoClose));
    }

    public Socket createSocket(String host, int port) {
        return a(this.b.createSocket(host, port));
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) {
        return a(this.b.createSocket(host, port, localHost, localPort));
    }

    public Socket createSocket(InetAddress host, int port) {
        return a(this.b.createSocket(host, port));
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        return a(this.b.createSocket(address, port, localAddress, localPort));
    }

    private static Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            List arrayList = new ArrayList();
            for (String str : sSLSocket.getSupportedProtocols()) {
                if (!str.equals("SSLv3")) {
                    arrayList.add(str);
                }
            }
            c.a(a, "Enabling SSL protocols: " + arrayList);
            sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return socket;
    }
}
