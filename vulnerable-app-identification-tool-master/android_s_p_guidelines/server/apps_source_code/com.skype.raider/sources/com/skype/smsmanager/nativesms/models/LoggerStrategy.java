package com.skype.smsmanager.nativesms.models;

public interface LoggerStrategy {
    void a(String str, String str2);

    void a(String str, String str2, Throwable th);

    void b(String str, String str2);

    void c(String str, String str2);
}
