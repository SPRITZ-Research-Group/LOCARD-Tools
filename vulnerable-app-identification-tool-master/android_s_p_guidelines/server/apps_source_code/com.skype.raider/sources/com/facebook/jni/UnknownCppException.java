package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class UnknownCppException extends CppException {
    @DoNotStrip
    public UnknownCppException() {
        super("Unknown");
    }

    @DoNotStrip
    public UnknownCppException(String message) {
        super(message);
    }
}
