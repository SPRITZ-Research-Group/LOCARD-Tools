package okhttp3;

import c.f;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final Builder add(String pattern, String... pins) {
            if (pattern == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String pin : pins) {
                this.pins.add(new Pin(pattern, pin));
            }
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet(this.pins), null);
        }
    }

    static final class Pin {
        private static final String WILDCARD = "*.";
        final String canonicalHostname;
        final f hash;
        final String hashAlgorithm;
        final String pattern;

        Pin(String pattern, String pin) {
            String host;
            this.pattern = pattern;
            if (pattern.startsWith(WILDCARD)) {
                host = HttpUrl.get("http://" + pattern.substring(2)).host();
            } else {
                host = HttpUrl.get("http://" + pattern).host();
            }
            this.canonicalHostname = host;
            if (pin.startsWith("sha1/")) {
                this.hashAlgorithm = "sha1/";
                this.hash = f.b(pin.substring(5));
            } else if (pin.startsWith("sha256/")) {
                this.hashAlgorithm = "sha256/";
                this.hash = f.b(pin.substring(7));
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
            }
            if (this.hash == null) {
                throw new IllegalArgumentException("pins must be base64: " + pin);
            }
        }

        final boolean matches(String hostname) {
            if (!this.pattern.startsWith(WILDCARD)) {
                return hostname.equals(this.canonicalHostname);
            }
            int firstDot = hostname.indexOf(46);
            if ((hostname.length() - firstDot) - 1 != this.canonicalHostname.length()) {
                return false;
            }
            if (hostname.regionMatches(false, firstDot + 1, this.canonicalHostname, 0, this.canonicalHostname.length())) {
                return true;
            }
            return false;
        }

        public final boolean equals(Object other) {
            return (other instanceof Pin) && this.pattern.equals(((Pin) other).pattern) && this.hashAlgorithm.equals(((Pin) other).hashAlgorithm) && this.hash.equals(((Pin) other).hash);
        }

        public final int hashCode() {
            return ((((this.pattern.hashCode() + 527) * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }

        public final String toString() {
            return this.hashAlgorithm + this.hash.b();
        }
    }

    CertificatePinner(Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public final boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof CertificatePinner) && Util.equal(this.certificateChainCleaner, ((CertificatePinner) other).certificateChainCleaner) && this.pins.equals(((CertificatePinner) other).pins);
    }

    public final int hashCode() {
        return ((this.certificateChainCleaner != null ? this.certificateChainCleaner.hashCode() : 0) * 31) + this.pins.hashCode();
    }

    public final void check(String hostname, List<Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        List<Pin> pins = findMatchingPins(hostname);
        if (!pins.isEmpty()) {
            int c;
            X509Certificate x509Certificate;
            int pinsSize;
            int p;
            if (this.certificateChainCleaner != null) {
                peerCertificates = this.certificateChainCleaner.clean(peerCertificates, hostname);
            }
            int certsSize = peerCertificates.size();
            for (c = 0; c < certsSize; c++) {
                x509Certificate = (X509Certificate) peerCertificates.get(c);
                f sha1 = null;
                f sha256 = null;
                pinsSize = pins.size();
                for (p = 0; p < pinsSize; p++) {
                    Pin pin = (Pin) pins.get(p);
                    if (pin.hashAlgorithm.equals("sha256/")) {
                        if (sha256 == null) {
                            sha256 = sha256(x509Certificate);
                        }
                        if (pin.hash.equals(sha256)) {
                            return;
                        }
                    } else if (pin.hashAlgorithm.equals("sha1/")) {
                        if (sha1 == null) {
                            sha1 = sha1(x509Certificate);
                        }
                        if (pin.hash.equals(sha1)) {
                            return;
                        }
                    } else {
                        throw new AssertionError("unsupported hashAlgorithm: " + pin.hashAlgorithm);
                    }
                }
            }
            StringBuilder message = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
            certsSize = peerCertificates.size();
            for (c = 0; c < certsSize; c++) {
                x509Certificate = (X509Certificate) peerCertificates.get(c);
                message.append("\n    ").append(pin(x509Certificate)).append(": ").append(x509Certificate.getSubjectDN().getName());
            }
            message.append("\n  Pinned certificates for ").append(hostname).append(":");
            pinsSize = pins.size();
            for (p = 0; p < pinsSize; p++) {
                message.append("\n    ").append((Pin) pins.get(p));
            }
            throw new SSLPeerUnverifiedException(message.toString());
        }
    }

    public final void check(String hostname, Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        check(hostname, Arrays.asList(peerCertificates));
    }

    final List<Pin> findMatchingPins(String hostname) {
        List<Pin> result = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(hostname)) {
                if (result.isEmpty()) {
                    result = new ArrayList();
                }
                result.add(pin);
            }
        }
        return result;
    }

    final CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner certificateChainCleaner) {
        if (Util.equal(this.certificateChainCleaner, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + sha256((X509Certificate) certificate).b();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static f sha1(X509Certificate x509Certificate) {
        return f.a(x509Certificate.getPublicKey().getEncoded()).d();
    }

    static f sha256(X509Certificate x509Certificate) {
        return f.a(x509Certificate.getPublicKey().getEncoded()).e();
    }
}
