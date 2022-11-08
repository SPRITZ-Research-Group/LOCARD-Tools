package com.facebook.login;

enum g {
    SUCCESS("success"),
    CANCEL("cancel"),
    ERROR("error");
    
    private final String loggingValue;

    private g(String str) {
        this.loggingValue = str;
    }

    final String a() {
        return this.loggingValue;
    }
}
