package defpackage;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b'\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016¨\u0006\u0019"}, d2 = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Companion", "Default", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
/* renamed from: acth */
public abstract class acth {
    public static final acti a = acti.c;
    public static final actj b = new actj();
    private static final acth c = acpo.a();

    public abstract int a(int i);

    public int b() {
        return a(32);
    }

    public int b(int i) {
        return a(0, i);
    }

    public int a(int i, int i2) {
        if ((i2 > i ? 1 : null) != null) {
            int i3 = i2 - i;
            if (i3 > 0 || i3 == Integer.MIN_VALUE) {
                if (((-i3) & i3) == i3) {
                    i2 = a(31 - Integer.numberOfLeadingZeros(i3));
                } else {
                    int i4;
                    do {
                        i2 = b() >>> 1;
                        i4 = i2 % i3;
                    } while ((i2 - i4) + (i3 - 1) < 0);
                    i2 = i4;
                }
                return i + i2;
            }
            while (true) {
                int b = b();
                if (i <= b) {
                    if (i2 > b) {
                        return b;
                    }
                }
            }
        } else {
            Integer valueOf = Integer.valueOf(i);
            Integer valueOf2 = Integer.valueOf(i2);
            StringBuilder stringBuilder = new StringBuilder("Random range is empty: [");
            stringBuilder.append(valueOf);
            stringBuilder.append(", ");
            stringBuilder.append(valueOf2);
            stringBuilder.append(").");
            throw new IllegalArgumentException(stringBuilder.toString().toString());
        }
    }

    static {
        acpo acpo = acpp.a;
    }
}
