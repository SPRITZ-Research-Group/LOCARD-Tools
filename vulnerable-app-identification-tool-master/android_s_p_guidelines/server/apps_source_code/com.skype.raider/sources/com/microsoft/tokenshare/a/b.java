package com.microsoft.tokenshare.a;

enum b {
    NONE("none", null),
    HS256("HS256", new d("HmacSHA256")),
    HS384("HS384", new d("HmacSHA384")),
    HS512("HS512", new d("HmacSHA512")),
    RS256("RS256", new f("SHA256withRSA")),
    RS384("RS384", new f("SHA384withRSA")),
    RS512("RS512", new f("SHA512withRSA"));
    
    private final String h;
    private final a i;

    private b(String id, a cryptoValidator) {
        this.h = id;
        this.i = cryptoValidator;
    }

    public final a a() {
        return this.i;
    }

    public static b a(String value) {
        for (b alg : values()) {
            if (alg.h.equalsIgnoreCase(value)) {
                return alg;
            }
        }
        return null;
    }
}
