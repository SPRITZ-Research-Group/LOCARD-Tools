package okhttp3.internal;

import c.c;
import c.e;
import c.f;
import c.t;
import com.adjust.sdk.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final RequestBody EMPTY_REQUEST = RequestBody.create(null, EMPTY_BYTE_ARRAY);
    public static final ResponseBody EMPTY_RESPONSE = ResponseBody.create(null, EMPTY_BYTE_ARRAY);
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Comparator<String> NATURAL_ORDER = new Comparator<String>() {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    };
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
    private static final Charset UTF_16_BE = Charset.forName("UTF-16BE");
    private static final f UTF_16_BE_BOM = f.c("feff");
    private static final Charset UTF_16_LE = Charset.forName("UTF-16LE");
    private static final f UTF_16_LE_BOM = f.c("fffe");
    private static final Charset UTF_32_BE = Charset.forName("UTF-32BE");
    private static final f UTF_32_BE_BOM = f.c("0000ffff");
    private static final Charset UTF_32_LE = Charset.forName("UTF-32LE");
    private static final f UTF_32_LE_BOM = f.c("ffff0000");
    public static final Charset UTF_8 = Charset.forName(Constants.ENCODING);
    private static final f UTF_8_BOM = f.c("efbbbf");
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    private static final Method addSuppressedExceptionMethod;

    static {
        Method m;
        try {
            m = Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Exception e) {
            m = null;
        }
        addSuppressedExceptionMethod = m;
    }

    public static void addSuppressedIfPossible(Throwable e, Throwable suppressed) {
        if (addSuppressedExceptionMethod != null) {
            try {
                addSuppressedExceptionMethod.invoke(e, new Object[]{suppressed});
            } catch (InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
            }
        }
    }

    private Util() {
    }

    public static void checkOffsetAndCount(long arrayLength, long offset, long count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!isAndroidGetsocknameError(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static boolean discard(t source, int timeout, TimeUnit timeUnit) {
        try {
            return skipAll(source, timeout, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean skipAll(t source, int duration, TimeUnit timeUnit) throws IOException {
        long originalDuration;
        long now = System.nanoTime();
        if (source.timeout().hasDeadline()) {
            originalDuration = source.timeout().deadlineNanoTime() - now;
        } else {
            originalDuration = Long.MAX_VALUE;
        }
        source.timeout().deadlineNanoTime(Math.min(originalDuration, timeUnit.toNanos((long) duration)) + now);
        try {
            c skipBuffer = new c();
            while (source.read(skipBuffer, 8192) != -1) {
                skipBuffer.t();
            }
            if (originalDuration == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (originalDuration == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            return false;
        } catch (Throwable th) {
            if (originalDuration == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
            throw th;
        }
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static <T> List<T> immutableList(T... elements) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) elements.clone()));
    }

    public static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }

    public static String[] intersect(Comparator<? super String> comparator, String[] first, String[] second) {
        List<String> result = new ArrayList();
        for (String a : first) {
            for (String b : second) {
                if (comparator.compare(a, b) == 0) {
                    result.add(a);
                    break;
                }
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    public static boolean nonEmptyIntersection(Comparator<String> comparator, String[] first, String[] second) {
        if (first == null || second == null || first.length == 0 || second.length == 0) {
            return false;
        }
        for (String a : first) {
            for (String b : second) {
                if (comparator.compare(a, b) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String hostHeader(HttpUrl url, boolean includeDefaultPort) {
        String host;
        if (url.host().contains(":")) {
            host = "[" + url.host() + "]";
        } else {
            host = url.host();
        }
        return (includeDefaultPort || url.port() != HttpUrl.defaultPort(url.scheme())) ? host + ":" + url.port() : host;
    }

    public static boolean isAndroidGetsocknameError(AssertionError e) {
        return (e.getCause() == null || e.getMessage() == null || !e.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static int indexOf(Comparator<String> comparator, String[] array, String value) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static String[] concat(String[] array, String value) {
        String[] result = new String[(array.length + 1)];
        System.arraycopy(array, 0, result, 0, array.length);
        result[result.length - 1] = value;
        return result;
    }

    public static int skipLeadingAsciiWhitespace(String input, int pos, int limit) {
        int i = pos;
        while (i < limit) {
            switch (input.charAt(i)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i++;
                default:
                    return i;
            }
        }
        return limit;
    }

    public static int skipTrailingAsciiWhitespace(String input, int pos, int limit) {
        int i = limit - 1;
        while (i >= pos) {
            switch (input.charAt(i)) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    i--;
                default:
                    return i + 1;
            }
        }
        return pos;
    }

    public static String trimSubstring(String string, int pos, int limit) {
        int start = skipLeadingAsciiWhitespace(string, pos, limit);
        return string.substring(start, skipTrailingAsciiWhitespace(string, start, limit));
    }

    public static int delimiterOffset(String input, int pos, int limit, String delimiters) {
        for (int i = pos; i < limit; i++) {
            if (delimiters.indexOf(input.charAt(i)) != -1) {
                return i;
            }
        }
        return limit;
    }

    public static int delimiterOffset(String input, int pos, int limit, char delimiter) {
        for (int i = pos; i < limit; i++) {
            if (input.charAt(i) == delimiter) {
                return i;
            }
        }
        return limit;
    }

    public static String canonicalizeHost(String host) {
        if (host.contains(":")) {
            InetAddress inetAddress;
            if (host.startsWith("[") && host.endsWith("]")) {
                inetAddress = decodeIpv6(host, 1, host.length() - 1);
            } else {
                inetAddress = decodeIpv6(host, 0, host.length());
            }
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            throw new AssertionError("Invalid IPv6 address: '" + host + "'");
        }
        try {
            String result = IDN.toASCII(host).toLowerCase(Locale.US);
            if (result.isEmpty()) {
                return null;
            }
            if (containsInvalidHostnameAsciiCodes(result)) {
                return null;
            }
            return result;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static boolean containsInvalidHostnameAsciiCodes(String hostnameAscii) {
        for (int i = 0; i < hostnameAscii.length(); i++) {
            char c = hostnameAscii.charAt(i);
            if (c <= 31 || c >= 127 || " #%/:?@[\\]".indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfControlOrNonAscii(String input) {
        int i = 0;
        int length = input.length();
        while (i < length) {
            char c = input.charAt(i);
            if (c <= 31 || c >= 127) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static Charset bomAwareCharset(e source, Charset charset) throws IOException {
        if (source.b(UTF_8_BOM)) {
            source.h((long) UTF_8_BOM.h());
            return UTF_8;
        } else if (source.b(UTF_16_BE_BOM)) {
            source.h((long) UTF_16_BE_BOM.h());
            return UTF_16_BE;
        } else if (source.b(UTF_16_LE_BOM)) {
            source.h((long) UTF_16_LE_BOM.h());
            return UTF_16_LE;
        } else if (source.b(UTF_32_BE_BOM)) {
            source.h((long) UTF_32_BE_BOM.h());
            return UTF_32_BE;
        } else if (!source.b(UTF_32_LE_BOM)) {
            return charset;
        } else {
            source.h((long) UTF_32_LE_BOM.h());
            return UTF_32_LE;
        }
    }

    public static int checkDuration(String name, long duration, TimeUnit unit) {
        if (duration < 0) {
            throw new IllegalArgumentException(name + " < 0");
        } else if (unit == null) {
            throw new NullPointerException("unit == null");
        } else {
            long millis = unit.toMillis(duration);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException(name + " too large.");
            } else if (millis != 0 || duration <= 0) {
                return (int) millis;
            } else {
                throw new IllegalArgumentException(name + " too small.");
            }
        }
    }

    public static AssertionError assertionError(String message, Exception e) {
        AssertionError assertionError = new AssertionError(message);
        try {
            assertionError.initCause(e);
        } catch (IllegalStateException e2) {
        }
        return assertionError;
    }

    public static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    @Nullable
    private static InetAddress decodeIpv6(String input, int pos, int limit) {
        byte[] address = new byte[16];
        int b = 0;
        int compress = -1;
        int groupOffset = -1;
        int i = pos;
        while (i < limit) {
            if (b == 16) {
                return null;
            }
            if (i + 2 <= limit && input.regionMatches(i, "::", 0, 2)) {
                if (compress == -1) {
                    i += 2;
                    b += 2;
                    compress = b;
                    if (i == limit) {
                        break;
                    }
                }
                return null;
            } else if (b != 0) {
                if (input.regionMatches(i, ":", 0, 1)) {
                    i++;
                } else if (!input.regionMatches(i, ".", 0, 1)) {
                    return null;
                } else {
                    if (!decodeIpv4Suffix(input, groupOffset, limit, address, b - 2)) {
                        return null;
                    }
                    b += 2;
                }
            }
            int value = 0;
            groupOffset = i;
            while (i < limit) {
                int hexDigit = decodeHexDigit(input.charAt(i));
                if (hexDigit == -1) {
                    break;
                }
                value = (value << 4) + hexDigit;
                i++;
            }
            int groupLength = i - groupOffset;
            if (groupLength == 0 || groupLength > 4) {
                return null;
            }
            int i2 = b + 1;
            address[b] = (byte) ((value >>> 8) & 255);
            b = i2 + 1;
            address[i2] = (byte) (value & 255);
        }
        if (b != 16) {
            if (compress == -1) {
                return null;
            }
            System.arraycopy(address, compress, address, 16 - (b - compress), b - compress);
            Arrays.fill(address, compress, (16 - b) + compress, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(address);
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    private static boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int i = pos;
        int b = addressOffset;
        while (i < limit) {
            if (b == address.length) {
                return false;
            }
            if (b != addressOffset) {
                if (input.charAt(i) != '.') {
                    return false;
                }
                i++;
            }
            int value = 0;
            int groupOffset = i;
            while (i < limit) {
                char c = input.charAt(i);
                if (c < '0' || c > '9') {
                    break;
                } else if (value == 0 && groupOffset != i) {
                    return false;
                } else {
                    value = ((value * 10) + c) - 48;
                    if (value > 255) {
                        return false;
                    }
                    i++;
                }
            }
            if (i - groupOffset == 0) {
                return false;
            }
            int b2 = b + 1;
            address[b] = (byte) value;
            b = b2;
        }
        if (b == addressOffset + 4) {
            return true;
        }
        return false;
    }

    private static String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        int i = 0;
        while (i < address.length) {
            int currentRunOffset = i;
            while (i < 16 && address[i] == (byte) 0 && address[i + 1] == (byte) 0) {
                i += 2;
            }
            int currentRunLength = i - currentRunOffset;
            if (currentRunLength > longestRunLength && currentRunLength >= 4) {
                longestRunOffset = currentRunOffset;
                longestRunLength = currentRunLength;
            }
            i += 2;
        }
        c result = new c();
        i = 0;
        while (i < address.length) {
            if (i == longestRunOffset) {
                result.b(58);
                i += longestRunLength;
                if (i == 16) {
                    result.b(58);
                }
            } else {
                if (i > 0) {
                    result.b(58);
                }
                result.k((long) (((address[i] & 255) << 8) | (address[i + 1] & 255)));
                i += 2;
            }
        }
        return result.q();
    }

    public static X509TrustManager platformTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw assertionError("No System TLS", e);
        }
    }
}
