package com.google.android.gms.internal.clearcut;

final class by {
    private final String a;
    private int b = 0;

    by(String str) {
        this.a = str;
    }

    final boolean a() {
        return this.b < this.a.length();
    }

    final int b() {
        String str = this.a;
        int i = this.b;
        this.b = i + 1;
        char charAt = str.charAt(i);
        if (charAt < 55296) {
            return charAt;
        }
        i = charAt & 8191;
        int i2 = 13;
        while (true) {
            String str2 = this.a;
            int i3 = this.b;
            this.b = i3 + 1;
            char charAt2 = str2.charAt(i3);
            if (charAt2 < 55296) {
                return (charAt2 << i2) | i;
            }
            i |= (charAt2 & 8191) << i2;
            i2 += 13;
        }
    }
}
