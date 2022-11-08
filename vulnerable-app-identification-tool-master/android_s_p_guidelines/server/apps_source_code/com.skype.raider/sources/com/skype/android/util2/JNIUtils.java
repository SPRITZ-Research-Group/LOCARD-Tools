package com.skype.android.util2;

public class JNIUtils {
    public static native Object globalRefToObject(long j);

    public static native long objectToGlobalRef(Object obj);
}
