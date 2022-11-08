package com.airbnb.lottie;

import java.util.Map;

public final class l {
    private final Map<String, String> a;
    private boolean b;

    public final String a(String input) {
        if (this.b && this.a.containsKey(input)) {
            return (String) this.a.get(input);
        }
        String text = input;
        if (this.b) {
            this.a.put(input, text);
        }
        return text;
    }
}
