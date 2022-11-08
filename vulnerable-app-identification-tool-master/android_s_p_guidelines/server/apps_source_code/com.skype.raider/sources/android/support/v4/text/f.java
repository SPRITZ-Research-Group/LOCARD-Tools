package android.support.v4.text;

import java.util.Locale;

public final class f {
    public static final e a = new e(null, false);
    public static final e b = new e(null, true);
    public static final e c = new e(b.a, false);
    public static final e d = new e(b.a, true);
    public static final e e = new e(a.a, false);
    public static final e f = f.a;

    private interface c {
        int a(CharSequence charSequence, int i);
    }

    private static class a implements c {
        public static final a a = new a(true);
        public static final a b = new a(false);
        private final boolean c;

        public final int a(CharSequence cs, int count) {
            boolean haveUnlookedFor = false;
            int e = count + 0;
            for (int i = 0; i < e; i++) {
                switch (f.a(Character.getDirectionality(cs.charAt(i)))) {
                    case 0:
                        if (!this.c) {
                            haveUnlookedFor = true;
                            break;
                        }
                        return 0;
                    case 1:
                        if (this.c) {
                            haveUnlookedFor = true;
                            break;
                        }
                        return 1;
                    default:
                        break;
                }
            }
            if (!haveUnlookedFor) {
                return 2;
            }
            if (this.c) {
                return 1;
            }
            return 0;
        }

        private a(boolean lookForRtl) {
            this.c = lookForRtl;
        }
    }

    private static class b implements c {
        public static final b a = new b();

        public final int a(CharSequence cs, int count) {
            int result = 2;
            int e = count + 0;
            for (int i = 0; i < e && result == 2; i++) {
                result = f.b(Character.getDirectionality(cs.charAt(i)));
            }
            return result;
        }

        private b() {
        }
    }

    private static abstract class d implements e {
        private final c a;

        protected abstract boolean a();

        public d(c algorithm) {
            this.a = algorithm;
        }

        public final boolean a(CharSequence cs, int count) {
            if (cs == null || count < 0 || cs.length() - count < 0) {
                throw new IllegalArgumentException();
            } else if (this.a == null) {
                return a();
            } else {
                switch (this.a.a(cs, count)) {
                    case 0:
                        return true;
                    case 1:
                        return false;
                    default:
                        return a();
                }
            }
        }
    }

    private static class e extends d {
        private final boolean a;

        e(c algorithm, boolean defaultIsRtl) {
            super(algorithm);
            this.a = defaultIsRtl;
        }

        protected final boolean a() {
            return this.a;
        }
    }

    private static class f extends d {
        public static final f a = new f();

        public f() {
            super(null);
        }

        protected final boolean a() {
            if (g.a(Locale.getDefault()) == 1) {
                return true;
            }
            return false;
        }
    }

    static int a(int directionality) {
        switch (directionality) {
            case 0:
                return 1;
            case 1:
            case 2:
                return 0;
            default:
                return 2;
        }
    }

    static int b(int directionality) {
        switch (directionality) {
            case 0:
            case 14:
            case 15:
                return 1;
            case 1:
            case 2:
            case 16:
            case 17:
                return 0;
            default:
                return 2;
        }
    }
}
