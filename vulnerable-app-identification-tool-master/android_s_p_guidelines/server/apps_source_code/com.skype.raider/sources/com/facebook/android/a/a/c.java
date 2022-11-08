package com.facebook.android.a.a;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import com.adjust.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;

public final class c {
    private static boolean a;

    public static class a extends RuntimeException {
        public a(Throwable throwable) {
            super("Error fixing the Android's SecureRandom", throwable);
        }
    }

    public static class b extends SecureRandomSpi {
        private static final File a = new File("/dev/urandom");
        private static final Object b = new Object();
        private static DataInputStream c;
        private static OutputStream d;
        private boolean e;

        protected void engineSetSeed(byte[] bytes) {
            try {
                OutputStream out;
                synchronized (b) {
                    out = b();
                }
                out.write(bytes);
                out.flush();
            } catch (Throwable th) {
            } finally {
                this.e = true;
            }
        }

        protected void engineNextBytes(byte[] bytes) {
            if (!this.e) {
                engineSetSeed(c.c());
            }
            try {
                DataInputStream in;
                synchronized (b) {
                    in = a();
                }
                synchronized (in) {
                    in.readFully(bytes);
                }
            } catch (IOException e) {
                throw new SecurityException("Failed to read from " + a, e);
            }
        }

        protected byte[] engineGenerateSeed(int size) {
            byte[] seed = new byte[size];
            engineNextBytes(seed);
            return seed;
        }

        private static DataInputStream a() {
            DataInputStream dataInputStream;
            synchronized (b) {
                if (c == null) {
                    try {
                        c = new DataInputStream(new FileInputStream(a));
                    } catch (IOException e) {
                        throw new SecurityException("Failed to open " + a + " for reading", e);
                    }
                }
                dataInputStream = c;
            }
            return dataInputStream;
        }

        private static OutputStream b() {
            OutputStream outputStream;
            synchronized (b) {
                if (d == null) {
                    try {
                        d = new FileOutputStream(a);
                    } catch (IOException e) {
                        throw new SecurityException("Failed to open " + a + " for writing", e);
                    }
                }
                outputStream = d;
            }
            return outputStream;
        }
    }

    private static class c extends Provider {
        public c() {
            super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
            put("SecureRandom.SHA1PRNG", b.class.getName());
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

    public static synchronized void a() throws a {
        synchronized (c.class) {
            if (!a) {
                try {
                    if (VERSION.SDK_INT >= 17 && VERSION.SDK_INT <= 18) {
                        Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", new Class[]{byte[].class}).invoke(null, new Object[]{c()});
                        int intValue = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", new Class[]{String.class, Long.TYPE}).invoke(null, new Object[]{"/dev/urandom", Integer.valueOf(1024)})).intValue();
                        if (intValue != 1024) {
                            throw new IOException("Unexpected number of bytes read from Linux PRNG: " + intValue);
                        }
                    }
                    if (VERSION.SDK_INT <= 18) {
                        Provider[] providers = Security.getProviders("SecureRandom.SHA1PRNG");
                        if (providers == null || providers.length <= 0 || !c.class.equals(providers[0].getClass())) {
                            Security.insertProviderAt(new c(), 1);
                        }
                        SecureRandom secureRandom = new SecureRandom();
                        if (c.class.equals(secureRandom.getProvider().getClass())) {
                            secureRandom = SecureRandom.getInstance("SHA1PRNG");
                            if (!c.class.equals(secureRandom.getProvider().getClass())) {
                                throw new SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: " + secureRandom.getProvider().getClass());
                            }
                        }
                        throw new SecurityException("new SecureRandom() backed by wrong Provider: " + secureRandom.getProvider().getClass());
                    }
                    a = true;
                } catch (Throwable e) {
                    throw new SecurityException("SHA1PRNG not available", e);
                } catch (Throwable e2) {
                    throw new SecurityException("Failed to seed OpenSSL PRNG", e2);
                } catch (Throwable t) {
                    a aVar = new a(t);
                }
            }
        }
    }

    private static byte[] c() {
        try {
            ByteArrayOutputStream seedBuffer = new ByteArrayOutputStream();
            DataOutputStream seedBufferOut = new DataOutputStream(seedBuffer);
            seedBufferOut.writeLong(System.currentTimeMillis());
            seedBufferOut.writeLong(System.nanoTime());
            seedBufferOut.writeInt(Process.myPid());
            seedBufferOut.writeInt(Process.myUid());
            seedBufferOut.write(d());
            seedBufferOut.close();
            return seedBuffer.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    private static byte[] d() {
        StringBuilder result = new StringBuilder();
        String fingerprint = Build.FINGERPRINT;
        if (fingerprint != null) {
            result.append(fingerprint);
        }
        String serial = e();
        if (serial != null) {
            result.append(serial);
        }
        try {
            return result.toString().getBytes(Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }

    private static String e() {
        try {
            return (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception e) {
            return null;
        }
    }
}
