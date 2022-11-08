package com.google.gson.a.a;

import com.google.gson.a.f;
import com.google.gson.annotations.SerializedName;
import com.google.gson.c.b;
import com.google.gson.c.c;
import com.google.gson.e;
import com.google.gson.g;
import com.google.gson.i;
import com.google.gson.j;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class n {
    public static final r<String> A = new r<String>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.b((String) obj);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            } else if (f == b.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }
    };
    public static final r<BigDecimal> B = new r<BigDecimal>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass9.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((BigDecimal) obj);
        }

        private static BigDecimal b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return new BigDecimal(in.h());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final r<BigInteger> C = new r<BigInteger>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass10.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((BigInteger) obj);
        }

        private static BigInteger b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return new BigInteger(in.h());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final s D = a(String.class, A);
    public static final r<StringBuilder> E = new r<StringBuilder>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            StringBuilder stringBuilder = (StringBuilder) obj;
            if (stringBuilder == null) {
                str = null;
            } else {
                str = stringBuilder.toString();
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }
    };
    public static final s F = a(StringBuilder.class, E);
    public static final r<StringBuffer> G = new r<StringBuffer>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            StringBuffer stringBuffer = (StringBuffer) obj;
            if (stringBuffer == null) {
                str = null;
            } else {
                str = stringBuffer.toString();
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }
    };
    public static final s H = a(StringBuffer.class, G);
    public static final r<URL> I = new r<URL>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if ("null".equals(h)) {
                return null;
            }
            return new URL(h);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            URL url = (URL) obj;
            if (url == null) {
                str = null;
            } else {
                str = url.toExternalForm();
            }
            cVar.b(str);
        }
    };
    public static final s J = a(URL.class, I);
    public static final r<URI> K = new r<URI>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass15.b(aVar);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            URI uri = (URI) obj;
            if (uri == null) {
                str = null;
            } else {
                str = uri.toASCIIString();
            }
            cVar.b(str);
        }

        private static URI b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                String nextString = in.h();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (Throwable e) {
                throw new j(e);
            }
        }
    };
    public static final s L = a(URI.class, K);
    public static final r<InetAddress> M = new r<InetAddress>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            InetAddress inetAddress = (InetAddress) obj;
            if (inetAddress == null) {
                str = null;
            } else {
                str = inetAddress.getHostAddress();
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }
    };
    public static final s N = b(InetAddress.class, M);
    public static final r<UUID> O = new r<UUID>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            UUID uuid = (UUID) obj;
            if (uuid == null) {
                str = null;
            } else {
                str = uuid.toString();
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }
    };
    public static final s P = a(UUID.class, O);
    public static final r<Currency> Q = new r<Currency>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.b(((Currency) obj).getCurrencyCode());
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return Currency.getInstance(aVar.h());
        }
    }.a();
    public static final s R = a(Currency.class, Q);
    public static final s S = new s() {
        public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
            if (typeToken.a() != Timestamp.class) {
                return null;
            }
            final r<Date> dateTypeAdapter = gson.a(Date.class);
            return new r<Timestamp>(this) {
                final /* synthetic */ AnonymousClass19 b;

                public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
                    dateTypeAdapter.a(cVar, (Timestamp) obj);
                }

                public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
                    Date date = (Date) dateTypeAdapter.a(aVar);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }
            };
        }
    };
    public static final r<Calendar> T = new r<Calendar>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            int i = 0;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.f() != b.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if ("year".equals(g)) {
                    i6 = m;
                } else if ("month".equals(g)) {
                    i5 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i4 = m;
                } else if ("hourOfDay".equals(g)) {
                    i3 = m;
                } else if ("minute".equals(g)) {
                    i2 = m;
                } else if ("second".equals(g)) {
                    i = m;
                }
            }
            aVar.d();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            Calendar calendar = (Calendar) obj;
            if (calendar == null) {
                cVar.f();
                return;
            }
            cVar.d();
            cVar.a("year");
            cVar.a((long) calendar.get(1));
            cVar.a("month");
            cVar.a((long) calendar.get(2));
            cVar.a("dayOfMonth");
            cVar.a((long) calendar.get(5));
            cVar.a("hourOfDay");
            cVar.a((long) calendar.get(11));
            cVar.a("minute");
            cVar.a((long) calendar.get(12));
            cVar.a("second");
            cVar.a((long) calendar.get(13));
            cVar.e();
        }
    };
    public static final s U;
    public static final r<Locale> V = new r<Locale>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String nextToken;
            String nextToken2;
            String nextToken3;
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            if (stringTokenizer.hasMoreElements()) {
                nextToken = stringTokenizer.nextToken();
            } else {
                nextToken = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken2 = stringTokenizer.nextToken();
            } else {
                nextToken2 = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken3 = stringTokenizer.nextToken();
            } else {
                nextToken3 = null;
            }
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            Locale locale = (Locale) obj;
            if (locale == null) {
                str = null;
            } else {
                str = locale.toString();
            }
            cVar.b(str);
        }
    };
    public static final s W = a(Locale.class, V);
    public static final r<i> X = new r<i>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return b(aVar);
        }

        private i b(com.google.gson.c.a in) throws IOException {
            switch (AnonymousClass29.a[in.f().ordinal()]) {
                case 1:
                    return new com.google.gson.n(new f(in.h()));
                case 2:
                    return new com.google.gson.n(Boolean.valueOf(in.i()));
                case 3:
                    return new com.google.gson.n(in.h());
                case 4:
                    in.j();
                    return k.a;
                case 5:
                    i array = new g();
                    in.a();
                    while (in.e()) {
                        array.a(b(in));
                    }
                    in.b();
                    return array;
                case 6:
                    i object = new l();
                    in.c();
                    while (in.e()) {
                        object.a(in.g(), b(in));
                    }
                    in.d();
                    return object;
                default:
                    throw new IllegalArgumentException();
            }
        }

        private void a(c out, i value) throws IOException {
            if (value == null || (value instanceof k)) {
                out.f();
            } else if (value instanceof com.google.gson.n) {
                com.google.gson.n primitive = value.g();
                if (primitive.i()) {
                    out.a(primitive.a());
                } else if (primitive.h()) {
                    out.a(primitive.f());
                } else {
                    out.b(primitive.b());
                }
            } else if (value instanceof g) {
                out.b();
                if (value instanceof g) {
                    Iterator it = ((g) value).iterator();
                    while (it.hasNext()) {
                        a(out, (i) it.next());
                    }
                    out.c();
                    return;
                }
                throw new IllegalStateException("This is not a JSON Array.");
            } else if (value instanceof l) {
                out.d();
                if (value instanceof l) {
                    for (Entry<String, i> e : ((l) value).h()) {
                        out.a((String) e.getKey());
                        a(out, (i) e.getValue());
                    }
                    out.e();
                    return;
                }
                throw new IllegalStateException("Not a JSON Object: " + value);
            } else {
                throw new IllegalArgumentException("Couldn't write " + value.getClass());
            }
        }
    };
    public static final s Y = b(i.class, X);
    public static final s Z = new s() {
        public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
            Class<? super T> rawType = typeToken.a();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new a(rawType);
        }
    };
    public static final r<Class> a = new r<Class>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            Class cls = (Class) obj;
            if (cls == null) {
                cVar.f();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final s b = a(Class.class, a);
    public static final r<BitSet> c = new r<BitSet>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass12.b(aVar);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            BitSet bitSet = (BitSet) obj;
            if (bitSet == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (int i = 0; i < bitSet.length(); i++) {
                int i2;
                if (bitSet.get(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                cVar.a((long) i2);
            }
            cVar.c();
        }

        private static BitSet b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            BitSet bitset = new BitSet();
            in.a();
            int i = 0;
            b tokenType = in.f();
            while (tokenType != b.END_ARRAY) {
                boolean set;
                switch (AnonymousClass29.a[tokenType.ordinal()]) {
                    case 1:
                        if (in.m() == 0) {
                            set = false;
                            break;
                        }
                        set = true;
                        break;
                    case 2:
                        set = in.i();
                        break;
                    case 3:
                        String stringValue = in.h();
                        try {
                            if (Integer.parseInt(stringValue) == 0) {
                                set = false;
                                break;
                            }
                            set = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new p("Error: Expecting: bitset number value (1, 0), Found: " + stringValue);
                        }
                    default:
                        throw new p("Invalid bitset value type: " + tokenType);
                }
                if (set) {
                    bitset.set(i);
                }
                i++;
                tokenType = in.f();
            }
            in.b();
            return bitset;
        }
    };
    public static final s d = a(BitSet.class, c);
    public static final r<Boolean> e = new r<Boolean>() {
        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Boolean) obj);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                return Boolean.valueOf(aVar.i());
            }
        }
    };
    public static final r<Boolean> f = new r<Boolean>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            Boolean bool = (Boolean) obj;
            if (bool == null) {
                str = "null";
            } else {
                str = bool.toString();
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }
    };
    public static final s g = a(Boolean.TYPE, Boolean.class, e);
    public static final r<Number> h = new r<Number>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass31.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        private static Number b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) in.m());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final s i = a(Byte.TYPE, Byte.class, h);
    public static final r<Number> j = new r<Number>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass32.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        private static Number b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return Short.valueOf((short) in.m());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final s k = a(Short.TYPE, Short.class, j);
    public static final r<Number> l = new r<Number>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass33.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        private static Number b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return Integer.valueOf(in.m());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final s m = a(Integer.TYPE, Integer.class, l);
    public static final r<AtomicInteger> n = new r<AtomicInteger>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass34.b(aVar);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((long) ((AtomicInteger) obj).get());
        }

        private static AtomicInteger b(com.google.gson.c.a in) throws IOException {
            try {
                return new AtomicInteger(in.m());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    }.a();
    public static final s o = a(AtomicInteger.class, n);
    public static final r<AtomicBoolean> p = new r<AtomicBoolean>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a(((AtomicBoolean) obj).get());
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return new AtomicBoolean(aVar.i());
        }
    }.a();
    public static final s q = a(AtomicBoolean.class, p);
    public static final r<AtomicIntegerArray> r = new r<AtomicIntegerArray>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass2.b(aVar);
        }

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            cVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                cVar.a((long) atomicIntegerArray.get(i));
            }
            cVar.c();
        }

        private static AtomicIntegerArray b(com.google.gson.c.a in) throws IOException {
            List<Integer> list = new ArrayList();
            in.a();
            while (in.e()) {
                try {
                    list.add(Integer.valueOf(in.m()));
                } catch (Throwable e) {
                    throw new p(e);
                }
            }
            in.b();
            int length = list.size();
            AtomicIntegerArray array = new AtomicIntegerArray(length);
            for (int i = 0; i < length; i++) {
                array.set(i, ((Integer) list.get(i)).intValue());
            }
            return array;
        }
    }.a();
    public static final s s = a(AtomicIntegerArray.class, r);
    public static final r<Number> t = new r<Number>() {
        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            return AnonymousClass3.b(aVar);
        }

        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        private static Number b(com.google.gson.c.a in) throws IOException {
            if (in.f() == b.NULL) {
                in.j();
                return null;
            }
            try {
                return Long.valueOf(in.l());
            } catch (Throwable e) {
                throw new p(e);
            }
        }
    };
    public static final r<Number> u = new r<Number>() {
        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }
    };
    public static final r<Number> v = new r<Number>() {
        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }
    };
    public static final r<Number> w = new r<Number>() {
        public final /* bridge */ /* synthetic */ void a(c cVar, Object obj) throws IOException {
            cVar.a((Number) obj);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            b f = aVar.f();
            switch (f) {
                case NUMBER:
                    return new f(aVar.h());
                case NULL:
                    aVar.j();
                    return null;
                default:
                    throw new p("Expecting number, got: " + f);
            }
        }
    };
    public static final s x = a(Number.class, w);
    public static final r<Character> y = new r<Character>() {
        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            Character ch = (Character) obj;
            if (ch == null) {
                str = null;
            } else {
                str = String.valueOf(ch);
            }
            cVar.b(str);
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new p("Expecting character, got: " + h);
        }
    };
    public static final s z = a(Character.TYPE, Character.class, y);

    private static final class a<T extends Enum<T>> extends r<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public final /* synthetic */ void a(c cVar, Object obj) throws IOException {
            String str;
            Enum enumR = (Enum) obj;
            if (enumR == null) {
                str = null;
            } else {
                str = (String) this.b.get(enumR);
            }
            cVar.b(str);
        }

        public a(Class<T> classOfT) {
            try {
                for (T constant : (Enum[]) classOfT.getEnumConstants()) {
                    String name = constant.name();
                    SerializedName annotation = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (annotation != null) {
                        name = annotation.value();
                        for (String alternate : annotation.alternate()) {
                            this.a.put(alternate, constant);
                        }
                    }
                    this.a.put(name, constant);
                    this.b.put(constant, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }
    }

    static {
        final Class cls = Calendar.class;
        final Class cls2 = GregorianCalendar.class;
        final r rVar = T;
        U = new s() {
            public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
                Class<? super T> rawType = typeToken.a();
                return (rawType == cls || rawType == cls2) ? rVar : null;
            }

            public final String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + rVar + "]";
            }
        };
    }

    public static <TT> s a(final Class<TT> type, final r<TT> typeAdapter) {
        return new s() {
            public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
                return typeToken.a() == type ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[type=" + type.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> s a(final Class<TT> unboxed, final Class<TT> boxed, final r<? super TT> typeAdapter) {
        return new s() {
            public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
                Class<? super T> rawType = typeToken.a();
                return (rawType == unboxed || rawType == boxed) ? typeAdapter : null;
            }

            public final String toString() {
                return "Factory[type=" + boxed.getName() + "+" + unboxed.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    private static <T1> s b(final Class<T1> clazz, final r<T1> typeAdapter) {
        return new s() {
            public final <T2> r<T2> a(e gson, com.google.gson.b.a<T2> typeToken) {
                final Class<? super T2> requestedType = typeToken.a();
                if (clazz.isAssignableFrom(requestedType)) {
                    return new r<T1>(this) {
                        final /* synthetic */ AnonymousClass28 b;

                        public final void a(c out, T1 value) throws IOException {
                            typeAdapter.a(out, value);
                        }

                        public final T1 a(com.google.gson.c.a in) throws IOException {
                            T1 result = typeAdapter.a(in);
                            if (result == null || requestedType.isInstance(result)) {
                                return result;
                            }
                            throw new p("Expected a " + requestedType.getName() + " but was " + result.getClass().getName());
                        }
                    };
                }
                return null;
            }

            public final String toString() {
                return "Factory[typeHierarchy=" + clazz.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
