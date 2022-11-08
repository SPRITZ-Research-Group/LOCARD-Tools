package com.a.a.a;

import java.io.IOException;

public class f extends IOException {
    protected d a;

    private f(String str) {
        super(str);
        this.a = null;
    }

    protected f(String str, byte b) {
        this(str);
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        d dVar = this.a;
        if (dVar == null) {
            return message;
        }
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(message);
        if (dVar != null) {
            stringBuilder.append(10);
            stringBuilder.append(" at ");
            stringBuilder.append(dVar.toString());
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
