package defpackage;

/* renamed from: pzn */
public final class pzn {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
