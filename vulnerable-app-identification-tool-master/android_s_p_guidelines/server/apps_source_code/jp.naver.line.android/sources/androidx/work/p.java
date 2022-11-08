package androidx.work;

public abstract class p {
    private static p a = null;
    private static final int b = 20;

    public abstract void a(String str, String str2);

    public abstract void a(String str, String str2, Throwable... thArr);

    public abstract void b(String str, String str2, Throwable... thArr);

    public static synchronized void a(p pVar) {
        synchronized (p.class) {
            a = pVar;
        }
    }

    public static String a(String str) {
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(23);
        stringBuilder.append("WM-");
        if (length >= b) {
            stringBuilder.append(str.substring(0, b));
        } else {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            if (a == null) {
                a = new q(3);
            }
            pVar = a;
        }
        return pVar;
    }
}
