package net.hockeyapp.android.f;

public final class e {
    private static int a = 6;

    public static void a() {
        h(null);
    }

    public static void a(String tag) {
        h(tag);
    }

    public static void b() {
        h(null);
    }

    public static void b(String tag) {
        h(tag);
    }

    public static void c(String tag) {
        h(tag);
    }

    public static void d() {
        h(null);
    }

    public static void d(String tag) {
        h(tag);
    }

    public static void e(String tag) {
        h(tag);
    }

    public static void e() {
        h(null);
    }

    public static void f(String tag) {
        h(tag);
    }

    public static void f() {
        h(null);
    }

    public static void g(String tag) {
        h(tag);
    }

    private static String h(String tag) {
        if (tag == null || tag.length() == 0 || tag.length() > 23) {
            return "HockeyApp";
        }
        return tag;
    }

    public static void c() {
        h(null);
    }
}
