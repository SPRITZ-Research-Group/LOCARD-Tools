package com.microsoft.tokenshare;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class k extends l {
    private static final String a = k.class.getName();
    private static URL b;
    private static PublicKey c = null;
    private final AtomicReference<a> d = new AtomicReference();
    private final AtomicBoolean e = new AtomicBoolean(false);

    private static class a {
        @SerializedName("certificateChains")
        ArrayList<e> a;
        @SerializedName("applicationIds")
        ArrayList<String> b;
        @SerializedName("expiration")
        Long c;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        final long a() {
            return (this.c == null || this.c.longValue() <= 0) ? 86400000 : this.c.longValue();
        }
    }

    private static class b extends a {
        b(Throwable throwable) {
            super("GetRemoteConfigurations");
            a("ErrorClass", throwable.getClass());
            a("ErrorMessage", throwable.getMessage());
        }
    }

    private static class c {
        private final SharedPreferences a;

        c(@NonNull Context context) {
            this.a = context.getSharedPreferences("TokenShare_Configuration_Status", 0);
        }

        final long a() {
            return this.a.getLong("expirationTime", 0);
        }

        final void a(long time) {
            this.a.edit().putLong("expirationTime", time).apply();
        }

        final String b() {
            return this.a.getString("lastModified", null);
        }

        final void a(String eTag) {
            this.a.edit().putString("lastModified", eTag).apply();
        }
    }

    private class d implements Runnable {
        final /* synthetic */ k a;
        private final c b;
        private final Context c;

        d(k kVar, @NonNull Context context, @NonNull c refreshStatus) {
            this.a = kVar;
            this.c = context;
            this.b = refreshStatus;
        }

        public final void run() {
            Exception e;
            Exception e2;
            String b;
            Throwable th;
            InputStream networkStream = null;
            OutputStream outputFileStream = null;
            InputStream inputFileStream = null;
            File tmpFile = new File(this.c.getCacheDir(), "ts_configuration.tmp");
            File configurationFile = new File(this.c.getExternalCacheDir(), "ts_configuration.jwt");
            try {
                HttpURLConnection connection = (HttpURLConnection) k.b.openConnection();
                if (configurationFile.exists() && !TextUtils.isEmpty(this.b.b())) {
                    connection.setRequestProperty("If-Modified-Since", this.b.b());
                }
                InputStream networkStream2 = new BufferedInputStream(connection.getInputStream());
                try {
                    a configuration;
                    if (connection.getResponseCode() == 200) {
                        InputStream inputFileStream2;
                        OutputStream outputFileStream2 = new FileOutputStream(tmpFile.getAbsolutePath());
                        try {
                            m.a(networkStream2, outputFileStream2);
                            m.a(outputFileStream2);
                            inputFileStream2 = new BufferedInputStream(new FileInputStream(tmpFile.getAbsolutePath()));
                        } catch (IOException e3) {
                            e = e3;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            try {
                                b = k.a;
                                new StringBuilder("Can't get configuration from network").append(e2.toString());
                                h.a(b);
                                m.a(networkStream);
                                m.a(outputFileStream);
                                m.a(inputFileStream);
                                tmpFile.delete();
                                this.a.e.set(false);
                            } catch (Throwable th2) {
                                th = th2;
                                m.a(networkStream);
                                m.a(outputFileStream);
                                m.a(inputFileStream);
                                tmpFile.delete();
                                this.a.e.set(false);
                                throw th;
                            }
                        } catch (com.microsoft.tokenshare.a.e e4) {
                            e = e4;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            b = k.a;
                            new StringBuilder("Can't get configuration from network").append(e2.toString());
                            h.a(b);
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                        } catch (CertificateException e5) {
                            e = e5;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            b = k.a;
                            new StringBuilder("Can't get configuration from network").append(e2.toString());
                            h.a(b);
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                        } catch (Throwable th3) {
                            th = th3;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                            throw th;
                        }
                        try {
                            configuration = k.b(inputFileStream2);
                            m.a(inputFileStream2);
                            this.a.d.set(configuration);
                            inputFileStream = new BufferedInputStream(new FileInputStream(tmpFile.getAbsolutePath()));
                            outputFileStream = new FileOutputStream(configurationFile.getAbsolutePath());
                            m.a(inputFileStream, outputFileStream);
                            this.b.a(connection.getHeaderField("Last-Modified"));
                            this.b.a(System.currentTimeMillis() + configuration.a());
                        } catch (IOException e6) {
                            e = e6;
                            inputFileStream = inputFileStream2;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            b = k.a;
                            new StringBuilder("Can't get configuration from network").append(e2.toString());
                            h.a(b);
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                        } catch (com.microsoft.tokenshare.a.e e7) {
                            e = e7;
                            inputFileStream = inputFileStream2;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            b = k.a;
                            new StringBuilder("Can't get configuration from network").append(e2.toString());
                            h.a(b);
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                        } catch (CertificateException e8) {
                            e = e8;
                            inputFileStream = inputFileStream2;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            e2 = e;
                            b = k.a;
                            new StringBuilder("Can't get configuration from network").append(e2.toString());
                            h.a(b);
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                        } catch (Throwable th4) {
                            th = th4;
                            inputFileStream = inputFileStream2;
                            outputFileStream = outputFileStream2;
                            networkStream = networkStream2;
                            m.a(networkStream);
                            m.a(outputFileStream);
                            m.a(inputFileStream);
                            tmpFile.delete();
                            this.a.e.set(false);
                            throw th;
                        }
                    } else if (connection.getResponseCode() == 304) {
                        configuration = (a) this.a.d.get();
                        this.b.a((configuration != null ? configuration.a() : new a().a()) + System.currentTimeMillis());
                    }
                    m.a(networkStream2);
                    m.a(outputFileStream);
                    m.a(inputFileStream);
                    tmpFile.delete();
                    this.a.e.set(false);
                    networkStream = networkStream2;
                } catch (IOException e9) {
                    e = e9;
                    networkStream = networkStream2;
                } catch (com.microsoft.tokenshare.a.e e10) {
                    e = e10;
                    networkStream = networkStream2;
                    e2 = e;
                    b = k.a;
                    new StringBuilder("Can't get configuration from network").append(e2.toString());
                    h.a(b);
                    m.a(networkStream);
                    m.a(outputFileStream);
                    m.a(inputFileStream);
                    tmpFile.delete();
                    this.a.e.set(false);
                } catch (CertificateException e11) {
                    e = e11;
                    networkStream = networkStream2;
                    e2 = e;
                    b = k.a;
                    new StringBuilder("Can't get configuration from network").append(e2.toString());
                    h.a(b);
                    m.a(networkStream);
                    m.a(outputFileStream);
                    m.a(inputFileStream);
                    tmpFile.delete();
                    this.a.e.set(false);
                } catch (Throwable th5) {
                    th = th5;
                    networkStream = networkStream2;
                    m.a(networkStream);
                    m.a(outputFileStream);
                    m.a(inputFileStream);
                    tmpFile.delete();
                    this.a.e.set(false);
                    throw th;
                }
            } catch (IOException e12) {
                e = e12;
                e2 = e;
                b = k.a;
                new StringBuilder("Can't get configuration from network").append(e2.toString());
                h.a(b);
                m.a(networkStream);
                m.a(outputFileStream);
                m.a(inputFileStream);
                tmpFile.delete();
                this.a.e.set(false);
            } catch (com.microsoft.tokenshare.a.e e13) {
                e = e13;
                e2 = e;
                b = k.a;
                new StringBuilder("Can't get configuration from network").append(e2.toString());
                h.a(b);
                m.a(networkStream);
                m.a(outputFileStream);
                m.a(inputFileStream);
                tmpFile.delete();
                this.a.e.set(false);
            } catch (CertificateException e14) {
                e = e14;
                e2 = e;
                b = k.a;
                new StringBuilder("Can't get configuration from network").append(e2.toString());
                h.a(b);
                m.a(networkStream);
                m.a(outputFileStream);
                m.a(inputFileStream);
                tmpFile.delete();
                this.a.e.set(false);
            }
        }
    }

    static class e {
        @SerializedName("signatures")
        List<String> a;

        e() {
        }
    }

    static {
        try {
            b = new URL("https://oneclient.sfx.ms/mobile/ts_configuration.jwt");
        } catch (MalformedURLException e) {
        }
    }

    k() {
    }

    @NonNull
    public final List<e> a(@NonNull Context context) {
        Exception e;
        try {
            return c(context).a;
        } catch (IOException e2) {
            e = e2;
        } catch (com.microsoft.tokenshare.a.e e3) {
            e = e3;
        } catch (CertificateException e4) {
            e = e4;
        }
        b bVar = new b(e);
        b.a;
        return super.a(context);
    }

    @NonNull
    public final List<String> b(@NonNull Context context) {
        Exception e;
        try {
            return c(context).b;
        } catch (IOException e2) {
            e = e2;
        } catch (com.microsoft.tokenshare.a.e e3) {
            e = e3;
        } catch (CertificateException e4) {
            e = e4;
        }
        b bVar = new b(e);
        b.a;
        return super.b(context);
    }

    @NonNull
    private a c(@NonNull Context context) throws IOException, com.microsoft.tokenshare.a.e, CertificateException {
        Throwable th;
        a result = (a) this.d.get();
        if (result != null) {
            return result;
        }
        File cachedConfiguration = new File(context.getExternalCacheDir(), "ts_configuration.jwt");
        InputStream stream = null;
        try {
            if (cachedConfiguration.exists()) {
                InputStream stream2 = new FileInputStream(cachedConfiguration.getAbsolutePath());
                try {
                    result = b(stream2);
                    stream = stream2;
                } catch (IOException e) {
                    stream = stream2;
                    m.a(stream);
                    if (result == null) {
                        try {
                            stream = context.getAssets().open("ts_configuration.jwt");
                            result = b(stream);
                            new c(context).a(0);
                        } finally {
                            m.a(stream);
                        }
                    }
                    this.d.set(result);
                    d(context);
                    return (a) this.d.get();
                } catch (com.microsoft.tokenshare.a.e e2) {
                    stream = stream2;
                    try {
                        m.a(stream);
                        cachedConfiguration.delete();
                        m.a(stream);
                        if (result == null) {
                            stream = context.getAssets().open("ts_configuration.jwt");
                            result = b(stream);
                            new c(context).a(0);
                        }
                        this.d.set(result);
                        d(context);
                        return (a) this.d.get();
                    } catch (Throwable th2) {
                        th = th2;
                        m.a(stream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    stream = stream2;
                    m.a(stream);
                    throw th;
                }
            }
            m.a(stream);
        } catch (IOException e3) {
            m.a(stream);
            if (result == null) {
                stream = context.getAssets().open("ts_configuration.jwt");
                result = b(stream);
                new c(context).a(0);
            }
            this.d.set(result);
            d(context);
            return (a) this.d.get();
        } catch (com.microsoft.tokenshare.a.e e4) {
            m.a(stream);
            cachedConfiguration.delete();
            m.a(stream);
            if (result == null) {
                stream = context.getAssets().open("ts_configuration.jwt");
                result = b(stream);
                new c(context).a(0);
            }
            this.d.set(result);
            d(context);
            return (a) this.d.get();
        }
        if (result == null) {
            stream = context.getAssets().open("ts_configuration.jwt");
            result = b(stream);
            new c(context).a(0);
        }
        this.d.set(result);
        d(context);
        return (a) this.d.get();
    }

    private synchronized void d(@NonNull Context context) {
        c refreshStatus = new c(context.getApplicationContext());
        if (refreshStatus.a() < System.currentTimeMillis() && !this.e.getAndSet(true)) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new d(this, context, refreshStatus));
        }
    }

    @NonNull
    private static a b(@NonNull InputStream stream) throws com.microsoft.tokenshare.a.e, CertificateException, IOException {
        Throwable th;
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(stream));
            try {
                a aVar = (a) new com.microsoft.tokenshare.a.c().a(c()).a(reader2.readLine(), a.class);
                m.a(reader2);
                return aVar;
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                m.a(reader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            m.a(reader);
            throw th;
        }
    }

    private static synchronized Key c() throws CertificateException {
        Key key;
        synchronized (k.class) {
            if (c == null) {
                c = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream("-----BEGIN CERTIFICATE-----\nMIIHTzCCBTegAwIBAgITMwAAAJEHan8WujQeWwAAAAAAkTANBgkqhkiG9w0BAQsF\nADCBkDELMAkGA1UEBhMCVVMxEzARBgNVBAgTCldhc2hpbmd0b24xEDAOBgNVBAcT\nB1JlZG1vbmQxHjAcBgNVBAoTFU1pY3Jvc29mdCBDb3Jwb3JhdGlvbjE6MDgGA1UE\nAxMxTWljcm9zb2Z0IENvcnBvcmF0aW9uIFRoaXJkIFBhcnR5IE1hcmtldHBsYWNl\nIFBDQTAeFw0xNzA0MTkxODAxNDJaFw0yNzA0MTkxODAxNDJaMIGyMQswCQYDVQQG\nEwJVUzETMBEGA1UECBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEeMBwG\nA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMRUwEwYDVQQLEwxKU09OIFNpZ25p\nbmcxRTBDBgNVBAMTPE1pY3Jvc29mdCBDb3Jwb3JhdGlvbiBUaGlyZCBQYXJ0eSBN\nYXJrZXRwbGFjZSAoRG8gTm90IFRydXN0KTCCAiIwDQYJKoZIhvcNAQEBBQADggIP\nADCCAgoCggIBALeVe2CkvfePyyf4oaCnRGBgDZzkVbokMtsEARxBRgvaRFMxTJtu\nOHonsUedK/0BRbuALahYowTcVns+M9A+zWrDrO4Lm7HN52EHPu1RrKi/Lovovja+\nZlbpkZU4diT6VP1Az4PfOP4L/SSKipnF2AKxRuryp1VrFa6WEs++pegEbdUjltBJ\nYjXAOHn52v+tJANb7MbeZz98hn9s8PO5vn//8mvimQul5s9BwgSSEleOYqlB0pLW\n/90gBPonoldeyLN3ZTv2z+LMCPJ/OmWvylGyDZICsuE++UFBCgj8tWMYrDbXrbI2\nE5EQIDLJb9R2DgcmrB1weyfHiEG/lAFvP34QbS6eT/uekYeArJQGO+jsDpNGCDSU\nY6KbEnJ1k05EVYQT032YTwFVh25KK8XjsNUYQ6jP3Gwc7BK+RQNCfvN3BOCzJwvH\ntFgk9CLpeJsd07H1UVdSKf7Lc26f1EK1o6zPuj+B56YUFaTcmFqApyYCB4lGpdha\nscHkn6LYsSte+QP8EcCW/0MZsPKOKDgEjkT5TuuTjeQkkFUpB1I6ZCRQT4qou/EA\n1OwaTniC5Gb6hYPiritABtopXV9AYJC6IvAiIqBBoE2AQQrxRkyjta/bhNLakQ83\nCauMagaD3Tx2AvUXaJuP6X6AHlyZ/p4N2yHeAsQb3+Kg+wGFzp36LcbzAgMBAAGj\nggF8MIIBeDAVBgNVHSUEDjAMBgorBgEEAYI3TAIBMB0GA1UdDgQWBBTqK9/tEAAU\n80UPa9Lkp+k2PwtxzjBRBgNVHREESjBIpEYwRDEMMAoGA1UECxMDQU9DMTQwMgYD\nVQQFEysyMzg4NDYrNWEyODFjOWMtMTYyNi00NjkwLThiMTctYTlhZjgzY2UxYjZi\nMB8GA1UdIwQYMBaAFK6R5GCfmMAL3xoLa/BWMydHrMfHMFwGA1UdHwRVMFMwUaBP\noE2GS2h0dHA6Ly9jcmwubWljcm9zb2Z0LmNvbS9wa2kvY3JsL3Byb2R1Y3RzL01p\nY0NvclRoaVBhck1hclBDQV8yMDEwLTEwLTA1LmNybDBgBggrBgEFBQcBAQRUMFIw\nUAYIKwYBBQUHMAKGRGh0dHA6Ly93d3cubWljcm9zb2Z0LmNvbS9wa2kvY2VydHMv\nTWljQ29yVGhpUGFyTWFyUENBXzIwMTAtMTAtMDUuY3J0MAwGA1UdEwEB/wQCMAAw\nDQYJKoZIhvcNAQELBQADggIBAKdKB4Z/TyOLPMtk6gEU2iJKI841s5WBW/txHUtk\nDWJO8RlFe1J8oAtowHE+Pl2rWED0JhMF80XqPwcMRvwOp9ASY0G0OnWSNT+y5Vn+\nuUCr7u9I7f7LFQd3Ioe2bXnOhz5TJUr7Udd3vdjQa8LyTH7hqJw8PCUMlGGCpvnd\n1Ac4RphBSNeqMtEe9k0C2ySghrRfmFa2LwJJ7de/NwlN+TqCWWqwseatdqYVxdXg\nrw4R1DzXfbsMSy08U6AXBiavOEQvULwnFk4NZ4uMJ3QtyLBTEiNlg2NDpHnr5Ctx\ndd2cEXmiCxgLDMs6aF1WkAThxSXejvI3l9br2XDeetXDqOv+KPWLIdfsqkehqtqQ\n+pVy8h4x0uHUqCR7qrTSgSHGuPUUe9/Vg1bk3jPcwzBBT38LOun3ZNt+TzdPsog6\nHALZ74qBEmgm7JtXqRf2tQ1J4Qs+VQN7ftloL/IFl30LHMgfrH6uj2Cwgj7sISqw\nDbfttUx11l9wA4jVoo2NLx9teU8b68QCV0yqeEoiPP2F3IN9Y5THEdehfcsoTNP+\noBlikToVVDpVNN5F/fWRmWv3EAmjWfQdTyC3ZRzRmpS8lp+qHJ2YfQ0bn6iRowOc\nGWNL+BJAHOy2sbQ+Dx844jf3CPU0o89IqMAvnIT4L/nL4ETid0TK8YVwzZr/2w78\nmHVu\n-----END CERTIFICATE-----\n".getBytes())).getPublicKey();
            }
            key = c;
        }
        return key;
    }
}
