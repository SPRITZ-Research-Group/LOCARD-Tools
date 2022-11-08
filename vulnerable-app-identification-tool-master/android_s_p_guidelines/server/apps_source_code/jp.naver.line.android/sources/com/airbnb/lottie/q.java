package com.airbnb.lottie;

import java.util.Map;

public final class q {
    private final Map<String, String> a;
    private boolean b;

    public final String a(String str) {
        if (this.b && this.a.containsKey(str)) {
            return (String) this.a.get(str);
        }
        if (this.b) {
            this.a.put(str, str);
        }
        return str;
    }
}
