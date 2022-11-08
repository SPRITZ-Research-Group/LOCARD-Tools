package com.slowpath.hockeyapp;

public final class b {
    private String a;
    private int b;
    private String c;

    public b(String token, int authType, String appSecret) {
        this.a = token;
        this.b = authType;
        this.c = appSecret;
        switch (authType) {
            case 1:
                this.b = 1;
                return;
            case 2:
                this.b = 2;
                return;
            case 3:
                this.b = 3;
                return;
            case 4:
                throw new IllegalArgumentException("Web authentication is not supported!");
            default:
                this.b = 0;
                return;
        }
    }

    public final String a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }
}
