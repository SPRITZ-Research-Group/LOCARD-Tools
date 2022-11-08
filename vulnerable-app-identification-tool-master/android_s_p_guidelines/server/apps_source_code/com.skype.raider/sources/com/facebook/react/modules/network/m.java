package com.facebook.react.modules.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class m extends SSLSocketFactory {
    private SSLSocketFactory a;

    public m() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);
        this.a = context.getSocketFactory();
    }

    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    public final Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return a(this.a.createSocket(s, host, port, autoClose));
    }

    public final Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return a(this.a.createSocket(host, port));
    }

    public final Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return a(this.a.createSocket(host, port, localHost, localPort));
    }

    public final Socket createSocket(InetAddress host, int port) throws IOException {
        return a(this.a.createSocket(host, port));
    }

    public final Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return a(this.a.createSocket(address, port, localAddress, localPort));
    }

    private static Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"});
        }
        return socket;
    }
}
