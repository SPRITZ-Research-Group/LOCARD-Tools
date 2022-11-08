package com.skype.android.jipc.inout;

public class OutBoolean extends OutInt32 {
    public final boolean a() {
        return b() != 0;
    }

    public String toString() {
        return Boolean.toString(a());
    }
}
