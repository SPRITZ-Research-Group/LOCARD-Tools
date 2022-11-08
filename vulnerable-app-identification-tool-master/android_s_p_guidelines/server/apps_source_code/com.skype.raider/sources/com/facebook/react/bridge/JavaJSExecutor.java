package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface JavaJSExecutor {

    public static class a extends Exception {
    }

    void close();

    @DoNotStrip
    String executeJSCall(String str, String str2) throws a;

    @DoNotStrip
    void loadApplicationScript(String str) throws a;

    @DoNotStrip
    void setGlobalVariable(String str, String str2);
}
