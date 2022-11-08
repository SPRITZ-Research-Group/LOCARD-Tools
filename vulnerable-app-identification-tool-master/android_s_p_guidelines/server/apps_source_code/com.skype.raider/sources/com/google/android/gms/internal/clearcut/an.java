package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class an extends IOException {
    private bk a = null;

    public an(String str) {
        super(str);
    }

    static an a() {
        return new an("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static ao b() {
        return new ao("Protocol message tag had invalid wire type.");
    }
}
