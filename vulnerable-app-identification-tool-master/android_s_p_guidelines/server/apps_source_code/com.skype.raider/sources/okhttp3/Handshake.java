package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;

public final class Handshake {
    private final CipherSuite cipherSuite;
    private final List<Certificate> localCertificates;
    private final List<Certificate> peerCertificates;
    private final TlsVersion tlsVersion;

    private Handshake(TlsVersion tlsVersion, CipherSuite cipherSuite, List<Certificate> peerCertificates, List<Certificate> localCertificates) {
        this.tlsVersion = tlsVersion;
        this.cipherSuite = cipherSuite;
        this.peerCertificates = peerCertificates;
        this.localCertificates = localCertificates;
    }

    public static Handshake get(SSLSession session) throws IOException {
        String cipherSuiteString = session.getCipherSuite();
        if (cipherSuiteString == null) {
            throw new IllegalStateException("cipherSuite == null");
        } else if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuiteString)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        } else {
            CipherSuite cipherSuite = CipherSuite.forJavaName(cipherSuiteString);
            String tlsVersionString = session.getProtocol();
            if (tlsVersionString == null) {
                throw new IllegalStateException("tlsVersion == null");
            } else if ("NONE".equals(tlsVersionString)) {
                throw new IOException("tlsVersion == NONE");
            } else {
                Object[] peerCertificates;
                List<Certificate> peerCertificatesList;
                List<Certificate> localCertificatesList;
                TlsVersion tlsVersion = TlsVersion.forJavaName(tlsVersionString);
                try {
                    peerCertificates = session.getPeerCertificates();
                } catch (SSLPeerUnverifiedException e) {
                    peerCertificates = null;
                }
                if (peerCertificates != null) {
                    peerCertificatesList = Util.immutableList(peerCertificates);
                } else {
                    peerCertificatesList = Collections.emptyList();
                }
                Object[] localCertificates = session.getLocalCertificates();
                if (localCertificates != null) {
                    localCertificatesList = Util.immutableList(localCertificates);
                } else {
                    localCertificatesList = Collections.emptyList();
                }
                return new Handshake(tlsVersion, cipherSuite, peerCertificatesList, localCertificatesList);
            }
        }
    }

    public static Handshake get(TlsVersion tlsVersion, CipherSuite cipherSuite, List<Certificate> peerCertificates, List<Certificate> localCertificates) {
        if (tlsVersion == null) {
            throw new NullPointerException("tlsVersion == null");
        } else if (cipherSuite != null) {
            return new Handshake(tlsVersion, cipherSuite, Util.immutableList((List) peerCertificates), Util.immutableList((List) localCertificates));
        } else {
            throw new NullPointerException("cipherSuite == null");
        }
    }

    public final TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    public final CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    public final List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }

    @Nullable
    public final Principal peerPrincipal() {
        if (this.peerCertificates.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.peerCertificates.get(0)).getSubjectX500Principal();
    }

    public final List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    @Nullable
    public final Principal localPrincipal() {
        if (this.localCertificates.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.localCertificates.get(0)).getSubjectX500Principal();
    }

    public final boolean equals(@Nullable Object other) {
        if (!(other instanceof Handshake)) {
            return false;
        }
        Handshake that = (Handshake) other;
        if (this.tlsVersion.equals(that.tlsVersion) && this.cipherSuite.equals(that.cipherSuite) && this.peerCertificates.equals(that.peerCertificates) && this.localCertificates.equals(that.localCertificates)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.tlsVersion.hashCode() + 527) * 31) + this.cipherSuite.hashCode()) * 31) + this.peerCertificates.hashCode()) * 31) + this.localCertificates.hashCode();
    }
}
